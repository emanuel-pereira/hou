package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ExternalSensorTest {

    @Test
    void setSensorAttributes(){
        SensorType rain = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, rain, "l/m2", readingList);

        sensor.getSensorBehavior().setSensorDesignation("Meteo station ISEP - rainfall");
        assertFalse(sensor.getSensorBehavior().setSensorDesignation(" "));
        Calendar date = new GregorianCalendar(2018,Calendar.NOVEMBER,21);
        sensor.getSensorBehavior().setStartDate(date);
        Location location1 = new Location(70,130,4000);
        sensor.setLocation(location1);
        SensorType sType = new SensorType("rainfall");
        sensor.getSensorBehavior().setSensorType(sType);
        sensor.getSensorBehavior().setUnit("l/m2");

        String result1 = sensor.getId();
        String result5 = sensor.getSensorBehavior().getDesignation();
        Calendar result3 = sensor.getSensorBehavior().getStartDate();
        Location result6 = sensor.getLocation();
        SensorType result2 = sensor.getSensorBehavior().getSensorType();
        String result4 = sensor.getSensorBehavior().getUnit();

        assertEquals("P2355",result1);
        assertEquals(sType,result2);
        assertEquals(date,result3);
        assertEquals("l/m2",result4);
        assertEquals("Meteo station ISEP - rainfall",result5);
        assertEquals(location1,result6);

    }

    @Test
    public void testIfRenamingASensorWithINVALIDStringReturnsFalse() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        ExternalSensor tempSensor = new ExternalSensor("", "", startDate, loc, type1, "Celsius", readingList);
        String designation = "";
        boolean result = tempSensor.getSensorBehavior().setSensorDesignation (designation);
        assertFalse (result);
    }


    @Test
    public void testIfRenamingASensorWithValidStringReturnsTrue() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        ExternalSensor tempSensor = new ExternalSensor("", "Sensor04TempPorto", startDate, loc, type1, "Celsius", readingList);
        String designation = "Sensor04TempPorto";
        boolean result = tempSensor.getSensorBehavior().setSensorDesignation (designation);
        assertTrue (result);
    }


    @Test
    public void testIfRenamingASensorWithAnEmptyStringIsIgnored() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        ExternalSensor tempSensor = new ExternalSensor("", "Sensor01TempMat", startDate, loc, type1, "Celsius", readingList);
        String designation = "";
        String expectedResult = "Sensor01TempMat";
        tempSensor.getSensorBehavior().setSensorDesignation (designation);
        String result = tempSensor.getSensorBehavior().getDesignation ();
        assertEquals (expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation() {
        SensorType type1 = new SensorType ("visibility");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        ExternalSensor tempSensor = new ExternalSensor("", " ", startDate, loc, type1, "meters", readingList);
        String designation = "SensorVisibilityLisbon";
        tempSensor.getSensorBehavior().setSensorDesignation (designation);
        String expectedResult = "SensorVisibilityLisbon";
        String result = tempSensor.getSensorBehavior().getDesignation ();
        assertEquals (expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation() {
        SensorType type1 = new SensorType ("visibility");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        ExternalSensor tempSensor = new ExternalSensor("", "sensor", startDate, loc, type1, "meters", readingList);
        String designation = "SensorVisibilityLisboa";
        tempSensor.getSensorBehavior().setSensorDesignation (designation);
        String expectedResult = "SensorVisibilityLisboa";
        String result = tempSensor.getSensorBehavior().getDesignation ();
        assertEquals (expectedResult, result);
    }


    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation() {
        SensorType type1 = new SensorType ("wind");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("", "WindSensorSantarem", startDate, loc, type1, "meters", readingList);
        String designation = "WindSensorLisboa";
        sensor.getSensorBehavior().setSensorDesignation (designation);
        String expectedResult = "WindSensorSantarem";
        String result = sensor.getSensorBehavior().getDesignation ();
        assertNotEquals (expectedResult, result);
    }


    @Test
    void checkIfLocationOfRainfallSensorIsUpdated() {
        SensorType type1 = new SensorType ("rainfall");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readingList = new ReadingList ();
        ExternalSensor rainfallSensor = new ExternalSensor("", "RainfallSensorOfPorto", startDate, loc, type1, "Celsius", readingList);
        Location loc1 = new Location (30, -12, 62);
        rainfallSensor.setLocation (loc1);
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

        ExternalSensor sensor1 = new ExternalSensor("P2355", "PrecipitationSensor", rTime1, l1, type1, "l/m2", readings);
        ExternalSensor sensor2 = new ExternalSensor("TT1023", "TemperatureSensor", rTime2, l2, type2, "C", readings);

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

        ExternalSensor sensor1 = new ExternalSensor("P2355", "PrecipitationSensor", rTime1, loc, type1, "l/m2", readings);
        ExternalSensor sensor2 = new ExternalSensor("TT1023", "TemperatureSensor", rTime2, loc, type2, "C", readings);

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

        ExternalSensor sensor1 = new ExternalSensor("P2355", "PrecipitationSensor", rTime1, loc1, type1, "l/m2", readings);
        ExternalSensor sensor2 = new ExternalSensor("TT1023", "TemperatureSensor", rTime2, loc2, type2, "C", readings);

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

        ExternalSensor sensor1 = new ExternalSensor("P2355", "PrecipitationSensor", rTime1, loc1, type1, "l/m2", readings);
        ExternalSensor sensor2 = new ExternalSensor("TT1023", "TemperatureSensor", rTime2, loc2, type2, "C", readings);
        double expectedResult = 0;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors (sensor1, sensor2);
        assertNotEquals (expectedResult, result);
    }

    @Test
    void getReadingListTest(){
        Location loc = new Location (30, 25, 20);
        Address address = new Address("rua","123","1234-567","town","country",loc);
        House.setHouseAddress(address);
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2019, 2, 1), "C");
        Reading r2 = new Reading(16, new GregorianCalendar(2019, 2, 2), "C");
        Reading r3 = new Reading(18, new GregorianCalendar(2019, 2, 3), "C");
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 3, 3), "C");

        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);

        GregorianCalendar rTime = new GregorianCalendar (2018, 2, 1, 12, 0);
        SensorType sType = new SensorType ("temperature");
        ExternalSensor sensor = new ExternalSensor ("TT1023", "TemperatureSensor", rTime,loc, sType, "C", readingList);

        List<Reading> expected = Arrays.asList(r1,r2,r3,r4);
        List<Reading> result = sensor.getSensorBehavior().getReadingList().getReadingsList();

        assertEquals(expected,result);
    }

    @Test
    void getLastReadingTest(){
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2019, 2, 2), "C");
        Reading r2 = new Reading(16, new GregorianCalendar(2019, 2, 2), "C");
        Reading r3 = new Reading(18, new GregorianCalendar(2019, 2, 2), "C");
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 3, 3), "C");

        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);

        GregorianCalendar rTime = new GregorianCalendar (2018, 2, 1, 12, 0);
        Location loc = new Location (30, 25, 20);
        SensorType sType = new SensorType ("temperature");
        ExternalSensor sensor = new ExternalSensor ("TT1023", "TemperatureSensor", rTime, loc, sType, "C", readingList);

        Reading result = sensor.getSensorBehavior().getLastReading();

        assertEquals(r4,result);
    }

    @Test
    void getLastReadingValueTest(){
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2019, 2, 2), "C");
        Reading r2 = new Reading(16, new GregorianCalendar(2019, 2, 2), "C");
        Reading r3 = new Reading(18, new GregorianCalendar(2019, 2, 2), "C");
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 3, 3), "C");

        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);

        GregorianCalendar rTime = new GregorianCalendar (2018, 2, 1, 12, 0);
        Location loc = new Location (30, 25, 20);
        SensorType sType = new SensorType ("temperature");
        ExternalSensor sensor = new ExternalSensor ("TT1023", "TemperatureSensor", rTime, loc, sType, "C", readingList);

        double result = sensor.getSensorBehavior().getLastReadingValue();
        assertEquals(20,result);
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

        ExternalSensor sensor1 = new ExternalSensor("P2355", "PrecipitationSensor", startDate, loc, type1, "l/m2", readings);
        ExternalSensor sensor2 = new ExternalSensor("TT1023", "TemperatureSensor", startDate, loc, type2, "C", readings);

        assertEquals(true,sensor1.equals(sensor1));
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
        ExternalSensor sensor1 = new ExternalSensor("P2355", "PrecipitationSensor", startDate, loc, type1, "l/m2", readings);
        Calendar expected = new GregorianCalendar (2018, 8, 1, 9, 0);
        Calendar result = sensor1.getSensorBehavior().getStartDate ();
        assertEquals (expected, result);
    }

    @Test
    void getUnit() {
        SensorType type1 = new SensorType ("precipitation");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        Location loc = new Location (40, 20, 10);
        ReadingList readings = new ReadingList ();
        ExternalSensor sensor1 = new ExternalSensor("P2355", "PrecipitationSensor", startDate, loc, type1, "l/m2", readings);
        String expected = "l/m2";
        String result = sensor1.getSensorBehavior().getUnit ();
        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Check if a sensor is active when is created and return true")
    void activeSensor() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue (sensor.getSensorBehavior().isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is deactivated with success")
    void deactivateSensor() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue (sensor.getSensorBehavior().deactivate (pauseDate));

        assertFalse (sensor.getSensorBehavior().isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is deactivated when we try to deactivated again")
    void deactivateSensorTwice() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue(sensor.getSensorBehavior().deactivate (pauseDate));
        assertFalse(sensor.getSensorBehavior().deactivate (pauseDate));

        assertFalse (sensor.getSensorBehavior().isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is reactivated with success")
    void reactivateSensor() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue(sensor.getSensorBehavior().deactivate (pauseDate));
        assertTrue(sensor.getSensorBehavior().reactivate ());

        assertTrue(sensor.getSensorBehavior().isActive ());
    }

    @Test
    @DisplayName("Try unsuccessfully to reactivate a sensor before deactivation")
    void reactivateSensorFirst() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertFalse(sensor.getSensorBehavior().reactivate ());

        assertTrue(sensor.getSensorBehavior().isActive ());
    }

    @Test
    @DisplayName("Get pause date if deactivated with success with posterior date")
    void getDeactivateWithSuccess() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertTrue (sensor.getSensorBehavior().deactivate (pauseDate));

        Calendar expected = pauseDate;
        Calendar result = sensor.getSensorBehavior().getPauseDate ();

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
        ExternalSensor sensor = new ExternalSensor("P2355", "PrecipitationSensor", startDate, location, sensorType, "l/m2", readingList);

        assertFalse (sensor.getSensorBehavior().deactivate (pauseDate));

        Calendar expected = null;
        Calendar result = sensor.getSensorBehavior().getPauseDate ();

        assertEquals (expected,result);

    }

    @Test
    void constructor(){
        SensorType rain = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        Location location = new Location (2, 2, 2);
        ReadingList readingList = new ReadingList ();
        ExternalSensor sensor = new ExternalSensor(" ", "PrecipitationSensor", startDate, location, rain, "l/m2", readingList);



    }

}