package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;
import smarthome.model.devices.FanType;
import smarthome.model.devices.OvenType;
import smarthome.model.devices.TvType;
import smarthome.model.devices.WallTowelHeaterType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static smarthome.model.House.getHGListInHouse;
import static smarthome.model.House.getHouseRoomList;

class GetDeviceListInGridByTypeCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);


    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    @DisplayName("Return the house's house grid list")
    void getHouseGridListTest () {

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);


        List<HouseGrid> expectedResult = Arrays.asList(hg1,hg2);
        List<HouseGrid> result = ctrl160.getHouseGridListCtrl().getHouseGridList();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house's house grid list size")
    void getHouseGridListSizeTest () {

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        int expectedResult = 2;
        int result = ctrl160.getHGListSizeCtrl();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house's house grid list in a string")
    void showHouseGridListInStringTest () {

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        String expectedResult = "1 - grid1\n"+
                "2 - grid2\n";
        String result = ctrl160.showHouseGridListInStringCtrl();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the name of a chosen house grid")
    void getHouseGridNameTest () {

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        String expectedResult = "grid1";
        String result = ctrl160.getHouseGridName(1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Return the house grid's room list")
    void getRoomListInAGridTest() {

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();

        HouseGrid houseGrid = new HouseGrid ("main");
        HouseGrid houseGrid2 = new HouseGrid ("backup");
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid);
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid2);

        Room roomA = new Room ("R01","bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("R01","garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);
        houseGrid.attachRoomToGrid (roomB);


        List<Room> expectedResult = Arrays.asList(roomA,roomB);
        List<Room> result = ctrl160.getListOfRoomsInGrid(1).getRoomList();

        assertEquals (expectedResult, result);
    }

    @Test
    @DisplayName("Return the house grid's room list size")
    void getRoomListInAGridSizeTest() {

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();

        HouseGrid houseGrid = new HouseGrid ("main");
        HouseGrid houseGrid2 = new HouseGrid ("backup");
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid);
        ctrl160.getHouseGridListCtrl().addHouseGrid(houseGrid2);

        Room roomA = new Room ("R01","bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("R01","garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);
        houseGrid.attachRoomToGrid (roomB);


        int expectedResult = 2;
        int result = ctrl160.getRoomListSizeCtrl(1);

        assertEquals (expectedResult, result);
    }

    @Test
    @DisplayName("Return the house grid's device list size")
    void deviceListInGridCtrlTestSize() {

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("R01","cozinha",1,2,2,2);
        Room r2 = new Room("R02","sala",1,2,2,2);
        Room r3 = new Room("R03","quarto",2,2,2,2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r2);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);


        getHouseRoomList().get(0).getDeviceList().addDevice(d1);
        getHouseRoomList().get(0).getDeviceList().addDevice(d2);
        getHouseRoomList().get(0).getDeviceList().addDevice(d3);
        getHouseRoomList().get(1).getDeviceList().addDevice(d4);
        getHouseRoomList().get(2).getDeviceList().addDevice(d5);

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

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("R01","cozinha",1,2,2,2);
        Room r2 = new Room("R02","sala",1,2,2,2);
        Room r3 = new Room("R03","quarto",2,2,2,2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r2);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);


        getHouseRoomList().get(0).getDeviceList().addDevice(d1);
        getHouseRoomList().get(0).getDeviceList().addDevice(d2);
        getHouseRoomList().get(0).getDeviceList().addDevice(d3);
        getHouseRoomList().get(1).getDeviceList().addDevice(d4);
        getHouseRoomList().get(2).getDeviceList().addDevice(d5);

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

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("R01","cozinha",1,2,2,2);
        Room r2 = new Room("R02","sala",1,2,2,2);
        Room r3 = new Room("R03","quarto",2,2,2,2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r2);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        getHouseRoomList().get(0).getDeviceList().addDevice(d1);
        getHouseRoomList().get(0).getDeviceList().addDevice(d2);
        getHouseRoomList().get(0).getDeviceList().addDevice(d3);
        getHouseRoomList().get(1).getDeviceList().addDevice(d4);
        getHouseRoomList().get(2).getDeviceList().addDevice(d5);

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

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("R01","cozinha",1,2,2,2);
        Room r2 = new Room("R02","sala",1,2,2,2);
        Room r3 = new Room("R03","quarto",2,2,2,2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r2);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        getHouseRoomList().get(0).getDeviceList().addDevice(d1);
        getHouseRoomList().get(0).getDeviceList().addDevice(d2);
        getHouseRoomList().get(0).getDeviceList().addDevice(d3);
        getHouseRoomList().get(1).getDeviceList().addDevice(d4);
        getHouseRoomList().get(2).getDeviceList().addDevice(d5);

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

        GetDeviceListInGridByTypeCTRL ctrl160 = new GetDeviceListInGridByTypeCTRL();
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        getHGListInHouse().addHouseGrid(hg1);
        getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("R01","cozinha",1,2,2,2);
        Room r2 = new Room("R02","sala",1,2,2,2);
        Room r3 = new Room("R03","quarto",2,2,2,2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r2);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker",420);
        Device d2 = typeTv.createDevice("Silver",200);
        Device d3 = typeWth.createDevice("Textile Dryer",300);
        Device d4 = typeFan.createDevice("Micro Fan",250);
        Device d5 = typeTv.createDevice("Smart Tv",200);

        getHouseRoomList().get(0).getDeviceList().addDevice(d1);
        getHouseRoomList().get(0).getDeviceList().addDevice(d2);
        getHouseRoomList().get(0).getDeviceList().addDevice(d3);
        getHouseRoomList().get(1).getDeviceList().addDevice(d4);
        getHouseRoomList().get(2).getDeviceList().addDevice(d5);

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