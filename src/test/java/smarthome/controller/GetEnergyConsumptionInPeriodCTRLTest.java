package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetEnergyConsumptionInPeriodCTRLTest {

    @Test
@DisplayName("Ensure that only metered devices names are shown in a string format")
    void showMeteredDevicesInStr() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList = house.getRoomList();

        Room kitchen1 = new Room("Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.addDevice(fridgeA);
        k2DeviceList.addDevice(fridgeB);
        k1DeviceList.addDevice(kettle);
        k1DeviceList.addDevice(lamp);

        String expected = "1 - FridgeA\n2 - KettleA\n3 - LampA\n4 - FridgeB\n";
        String result = ctrl.showMeteredDevicesInStr();
        assertEquals(expected, result);
    }

    @Test
    void getMeteredDevicesInHouseSize() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList = house.getRoomList();

        Room kitchen1 = new Room("Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.addDevice(fridgeA);
        k2DeviceList.addDevice(fridgeB);
        k1DeviceList.addDevice(kettle);
        k1DeviceList.addDevice(lamp);

        int expected = 4;
        int result = ctrl.getMeteredDevicesInHouseSize();

        assertEquals(expected, result);

    }

    @Test
    void getEnergyConsumptionInPeriod() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList = house.getRoomList();

        Room kitchen1 = new Room("Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("Kitchen2", 0, 6, 4, 3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.addDevice(fridgeA);
        k2DeviceList.addDevice(fridgeB);
        k1DeviceList.addDevice(kettle);
        k1DeviceList.addDevice(lamp);

        ReadingList fridgeALog = fridgeA.getActivityLog();
        ReadingList fridgeBLog = fridgeB.getActivityLog();
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 15, 5, 0, 0));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 20));
        Reading r4 = new Reading(37, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 5, 0, 40));
        Reading r6 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 50));
        Reading r7 = new Reading(22, new GregorianCalendar(2018, 11, 5, 1, 0));
        Reading r8 = new Reading(37, new GregorianCalendar(2018, 11, 5, 1, 10));
        fridgeALog.addReading(r2);
        fridgeALog.addReading(r3);
        fridgeALog.addReading(r4);
        fridgeALog.addReading(r5);
        fridgeALog.addReading(r6);
        fridgeALog.addReading(r7);
        fridgeALog.addReading(r8);
        fridgeBLog.addReading(r2);
        fridgeBLog.addReading(r3);
        fridgeBLog.addReading(r4);
        fridgeBLog.addReading(r5);
        fridgeBLog.addReading(r6);
        fridgeBLog.addReading(r7);
        fridgeBLog.addReading(r8);

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 5, 0, 10);
        GregorianCalendar endDate = new GregorianCalendar(2018, 11, 5, 1, 0);

        double expected = 130.0;
        double result = ctrl.getEnergyConsumptionInPeriod(0, startDate, endDate);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getHGName() returns the name of the housegrid as a string")
    void getHGName() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        String expected = "Grid 1";
        String result = ctrl.getHGName(0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that method shows a list of grids in a single string")
    void showHouseGridListInString() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        String expected = "1 - Grid 1\n" +
                "2 - Grid 2\n";
        String result = ctrl.showHouseGridListInString();
        assertEquals(expected, result);
    }

    @Test
    void getHouseGridEnergyConsumptionInPeriod() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("Kitchen2", 0, 6, 4, 3);
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
        k1DeviceList.addDevice(fridgeA);
        k2DeviceList.addDevice(fridgeB);
        k1DeviceList.addDevice(kettle);
        k1DeviceList.addDevice(lamp);

        ReadingList fridgeALog = fridgeA.getActivityLog();
        ReadingList fridgeBLog = fridgeB.getActivityLog();
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 15, 5, 0, 0));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 20));
        Reading r4 = new Reading(37, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 5, 0, 40));
        Reading r6 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 50));
        Reading r7 = new Reading(22, new GregorianCalendar(2018, 11, 5, 1, 0));
        Reading r8 = new Reading(37, new GregorianCalendar(2018, 11, 5, 1, 10));
        fridgeALog.addReading(r2);
        fridgeALog.addReading(r3);
        fridgeALog.addReading(r4);
        fridgeALog.addReading(r5);
        fridgeALog.addReading(r6);
        fridgeALog.addReading(r7);
        fridgeALog.addReading(r8);
        fridgeBLog.addReading(r2);
        fridgeBLog.addReading(r3);
        fridgeBLog.addReading(r4);
        fridgeBLog.addReading(r5);
        fridgeBLog.addReading(r6);
        fridgeBLog.addReading(r7);
        fridgeBLog.addReading(r8);
        Calendar startTime = new GregorianCalendar(2018, 11, 5, 0, 40);
        Calendar endTime = new GregorianCalendar(2018, 11, 5, 1, 0);

        double expected = 80;
        double result=ctrl.getHouseGridEnergyConsumptionInPeriod(0,startTime,endTime);
        assertEquals(expected,result);

        int expectedMeteredsSize=6;
        int resultOfMeteredListSize=ctrl.meteredListSize();
        assertEquals(expectedMeteredsSize,resultOfMeteredListSize);


    }

    @Test
    void getHouseGridListSize() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getHouseGridListSize();
        assertEquals(expected, result);
    }

    @Test
    void showListRoomInString() {
        House house = new House ();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        Room r1 = new Room ("cozinha", 1, 10, 20, 3);
        Room r2 = new Room ("sala", 1, 10, 20, 3);
        house.getRoomList ().addRoom (r1);
        house.getRoomList ().addRoom (r2);

        String expectedResult = "1 - cozinha\n2 - sala\n";
        String result = ctrl.showRoomListInStr ();

        assertEquals (expectedResult, result);
    }

    @Test
    void getRoomListSize() {
        House house = new House ();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        Room r1 = new Room ("cozinha", 1, 10, 20, 3);
        Room r2 = new Room ("sala", 1, 10, 20, 3);
        house.getRoomList ().addRoom (r1);
        house.getRoomList ().addRoom (r2);

        int expectedResult = 2;
        int result = ctrl.getRoomListSize ();

        assertEquals (expectedResult, result);
    }

    /**
     * Get the total energy consumption of a room with three devices, two of them area metered from a a list off
     * two rooms and return the correct sum
     */
    @Test
    void getDeviceName() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("Kitchen2", 0, 6, 4, 3);
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
        k1DeviceList.addDevice(fridgeA);
        k2DeviceList.addDevice(fridgeB);
        k1DeviceList.addDevice(kettle);
        k1DeviceList.addDevice(lamp);

        String deviceName = ctrl.getDeviceName(0);
        assertEquals("FridgeA", deviceName);
    }

    @Test
    void getRoomEnergyConsumptionInPeriod() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList = house.getRoomList();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("Kitchen2", 0, 6, 4, 3);
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
        k1DeviceList.addDevice(fridgeA);
        k2DeviceList.addDevice(fridgeB);
        k1DeviceList.addDevice(kettle);
        k1DeviceList.addDevice(lamp);

        ReadingList fridgeALog = fridgeA.getActivityLog();
        ReadingList fridgeBLog = fridgeB.getActivityLog();
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 15, 5, 0, 0));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 20));
        Reading r4 = new Reading(37, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 5, 0, 40));
        Reading r6 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 50));
        Reading r7 = new Reading(22, new GregorianCalendar(2018, 11, 5, 1, 0));
        Reading r8 = new Reading(37, new GregorianCalendar(2018, 11, 5, 1, 10));
        fridgeALog.addReading(r2);
        fridgeALog.addReading(r3);
        fridgeALog.addReading(r4);
        fridgeALog.addReading(r5);
        fridgeALog.addReading(r6);
        fridgeALog.addReading(r7);
        fridgeALog.addReading(r8);

        fridgeBLog.addReading(r2);
        fridgeBLog.addReading(r3);
        fridgeBLog.addReading(r4);
        fridgeBLog.addReading(r5);
        fridgeBLog.addReading(r6);
        fridgeBLog.addReading(r7);
        fridgeBLog.addReading(r8);

        Calendar startTime = new GregorianCalendar(2018, 11, 5, 0, 10);
        Calendar endTime = new GregorianCalendar(2018, 11, 5, 1, 0);

        double expected = 130.0;
        double result = ctrl.getRoomEnergyConsumption(0, startTime, endTime);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the room name is shown")
    void getRoomName() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        Room bedroom = new Room("Bedroom",1,2,2,2);
        Room office = new Room("Office",0,2,1,1);
        RoomList rList = house.getRoomList ();
        rList.addRoom (bedroom);
        rList.addRoom (office);
        String expected = "Bedroom";
        String result = ctrl.getRoomName (0);
        assertEquals(expected, result);
    }

    @Test
    void getMeteredName() { House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGridList houseGridList=house.getHGListInHouse();
        HouseGrid grid= new HouseGrid("MainGrid");
        houseGridList.addHouseGrid(grid);


        RoomList roomList=grid.getRoomListInAGrid();
        Room kitchen= new Room("Kitchen",0,8,8,3);
        Room garage= new Room("Living Room",0,5,4,3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitDeviceList= kitchen.getDeviceList();
        DeviceList grDeviceList= garage.getDeviceList();

        DeviceSpecs fridgeSpecs= new Fridge();
        Device fridge= new Device("LG Fridge",fridgeSpecs,2);

        DeviceSpecs ewhSpecs= new ElectricWaterHeater();
        Device ewh1= new Device("Daikin EWH1",ewhSpecs,2);
        Device ewh2= new Device("Daikin EWH2",ewhSpecs,2);

        kitDeviceList.addDevice(fridge);
        kitDeviceList.addDevice(ewh1);
        grDeviceList.addDevice(ewh2);

        String expected="Living Room";
        String result=ctrl.getMeteredName(2);
        assertEquals(expected,result);}



    @Test
    void showMetered() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGridList houseGridList=house.getHGListInHouse();
        HouseGrid grid= new HouseGrid("MainGrid");
        houseGridList.addHouseGrid(grid);


        RoomList roomList=grid.getRoomListInAGrid();
        Room kitchen= new Room("Kitchen",0,8,8,3);
        Room garage= new Room("Living Room",0,5,4,3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitDeviceList= kitchen.getDeviceList();
        DeviceList grDeviceList= garage.getDeviceList();

        DeviceSpecs fridgeSpecs= new Fridge();
        Device fridge= new Device("LG Fridge",fridgeSpecs,2);

        DeviceSpecs ewhSpecs= new ElectricWaterHeater();
        Device ewh1= new Device("Daikin EWH1",ewhSpecs,2);
        Device ewh2= new Device("Daikin EWH2",ewhSpecs,2);

        kitDeviceList.addDevice(fridge);
        kitDeviceList.addDevice(ewh1);
        grDeviceList.addDevice(ewh2);

        String expected="1 - MainGrid\n" +
                "2 - Kitchen\n" +
                "3 - Living Room\n" +
                "4 - LG Fridge\n" +
                "5 - Daikin EWH1\n" +
                "6 - Daikin EWH2\n";
        String result=ctrl.showMetered();
        assertEquals(expected,result);
    }
}