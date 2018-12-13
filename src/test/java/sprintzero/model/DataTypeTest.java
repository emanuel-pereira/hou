package sprintzero.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTypeTest {


    @Test
    @DisplayName("Tests designation not equal to input string")
    public void dataTypeDesignationEqualsString() {

        //Arrange
        String type = "humidity";
        DataType dataType = new DataType("temperature");

        //Act
        boolean result;
        result = dataType.equals(type);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if two data type designations of different instances are the same")
    public void differentDataTypeInstancesHaveEqualDesignation() {

        //Arrange
        DataType type1 = new DataType("Wind");
        DataType type2 = new DataType("Wind");

        //Act
        boolean result;
        result = type1.equals(type2);

        assertEquals(type1.hashCode(), type2.hashCode());
        assertTrue(result);
    }


    @Test
    @DisplayName("Tests if two data type designations of different instances are different")
    public void differentDataTypeInstancesHaveNotEqualDesignation() {

        //Arrange
        DataType type1 = new DataType("Rainfall");
        DataType type2 = new DataType("Visibility");

        //Act
        boolean result;
        result = type1.equals(type2);

        //Assert
        assertNotEquals(type1.hashCode(), type2.hashCode());
        assertFalse(result);
    }




}