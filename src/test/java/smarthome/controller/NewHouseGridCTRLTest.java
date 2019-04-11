package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;


import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;



class NewHouseGridCTRLTest {

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
    void createNewHouseGridTestSize() {
        NewHouseGridCTRL ctrl130 = new NewHouseGridCTRL();
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridListSize());
        //create new grid
        assertTrue(ctrl130.createNewHouseGrid("main"));
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridListSize());
        //create new grids
        assertTrue(ctrl130.createNewHouseGrid("backup"));
        assertEquals(2, ctrl130.getHouseGridListSize());
    }

    @Test
    void createDuplicateGridTest() {



        NewHouseGridCTRL ctrl130 = new NewHouseGridCTRL();
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridListSize());
        //create new grid
        assertTrue(ctrl130.createNewHouseGrid("main"));
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridListSize());
        //create new grids
        assertFalse(ctrl130.createNewHouseGrid("main"));
        assertEquals(1, ctrl130.getHouseGridListSize());
    }

    @Test
    void presentHouseGridsList() {

        NewHouseGridCTRL ctrl130 = new NewHouseGridCTRL();
        //confirm that initial list size is of zero
        assertEquals(0, ctrl130.getHouseGridListSize());
        //create new grid
        ctrl130.createNewHouseGrid("main");
        //assert a list of grids
        assertEquals(1, ctrl130.getHouseGridListSize());
        //create new grids
        ctrl130.createNewHouseGrid("backup");
        assertEquals(2, ctrl130.getHouseGridListSize());
        //assert a list of grids in String format
        assertEquals("1 - main\n2 - backup\n", ctrl130.showGridsListInString());
    }
}
