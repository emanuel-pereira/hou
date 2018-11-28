package Sprint_0;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class LocationTest {

    @Test
    public void checkIfCoordinateValidTestTrue() {
        Location l = new Location (12,-4,100);
        //Arrange
        boolean result;
        boolean expectedresult = true;
        //Act
        result = l.CheckIfInputValid();
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
        result = l.CheckIfInputValid();
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
        result = l.CheckIfInputValid();
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
        result = l.CheckIfInputValid();
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

}