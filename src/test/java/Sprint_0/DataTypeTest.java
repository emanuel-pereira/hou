package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTypeTest {


    @Test
    public void getMeteorologicalTypeDesignationIfTemperature() {

        // arrange
        DataType meteoType = new DataType ("Temperature");
        String expectedResult = "Temperature";
        String result;

        // act
        result = meteoType.getDataTypeDesignation ();

        // assert
        assertEquals (expectedResult, result);
    }

    @Test
    public void getMeteorologicalTypeDesignationIfEmpty() {

        // arrange
        DataType meteoType = new DataType (" ");
        String result;

        // act
        result = meteoType.getDataTypeDesignation ();

        // assert
        assertEquals (null, result);
    }

    @Test
    public void getMeteorologicalTypeDesignationIfWrong() {

        // arrange
        DataType meteoType = new DataType ("Temperature");
        String expectedResult = "Wind";
        String result;

        // act
        result = meteoType.getDataTypeDesignation ();

        // assert
        assertNotEquals (expectedResult, result);
    }




}