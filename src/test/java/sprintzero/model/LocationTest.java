package sprintzero.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sprintzero.model.Location;


class LocationTest {

    @Test
    public void checkIfCoordinateValidTestTrue() {
        Location l = new Location (12,-4,100);
        //Arrange
        boolean result;
        boolean expectedresult = true;
        //Act
        result = l.checkIfInputValid();
        //Assert
        assertEquals (expectedresult, result);
    }

    @Test
    public void checkIfCoordinateValidTestFalseLatitude() {
        Location l = new Location (200, -4,100);
        //Arrange
        boolean result;
        boolean expectedresult = false;
        //Act
        result = l.checkIfInputValid();
        //Assert
        assertEquals (expectedresult, result);
    }

    @Test
    public void checkIfCoordinateValidTestFalseLongitude() {
        Location l = new Location (12,-250,100);
        //Arrange
        boolean result;
        boolean expectedresult = false;
        //Act
        result = l.checkIfInputValid();
        //Assert
        assertEquals (expectedresult, result);
    }

    @Test
    public void checkIfCoordinateValidTestFalseAltitude() {
        Location l = new Location (12, -4,10000);
        //Arrange
        boolean result;
        boolean expectedresult = false;
        //Act
        result = l.checkIfInputValid();
        //Assert
        assertEquals (expectedresult, result);
    }


    @Test
    public void getLocationTest () {
        Location l = new Location (41.15,-8.61024,83);
        //Arrange
        double[] result;
        double[] expectedresult = {41.15,-8.61024,83};
        //Act
        result = l.getLocation();
        //Assert
        assertArrayEquals( expectedresult, result);
    }

    @Test
    public void calcLinearDistanceBetweenL1AndL2Test () {
        Location l1 = new Location (41.15,-8.6,83);
        Location l2 = new Location (38.7, -9.1,4);
        //Arrange
        double result;
        double expectedresult = 79.0395;
        //Act
        result = Location.calcLinearDistanceBetweenTwoPoints(l1,l2);
        //Assert
        assertEquals(expectedresult,result,0.0001);
    }

    @Test
    public void calcLinearDistanceBetweenL1AndL2TestNotZero () {
        Location l1 = new Location (41.15,-8.6,83);
        Location l2 = new Location (38.7, -9.1,4);
        //Arrange
        double result;
        double expectedresult = 0;
        //Act
        result = Location.calcLinearDistanceBetweenTwoPoints(l1,l2);
        //Assert
        assertNotEquals(expectedresult,result);
    }

}