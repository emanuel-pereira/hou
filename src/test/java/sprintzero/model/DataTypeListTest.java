package sprintzero.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataTypeListTest {

    @Test
    @DisplayName("Tests if a new type of data is created")
    void createNewDataTypeObject() {
        //Arrange
        DataTypeList type = new DataTypeList();

        //Act
        DataType temperature = type.newDataType("Temperature");

        //Assert
        assertEquals("Temperature", temperature.getDataTypeDesignation());
    }



    @Test
    @DisplayName("Tests if a new data type is added to the Data Type list")
    void addDataTypeToList() {

        //Arrange
        DataTypeList type = new DataTypeList ();
        DataType wind = type.newDataType("wind");

        //Act
        assertTrue(type.addDataType (wind));
        List<DataType> expectedResult = Arrays.asList (wind);
        List<DataType> result = type.getDataTypeList();

        //Assert
        assertEquals (expectedResult, result);
    }

    @DisplayName("Tests if a data type is not added to the list if it is repeated")
    @Test
    public void notAddRepeatedDataType() {
        //Arrange
        DataTypeList type = new DataTypeList ();
        DataType visibility1= type.newDataType ("visibility");
        DataType visibility2= type.newDataType ("visibility");

        //Act
        assertEquals (0, type.getDataTypeList().size ());
        assertTrue(type.addDataType(visibility1));
        assertEquals (1, type.getDataTypeList().size ());
        assertFalse(type.addDataType(visibility2));
        assertEquals (1, type.getDataTypeList().size ());

        List<DataType> expectedResult = Arrays.asList (visibility1);
        List<DataType> result = type.getDataTypeList();

        //Assert
        assertEquals (expectedResult, result);
    }

    @DisplayName("Tests if a data type is added to the list if it is not repeated")
    @Test
    public void AddDifferentDataTypes() {

        //Arrange
        DataTypeList type = new DataTypeList ();
        DataType visibility1= type.newDataType ("visibility");
        DataType wind= type.newDataType ("wind speed");

        //Act
        assertEquals (0, type.getDataTypeList().size ());
        assertTrue(type.addDataType(visibility1));
        assertEquals (1, type.getDataTypeList().size ());
        assertTrue(type.addDataType(wind));
        assertEquals (2, type.getDataTypeList().size ());

        List<DataType> expectedResult = Arrays.asList (visibility1, wind);
        List<DataType> result = type.getDataTypeList();

        //Assert
        assertEquals (expectedResult, result);
    }


}