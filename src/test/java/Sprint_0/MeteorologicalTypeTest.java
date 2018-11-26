package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeteorologicalTypeTest {


    @Test
    public void getMeteorologicalTypeDesignationIfTemperature() {

        // arrange
        MeteorologicalType meteoType = new MeteorologicalType ("Temperature");
        String expectedResult = "Temperature";
        String result;

        // act
        result = meteoType.getMeteorologicalTypeDesignation ();

        // assert
        assertEquals (expectedResult, result);
    }

    @Test
    public void getMeteorologicalTypeDesignationIfEmpty() {

        // arrange
        MeteorologicalType meteoType = new MeteorologicalType (" ");
        String result;

        // act
        result = meteoType.getMeteorologicalTypeDesignation ();

        // assert
        assertEquals (null, result);
    }

    @Test
    public void getMeteorologicalTypeDesignationIfWrong() {

        // arrange
        MeteorologicalType meteoType = new MeteorologicalType ("Temperature");
        String expectedResult = "Wind";
        String result;

        // act
        result = meteoType.getMeteorologicalTypeDesignation ();

        // assert
        assertNotEquals (expectedResult, result);
    }




}