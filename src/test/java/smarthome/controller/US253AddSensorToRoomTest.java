package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class US253AddSensorToRoomTest {
    @DisplayName("Test if DataType List is showed as a string to the user")
    @Test
    void showDataTypeListInString() {
        DataTypeList dataTypeList = new DataTypeList();
        List <Room> roomList = new ArrayList<>();
        US253AddSensorToRoomCTRL ctr1 = new US253AddSensorToRoomCTRL(dataTypeList,roomList);
        DataType type1= new DataType("Temperature");
        DataType type2= new DataType("Wind");
        dataTypeList.addDataType(type1);
        dataTypeList.addDataType(type2);
        String expected = "1 - Temperature\n2 - Wind\n";
        String result =  ctr1.showDataTypeListInString();
        assertEquals(expected,result);
    }

    @DisplayName("Test if Room List is showed as a string to the user")
    @Test
    void showRoomListInString() {
        DataTypeList dataTypeList = new DataTypeList();
        List <Room> roomList = new ArrayList<>();
        US253AddSensorToRoomCTRL ctrl = new US253AddSensorToRoomCTRL(dataTypeList,roomList);
        Room r1= new Room("Living Room",1,2,3, 2);
        Room r2= new Room("Bed Room",1,2,3, 2);
        roomList.add(r1);
        roomList.add(r2);
        String expected = "1 - Living Room\n2 - Bed Room\n";
        String result =  ctrl.showRoomListInString();
        assertEquals(expected,result);
    }
    @DisplayName("Ensure that two different sensors are added to the respective Rooms")
    @Test
    void addNewSensorToRoom() {
        DataTypeList dataTypeList = new DataTypeList();
        List <Room> roomList = new ArrayList<>();
        US253AddSensorToRoomCTRL ctrl = new US253AddSensorToRoomCTRL(dataTypeList,roomList);
        Room r1= new Room("Living Room",1,2,3, 2);
        Room r2= new Room("Bed Room",1,2,3, 2);
        roomList.add(r1);
        roomList.add(r2);
        DataType type1= new DataType("Temperature");
        DataType type2= new DataType("Wind");
        dataTypeList.addDataType(type1);
        dataTypeList.addDataType(type2);
        Sensor s1 = new Sensor();
        Sensor s2 = new Sensor();
        s1.setRoom(r1);
        s2.setRoom(r2);

        ctrl.addNewSensorToRoom("LivingRoomTempSensor",new GregorianCalendar(2018,12,26),1,1);
        assertEquals(r1,s1.getRoom());

        ctrl.addNewSensorToRoom("BedRoomWindSensor",new GregorianCalendar(2018,12,26),2,2);
        assertEquals(r2,s2.getRoom());
    }
}
