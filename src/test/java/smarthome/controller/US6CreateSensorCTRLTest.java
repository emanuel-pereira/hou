
package smarthome.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class US6CreateSensorCTRLTest {


    @DisplayName("Test if SensorTypeList is showed as a string to the user")
    @Test
    void showSensorTypeListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList, gaList);
        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);
        String expected = "1 - Temperature\n2 - Wind\n";
        String result = ctrl6.showSensorTypeListInString();
        assertEquals(expected, result);

    }

    @DisplayName("Test if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList, gaList);
        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", 45, 25, 32, 42, 41);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        String expected = "1 - Porto\n2 - Lisboa\n";
        String result = ctrl6.showGAListInString();
        assertEquals(expected, result);
    }

    @Test
    void getGAList() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList, gaList);
        GeographicalArea area1 = gaList.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10);
        GeographicalArea area2 = gaList.newGA("Pt","Braga", "district", 20, 20, 1, 3, -10);
        gaList.addGA(area1);
        gaList.addGA(area2);

        List<GeographicalArea> expectedResult = Arrays.asList(area1, area2);
        List<GeographicalArea> result = ctrl6.getGAList();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Ensure that two different sensors are added to the respective Geographical Area")
    @Test
    void addNewSensorToGA() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList, gaList);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", 45, 25, 32, 42, 41);

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2Porto = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readingsPt = new ReadingList();
        readingsPt.addReading(r1Porto);
        readingsPt.addReading(r2Porto);

        Reading r1Lis = new Reading(27, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2Lis = new Reading(21, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readingsLis = new ReadingList();
        readingsLis.addReading(r1Lis);
        readingsLis.addReading(r2Lis);


        boolean result = ctrl6.addNewSensorToGA("LisboaTempSensor", new GregorianCalendar(2018, 12, 26), 1, "C", 55, 40, 15, 1, readingsPt);
        assertTrue(result);

        boolean result1 = ctrl6.addNewSensorToGA("PortoWindSensor", new GregorianCalendar(2018, 12, 26), 2, "km/h", 55, 40, 15, 2, readingsLis);
        assertTrue(result1);
    }

    @DisplayName("Check if throws exception when the latitude is wrong")
    @Test
    public void throwsIllegalArgumentExceptionForLatitude() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        ReadingList readingsPt = new ReadingList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading(r1Porto);

        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList, gaList);

        boolean thrown = false;

        try {

            ctrl6.addNewSensorToGA("LisboaTempSensor", new GregorianCalendar(2018, 12, 26), 1, "C", 900, 80, 15, 1, readingsPt);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the longitude is wrong")
    @Test
    public void throwsIllegalArgumentExceptionForLongitude() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        ReadingList readingsPt = new ReadingList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading(r1Porto);

        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList, gaList);

        boolean thrown = false;

        try {

            ctrl6.addNewSensorToGA("LisboaTempSensor", new GregorianCalendar(2018, 12, 26), 1, "C", 90, 190, 1500, 1, readingsPt);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the altitude is wrong")
    @Test
    public void throwsIllegalArgumentExceptionForAltitude() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        ReadingList readingsPt = new ReadingList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading(r1Porto);

        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList, gaList);

        boolean thrown = false;

        try {

            ctrl6.addNewSensorToGA("LisboaTempSensor", new GregorianCalendar(2018, 12, 26), 1, "C", 90, 190, 9000, 1, readingsPt);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }


    @Test
    @DisplayName("Test if GPS coordinates validation methods return true when GPS cordinates are within defined range")
    void testIfGPSCoordinatesAreValid() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        GeographicalArea porto = gaList.newGA("PT","Porto","City",25,12,23,12,11);
        gaList.addGA(porto);
        US6CreateSensorCTRL ctrl = new US6CreateSensorCTRL(sensorTypeList,gaList);
        Sensor sensor = new Sensor("Name",new GregorianCalendar(2020,11,1),25,28,11,temperature,"Celsius",new ReadingList());

        boolean expected= true;
        boolean result=ctrl.latitudeIsValid(sensor.getLocation().getLatitude());
        assertEquals(expected,result);

        boolean expected1= true;
        boolean result1=ctrl.longitudeIsValid(sensor.getLocation().getLongitude());
        assertEquals(expected1,result1);

        boolean expected2= true;
        boolean result2=ctrl.altitudeIsValid(sensor.getLocation().getAltitude());
        assertEquals(expected2,result2);

    }

    @Test
    @DisplayName("Test if all date inputs are valid")
    void inputDateIsValid() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        GeographicalArea porto = gaList.newGA("PT","Porto","City",25,12,23,12,11);
        gaList.addGA(porto);
        US6CreateSensorCTRL ctrl = new US6CreateSensorCTRL(sensorTypeList,gaList);
        String year="2018";

        String expected= "2018";
        String result= ctrl.yearIsValid(year);
        assertEquals(expected,result);

        String month="11";
        String expected1= "11";
        String result1= ctrl.monthIsValid(month);
        assertEquals(expected1,result1);

        int yearAsInteger=Integer.parseInt(year);
        int monthAsInteger=Integer.parseInt(month);
        String day="30";
        String expected2= "30";
        String result2= ctrl.dayIsValid(day,monthAsInteger,yearAsInteger);
        assertEquals(expected2,result2);
    }

    @Test
    void hourIsValid() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        GeographicalArea porto = gaList.newGA("PT","Porto","City",25,12,23,12,11);
        gaList.addGA(porto);
        US6CreateSensorCTRL ctrl = new US6CreateSensorCTRL(sensorTypeList,gaList);
        String hour="22";

        String expected="22";
        String result=ctrl.hourIsValid(hour);

        assertEquals(expected,result);
    }

    @Test
    void nameIsValid() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        GeographicalArea porto = gaList.newGA("PT","Porto","City",25,12,23,12,11);
        gaList.addGA(porto);
        US6CreateSensorCTRL ctrl = new US6CreateSensorCTRL(sensorTypeList,gaList);
        String inputName="Sensor - ISEP";

        String expected="Sensor - ISEP";
        String result=ctrl.nameIsValid(inputName);

        assertEquals(expected,result);

    }
}
