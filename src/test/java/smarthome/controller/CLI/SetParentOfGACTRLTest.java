package smarthome.controller.CLI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.controller.CLI.SetParentOfGACTRL;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SetParentOfGACTRLTest {


    @Test
    @DisplayName("Ensure that GA2 Portugal has been set as GA Porto parent")
    public void setParentOfGA() {

        GAList gaList = new GAList();

        OccupationArea oc = new OccupationArea(5, 6);
        Location loc = new Location(2, 3, 4);
        OccupationArea oc1 = new OccupationArea(11, 6);
        Location loc1 = new Location(4, 6, 8);

        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea ga1 = new GeographicalArea("opo", "Porto", "city", oc, loc);
        TypeGAList.addTypeGA(new TypeGA("country"));
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", oc1, loc1);

        int indexGA1 = 1;
        int indexGA2 = 2;

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);
        ctrl7.setParentofGA(indexGA1, indexGA2);

        String result = ga1.getGeographicalParentGA().getGAName();
        String expectedResult = "Portugal";
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure that two GAs with the same type are not set as parent")
    public void invalidSetParentOfGA() {

        GAList gaList = new GAList();

        OccupationArea oc = new OccupationArea(5, 6);
        Location loc = new Location(2, 3, 4);
        OccupationArea oc1 = new OccupationArea(11, 6);
        Location loc1 = new Location(4, 6, 8);
        OccupationArea oc2 = new OccupationArea(111, 117);
        Location loc2 = new Location(14, 16, 18);

        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea ga1 = new GeographicalArea("Opo", "Porto", "city", oc, loc);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", oc1, loc1);
        TypeGAList.addTypeGA(new TypeGA("country"));
        GeographicalArea ga3 = new GeographicalArea("Pt", "Portugal", "country", oc2, loc2);

        int indexGA1 = 1;
        int indexGA2 = 2;
        int indexGA3 = 3;

        gaList.addGA(ga1);
        gaList.addGA(ga2);
        gaList.addGA(ga3);

        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);
        ctrl7.setParentofGA(indexGA1, indexGA2);

        ctrl7.setParentofGA(indexGA1, indexGA3);

        String result = ga1.getGeographicalParentGA().getGAName();
        String expectedResult = "Portugal";
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure that Espanha has not been set as GA Porto parent")
    public void setNotParentOfGA() {

        GAList gaList = new GAList();

        OccupationArea oc = new OccupationArea(5, 6);
        Location loc = new Location(2, 3, 4);
        OccupationArea oc1 = new OccupationArea(11, 6);
        Location loc1 = new Location(4, 6, 8);

        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea ga1 = new GeographicalArea("opo", "Porto", "city", oc, loc);
        TypeGAList.addTypeGA(new TypeGA("country"));
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", oc1, loc1);

        int indexGA1 = 1;
        int indexGA2 = 2;

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);
        ctrl7.setParentofGA(indexGA1, indexGA2);

        String result = ga1.getGeographicalParentGA().getGAName();
        String expectedResult = "Espanha";
        assertNotEquals(expectedResult, result);
    }


    @Test
    @DisplayName("Checks if, with an empty constructor, the get List returns an empty list")
    public void getGAList() {

        GAList gaList = new GAList();
        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);

        List<GeographicalArea> expectedResult = new ArrayList<>();
        List<GeographicalArea> result;

        result = ctrl7.getGAList();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check if console shows the parent Ga of Porto, in this case, is Portugal")
    public void showListInString() {

        GAList gaList = new GAList();


        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);

        OccupationArea oc = new OccupationArea(6, 7);
        Location loc = new Location(3, 4, 5);
        OccupationArea oc1 = new OccupationArea(7, 3);
        Location loc1 = new Location(5, 3, 6);

        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea ga1 = new GeographicalArea("opo", "Porto", "city", oc, loc);
        TypeGAList.addTypeGA(new TypeGA("country"));
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", oc1, loc1);

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        String expectedResult = "1 - Porto\n2 - Portugal\n";
        String result = ctrl7.showListInString();

        assertEquals(expectedResult, result);
    }


    @Test
    public void getGAListSize() {

        GAList gaList = new GAList();

        OccupationArea oc = new OccupationArea(5, 6);
        Location loc = new Location(2, 3, 4);
        OccupationArea oc1 = new OccupationArea(7, 3);
        Location loc1 = new Location(5, 3, 6);

        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea ga1 = new GeographicalArea("opo", "Porto", "city", oc, loc);
        TypeGAList.addTypeGA(new TypeGA("country"));
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", oc1, loc1);

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);

        int result = ctrl7.getGAListSize();
        int expectedResult = 2;

        assertEquals(expectedResult, result);


    }

    @Test
    public void getGaName() {

        GAList gaList = new GAList();

        OccupationArea oc = new OccupationArea(5, 6);
        Location loc = new Location(2, 3, 4);
        OccupationArea oc1 = new OccupationArea(5, 6);
        Location loc1 = new Location(2, 3, 4);

        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea ga1 = new GeographicalArea("opo", "Porto", "city", oc, loc);
        TypeGAList.addTypeGA(new TypeGA("country"));
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", oc1, loc1);


        gaList.addGA(ga1);
        gaList.addGA(ga2);

        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);


        String result = ctrl7.getGaName(1);

        String expectedResult = "Porto";

        assertEquals(expectedResult, result);
    }
}
