package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.House;

import static org.junit.jupiter.api.Assertions.*;

class NewHouseGridCTRLTest {


    @Test
    void createNewHouseGridTestSize() {
        House house = new House();
        NewHouseGridCTRL ctrl130 = new NewHouseGridCTRL (house);
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridListSize ());
        //create new grid
        assertTrue(ctrl130.createNewHouseGrid("main"));
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridListSize ());
        //create new grids
        assertTrue(ctrl130.createNewHouseGrid("backup"));
        assertEquals(2, ctrl130.getHouseGridListSize ());
    }

    @Test
    void createDuplicateGridTest() {
        House house = new House();
        NewHouseGridCTRL ctrl130 = new NewHouseGridCTRL (house);
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridListSize ());
        //create new grid
        assertTrue(ctrl130.createNewHouseGrid("main"));
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridListSize ());
        //create new grids
        assertFalse(ctrl130.createNewHouseGrid("main"));
        assertEquals(1, ctrl130.getHouseGridListSize ());
    }

    @Test
    void presentHouseGridsList() {
        House house = new House();
        NewHouseGridCTRL ctrl130 = new NewHouseGridCTRL (house);
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridListSize ());
        //create new grid
        ctrl130.createNewHouseGrid("main");
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridListSize ());
        //create new grids
        ctrl130.createNewHouseGrid("backup");
        assertEquals(2, ctrl130.getHouseGridListSize ());
        //assert a list of grids in String format
        assertEquals("1 - main\n2 - backup\n",ctrl130.showGridsListInString());
    }
}
