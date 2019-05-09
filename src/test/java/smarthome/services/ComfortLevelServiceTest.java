package smarthome.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
/*
    @Test
    void checkIfGeoAreaHasSensorByTypeTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor1 = new Sensor("101", "A", startDate, sensorType, "C", readingList);
        GeographicalArea geographicalArea = getHouseGA();
        geographicalArea.getSensorListInGA().addSensor(sensor1);

        boolean result = comfortLevelService.checkIfGeoAreaHasSensorByType(sensorType);
        assertTrue(result);
    }

    @Test
    void checkIfGeoAreaHasSensorByTypeFailTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("rainfall");
        SensorType sensorType2 = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor1 = new Sensor("101", "A", startDate, sensorType2, "C", readingList);
        GeographicalArea geographicalArea = getHouseGA();
        geographicalArea.getSensorListInGA().addSensor(sensor1);

        boolean result = comfortLevelService.checkIfGeoAreaHasSensorByType(sensorType);
        assertFalse(result);
    }
*/

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
        RoomList roomList = getHouseRoomList();

        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        boolean result = comfortLevelService.checkIfHouseHasRooms();
        assertFalse(result);
    }
/*
    @Test
    void checkIfAnyRoomHasSensorByTypeFailTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);
        SensorType sensorType = new SensorType("temperature");

        boolean result = comfortLevelService.checkIfAnyRoomHasSensorByType(sensorType);
        assertFalse(result);
    }

    @Test
    void checkIfAnyRoomHasSensorByTypeTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("101", "A", startDate, sensorType, "C", readingList);
        room.getSensorListInRoom().addSensor(sensor);
        getHouseRoomList().getRoomList().add(room);

        boolean result = comfortLevelService.checkIfAnyRoomHasSensorByType(sensorType);
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
        Sensor sensor = new Sensor("101", "A", startDate, sensorType, "C", readingList);
        room.getSensorListInRoom().addSensor(sensor);
        getHouseRoomList().getRoomList().add(room);

        boolean result = comfortLevelService.checkSensorsOfRoomHaveReadings();
        assertFalse(result);
    }



    @Test
    void checkSensorsOfRoomHaveReadingsTest() {
        ComfortLevelService comfortLevelService = new ComfortLevelService();
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(23,startDate,"C");
        readingList.addReading(reading);
        Sensor sensor = new Sensor("101", "A", startDate, sensorType, "C", readingList);
        room.getSensorListInRoom().addSensor(sensor);
        getHouseRoomList().getRoomList().add(room);

        boolean result = comfortLevelService.checkSensorsOfRoomHaveReadings();
        assertTrue(result);
    }
    */
}