package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GetTotalNominalPowerCTRLTest {

    /**
     * Check if the house grid list has the correct size.
     * House with two grids
     */
    @Test
    void getGridListSizeIfTwo() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid");
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid");
        houseGridList.addHouseGrid (houseGrid2);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 2;
        double result = US172CTRL.getGridListSize ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the house grid list has the correct size.
     * House with no grids
     */
    @Test
    void getGridListSizeIfZero() {
        House house = new House ();

        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 0;
        double result = US172CTRL.getGridListSize ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the room list has the correct size
     * Room list with one room
     */
    @Test
    void getRoomListSizeIfOne() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom (room1);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 1;
        double result = US172CTRL.getRoomListSize ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the room list has the correct size
     * Room list with zero rooms
     */
    @Test
    void getRoomListSizeIfZero() {
        House house = new House ();
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 0;
        double result = US172CTRL.getRoomListSize ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the house grid list in string is correct by confirming the content
     */
    @Test
    void showGridListInString() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid");
        houseGridList.addHouseGrid (houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid ("ExternalGrid");
        houseGridList.addHouseGrid (houseGrid2);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL (house);

        String expectedResult = "1 - InternalGrid\n2 - ExternalGrid\n";
        String result = US172CTRL.showGridListInString ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the room list in string is correct by confirming the content
     */
    @Test
    void showRoomListInString() {
        House house = new House();

        Room r1 = new Room("cozinha", 1, 10, 20, 3);
        Room r2 = new Room("sala", 1, 10, 20, 3);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        GetTotalNominalPowerCTRL US230CTRL = new GetTotalNominalPowerCTRL (house);


        String expectedResult = "1 - cozinha\n2 - sala\n";
        String result = US230CTRL.showRoomListInString ();

        assertEquals(expectedResult, result);
    }

    /**
     * Confirm the right total nominal power in a grid if expected result is correct (two grids exist)
     */
    @Test
    void getGridTotalNominalPowerIfCorrect() {
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
        Lamp lampObj = new Lamp (25);
        Device d1 = dLb.newDevice ("lamp1", lampObj, 20);
        Lamp lampObj1 = new Lamp ( 25);
        Device d2 = dLb.newDevice ("lamp2", lampObj1, 2);
        dLb.addDevice (d1);
        dLb.addDevice (d2);
        Fridge fridge = new Fridge ( 25, 50, 25);
        Device d3 = dLb.newDevice ("fridge", fridge, 150);
        Dishwasher dishwasher = new Dishwasher (50);
        Device d4 = dLb.newDevice ("dishwasher", dishwasher, 100);
        dLK.addDevice (d3);
        dLK.addDevice (d4);
        hG1.attachRoomToGrid (bedroom);
        hG1.attachRoomToGrid (kitchen);
        hG2.attachRoomToGrid (garden);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL (h);

        double expectedResult = 272;
        double result = US172CTRL.getGridTotalNominalPower (0);

        assertEquals (expectedResult, result);
    }

    /**
     * Confirm the right total nominal power in a grid if expected result is incorrect
     */
    @Test
    void getGridTotalNominalPowerIfIncorrect() {
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
        Lamp lampObj = new Lamp ( 25);
        Lamp lampObj1 = new Lamp ( 25);
        Device d1 = dLb.newDevice ("lamp1", lampObj, 10);
        Device d2 = dLb.newDevice ("lamp2", lampObj1, 10);
        dLb.addDevice (d1);
        dLb.addDevice (d2);
        Fridge fridge1 = new Fridge ( 25, 50, 25);
        Device d3 = dLb.newDevice ("fridge", fridge1, 100);
        Dishwasher dishwasher = new Dishwasher ( 50);
        Device d4 = dLb.newDevice ("dishwasher", dishwasher, 100);
        dLK.addDevice (d3);
        dLK.addDevice (d4);
        hG.attachRoomToGrid (bedroom);
        hG.attachRoomToGrid (kitchen);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL (h);

        double expectedResult = 221;
        double result = US172CTRL.getGridTotalNominalPower (0);

        assertNotEquals (expectedResult, result);
    }

    /**
     * Confirm the right total nominal power in a room if expected result is correct
     */
    @Test
    void getRoomTotalNominalPower() {
        House house = new House();
        Room r1 = new Room("cozinha", 1, 10, 20, 3);
        Room r2 = new Room("sala", 1, 10, 20, 3);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        OtherDevices tv = new OtherDevices();
        Fridge f = new Fridge(25, 75, 25);
        OtherDevices stove = new OtherDevices();
        Lamp l = new Lamp(75);
        Device d1 = new Device("tv", tv, 200);
        Device d2 = new Device("fridge", f, 300);
        Device d3 = new Device("stove", stove, 500);
        Device d4 = new Device("lamp", l, 100);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(0).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d1);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        GetTotalNominalPowerCTRL US230CTRL = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 300;
        double result = US230CTRL.getRoomTotalNominalPower (1);

        assertEquals(expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * Two grids, both of them with one room
     */
    @Test
    void getSizeRoomListInGridIfOne() {
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
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 1;
        double result = ctrl.getSizeRoomListInGrid (1);

        assertEquals (expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * One grid with two rooms
     */
    @Test
    void getSizeRoomListInGridIfTwo() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid");
        houseGridList.addHouseGrid (houseGrid1);
        RoomList roomList = house.getRoomList ();
        Room room1 = roomList.createNewRoom ("Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom (room1);
        houseGrid1.attachRoomToGrid (room1);
        Room room2 = roomList.createNewRoom ("Kitchen", 0, 1.5, 2, 1.7);
        roomList.addRoom (room2);
        houseGrid1.attachRoomToGrid (room2);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 2;
        double result = ctrl.getSizeRoomListInGrid (0);

        assertEquals (expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * One grid with zero rooms
     */
    @Test
    void getSizeRoomListInGridIfZero() {
        House house = new House ();
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid ("InternalGrid");
        houseGridList.addHouseGrid (houseGrid1);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 0;
        double result = ctrl.getSizeRoomListInGrid (0);

        assertEquals (expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * Two grids, one with rooms the selected with zero rooms
     */
    @Test
    void getSizeRoomListInSecondGridIfZero() {
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
        houseGrid1.attachRoomToGrid (room2);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        double expectedResult = 0;
        double result = ctrl.getSizeRoomListInGrid (1);

        assertEquals (expectedResult, result);
    }

    /**
     * Check if the device list in all rooms of the grid is empty
     * One grid, two rooms, the first with two devices and the other one with one device: sum of three devices
     */
    @Test
    void getSizeDeviceListInGridIfThree() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 2, 2, 2, 2);
        roomList.addRoom (bedroom);
        Room kitchen = roomList.createNewRoom ("kitchen", 1, 2, 2, 2);
        roomList.addRoom (kitchen);
        DeviceList dLbedroom = bedroom.getDeviceList ();
        DeviceList dLkitchen = kitchen.getDeviceList ();
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device ("EWH DAIKIN1", ewh, 15);
        Device dEWH2 = new Device ("EWH DAIKIN2", ewh, 15);
        Device dEWH3 = new Device ("EWH DAIKIN3", ewh, 15);
        dLbedroom.addDevice (dEWH1);
        dLbedroom.addDevice (dEWH2);
        dLkitchen.addDevice (dEWH3);
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid mainGrid = houseGridList.newHouseGrid ("MainGrid");
        houseGridList.addHouseGrid (mainGrid);
        mainGrid.attachRoomToGrid (bedroom);
        mainGrid.attachRoomToGrid (kitchen);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        int expected = 3;
        int result = ctrl.getSizeDeviceListInGrid (0);

        assertEquals (expected, result);
    }

    /**
     * Check if the device list in all rooms of the grid is empty
     * Two grids, one with one room and three devices. The selected one has one room but zero devices: sum of zero devices
     */
    @Test
    void getSizeDeviceListInGridIfZero() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 2, 2, 2, 2);
        roomList.addRoom (bedroom);
        Room kitchen = roomList.createNewRoom ("kitchen", 1, 2, 2, 2);
        roomList.addRoom (kitchen);
        DeviceList dLbedroom = bedroom.getDeviceList ();
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device ("EWH DAIKIN1", ewh, 15);
        Device dEWH2 = new Device ("EWH DAIKIN2", ewh, 15);
        Device dEWH3 = new Device ("EWH DAIKIN3", ewh, 15);
        dLbedroom.addDevice (dEWH1);
        dLbedroom.addDevice (dEWH2);
        dLbedroom.addDevice (dEWH3);
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid mainGrid = houseGridList.newHouseGrid ("MainGrid");
        HouseGrid exteriorGrid = houseGridList.newHouseGrid ("ExteriorGrid");
        houseGridList.addHouseGrid (mainGrid);
        houseGridList.addHouseGrid (exteriorGrid);
        mainGrid.attachRoomToGrid (bedroom);
        exteriorGrid.attachRoomToGrid (kitchen);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        int expected = 0;
        int result = ctrl.getSizeDeviceListInGrid (1);

        assertEquals (expected, result);
    }

    /**
     * Check if the device list in all rooms of the grid is empty
     * Two grids, one with two rooms and two devices. The selected one has no rooms, so there are no devices: sum of zero devices
     */
    @Test
    void getSizeDeviceListInFirstGridIfZero() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 2, 2, 2, 2);
        roomList.addRoom (bedroom);
        Room kitchen = roomList.createNewRoom ("kitchen", 1, 2, 2, 2);
        roomList.addRoom (kitchen);
        DeviceList dLbedroom = bedroom.getDeviceList ();
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device ("EWH DAIKIN1", ewh, 15);
        dLbedroom.addDevice (dEWH1);
        HouseGridList houseGridList = house.getHGListInHouse ();
        HouseGrid mainGrid = houseGridList.newHouseGrid ("MainGrid");
        HouseGrid exteriorGrid = houseGridList.newHouseGrid ("ExteriorGrid");
        houseGridList.addHouseGrid (mainGrid);
        houseGridList.addHouseGrid (exteriorGrid);
        exteriorGrid.attachRoomToGrid (bedroom);
        exteriorGrid.attachRoomToGrid (kitchen);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        int expected = 0;
        int result = ctrl.getSizeDeviceListInGrid (0);

        assertEquals (expected, result);
    }

    /**
     * Check if a room has devices by getting the size of the list of the devices added to that room
     * Two rooms, both with two devices
     */
    @Test
    void getSizeDeviceListInRoomIfTwo() {
        House house = new House();
        Room r1 = new Room("cozinha", 1, 10, 20, 3);
        Room r2 = new Room("sala", 1, 10, 20, 3);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        OtherDevices tv = new OtherDevices();
        OtherDevices stove = new OtherDevices();
        Fridge f = new Fridge(25, 75, 25);
        Lamp l = new Lamp( 75);
        Device d1 = new Device("tv", tv, 200);
        Device d2 = new Device("fridge", f, 300);
        Device d3 = new Device("stove", stove, 500);
        Device d4 = new Device("lamp", l, 100);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(0).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d1);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        int expectedResult = 2;
        int result = ctrl.getSizeDeviceListInRoom (1);

        assertEquals(expectedResult, result);
    }

    /**
     * Check if a room has devices by getting the size of the list of the devices added to that room
     * Two rooms, one with four devices and the selected one with zero
     */
    @Test
    void getSizeDeviceListInRoomIfZero() {
        House house = new House();
        Room r1 = new Room("cozinha", 1, 10, 20, 3);
        Room r2 = new Room("sala", 1, 10, 20, 3);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        OtherDevices tv = new OtherDevices();
        OtherDevices stove = new OtherDevices();
        Fridge f = new Fridge(25, 75, 25);
        Lamp l = new Lamp( 75);
        Device d1 = new Device("tv", tv, 200);
        Device d2 = new Device("fridge", f, 300);
        Device d3 = new Device("stove", stove, 500);
        Device d4 = new Device("lamp", l, 100);
        house.getRoomList().get(1).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d1);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL (house);

        int expectedResult = 0;
        int result = ctrl.getSizeDeviceListInRoom (0);

        assertEquals(expectedResult, result);
    }
}