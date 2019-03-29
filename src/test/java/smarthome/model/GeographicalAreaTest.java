package smarthome.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaTest {

    @DisplayName("Test if getHouseLocation method returns the location coordinates of the Geographical Area Aveiro")
    @Test
    public void defineTypesOfGeograficalAreaCityLocation() {


        Location loc = new Location(25, 35, 15);

        OccupationArea oc = new OccupationArea(40, 45);
        GeographicalArea ga = new GeographicalArea("AVR", "Aveiro", "City", oc, loc);
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
        OccupationArea oc = new OccupationArea(40, 45);
        Location loc = new Location(25, 35, 15);

        GeographicalArea ga = new GeographicalArea("AVR", "Aveiro", "City", oc, loc);

        boolean result = ga.getSensorListInGA().getSensorList().isEmpty();

        assertTrue(result);
    }


    @DisplayName("Calculate distance between to geographical areas")
    @Test
    void calculateDistanceFromPortoToGaia() {

        OccupationArea oc1 = new OccupationArea(40, 45);
        Location loc1 = new Location(25, 35, 15);

        OccupationArea oc2 = new OccupationArea(25.3, 22.4);
        Location loc2 = new Location(33.5, 39.7, 14);

        GeographicalArea PortoGA = new GeographicalArea("POR", "Porto", "City", oc1, loc1);
        GeographicalArea GaiaGA = new GeographicalArea("VNG", "Gaia", "City", oc2, loc2);

        double expectedDistance = 9.76;
        double result = PortoGA.calculateDistanceTo(GaiaGA);

        assertEquals(expectedDistance, result, 0.5);
    }

    @DisplayName("Calculate distance between two geographical areas")
    @Test
    void calculateDistanceFromPortoToFunchal() {

        OccupationArea oc1 = new OccupationArea(2, 5);
        Location loc1 = new Location(12.3, 35.2, 120);

        OccupationArea oc2 = new OccupationArea(2, 5);
        Location loc2 = new Location(8, -125, 10);

        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", oc1, loc1);
        GeographicalArea gaia = new GeographicalArea("VNG", "Gaia", "City", oc2, loc2);

        double expectedDistance = 194.37;
        double result = porto.calculateDistanceTo(gaia);
        assertNotEquals(porto.hashCode(), gaia.hashCode());//to test the hash code method
        assertEquals(expectedDistance, result, 0.5);
    }

    @DisplayName("Ensure equals method returns false when comparing different objects of same type")
    @Test
    public void comparisonOfTwoDifferentGAObjects() {

        OccupationArea oc1 = new OccupationArea(2, 5);
        Location loc1 = new Location(12.3, 35.2, 120);

        OccupationArea oc2 = new OccupationArea(2, 5);
        Location loc2 = new Location(8, -125, 10);

        GeographicalArea ga1 = new GeographicalArea("POR", "Porto", "City", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("VNG", "Gaia", "City", oc2, loc2);
        assertNotEquals(ga1, ga2);
    }

    @DisplayName("Ensure equals method returns false when comparing different objects of different type")
    @Test
    public void comparisonOfTwoDifferentObjects() {

        OccupationArea oc1 = new OccupationArea(2, 5);
        Location loc1 = new Location(12.3, 35.2, 120);

        OccupationArea oc2 = new OccupationArea(2, 5);
        Location loc2 = new Location(8, -125, 10);


        GeographicalArea ga1 = new GeographicalArea("POR", "Porto", "City", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("VNG", "Gaia", "City", oc2, loc2);
        assertNotEquals(ga1, ga2);
    }

    @DisplayName("Ensure equals method returns true when comparing object o with same object")
    @Test
    public void comparisonOfSameObject() {

        OccupationArea oc1 = new OccupationArea(2, 5);
        Location loc1 = new Location(12.3, 35.2, 120);


        GeographicalArea ga1 = new GeographicalArea("POR", "Porto", "City", oc1, loc1);
        assertEquals(ga1, ga1);
    }

    @Test
    public void ifGAEqualsGASameContent() {
        boolean result;

        OccupationArea oc1 = new OccupationArea(5, 6);
        Location loc1 = new Location(2, 4, 5);

        OccupationArea oc2 = new OccupationArea(5, 6);
        Location loc2 = new Location(2, 4, 5);


        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Porto", "city", oc2, loc2);

        result = ga1.equals(ga2);

        assertEquals(ga1.hashCode(), ga2.hashCode());
        assertTrue(result);
    }

    @Test
    @DisplayName("Define the parent Geographical Area of another Geographical Area")
    public void setParentGA() {


        OccupationArea oc1 = new OccupationArea(5, 6);
        Location loc1 = new Location(2, 4, 5);

        OccupationArea oc2 = new OccupationArea(6, 7);
        Location loc2 = new Location(3, 4, 5);


        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Portugal", "Country", oc2, loc2);

        ga1.setParentGA(ga2);

        GeographicalArea result = ga1.getGeographicalParentGA();
        assertEquals(ga2, result);

    }

    @Test
    @DisplayName("Return the parent of a Geographical Area of another Geographical Area")
    public void getParentGA() {

        OccupationArea oc1 = new OccupationArea(5, 6);
        Location loc1 = new Location(2, 4, 5);


        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);

        GeographicalArea expectedResult = null;
        GeographicalArea result = ga1.getGeographicalParentGA();
        assertEquals(expectedResult, result);


    }


    @Test
    public void ifStringEqualsGA() {
        String text = "Text";

        OccupationArea oc1 = new OccupationArea(5, 6);
        Location loc1 = new Location(2, 4, 5);


        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        boolean result;

        result = ga1.equals(text);

        assertFalse(result);
    }


    @Test
    @DisplayName("check if occupation area method (length * width) is correct.")
    public void getOccupation() {


        OccupationArea oc1 = new OccupationArea(30, 20);
        Location loc1 = new Location(3, 4, 3);


        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", oc1, loc1);

        double expectedResult = 600;
        double result = ga.getOccupation().getOccupationArea();

        assertEquals(expectedResult, result);
    }

    @Test
    public void checkImportedReadingsToSensorsFromCSVFileTest() throws IOException {
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        GregorianCalendar startDate = new GregorianCalendar(2018, 2, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorISEP = new Sensor("TT12346", "SensorISEP", startDate, location, temp, "C", new ReadingList());
        Sensor sensorPorto = new Sensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGA().addSensor(sensorISEP);
        ga.getSensorListInGA().addSensor(sensorPorto);

        ga.importReadingsToSensorsFromCSVFile("resources/ReadingsRegistry");
        List<Reading> rList = ga.getSensorListInGA().getSensorList().get(0).getReadingList().getReadingsList();
        double r = rList.get(3).returnValueOfReading();
        assertEquals(15.1, r);
    }

    @Test
    public void checkIfOutdatedReadingsTest() throws IOException {
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        GregorianCalendar startDate = new GregorianCalendar(2019, 2, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorPorto = new Sensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGA().addSensor(sensorPorto);

        ga.importReadingsToSensorsFromCSVFile("resources/ReadingsRegistry");
    }

    @Test
    void getType() {
        GeographicalArea ga = new GeographicalArea();
        assertNull(ga.getType());
    }

    @Test
    void checkGAConstructor() {
        GeographicalArea ga = new GeographicalArea("cityX9000", "WonderLand");
        TypeGA newType = new TypeGA("city");
        assertTrue(ga.setType(newType));
        assertEquals(newType, ga.getType());
    }

    @Test
    void checkGAConstructorWithNullParemeter() {
        GeographicalArea ga = new GeographicalArea("cityX9000", "WonderLand");
        TypeGA newType = null;
        assertFalse(ga.setType(newType));
    }

    @Test
    void setDesignation() {
        GeographicalArea ga = new GeographicalArea("cityX9000", "WonderLand");
        ga.setDesignation("WonderCity");
        assertEquals("WonderCity", ga.getDesignation());
    }

    @Test
    void setIdentification() {
        GeographicalArea ga = new GeographicalArea("cityX9000", "WonderLand");
        ga.setIdentification("X9000");
        assertEquals("X9000", ga.getIdentification());
    }
}
