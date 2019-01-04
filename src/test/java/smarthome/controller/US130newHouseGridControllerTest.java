package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.House;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class US130newHouseGridControllerTest {
    House house = new House();
    US130newHouseGridController ctrl130 = new US130newHouseGridController(house);

    @Test
    void createNewHouseGrid() {
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridList().size());
        //create new grid
        ctrl130.createNewHouseGrid(123, house, "main");
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridList().size());
        //create new grids
        ctrl130.createNewHouseGrid(223, house, "backup");
        assertEquals(2, ctrl130.getHouseGridList().size());
        //assert a list of grids in String format
        //assertEquals(,ctrl130.getHouseGridListString());
    }

    @Test
    void presentHouseGridsList() {
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridList().size());
        //create new grid
        ctrl130.createNewHouseGrid(123, house, "main");
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridList().size());
        //create new grids
        ctrl130.createNewHouseGrid(223, house, "backup");
        assertEquals(2, ctrl130.getHouseGridList().size());
        //assert a list of grids in String format
        assertEquals("1 - main\n2 - backup\n",ctrl130.showGridsListInString());
    }
}