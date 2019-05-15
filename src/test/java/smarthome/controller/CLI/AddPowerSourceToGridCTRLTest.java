package smarthome.controller.CLI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.controller.CLI.AddPowerSourceToGridCTRL;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getGridListInHouse;


class AddPowerSourceToGridCTRLTest {

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
    @DisplayName("Method to get a list of the previously inserted House Grids")
    void getHouseGridListTest() {

        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();

        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");

        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);


        List<HouseGrid> expectedResult = Arrays.asList(hg01, hg02);
        List<HouseGrid> result = ctrl135.getHouseGridList();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Method to get the size of the list of the previously inserted House Grids")
    void getHouseGridListTestSize() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();


        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        int expectedResult = 2;
        int result = ctrl135.getHGListSizeCtrl();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Method to get a list of the previously inserted House Grids, testing it adds both with AssertNotEquals")
    void getHouseGridListTestNotEquals() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();

        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);


        List<HouseGrid> expectedResult = Arrays.asList(hg01);
        List<HouseGrid> result = ctrl135.getHouseGridList();

        assertNotEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Show the List of House Grids in a string")
    void getHouseGridListTestInString() {

        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();

        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);


        String expectedResult = "1 - energygrid01\n" +
                "2 - energygrid02\n";
        String result = ctrl135.getHGListInStringCtrl();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Return the name of a chosen house grid")
    void getHouseGridNameTest() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();


        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");

        getGridListInHouse().addHouseGrid(hg1);
        getGridListInHouse().addHouseGrid(hg2);

        String expectedResult = "grid1";
        String result = ctrl135.getHouseGridName(1);

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Add a Power Source to a House Grid from the List")
    void addPSTest() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();


        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        boolean result = ctrl135.addNewPSToGrid(1, "panel002", "solar", 100, 100);

        assertTrue(result);
    }

    @Test
    @DisplayName("Add a Power Source to a House Grid from the List, asserting with notEquals")
    void addPSTestNotEquals() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();

        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        boolean expectedResult = false;
        new PowerSource("panel002", "solar", 100, 100);

        boolean result = ctrl135.addNewPSToGrid(1, "panel002", "solar", 100, 100);

        assertNotEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get Power Source List")
    void getPSListTest() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();

        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        PowerSource ps1 = new PowerSource("panel002", "solar", 100, 100);
        PowerSource ps2 = new PowerSource("turbine003", "wind", 100, 100);


        hg01.getPSListInHG().addPS(ps1);
        hg01.getPSListInHG().addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1, ps2);
        List<PowerSource> result = ctrl135.getPowerSourceListCtrl(1);

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get Power Source List")
    void getPSListTestSize() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();

        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        ctrl135.addNewPSToGrid(1, "panel002", "solar", 100, 100);
        ctrl135.addNewPSToGrid(1, "turbine003", "wind", 100, 100);

        int expectedResult = 2;
        int result = ctrl135.getPSListSizeCtrl(1);

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get Power Source List Size Zero")
    void getPSListTestSizeZero() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();

        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        ctrl135.addNewPSToGrid(1, "panel002", "solar", 100, 100);
        ctrl135.addNewPSToGrid(1, "turbine003", "wind", 100, 100);

        int expectedResult = 0;
        int result = ctrl135.getPSListSizeCtrl(2);

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Show Power Source List In String")
    void showPSListInStringTest() {
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL();


        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);


        ctrl135.addNewPSToGrid(1, "panel002", "solar", 100.456, 100.123);
        ctrl135.addNewPSToGrid(1, "turbine003", "wind", 100.096, 100.864);

        String expectedResult = "1 - panel002, solar type, Maximum Power 100.46 kw, Storage Capacity 100.12 kw.\n" +
                "2 - turbine003, wind type, Maximum Power 100.1 kw, Storage Capacity 100.86 kw.\n";
        String result = ctrl135.showPowerSourceListInString(1);

        assertEquals(expectedResult, result);
    }
}