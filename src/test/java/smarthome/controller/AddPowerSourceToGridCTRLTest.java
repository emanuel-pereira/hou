package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddPowerSourceToGridCTRLTest {

    @Test
    @DisplayName("Method to get a list of the previously inserted House Grids")
    void getHouseGridListTest(){
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);


        List<HouseGrid> expectedResult = Arrays.asList(hg01,hg02);
        List<HouseGrid> result = ctrl135.getHouseGridList();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Method to get the size of the list of the previously inserted House Grids")
    void getHouseGridListTestSize(){
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);


        int expectedResult = 2;
        int result = ctrl135.getHGListSizeCtrl();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Method to get a list of the previously inserted House Grids, testing it adds both with AssertNotEquals")
    void getHouseGridListTestNotEquals (){
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);


        List<HouseGrid> expectedResult = Arrays.asList(hg01);
        List<HouseGrid> result = ctrl135.getHouseGridList();

        assertNotEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Show the List of House Grids in a string")
    void getHouseGridListTestInString(){
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);


        String expectedResult = "1 - energygrid01\n" +
                                "2 - energygrid02\n";
        String result = ctrl135.getHGListInStringCtrl();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Return the name of a chosen house grid")
    void getHouseGridNameTest () {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        String expectedResult = "grid1";
        String result = ctrl135.getHouseGridName(1);

        assertEquals(expectedResult,result);
    }

       @Test
    @DisplayName("Add a Power Source to a House Grid from the List")
    void addPSTest() {
        House house = new House();
           AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
           HouseGrid hg01 = new HouseGrid("energygrid01");
           HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        boolean result = ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);

        assertTrue(result);
    }

    @Test
    @DisplayName("Add a Power Source to a House Grid from the List, asserting with notEquals")
    void addPSTestNotEquals() {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        boolean expectedResult = false;
        new PowerSource("panel002", "solar", 100, 100);

        boolean result = ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);

        assertNotEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Get Power Source List")
    void getPSListTest () {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        PowerSource ps1 = new PowerSource("panel002","solar",100,100);
        PowerSource ps2 = new PowerSource("turbine003", "wind", 100,100);
        hg01.getPSListInHG().addPS(ps1);
        hg01.getPSListInHG().addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1,ps2);
        List<PowerSource> result = ctrl135.getPowerSourceListCtrl(1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Get Power Source List")
    void getPSListTestSize () {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);
        ctrl135.addNewPSToGrid(1,"turbine003", "wind", 100,100);

        int expectedResult = 2;
        int result = ctrl135.getPSListSizeCtrl(1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Get Power Source List Size Zero")
    void getPSListTestSizeZero () {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);
        ctrl135.addNewPSToGrid(1,"turbine003", "wind", 100,100);

        int expectedResult = 0;
        int result = ctrl135.getPSListSizeCtrl(2);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Show Power Source List In String")
    void showPSListInStringTest () {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);
        ctrl135.addNewPSToGrid(1,"turbine003", "wind", 100,100);

        String expectedResult = "1 - panel002, solar type, Maximum Power 100.0 kw, Storage Capacity 100.0 kw.\n2 - turbine003, wind type, Maximum Power 100.0 kw, Storage Capacity 100.0 kw.\n";
        String result = ctrl135.showPowerSourceListInString(1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Test if name/type validation string is true")
    void alphanumericNameTrue () {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);

        boolean result = ctrl135.alphanumericName("painel01");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if name/type validation string is false(does not accept points for example)")
    void alphanumericNameFalse () {
        House house = new House();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house);

        boolean result = ctrl135.alphanumericName("painel1.");
        assertFalse(result);
    }

}