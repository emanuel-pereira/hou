package smarthome.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaTest {

    @DisplayName("set country name and type of GA for country and set of a specific position")
    @Test
    public void defineTypesOfGeograficalAreaCityLocation() {
        TypeGAList tga = new TypeGAList();
        TypeGA city = tga.newTypeGA("city");

        String geographicalAreaName = "Porto";
        Location loc = new Location(12.3, 35.2, 120);

        GeographicalArea ga = new GeographicalArea(geographicalAreaName, city, loc);

        Location expectedLocation = loc;
        Location result = ga.getLocation();

        assertEquals(expectedLocation, result);
    }


    @DisplayName("Test if getListOfSensors returns null for an empty sensor list")
    @Test
    public void getEmptyListOfSensors() {
        TypeGAList tga = new TypeGAList();
        TypeGA district = tga.newTypeGA("district");
        GeographicalArea a = new GeographicalArea("Beja", district);
        List<Sensor> expectedResult = new ArrayList<>();
        List<Sensor> result = a.getListOfSensors();
        assertEquals(expectedResult, result);
    }


    @DisplayName("Test if getListOfSensors returns mSensorList for a specific geographic area")
    @Test
    public void getListOfSensor() {
        Sensor sensor1 = new Sensor();
        Sensor sensor2 = new Sensor();
        TypeGAList tga = new TypeGAList();
        TypeGA district = tga.newTypeGA("district");
        GeographicalArea a = new GeographicalArea("Aveiro", district, new Location(25, -17, 15), Arrays.asList(sensor1, sensor2));
        List<Sensor> expectedResult = Arrays.asList(sensor1, sensor2);
        List<Sensor> result = a.getListOfSensors();
        assertEquals(expectedResult, result);
    }


    @DisplayName("Test if method returns last reading values and respective date of each sensor in a specific geographic area")
    @Test
    public void checkIfGetLastValuesOfSensors() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        GregorianCalendar rTime3 = new GregorianCalendar(2018, 11, 29, 18, 0);

        List<Reading> readS1 = Arrays.asList(new Reading(15.5, rTime1), new Reading(16, rTime2), new Reading(16.5, rTime3));
        List<Reading> readS2 = Arrays.asList(new Reading(55, rTime1), new Reading(85, rTime2), new Reading(10, rTime3));
        List<Reading> readS3 = Arrays.asList(new Reading(5, rTime1), new Reading(37, rTime2), new Reading(58, rTime3));

        DataType type1 = new DataType("temperature");
        DataType type2 = new DataType("precipitation");
        DataType type3 = new DataType("wind");

        Sensor sensor1 = new Sensor("TemperatureSensor", rTime1, 41, -7.5, 49, type1, readS1);
        Sensor sensor2 = new Sensor("PrecipitationSensor", rTime2, 41, -7.5, 49, type2, readS2);
        Sensor sensor3 = new Sensor("WindSensor", rTime3, 41, -7.5, 49, type3, readS3);

        TypeGAList tga = new TypeGAList();
        TypeGA city = tga.newTypeGA("city");

        GeographicalArea gA1 = new GeographicalArea("Porto", city, new Location(41, -8, 50), Arrays.asList(sensor1, sensor2, sensor3));
        List<Reading> expectedResult = Arrays.asList(sensor1.getLastReadingPerSensor(), sensor2.getLastReadingPerSensor(), sensor3.getLastReadingPerSensor());
        List<Reading> result = gA1.getLastValuesOfSensorsInGA();
        assertEquals(expectedResult, result);
    }


    @DisplayName("Test if method returns last reading values and respective date of each sensor in a specific geographic area")
    @Test
    public void ensureGetLastValuesOfSensorsInGADoesNotReturnNull() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        GregorianCalendar rTime3 = new GregorianCalendar(2018, 11, 29, 18, 0);

        List<Reading> readS1 = Arrays.asList(new Reading(15.5, rTime1), new Reading(16, rTime2), new Reading(16.5, rTime3));
        List<Reading> readS2 = Arrays.asList(new Reading(55, rTime1), new Reading(85, rTime2), new Reading(10, rTime3));
        List<Reading> readS3 = Arrays.asList(new Reading(5, rTime1), new Reading(37, rTime2), new Reading(58, rTime3));

        DataType type1 = new DataType("temperature");
        DataType type2 = new DataType("precipitation");
        DataType type3 = new DataType("wind");

        Sensor sensor1 = new Sensor("TemperatureSensor", rTime1, 41, -7.5, 49, type1, readS1);
        Sensor sensor2 = new Sensor("PrecipitationSensor", rTime1, 41, -7.5, 49, type2, readS2);
        Sensor sensor3 = new Sensor("WindSensor", rTime1, 41, -7.5, 49, type3, readS3);

        TypeGAList tga = new TypeGAList();
        TypeGA city = tga.newTypeGA("city");

        GeographicalArea gA1 = new GeographicalArea("Porto", city, new Location(41, -8, 50), Arrays.asList(sensor1, sensor2, sensor3));
        List<Reading> result = gA1.getLastValuesOfSensorsInGA();
        assertNotEquals(null, result);
    }


    @DisplayName("Calculate distance between to geographical areas")
    @Test
    void calculateDistanceFromPortoToGaia() {
        TypeGAList tga = new TypeGAList();
        TypeGA city = tga.newTypeGA("city");

        Location locationPorto = new Location(12.3, 35.2, 120);
        GeographicalArea PortoGA = new GeographicalArea("Porto", city, locationPorto);

        Location locationGaia = new Location(5.3, 33.2, 10);
        GeographicalArea GaiaGA = new GeographicalArea("Porto", city, locationGaia);

        double expectedDistance = 110.24;
        double result = PortoGA.calculateDistanceTo(GaiaGA);

        assertEquals(expectedDistance, result, 0.5);
    }


    @DisplayName("Calculate distance between two geographical areas")
    @Test
    void calculateDistanceFromPortoToFunchal() {
        TypeGAList tga = new TypeGAList();
        TypeGA city = tga.newTypeGA("city");
        Location locationPorto = new Location(12.3, 35.2, 120);
        GeographicalArea PortoGA = new GeographicalArea("Porto", city, locationPorto);

        Location locationFunchal = new Location(8, -125, 10);
        GeographicalArea FunchalGA = new GeographicalArea("Funchal", city, locationFunchal);

        double expectedDistance = 194.37;
        double result = PortoGA.calculateDistanceTo(FunchalGA);
        assertNotEquals(PortoGA.hashCode(), FunchalGA.hashCode());//to test the hash code method
        assertEquals(expectedDistance, result, 0.5);
    }

    @Test
    @DisplayName("Check if longitude in top left corner of Matosinhos returns 4")
    void getLongitudeTopLeftCornerGA() {
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        double expectedResult = 4;
        double result = g.getLongitudeTopLeftCornerGA();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check if latitude in top left corner of Matosinhos returns 6.5")
    void getLatitudeTopLeftCornerGA() {
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        double expectedResult = 6.5;
        double result = g.getLatitudeTopLeftCornerGA();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check if longitude in bottom right corner of Matosinhos returns 6")
    void getLongitudeBottomRightCornerGA() {
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        double expectedResult = 6;
        double result = g.getLongitudeBottomRightCornerGA();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check if latitude in bottom right corner of Matosinhos returns 1.5")
    void getLatitudeBottomRightCornerGA() {
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        double expectedResult = 1.5;
        double result = g.getLatitudeBottomRightCornerGA();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure that location is within Geographical Area")
    void locationIsInAG() {
        Location l = new Location(5, 4, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean expectedResult = true;
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure that location is within Geographical Area (limit test)")
    void locationIsInTopLeftLimitOfGA() {
        Location l = new Location(6.5, 4, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Ensure that location is within Geographical Area (limit test)")
    void locationIsInBottomLeftLimitOfGA() {
        Location l = new Location(1.5, 4, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Ensure that location is within Geographical Area (limit test)")
    void locationIsInTopRightLimitOfGA() {
        Location l = new Location(6.5, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Ensure that location is within Geographical Area (limit test)")
    void locationIsInBottomRightLimitOfGA() {
        Location l = new Location(1.5, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that location l is not within Geographical Area")
    void locationIsNotInGABecauseLatitudeIsOutOfGABoundsLeft() {
        Location l = new Location(1, 4.5, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 2, 5);
        boolean expectedResult = false;
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure that location is not within Geographical Area")
    void locationIsNotInGABecauseLatitudeIsOutOfGABoundsRight() {
        Location l = new Location(7, 5, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 2, 5);
        boolean expectedResult = false;
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure that location is not within Geographical Area (limit test)")
    void locationIsNotInGABecauseLongitudeIsOutOfBoundsLeft() {
        Location l = new Location(2, -5, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.locationIsInAG(l.getLatitude(), l.getLongitude());
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Ensure that location is not within Geographical Area (limit test)")
    void locationIsNotInGABecauseLongitudeIsOutOfBoundsRight() {
        Location l4 = new Location(2, 7, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.longitudeIsInAG(l4.getLongitude());
        assertEquals(false,result);
    }
    /*@Test
    @DisplayName("Ensure that location is not within Geographical Area (limit test)")
    void locationIsInGABecauseLongitudeLimit() {
        Location l4 = new Location(1.5, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.longitudeIsInAG(l4.getLongitude());
        assertEquals(true,result);
    }
    @Test
    @DisplayName("Ensure that location is not within Geographical Area (limit test)")
    void locationIsNotInGABecauseLongitudeLimit1() {
        Location l4 = new Location(1.5, 3, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.longitudeIsInAG(l4.getLongitude());
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Ensure that location is not within Geographical Area (limit test)")
    void locationIsNotInGABecauseLatitudeLimit() {
        Location l4 = new Location(1.5, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.latitudeIsInAG(l4.getLongitude());
        assertEquals(true,result);
    }
    @Test
    @DisplayName("Ensure that location is not within Geographical Area (limit test)")
    void locationIsNotInGABecauseLatitudeLimit1() {
        Location l4 = new Location(2, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.latitudeIsInAG(l4.getLongitude());
        assertEquals(true,result);
    }
    @Test
    @DisplayName("Ensure that location l4 is not within Geographical Area (limit test)")
    void lo() {
        Location l4 = new Location(4, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.longitudeIsInAG(l4.getLongitude());
        assertEquals(true,result);
    }
    @Test
    @DisplayName("Ensure that location l4 is not within Geographical Area (limit test)")
    void locationIs() {
        Location l4 = new Location(1.5, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.latitudeIsInAG(l4.getLongitude());
        assertEquals(true,result);
    }
    @Test
    @DisplayName("Ensure that location l4 is not within Geographical Area (limit test)")
    void locationIs2() {
        Location l4 = new Location(1, 6, 0);
        GeographicalArea g = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        boolean result = g.latitudeIsInAG(l4.getLongitude());
        assertEquals(true,result);
    }*/


    @DisplayName("Test if getListOfSensors returns null for an empty sensor list")
    @Test
    public void GAComparison() {
        TypeGAList tGAList = new TypeGAList();
        TypeGA district = tGAList.newTypeGA("district");
        TypeGA city = tGAList.newTypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Beja", district);
        GeographicalArea ga2 = new GeographicalArea("Funchal", city);

        assertFalse(ga1.equals(ga2));
        assertFalse(ga2.equals(city));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addSensor() {
        Sensor sensor = new Sensor("TemperatureSensor", new GregorianCalendar(2018, 12, 1), 2, 2, 0, "Temperature");
        GeographicalArea ga = new GeographicalArea("Matosinhos", "Cidade", 4, 5, 2, 5,2);
        ga.addSensor(sensor);
        assertTrue(ga.getListOfSensors().size() == 1);
        ga.addSensor(sensor);
        assertTrue(ga.getListOfSensors().size() == 1);
    }





    @Test
    @DisplayName("Define the parent Geographical Area of another Geographical Area")
    public void setmParentGA() {

        GeographicalArea ga1= new GeographicalArea("Porto","city",2,4,5,5,6);
        GeographicalArea ga2= new GeographicalArea("Portugal","Country",3,4,5,6,7);

        ga1.setmParentGA(ga2);

        GeographicalArea expectedResult = ga2;
        GeographicalArea result = ga1.getmParentGA();
        assertEquals(expectedResult, result);

    }


    @Test
    @DisplayName("Return the parent of a Geographical Area of another Geographical Area")
    public void getmParentGA() {

        GeographicalArea ga1 = new GeographicalArea("Porto", "city", 2, 4, 5, 5, 6);
        GeographicalArea ga2 = new GeographicalArea("Portugal", "Country", 3, 4, 5, 6, 7);

        ga1.getmParentGA();

        GeographicalArea expectedResult = null;
        GeographicalArea result = ga1.getmParentGA();
        assertEquals(expectedResult, result);

    }
}
