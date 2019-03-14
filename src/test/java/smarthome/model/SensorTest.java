package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SensorTest {


    @Test
    public void testIfRenamingASensorWithINVALIDStringReturnsFalse() {
        SensorType type1 = new SensorType("temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor tempSensor = new Sensor("", "", startDate, loc, type1, "Celsius", readingList);
        String designation = "";
        boolean result = tempSensor.setSensorDesignation(designation);
        assertFalse(result);
    }


    @Test
    public void testIfRenamingASensorWithValidStringReturnsTrue() {
        SensorType type1 = new SensorType("temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor tempSensor = new Sensor("", "Sensor04TempPorto", startDate, loc, type1, "Celsius", readingList);
        String designation = "Sensor04TempPorto";
        boolean result = tempSensor.setSensorDesignation(designation);
        assertTrue(result);
    }


    @Test
    public void testIfRenamingASensorWithAnEmptyStringIsIgnored() {
        SensorType type1 = new SensorType("temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor tempSensor = new Sensor("", "Sensor01TempMat", startDate, loc, type1, "Celsius", readingList);
        String designation = "";
        String expectedResult = "Sensor01TempMat";
        tempSensor.setSensorDesignation(designation);
        String result = tempSensor.getDesignation();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation() {
        SensorType type1 = new SensorType("visibility");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor tempSensor = new Sensor("", " ", startDate, loc, type1, "meters", readingList);
        String designation = "SensorVisibilityLisbon";
        tempSensor.setSensorDesignation(designation);
        String expectedResult = "SensorVisibilityLisbon";
        String result = tempSensor.getDesignation();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation() {
        SensorType type1 = new SensorType("visibility");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor tempSensor = new Sensor("", "sensor", startDate, loc, type1, "meters", readingList);
        String designation = "SensorVisibilityLisboa";
        tempSensor.setSensorDesignation(designation);
        String expectedResult = "SensorVisibilityLisboa";
        String result = tempSensor.getDesignation();
        assertEquals(expectedResult, result);
    }


    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation() {
        SensorType type1 = new SensorType("wind");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("", "WindSensorSantarem", startDate, loc, type1, "meters", readingList);
        String designation = "WindSensorLisboa";
        sensor.setSensorDesignation(designation);
        String expectedResult = "WindSensorSantarem";
        String result = sensor.getDesignation();
        assertNotEquals(expectedResult, result);
    }


    @Test
    void checkIfLocationOfRainfallSensorIsUpdated() {
        SensorType type1 = new SensorType("rainfall");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor rainfallSensor = new Sensor("", "RainfallSensorOfPorto", startDate, loc, type1, "Celsius", readingList);
        Location loc1 = new Location(30, -12, 62);
        rainfallSensor.setSensorLocation(loc1);
        Location result = rainfallSensor.getLocation();
        assertEquals(loc1, result);
    }

    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsReturnsExpectedResult() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        SensorType type1 = new SensorType("precipitation");
        SensorType type2 = new SensorType("temperature");

        Location l1 = new Location(0, 10, 15);
        Location l2 = new Location(30, 25, 20);
        ReadingList readings= new ReadingList();

        Sensor sensor1 = new Sensor("P2355","PrecipitationSensor", rTime1, l1, type1,"l/m2",readings);
        Sensor sensor2 = new Sensor("TT1023","TemperatureSensor", rTime2, l2, type2,"C",readings);

        double expectedResult = 33.91;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors(sensor1, sensor2);
        assertEquals(expectedResult, result);
    }

    @Test
    void calculateLinearDistanceBetweenTwoSensorsInTheSamePositionReturnsZero() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        SensorType type1 = new SensorType("precipitation");
        SensorType type2 = new SensorType("temperature");
        Location loc = new Location(10, 10, 10);
        ReadingList readings= new ReadingList();

        Sensor sensor1 = new Sensor("P2355","PrecipitationSensor", rTime1, loc, type1,"l/m2",readings);
        Sensor sensor2 = new Sensor("TT1023","TemperatureSensor", rTime2, loc, type2,"C",readings);

        double expectedResult = 0;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors(sensor1, sensor2);
        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsDoesNotReturnZero() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Location loc1 = new Location(20, 10, 15);
        Location loc2 = new Location(30, 25, 20);
        SensorType type1 = new SensorType("precipitation");
        SensorType type2 = new SensorType("temperature");
        ReadingList readings= new ReadingList();

        Sensor sensor1 = new Sensor("P2355","PrecipitationSensor", rTime1, loc1, type1,"l/m2",readings);
        Sensor sensor2 = new Sensor("TT1023","TemperatureSensor", rTime2, loc2, type2,"C",readings);

        double expectedResult = 0;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors(sensor1, sensor2);
        assertNotEquals(expectedResult, result);
    }


    @Test
    void addReadingValue() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Location loc1 = new Location(20, 10, 15);
        Location loc2 = new Location(30, 25, 20);

        SensorType type1 = new SensorType("precipitation");
        SensorType type2 = new SensorType("temperature");
        ReadingList readings= new ReadingList();

        Sensor sensor1 = new Sensor("P2355","PrecipitationSensor", rTime1, loc1, type1,"l/m2",readings);
        Sensor sensor2 = new Sensor("TT1023","TemperatureSensor", rTime2, loc2, type2,"C",readings);
        double expectedResult = 0;
        double result = sensor1.calcLinearDistanceBetweenTwoSensors(sensor1, sensor2);
        assertNotEquals(expectedResult, result);
    }


    @Test
    @DisplayName("Comparison of not equal sensors")
    void sensorsComparison() {
        //Arrange
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        SensorType type1 = new SensorType("precipitation");
        SensorType type2 = new SensorType("temperature");
        ReadingList readings= new ReadingList();

        Sensor sensor1 = new Sensor("P2355","PrecipitationSensor", startDate, loc, type1,"l/m2",readings);
        Sensor sensor2 = new Sensor("TT1023","TemperatureSensor", startDate, loc, type2,"C",readings);

        assertFalse(sensor1.equals(sensor2));
        assertFalse(sensor2.equals(type1));
        assertNotEquals(sensor1.hashCode(), sensor2.hashCode());
    }

    @Test
    void getStartDate() {
        SensorType type1 = new SensorType("precipitation");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readings= new ReadingList();
        Sensor sensor1 = new Sensor("P2355","PrecipitationSensor", startDate, loc, type1,"l/m2",readings);
        Calendar expected = new GregorianCalendar(2018, 8, 1, 9, 0);
        Calendar result = sensor1.getStartDate();
        assertEquals(expected, result);
    }

    @Test
    void getUnit() {
        SensorType type1 = new SensorType("precipitation");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        ReadingList readings= new ReadingList();
        Sensor sensor1 = new Sensor("P2355","PrecipitationSensor", startDate, loc, type1,"l/m2",readings);
        String expected = "l/m2";
        String result = sensor1.getUnit();
        assertEquals(expected, result);
    }

}