package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseGridTest {

    @Test
    @DisplayName("Set new House Grid with correct Power Value")
    void setAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid ("main");
        hg.setContractedMaximumPower (2350);
        double result = hg.getContractedMaximumPower ();
        assertEquals (2350, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with incorrect Power Value")
    void setUpdateAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid ("grid01");
        hg.setContractedMaximumPower (2351.3);
        double result = hg.getContractedMaximumPower ();
        assertEquals (2351.3, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with zero Power Value")
    void setZeroContractedMaximumPower() {
        HouseGrid hg = new HouseGrid ("grid01");
        hg.setContractedMaximumPower (0.0);
        double result = hg.getContractedMaximumPower ();
        assertEquals (Double.NaN, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with incorrect Power Value")
    void setNegativeAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid ("main");

        hg.setContractedMaximumPower (-35.0);

        double result = hg.getContractedMaximumPower ();
        assertEquals (Double.NaN, result, 0.1);
    }


    @Test
    @DisplayName("Set new House Grid with name ID")
    void setContractedMaximumPowerAndGridID() {
        HouseGrid hg = new HouseGrid ("main grid");
        assertEquals ("main grid", hg.getGridID ());
    }

    @Test
    @DisplayName("Get the ID of a House Grid")
    void getGridIDTest() {
        HouseGrid hg = new HouseGrid ("grid002");

        String expectedResult = "grid002";
        String result = hg.getGridID ();
        assertEquals (expectedResult, result);
    }

    @Test
    @DisplayName("Get the list of Previously added Power Sources")
    void getPSListTest() {
        HouseGrid houseGrid = new HouseGrid ("main");
        PowerSource ps1 = new PowerSource ("panel002", "solar", 250, 14);
        PowerSource ps2 = new PowerSource ("panel003", "solar", 250, 14);
        houseGrid.getPSListInHG ().addPS (ps1);
        houseGrid.getPSListInHG ().addPS (ps2);

        List<PowerSource> expectedResult = Arrays.asList (ps1, ps2);
        List<PowerSource> result = houseGrid.getPSListInHG ().getPSList ();

        assertEquals (expectedResult, result);

    }

    @Test
    @DisplayName("Attach one room to a grid and return true")
    void attachRoomToGrid() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);

        boolean result = houseGrid.attachRoomToGrid (roomA);

        assertTrue (result);

    }

    @Test
    @DisplayName("Attach two different rooms to a grid and return true")
    void attachTwoNewRoomsToGrid() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);

        boolean result = houseGrid.attachRoomToGrid (roomB);

        assertTrue (result);
    }

    @Test
    @DisplayName("Attach the same room twice to a grid and return false")
    void attachSameRoomTwiceToGrid() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);

        boolean result = houseGrid.attachRoomToGrid (roomA);

        assertFalse (result);
    }

    @Test
    @DisplayName("Attach two equal rooms to a grid and return false")
    void attachTwoEqualRoomsToGrid() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("bedroom", 1, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);

        boolean result = houseGrid.attachRoomToGrid (roomB);

        assertFalse (result);
    }

    @Test
    @DisplayName("Attach two different rooms to a grid and return that grid")
    void getRoomListInAGrid() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);
        houseGrid.attachRoomToGrid (roomB);

        int expectedResult = 2;
        int result = houseGrid.getRoomListInAGrid ().getRoomList ().size ();

        assertEquals (expectedResult, result);
    }

    @Test
    @DisplayName("Detach one room from a grid and return true")
    void detachRoomFromGrid() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);
        houseGrid.attachRoomToGrid (roomB);

        boolean result = houseGrid.detachRoomFromGrid (roomB);

        assertTrue (result);
    }

    @Test
    @DisplayName("Try to detach a room that doesn't exist in a grid and return false")
    void detachRoom() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);

        boolean result = houseGrid.detachRoomFromGrid (roomB);

        assertFalse (result);
    }

    @Test
    @DisplayName("Get the nominal power of a grid")
    void getNominalPower() {
        HouseGrid houseGrid = new HouseGrid ("main");
        Room roomA = new Room ("bedroom", 1, 2, 2, 2);
        Room roomB = new Room ("garden", 0, 2, 2, 2);
        houseGrid.attachRoomToGrid (roomA);
        houseGrid.attachRoomToGrid (roomB);
        DeviceList deviceList = roomA.getDeviceList ();
        Fridge fridge = new Fridge (DeviceType.FRIDGE, 20, 100, 100);
        Device fridgeA = deviceList.newDevice ("FridgeA", fridge, 150);
        Device fridgeB = deviceList.newDevice ("FridgeB", fridge, 150);
        deviceList.addDevice (fridgeA);
        deviceList.addDevice (fridgeB);

        double expectedResult = 300;
        double result = houseGrid.getNominalPower ();

        assertEquals (expectedResult, result);
    }

}

