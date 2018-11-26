package Sprint_0;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeOfGeographicalAreaTest {

    TypeOfGeographicalArea tga = new TypeOfGeographicalArea();


    @Test
    public void return_getAreaName() {

        TypeOfGeographicalArea tga = new TypeOfGeographicalArea();


         String expectedResult = null;
         String actualResult;
         actualResult = tga.getAreaName();

         assertEquals(expectedResult,actualResult);

    }

    @Test
    public void define_setAreaName() {


        TypeOfGeographicalArea tga = new TypeOfGeographicalArea();


        String expected_result = "Porto";
        String actualResult;
        actualResult = tga.getAreaName();

        assertEquals(expected_result, actualResult);

    }


}