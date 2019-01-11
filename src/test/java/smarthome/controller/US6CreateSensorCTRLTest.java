
package smarthome.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        readingsPt.addReading (r1Porto);
        readingsPt.addReading (r2Porto);

        Reading r1Lis = new Reading(27, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2Lis = new Reading(21, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readingsLis = new ReadingList();
        readingsLis.addReading (r1Lis);
        readingsLis.addReading (r2Lis);

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
        ReadingList readingsPt = new ReadingList ();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading (r1Porto);

        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList,gaList);

        boolean thrown = false;

        try {

           ctrl6.addNewSensorToGA("LisboaTempSensor", new GregorianCalendar(2018, 12, 26), 1, "C",900, 80, 15, 1, readingsPt);
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
        readingsPt.addReading (r1Porto);

        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList,gaList);

        boolean thrown = false;

        try {

            ctrl6.addNewSensorToGA("LisboaTempSensor", new GregorianCalendar(2018, 12, 26), 1, "C",90,190,1500, 1, readingsPt);
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
        ReadingList readingsPt = new ReadingList ();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading (r1Porto);

        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(sensorTypeList,gaList);

        boolean thrown = false;

        try {

            ctrl6.addNewSensorToGA("LisboaTempSensor", new GregorianCalendar(2018, 12, 26), 1, "C",90,190,9000, 1, readingsPt);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }


}
