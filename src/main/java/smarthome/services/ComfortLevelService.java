package smarthome.services;

import smarthome.dto.RoomDTO;
import smarthome.mapper.RoomMapper;
import smarthome.model.*;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static smarthome.model.House.getHouseGA;
import static smarthome.model.House.getHouseRoomList;

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
        SensorList sensorsInRoom = new SensorList();
        for (Room room : roomList.getRoomList())

            if (checkThatAllSensorsHaveReadings(room)) {
                roomsWithSensorsWithReadings.addRoom(room);
            }

        return roomsWithSensorsWithReadings;
    }

    // WRONG? Unneeded?
    private boolean checkThatAllSensorsHaveReadings(Room room) {
        for (Sensor sensor : room.getSensorListInRoom().getSensorList()) {
            if (sensor.getReadingList().getReadingsList().isEmpty()) {
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


    private List<Double> getAverageOutsideTemperature(Calendar startDate, Calendar endDate, Sensor sensor) {
        List<Double> results = new ArrayList<>();
        ReadingList sensorReadings = sensor.getReadingList();
        sensorReadings = sensorReadings.filterByDate(startDate,endDate);

        double outsideTemperature;
        GregorianCalendar aDate = (GregorianCalendar) startDate;

        while (aDate.before(endDate)) {
            outsideTemperature = sensorReadings.dailyAverageOfReadings(aDate);
            results.add(outsideTemperature);
            aDate = getDayAfter(aDate);
        }
        return results;
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
    public String calculateThermalComfort(RoomDTO roomDTO, boolean maxOrMin, int category, GregorianCalendar startDate, GregorianCalendar endDate) {

        //Get selected sensors
        Sensor sensorInRoom = getSensorOnRoomByType(TEMPERATURE, roomDTO);
        Sensor sensorInGA = this.sensorsListInGeoArea.getRequiredSensorPerType(TEMPERATURE);


        //Get reading lists
        ReadingList sensorInRoomReadings = sensorInRoom.getReadingList();
        ReadingList sensorInGAReadings = sensorInGA.getReadingList();

        // Calculate overlap of readings interval
        Calendar GAReadingsStartDate = sensorInRoomReadings.getStartDateOfReadings();
        Calendar GAReadingsEndDate = sensorInGAReadings.getEndDateOfReadings();

        Calendar RoomReadingsStartDate = sensorInRoomReadings.getStartDateOfReadings();
        Calendar RoomReadingsEndDate = sensorInRoomReadings.getEndDateOfReadings();

        List<Calendar> dateInterval = overlapInterval(GAReadingsStartDate, GAReadingsEndDate, RoomReadingsStartDate, RoomReadingsEndDate);

        Calendar intervalStart = dateInterval.get(0);
        Calendar intervalEnd = dateInterval.get(1);

        //Overlap readings and user requested interval
        List<Calendar> calculationInterval = overlapInterval(intervalStart, intervalEnd, startDate, endDate);
        Calendar calculationStart = calculationInterval.get(0);
        Calendar calculationEnd = calculationInterval.get(1);

        List<Double> outsideTemperature = getAverageOutsideTemperature(calculationStart, calculationEnd, sensorInGA);


        GregorianCalendar tempDate = startDate;


        HashMap<GregorianCalendar, Double> result = new HashMap<>();

        for (Double value : outsideTemperature) {
            ReadingList rl = sensorInRoomReadings.getReadingsInSpecificDay(tempDate);
            result.putAll(checkComfort(rl, category, maxOrMin, value));
            tempDate = getDayAfter(tempDate);
        }

        String resultDTO = result.toString();

        return resultDTO;
    }


    private HashMap<GregorianCalendar, Double> checkComfort(ReadingList readingList, int category, boolean maxOrMin, double outsideTemperature) {
        HashMap<GregorianCalendar, Double> result = new HashMap<>();

        //case MAX
        if (maxOrMin) {
            for (Reading r : readingList.getReadingsList()) {
                if (r.returnValueOfReading() > getMaxTemperatureForComfortLevel(outsideTemperature, category)) {
                    result.put((GregorianCalendar) r.getDateAndTime(), r.returnValueOfReading());
                }
            }
            return result;
        }

        //case MIN
        for (Reading r : readingList.getReadingsList()) {
            if (r.returnValueOfReading() < getMinTemperatureForComfortLevel(outsideTemperature, category)) {
                result.put((GregorianCalendar) r.getDateAndTime(), r.returnValueOfReading());
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


    private GregorianCalendar getDayAfter(Calendar day) {
        LocalDateTime dayLDT = LocalDateTime.of(day.get(Calendar.YEAR), day.get(Calendar.MONTH) + 1, day.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        dayLDT.plusDays(1);
        GregorianCalendar dayAfter = new GregorianCalendar();
        dayAfter.set(dayLDT.getYear(), dayLDT.getMonthValue(), dayLDT.getDayOfMonth());
        return dayAfter;
    }
}
