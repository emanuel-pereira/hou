package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseRoomList;

class GetCurrentTemperatureInRoomCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @Test
    void showRoomListInString() {

        SensorList sList = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readings1 = new ReadingList();
        readings1.addReading(r1);
        readings1.addReading(r2);
        SensorType temp = new SensorType("temperature");
        Sensors sensor1 = sList.createNewInternalSensor("S01", "SensorLight", new GregorianCalendar(2018, 12, 15), temp, "Percentage", readings1);
        Reading r3 = new Reading(80, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r4 = new Reading(81, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readings2 = new ReadingList();
        readings2.addReading(r3);
        readings2.addReading(r4);
        SensorType hum = new SensorType("humidity");
        Sensors sensor2 = sList.createNewInternalSensor("S02", "SensorHum", new GregorianCalendar(2018, 12, 15), hum, "Percentage", readings2);
        sList.addSensor(sensor1);
        sList.addSensor(sensor2);

        SensorTypeList sTl = new SensorTypeList();
        sTl.addSensorType(temp);
        sTl.addSensorType(hum);

        RoomList rList = getHouseRoomList();
        GetCurrentTemperatureInRoomCTRL ctrl = new GetCurrentTemperatureInRoomCTRL(sTl);
        Room room1 = new Room("R01", "Living Room", 1, 2, 3, 2);
        Room room2 = new Room("R02", "Bed Room", 1, 2, 3, 2);
        rList.addRoom(room1);
        rList.addRoom(room2);
        String expected = "1 - R01, Living Room\n" +
                            "2 - R02, Bed Room\n";
        String result = ctrl.showRoomListInString();
        assertEquals(expected, result);
    }

    @Test
    void getCurrentTempOfaRoom() {
        SensorType temp = new SensorType("temperature");
        SensorTypeList sL = new SensorTypeList();
        sL.addSensorType(temp);

        RoomList rList = getHouseRoomList();

        Room r1 = rList.createNewRoom("R01", "Living Room", 1, 2, 3, 2);
        Room r2 = rList.createNewRoom("R02", "Bed Room", 1, 2, 3, 2);

        rList.addRoom(r1);
        rList.addRoom(r2);

        Reading r11 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0), "C");
        Reading r12 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0), "C");
        ReadingList readings1 = new ReadingList();
        readings1.addReading(r11);
        readings1.addReading(r12);

        Sensors sensor1 = r1.getSensorListInRoom().createNewInternalSensor("S01", "temperature", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.getReadingList().addReading(r11);
        sensor1.getReadingList().addReading(r12);

        r1.getSensorListInRoom().addSensor(sensor1);
        GetCurrentTemperatureInRoomCTRL ctr2 = new GetCurrentTemperatureInRoomCTRL(sL);

        assertEquals(2, ctr2.getRoomList().size());
        assertEquals(1, ctr2.getSensorTypeList().size());

        double result = ctr2.getCurrentTemp(1);
        double expected = 18;

        assertEquals(expected, result);
    }

    /**
     * Check if required SensorType exists in the SensorTypeList and return true
     */
    @Test
    public void checkIfSensorTypeExists() {

        SensorTypeList sL = new SensorTypeList();
        SensorType temp = sL.newSensorType("temperature");
        SensorType rain = sL.newSensorType("rain");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(temp));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(rain));
        assertEquals(2, sL.getSensorTypeList().size());

        GetCurrentTemperatureInRoomCTRL ctr2 = new GetCurrentTemperatureInRoomCTRL(sL);

        boolean result = ctr2.checkIfRequiredSensorTypeExists("temperature");

        assertTrue(result);
    }


    /**
     * Check if required SensorType dont exists in the SensorTypeList and return false
     */
    @Test
    public void checkIfSensorTypeDontExist() {

        SensorTypeList sL = new SensorTypeList();
        SensorType hum = sL.newSensorType("humidity");
        SensorType rain = sL.newSensorType("rain");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(hum));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(rain));
        assertEquals(2, sL.getSensorTypeList().size());

        GetCurrentTemperatureInRoomCTRL ctr2 = new GetCurrentTemperatureInRoomCTRL(sL);

        boolean result = ctr2.checkIfRequiredSensorTypeExists("temperature");

        assertFalse(result);
    }

    /**
     * Check if sensor type exists in room and return true
     */
    @Test
    void checkIfSensorTypeExistInRoom() {

        SensorType temp = new SensorType("temperature");
        SensorTypeList sL = new SensorTypeList();
        sL.addSensorType(temp);

        RoomList rList = getHouseRoomList();

        Room r1 = rList.createNewRoom("R01", "Living Room", 1, 2, 3, 2);
        Room r2 = rList.createNewRoom("R02", "Bed Room", 1, 2, 3, 2);

        rList.addRoom(r1);
        rList.addRoom(r2);

        Reading r11 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0), "C");
        Reading r12 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0), "C");
        ReadingList readings1 = new ReadingList();
        readings1.addReading(r11);
        readings1.addReading(r12);

        Sensors sensor1 = r1.getSensorListInRoom().createNewInternalSensor("S01", "temperature", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.getReadingList().addReading(r11);
        sensor1.getReadingList().addReading(r12);

        Sensors sensor2 = r2.getSensorListInRoom().createNewInternalSensor("S02", "rain", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor2.getReadingList().addReading(r11);
        sensor2.getReadingList().addReading(r12);

        r1.getSensorListInRoom().addSensor(sensor1);
        r2.getSensorListInRoom().addSensor(sensor2);


        GetCurrentTemperatureInRoomCTRL ctr2 = new GetCurrentTemperatureInRoomCTRL(sL);

        boolean result = ctr2.checkIfSensorTypeExistsInRoom(1, "temperature");

        assertTrue(result);
    }

    /**
     * Check if sensor type exists in room and return false
     */
    @Test
    void checkIfSensorTypeDoesntExistInRoom() {

        SensorType temp = new SensorType("temperature");
        SensorTypeList sL = new SensorTypeList();
        sL.addSensorType(temp);

        RoomList rList = getHouseRoomList();

        Room r1 = rList.createNewRoom("R01", "Living Room", 1, 2, 3, 2);
        Room r2 = rList.createNewRoom("R01", "Bed Room", 1, 2, 3, 2);

        rList.addRoom(r1);
        rList.addRoom(r2);

        Reading r11 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0), "C");
        Reading r12 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0), "C");
        ReadingList readings1 = new ReadingList();
        readings1.addReading(r11);
        readings1.addReading(r12);

        Sensors sensor1 = r1.getSensorListInRoom().createNewInternalSensor("S02", "temperature", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.getReadingList().addReading(r11);
        sensor1.getReadingList().addReading(r12);

        r1.getSensorListInRoom().addSensor(sensor1);
        GetCurrentTemperatureInRoomCTRL ctr2 = new GetCurrentTemperatureInRoomCTRL(sL);

        boolean result = ctr2.checkIfSensorTypeExistsInRoom(1, "rain");

        assertFalse(result);
    }

}