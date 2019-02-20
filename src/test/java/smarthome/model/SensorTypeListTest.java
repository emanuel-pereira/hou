package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeListTest {

    @Test
    @DisplayName("Tests if a new type of sensor is created")
    void createNewSensorTypeObject() {
        //Arrange
        SensorTypeList type = new SensorTypeList();

        //Act
        SensorType temperature = type.newSensorType("Temperature");

        //Assert
        assertEquals("temperature", temperature.getType());
    }



    @Test
    @DisplayName("Tests if a new sensor type is added to the Data Type list")
    void addSensorTypeToList() {

        //Arrange
        SensorTypeList type = new SensorTypeList();
        SensorType wind = type.newSensorType("wind");

        //Act
        assertTrue(type.addSensorType(wind));
        List<SensorType> expectedResult = Arrays.asList (wind);
        List<SensorType> result = type.getSensorTypeList();

        //Assert
        assertEquals (expectedResult, result);
    }

    @DisplayName("Tests if a sensor type is not added to the list if it is repeated")
    @Test
    public void notAddRepeatedSensorType() {
        //Arrange
        SensorTypeList type = new SensorTypeList();
        SensorType visibility1= type.newSensorType("visibility");
        SensorType visibility2= type.newSensorType("visibility");

        //Act
        assertEquals (0, type.getSensorTypeList().size ());
        assertTrue(type.addSensorType(visibility1));
        assertEquals (1, type.getSensorTypeList().size ());
        assertFalse(type.addSensorType(visibility2));
        assertEquals (1, type.getSensorTypeList().size ());

        List<SensorType> expectedResult = Arrays.asList (visibility1);
        List<SensorType> result = type.getSensorTypeList();

        //Assert
        assertEquals (expectedResult, result);
    }

    @DisplayName("Tests if a sensor type is added to the list if it is not repeated")
    @Test
    public void AddDifferentSensorTypes() {

        //Arrange
        SensorTypeList type = new SensorTypeList();
        SensorType visibility1= type.newSensorType("visibility");
        SensorType wind= type.newSensorType("wind");

        //Act
        assertEquals (0, type.getSensorTypeList().size ());
        assertTrue(type.addSensorType(visibility1));
        assertEquals (1, type.getSensorTypeList().size ());
        assertTrue(type.addSensorType(wind));
        assertEquals (2, type.getSensorTypeList().size ());

        List<SensorType> expectedResult = Arrays.asList (visibility1, wind);
        List<SensorType> result = type.getSensorTypeList();

        //Assert
        assertEquals (expectedResult, result);
    }

    @DisplayName("Tests if a sensor type is not added to the list if it is null or empty")
    @Test
    public void AddNullAndEmptySensorTypes() {

        //Arrange
        SensorTypeList type = new SensorTypeList();
        SensorType visibility1= type.newSensorType(" ");
        SensorType wind= type.newSensorType(null);

        //Act
        assertEquals (0, type.getSensorTypeList().size ());
        assertFalse(type.addSensorType(visibility1));

        assertEquals (0, type.getSensorTypeList().size ());
        assertFalse(type.addSensorType(wind));

        assertEquals (0, type.getSensorTypeList().size ());

        List<SensorType> expectedResult = Arrays.asList ();
        List<SensorType> result = type.getSensorTypeList();

        //Assert
        assertEquals (expectedResult, result);
    }

    /**
     * Check if required SensorType exists in the SensorTypeList and return true
     */
    @Test
    public void checkIfSensorTypeExists() {

        //Arrange
        SensorTypeList type = new SensorTypeList();
        SensorType temp = type.newSensorType("temperature");
        SensorType rain = type.newSensorType("rain");

        //Act
        assertEquals (0, type.getSensorTypeList().size ());
        assertTrue(type.addSensorType(temp));
        assertEquals (1, type.getSensorTypeList().size ());
        assertTrue(type.addSensorType(rain));
        assertEquals (2, type.getSensorTypeList().size ());

        boolean result = type.checkIfSensorTypeExists("temperature");

        //Assert
        assertTrue(result);
    }

    /**
     * Check if required SensorType exists in the SensorTypeList and return true
     */
    @Test
    void checkIfSensorTypeDontExist() {

        //Arrange
        SensorTypeList type = new SensorTypeList();
        SensorType wind = type.newSensorType("wind");
        SensorType rain = type.newSensorType("rain");

        //Act
        assertEquals (0, type.getSensorTypeList().size ());
        assertTrue(type.addSensorType(wind));
        assertEquals (1, type.getSensorTypeList().size ());
        assertTrue(type.addSensorType(rain));
        assertEquals (2, type.getSensorTypeList().size ());

        boolean result = type.checkIfSensorTypeExists("temperature");

        //Assert
        assertFalse (result);
    }

    @DisplayName("Test if SensorType List is showed as a string to the user")
    @Test
    void showSensorTypeListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);
        String expected = "1 - Temperature\n2 - Wind\n";
        String result = sensorTypeList.showSensorTypeListInString();
        assertEquals(expected, result);
    }

}