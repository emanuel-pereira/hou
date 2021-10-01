package smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.FanType;
import smarthome.model.devices.OvenType;
import smarthome.model.devices.TvType;
import smarthome.model.devices.WallTowelHeaterType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getGridListInHouse;
import static smarthome.model.House.getHouseRoomList;

class HouseGridTest {

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
    @DisplayName("Set new House Grid with correct Power Value")
    void setAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid("main");
        hg.setContractedMaximumPower(2350);
        double result = hg.getContractedMaximumPower();
        assertEquals(2350, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with incorrect Power Value")
    void setUpdateAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid("grid01");
        hg.setContractedMaximumPower(2351.3);
        double result = hg.getContractedMaximumPower();
        assertEquals(2351.3, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with zero Power Value")
    void setZeroContractedMaximumPower() {
        HouseGrid hg = new HouseGrid("grid01");
        hg.setContractedMaximumPower(0.0);
        double result = hg.getContractedMaximumPower();
        assertEquals(Double.NaN, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with incorrect Power Value")
    void setNegativeAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid("main");

        hg.setContractedMaximumPower(-35.0);

        double result = hg.getContractedMaximumPower();
        assertEquals(Double.NaN, result, 0.1);
    }


    @Test
    @DisplayName("Set new House Grid with name ID")
    void setContractedMaximumPowerAndGridID() {
        HouseGrid hg = new HouseGrid("main grid");
        assertEquals("main grid", hg.getDesignation());
    }

    @Test
    @DisplayName("Get the ID of a House Grid")
    void getGridIDTest() {
        HouseGrid hg = new HouseGrid("grid002");

        String expectedResult = "grid002";
        String result = hg.getDesignation();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get the list of Previously added Power Sources")
    void getPSListTest() {
        HouseGrid houseGrid = new HouseGrid("main");
        PowerSource ps1 = new PowerSource("panel002", "solar", 250, 14);
        PowerSource ps2 = new PowerSource("panel003", "solar", 250, 14);
        houseGrid.getPsListInHG().addPS(ps1);
        houseGrid.getPsListInHG().addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1, ps2);
        List<PowerSource> result = houseGrid.getPsListInHG().getPSList();

        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Attach one room to a grid and return true")
    void attachRoomToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);

        boolean result = houseGrid.attachRoomToGrid(roomA);

        assertTrue(result);

    }

    @Test
    @DisplayName("Attach two different rooms to a grid and return true")
    void attachTwoNewRoomsToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        Room roomB = new Room("R02", "garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.attachRoomToGrid(roomB);

        assertTrue(result);
    }

    @Test
    @DisplayName("Attach the same room twice to a grid and return false")
    void attachSameRoomTwiceToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.attachRoomToGrid(roomA);

        assertFalse(result);
    }

    @Test
    @DisplayName("Attach two equal rooms to a grid and return false")
    void attachTwoEqualRoomsToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        Room roomB = new Room("R01", "bedroom", 1, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.attachRoomToGrid(roomB);

        assertFalse(result);
    }

    @Test
    @DisplayName("Attach two different rooms to a grid and return that room list size")
    void getRoomListInAGridSize() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        Room roomB = new Room("R02", "garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);
        houseGrid.attachRoomToGrid(roomB);

        int expectedResult = 2;
        int result = houseGrid.getRoomListInAGridSize();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Attach two different rooms to a grid and return that room list")
    void getRoomListInAGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        Room roomB = new Room("R02", "garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);
        houseGrid.attachRoomToGrid(roomB);

        List<Room> expectedResult = Arrays.asList(roomA, roomB);
        List<Room> result = houseGrid.getRoomList().getRoomList();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Detach one room from a grid and return true")
    void detachRoomFromGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        Room roomB = new Room("R02", "garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);
        houseGrid.attachRoomToGrid(roomB);

        boolean result = houseGrid.detachRoomFromGrid(roomB);

        assertTrue(result);
    }

    @Test
    @DisplayName("Try to detach a room that doesn't exist in a grid and return false")
    void detachRoom() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        Room roomB = new Room("R02", "garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.detachRoomFromGrid(roomB);

        assertFalse(result);
    }

    @Test
    @DisplayName("Get the Device List in a Grid")
    void getDeviceList() {
         

        HouseGrid hg1 = new HouseGrid("grid1");
        getGridListInHouse().addHouseGrid(hg1);

        Room r1 = new Room("R01", "cozinha", 1, 2, 2, 2);
        Room r3 = new Room("R02", "quarto", 2, 2, 2, 2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        getHouseRoomList().get(0).getDeviceList().add(d1);
        getHouseRoomList().get(0).getDeviceList().add(d2);
        getHouseRoomList().get(0).getDeviceList().add(d3);
        getHouseRoomList().get(1).getDeviceList().add(d4);
        getHouseRoomList().get(1).getDeviceList().add(d5);

        hg1.getRoomList().addRoom(r1);
        hg1.getRoomList().addRoom(r3);

        List<Device> expectedResult = Arrays.asList(d1, d2, d3, d4, d5);
        List<Device> result = hg1.getDeviceListInGrid().getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get the Device List in a Grid from a specific Type")
    void getDeviceListFromType() {

         

        HouseGrid hg1 = new HouseGrid("grid1");
        getGridListInHouse().addHouseGrid(hg1);

        Room r1 = new Room("R01", "cozinha", 1, 2, 2, 2);
        Room r3 = new Room("R02", "quarto", 2, 2, 2, 2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        getHouseRoomList().get(0).getDeviceList().add(d1);
        getHouseRoomList().get(0).getDeviceList().add(d2);
        getHouseRoomList().get(0).getDeviceList().add(d3);
        getHouseRoomList().get(1).getDeviceList().add(d4);
        getHouseRoomList().get(1).getDeviceList().add(d5);

        hg1.getRoomList().addRoom(r1);
        hg1.getRoomList().addRoom(r3);

        List<Device> expectedResult = Arrays.asList(d2, d5);
        List<Device> result = hg1.getDeviceListFromType(16).getDeviceList();

        assertEquals(expectedResult, result);
    }

    /*@Test
    @DisplayName("Get the Device List in a Grid ordered Type")
    void getDeviceListGroupedByType() {
        HouseGrid hg1 = new HouseGrid("grid1");
        getGridListInHouse().addHouseGrid(hg1);

        Room r1 = new Room("R01", "cozinha", 1, 2, 2, 2);
        Room r3 = new Room("R02", "quarto", 2, 2, 2, 2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        getHouseRoomList().get(0).getDeviceList().addDevice(d1);
        getHouseRoomList().get(0).getDeviceList().addDevice(d2);
        getHouseRoomList().get(0).getDeviceList().addDevice(d3);
        getHouseRoomList().get(1).getDeviceList().addDevice(d4);
        getHouseRoomList().get(1).getDeviceList().addDevice(d5);

        hg1.getRoomList().addRoom(r1);
        hg1.getRoomList().addRoom(r3);

        List<Device> expectedResult = Arrays.asList(d1, d3, d4, d2, d5);
        List<Device> result = hg1.getDeviceListInGridGroupBy().getDeviceList();

        assertEquals(expectedResult, result);
    }
*/
    @Test
    @DisplayName("Print the device list in Grid ordered by type")
    void deviceListInGridCtrlTestGroupByTypeInString() {

        HouseGrid hg1 = new HouseGrid("grid1");
        getGridListInHouse().addHouseGrid(hg1);

        Room r1 = new Room("R01", "cozinha", 1, 2, 2, 2);
        Room r3 = new Room("R02", "quarto", 2, 2, 2, 2);
        getHouseRoomList().addRoom(r1);
        getHouseRoomList().addRoom(r3);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        getHouseRoomList().get(0).getDeviceList().add(d1);
        getHouseRoomList().get(0).getDeviceList().add(d2);
        getHouseRoomList().get(0).getDeviceList().add(d3);
        getHouseRoomList().get(1).getDeviceList().add(d4);
        getHouseRoomList().get(1).getDeviceList().add(d5);

        hg1.getRoomList().addRoom(r1);
        hg1.getRoomList().addRoom(r3);

        String expectedResult = "1 - Device: baker | Type: Oven | Location: cozinha | Active: true\n" +
                "2 - Device: Textile Dryer | Type: WallTowelHeater | Location: cozinha | Active: true\n" +
                "3 - Device: Micro Fan | Type: Fan | Location: quarto | Active: true\n" +
                "4 - Device: Silver | Type: Tv | Location: cozinha | Active: true\n" +
                "5 - Device: Smart Tv | Type: Tv | Location: quarto | Active: true\n";
        String result = hg1.showGroupedDeviceListInGridString();

        assertEquals(expectedResult, result);
    }


    @Test
    @DisplayName("Get the nominal power of a grid")
    void getNominalPower() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("R01", "bedroom", 1, 2, 2, 2);
        Room roomB = new Room("R02", "garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);
        houseGrid.attachRoomToGrid(roomB);
        DeviceList deviceList = roomA.getDeviceList();

        try {
            Device fridgeA = deviceList.newDevice("FridgeA", "Fridge", 150);
            Device fridgeB = deviceList.newDevice("FridgeB", "Fridge", 150);
            Device kettle = deviceList.newDevice("KettleA", "Kettle", 1500);
            Device lamp = deviceList.newDevice("LampA", "Lamp", 15);
            deviceList.add(fridgeA);
            deviceList.add(fridgeB);
            deviceList.add(kettle);
            deviceList.add(lamp);
        } catch (Exception e) {
            //Do nothing.
        }
        double expectedResult = 1815;
        double result = houseGrid.getNominalPower();

        assertEquals(expectedResult, result);
    }


    @Test
    public void equalsIfPersonEqualsHouseGrid() {
        String person = "Ricardo";
        HouseGrid grid = new HouseGrid("grid");
        boolean result;

        result = grid.equals(person);

        assertFalse(result);
    }

    @Test
    public void equalsIfGridEqualsGrid() {
        HouseGrid gridA = new HouseGrid("InteriorGrid");
        HouseGrid gridB = new HouseGrid("InteriorGrid");

        boolean result = gridA.equals(gridB);

        assertEquals(gridA.hashCode(), gridB.hashCode());
        assertTrue(result);
    }

    @Test
    public void equalsIfGridEqualsDifferentGrid() {
        HouseGrid gridA = new HouseGrid("InteriorGrid");
        HouseGrid gridB = new HouseGrid("ExteriorGRid");

        boolean result = gridA.equals(gridB);

        assertNotEquals(gridA.hashCode(), gridB.hashCode());
        assertFalse(result);
    }

    @Test
    public void equalsIfGridEqualsSameGrid() {
        HouseGrid grid = new HouseGrid("Main");
        //TODO this test does not make sense
        boolean result = grid.equals(grid);

        assertTrue(result);
    }


    @Test
    @DisplayName("Ensure that getEnergyConsumption returns 80 as devices fridge and ewh2 have both two readings each in  defined time interval")
    void getEnergyConsumption() {
        HouseGrid grid = new HouseGrid("MainGrid");
        RoomList roomList = grid.getRoomList();

        Room kitchen = new Room("R01", "Kitchen", 0, 8, 8, 3);
        Room garage = new Room("R02", "Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitDeviceList = kitchen.getDeviceList();
        DeviceList grDeviceList = garage.getDeviceList();

        grid.setTime(2);
        try {
            Device fridgeA = kitDeviceList.newDevice("LG Fridge", "Fridge", 150);
            Device kettleA = kitDeviceList.newDevice("Daijutsu", "Kettle", 250);
            Device lampA = kitDeviceList.newDevice("Philips Smart Bulb", "Lamp", 15);
            Device fridgeB = kitDeviceList.newDevice("Samsung Fridge", "Fridge", 250);

            kitDeviceList.add(fridgeA);
            kitDeviceList.add(kettleA);
            kitDeviceList.add(lampA);
            grDeviceList.add(lampA);
            grDeviceList.add(fridgeB);

            ReadingList fridgeALog = fridgeA.getActivityLog();
            Reading r1 = new Reading(20, new GregorianCalendar(2018, 2, 1, 9, 10), "C");
            Reading r2 = new Reading(20, new GregorianCalendar(2018, 2, 2, 9, 10), "C");
            Reading r3 = new Reading(20, new GregorianCalendar(2018, 2, 3, 9, 10), "C");
            Reading r4 = new Reading(20, new GregorianCalendar(2018, 2, 4, 9, 10), "C");
            Reading r5 = new Reading(20, new GregorianCalendar(2018, 2, 5, 9, 10), "C");
            Reading r6 = new Reading(20, new GregorianCalendar(2018, 2, 6, 9, 10), "C");

            fridgeALog.addReading(r1);
            fridgeALog.addReading(r2);
            fridgeALog.addReading(r3);
            fridgeALog.addReading(r4);
            fridgeALog.addReading(r5);
            fridgeALog.addReading(r6);

            ReadingList fridgeBLog = fridgeB.getActivityLog();
            fridgeBLog.addReading(r1);
            fridgeBLog.addReading(r2);
            fridgeBLog.addReading(r3);
            fridgeBLog.addReading(r4);
            fridgeBLog.addReading(r5);
            fridgeBLog.addReading(r6);

        } catch (Exception e) {
            //Do nothing.
        }

        Calendar startTime = new GregorianCalendar(2018, 2, 1, 12, 20);
        Calendar endTime = new GregorianCalendar(2018, 2, 6, 15, 20);

        double expected = 200;
        double result = grid.getEnergyConsumption(startTime, endTime);

        assertEquals(expected, result);
    }


    @Test
    void showRoomsInHouseGrid() {
        HouseGrid grid = new HouseGrid("MainGrid");
        RoomList roomList = grid.getRoomList();

        Room kitchen = new Room("R01", "Kitchen", 0, 8, 8, 3);
        Room garage = new Room("R02", "Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        String expected = "1 - Kitchen\n" +
                "2 - Living Room\n";
        String result = grid.showRoomsInHouseGrid();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getEstimatedEnergyConsumption returns 1.09 Kwh")
    void getEstimatedEnergyConsumption() {
        HouseGrid grid = new HouseGrid("MainGrid");
        RoomList roomList = grid.getRoomList();

        Room kitchen = new Room("R01", "Kitchen", 0, 8, 8, 3);
        Room garage = new Room("R02", "Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);
        DeviceList kitDeviceList = kitchen.getDeviceList();
        DeviceList grDeviceList = garage.getDeviceList();

        try {
            Device fridgeA = kitDeviceList.newDevice("LG Fridge", "Fridge", 150);
            Device kettleA = kitDeviceList.newDevice("Daijutsu", "Kettle", 250);
            Device lampA = kitDeviceList.newDevice("Philips Smart Bulb", "Lamp", 15);
            Device fridgeB = kitDeviceList.newDevice("Samsung Fridge", "Fridge", 250);

            kitDeviceList.add(fridgeA);
            kitDeviceList.add(kettleA);
            kitDeviceList.add(lampA);
            grDeviceList.add(lampA);
            grDeviceList.add(fridgeB);

            ReadingList fridgeALog = fridgeA.getActivityLog();
            Reading r1 = new Reading(20, new GregorianCalendar(2018, 2, 1, 9, 10), "C");
            Reading r2 = new Reading(20, new GregorianCalendar(2018, 2, 2, 9, 10), "C");
            Reading r3 = new Reading(20, new GregorianCalendar(2018, 2, 3, 9, 10), "C");
            Reading r4 = new Reading(20, new GregorianCalendar(2018, 2, 4, 9, 10), "C");
            Reading r5 = new Reading(20, new GregorianCalendar(2018, 2, 5, 9, 10), "C");
            Reading r6 = new Reading(20, new GregorianCalendar(2018, 2, 6, 9, 10), "C");

            fridgeALog.addReading(r1);
            fridgeALog.addReading(r2);
            fridgeALog.addReading(r3);
            fridgeALog.addReading(r4);
            fridgeALog.addReading(r5);
            fridgeALog.addReading(r6);

            ReadingList fridgeBLog = fridgeB.getActivityLog();
            fridgeBLog.addReading(r1);
            fridgeBLog.addReading(r2);
            fridgeBLog.addReading(r3);
            fridgeBLog.addReading(r4);
            fridgeBLog.addReading(r5);
            fridgeBLog.addReading(r6);

        } catch (Exception e) {
            //Do nothing.
        }
        double expected = 1.09;
        double result = grid.getEstimatedEnergyConsumption();
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Ensure that getEstimatedEnergyConsumption for grid returns zero as the houseGrid does not have any device")
    void getEstimatedEnergyConsumptionReturnsZero() {
        HouseGrid grid = new HouseGrid("MainGrid");
        RoomList roomList = grid.getRoomList();

        Room kitchen = new Room("R01", "Kitchen", 0, 8, 8, 3);
        Room garage = new Room("R02", "Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        double expected = 0;
        double result = grid.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }
}

