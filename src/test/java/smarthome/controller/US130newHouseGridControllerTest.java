package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.HouseGridList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US130newHouseGridControllerTest {


    @Test
    void createNewHouseGridTestSize() {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        US130newHouseGridController ctrl130 = new US130newHouseGridController(house);
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridList().size());
        //create new grid
        ctrl130.createNewHouseGrid("main",123);
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridList().size());
        //create new grids
        ctrl130.createNewHouseGrid("backup",223);
        assertEquals(2, ctrl130.getHouseGridList().size());
        //assert a list of grids in String format
        //assertEquals(,ctrl130.getHouseGridListString());
    }

    @Test
    void createNewHouseGridTestList() {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        US130newHouseGridController ctrl130 = new US130newHouseGridController(house);
        boolean expectedResult = true;
        boolean result = ctrl130.createNewHouseGrid("main",123);

        assertEquals(expectedResult,result);

    }

    @Test
    void presentHouseGridsList() {
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        US130newHouseGridController ctrl130 = new US130newHouseGridController(house);
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridList().size());
        //create new grid
        ctrl130.createNewHouseGrid("main",123);
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridList().size());
        //create new grids
        ctrl130.createNewHouseGrid("backup",223);
        assertEquals(2, ctrl130.getHouseGridList().size());
        //assert a list of grids in String format
        assertEquals("1 - main\n2 - backup\n",ctrl130.showGridsListInString());
    }
}