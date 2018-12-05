package sprintzero;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccupationAreaTest {

    OccupationArea OA = new OccupationArea();

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
    public void checkGetOccupationTestWithoutInputs() {
        OccupationArea C = new OccupationArea();
        double result = C.getOccupationArea();
        double expectedResult = 0;
        assertEquals(expectedResult,result);
    }
    @Test
    public void checkSetOccupationTestCorrect() {
        OA.setOccupationArea(3, 4);
        double result = OA.getOccupationArea();
        double expectedResult = 12;
        assertEquals(expectedResult, result,0.1);
    }
    @Test
    public void getOcuppationAreaTest() {
        OA.setOccupationArea(3, 3);
        double result = OA.getOccupationArea();
        double expectedResult = 9;
        assertEquals(expectedResult, result);
    }
    @Test
    public void lengthandwigthNegativeValue() {
        OA.setOccupationArea(-2, 2);
        boolean expectedResult = OA.positiveLengthAndWidth(-2,2);
        boolean result = false;
        assertEquals(expectedResult, result);
    }
    @Test
    public void lengthandwigthPositiveValue() {
        OA.setOccupationArea(2, 2);
        boolean expectedResult = OA.positiveLengthAndWidth(2,2);
        boolean result = true;
        assertEquals(expectedResult, result);
    }
    @Test
    public void checkifConvertMetersToKM() {
        OA.convertMetersToKm(3,3);
        double expectedResult = 0.000009;
        double result = OA.getOccupationArea();
        assertEquals(expectedResult,result, 0.1);
    }

}

