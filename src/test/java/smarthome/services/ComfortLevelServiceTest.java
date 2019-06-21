/*
package smarthome.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.dto.RoomDTO;
import smarthome.mapper.RoomMapper;
import smarthome.mapper.SensorMapper;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseGA;
import static smarthome.model.House.getHouseRoomList;

class ComfortLevelServiceTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {

        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @Test
    void checkIfGeoAreaHasSensorByTypeTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Location loc = new Location(20, 20, 20);

        Sensor sensor1 = new ExternalSensor("101", "A", startDate, loc, sensorType, "C", readingList);
        GeographicalArea geographicalArea = getHouseGA();
        geographicalArea.getSensorListInGa().addSensor(sensor1);

        boolean result = comfortLevelService.checkIfGeoAreaHasTemperatureSensor();
        assertTrue(result);
    }

    @Test
    void checkIfGeoAreaHasSensorByTypeFailTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("rainfall");

        ReadingList readingList = new ReadingList();
        Location loc = new Location(20, 20, 20);

        Sensor sensor1 = new ExternalSensor("101", "A", startDate, loc, sensorType, "C", readingList);
        GeographicalArea geographicalArea = getHouseGA();
        geographicalArea.getSensorListInGa().addSensor(sensor1);

        boolean result = comfortLevelService.checkIfGeoAreaHasTemperatureSensor();
        assertFalse(result);
    }


    @Test
    void checkIfHouseHasRoomsTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        RoomList roomList = getHouseRoomList();

        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        roomList.addRoom(room);

        boolean result = comfortLevelService.checkIfHouseHasRooms();
        assertTrue(result);
    }

    @Test
    void checkIfHouseHasRoomsFailTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        assertTrue(getHouseRoomList().getRoomList().size() == 0);


        boolean result = comfortLevelService.checkIfHouseHasRooms();
        assertFalse(result);
    }

    @Test
    void getRoomsWithSensorByType() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        roomList.addRoom(room1);

        ReadingList readingList = new ReadingList();
        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);

        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);
        roomList.addRoom(room2);
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList);
        room2.getSensorListInRoom().addSensor(sensor2);

        RoomList resultRL = comfortLevelService.getRoomsWithSensorByType("temperature");
        RoomList expectedRL = new RoomList();
        expectedRL.addRoom(room1);

        Room expected = expectedRL.get(0);
        Room result = resultRL.get(0);

        assertEquals(expected, result);
    }

    @Test
    void getRoomsWithSensorsWithReadings() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList(); // has reading list
        Reading reading = new Reading(30, startDate, "C");
        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);


        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);


        ReadingList readingList1 = new ReadingList();
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList1);
        room2.getSensorListInRoom().addSensor(sensor2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        RoomList resultRL = comfortLevelService.getRoomsWithSensorsWithReadings(roomList);
        RoomList expectedRL = new RoomList();
        expectedRL.addRoom(room1);

        Room expected = expectedRL.get(0);
        Room result = resultRL.get(0);

        assertEquals(expected, result);
    }

    @Test
    void getFilteredRoomList() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList(); // has reading list
        Reading reading = new Reading(30, startDate, "C");
        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);


        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);


        ReadingList readingList1 = new ReadingList();
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList1);
        room2.getSensorListInRoom().addSensor(sensor2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        RoomList result = comfortLevelService.getFilteredRoomList();
        RoomList expectedRL = new RoomList();
        expectedRL.addRoom(room1);
    }

    @Test
    void getRoomListDTO() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList(); // has reading list
        Reading reading = new Reading(30, startDate, "C");
        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);


        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);

        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList);
        room2.getSensorListInRoom().addSensor(sensor2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        RoomMapper mapper = new RoomMapper();

        List<RoomDTO> expectedDTO = new ArrayList<>();

        RoomDTO rdto1 = mapper.toDto(room1);
        RoomDTO rdto2 = mapper.toDto(room2);

        expectedDTO.add(rdto1);
        expectedDTO.add(rdto2);

        List<RoomDTO> resultDTO = comfortLevelService.getRoomListDTO();

        RoomDTO resultRoom1 = resultDTO.get(0);
        RoomDTO resultRoom2 = resultDTO.get(1);
        RoomDTO expectedRoom1 = expectedDTO.get(0);
        RoomDTO expectedRoom2 = expectedDTO.get(1);

        //This is dumb...
        assertEquals(resultRoom1.getID(), expectedRoom1.getID());
        assertEquals(resultRoom2.getID(), expectedRoom2.getID());
        assertEquals(resultRoom1.getDescription(), expectedRoom1.getDescription());
        assertEquals(resultRoom2.getDescription(), expectedRoom2.getDescription());

    }


    @Test
    void validateRoomsHaveTemperatureSensorsTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new InternalSensor("101", "A", startDate, sensorType, "C", readingList);
        room.getSensorListInRoom().addSensor(sensor);

        assertTrue(comfortLevelService.validateRoomsHaveTemperatureSensors());
    }


    @Test
    void checkSensorsOfRoomHaveReadingsTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");

        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(23, startDate, "C");
        readingList.addReading(reading);

        Sensor sensor = new InternalSensor("101", "A", startDate, sensorType, "C", readingList);
        room.getSensorListInRoom().addSensor(sensor);
        getHouseRoomList().getRoomList().add(room);

        boolean result = comfortLevelService.checkThatAllSensorsHaveReadings(room);
        assertTrue(result);
    }

    @Test
    void checkSensorsOfRoomHaveReadingsFailTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new InternalSensor("101", "A", startDate, sensorType, "C", readingList);
        room.getSensorListInRoom().addSensor(sensor);
        getHouseRoomList().getRoomList().add(room);

        boolean result = comfortLevelService.checkThatAllSensorsHaveReadings(room);
        assertFalse(result);
    }


    @Test
    void getMaxTemperatureForComfortLevel() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        double result = comfortLevelService.getMaxTemperatureForComfortLevel(20, 1);
        double expected = 27.4;

        assertEquals(expected, result, 0.1);

    }

    @Test
    void getMaxTemperatureForComfortLevelFail() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        double result = comfortLevelService.getMaxTemperatureForComfortLevel(20, 0);
        double expected = -273.15;

        assertEquals(expected, result, 0.1);

    }


    @Test
    void getMinTemperatureForComfortLevel() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        double result = comfortLevelService.getMinTemperatureForComfortLevel(20, 1);
        double expected = 23.4;

        assertEquals(expected, result, 0.1);
    }

    @Test
    void getMinTemperatureForComfortLevelFail() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        double result = comfortLevelService.getMinTemperatureForComfortLevel(20, 4);
        double expected = -273.15;

        assertEquals(expected, result, 0.1);
    }


    @Test
    void getDayAfter() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        GregorianCalendar date = new GregorianCalendar(2018, 5, 5);
        Calendar result = comfortLevelService.getDayAfter(date);
        Calendar expected = new GregorianCalendar(2018, 5, 6);
        assertEquals(expected, result);
    }

    @Test
    void validateComfortLevelCategoryOutsideBelow() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        boolean result = comfortLevelService.validateComfortLevelCategory(0);
        assertFalse(result);
    }

    @Test
    void validateComfortLevelCategoryInsideLow() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        boolean result = comfortLevelService.validateComfortLevelCategory(1);
        assertTrue(result);
    }

    @Test
    void validateComfortLevelCategoryInsideHigh() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        boolean result = comfortLevelService.validateComfortLevelCategory(3);
        assertTrue(result);
    }

    @Test
    void validateComfortLevelCategoryOutsideHigh() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        boolean result = comfortLevelService.validateComfortLevelCategory(4);
        assertFalse(result);
    }

    @Test
    void getSensorOnRoomByType() {

        ComfortLevelService comfortLevelService = new ComfortLevelService();
        RoomMapper mapper = new RoomMapper();

        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(30, startDate, "C");
        readingList.addReading(reading);

        Sensor sensor = new InternalSensor("101", "A", startDate, sensorType, "C", readingList);


        room.getSensorListInRoom().addSensor(sensor);

        RoomDTO roomDTO = mapper.toDto(room);

        Sensor result = comfortLevelService.getSensorOnRoomByType("temperature", roomDTO);
        assertEquals(sensor, result);
    }


    @Test
    void setRoomByDTO() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(30, startDate, "C");
        readingList.addReading(reading);

        Sensor sensor = new InternalSensor("101", "A", startDate, sensorType, "C", readingList);


        room.getSensorListInRoom().addSensor(sensor);

        RoomMapper mapper = new RoomMapper();
        RoomDTO roomDTO = mapper.toDto(room);

        Room result = comfortLevelService.setRoomByDTO(roomDTO);
        Room expected = room;

        assertEquals(expected, result);
    }

    @Test
    void calculateThermalComfortMAX() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList(); // has reading list
        Reading reading = new Reading(30, startDate, "C");

        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);


        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);


        ReadingList readingList1 = new ReadingList();
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList1);
        room2.getSensorListInRoom().addSensor(sensor2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        RoomMapper mapper = new RoomMapper();
        RoomDTO roomDTO = mapper.toDto(room1);

        GeographicalArea geographicalArea = getHouseGA();
        SensorList sensorListGA = geographicalArea.getSensorListInGa();
        ReadingList readingsGA = new ReadingList();
        Reading readingGA = new Reading(30, startDate, "C");
        readingsGA.addReading(readingGA);

        Sensor sensorGA = new ExternalSensor("101EX", "ExtSensor", startDate, loc, sensorType1, "C", readingsGA);
        sensorListGA.addSensor(sensorGA);


        boolean maxOrMin = true; // MAX
        int category = 1;

        Calendar resultStartDate = new GregorianCalendar(2019, 1, 1);
        Calendar resultEndDate = new GregorianCalendar(2019, 1, 3);

        String result = comfortLevelService.calculateThermalComfort(roomDTO, maxOrMin, category, resultStartDate, resultEndDate);
        String expected = "No readings outside the comfort level were found in the selected time interval.\n";
        assertEquals(expected, result);
    }

    @Test
    void calculateThermalComfortMIN() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList(); // has reading list
        Reading reading = new Reading(30, startDate, "C");

        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);


        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);


        ReadingList readingList1 = new ReadingList();
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList1);
        room2.getSensorListInRoom().addSensor(sensor2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        RoomMapper mapper = new RoomMapper();
        RoomDTO roomDTO = mapper.toDto(room1);

        GeographicalArea geographicalArea = getHouseGA();
        SensorList sensorListGA = geographicalArea.getSensorListInGa();
        ReadingList readingsGA = new ReadingList();
        Reading readingGA = new Reading(30, startDate, "C");
        readingsGA.addReading(readingGA);

        Sensor sensorGA = new ExternalSensor("101EX", "ExtSensor", startDate, loc, sensorType1, "C", readingsGA);
        sensorListGA.addSensor(sensorGA);


        boolean maxOrMin = false; // MIN
        int category = 1;

        Calendar resultStartDate = new GregorianCalendar(2019, 1, 1);
        Calendar resultEndDate = new GregorianCalendar(2019, 1, 3);

        String result = comfortLevelService.calculateThermalComfort(roomDTO, maxOrMin, category, resultStartDate, resultEndDate);
        String expected = "No readings outside the comfort level were found in the selected time interval.\n";
        assertEquals(expected, result);
    }

    @Test
    void calculateThermalComfortMaxUncomfortable() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList(); // has reading list
        Reading reading = new Reading(40, startDate, "C");

        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);


        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);


        ReadingList readingList1 = new ReadingList();
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList1);
        room2.getSensorListInRoom().addSensor(sensor2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        RoomMapper mapper = new RoomMapper();
        RoomDTO roomDTO = mapper.toDto(room1);

        GeographicalArea geographicalArea = getHouseGA();
        SensorList sensorListGA = geographicalArea.getSensorListInGa();
        ReadingList readingsGA = new ReadingList();
        Reading readingGA = new Reading(30, startDate, "C");
        readingsGA.addReading(readingGA);

        Sensor sensorGA = new ExternalSensor("101EX", "ExtSensor", startDate, loc, sensorType1, "C", readingsGA);
        sensorListGA.addSensor(sensorGA);


        boolean maxOrMin = true; // MAX
        int category = 1;

        Calendar resultStartDate = new GregorianCalendar(2019, 1, 1);
        Calendar resultEndDate = new GregorianCalendar(2019, 1, 3);

        String result = comfortLevelService.calculateThermalComfort(roomDTO, maxOrMin, category, resultStartDate, resultEndDate);
        String expected = "2019-2-1 00:00 -> 40.0 ºC\n";
        assertEquals(expected, result);
    }

    @Test
    void calculateThermalComfortMinUncomfortable() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();

        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList(); // has reading list
        Reading reading = new Reading(-30, startDate, "C");

        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().addSensor(sensor1);


        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);


        ReadingList readingList1 = new ReadingList();
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList1);
        room2.getSensorListInRoom().addSensor(sensor2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        RoomMapper mapper = new RoomMapper();
        RoomDTO roomDTO = mapper.toDto(room1);

        GeographicalArea geographicalArea = getHouseGA();
        SensorList sensorListGA = geographicalArea.getSensorListInGa();
        ReadingList readingsGA = new ReadingList();
        Reading readingGA = new Reading(30, startDate, "C");
        readingsGA.addReading(readingGA);

        Sensor sensorGA = new ExternalSensor("101EX", "ExtSensor", startDate, loc, sensorType1, "C", readingsGA);
        sensorListGA.addSensor(sensorGA);


        boolean maxOrMin = false; // MIN
        int category = 1;

        Calendar resultStartDate = new GregorianCalendar(2019, 1, 1);
        Calendar resultEndDate = new GregorianCalendar(2019, 1, 3);

        String result = comfortLevelService.calculateThermalComfort(roomDTO, maxOrMin, category, resultStartDate, resultEndDate);
        String expected = "2019-2-1 00:00 -> -30.0 ºC\n";
        assertEquals(expected, result);
    }


}*/
