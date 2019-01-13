package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US135AddPowerSourceToGridCTRLTest {

    @Test
    @DisplayName("Method to get a list of the previously inserted House Grids")
    void getHouseGridListTest (){
        House house = new House();
        HouseGrid houseGrid = new HouseGrid();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        US135AddPowerSourceToGridCTRL ctrl135 = new US135AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01",300);
        HouseGrid hg02 = new HouseGrid("energygrid02",200);
        hgList.addHouseGrid(hg01);
        hgList.addHouseGrid(hg02);


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
        US135AddPowerSourceToGridCTRL ctrl135 = new US135AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01",300);
        HouseGrid hg02 = new HouseGrid("energygrid02",200);
        hgList.addHouseGrid(hg01);
        hgList.addHouseGrid(hg02);


        List<HouseGrid> expectedResult = Arrays.asList(hg01);
        List<HouseGrid> result = ctrl135.getHouseGridList();

        assertNotEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Method that shows the House Grid List in a String")
    void showHouseGridListInString(){
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        US135AddPowerSourceToGridCTRL ctrl135 = new US135AddPowerSourceToGridCTRL(house,hgList, psList);
        HouseGrid hg01 = new HouseGrid("energygrid01",300);
        HouseGrid hg02 = new HouseGrid("energygrid02",200);
        hgList.addHouseGrid(hg01);
        hgList.addHouseGrid(hg02);

        String expectedResult = "1 - energygrid01, Nominal Power: 300.0\n2 - energygrid02, Nominal Power: 200.0\n";
        String result = ctrl135.showHouseGridListInString();
        assertEquals(expectedResult,result);
    }



    @Test
    @DisplayName("Add a Power Source to a House Grid from the List")
    void addPSTest() {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        US135AddPowerSourceToGridCTRL ctrl135 = new US135AddPowerSourceToGridCTRL(house,hgList,psList);
        HouseGrid hg01 = new HouseGrid("energygrid01",300);
        HouseGrid hg02 = new HouseGrid("energygrid02",200);
        ctrl135.addHouseGridCtrl(hg01);
        house.addHGinHGLinHouse(hg02);

        //boolean expectedResult = true; /*new PowerSource("panel002", "solar", 100, 100);*/
        boolean result = ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);

        //assertEquals(expectedResult,result);
        assertTrue(result);
    }

    @Test
    @DisplayName("Add a Power Source to a House Grid from the List, asserting with notEquals")
    void addPSTestNotEquals() {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        US135AddPowerSourceToGridCTRL ctrl135 = new US135AddPowerSourceToGridCTRL(house,hgList, psList);
        HouseGrid hg01 = new HouseGrid("energygrid01",300);
        HouseGrid hg02 = new HouseGrid("energygrid02",200);
        hgList.addHouseGrid(hg01);
        hgList.addHouseGrid(hg02);

        boolean expectedResult = false; /*new PowerSource("panel002", "solar", 100, 100);*/
        boolean result = ctrl135.addNewPSToGrid(1,"panel002","solar",100,100);

        assertNotEquals(expectedResult,result);
    }
}