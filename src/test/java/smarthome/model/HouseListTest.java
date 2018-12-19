package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseListTest {


    @Test
    @DisplayName("Tests if a new house is created in a House List")
    void createNewHouseObject() {
        //Arrange
        HouseList houseList = new HouseList();

        //Act
        Location l1 = new Location(54, 40.1, 200);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA t1 = new TypeGA("street");
        GeographicalArea g1 = new GeographicalArea("Porto", t1);


        House house1 = houseList.newHouse(a1, g1);

        Address result = house1.getAddress();
        Address expectedResult = a1;


        GeographicalArea result2 = house1.getGA();
        GeographicalArea expectedResult2 = g1;


        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
    }

    @Test
    @DisplayName("Tests if a new house is added to the House list")
    void addHouseToList() {

        //Arrange
        HouseList houseList = new HouseList ();

        Location l1 = new Location(54, 40.1, 200);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA t1 = new TypeGA("street");
        GeographicalArea g1 = new GeographicalArea("Porto", t1);

        House h1 = houseList.newHouse(a1, g1);

        //Act
        assertTrue(houseList.addHouse(h1));

        List<House> expectedResult = Arrays.asList (h1);
        List<House> result = houseList.getHouseList();

        //Assert
        assertEquals (expectedResult, result);
    }

    @DisplayName("Tests if a house is not added to the list if it is repeated")
    @Test
    public void notAddRepeatedHouse() {
        //Arrange
        HouseList houseList = new HouseList ();

        Location l1 = new Location(54, 40.1, 200);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA t1 = new TypeGA("street");
        GeographicalArea g1 = new GeographicalArea("Porto", t1);

        House h1 = houseList.newHouse(a1, g1);


        House h2 = houseList.newHouse(a1, g1);

        //Act

        assertEquals (0, houseList.getHouseList().size ());
        assertTrue(houseList.addHouse(h1));
        assertEquals (1, houseList.getHouseList().size ());
        assertFalse(houseList.addHouse(h2));
        assertEquals (1, houseList.getHouseList().size ());

        List<House> expectedResult = Arrays.asList (h1);
        List<House> result = houseList.getHouseList();

        //Assert
        assertEquals (expectedResult, result);
    }


}