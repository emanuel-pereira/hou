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
    void getHouseGridListTest (){
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);


        List<HouseGrid> expectedResult = Arrays.asList(hg01,hg02);
        List<HouseGrid> result = ctrl135.getHouseGridList();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Method to get a list of the previously inserted House Grids, testing it adds both with AssertNotEquals")
    void getHouseGridListTestNotEquals (){
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);


        List<HouseGrid> expectedResult = Arrays.asList(hg01);
        List<HouseGrid> result = ctrl135.getHouseGridList();

        assertNotEquals(expectedResult, result);
    }

       @Test
    @DisplayName("Add a Power Source to a House Grid from the List")
    void addPSTest() {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house,hgList,psList);
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
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house,hgList, psList);
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
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        PowerSource ps1 = new PowerSource("panel002","solar",100,100);
        PowerSource ps2 = new PowerSource("turbine003", "wind", 100,100);
        hg01.getPSListInHG().addPS(ps1);
        hg01.getPSListInHG().addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1,ps2);
        List<PowerSource> result = ctrl135.getPowerSourceListCtrl(hg01);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Get Power Source List")
    void getPSListTestSize () {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);
        ctrl135.addNewPSToGrid(1,"turbine003", "wind", 100,100);

        int expectedResult = 2;
        int result = ctrl135.getPowerSourceListCtrl(hg01).size();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Show Power Spurce List In String")
    void showPSListInStringTest () {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        AddPowerSourceToGridCTRL ctrl135 = new AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01");
        HouseGrid hg02 = new HouseGrid("energygrid02");
        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);
        ctrl135.addNewPSToGrid(1,"turbine003", "wind", 100,100);

        String expectedResult = "1 - panel002, solar type, Maximum Power 100.0 kw, Storage Capacity 100.0 kw.\n2 - turbine003, wind type, Maximum Power 100.0 kw, Storage Capacity 100.0 kw.\n";
        String result = ctrl135.showPowerSourceListInString(hg01);

        assertEquals(expectedResult,result);

    }
}