package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;
import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.Room;
import smarthome.model.devices.FanType;
import smarthome.model.devices.OvenType;
import smarthome.model.devices.TvType;
import smarthome.model.devices.WallTowelHeaterType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GetDeviceListInGridByTypeCTRLTest {

    @Test
    @DisplayName("Return the house's house grid list")
    void getHouseGridListTest () {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);

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
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);

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
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(0).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        int expectedResult = 4;
        int result = ctrl160.getDeviceListInGridSizeCtrl(1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house grid's device list")
    void deviceListInGridCtrlTest() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(0).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        List<Device> expectedResult = Arrays.asList(d1,d2,d3,d5);
        List<Device> result = ctrl160.getDeviceListInGridCtrl(1).getDeviceList();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house grid's device list but the devices are ordered by type")
    void deviceListInGridCtrlTestGROUPBY() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(0).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        List<Device> expectedResult = Arrays.asList(d1,d3,d2,d5);
        List<Device> result = ctrl160.deviceListGroupByTypeCtrl(1).getDeviceList();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Check if by changing the order of the devices it will show not equals")
    void deviceListInGridCtrlTestGROUPBYnotEquals() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(0).getDeviceList().addDevice(d3);
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
        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL(house);
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

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(0).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.getRoomListInAGrid().addRoom(r1);
        hg1.getRoomListInAGrid().addRoom(r3);
        hg2.getRoomListInAGrid().addRoom(r2);

        String expectedResult = "1 - Device: baker | Type: Oven | Location: cozinha | Active: true\n" +
                "2 - Device: Textile Dryer | Type: WallTowelHeater | Location: cozinha | Active: true\n" +
                "3 - Device: Silver | Type: Tv | Location: cozinha | Active: true\n" +
                "4 - Device: Smart Tv | Type: Tv | Location: quarto | Active: true\n";
        String result = ctrl160.showGroupedDeviceListInGridString(1);

        assertEquals(expectedResult,result);
    }
}