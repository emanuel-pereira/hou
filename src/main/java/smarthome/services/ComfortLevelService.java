package smarthome.services;

import smarthome.dto.RoomDTO;
import smarthome.mapper.RoomMapper;
import smarthome.model.*;
import smarthome.controller.*;

import java.util.*;

public class ComfortLevelService {

    private RoomList roomList;
    private GeographicalArea geographicalArea;
    private SensorList sensorsListInGeoArea;
    private Sensor geoAreaSensor;

    //private House house = House.getHouseInstance();
    private static final String TEMPERATURE = "temperature";
    private RoomMapper roomMapper = new RoomMapper();

    public ComfortLevelService() {
        this.roomList = House.getHouseRoomList();
        this.geographicalArea = House.getHouseGA();
        this.sensorsListInGeoArea = geographicalArea.getSensorListInGA();

    }


    // Methods for validation
    public boolean checkIfGeoAreaHasTemperatureSensor() {
        Sensor s = geographicalArea.getSensorListInGA().getRequiredSensorPerType(TEMPERATURE);
        return (s != null);
    }

    public boolean checkIfHouseHasRooms() {
        return !roomList.getRoomList().isEmpty();
    }

    /**
     * @param sensorType a string containing a sensor type designation, e.g. "TEMPERATURE"
     * @return a list of rooms with the selected sensor type
     */
    private RoomList getRoomsWithSensorByType(String sensorType) {
        RoomList roomsWithSensorsOfType = new RoomList();
        for (Room room : roomList.getRoomList()) {
            if (room.getSensorListInRoom().checkIfRequiredSensorTypeExists(sensorType)) {
                roomsWithSensorsOfType.addRoom(room);
            }
        }
        return roomsWithSensorsOfType;
    }

    private RoomList getRoomsWithSensorsWithReadings(RoomList roomList) {
        RoomList roomsWithSensorsWithReadings = new RoomList();

        for (Room room : roomList.getRoomList())

            if (checkThatAllSensorsHaveReadings(room)) {
                roomsWithSensorsWithReadings.addRoom(room);
            }

        return roomsWithSensorsWithReadings;
    }

    // WRONG? Unneeded?
    private boolean checkThatAllSensorsHaveReadings(Room room) {
        for (Sensor sensor : room.getSensorListInRoom().getSensorList()) {
            if (sensor.getSensorBehavior().getReadingList().getReadingsList().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // User story specific methods, which should not be used elsewhere
    public boolean validateRoomsHaveTemperatureSensors() {
        return !getRoomsWithSensorByType(TEMPERATURE).getRoomList().isEmpty();
    }

    public boolean validateTemperatureSensorsHaveReadings() {
        RoomList roomList1 = getRoomsWithSensorByType(TEMPERATURE); //get all rooms with TEMPERATURE sensors
        RoomList roomList2 = getRoomsWithSensorsWithReadings(roomList1); // filter previous list
        return !roomList2.getRoomList().isEmpty();
    }

    private RoomList getFilteredRoomList() {
        RoomList r = getRoomsWithSensorByType(TEMPERATURE);
        return getRoomsWithSensorsWithReadings(r);
    }

    // DTOs for the Controller/UI

    public List<RoomDTO> getRoomListDTO() {
        List<RoomDTO> roomListDTO = new ArrayList<>();
        roomList = getFilteredRoomList();
        for (Room room : roomList.getRoomList()) {
            RoomDTO r = roomMapper.toDto(room);
            roomListDTO.add(r);
        }
        return roomListDTO;
    }

    public Room setRoomByDTO(RoomDTO roomDTO) {
        Room selectedRoom;
        RoomList roomList = getFilteredRoomList();
        selectedRoom = roomList.getRoomById(roomDTO.getID());
        return selectedRoom;
    }

    private Sensor getSensorOnRoomByType(String sensorType, RoomDTO roomDTO) {
        Room room = setRoomByDTO(roomDTO);
        SensorList sensorList = room.getSensorListInRoom();
        return sensorList.getRequiredSensorPerType(sensorType);
    }


    /**
     * Calculates the maximum acceptable TEMPERATURE in a room for a given comfort category as per EN 15251:2006
     *
     * @param outsideTemperature average outside TEMPERATURE for a given day (t0)
     * @param category           the comfort level category
     * @return the maximum acceptable TEMPERATURE or 0 Kelvin if an error occurred
     */
    public double getMaxTemperatureForComfortLevel(double outsideTemperature, int category) {
        int k = category + 1;
        if (validateComfortLevelCategory(category)) {
            return (0.33 * outsideTemperature + 18.8 + k);
        }
        return -273.15; //absolute zero. Not comfortable.
    }

    /**
     * Calculates the minimum acceptable TEMPERATURE in a room for a given comfort category as per EN 15251:2006
     *
     * @param outsideTemperature average outside TEMPERATURE for a given day (t0)
     * @param category           the comfort level category
     * @return the minimum acceptable TEMPERATURE or 0 Kelvin if an error occurred
     */
    public double getMinTemperatureForComfortLevel(double outsideTemperature, int category) {
        int k = category + 1;
        if (validateComfortLevelCategory(category)) {
            return (0.33 * outsideTemperature + 18.8 - k);
        }
        return -273.15; //absolute zero. Not comfortable.
    }

    /**
     * Validates the category for TEMPERATURE comfort level
     *
     * @param category must be 1,2 or 3
     * @return true if category is valid
     */
    private boolean validateComfortLevelCategory(int category) {
        return (category >= 1 && category <= 3);
    }

    /**
     * @param maxOrMin  specifies whether the user wants to check the thermal comfort versus the max or min temperature as defined in the standard.
     * @param category  the category as per EN15251:2006
     * @param startDate user defined time interval for the verification.
     * @param endDate
     * @return
     */
    public String calculateThermalComfort(RoomDTO roomDTO, boolean maxOrMin, int category, Calendar startDate, Calendar endDate) {
        //Get selected sensors
        Sensor sensorInRoom = getSensorOnRoomByType(TEMPERATURE, roomDTO);
        Sensor sensorInGA = this.sensorsListInGeoArea.getRequiredSensorPerType(TEMPERATURE);

        //Get reading lists
        ReadingList sensorInRoomReadings = sensorInRoom.getSensorBehavior().getReadingList();
        ReadingList sensorInGAReadings = sensorInGA.getSensorBehavior().getReadingList();

        List<Reading> finalList = new ArrayList<>();

        while (!startDate.after(endDate)) {
            double temp = -237.15;

            if (sensorInGAReadings.getReadingsInSpecificDay(startDate).size() != 0) {
                temp = sensorInGAReadings.dailyAverageOfReadings(startDate);

                ReadingList sensorInRoomReadingsForDay = sensorInRoomReadings.getReadingsInSpecificDay(startDate);
                List<Reading> dailyResult = checkComfort(sensorInRoomReadingsForDay, category, maxOrMin, temp);
                finalList.addAll(dailyResult);

            }

            startDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        StringBuilder result = new StringBuilder();
        for (Reading r : finalList
        ) {

            int hour = r.getDateAndTime().get(Calendar.HOUR_OF_DAY);

            String padding = "";
            if (hour < 10) {
                padding = "0";
            }

            result.append(r.getDateOfReadingAsString() + " " + padding + hour + ":00");
            result.append(" -> ");

            double reading = (double) Math.round((r.returnValue() * 100)) / 100;

            result.append(reading + " ºC");
            result.append("\n");
        }
        if (result.toString().isEmpty()) {
            result.append("No readings outside the comfort level were found in the selected time interval.\n");
        }

        return result.toString();
    }


    private List<Reading> checkComfort(ReadingList readingList, int category, boolean maxOrMin, double outsideTemperature) {
        List<Reading> result = new ArrayList<>();


        //case MAX
        if (maxOrMin) {
            for (Reading r : readingList.getReadingsList()) {
                if (r.returnValue() > getMaxTemperatureForComfortLevel(outsideTemperature, category)) {
                    result.add(r);
                }
            }
            return result;
        }

        //case MIN
        for (Reading r : readingList.getReadingsList()) {
            if (r.returnValue() < getMinTemperatureForComfortLevel(outsideTemperature, category)) {
                result.add(r);
            }
        }
        return result;
    }

// Auxiliary methods for dealing with dates

    /**
     * Checks if two time intervals have overlap and returns the earliest and latest date for which it occurs
     *
     * @param startDate1
     * @param endDate1
     * @param startDate2
     * @param endDate2
     * @return true if overlaps.
     */
    private List<Calendar> overlapInterval(Calendar startDate1, Calendar endDate1, Calendar startDate2, Calendar endDate2) {
        List<Calendar> interval = new ArrayList<>();
        Calendar earliestDate = new GregorianCalendar();
        Calendar latestDate = new GregorianCalendar();

        //overlap calculation.
        if (startDate2.after(startDate1) && startDate2.before(endDate1)) {
            earliestDate = startDate1;
        }
        if (startDate1.after(startDate2) && startDate1.before(endDate2)) {
            earliestDate = startDate2;
        }
        if (endDate2.after(endDate1) && startDate2.before(endDate1)) {
            latestDate = endDate2;
        }
        if (endDate1.after(endDate2) && startDate1.before(endDate2)) {
            latestDate = endDate1;
        }
        interval.add(earliestDate);
        interval.add(latestDate);
        return interval;
    }

    private Calendar getDayAfter(Calendar day) {
        day.add(Calendar.DAY_OF_MONTH, 1);
        return day;
    }
}
