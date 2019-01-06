package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class LocationTest {

    @Test
    @DisplayName("Check if all GPS coordinates are valid while creating an instance of a location.")
    public void checkIfGPSCoordinatesAreValid() {
        //Arrange
        Location l = new Location(12, -4, 100);

        //Act
        double expectedLat = 12;
        double resultLat = l.getLatitude();

        //Assert
        assertEquals(expectedLat, resultLat);

        double expectedLon = -4;
        double resultLon = l.getLongitude();
        assertEquals(expectedLon, resultLon);

        double expectedAlt = 100;
        double resultAlt = l.getAltitude();
        assertEquals(expectedAlt, resultAlt);
    }



   /* validate why expected=IllegalArgumentException.class is not accepted
   @Test(expected=IllegalArgumentException.class)
    @DisplayName("Check if all GPS coordinates are valid while creating an instance of a location.")
    public void checkIfGPSCoordinatesAreValid1() {
        //Arrange
        Location l = new Location(120, -4, 100);

        //Act
        double expectedLat = 120;
        double resultLat = l.getLatitude();
    }*/


    @Test
    @DisplayName("Check that all max limit GPS coordinates are valid while creating an instance of a location.")
    public void ensureAllMaxLimitGPSCoordinatesAreValid() {
        //Arrange
        Location l = new Location(90, 180, 8848);

        //Act
        double expectedLat = 90;
        double resultLat = l.getLatitude();

        //Assert
        assertEquals(expectedLat, resultLat);

        double expectedLon = 180;
        double resultLon = l.getLongitude();
        assertEquals(expectedLon, resultLon);

        double expectedAlt = 8848;
        double resultAlt = l.getAltitude();
        assertEquals(expectedAlt, resultAlt);
    }

    @Test
    @DisplayName("Check that all min limit GPS coordinates are valid while creating an instance of a location.")
    public void ensureAllMinLimitGPSCoordinatesAreValid() {
        //Arrange
        Location l = new Location(-90, -180, -12500);

        //Act
        double expectedLat = -90;
        double resultLat = l.getLatitude();

        //Assert
        assertEquals(expectedLat, resultLat);

        double expectedLon = -180;
        double resultLon = l.getLongitude();
        assertEquals(expectedLon, resultLon);

        double expectedAlt = -12500;
        double resultAlt = l.getAltitude();
        assertEquals(expectedAlt, resultAlt);
    }


    @Test
    public void calcLinearDistanceBetweenL1AndL2Test() {
        Location l1 = new Location(41.15, -8.6, 83);
        Location l2 = new Location(38.7, -9.1, 4);
        //Arrange
        double result;
        double expectedresult = 79.0395;
        //Act
        result = Location.calcLinearDistanceBetweenTwoPoints(l1, l2);
        //Assert
        assertEquals(expectedresult, result, 0.0001);
    }

    @Test
    public void calcLinearDistanceBetweenL1AndL2TestNotZero() {
        Location l1 = new Location(41.15, -8.6, 83);
        Location l2 = new Location(38.7, -9.1, 4);
        //Arrange
        double result;
        double expectedresult = 0;
        //Act
        result = Location.calcLinearDistanceBetweenTwoPoints(l1, l2);
        //Assert
        assertNotEquals(expectedresult, result);
    }
}