package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GAListTest {

    @Test
    void newGA() {

        GAList ga = new GAList();
        GeographicalArea area1 = ga.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10);
        String expectedResult = "Porto";
        String result = area1.getGeographicalAreaDesignation();
        assertEquals(expectedResult, result);
    }

    @Test
    void addGA() {
        GAList ga = new GAList();
        GeographicalArea area1  = ga.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        List<GeographicalArea> expectedResult = Arrays.asList(area1);
        List<GeographicalArea> result = ga.getGAList();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Test addition of multiple equal areas")
    @Test
    void addGA2() {
        GAList ga = new GAList();

        GeographicalArea area1  = ga.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        assertEquals(Arrays.asList(area1), ga.getGAList());

        GeographicalArea area2  = ga.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area2);
        assertEquals(Arrays.asList(area1), ga.getGAList());
    }

    @Test
    void getGAList() {
        GAList ga = new GAList();
        GeographicalArea area1 = ga.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10);
        GeographicalArea area2 = ga.newGA("Pt","Braga", "district", 20, 20, 1, 3, -10);

        ga.addGA(area1);
        ga.addGA(area2);

        List<GeographicalArea> expectedResult = Arrays.asList(area1, area2);
        List<GeographicalArea> result = ga.getGAList();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure GAList doesn't allow to add the same GA twice.")
    void addGAReturnsFalse() {
        GAList ga = new GAList();
        GeographicalArea area1  = ga.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        boolean expectedResult = false;
        boolean result = ga.addGA(area1);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check if method returns a List of GA from the type chosen by the user, when there is only 1 result")
    void GAFromTypeTestOneElement() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Pt","Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Pt","Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt","Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = new ArrayList<>(Arrays.asList(ga3)); //Usar Arrays.asList dentro de um a nova array list caso dê erro null point exception
        List<GeographicalArea> result = gaList.gAFromThisType("street");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns a List of GA from the type chosen by the user, when there is 2 or more results")
    void GAFromTypeTestTwoOrMoreElements() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Pt","Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Pt","Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt","Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = new ArrayList<>(Arrays.asList(ga1,ga2));
        List<GeographicalArea> result = gaList.gAFromThisType("city");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns an empty List of GA, when there is no matches")
    void GAFromTypeTestEmptyNoMatch() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Pt","Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Pt","Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt","Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = Arrays.asList();
        List<GeographicalArea> result = gaList.gAFromThisType("Country");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns an empty List of GA, when there is no input/empty")
    void GAFromTypeTestEmptyNoInput() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Pt","Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Pt","Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt","Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = Arrays.asList();
        List<GeographicalArea> result = gaList.gAFromThisType("");
        assertEquals(expectedresult, result);
    }

    @DisplayName("Test if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        GAList gaList = new GAList();
        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", 45, 25, 32, 42, 41);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        String expected = "1 - Porto\n2 - Lisboa\n";
        String result = gaList.showGAListInString();
        assertEquals(expected, result);
    }

}
