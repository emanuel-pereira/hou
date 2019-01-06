package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US101ConfigureHouseLocationCTRLTest {

    @DisplayName("Tests if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        House h1 = new House();
        GAList gl1 = new GAList();
        US101ConfigureHouseLocationCTRL ctrl101 = new US101ConfigureHouseLocationCTRL(gl1, h1);

        GeographicalArea ga1 = new GeographicalArea("Pt","Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Ls","Lisboa", "city", 45, 25, 32, 42, 41);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String expected = "1 - Porto\n2 - Lisboa\n";
        String result = ctrl101.showGAListInString();
        assertEquals(expected, result);
    }

    @DisplayName("Tests if Geographical Area List is returned")
    @Test
    void getGAList() {
        House h1 = new House();
        GAList gl1 = new GAList();
        US101ConfigureHouseLocationCTRL ctrl101 = new US101ConfigureHouseLocationCTRL(gl1, h1);

        GeographicalArea ga1 = new GeographicalArea("Pt","Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Ls","Lisboa", "city", 45, 25, 32, 42, 41);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        List<GeographicalArea> expectedResult = gl1.getGAList();
        List<GeographicalArea> result = ctrl101.getGAList();
        assertEquals(expectedResult, result);
    }



    @DisplayName("Tests if the house location is configured correctly")
    @Test
    void configureHouseLocation() {
        GAList gl1 = new GAList();
        House h1 = new House();
        US101ConfigureHouseLocationCTRL ctrl101 = new US101ConfigureHouseLocationCTRL(gl1, h1);

        GeographicalArea ga1 = new GeographicalArea("Pt","Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Ls","Lisboa", "city", 45, 25, 32, 42, 41);

        gl1.addGA(ga1);
        gl1.addGA(ga2);


        boolean result = ctrl101.configureHouseLocation(1,"Rua Júlio Dinis", "345", "3380-45", 41, 12.3, 110);
        GeographicalArea result2 = h1.getHouseGA();
        GeographicalArea expectedResult = ga1;

        assertTrue(result);
        assertEquals (expectedResult,result2);


    }
}
