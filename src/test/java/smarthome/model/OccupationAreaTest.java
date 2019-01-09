package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccupationAreaTest {

    @Test
    @DisplayName("Constructor with both valid parameters")
    public void checkConstructorValidParameters() {
        OccupationArea C = new OccupationArea(2, 2);
        double expectedResult = 4;
        double result = C.getOccupationArea();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Constructor with one parameter zero")
    public void checkConstructorParameterZero() {
        OccupationArea C = new OccupationArea(0, 4);
        double result = C.getOccupationArea();
        double expectedResult = Double.NaN;
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    @DisplayName("Constructor with second parameter zero")
    public void checkConstructorParameterZero2() {
        OccupationArea C = new OccupationArea(4, 0);
        double result = C.getOccupationArea();
        double expectedResult = Double.NaN;
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    @DisplayName("Constructor with both parameters zero")
    public void checkConstructorBothParametersZero() {
        OccupationArea C = new OccupationArea(0, 0);
        double result = C.getOccupationArea();
        double expectedResult = Double.NaN;
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    @DisplayName("Constructor with both negative parameters")
    public void checkConstructorBothNegativeParameters() {
        OccupationArea C = new OccupationArea(-3, -3);
        double result = C.getOccupationArea();
        double expectedResult = Double.NaN;
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Constructor with one negative parameter")
    public void checkConstructorOneNegativeParameter() {
        OccupationArea C = new OccupationArea(-2, 2);
        double expectedResult = Double.NaN;
        double result = C.getOccupationArea();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Constructor with one negative parameter")
    public void checkConstructorSecondNegativeParameter() {
        OccupationArea C = new OccupationArea(2, -2);
        double expectedResult = Double.NaN;
        double result = C.getOccupationArea();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure getLength in occupation area o returns 4")
    void getmLength() {
        OccupationArea o = new OccupationArea(4, 3);
        double expectedResult= 4;
        double result= o.getmLength();
        assertEquals(expectedResult, result);
    }

    @Test
    void getmWidth() {
        OccupationArea o = new OccupationArea(4, 3);
        double expectedResult= 3;
        double result= o.getmWidth();
        assertEquals(expectedResult, result);
    }
}

