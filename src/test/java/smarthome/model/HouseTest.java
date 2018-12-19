package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    @Test
    @DisplayName("define and check house address, zip code, location and geographical area")
    void checkHouseLocation() {
        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house1 = new House("Rua Júlio Dinis, 345", "3380-45", l1, ga1);

        //Act
        String result = house1.getAddress();
        String expectedResult = "Rua Júlio Dinis, 345";

        String result2 = house1.getZipCode();
        String expectedResult2 = "3380-45";

        Location result3 = house1.getGPSLocation();
        Location expectedResult3 = l1;

        GeographicalArea result4 = house1.getGA();
        GeographicalArea expectedResult4 = ga1;


        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);

    }


}