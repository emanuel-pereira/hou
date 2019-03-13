package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetEnergyConsumptionInPeriodCTRLTest {


    @Test
    void getEnergyConsumptionInPeriod() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        Room kitchen1 = new Room("Kitchen1", 0, 5, 5, 3);
        Room kitchen2 = new Room("Kitchen2", 0, 6, 4, 3);
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

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 5, 0, 10);
        GregorianCalendar endDate = new GregorianCalendar(2018, 11, 5, 1, 0);

        double expected = 260.0;
        double result = ctrl.getEnergyConsumptionInPeriod(0, startDate, endDate);

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
        double result = ctrl.getEnergyConsumptionInPeriod(0, startTime, endTime);
        assertEquals(expected, result);

        int expectedMeteredsSize = 8;
        int resultOfMeteredListSize = ctrl.meteredListSize();
        assertEquals(expectedMeteredsSize, resultOfMeteredListSize);
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

        double expected = 260.0;
        double result = ctrl.getEnergyConsumptionInPeriod(0, startTime, endTime);
        assertEquals(expected, result);
    }


    @Test
    void getMeteredName() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGridList houseGridList = house.getHGListInHouse();
        HouseGrid grid = new HouseGrid("MainGrid");
        houseGridList.addHouseGrid(grid);


        RoomList roomList = grid.getRoomListInAGrid();
        Room kitchen = new Room("Kitchen", 0, 8, 8, 3);
        Room garage = new Room("Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitDeviceList = kitchen.getDeviceList();
        DeviceList grDeviceList = garage.getDeviceList();


        Device fridgeA = kitDeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = grDeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = kitDeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = grDeviceList.newDevice("LampA", "Lamp", 15);
        kitDeviceList.addDevice(fridgeA);
        grDeviceList.addDevice(fridgeB);
        kitDeviceList.addDevice(kettle);
        kitDeviceList.addDevice(lamp);

        String expected = "Living Room";
        String result = ctrl.getMeteredName(2);
        assertEquals(expected, result);
    }


    @Test
    void showMetered() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGridList houseGridList = house.getHGListInHouse();
        HouseGrid grid = new HouseGrid("MainGrid");
        houseGridList.addHouseGrid(grid);


        RoomList roomList = grid.getRoomListInAGrid();
        Room kitchen = new Room("Kitchen", 0, 8, 8, 3);
        Room garage = new Room("Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitDeviceList = kitchen.getDeviceList();
        DeviceList grDeviceList = garage.getDeviceList();


        Device fridgeA = kitDeviceList.newDevice("FridgeA", "Fridge", 150);
        Device fridgeB = grDeviceList.newDevice("FridgeB", "Fridge", 150);
        Device kettle = kitDeviceList.newDevice("KettleA", "Kettle", 1500);
        Device lamp = grDeviceList.newDevice("LampA", "Lamp", 15);
        kitDeviceList.addDevice(fridgeA);
        grDeviceList.addDevice(fridgeB);
        kitDeviceList.addDevice(kettle);
        kitDeviceList.addDevice(lamp);

        String expected = "1 - MainGrid\n" +
                "2 - Kitchen\n" +
                "3 - Living Room\n" +
                "4 - FridgeA\n" +
                "5 - KettleA\n" +
                "6 - LampA\n" +
                "7 - FridgeB\n";
        String result = ctrl.showMetered();
        assertEquals(expected, result);
    }
}