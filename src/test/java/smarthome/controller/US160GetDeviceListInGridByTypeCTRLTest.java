package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US160GetDeviceListInGridByTypeCTRLTest {

    @Test
    @DisplayName("Return the house's house grid list")
    void getHouseGridListTest () {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        List<HouseGrid> expectedResult = Arrays.asList(hg1,hg2);
        List<HouseGrid> result = ctrl160.getHouseGridListCtrl().getHouseGridList();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house's house grid list size")
    void getHouseGridListSizeTest () {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        int expectedResult = 2;
        int result = ctrl160.getHGListSizeCtrl();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house's house grid list in a string")
    void showHouseGridListInStringTest () {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        String expectedResult = "1 - grid1\n"+
                                "2 - grid2\n";
        String result = ctrl160.showHouseGridListInStringCtrl();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the name of a chosen house grid")
    void getHouseGridNameTest () {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        String expectedResult = "grid1";
        String result = ctrl160.getHouseGridName(1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house grid's room list")
    void getRoomListInAGridTest() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);

        HouseGrid houseGrid = new HouseGrid ("main");
        HouseGrid houseGrid2 = new HouseGrid ("backup");
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid);
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid2);

        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);
        houseGrid.attachRoomToGrid (roomB);


        List<Room> expectedResult = Arrays.asList(roomA,roomB);
        List<Room> result = ctrl160.getListOfRoomsInGrid(1).getRoomList();

        assertEquals (expectedResult, result);
    }

    @Test
    @DisplayName("Return the house grid's room list size")
    void getRoomListInAGridSizeTest() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);

        HouseGrid houseGrid = new HouseGrid ("main");
        HouseGrid houseGrid2 = new HouseGrid ("backup");
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid);
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid2);

        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);
        houseGrid.attachRoomToGrid (roomB);


        int expectedResult = 2;
        int result = ctrl160.getRoomListSizeCtrl(1);

        assertEquals (expectedResult, result);
    }

    @Test
    @DisplayName("Return the house grid's device list size")
    void deviceListInGridCtrlTestSize() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        house.getRoomList().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,10);
        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        int expectedResult = 3;
        int result = ctrl160.getDeviceListInGridSizeCtrl(1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house grid's device list")
    void deviceListInGridCtrlTest() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        house.getRoomList().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,50);
      Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,10);
        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        List<Device> expectedResult = Arrays.asList(d1,d2,d5);
        List<Device> result = ctrl160.getDeviceListInGridCtrl(1).getDeviceList();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house grid's device list but the devices are ordered by type")
    void deviceListInGridCtrlTestGROUPBY() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        house.getRoomList().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,10);
        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        List<Device> expectedResult = Arrays.asList(d2,d1,d5);
        List<Device> result = ctrl160.deviceListGroupByTypeCtrl(1).getDeviceList();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Check if by changing the order of the devices it will show not equals")
    void deviceListInGridCtrlTestGROUPBYnotEquals() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        house.getRoomList().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,10);
        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        List<Device> expectedResult = Arrays.asList(d1,d2,d5);
        List<Device> result = ctrl160.deviceListGroupByTypeCtrl(1).getDeviceList();

        assertNotEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house grid's device list but the devices are ordered by type, in string format. Also show's the location(room) of each device")
    void showGroupedByDeviceListInGridStringTest() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        house.getRoomList().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,10);
        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        String expectedResult = "1 - Device: maq loica | Type: DISHWASHER | Location: cozinha | Active: true\n" +
                                "2 - Device: frigo | Type: FRIDGE | Location: cozinha | Active: true\n" +
                                "3 - Device: candeeiro2 | Type: LAMP | Location: quarto | Active: true\n";
        String result = ctrl160.showGroupedDeviceListInGridString(1);

        assertEquals(expectedResult,result);
    }
}