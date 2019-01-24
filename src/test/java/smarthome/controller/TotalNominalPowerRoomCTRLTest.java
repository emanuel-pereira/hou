
package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class US230TotalNominalPowerRoomCTRLTest {

    @Test
    void getRoomListSize() {
        House house = new House();
        TotalNominalPowerRoomCTRL ctrl230 = new TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomList ().addRoom(r1);
        house.getRoomList ().addRoom(r2);

        int expectedResult = 2;
        int result = ctrl230.getRoomListSize ();

        assertEquals(expectedResult,result);
    }

    @Test
    void getRoomNameCtrlTest() {
        House house = new House();
        TotalNominalPowerRoomCTRL ctrl230 = new TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomList ().addRoom(r1);
        house.getRoomList ().addRoom(r2);

        String expectedResult = "cozinha";
        String result = ctrl230.getRoomNameCtrl(1);

        assertEquals(expectedResult,result);
    }

    @Test
    void showListRoomInStringTest() {
        House house = new House();

        TotalNominalPowerRoomCTRL ctrl230 = new TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomList ().addRoom(r1);
        house.getRoomList ().addRoom(r2);

        String expectedResult = "1 - cozinha\n2 - sala\n";
        String result = ctrl230.showListRoomInString();

        assertEquals(expectedResult,result);
    }

    @Test
    void getNominalPowerRoomCtrlTest () {
        House house = new House();
        TotalNominalPowerRoomCTRL ctrl230 = new TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomList ().addRoom(r1);
        house.getRoomList ().addRoom(r2);
        OtherDevices tv= new OtherDevices(DeviceType.TV);
        Fridge f= new Fridge(DeviceType.FRIDGE,25,75,25);
        OtherDevices stove= new OtherDevices(DeviceType.STOVE);
        Lamp l= new Lamp(DeviceType.LAMP,75);
        Device d1 = new Device("tv",tv,200);
        Device d2 = new Device("fridge",f,300);
        Device d3 = new Device("stove",stove,500);
        Device d4 = new Device("lamp",l,100);
        house.getRoomList ().get(0).getDeviceList ().addDevice(d2);
        house.getRoomList ().get(0).getDeviceList ().addDevice(d3);
        house.getRoomList ().get(1).getDeviceList ().addDevice(d1);
        house.getRoomList ().get(1).getDeviceList ().addDevice(d4);

        double expectedResult = 300;
        double result = ctrl230.getNominalPowerRoomCtrl(2);

        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceListSizeInRoom () {
        House house = new House();
        TotalNominalPowerRoomCTRL ctrl230 = new TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomList ().addRoom(r1);
        house.getRoomList ().addRoom(r2);
        OtherDevices tv= new OtherDevices(DeviceType.TV);
        OtherDevices stove= new OtherDevices(DeviceType.STOVE);
        Fridge f= new Fridge(DeviceType.FRIDGE,25,75,25);
        Lamp l= new Lamp(DeviceType.LAMP,75);
        Device d1 = new Device("tv",tv,200);
        Device d2 = new Device("fridge",f,300);
        Device d3 = new Device("stove",stove,500);
        Device d4 = new Device("lamp",l,100);
        house.getRoomList ().get(0).getDeviceList ().addDevice(d2);
        house.getRoomList ().get(0).getDeviceList ().addDevice(d3);
        house.getRoomList ().get(1).getDeviceList ().addDevice(d1);
        house.getRoomList ().get(1).getDeviceList ().addDevice(d4);

        int expectedResult = 2;
        int result = ctrl230.getDeviceListSizeInRoom (2);

        assertEquals(expectedResult,result);
    }



}
