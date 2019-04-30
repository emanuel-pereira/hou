package smarthome.model;

import java.math.BigDecimal;
import java.util.*;

public class House {

    private static Address address;
    private static GeographicalArea gA;
    private static RoomList roomList;
    private static HouseGridList houseGridList;
    private static House theHouse = null;


    public static House getHouseInstance(Address address, GeographicalArea ga) {

        if (theHouse == null){
            theHouse = new House(address,ga);
        }

        return theHouse;
    }


    private House(Address houseAddress, GeographicalArea ga) {
        address = houseAddress;
        gA = ga;
        roomList = new RoomList();
        houseGridList = new HouseGridList();
    }


    public static GeographicalArea getHouseGA() {
        return gA;
    }

    public static void setHouseGA(GeographicalArea houseGA) {
        gA = houseGA;
    }

    public static void setHouseAddress(Address houseAddress) {
        address = houseAddress;
    }

    public static Address getAddress() {
        return address;
    }

    public static RoomList getHouseRoomList() {
        return roomList;
    }

    public static HouseGridList getGridListInHouse() {
        return houseGridList;
    }

    /**
     * Method to get a list of rooms not included in the roomList of the houseGrid defined as parameter.
     *
     * @param houseGrid houseGrid instance chosen as parameter
     * @return a list of all rooms that are not included in the houseGrid specified as parameter
     */
    public static RoomList getHouseRoomsWithoutGrid(HouseGrid houseGrid) {
        RoomList roomListWithoutHouseGrid = new RoomList();
        RoomList roomListInHouseGrid = houseGrid.getRoomListInAGrid();
        for (Room room : roomList.getRoomList()) {
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
    public static String showHouseRoomsWithoutHouseGrid(HouseGrid houseGrid) {
        RoomList listOfRoomsWithHouseGrid = getHouseRoomsWithoutGrid(houseGrid);
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
    public static double calculateDistance(Location aLocation) {
        return address.getGPSLocation().calcLinearDistanceBetweenTwoPoints(address.getGPSLocation(), aLocation);
    }

    /**
     * This method checks, for each sensor of the selected sensorType, its distance to the house.
     * Then it returns the sensors that have shortest distance to the house.
     *
     * @param sensorType selected to get the list of the closest sensors.
     * @return a list of sensors of the selected sensorType with the shortest distance to the house.
     */
    public static SensorList filterListByTypeAndProximity(SensorType sensorType) {
        SensorList gaSensorList;
        try {
            gaSensorList = gA.getSensorListInGA();
        } catch (NullPointerException exception) {
            return new SensorList();
        }

        SensorList sensorListOfType = gaSensorList.getListOfSensorsByType(sensorType);
        double distance;
        List<Sensor> sensorList = sensorListOfType.getSensorList();
        Sensor firstSensor = sensorList.get(0);
        Location sensorLocation = firstSensor.getLocation();
        double minDistance = calculateDistance(sensorLocation);
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

    public static SensorList filterListByTypeByIntervalAndDistance(SensorType type, Calendar startDate, Calendar endDate) {
        SensorList closestSensorsOfType = filterListByTypeAndProximity(type);
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

    public static Sensor filterByTypeByIntervalAndDistance(SensorType type, Calendar startDate, Calendar endDate) {
        SensorList closestSensorList = filterListByTypeByIntervalAndDistance(type, startDate, endDate);
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

    public static double averageOfReadingsInPeriod(SensorType type, Calendar startDate, Calendar endDate) {
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
    public static Sensor getClosestSensorWithLatestReading(SensorType sensorType) {
        SensorList closestSensors = filterListByTypeAndProximity(sensorType);
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
    public static SensorList getClosestSensorsWithReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {
        SensorList closestSensorsByType = filterListByTypeAndProximity(sensorType);
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
    public static boolean closestSensorsWithReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {
        SensorList closestSensorsByType = getClosestSensorsWithReadingsInDate(inputDate, sensorType);
        return closestSensorsByType.size() != 0;
    }


    public static boolean checkIfClosestSensorsHasReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        SensorList closestSensorsByType = filterListByTypeByIntervalAndDistance(sensorType, startDate, endDate);
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
    public static Sensor getSensorOfTypeWithLatestReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {

        SensorList closestSensors = getClosestSensorsWithReadingsInDate(inputDate, sensorType);

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


    public static List<String> getListOfDeviceTypes() {
        Configuration c = new Configuration();
        return c.getDeviceTypes();

    }


    //DEPRECATED. Do not use.
    public static String showDeviceTypesList() {
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

    public static List<Metered> getMetered() {
        List<Metered> meteredList = new ArrayList<>();
        for (HouseGrid houseGrid : houseGridList.getHouseGridList()) {
            List<Room> tempRoomList = houseGrid.getRoomListInAGrid().getRoomList();
            List<Metered> deviceList = houseGrid.getRoomListInAGrid().getMeteredDevicesList();
            meteredList.add(houseGrid);
            meteredList.addAll(tempRoomList);
            meteredList.addAll(deviceList);
        }
        return meteredList;
    }

    public static String showMetered() {
        StringBuilder meteredList = new StringBuilder();
        int nr = 1;
        for (Metered metered : getMetered()) {
            meteredList.append(nr);
            meteredList.append(" - ");
            meteredList.append(metered.getMeteredDesignation());
            meteredList.append("\n");
            nr++;
        }
        return meteredList.toString();
    }

    public static boolean checkIfLocationExists() {
        return getAddress() != null;
    }
}



