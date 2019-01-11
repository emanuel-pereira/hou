package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US605CurrentTempRoomUICurrentTempRoomCTRLTest {



    @Test
    void test() {
        SensorType temp = new SensorType ("temperature");
        SensorTypeList sL = new SensorTypeList ();
        sL.addSensorType (temp);

        House h1 = new House ();
        RoomList rList = h1.getRoomListFromHouse ();

        Room r1 = rList.createNewRoom("Living Room", 1, 2, 3, 2);
        Room r2 = rList.createNewRoom("Bed Room", 1, 2, 3, 2);

        Reading r11 = new Reading(15, new GregorianCalendar (2018, 12, 26, 12, 00));
        Reading r12 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readings1 = new ReadingList ();
        readings1.addReading (r11);
        readings1.addReading(r12);

        Sensor sensor1 = r1.getSensorListInRoom ().createNewInternalSensor ("temperature", new GregorianCalendar(2018, 12, 15), temp, "C", readings1);
        sensor1.addReadingToList (r11);
        sensor1.addReadingToList (r12);

        rList.addRoom(r1);
        rList.addRoom(r2);

        r1.getSensorListInRoom ().addSensor (sensor1);
        US605CurrentTempRoomCTRL ctr2 = new US605CurrentTempRoomCTRL(h1,sL);

        double result = ctr2.getCurrentTemp (1);
        double expected = 18;

        assertEquals(expected,result);
    }


}