package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccupationAreaTest {

    OccupationArea O = new OccupationArea();

    @Test
    public void checkConstrutorwithinputs(){
        OccupationArea C = new OccupationArea(2,2);
        double expectedResult = 4;
        C.calculOccupationArea();
        double result = C.getOccupationArea();
        assertEquals(expectedResult,result);

    }

    @Test
    public void setOccupationTest() {
        O.setOccupationArea(3, 4);
        O.calculOccupationArea();
        double result = O.getOccupationArea();
        double expectedResult = 12;
        assertEquals(expectedResult, result);
    }

    @Test
    public void getOcuppationAreaTest() {
        O.setOccupationArea(3, 3);
        O.calculOccupationArea();
        double result = O.getOccupationArea();
        double expectedResult = 9;
        assertEquals(expectedResult, result);
    }

    @Test
    public void lengthandwigthPositiveValues() {
        O.setOccupationArea(-2, 2);
        boolean expectedResult = O.positiveLenghtAndWidth();
        boolean result = false;
        assertEquals(expectedResult, result);
    }

}

