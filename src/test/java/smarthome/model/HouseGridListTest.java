package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseGridListTest {

    @Test
    @DisplayName("Add a new house grid to the house grid list of a house and check that the " +
            "same house grid object cannot be added twice")
    void newHouseGrid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Lisboa", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("LX","Lisboa", "cidade",10,10,10,10,10);

        House house = new House("Casa", a1, g1);
        HouseGridList hglist = new HouseGridList();
        HouseGrid h1 = hglist.newHouseGrid( "main grid", 5);

        assertTrue(hglist.addHouseGrid(h1));

        assertEquals(1, hglist.getHouseGridList().size());
        //Ensure the same house grid cannot be added twice
        hglist.addHouseGrid(h1);
        assertEquals(1, hglist.getHouseGridList().size());
        //Ensure a house grid with 0 inputContractedPower cannot be added to the list
        HouseGrid h2 = hglist.newHouseGrid("main grid", 0);
        assertFalse(hglist.addHouseGrid(h2));
        assertEquals(1, hglist.getHouseGridList().size());
    }

    @Test
    @DisplayName("Get a House Grid from a House Grid List through an int/index")
    void getHGWithInt() {
        HouseGridList hgList = new HouseGridList();
        HouseGrid hg1 = new HouseGrid("grid01",10);
        HouseGrid hg2 = new HouseGrid("grid02", 20);
        hgList.addHouseGrid(hg1);
        hgList.addHouseGrid(hg2);

        HouseGrid expectedResult = hg2;
        HouseGrid result = hgList.get(1);
        assertEquals(expectedResult,result);
    }

}