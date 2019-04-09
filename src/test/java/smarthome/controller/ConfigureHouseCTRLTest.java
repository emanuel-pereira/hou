package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static smarthome.model.House.getHouseGA;

class ConfigureHouseCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @DisplayName("Tests if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String expected = "1 - Porto\n2 - Lisboa\n";
        String result = ctrl101.showGAList();
        assertEquals(expected, result);
    }

    @DisplayName("Tests if Geographical Area List is returned")
    @Test
    void getGAList() {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

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
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);


        boolean result = ctrl101.configureHouseLocation(1, "Rua Júlio Dinis", "345", "3380-45", 41, 12.3, 110);
        GeographicalArea result2 = getHouseGA();


        assertTrue(result);
        assertEquals(ga1, result2);


    }

    @DisplayName("Check if throws exception when the latitude is wrong")
    @Test
    void throwsIllegalArgumentExceptionForLatitude() {

        GAList gl1 = new GAList();

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);


        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        boolean thrown = false;

        try {

            ctrl101.configureHouseLocation(1, "Rua Júlio Dinis", "345", "3380-45", 400, 12.3, 110);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the longitude is wrong")
    @Test
    void throwsIllegalArgumentExceptionForLongitude() {

        GAList gl1 = new GAList();


        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);


        gl1.addGA(ga1);
        gl1.addGA(ga2);

        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        boolean thrown = false;

        try {

            ctrl101.configureHouseLocation(1, "Rua Júlio Dinis", "345", "3380-45", 80, 181, 110);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the altitude is wrong")
    @Test
    void throwsIllegalArgumentExceptionForAltitude() {

        GAList gl1 = new GAList();

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt","Porto", "city", oc1,loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls","Lisboa", "city", oc2,loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        boolean thrown = false;

        try {

            ctrl101.configureHouseLocation(1, "Rua Júlio Dinis", "345", "3380-45", 80, -170, -13000);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    void getGAListSize() {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);


        int expected=2;
        int result = ctrl101.getGAListSize();

        assertEquals(expected,result);
    }
}
