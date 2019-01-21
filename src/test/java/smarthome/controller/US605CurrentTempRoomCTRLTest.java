package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class US605CurrentTempRoomCTRLTest {


    @Test
    void showRoomListInString() {

        House h = new House();

        SensorList sList = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar (2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readings1 = new ReadingList ();
        readings1.addReading (r1);
        readings1.addReading (r2);
        SensorType temp = new SensorType ("temperature");
        Sensor sensor1 = sList.createNewInternalSensor ("SensorLight", new GregorianCalendar(2018, 12, 15), temp, "Percentage", readings1);
        Reading r3 = new Reading(80, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r4 = new Reading(81, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readings2 = new ReadingList ();
        readings2.addReading (r3);
        readings2.addReading (r4);
        SensorType hum = new SensorType ("humidity");
        Sensor sensor2 = sList.createNewInternalSensor ("SensorHum", new GregorianCalendar(2018, 12, 15), hum, "Percentage", readings2);
        sList.addSensor(sensor1);
        sList.addSensor(sensor2);

        SensorTypeList sTl = new SensorTypeList ();
        sTl.addSensorType (temp);
        sTl.addSensorType (hum);

        RoomList rList = h.getRoomList();
        US605CurrentTempRoomCTRL ctrl = new US605CurrentTempRoomCTRL(h, sTl);
        Room room1= new Room("Living Room",1,2,3, 2);
        Room room2= new Room("Bed Room",1,2,3, 2);
        rList.addRoom (room1);
        rList.addRoom (room2);
        String expected = "1 - Living Room\n2 - Bed Room\n";
        String result =  ctrl.showRoomListInString ();
        assertEquals(expected,result);
    }

    @Test
    void getCurrentTempOfaRoom() {
        SensorType temp = new SensorType ("temperature");
        SensorTypeList sL = new SensorTypeList ();
        sL.addSensorType (temp);

        House h1 = new House ();
        RoomList rList = h1.getRoomList();

        Room r1 = rList.createNewRoom("Living Room", 1, 2, 3, 2);
        Room r2 = rList.createNewRoom("Bed Room", 1, 2, 3, 2);

        rList.addRoom(r1);
        rList.addRoom(r2);

        Reading r11 = new Reading(15, new GregorianCalendar (2018, 12, 26, 12, 0));
        Reading r12 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readings1 = new ReadingList ();
        readings1.addReading (r11);
        readings1.addReading(r12);

        Sensor sensor1 = r1.getSensorListInRoom ().createNewInternalSensor ("temperature", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.addReadingToList (r11);
        sensor1.addReadingToList (r12);

        r1.getSensorListInRoom ().addSensor (sensor1);
        US605CurrentTempRoomCTRL ctr2 = new US605CurrentTempRoomCTRL(h1,sL);

        assertEquals(2, ctr2.getRoomList ().size ());
        assertEquals(1, ctr2.getSensorTypeList ().size ());

        double result = ctr2.getCurrentTemp (1);
        double expected = 18;

        assertEquals(expected,result);
    }

    /**
     * Check if required SensorType exists in the SensorTypeList and return true
     */
    @Test
    public void checkIfSensorTypeExists() {

        House h = new House();
        SensorTypeList sL = new SensorTypeList();
        SensorType temp = sL.newSensorType("temperature");
        SensorType rain = sL.newSensorType("rain");

        assertEquals (0, sL.getSensorTypeList().size ());
        assertTrue(sL.addSensorType(temp));
        assertEquals (1, sL.getSensorTypeList().size ());
        assertTrue(sL.addSensorType(rain));
        assertEquals (2, sL.getSensorTypeList().size ());

        US605CurrentTempRoomCTRL ctr2 = new US605CurrentTempRoomCTRL(h,sL);

        boolean result = ctr2.checkIfRequiredSensorTypeExists ("temperature");

        assertTrue (result);
    }


    /**
     * Check if required SensorType dont exists in the SensorTypeList and return false
     */
    @Test
    public void checkIfSensorTypeDontExist() {

        House h = new House();
        SensorTypeList sL = new SensorTypeList();
        SensorType hum = sL.newSensorType("humidity");
        SensorType rain = sL.newSensorType("rain");

        assertEquals (0, sL.getSensorTypeList().size ());
        assertTrue(sL.addSensorType(hum));
        assertEquals (1, sL.getSensorTypeList().size ());
        assertTrue(sL.addSensorType(rain));
        assertEquals (2, sL.getSensorTypeList().size ());

        US605CurrentTempRoomCTRL ctr2 = new US605CurrentTempRoomCTRL(h,sL);

        boolean result = ctr2.checkIfRequiredSensorTypeExists ("temperature");

        assertFalse (result);
    }

    /**
     * Check if sensor type exists in room and return true
     */
    @Test
    void checkIfSensorTypeExistInRoom() {

        SensorType temp = new SensorType ("temperature");
        SensorTypeList sL = new SensorTypeList ();
        sL.addSensorType (temp);

        House h1 = new House ();
        RoomList rList = h1.getRoomList();

        Room r1 = rList.createNewRoom("Living Room", 1, 2, 3, 2);
        Room r2 = rList.createNewRoom("Bed Room", 1, 2, 3, 2);

        rList.addRoom(r1);
        rList.addRoom(r2);

        Reading r11 = new Reading(15, new GregorianCalendar (2018, 12, 26, 12, 0));
        Reading r12 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readings1 = new ReadingList ();
        readings1.addReading (r11);
        readings1.addReading(r12);

        Sensor sensor1 = r1.getSensorListInRoom ().createNewInternalSensor ("temperature", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.addReadingToList (r11);
        sensor1.addReadingToList (r12);

        Sensor sensor2 = r2.getSensorListInRoom ().createNewInternalSensor ("rain", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.addReadingToList (r11);
        sensor1.addReadingToList (r12);

        r1.getSensorListInRoom ().addSensor (sensor1);
        r2.getSensorListInRoom ().addSensor (sensor2);


        US605CurrentTempRoomCTRL ctr2 = new US605CurrentTempRoomCTRL(h1,sL);

        boolean result = ctr2.checkIfSensorTypeExistsInRoom (1,"temperature");

        assertTrue (result);
    }

    /**
     * Check if sensor type exists in room and return false
     */
    @Test
    void checkIfSensorTypeDoesntExistInRoom() {

        SensorType temp = new SensorType ("temperature");
        SensorTypeList sL = new SensorTypeList ();
        sL.addSensorType (temp);

        House h1 = new House ();
        RoomList rList = h1.getRoomList();

        Room r1 = rList.createNewRoom("Living Room", 1, 2, 3, 2);
        Room r2 = rList.createNewRoom("Bed Room", 1, 2, 3, 2);

        rList.addRoom(r1);
        rList.addRoom(r2);

        Reading r11 = new Reading(15, new GregorianCalendar (2018, 12, 26, 12, 0));
        Reading r12 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readings1 = new ReadingList ();
        readings1.addReading (r11);
        readings1.addReading(r12);

        Sensor sensor1 = r1.getSensorListInRoom ().createNewInternalSensor ("temperature", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.addReadingToList (r11);
        sensor1.addReadingToList (r12);

        r1.getSensorListInRoom ().addSensor (sensor1);
        US605CurrentTempRoomCTRL ctr2 = new US605CurrentTempRoomCTRL(h1,sL);

        boolean result = ctr2.checkIfSensorTypeExistsInRoom (1,"rain");

        assertFalse (result);
    }

}