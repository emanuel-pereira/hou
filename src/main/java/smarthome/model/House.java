package smarthome.model;

import java.math.BigDecimal;
import java.util.*;

public class House {

    private Address mAddress;
    private String mID;
    private GeographicalArea mGA;
    private RoomList mRoomList;
    private HouseGridList mHGListInHouse;


    public House() {
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }

    public House(Address houseAddress, GeographicalArea ga) {
        mAddress = houseAddress;
        mGA = ga;
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }

    public House(String id, Address houseAddress, GeographicalArea ga) {
        mID = id;
        mAddress = houseAddress;
        mGA = ga;
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }


    public void setHouseGA(GeographicalArea houseGA) {
        mGA = houseGA;
    }

    public GeographicalArea getHouseGA() {
        return mGA;
    }


    public void setHouseAddress(String streetName, String houseNumber, String zipCode, double latitude, double longitude, double altitude) {
        mAddress = new Address(streetName, houseNumber, zipCode, latitude, longitude, altitude);
    }

    public Address getAddress() {

        return mAddress;
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
        return Objects.equals(mAddress, house.mAddress) &&
                Objects.equals(mID, house.mID) &&
                Objects.equals(mGA, house.mGA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAddress, mID, mGA);
    }

    public RoomList getRoomList() {
        return mRoomList;
    }

    public HouseGridList getHGListInHouse() {
        return mHGListInHouse;
    }

    public RoomList getAvailableRoomsForGrid (){
        RoomList availableRooms = new RoomList();
        availableRooms.getRoomList().addAll(this.getRoomList().getRoomList());
        return availableRooms;
    }

    public RoomList getRoomListNotInAGrid(int indexHG){
        RoomList roomsNotInAGrid = new RoomList();
        for(Room r : this.getRoomList().getRoomList()){
            if(!this.getHGListInHouse().get(indexHG).getRoomListInAGrid().getRoomList().contains(r)){
                roomsNotInAGrid.addRoom(r);
            }
        }
        return roomsNotInAGrid;
    }

    public RoomList getRoomsWithoutAnyGrid(){
        RoomList roomsNotInAnyGrid = new RoomList();
        for (int indexHG = 0; indexHG<getHGListInHouse().getSize();indexHG++){
            roomsNotInAnyGrid.getRoomList().addAll(this.getRoomListNotInAGrid(indexHG).getRoomList());
        }
        return roomsNotInAnyGrid;
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
        for (Room room : mRoomList.getRoomList()) {
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
            result.append(r.getName());
            result.append("\n");
        }
        return result.toString();
    }

    private double calculateDistance(Location aLocation) {
        return mAddress.getGPSLocation().calcLinearDistanceBetweenTwoPoints(mAddress.getGPSLocation(), aLocation);
    }

    /**
     * This method checks for each sensor of the selected sensorType, its distance to the house.
     * Then it returns the sensors that have shortest distance to the house.
     *
     * @param sensorType selected to get the list of the closest sensors.
     * @return a list of sensors of the selected sensorType with the shortest distance to the house.
     */
    private SensorList getClosestSensorsOfType(SensorType sensorType) {
        SensorList gaSensorList = mGA.getSensorListInGA();

        SensorList sensorListOfType = gaSensorList.getListOfSensorsByType(sensorType);
        double distance;
        double minDistance = this.calculateDistance(sensorListOfType.getSensorList().get(0).getLocation());
        SensorList closestSensors = new SensorList();

        for (Sensor sensor : sensorListOfType.getSensorList()) {
            distance = calculateDistance(sensor.getLocation());
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

    public SensorList getClosestSensorsOfTypeWithReadingsInPeriod(SensorType type, Calendar startDate, Calendar endDate) {
        SensorList closestSensorsOfType = this.getClosestSensorsOfType(type);
        SensorList closestSensorWithReadingsInPeriod = new SensorList();


        for (Sensor sensor : closestSensorsOfType.getSensorList()) {

            ReadingList readingListInPeriod = sensor.getReadingList().getReadingsInPeriod(startDate, endDate);

            if (!readingListInPeriod.getReadingList().isEmpty()) {
                closestSensorWithReadingsInPeriod.addSensor(sensor);
            }
        }
        return closestSensorWithReadingsInPeriod;

    }

    /**
     * Method to obtain the closest sensor to the house with the most recent reading in a given time period
     *
     * @param type type of sensor
     * @param startDate first date of the interval
     * @param endDate last date of the interval
     * @return the closest sensor to the house
     */

    public Sensor getClosestSensorWithLatestReadingsInPeriod(SensorType type, Calendar startDate, Calendar endDate) {
        SensorList closestSensorsWithReadingsInPeriod = this.getClosestSensorsOfTypeWithReadingsInPeriod(type, startDate, endDate);
        Sensor closestSensorWithLatestReadingsInPeriod = closestSensorsWithReadingsInPeriod.getSensorList().get(0);
        ReadingList readingsInPeriod = closestSensorWithLatestReadingsInPeriod.getReadingList().getReadingsInPeriod(startDate, endDate);
        Reading latestReadingInPeriod = readingsInPeriod.getLastReading();

        for (Sensor sensor : closestSensorsWithReadingsInPeriod.getSensorList()) {
            ReadingList sensorReadingsInPeriod = sensor.getReadingList().getReadingsInPeriod(startDate, endDate);
            Reading sensorLatestReadingInPeriod = sensorReadingsInPeriod.getLastReading();

            if (sensorLatestReadingInPeriod.getDateAndTime().after(latestReadingInPeriod.getDateAndTime())) {
                latestReadingInPeriod = sensorLatestReadingInPeriod;
                closestSensorWithLatestReadingsInPeriod = sensor;
            }

        }
        return closestSensorWithLatestReadingsInPeriod;

    }

    /**
     * Method to calculate the daily average of the sensor that is closer to the house and has the most recent readings
     *
     * @param type type of sensor
     * @param startDate first date of the interval
     * @param endDate last date of the interval
     * @return the value of the daily average of the readings in the given time period
     */

    public double averageOfReadingsInPeriod(SensorType type, Calendar startDate, Calendar endDate) {
        Sensor closestSensorWithLatestReadingsInPeriod = getClosestSensorWithLatestReadingsInPeriod(type, startDate, endDate);
        ReadingList readingsInPeriod = closestSensorWithLatestReadingsInPeriod.getReadingList().getReadingsInPeriod(startDate, endDate);

        double sum = 0;
        int counter = 0;

        for (Calendar date = startDate; date.before(endDate)|| date.equals(endDate); date.add(Calendar.DAY_OF_MONTH, 1)) {

            if (readingsInPeriod.dailyAvgOfReadings(date.get(Calendar.DAY_OF_MONTH)) != -1000) {
                sum += readingsInPeriod.dailyAvgOfReadings(date.get(Calendar.DAY_OF_MONTH));
                counter++;
            }
        }
        return sum / counter;

    }


    /**
     * Method that selects the sensor with the latest readings, when the closest sensors to the house of the selected
     * sensorType are at the same distance to the house.
     *
     * @param sensorType type of Sensor selected
     * @return the sensor with the most recent reading of the closest sensors to the house.
     */
    public Sensor getClosestSensorWithLatestReading(SensorType sensorType) {
        SensorList closestSensors = this.getClosestSensorsOfType(sensorType);
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
        SensorList closestSensorsByType = this.getClosestSensorsOfType(sensorType);
        SensorList sensorsWithReadingsInDate = new SensorList();
        for (Sensor sensor : closestSensorsByType.getSensorList()) {
            ReadingList readingListInDay = sensor.getReadingList().getReadingsInSpecificDay(inputDate);
            if (!(readingListInDay.getReadingList().isEmpty())) {
                sensorsWithReadingsInDate.addSensor(sensor);
            }
        }

        return sensorsWithReadingsInDate;
    }

    /**
     * Boolean method to check if there is any closest sensors to the house of a specific type that has readings in the date inputted as parameter
     * @param inputDate date in GregorianCalendar format for which this method checks if exists any closest sensors to the house with readings
     * @param sensorType selected to check sensors of that type
     * @return true if at least exists one of the possible closest sensors with readings in the inputDate, otherwise returns false
     */
    public boolean closestSensorsWithReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {
        SensorList closestSensorsByType = this.getClosestSensorsWithReadingsInDate(inputDate, sensorType);
        return closestSensorsByType.size() != 0;
    }


    public boolean checkIfClosestSensorsHaveReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        SensorList closestSensorsByType = this.getClosestSensorsOfTypeWithReadingsInPeriod(sensorType,startDate,endDate);
        return closestSensorsByType.size() != 0;
    }



    /**
     * Method that checks for the closest sensors to the house of the selected SensorType that have readings in the specified date,
     * which one has the latest reading in the specified date
     *
     * @param inputDate  selected to check which closestSensor has the lattest readings in that date
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


    private List<String> getListOfDeviceTypesInString() {
        Configuration c = new Configuration();
        return c.getDeviceTypes();

    }

    public List<DeviceType> getListOfDeviceTypes() {
        Configuration c = new Configuration();
        List<String> listOfDeviceTypes = c.getDeviceTypes();
        List<DeviceType> deviceTypeList = new ArrayList<>();
        for (String type : listOfDeviceTypes)
            deviceTypeList.add(new DeviceType(type));
        return deviceTypeList;
    }

    public String showDeviceTypesList() {
        StringBuilder result = new StringBuilder();
        int number = 1;
        for (String deviceType : getListOfDeviceTypesInString()) {
            result.append(number);
            result.append(" - ");
            result.append(deviceType);
            result.append("\n");
            number++;
        }
        return result.toString();
    }
}



