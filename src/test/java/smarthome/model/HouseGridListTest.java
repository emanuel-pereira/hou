
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

        OccupationArea oc = new OccupationArea(10,10);
        Location loc = new Location(10,10,10);
        GeographicalArea g1 = new GeographicalArea("LX","Lisboa", "cidade",oc,loc);

        House house = new House("Casa", a1, g1);
        HouseGridList hglist = house.getHGListInHouse ();
        HouseGrid h1 = hglist.newHouseGrid( "main grid");

        assertTrue(hglist.addHouseGrid(h1));

        assertEquals(1, hglist.getHouseGridList().size());
        //Ensure the same house grid cannot be added twice
        hglist.addHouseGrid(h1);
        assertEquals(1, hglist.getHouseGridList().size());
        assertFalse(hglist.addHouseGrid(h1));
        assertEquals(1, hglist.getHouseGridList().size());
    }

    @Test
    @DisplayName("Get a House Grid from a House Grid List through an int/index")
    void getHGWithInt() {
        HouseGridList hgList = new HouseGridList();
        HouseGrid hg1 = new HouseGrid("grid01");
        HouseGrid hg2 = new HouseGrid("grid02");
        hgList.addHouseGrid(hg1);
        hgList.addHouseGrid(hg2);

        HouseGrid expectedResult = hg2;
        HouseGrid result = hgList.get(1);
        assertEquals(expectedResult,result);
    }

}
