package smarthome.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SetParentOfGACTRLTest {


    @Test
    @DisplayName("Ensure that GA2 Portugal has been set as GA Porto parent")
    public void setParentOfGA() {

        GAList gaList = new GAList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 3, 4, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", 2, 3, 4, 5, 6);

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

        GeographicalArea ga1 = new GeographicalArea("Opo", "Porto", "city", 2, 3, 4, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", 4, 6, 8, 11, 7);
        GeographicalArea ga3 = new GeographicalArea("Pt", "Portugal", "country", 14, 16, 18, 111, 117);

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

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 3, 4, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", 2, 3, 4, 5, 6);

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

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 3, 4, 5, 6, 7);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", 5, 3, 6, 7, 3);

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        String expectedResult = "1 - Porto\n2 - Portugal\n";
        String result = ctrl7.showListInString();

        assertEquals(expectedResult, result);
    }


    @Test
    public void getGAListSize() {

        GAList gaList = new GAList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 3, 4, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", 5, 3, 6, 7, 3);

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

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 3, 4, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", 2, 3, 4, 5, 6);


        gaList.addGA(ga1);
        gaList.addGA(ga2);

        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);


        String result = ctrl7.getGaName(1);

        String expectedResult = "Porto";

        assertEquals(expectedResult, result);
    }
}