package smarthome.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class US7SetParentOfGACTRLTest {



    @Test
    @DisplayName("Ensure that GA2 Portugal has been set as GA Porto parent")
    public void setParentOfGA() {

        GAList gaList = new GAList();

        GeographicalArea ga1 = new GeographicalArea("Porto", "city", 2, 3, 4, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Portugal", "Country", 2, 3, 4, 5, 6);

        int indexGA1 = 1;
        int indexGA2 = 2;

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        US7SetParentOfGACTRL ctrl7 = new US7SetParentOfGACTRL(gaList);
        ctrl7.setParentofGA(indexGA1,indexGA2);

        String result = ga1.getGeographicalParentGA().getGeographicalAreaDesignation();
        String expectedResult = "Portugal";
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure that Espanha has not been set as GA Porto parent")
    public void setNotParentOfGA() {

        GAList gaList = new GAList();

        GeographicalArea ga1 = new GeographicalArea("Porto", "city", 2, 3, 4, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Portugal", "Country", 2, 3, 4, 5, 6);

        int indexGA1 = 1;
        int indexGA2 = 2;

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        US7SetParentOfGACTRL ctrl7 = new US7SetParentOfGACTRL(gaList);
        ctrl7.setParentofGA(indexGA1,indexGA2);

        String result = ga1.getGeographicalParentGA().getGeographicalAreaDesignation();
        String expectedResult = "Espanha";
        assertNotEquals(expectedResult, result);
    }



    @Test
    @DisplayName("Checks if, with an empty constructor, the get List returns an empty list")
    public void getGAList() {

        GAList gaList = new GAList();

        List<GeographicalArea> expectedResult = new ArrayList<>();
        List<GeographicalArea> result;
        result = gaList.getGAList();
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Check if console shows the parent Ga of Porto, in this case, is Portugal")
    public void showListInString() {

        GAList gaList = new GAList ();


        US7SetParentOfGACTRL ctrl7 = new US7SetParentOfGACTRL(gaList);

        GeographicalArea ga1 = new GeographicalArea ("Porto","city",3,4,5,6,7);
        GeographicalArea ga2 = new GeographicalArea("Portugal","Country",5,3,6,7,3);

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        String expectedResult = "1 - Porto\n2 - Portugal\n";
        String result = ctrl7.showListInString();

        assertEquals(expectedResult, result);
    }

}
