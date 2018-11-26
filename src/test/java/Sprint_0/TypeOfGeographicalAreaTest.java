package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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





}