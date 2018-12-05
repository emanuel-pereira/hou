package sprint0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GeographicalAreaTest {

    @DisplayName("set street name and type of GA for street")
    @Test
    void GeographicalAreaConstructorStreet() {
        GeographicalArea GA = new GeographicalArea("Aliados", TypeOfGeographicalArea.STREET);
    }

    @DisplayName("set village name and type of GA for village")
    @Test
    public void defineTypesOfGeograficalAreaVillage() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.VILLAGE;
        String geographicalAreaName = "Paranhos";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set city name and type of GA for city")
    @Test
    public void defineTypesOfGeograficalAreaCity() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.CITY;
        String geographicalAreaName = "Porto";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set district name and type of GA for district")
    @Test
    public void defineTypesOfGeograficalAreaDistrict() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.DISTRICT;
        String geographicalAreaName = "Area Metropolitana do Porto";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set country name and type of GA for country")
    @Test
    public void defineTypesOfGeograficalAreaCountry() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.COUNTRY;
        String geographicalAreaName = "Portugal";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set country name and type of GA for country and set of a specific position")
    @Test
    public void defineTypesOfGeograficalAreaCityLocation() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.CITY;
        String geographicalAreaName = "Porto";
        Location location = new Location(12.3, 35.2, 120);
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga, location);

        double[] expectedLocation = {12.3, 35.2, 120};
        double[] result = location.getLocation();

        assertArrayEquals(expectedLocation, result);
    }

    @DisplayName("Test if getListOfSensors returns null for an empty sensor list")
    @Test
    public void getEmptyListOfSensors(){
        GeographicalArea a= new GeographicalArea("Beja",TypeOfGeographicalArea.DISTRICT);
        List<Sensor> result=a.getListOfSensors();
        assertEquals(null,result);
    }

    @DisplayName("Test if getListOfSensors returns mSensorList for a specific geographic area")
    @Test
    public void getListOfSensor(){
        Sensor sensor1=new Sensor();
        Sensor sensor2=new Sensor();
        GeographicalArea a= new GeographicalArea("Aveiro",TypeOfGeographicalArea.DISTRICT,new Location(25,-17,15),Arrays.asList(sensor1,sensor2));
        List<Sensor> expectedResult=Arrays.asList(sensor1,sensor2);
        List<Sensor> result=a.getListOfSensors();
        assertEquals(expectedResult,result);
    }

    @DisplayName("Test if method returns last reading values and respective date of each sensor in a specific geographic area")
    @Test
    public void checkIfGetLastValuesOfSensors() {
        GregorianCalendar cL1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Date date2 = cL2.getTime();
        GregorianCalendar cL3 = new GregorianCalendar(2018, 11, 29, 18, 0);
        Date date3 = cL3.getTime();
        List<Reading> readS1 = Arrays.asList(new Reading(15.5, date1), new Reading(16, date2), new Reading(16.5, date3));
        List<Reading> readS2 = Arrays.asList(new Reading(55, date1), new Reading(85, date2), new Reading(10, date3));
        List<Reading> readS3 = Arrays.asList(new Reading(5, date1), new Reading(37, date2), new Reading(58, date3));
        Sensor sensor1 = new Sensor("TemperatureSensor", date1, new Location(41, -7.5, 49), DataType.TEMPERATURE, readS1);
        Sensor sensor2 = new Sensor("PrecipitationSensor", date2,new Location(41, -7.5, 49), DataType.PRECIPITATION, readS2);
        Sensor sensor3 = new Sensor("WindSensor",date3, new Location(41, -7.5, 49), DataType.WIND, readS3);
        GeographicalArea gA1 = new GeographicalArea("Porto", TypeOfGeographicalArea.CITY, new Location(41, -8, 50), Arrays.asList(sensor1, sensor2, sensor3));
        List<Reading> expectedResult = Arrays.asList(sensor1.getLastReadingPerSensor(),sensor2.getLastReadingPerSensor(),sensor3.getLastReadingPerSensor());
        List<Reading> result = gA1.getLastValuesOfSensorsInGA();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Test if method returns last reading values and respective date of each sensor in a specific geographic area")
    @Test
        public void ensureGetLastValuesOfSensorsInGADoesNotReturnNull() {
        GregorianCalendar cL1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Date date2 = cL2.getTime();
        GregorianCalendar cL3 = new GregorianCalendar(2018, 11, 29, 18, 0);
        Date date3 = cL3.getTime();
        List<Reading> readS1 = Arrays.asList(new Reading(15.5, date1), new Reading(16, date2), new Reading(16.5, date3));
        List<Reading> readS2 = Arrays.asList(new Reading(55, date1), new Reading(85, date2), new Reading(10, date3));
        List<Reading> readS3 = Arrays.asList(new Reading(5, date1), new Reading(37, date2), new Reading(58, date3));
        Sensor sensor1 = new Sensor("TemperatureSensor", date1, new Location(41, -7.5, 49), DataType.TEMPERATURE, readS1);
        Sensor sensor2 = new Sensor("PrecipitationSensor",date2, new Location(41, -7.5, 49), DataType.PRECIPITATION, readS2);
        Sensor sensor3 = new Sensor("WindSensor",date3, new Location(41, -7.5, 49), DataType.WIND, readS3);
        GeographicalArea gA1 = new GeographicalArea("Porto", TypeOfGeographicalArea.CITY, new Location(41, -8, 50), Arrays.asList(sensor1, sensor2, sensor3));
        List<Reading> result = gA1.getLastValuesOfSensorsInGA();
        assertNotEquals(null, result);
    }

    @DisplayName("Calculate distance between to geographical areas")
    @Test
    void calculateDistanceFromPortoToGaia() {
        Location locationPorto = new Location(12.3, 35.2, 120);
        GeographicalArea PortoGA = new GeographicalArea("Porto", TypeOfGeographicalArea.CITY, locationPorto);

        Location locationGaia = new Location(5.3, 33.2, 10);
        GeographicalArea GaiaGA = new GeographicalArea("Porto", TypeOfGeographicalArea.CITY, locationGaia);

        double expectedDistance = 110.24;
        double result = PortoGA.calculateDistanceTo(GaiaGA);

        assertEquals(expectedDistance, result, 0.5);
    }

    @DisplayName("Calculate distance between to geographical areas")
    @Test
    void calculateDistanceFromPortoToFunchal() {
        Location locationPorto = new Location(12.3, 35.2, 120);
        GeographicalArea PortoGA = new GeographicalArea("Porto", TypeOfGeographicalArea.CITY, locationPorto);

        Location locationGaia = new Location(8, -125, 10);
        GeographicalArea GaiaGA = new GeographicalArea("Porto", TypeOfGeographicalArea.CITY, locationGaia);

        double expectedDistance = 194.37;
        double result = PortoGA.calculateDistanceTo(GaiaGA);

        assertEquals(expectedDistance, result, 0.5);
    }
}
