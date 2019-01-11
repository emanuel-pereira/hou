package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class US253AddSensorToRoomTest {
    @DisplayName("Test if SensorType List is showed as a string to the user")
    @Test
    void showSensorTypeListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        House h1 = new House();
        US253AddSensorToRoomCTRL ctr1 = new US253AddSensorToRoomCTRL(h1,sensorTypeList);
        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);
        String expected = "1 - Temperature\n2 - Wind\n";
        String result = ctr1.showDataTypeListInString();
        assertEquals(expected, result);
    }

    @DisplayName("Test if Room List is showed as a string to the user")
    @Test
    void showRoomListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        House h1 = new House();
        List<Room> roomList = h1.getRoomListFromHouse().getRoomList();
        US253AddSensorToRoomCTRL ctrl = new US253AddSensorToRoomCTRL(h1,sensorTypeList);
        Room r1 = new Room("Living Room", 1, 2, 3, 2);
        Room r2 = new Room("Bed Room", 1, 2, 3, 2);
        roomList.add(r1);
        roomList.add(r2);
        String expected = "1 - Living Room\n2 - Bed Room\n";
        String result = ctrl.showRoomListInString();
        assertEquals(expected, result);
    }

    @DisplayName("Ensure that two different sensors are added to the respective Rooms")
    @Test
    void addNewSensorToRoom() {
        House h1 = new House();
        RoomList roomList = h1.getRoomListFromHouse();
        SensorTypeList sensorTypeList = new SensorTypeList();
        US253AddSensorToRoomCTRL ctr1 = new US253AddSensorToRoomCTRL(h1,sensorTypeList);
        Room r1 = roomList.createNewRoom("Living Room", 1, 2, 3, 2);
        Room r2 = roomList.createNewRoom("Bed Room", 1, 2, 3, 2);

        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);


        roomList.addRoom(r1);
        roomList.addRoom(r2);

        Reading r1Lis= new Reading(27,new GregorianCalendar(2018,12,26,12,00));
        Reading r2Lis= new Reading(21,new GregorianCalendar(2018,12,26,13,00));
        ReadingList readingsLis= new ReadingList();
        readingsLis.addReading (r1Lis);
        readingsLis.addReading (r2Lis);

        ctr1.addNewSensorToRoom("S1",new GregorianCalendar(2018,3,3),1,1,"C", readingsLis);

        assertEquals(2, h1.getRoomListFromHouse().getRoomList().size());
    }
}
