package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetTotalNominalPowerCTRLTest {

    /**
     * Check if the house grid list has the correct size.
     * House with two grids
     */
    @Test
    void getGridListSizeIfTwo() {
        House house = new House();
        HouseGridList houseGridList = house.getHGListInHouse();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid("InternalGrid");
        houseGridList.addHouseGrid(houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid("ExternalGrid");
        houseGridList.addHouseGrid(houseGrid2);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 2;
        double result = US172CTRL.getGridListSize();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if the house grid list has the correct size.
     * House with no grids
     */
    @Test
    void getGridListSizeIfZero() {
        House house = new House();

        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 0;
        double result = US172CTRL.getGridListSize();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if the room list has the correct size
     * Room list with one room
     */
    @Test
    void getRoomListSizeIfOne() {
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room room1 = roomList.createNewRoom("R01", "Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom(room1);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 1;
        double result = US172CTRL.getRoomListSize();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if the room list has the correct size
     * Room list with zero rooms
     */
    @Test
    void getRoomListSizeIfZero() {
        House house = new House();
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 0;
        double result = US172CTRL.getRoomListSize();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if the house grid list in string is correct by confirming the content
     */
    @Test
    void showGridListInString() {
        House house = new House();
        HouseGridList houseGridList = house.getHGListInHouse();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid("InternalGrid");
        houseGridList.addHouseGrid(houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid("ExternalGrid");
        houseGridList.addHouseGrid(houseGrid2);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL(house);

        String expectedResult = "1 - InternalGrid\n2 - ExternalGrid\n";
        String result = US172CTRL.showGridListInString();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if the room list in string is correct by confirming the content
     */
    @Test
    void showRoomListInString() {
        House house = new House();

        Room r1 = new Room("R01", "cozinha", 1, 10, 20, 3);
        Room r2 = new Room("R02", "sala", 1, 10, 20, 3);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        GetTotalNominalPowerCTRL US230CTRL = new GetTotalNominalPowerCTRL(house);


        String expectedResult = "1 - cozinha\n2 - sala\n";
        String result = US230CTRL.showRoomListInString();

        assertEquals(expectedResult, result);
    }

    /**
     * Confirm the right total nominal power in a grid if expected result is correct (two grids exist)
     */
    @Test
    void getGridTotalNominalPowerIfCorrect() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);
        grid1RoomList.addRoom(kitchen1);
        grid1RoomList.addRoom(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(fridgeA);
        k2DeviceList.add(fridgeB);
        k1DeviceList.add(kettle);
        k1DeviceList.add(lamp);
        GetTotalNominalPowerCTRL US172CTRL = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 1815.0;
        double result = US172CTRL.getGridTotalNominalPower(0);

        assertEquals(expectedResult, result);
    }

    /**
     * Confirm the right total nominal power in a room if expected result is correct
     */
    @Test
    void getRoomTotalNominalPower() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);
        grid1RoomList.addRoom(kitchen1);
        grid1RoomList.addRoom(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(fridgeA);
        k2DeviceList.add(fridgeB);
        k1DeviceList.add(kettle);
        k1DeviceList.add(lamp);

        GetTotalNominalPowerCTRL US230CTRL = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 150;
        double result = US230CTRL.getRoomTotalNominalPower(1);

        assertEquals(expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * Two grids, both of them with one room
     */
    @Test
    void getSizeRoomListInGridIfOne() {
        House house = new House();
        HouseGridList houseGridList = house.getHGListInHouse();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid("InternalGrid");
        houseGridList.addHouseGrid(houseGrid1);
        HouseGrid houseGrid2 = houseGridList.newHouseGrid("ExternalGrid");
        houseGridList.addHouseGrid(houseGrid2);
        RoomList roomList = house.getRoomList();
        Room room1 = roomList.createNewRoom("R01", "Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom(room1);
        houseGrid1.attachRoomToGrid(room1);
        Room room2 = roomList.createNewRoom("R02", "Kitchen", 0, 1.5, 2, 1.7);
        roomList.addRoom(room2);
        houseGrid2.attachRoomToGrid(room2);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 1;
        double result = ctrl.getSizeRoomListInGrid(1);

        assertEquals(expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * One grid with two rooms
     */
    @Test
    void getSizeRoomListInGridIfTwo() {
        House house = new House();
        HouseGridList houseGridList = house.getHGListInHouse();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid("InternalGrid");
        houseGridList.addHouseGrid(houseGrid1);
        RoomList roomList = house.getRoomList();
        Room room1 = roomList.createNewRoom("R01", "Bedroom", 2, 1.5, 2, 1.7);
        roomList.addRoom(room1);
        houseGrid1.attachRoomToGrid(room1);
        Room room2 = roomList.createNewRoom("R02", "Kitchen", 0, 1.5, 2, 1.7);
        roomList.addRoom(room2);
        houseGrid1.attachRoomToGrid(room2);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 2;
        double result = ctrl.getSizeRoomListInGrid(0);

        assertEquals(expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * One grid with zero rooms
     */
    @Test
    void getSizeRoomListInGridIfZero() {
        House house = new House();
        HouseGridList houseGridList = house.getHGListInHouse();
        HouseGrid houseGrid1 = houseGridList.newHouseGrid("InternalGrid");
        houseGridList.addHouseGrid(houseGrid1);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 0;
        double result = ctrl.getSizeRoomListInGrid(0);

        assertEquals(expectedResult, result);
    }

    /**
     * Check if a grid has rooms attached by getting the size of the list of the rooms attached to that grid
     * Two grids, one with rooms the selected with zero rooms
     */
    @Test
    void getSizeRoomListInSecondGridIfZero() {
        House house = new House();
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);
        grid1RoomList.addRoom(kitchen1);
        grid1RoomList.addRoom(kitchen2);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        double expectedResult = 0;
        double result = ctrl.getSizeRoomListInGrid(1);

        assertEquals(expectedResult, result);

        assertEquals(2, ctrl.getSizeRoomListInGrid(0));
    }

    /**
     * Check if the device list in all rooms of the grid is empty
     * Two grids, one with one room and three devices. The selected one has one room but zero devices: sum of zero devices
     */
    @Test
    void getSizeDeviceListInGridIfZero() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);
        grid1RoomList.addRoom(kitchen1);

        DeviceList k1DeviceList = kitchen1.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(fridgeA);
        k1DeviceList.add(kettle);
        k1DeviceList.add(lamp);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        int expected = 3;
        int result = ctrl.getSizeDeviceListInGrid(0);

        assertEquals(expected, result);

        assertEquals(0, ctrl.getSizeDeviceListInRoom(1));
    }

    /**
     * Check if the device list in all rooms of the grid is empty
     * Two grids, one with two rooms and two devices. The selected one has no rooms, so there are no devices: sum of zero devices
     */
    @Test
    void getSizeDeviceListInFirstGridIfZero() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);
        grid1RoomList.addRoom(kitchen1);

        DeviceList k1DeviceList = kitchen1.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k1DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(fridgeA);
        k1DeviceList.add(fridgeB);
        k1DeviceList.add(kettle);
        k1DeviceList.add(lamp);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        int expected = 4;
        int result = ctrl.getSizeDeviceListInGrid(0);

        assertEquals(expected, result);

        assertEquals(0, ctrl.getSizeDeviceListInRoom(1));
    }

    /**
     * Check if a room has devices by getting the size of the list of the devices added to that room
     * Two rooms, both with devices
     */
    @Test
    void getSizeDeviceListInRoomIfTwo() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);
        grid1RoomList.addRoom(kitchen1);
        grid1RoomList.addRoom(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(fridgeA);
        k2DeviceList.add(fridgeB);
        k1DeviceList.add(kettle);
        k1DeviceList.add(lamp);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        int expectedResult = 3;
        int result = ctrl.getSizeDeviceListInRoom(0);
        assertEquals(expectedResult, result);

        assertEquals(1, ctrl.getSizeDeviceListInRoom(1));
    }

    /**
     * Check if a room has devices by getting the size of the list of the devices added to that room
     * Two rooms, one with four devices and the selected one with zero
     */
    @Test
    void getSizeDeviceListInRoomIfZero() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);
        grid1RoomList.addRoom(kitchen1);
        grid1RoomList.addRoom(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k1DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(fridgeA);
        k1DeviceList.add(fridgeB);
        k1DeviceList.add(kettle);
        k1DeviceList.add(lamp);
        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        int expectedResult = 4;
        int result = ctrl.getSizeDeviceListInRoom(0);
        assertEquals(expectedResult, result);

        assertEquals(0, ctrl.getSizeDeviceListInRoom(1));
    }

    @DisplayName("Check devices selection)")
    @Test
    void devicesListNominalPower() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);
        HouseGrid grid2 = new HouseGrid("Grid 2");
        house.addGrid(grid2);

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        grid1.attachRoomToGrid(kitchen1);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        grid2.attachRoomToGrid(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        k1DeviceList.add(fridgeA);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        k1DeviceList.add(kettle);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(lamp);

        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        k2DeviceList.add(fridgeB);

        GetTotalNominalPowerCTRL ctrl = new GetTotalNominalPowerCTRL(house);

        ctrl.newDeviceList();
        ctrl.addDevice(fridgeA);
        ctrl.addDevice(fridgeB);
        double result = ctrl.reportNominalPower();

        assertEquals(300, result);
    }
}