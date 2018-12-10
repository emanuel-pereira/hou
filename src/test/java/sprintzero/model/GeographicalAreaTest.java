package sprintzero.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaTest {

    @DisplayName("set country name and type of GA for country and set of a specific position")
    @Test
    public void defineTypesOfGeograficalAreaCityLocation() {
        TypeGAList tga = new TypeGAList ();
        TypeGA city = tga.newTypeGA ("city");

        String geographicalAreaName = "Porto";
        Location location = new Location(12.3, 35.2, 120);

        GeographicalArea GA = new GeographicalArea(geographicalAreaName, city, location);

        double[] expectedLocation = {12.3, 35.2, 120};
        double[] result = location.getLocation();

        assertArrayEquals(expectedLocation, result);
    }


    @DisplayName("Test if getListOfSensors returns null for an empty sensor list")
    @Test
    public void getEmptyListOfSensors(){
        TypeGAList tga = new TypeGAList ();
        TypeGA district = tga.newTypeGA ("district");
        GeographicalArea a= new GeographicalArea("Beja", district);
        List<Sensor> result=a.getListOfSensors();
        assertNull(result);
    }



    @DisplayName("Test if getListOfSensors returns mSensorList for a specific geographic area")
    @Test
    public void getListOfSensor(){
        Sensor sensor1=new Sensor();
        Sensor sensor2=new Sensor();
        TypeGAList tga = new TypeGAList ();
        TypeGA district = tga.newTypeGA ("district");
        GeographicalArea a= new GeographicalArea("Aveiro",district,new Location(25,-17,15),Arrays.asList(sensor1,sensor2));
        List<Sensor> expectedResult=Arrays.asList(sensor1,sensor2);
        List<Sensor> result=a.getListOfSensors();
        assertEquals(expectedResult,result);
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

        Sensor sensor1 = new Sensor("TemperatureSensor", rTime1, new Location(41, -7.5, 49), DataType.TEMPERATURE, readS1);
        Sensor sensor2 = new Sensor("PrecipitationSensor", rTime2,new Location(41, -7.5, 49), DataType.PRECIPITATION, readS2);
        Sensor sensor3 = new Sensor("WindSensor",rTime3, new Location(41, -7.5, 49), DataType.WIND, readS3);

        TypeGAList tga = new TypeGAList ();
        TypeGA city = tga.newTypeGA ("city");

        GeographicalArea gA1 = new GeographicalArea("Porto", city, new Location(41, -8, 50), Arrays.asList(sensor1, sensor2, sensor3));
        List<Reading> expectedResult = Arrays.asList(sensor1.getLastReadingPerSensor(),sensor2.getLastReadingPerSensor(),sensor3.getLastReadingPerSensor());
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

        Sensor sensor1 = new Sensor("TemperatureSensor", rTime1, new Location(41, -7.5, 49), DataType.TEMPERATURE, readS1);
        Sensor sensor2 = new Sensor("PrecipitationSensor",rTime1, new Location(41, -7.5, 49), DataType.PRECIPITATION, readS2);
        Sensor sensor3 = new Sensor("WindSensor",rTime1, new Location(41, -7.5, 49), DataType.WIND, readS3);

        TypeGAList tga = new TypeGAList ();
        TypeGA city = tga.newTypeGA ("city");

        GeographicalArea gA1 = new GeographicalArea("Porto", city, new Location(41, -8, 50), Arrays.asList(sensor1, sensor2, sensor3));
        List<Reading> result = gA1.getLastValuesOfSensorsInGA();
        assertNotEquals(null, result);
    }



    @DisplayName("Calculate distance between to geographical areas")
    @Test
    void calculateDistanceFromPortoToGaia() {
        TypeGAList tga = new TypeGAList ();
        TypeGA city = tga.newTypeGA ("city");

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
        TypeGAList tga = new TypeGAList ();
        TypeGA city = tga.newTypeGA ("city");
        Location locationPorto = new Location(12.3, 35.2, 120);
        GeographicalArea PortoGA = new GeographicalArea("Porto", city, locationPorto);

        Location locationGaia = new Location(8, -125, 10);
        GeographicalArea GaiaGA = new GeographicalArea("Porto", city, locationGaia);

        double expectedDistance = 194.37;
        double result = PortoGA.calculateDistanceTo(GaiaGA);

        assertEquals(expectedDistance, result, 0.5);
    }
}


