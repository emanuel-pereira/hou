package smarthome.model;

import java.math.BigDecimal;
import java.util.*;

public class House {

    private Address address;
    private String iD;
    private GeographicalArea gA;
    private RoomList roomList;
    private HouseGridList houseGridList;

    public House() {
        this.roomList = new RoomList();
        this.houseGridList = new HouseGridList();
    }

    public House(Address houseAddress, GeographicalArea ga) {
        this.address = houseAddress;
        this.gA = ga;
        this.roomList = new RoomList();
        this.houseGridList = new HouseGridList();
    }

    public House(String id, Address houseAddress, GeographicalArea ga) {
        this.iD = id;
        this.address = houseAddress;
        this.gA = ga;
        this.roomList = new RoomList();
        this.houseGridList = new HouseGridList();
    }

    public GeographicalArea getHouseGA() {
        return this.gA;
    }

    public void setHouseGA(GeographicalArea houseGA) {
        this.gA = houseGA;
    }

    public void setHouseAddress(String streetName, String houseNumber, String zipCode, double latitude, double longitude, double altitude) {
        this.address = new Address(streetName, houseNumber, zipCode, latitude, longitude, altitude);
    }

    public Address getAddress() {
        return this.address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof House)) {
            return false;
        }
        House house = (House) o;
        return Objects.equals(this.address, house.address) &&
                Objects.equals(this.iD, house.iD) &&
                Objects.equals(this.gA, house.gA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.address, this.iD, this.gA);
    }

    public RoomList getRoomList() {
        return this.roomList;
    }

    public HouseGridList getHGListInHouse() {
        return this.houseGridList;
    }


    /**
     * Method to get a list of rooms not included in the roomList of the houseGrid defined as parameter.
     *
     * @param houseGrid houseGrid instance chosen as parameter
     * @return a list of all rooms that are not included in the houseGrid specified as parameter
     */
    public RoomList getRoomsWithoutGrid(HouseGrid houseGrid) {
        RoomList roomListWithoutHouseGrid = new RoomList();
        RoomList roomListInHouseGrid = houseGrid.getRoomListInAGrid();
        for (Room room : this.roomList.getRoomList()) {
            if (!(roomListInHouseGrid.getRoomList().contains(room)))
                roomListWithoutHouseGrid.addRoom(room);
        }
        return roomListWithoutHouseGrid;
    }

    /**
     * Method to show in a String format a list of rooms not included in the roomList of the houseGrid defined as parameter.
     *
     * @param houseGrid houseGrid instance chosen as parameter
     * @return a list of all rooms in String that are not included in the houseGrid specified as parameter
     */
    public String showRoomsWithoutHouseGrid(HouseGrid houseGrid) {
        RoomList listOfRoomsWithHouseGrid = getRoomsWithoutGrid(houseGrid);
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsWithHouseGrid.getRoomList()) {
            result.append(number++);
            result.append(element);
            result.append(r.getMeteredDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Method to calculate the distance between the location of a house and other location
     *
     * @param aLocation location that is compared to the house location
     * @return distance value between the location of the house and other location
     */

    private double calculateDistance(Location aLocation) {
        return this.address.getGPSLocation().calcLinearDistanceBetweenTwoPoints(this.address.getGPSLocation(), aLocation);
    }

    /**
     * This method checks, for each sensor of the selected sensorType, its distance to the house.
     * Then it returns the sensors that have shortest distance to the house.
     *
     * @param sensorType selected to get the list of the closest sensors.
     * @return a list of sensors of the selected sensorType with the shortest distance to the house.
     */
    private SensorList filterListByTypeAndProximity(SensorType sensorType) {
        SensorList gaSensorList;
        try {
            gaSensorList = this.gA.getSensorListInGA();
        } catch (NullPointerException exception) {
            return new SensorList();
        }

        SensorList sensorListOfType = gaSensorList.getListOfSensorsByType(sensorType);
        double distance;
        List<Sensor> sensorList = sensorListOfType.getSensorList();
        Sensor firstSensor = sensorList.get(0);
        Location sensorLocation = firstSensor.getLocation();
        double minDistance = this.calculateDistance(sensorLocation);
        SensorList closestSensors = new SensorList();

        for (Sensor sensor : sensorList) {
            Location location = sensor.getLocation();
            distance = calculateDistance(location);
            if (BigDecimal.valueOf(distance).equals(BigDecimal.valueOf(minDistance))) {
                closestSensors.addSensor(sensor);
            }
            if (distance < minDistance) {
                minDistance = distance;
                closestSensors.getSensorList().clear();
                closestSensors.addSensor(sensor);
            }
        }
        return closestSensors;
    }

    public SensorList filterListByTypeByIntervalAndDistance(SensorType type, Calendar startDate, Calendar endDate) {
        SensorList closestSensorsOfType = this.filterListByTypeAndProximity(type);
        if (closestSensorsOfType.size() == 0)
            return new SensorList();
        else {
            SensorList closestSensorWithReadingsInPeriod = new SensorList();
            for (Sensor sensor : closestSensorsOfType.getSensorList()) {
                ReadingList readingListInPeriod = sensor.getReadingList().filterByDate(startDate, endDate);
                if (!readingListInPeriod.getReadingsList().isEmpty())
                    closestSensorWithReadingsInPeriod.addSensor(sensor);
            }
            return closestSensorWithReadingsInPeriod;
        }
    }

    /**
     * Method to obtain the closest sensor to the house with the most recent reading in a given time period
     *
     * @param type      type of sensor
     * @param startDate first date of the interval
     * @param endDate   last date of the interval
     * @return the closest sensor to the house
     */

    public Sensor filterByTypeByIntervalAndDistance(SensorType type, Calendar startDate, Calendar endDate) {
        SensorList closestSensorList = this.filterListByTypeByIntervalAndDistance(type, startDate, endDate);
        Sensor closestSensor = closestSensorList.getSensorList().get(0);
        ReadingList readingsInPeriod = closestSensor.getReadingList().filterByDate(startDate, endDate);
        Reading latestReadingInPeriod = readingsInPeriod.getLastReading();

        for (Sensor sensor : closestSensorList.getSensorList()) {
            ReadingList sensorReadingsInPeriod = sensor.getReadingList().filterByDate(startDate, endDate);
            Reading sensorLatestReadingInPeriod = sensorReadingsInPeriod.getLastReading();

            if (sensorLatestReadingInPeriod.getDateAndTime().after(latestReadingInPeriod.getDateAndTime())) {
                latestReadingInPeriod = sensorLatestReadingInPeriod;
                closestSensor = sensor;
            }
        }
        return closestSensor;
    }

    /**
     * Method to calculate the daily average of the sensor that is closer to the house and has the most recent readings
     *
     * @param type      type of sensor
     * @param startDate first date of the interval
     * @param endDate   last date of the interval
     * @return the value of the daily average of the readings in the given time period
     */

    public double averageOfReadingsInPeriod(SensorType type, Calendar startDate, Calendar endDate) {
        Sensor closestSensorWithLatestReadingsInPeriod = filterByTypeByIntervalAndDistance(type, startDate, endDate);
        ReadingList readingsFromSensorInPeriod = closestSensorWithLatestReadingsInPeriod.getReadingList();
        ReadingList readingsInPeriod = readingsFromSensorInPeriod.filterByDate(startDate, endDate);

        double sum = 0;
        int counter = 0;
        double average = 0;

        for (; startDate.before(endDate) || startDate.equals(endDate); startDate.add(Calendar.DAY_OF_MONTH, 1)) {
            if (readingsFromSensorInPeriod.getReadingsInSpecificDay(startDate).size() != 0) {
                sum = sum + readingsInPeriod.dailyAverageOfReadings(startDate);
                counter++;
                average = sum / counter;
            }
        }
        return average;
    }


    /**
     * Method that selects the sensor with the latest readings, when the closest sensors to the house of the selected
     * sensorType are at the same distance to the house.
     *
     * @param sensorType type of Sensor selected
     * @return the sensor with the most recent reading of the closest sensors to the house.
     */
    public Sensor getClosestSensorWithLatestReading(SensorType sensorType) {
        SensorList closestSensors = this.filterListByTypeAndProximity(sensorType);
        Sensor closestSensorWithLatestReading = closestSensors.getSensorList().get(0);
        Reading lastReading = closestSensorWithLatestReading.getLastReadingPerSensor();
        Calendar lastDate = lastReading.getDateAndTime();
        for (Sensor sensor : closestSensors.getSensorList()) {
            Reading sensorLastReading = sensor.getLastReadingPerSensor();
            if (sensorLastReading.getDateAndTime().after(lastDate)) {
                lastDate = sensor.getLastReadingPerSensor().getDateAndTime();
                closestSensorWithLatestReading = sensor;
            }
        }
        return closestSensorWithLatestReading;
    }


    /**
     * Method that checks for the closest sensors to the house of the selected SensorType, which of them have readings in the inputDate.
     *
     * @param inputDate  selected to check which closest sensors have readings in this date
     * @param sensorType selected to check the closest sensors of that type
     * @return the closest sensors to the house of the selected SensorType that have readings in the specified date.
     */
    private SensorList getClosestSensorsWithReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {
        SensorList closestSensorsByType = this.filterListByTypeAndProximity(sensorType);
        SensorList sensorsWithReadingsInDate = new SensorList();
        for (Sensor sensor : closestSensorsByType.getSensorList()) {
            ReadingList readingListInDay = sensor.getReadingList().getReadingsInSpecificDay(inputDate);
            if (!(readingListInDay.getReadingsList().isEmpty())) {
                sensorsWithReadingsInDate.addSensor(sensor);
            }
        }

        return sensorsWithReadingsInDate;
    }

    /**
     * Boolean method to check if there is any closest sensors to the house of a specific type that has readings in the date inputted as parameter
     *
     * @param inputDate  date in GregorianCalendar format for which this method checks if exists any closest sensors to the house with readings
     * @param sensorType selected to check sensors of that type
     * @return true if at least exists one of the possible closest sensors with readings in the inputDate, otherwise returns false
     */
    public boolean closestSensorsWithReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {
        SensorList closestSensorsByType = this.getClosestSensorsWithReadingsInDate(inputDate, sensorType);
        return closestSensorsByType.size() != 0;
    }


    public boolean checkIfClosestSensorsHasReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        SensorList closestSensorsByType = this.filterListByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        return closestSensorsByType.size() != 0;
    }


    /**
     * Method that checks for the closest sensors to the house of the selected SensorType that have readings in the specified date,
     * which one has the latest reading in the specified date
     *
     * @param inputDate  selected to check which closestSensor has the latest readings in that date
     * @param sensorType selected to check the closest sensors of that type
     * @return the closest sensor with the latest readings in the specified date.
     */
    public Sensor getSensorOfTypeWithLatestReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {

        SensorList closestSensors = this.getClosestSensorsWithReadingsInDate(inputDate, sensorType);

        Sensor closestSensorWithLatestReading = closestSensors.getSensorList().get(0);

        ReadingList readingListInDay = closestSensorWithLatestReading.getReadingList().getReadingsInSpecificDay(inputDate);
        Reading lastReading = readingListInDay.getLastReading();

        Calendar lastDate = lastReading.getDateAndTime();

        for (Sensor sensor : closestSensors.getSensorList()) {
            ReadingList sensorReadingListInDay = sensor.getReadingList().getReadingsInSpecificDay(inputDate);
            Reading sensorLastReadingInDay = sensorReadingListInDay.getLastReading();
            Calendar timeOfLastReading = sensorLastReadingInDay.getDateAndTime();
            if (timeOfLastReading.after(lastDate)) {
                lastDate = sensor.getLastReadingPerSensor().getDateAndTime();
                closestSensorWithLatestReading = sensor;
            }
        }
        return closestSensorWithLatestReading;
    }


    public List<String> getListOfDeviceTypes() {
        Configuration c = new Configuration();
        return c.getDeviceTypes();

    }


    //DEPRECATED. Do not use.
    public String showDeviceTypesList() {
        StringBuilder result = new StringBuilder();
        int number = 1;
        for (String deviceType : getListOfDeviceTypes()) {
            result.append(number);
            result.append(" - ");
            result.append(deviceType);
            result.append("\n");
            number++;
        }
        return result.toString();
    }

    public List<Metered> getMetered() {
        List<Metered> meteredList = new ArrayList<>();
        for (HouseGrid houseGrid : this.houseGridList.getHouseGridList()) {
            List<Room> tempRoomList = houseGrid.getRoomListInAGrid().getRoomList();
            List<Metered> deviceList = houseGrid.getRoomListInAGrid().getMeteredDevicesList();
            meteredList.add(houseGrid);
            meteredList.addAll(tempRoomList);
            meteredList.addAll(deviceList);
        }
        return meteredList;
    }

    public String showMetered() {
        StringBuilder meteredList = new StringBuilder();
        int nr = 1;
        for (Metered metered : this.getMetered()) {
            meteredList.append(nr);
            meteredList.append(" - ");
            meteredList.append(metered.getMeteredDesignation());
            meteredList.append("\n");
            nr++;
        }
        return meteredList.toString();
    }
}



