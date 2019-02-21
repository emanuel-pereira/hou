package smarthome.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaTest {

    @DisplayName("Test if getHouseLocation method returns the location coordinates of the Geographical Area Aveiro")
    @Test
    public void defineTypesOfGeograficalAreaCityLocation() {

        GeographicalArea ga = new GeographicalArea("AVR", "Aveiro", "City", 25, 35, 15, 40, 45);

        double resultLat = ga.getLocation().getLatitude();
        assertEquals(25, resultLat);

        double resultLon = ga.getLocation().getLongitude();
        assertEquals(35, resultLon);

        double resultAlt = ga.getLocation().getAltitude();
        assertEquals(15, resultAlt);

    }


    @DisplayName("Ensure that method getListOfSensors in Aveiro is empty")
    @Test
    public void getEmptyListOfSensors() {
        GeographicalArea ga = new GeographicalArea("AVR", "Aveiro", "City", 25, 35, 15, 40, 45);

        boolean result = ga.getSensorListInGA().getSensorList().isEmpty();

        assertTrue(result);
    }


    @DisplayName("Calculate distance between to geographical areas")
    @Test
    void calculateDistanceFromPortoToGaia() {
        GeographicalArea PortoGA = new GeographicalArea("POR", "Porto", "City", 12.3, 35.2, 120, 2, 5);
        GeographicalArea GaiaGA = new GeographicalArea("VNG", "Gaia", "City", 5.3, 33.2, 10, 2, 5);

        double expectedDistance = 110.24;
        double result = PortoGA.calculateDistanceTo(GaiaGA);

        assertEquals(expectedDistance, result, 0.5);
    }

    @DisplayName("Calculate distance between two geographical areas")
    @Test
    void calculateDistanceFromPortoToFunchal() {
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", 12.3, 35.2, 120, 2, 5);
        GeographicalArea gaia = new GeographicalArea("VNG", "Gaia", "City", 8, -125, 10, 2, 5);

        double expectedDistance = 194.37;
        double result = porto.calculateDistanceTo(gaia);
        assertNotEquals(porto.hashCode(), gaia.hashCode());//to test the hash code method
        assertEquals(expectedDistance, result, 0.5);
    }

    @DisplayName("Ensure equals method returns false when comparing different objects of same type")
    @Test
    public void comparisonOfTwoDifferentGAObjects() {
        GeographicalArea ga1 = new GeographicalArea("POR", "Porto", "City", 12.3, 35.2, 120, 2, 5);
        GeographicalArea ga2 = new GeographicalArea("VNG", "Gaia", "City", 8, -125, 10, 2, 5);
        assertNotEquals(ga1, ga2);
    }

    @DisplayName("Ensure equals method returns false when comparing different objects of different type")
    @Test
    public void comparisonOfTwoDifferentObjects() {
        GeographicalArea ga1 = new GeographicalArea("POR", "Porto", "City", 12.3, 35.2, 120, 2, 5);
        GeographicalArea ga2 = new GeographicalArea("VNG", "Gaia", "City", 8, -125, 10, 2, 5);
        assertNotEquals(ga1, ga2);
    }

    @DisplayName("Ensure equals method returns true when comparing object o with same object")
    @Test
    public void comparisonOfSameObject() {
        GeographicalArea ga1 = new GeographicalArea("POR", "Porto", "City", 12.3, 35.2, 120, 2, 5);
        assertEquals(ga1, ga1);
    }

    @Test
    public void ifGAEqualsGASameContent() {
        boolean result;
        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 4, 5, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Porto", "city", 2, 4, 5, 5, 6);

        result = ga1.equals(ga2);

        assertEquals(ga1.hashCode(), ga2.hashCode());
        assertTrue(result);
    }

    @Test
    @DisplayName("Define the parent Geographical Area of another Geographical Area")
    public void setParentGA() {

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 4, 5, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", 3, 4, 5, 6, 7);

        ga1.setParentGA(ga2);

        GeographicalArea result = ga1.getGeographicalParentGA();
        assertEquals(ga2, result);

    }

    @Test
    @DisplayName("Return the parent of a Geographical Area of another Geographical Area")
    public void getParentGA() {

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 4, 5, 5, 6);

        GeographicalArea expectedResult = null;
        GeographicalArea result = ga1.getGeographicalParentGA();
        assertEquals(expectedResult, result);


    }


    @Test
    public void ifStringEqualsGA() {
        String text = "Text";
        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 2, 4, 5, 5, 6);
        boolean result;

        result = ga1.equals(text);

        assertFalse(result);
    }


    @Test
    @DisplayName("check if occupation area method (length * width) is correct.")
    public void getOccupation() {


        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", 3, 4, 3, 30, 20);

        double expectedResult = 600;
        double result = ga.getOccupation().getOccupationArea();

        assertEquals(expectedResult, result);
    }
}
