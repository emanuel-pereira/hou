package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeTest {


    @Test
    @DisplayName("Tests designation not equal to input string")
    public void sensorTypeDesignationEqualsString() {

        //Arrange
        String type = "humidity";
        SensorType sensorType = new SensorType("temperature");

        //Act
        boolean result;
        result = sensorType.equals(type);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if two sensor type designations of different instances are the same")
    public void differentSensorTypeInstancesHaveEqualDesignation() {

        //Arrange
        SensorType type1 = new SensorType("Wind");
        SensorType type2 = new SensorType("Wind");

        //Act
        boolean result;
        result = type1.equals(type2);

        assertEquals(type1.hashCode(), type2.hashCode());
        assertTrue(result);
    }


    @Test
    @DisplayName("Tests if two sensor type designations of different instances are different")
    public void differentSensorTypeInstancesHaveNotEqualDesignation() {

        //Arrange
        SensorType type1 = new SensorType("Rainfall");
        SensorType type2 = new SensorType("Visibility");

        //Act
        boolean result;
        result = type1.equals(type2);

        //Assert
        assertNotEquals(type1.hashCode(), type2.hashCode());
        assertFalse(result);
    }


    @Test
    void getType() {
        SensorType type = new SensorType();
        assertNull(type.getType());
    }

    @Test
    void seeIfSensorTypeGetIdReturnsZeroToNonPersistedType() {
        SensorType type1 = new SensorType("Rainfall");
        assertNull(type1.getId());
    }
}