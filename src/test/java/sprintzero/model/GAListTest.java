package sprintzero.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GAListTest {

    @Test
    void newGA() {
    }

    @Test
    void addGA() {
    }

    @Test
    @DisplayName("Test if checkIfLocationIsInGAList method returns ga2")
    void checkIfLocationIsInGAList() {
        GAList gaList= new GAList();
       GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        Location loc = new Location(2.7,5.2,0);
        GeographicalArea expectedResult=ga2;
        GeographicalArea result=gaList.checkIfLocationIsInGAList(loc.getLatitude(),loc.getLongitude());
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that Location loc is not within any geographical area in gaList")
    void checkIfLocationIsNotInGAList() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        Location loc = new Location(22.7,5.2,0);
        GeographicalArea expectedResult=null;
        GeographicalArea result=gaList.checkIfLocationIsInGAList(loc.getLatitude(),loc.getLongitude());
        assertEquals(expectedResult,result);
    }
}