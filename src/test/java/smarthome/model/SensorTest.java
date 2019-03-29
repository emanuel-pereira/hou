package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


class SensorTest {

    @Test
    void setSensorAttributes(){
        Sensor sensor = new Sensor();

        sensor.setId("RF12345");

        SensorType sType = new SensorType("rainfall");
        sensor.setSensorType(sType);

        Calendar date = new GregorianCalendar(2018,Calendar.NOVEMBER,21);
        sensor.setStartDate(date);

        sensor.setUnit("l/m2");
        sensor.setSensorDesignation("Meteo station ISEP - rainfall");

        Location location = new Location(70,130,4000);
        sensor.setSensorLocation(location);

        String result1 = sensor.getId();
        SensorType result2 = sensor.getSensorType();
        Calendar result3 = sensor.getStartDate();
        String result4 = sensor.getUnit();
        String result5 = sensor.getDesignation();
        Location result6 = sensor.getLocation();

        assertEquals("RF12345",result1);
        assertEquals(sType,result2);
        assertEquals(date,result3);
        assertEquals("l/m2",result4);
        assertEquals("Meteo station ISEP - rainfall",result5);
        assertEquals(location,result6);

    }

    @Test
    public void testIfRenamingASensorWithINVALIDStringReturnsFalse() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        Sensor tempSensor = new Sensor ("", "", startDate, loc, type1, "Celsius", readingList);
        String designation = "";
        boolean result = tempSensor.setSensorDesignation (designation);
        assertFalse (result);
    }


    @Test
    public void testIfRenamingASensorWithValidStringReturnsTrue() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        Sensor tempSensor = new Sensor ("", "Sensor04TempPorto", startDate, loc, type1, "Celsius", readingList);
        String designation = "Sensor04TempPorto";
        boolean result = tempSensor.setSensorDesignation (designation);
        assertTrue (result);
    }


    @Test
    public void testIfRenamingASensorWithAnEmptyStringIsIgnored() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        Sensor tempSensor = new Sensor ("", "Sensor01TempMat", startDate, loc, type1, "Celsius", readingList);
        String designation = "";
        String expectedResult = "Sensor01TempMat";
        tempSensor.setSensorDesignation (designation);
        String result = tempSensor.getDesignation ();
        assertEquals (expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation() {
        SensorType type1 = new SensorType ("visibility");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        Sensor tempSensor = new Sensor ("", " ", startDate, loc, type1, "meters", readingList);
        String designation = "SensorVisibilityLisbon";
        tempSensor.setSensorDesignation (designation);
        String expectedResult = "SensorVisibilityLisbon";
        String result = tempSensor.getDesignation ();
        assertEquals (expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation() {
        SensorType type1 = new SensorType ("visibility");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        Sensor tempSensor = new Sensor ("", "sensor", startDate, loc, type1, "meters", readingList);
        String designation = "SensorVisibilityLisboa";
        tempSensor.setSensorDesignation (designation);
        String expectedResult = "SensorVisibilityLisboa";
        String result = tempSensor.getDesignation ();
        assertEquals (expectedResult, result);
    }


    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation() {
        SensorType type1 = new SensorType ("wind");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("", "WindSensorSantarem", startDate, loc, type1, "meters", readingList);
        String designation = "WindSensorLisboa";
        sensor.setSensorDesignation (designation);
        String expectedResult = "WindSensorSantarem";
        String result = sensor.getDesignation ();
        assertNotEquals (expectedResult, result);
    }


    @Test
    void checkIfLocationOfRainfallSensorIsUpdated() {
        SensorType type1 = new SensorType ("rainfall");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        Sensor rainfallSensor = new Sensor ("", "RainfallSensorOfPorto", startDate, loc, type1, "Celsius", readingList);
        Location loc1 = new Location (30, -12, 62);
        rainfallSensor.setSensorLocation (loc1);
        Location result = rainfallSensor.getLocation ();
        assertEquals (loc1, result);
    }

    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsReturnsExpectedResult() {
        GregorianCalendar rTime1 = new GregorianCalendar (2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar (2018, 11, 28, 12, 0);

        SensorType type1 = new SensorType ("precipitation");
        SensorType type2 = new SensorType ("temperature");

        Location l1 = new Location (0, 10, 15);
        Location l2 = new Location (30, 25, 20);
        ReadingList readings = new ReadingList ();

        Sensor sensor1 = new Sensor ("P2355", "PrecipitationSensor", rTime1, l1, type1, "l/m2", readings);
        Sensor sensor2 = new Sensor ("TT1023", "TemperatureSensor", rTime2, l2, type2, "C", readings);

        double expectedResult = 33.91;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors (sensor1, sensor2);
        assertEquals (expectedResult, result);
    }

    @Test
    void calculateLinearDistanceBetweenTwoSensorsInTheSamePositionReturnsZero() {
        GregorianCalendar rTime1 = new GregorianCalendar (2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar (2018, 11, 28, 12, 0);

        SensorType type1 = new SensorType ("precipitation");
        SensorType type2 = new SensorType ("temperature");
        Location loc = new Location (10, 10, 10);
        ReadingList readings = new ReadingList ();

        Sensor sensor1 = new Sensor ("P2355", "PrecipitationSensor", rTime1, loc, type1, "l/m2", readings);
        Sensor sensor2 = new Sensor ("TT1023", "TemperatureSensor", rTime2, loc, type2, "C", readings);

        double expectedResult = 0;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors (sensor1, sensor2);
        assertEquals (expectedResult, result);
    }


    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsDoesNotReturnZero() {
        GregorianCalendar rTime1 = new GregorianCalendar (2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar (2018, 11, 28, 12, 0);
        Location loc1 = new Location (20, 10, 15);
        Location loc2 = new Location (30, 25, 20);
        SensorType type1 = new SensorType ("precipitation");
        SensorType type2 = new SensorType ("temperature");
        ReadingList readings = new ReadingList ();

        Sensor sensor1 = new Sensor ("P2355", "PrecipitationSensor", rTime1, loc1, type1, "l/m2", readings);
        Sensor sensor2 = new Sensor ("TT1023", "TemperatureSensor", rTime2, loc2, type2, "C", readings);

        double expectedResult = 0;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors (sensor1, sensor2);
        assertNotEquals (expectedResult, result);
    }


    @Test
    void addReadingValue() {
        GregorianCalendar rTime1 = new GregorianCalendar (2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar (2018, 11, 28, 12, 0);
        Location loc1 = new Location (20, 10, 15);
        Location loc2 = new Location (30, 25, 20);

        SensorType type1 = new SensorType ("precipitation");
        SensorType type2 = new SensorType ("temperature");
        ReadingList readings = new ReadingList ();

        Sensor sensor1 = new Sensor ("P2355", "PrecipitationSensor", rTime1, loc1, type1, "l/m2", readings);
        Sensor sensor2 = new Sensor ("TT1023", "TemperatureSensor", rTime2, loc2, type2, "C", readings);
        double expectedResult = 0;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors (sensor1, sensor2);
        assertNotEquals (expectedResult, result);
    }


    @Test
    @DisplayName("Comparison of not equal sensors")
    void sensorsComparison() {
        //Arrange
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        SensorType type1 = new SensorType ("precipitation");
        SensorType type2 = new SensorType ("temperature");
        ReadingList readings = new ReadingList ();

        Sensor sensor1 = new Sensor ("P2355", "PrecipitationSensor", startDate, loc, type1, "l/m2", readings);
        Sensor sensor2 = new Sensor ("TT1023", "TemperatureSensor", startDate, loc, type2, "C", readings);

        assertNotEquals (sensor1, sensor2);
        assertNotEquals (sensor2, type1);
        assertNotEquals (sensor1.hashCode (), sensor2.hashCode ());
    }

    @Test
    void getStartDate() {
        SensorType type1 = new SensorType ("precipitation");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readings = new ReadingList ();
        Sensor sensor1 = new Sensor ("P2355", "PrecipitationSensor", startDate, loc, type1, "l/m2", readings);
        Calendar expected = new GregorianCalendar (2018, 8, 1, 9, 0);
        Calendar result = sensor1.getStartDate ();
        assertEquals (expected, result);
    }

    @Test
    void getUnit() {
        SensorType type1 = new SensorType ("precipitation");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readings = new ReadingList ();
        Sensor sensor1 = new Sensor ("P2355", "PrecipitationSensor", startDate, loc, type1, "l/m2", readings);
        String expected = "l/m2";
        String result = sensor1.getUnit ();
        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Check if a sensor is active when is created and return true")
    void activeSensor() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue (sensor.isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is deactivated with success")
    void deactivateSensor() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue (sensor.deactivate (pauseDate));

        assertFalse (sensor.isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is deactivated when we try to deactivated again")
    void deactivateSensorTwice() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue(sensor.deactivate (pauseDate));
        assertFalse(sensor.deactivate (pauseDate));

        assertFalse (sensor.isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is reactivated with success")
    void reactivateSensor() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue(sensor.deactivate (pauseDate));
        assertTrue(sensor.reactivate ());

        assertTrue(sensor.isActive ());
    }

    @Test
    @DisplayName("Try unsuccessfully to reactivate a sensor before deactivation")
    void reactivateSensorFirst() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertFalse(sensor.reactivate ());

        assertTrue(sensor.isActive ());
    }

    @Test
    @DisplayName("Get pause date if deactivated with success with posterior date")
    void getDeactivateWithSuccess() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue (sensor.deactivate (pauseDate));

        Calendar expected = pauseDate;
        Calendar result = sensor.getPauseDate ();

        assertEquals (expected,result);
    }

    @Test
    @DisplayName("Get pause date if deactivated without success withs previous date")
    void getDeactivateWithoutSuccess() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 1, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        Sensor sensor = new Sensor ("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertFalse (sensor.deactivate (pauseDate));

        Calendar expected = null;
        Calendar result = sensor.getPauseDate ();

        assertEquals (expected,result);

    }



}