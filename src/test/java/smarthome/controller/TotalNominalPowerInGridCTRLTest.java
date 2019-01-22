package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TotalNominalPowerInGridCTRLTest {


    @Test
    @DisplayName("Check if the house grid list is correct by confirming the correct size of that list")
    void getHouseGridListSize() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid");
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid");
        houseGridList.addHouseGrid (houseGrid2);
        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (house);

        double expectedResult = 2;
        double result = US172CTRL.getHouseGridListSize ();

        assertEquals (expectedResult, result);
    }


    /**
     * Check if the room list is correct by confirming the correct size of that list
     */
    @Test
    void getRoomListSize() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom (room1);
        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (house);

        double expectedResult = 1;
        double result = US172CTRL.getRoomListSize ();

        assertEquals (expectedResult, result);
    }


    /**
     * Check if the device list in all rooms of the grid are not empty and return true
     * Two grids: test one of them with two rooms, both with one device
     */

    @Test
    void deviceListSizeInGridIsNotEmptyA() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom1", 2, 2, 1, 2);
        roomList.addRoom (room1);
        Room room2 = roomList.createNewRoom ("Bedroom2", 2, 2, 1, 2);
        roomList.addRoom (room2);
        DeviceList deviceListA = room1.getDeviceList ();
        DeviceList deviceListB = room1.getDeviceList ();
        Lamp lampObj = new Lamp (DeviceType.LAMP, 50);
        Device lamp1 = deviceListA.newDevice ("Lamp1", lampObj, 120);
        deviceListA.addDevice (lamp1);
        Lamp lampObj2 = new Lamp (DeviceType.LAMP, 50);
        Device lamp2 = deviceListB.newDevice ("Lamp2", lampObj2, 120);
        deviceListB.addDevice (lamp2);
        HouseGridList hGl = house.getHGListInHouse ();
        HouseGrid gridA = hGl.newHouseGrid ("GridA");
        hGl.addHouseGrid (gridA);
        roomList.addDeviceToRoom (lamp1, 1);
        roomList.addDeviceToRoom (lamp2, 2);
        gridA.attachRoomToGrid (room1);
        gridA.attachRoomToGrid (room2);

        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (house);

        boolean expectedResult = true;
        boolean result = US172CTRL.deviceListSizeInGridIsNotEmpty (0);

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the device list in all rooms of the grid are not empty and return true
     * One grid: two rooms, one with a device and another without devices
     */
    @Test
    void deviceListSizeInGridIsNotEmptyB() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom1", 2, 2, 1, 2);
        roomList.addRoom (room1);
        Room room2 = roomList.createNewRoom ("Bedroom2", 2, 2, 1, 2);
        roomList.addRoom (room2);
        DeviceList deviceListA = room1.getDeviceList ();
        Lamp lampObj = new Lamp (DeviceType.LAMP, 50);
        Device lamp1 = deviceListA.newDevice ("Lamp1", lampObj, 120);
        deviceListA.addDevice (lamp1);
        HouseGridList hGl = house.getHGListInHouse ();
        HouseGrid gridA = hGl.newHouseGrid ("GridA");
        hGl.addHouseGrid (gridA);
        roomList.addDeviceToRoom (lamp1, 1);
        gridA.attachRoomToGrid (room1);
        gridA.attachRoomToGrid (room2);


        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (house);

        boolean expectedResult = true;
        boolean result = US172CTRL.deviceListSizeInGridIsNotEmpty (0);

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the device list in all rooms of the grid are empty and return false
     * Two grids: test one of them with two rooms, both with no devices
     */
    @Test
    void deviceListSizeInGridIsEmpty() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom1", 2, 2, 1, 2);
        roomList.addRoom (room1);
        Room room2 = roomList.createNewRoom ("Bedroom2", 2, 2, 1, 2);
        roomList.addRoom (room2);
        Room room3 = roomList.createNewRoom ("Bedroom3", 2, 2, 1, 2);
        roomList.addRoom (room3);
        DeviceList deviceListA = room1.getDeviceList ();
        Lamp lampObj = new Lamp (DeviceType.LAMP, 50);
        Device lamp1 = deviceListA.newDevice ("Lamp1", lampObj, 120);
        deviceListA.addDevice (lamp1);
        Lamp lampObj2 = new Lamp (DeviceType.LAMP, 50);
        Device lamp2 = deviceListA.newDevice ("Lamp2", lampObj2, 120);
        deviceListA.addDevice (lamp2);
        HouseGridList hGl = house.getHGListInHouse ();
        HouseGrid gridA = hGl.newHouseGrid ("GridA");
        hGl.addHouseGrid (gridA);
        HouseGrid gridB = hGl.newHouseGrid ("GridB");
        hGl.addHouseGrid (gridB);
        roomList.addDeviceToRoom (lamp1, 1);
        roomList.addDeviceToRoom (lamp2, 1);
        gridA.attachRoomToGrid (room1);
        gridB.attachRoomToGrid (room2);
        gridB.attachRoomToGrid (room3);

        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (house);

        boolean expectedResult = false;
        boolean result = US172CTRL.deviceListSizeInGridIsNotEmpty (1);

        assertEquals (expectedResult, result);
    }


    /**
     * Check if the the room list in a grid is correct by confirming the correct size of that list
     */
    @Test
    void getListOfRoomsWithHouseGridTestSize() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid");
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid");
        houseGridList.addHouseGrid (houseGrid2);
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom (room1);
        houseGrid1.attachRoomToGrid (room1);
        Room room2 = roomList.createNewRoom ("Kitchen", 0, 1.5, 2, 1.7);
        roomList.addRoom (room2);
        houseGrid2.attachRoomToGrid (room2);
        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (house);

        double expectedResult = 1;
        double result = US172CTRL.getRoomListInAGridSize (1);

        assertEquals (expectedResult, result);
    }


    /**
     * Show the house grid list in string is correct by confirming the content
     */
    @Test
    void showHouseGridListInString() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid");
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid");
        houseGridList.addHouseGrid (houseGrid2);
        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (house);

        String expectedResult = "1 - InternalGrid\n2 - ExternalGrid\n";
        String result = US172CTRL.showHouseGridListInString ();

        assertEquals (expectedResult, result);
    }

    /**
     * Confirm the right total nominal power in a grid if expected result is correct (two grids exist)
     */

    @Test
    void getTotalNominalPowerInGridIfCorrect() {
        House h = new House ();
        HouseGridList hGl = h.getHGListInHouse ();
        HouseGrid hG1 = hGl.newHouseGrid ("internalGrid");
        hGl.addHouseGrid (hG1);
        HouseGrid hG2 = hGl.newHouseGrid ("externalGrid");
        hGl.addHouseGrid (hG2);
        RoomList list = h.getRoomList ();
        Room bedroom = list.createNewRoom ("bedroom", 1, 2, 3, 2);
        list.addRoom (bedroom);
        Room garden = list.createNewRoom ("garden", 0, 2, 3, 2);
        list.addRoom (garden);
        DeviceList dLb = bedroom.getDeviceList ();
        Room kitchen = list.createNewRoom ("kitchen", 0, 1, 2, 1.5);
        list.addRoom (kitchen);
        DeviceList dLK = kitchen.getDeviceList ();
        Lamp lampObj = new Lamp (DeviceType.LAMP, 25);
        Device d1 = dLb.newDevice ("lamp1", lampObj, 20);
        Lamp lampObj1 = new Lamp (DeviceType.LAMP, 25);
        Device d2 = dLb.newDevice ("lamp2", lampObj1, 2);
        dLb.addDevice (d1);
        dLb.addDevice (d2);
        Fridge fridge = new Fridge (DeviceType.FRIDGE, 25, 50, 25);
        Device d3 = dLb.newDevice ("fridge", fridge, 150);
        Dishwasher dishwasher = new Dishwasher (DeviceType.DISHWASHER, 50);
        Device d4 = dLb.newDevice ("dishwasher", dishwasher, 100);
        dLK.addDevice (d3);
        dLK.addDevice (d4);
        hG1.attachRoomToGrid (bedroom);
        hG1.attachRoomToGrid (kitchen);
        hG2.attachRoomToGrid (garden);

        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (h);

        double expectedResult = 272;
        double result = US172CTRL.getTotalNominalPowerInGrid (1);

        assertEquals (expectedResult, result);
    }


    /**
     * Confirm the right total nominal power in a grid if expected result is incorrect
     */
    @Test
    void getTotalNominalPowerInGridIfIncorrect() {
        House h = new House ();
        HouseGridList hGl = h.getHGListInHouse ();
        HouseGrid hG = hGl.newHouseGrid ("mainGrid");
        hGl.addHouseGrid (hG);
        RoomList list = h.getRoomList ();
        Room bedroom = list.createNewRoom ("bedroom", 1, 2, 3, 2);
        list.addRoom (bedroom);
        DeviceList dLb = bedroom.getDeviceList ();
        Room kitchen = list.createNewRoom ("kitchen", 0, 1, 2, 1.5);
        list.addRoom (kitchen);
        DeviceList dLK = kitchen.getDeviceList ();
        Lamp lampObj = new Lamp (DeviceType.LAMP, 25);
        Lamp lampObj1 = new Lamp (DeviceType.LAMP, 25);
        Device d1 = dLb.newDevice ("lamp1", lampObj, 10);
        Device d2 = dLb.newDevice ("lamp2", lampObj1, 10);
        dLb.addDevice (d1);
        dLb.addDevice (d2);
        Fridge fridge1 = new Fridge (DeviceType.FRIDGE, 25, 50, 25);
        Device d3 = dLb.newDevice ("fridge", fridge1, 100);
        Dishwasher dishwasher = new Dishwasher (DeviceType.DISHWASHER, 50);
        Device d4 = dLb.newDevice ("dishwasher", dishwasher, 100);
        dLK.addDevice (d3);
        dLK.addDevice (d4);
        hG.attachRoomToGrid (bedroom);
        hG.attachRoomToGrid (kitchen);

        TotalNominalPowerInGridCTRL US172CTRL = new TotalNominalPowerInGridCTRL (h);

        double expectedResult = 221;
        double result = US172CTRL.getTotalNominalPowerInGrid (1);

        assertNotEquals (expectedResult, result);
    }
}
