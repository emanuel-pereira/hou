/*
package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class US172TotalNominalPowerInGridCTRLTest {

    */
/**
     * Check if the house grid list is correct by confirming the correct size of that list
     *//*

    @Test
    void getHouseGridList() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid2);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (house);

        double expectedResult = 2;
        double result = US172CTRL.getHouseGridList ().getHouseGridList ().size ();

        assertEquals (expectedResult, result);
    }

    */
/**
     * Check if the room list is correct by confirming the correct size of that list
     *//*

    @Test
    void getRoomList() {
        House house = new House();
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom (room1);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (house);

        double expectedResult = 1;
        double result = US172CTRL.getRoomList ().getRoomList ().size ();

        assertEquals (expectedResult, result);
    }

    */
/**
     * Check if the device list is correct by confirming the correct size of that list
     *//*

    @Test
    void getDeviceList() {
        House house = new House();
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom1", 2, 2, 1,2);
        roomList.addRoom (room1);
        DeviceList deviceList = room1.getDeviceList ();
        Lamp lampObj = new Lamp(DeviceType.LAMP,50);
        Device lamp1 = deviceList.newDevice ("Lamp1",lampObj,120);
        deviceList.addDevice (lamp1);
        Lamp lampObj2 = new Lamp(DeviceType.LAMP,50);
        Device lamp2 = deviceList.newDevice ("Lamp2", lampObj2, room1, 120);
        deviceList.addDevice (lamp2);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (house);

        double expectedResult = 2;
        double result = US172CTRL.getDeviceList (1).getDeviceList ().size ();

        assertEquals (expectedResult, result);
    }

    */
/**
     * Check if the the room list in a grid is correct by confirming the correct size of that list
     *//*

    @Test
    void getListOfRoomsWithHouseGridTestSize() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid2);
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom (room1);
        room1.setmHouseGrid (houseGrid1);
        Room room2 = roomList.createNewRoom ("Kitchen", 0, 1.5, 2, 1.7);
        roomList.addRoom (room2);
        room2.setmHouseGrid (houseGrid2);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (house);

        double expectedResult = 1;
        double result = US172CTRL.getListOfRoomsWithThisGrid (1).getRoomList ().size ();

        assertEquals (expectedResult, result);
    }

    @Test
    void getListOfRoomsWithHouseGridTest() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid2);
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom (room1);
        room1.setmHouseGrid (houseGrid1);
        Room room2 = roomList.createNewRoom ("Kitchen", 0, 1.5, 2, 1.7);
        roomList.addRoom (room2);
        room2.setmHouseGrid (houseGrid2);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (house);

        List<Room> expectedResult = Arrays.asList(room1);
        List<Room> result = US172CTRL.getListOfRoomsWithThisGrid (1).getRoomList();

        assertEquals (expectedResult, result);
    }


    */
/**
     * Show the house grid list in string is correct by confirming the content
     *//*

    @Test
    void showHouseGridListInString() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid", 100);
        houseGridList.addHouseGrid (houseGrid2);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (house);

        String expectedResult = "1 - InternalGrid\n2 - ExternalGrid\n";
        String result = US172CTRL.showHouseGridListInString ();

        assertEquals (expectedResult, result);
    }

    */
/**
     * Confirm the right total nominal power in a grid if expected result is correct (two grids exist)
     *//*

    @Test
    void getTotalNominalPowerInGridIfCorrect() {
        House h = new House();
        HouseGridList hGl = h.getHGListInHouse ();
        HouseGrid hG1 = hGl.newHouseGrid ("internalGrid",800);
        hGl.addHouseGrid (hG1);
        HouseGrid hG2 = hGl.newHouseGrid ("externalGrid",800);
        hGl.addHouseGrid (hG2);
        RoomList list = h.getRoomList ();
        Room bedroom = list.createNewRoom ("bedroom", 1, 2, 3, 2);
        list.addRoom (bedroom);
        Room garden = list.createNewRoom ("garden", 0, 2, 3, 2);
        list.addRoom (garden);
        DeviceList dLb = bedroom.getDeviceList ();
        Room kitchen = list.createNewRoom ("kitchen", 0, 1,2,1.5);
        list.addRoom (kitchen);
        DeviceList dLK = kitchen.getDeviceList ();
        Lamp lampObj= new Lamp(DeviceType.LAMP,25);
        Device d1 = dLb.newDevice ("lamp1",lampObj, bedroom,20);
        Lamp lampObj1= new Lamp(DeviceType.LAMP,25);
        Device d2 = dLb.newDevice("lamp2",lampObj1, bedroom, 22);
        dLb.addDevice (d1);
        dLb.addDevice (d2);
        Fridge fridge= new Fridge(DeviceType.FRIDGE,25,50,25);
        Device d3 = dLb.newDevice ("fridge",fridge, bedroom, 150);
        Dishwasher dishwasher= new Dishwasher(DeviceType.DISHWASHER,50);
        Device d4 = dLb.newDevice("dishwasher", dishwasher,bedroom, 100);
        dLK.addDevice (d3);
        dLK.addDevice (d4);
        bedroom.setmHouseGrid (hG1);
        kitchen.setmHouseGrid (hG1);
        garden.setmHouseGrid (hG2);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (h);

        double expectedResult = 292;
        double result = US172CTRL.getTotalNominalPowerInGrid (1);

        assertEquals (expectedResult, result);
    }

    */
/**
     * Confirm the right total nominal power in a grid if expected result is incorrect
     *//*


import org.junit.jupiter.api.Test;

@Test
    void getTotalNominalPowerInGridIfIncorrect() {
        House h = new House();
        HouseGridList hGl = h.getHGListInHouse ();
        HouseGrid hG = hGl.newHouseGrid ("mainGrid",800);
        hGl.addHouseGrid (hG);
        RoomList list = h.getRoomList ();
        Room bedroom = list.createNewRoom ("bedroom", 1, 2, 3, 2);
        list.addRoom (bedroom);
        DeviceList dLb = bedroom.getDeviceList ();
        Room kitchen = list.createNewRoom ("kitchen", 0, 1,2,1.5);
        list.addRoom (kitchen);
        DeviceList dLK = kitchen.getDeviceList ();
        Lamp lampObj= new Lamp(DeviceType.LAMP,25);
        Lamp lampObj1= new Lamp(DeviceType.LAMP,25);
        Device d1 = dLb.newDevice ("lamp1",lampObj, bedroom,10);
        Device d2 = dLb.newDevice("lamp2",lampObj1, bedroom, 10);
        dLb.addDevice (d1);
        dLb.addDevice (d2);
        Fridge fridge1= new Fridge(DeviceType.FRIDGE,25,50,25);
        Device d3 = dLb.newDevice ("fridge",fridge1, bedroom, 100);
        Dishwasher dishwasher= new Dishwasher(DeviceType.DISHWASHER,50);
        Device d4 = dLb.newDevice("dishwasher",dishwasher, bedroom, 100);
        dLK.addDevice (d3);
        dLK.addDevice (d4);
        bedroom.setmHouseGrid (hG);
        kitchen.setmHouseGrid (hG);
        US172TotalNominalPowerInGridCTRL US172CTRL = new US172TotalNominalPowerInGridCTRL (h);

        double expectedResult = 221;
        double result = US172CTRL.getTotalNominalPowerInGrid (1);

        assertNotEquals (expectedResult, result);
    }
}*/
