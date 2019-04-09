
package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseGridListTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @Test
    @DisplayName("Add a new house grid to the house grid list of a house and check that the " +
            "same house grid object cannot be added twice")
    void newHouseGrid() {
        HouseGridList hglist = new HouseGridList();
        HouseGrid h1 = hglist.newHouseGrid("main grid");

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

        HouseGrid result = hgList.get(1);
        assertEquals(hg2, result);
    }


}