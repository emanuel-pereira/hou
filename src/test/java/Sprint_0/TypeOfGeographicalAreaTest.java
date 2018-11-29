package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeOfGeographicalAreaTest {


    @Test
    public void return_getTypeArea() {

        TypeOfGeographicalArea tga = new TypeOfGeographicalArea("street");


        String expectedResult = "street";
        String actualResult;

        actualResult = tga.getTypeArea();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void define_setTypeArea() {

        TypeOfGeographicalArea tga = new TypeOfGeographicalArea("street");


        String tgaName = "place";

        tga.setTypeArea(tgaName);

        String expectedResult = "place";
        String actualResult;

        actualResult = tga.getTypeArea();

            assertEquals(expectedResult, actualResult);
    }

/*
        @TESt

        public void agInseridaEmDireto()

            //arrange

            AG ag1 = new AG("AMP",.......);
            AG ag2 = new AG ("Porto",.......);
            AG ag3 = new AG ("Portugal",.....);

            //ACT

            ag2.setInseridaEM(ag1));

            ag2.setINseridaEM(ag1);


            //assert

            assertEquals(ag2INseridaEmDireto(ag3),true);

*/
}
