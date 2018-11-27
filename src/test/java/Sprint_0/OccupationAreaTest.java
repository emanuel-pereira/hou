package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccupationAreaTest {

    OccupationArea O = new OccupationArea();

    @Test
    public void checkConstrutorWithInputs(){
        OccupationArea C = new OccupationArea(2,2);
        double expectedResult = 4;
        double result = C.getOccupationArea();
        assertEquals(expectedResult,result);
    }
    @Test
    public void checkConstrutorWithoutInputs() {
        OccupationArea C = new OccupationArea();
        double result = C.getOccupationArea();
        double expectedResult = 0;
        assertEquals(expectedResult,result);
    }
    @Test
    public void checkgetOccupationTestEqual0() {
        OccupationArea C = new OccupationArea();
        double result = C.getOccupationArea();
        double expectedResult = 0;
        assertEquals(expectedResult,result);
    }
    @Test
    public void setOccupationTest() {
        O.setOccupationArea(3, 4);
        double result = O.getOccupationArea();
        double expectedResult = 12;
        assertEquals(expectedResult, result);
    }
    @Test
    public void getOcuppationAreaTest() {
        O.setOccupationArea(3, 3);
        double result = O.getOccupationArea();
        double expectedResult = 9;
        assertEquals(expectedResult, result);
    }
    @Test
    public void lengthandwigthNegativeValue() {
        O.setOccupationArea(-2, 2);
        boolean expectedResult = O.positiveLenghtAndWidth();
        boolean result = false;
        assertEquals(expectedResult, result);
    }
    @Test
    public void lengthandwigthPositiveValue() {
        O.setOccupationArea(2, 2);
        boolean expectedResult = O.positiveLenghtAndWidth();
        boolean result = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkifConvertMetersToKM() {
        O.convertMetersToKm(3,3);
        double expectedResult = 0.000009;
        double result = O.getOccupationArea();
        assertEquals(expectedResult,result);
    }

}

