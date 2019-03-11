package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseGridTest {

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
        assertEquals("main grid", hg.getGridID());
    }

    @Test
    @DisplayName("Get the ID of a House Grid")
    void getGridIDTest() {
        HouseGrid hg = new HouseGrid("grid002");

        String expectedResult = "grid002";
        String result = hg.getGridID();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get the list of Previously added Power Sources")
    void getPSListTest() {
        HouseGrid houseGrid = new HouseGrid("main");
        PowerSource ps1 = new PowerSource("panel002", "solar", 250, 14);
        PowerSource ps2 = new PowerSource("panel003", "solar", 250, 14);
        houseGrid.getPSListInHG().addPS(ps1);
        houseGrid.getPSListInHG().addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1, ps2);
        List<PowerSource> result = houseGrid.getPSListInHG().getPSList();

        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Attach one room to a grid and return true")
    void attachRoomToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);

        boolean result = houseGrid.attachRoomToGrid(roomA);

        assertTrue(result);

    }

    @Test
    @DisplayName("Attach two different rooms to a grid and return true")
    void attachTwoNewRoomsToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        Room roomB = new Room("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.attachRoomToGrid(roomB);

        assertTrue(result);
    }

    @Test
    @DisplayName("Attach the same room twice to a grid and return false")
    void attachSameRoomTwiceToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.attachRoomToGrid(roomA);

        assertFalse(result);
    }

    @Test
    @DisplayName("Attach two equal rooms to a grid and return false")
    void attachTwoEqualRoomsToGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        Room roomB = new Room("bedroom", 1, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.attachRoomToGrid(roomB);

        assertFalse(result);
    }

    @Test
    @DisplayName("Attach two different rooms to a grid and return that room list size")
    void getRoomListInAGridSize() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        Room roomB = new Room("garden", 0, 2, 2, 2);
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
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        Room roomB = new Room("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);
        houseGrid.attachRoomToGrid(roomB);

        List<Room> expectedResult = Arrays.asList(roomA, roomB);
        List<Room> result = houseGrid.getRoomListInAGrid().getRoomList();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Detach one room from a grid and return true")
    void detachRoomFromGrid() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        Room roomB = new Room("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);
        houseGrid.attachRoomToGrid(roomB);

        boolean result = houseGrid.detachRoomFromGrid(roomB);

        assertTrue(result);
    }

    @Test
    @DisplayName("Try to detach a room that doesn't exist in a grid and return false")
    void detachRoom() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        Room roomB = new Room("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);

        boolean result = houseGrid.detachRoomFromGrid(roomB);

        assertFalse(result);
    }


    @Test
    @DisplayName("Get the nominal power of a grid")
    void getNominalPower() {
        HouseGrid houseGrid = new HouseGrid("main");
        Room roomA = new Room("bedroom", 1, 2, 2, 2);
        Room roomB = new Room("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid(roomA);
        houseGrid.attachRoomToGrid(roomB);
        DeviceList deviceList = roomA.getDeviceList();

        try{
            Device fridgeA = deviceList.newDevice("FridgeA", "Fridge", 150);
            Device fridgeB = deviceList.newDevice("FridgeB", "Fridge", 150);
            Device kettle = deviceList.newDevice("KettleA", "Kettle", 1500);
            Device lamp = deviceList.newDevice("LampA", "Lamp", 15);
            deviceList.addDevice(fridgeA);
            deviceList.addDevice(fridgeB);
            deviceList.addDevice(kettle);
            deviceList.addDevice(lamp);
        }
        catch(Exception e){
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

/*
    @Test
    @DisplayName("Ensure that getDailyEnergyConsumption returns 80 as devices fridge and ewh2 have both two readings each in  defined time interval")
    void getDailyEnergyConsumption() {
        HouseGrid grid = new HouseGrid ("MainGrid");
        RoomList roomList = grid.getRoomListInAGrid ();

        Room kitchen = new Room ("Kicthen", 0, 8, 8, 3);
        Room garage = new Room ("Living Room", 0, 5, 4, 3);
        roomList.addRoom (kitchen);
        roomList.addRoom (garage);

        DeviceList kitDeviceList = kitchen.getDeviceList ();
        DeviceList grDeviceList = garage.getDeviceList ();

        DeviceSpecs fridgeSpecs = new Fridge();
        Device fridge = new Device("LG Fridge", fridgeSpecs, 2);

        DeviceSpecs ewhSpecs = new ElectricWaterHeater ();
        Device ewh1 = new Device("Daikin EWH1", ewhSpecs, 2);
        Device ewh2 = new Device("Daikin EWH1", ewhSpecs, 2);

        kitDeviceList.addDevice (fridge);
        kitDeviceList.addDevice (ewh1);
        grDeviceList.addDevice (ewh2);

        ReadingList fridgeActivityLog = fridge.getActivityLog ();
        Reading r1 = new Reading (20, new GregorianCalendar (2018, 2, 1, 9, 10));
        Reading r2 = new Reading (20, new GregorianCalendar (2018, 2, 1, 12, 10));
        Reading r3 = new Reading (20, new GregorianCalendar (2018, 2, 1, 12, 20));
        Reading r4 = new Reading (20, new GregorianCalendar (2018, 2, 1, 12, 30));
        Reading r5 = new Reading (20, new GregorianCalendar (2018, 2, 1, 14, 40));
        Reading r6 = new Reading (20, new GregorianCalendar (2018, 2, 1, 17, 50));
        fridgeActivityLog.addReading (r1);
        fridgeActivityLog.addReading (r2);
        fridgeActivityLog.addReading (r3);
        fridgeActivityLog.addReading (r4);
        fridgeActivityLog.addReading (r5);
        fridgeActivityLog.addReading (r6);

        ReadingList ewh2ActivityLog = ewh2.getActivityLog ();
        ewh2ActivityLog.addReading (r1);
        ewh2ActivityLog.addReading (r2);
        ewh2ActivityLog.addReading (r3);
        ewh2ActivityLog.addReading (r4);
        ewh2ActivityLog.addReading (r5);
        ewh2ActivityLog.addReading (r6);

        Calendar startTime = new GregorianCalendar (2018, 2, 1, 12, 20);
        Calendar endTime = new GregorianCalendar (2018, 2, 1, 15, 20);

        double expected = 80;
        double result = grid.getDailyEnergyConsumption (startTime, endTime);

        assertEquals(expected,result);
    }
*/


    @Test
    void showRoomsInHouseGrid() {
        HouseGrid grid = new HouseGrid("MainGrid");
        RoomList roomList = grid.getRoomListInAGrid();

        Room kitchen = new Room("Kicthen", 0, 8, 8, 3);
        Room garage = new Room("Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        String expected = "1 - Kicthen\n" +
                "2 - Living Room\n";
        String result = grid.showRoomsInHouseGrid();

        assertEquals(expected, result);
    }

    @Test
    void showRoomListInGridInString() {
        House house = new House();
        HouseGrid hg1 = new HouseGrid("grid1");
        house.getHGListInHouse().addHouseGrid(hg1);

        Room r1 = new Room("cozinha", 1, 2, 2, 2);
        Room r3 = new Room("quarto", 2, 2, 2, 2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r3);

        hg1.attachRoomToGrid(r1);
        hg1.attachRoomToGrid(r3);

        String expectedResult = "1 - cozinha\n" +
                "2 - quarto\n";
        String result = hg1.showRoomsInHouseGrid();

        assertEquals(expectedResult, result);
    }

}

