package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


class InternalSensorTest {

    @Test
    void setSensorAttributes(){
        SensorType temperature = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "HouseSensor", startDate, temperature, "l/m2", readingList);

        sensor.setId("RF12345");
        sensor.setSensorDesignation("House Sensor");
        Calendar date = new GregorianCalendar(2018,Calendar.NOVEMBER,21);
        sensor.setStartDate(date);
        SensorType sType = new SensorType("Air Quality");
        sensor.setSensorType(sType);
        sensor.setUnit("l/m2");

        String result1 = sensor.getId();
        String result5 = sensor.getDesignation();
        Calendar result3 = sensor.getStartDate();
        SensorType result2 = sensor.getSensorType();
        String result4 = sensor.getUnit();

        assertEquals("RF12345",result1);
        assertEquals(sType,result2);
        assertEquals(date,result3);
        assertEquals("l/m2",result4);
        assertEquals("House Sensor",result5);

    }

    @Test
    public void testIfRenamingASensorWithINVALIDStringReturnsFalse() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        ReadingList readingList = new ReadingList ();
        InternalSensor tempSensor = new InternalSensor("", "", startDate, type1, "Celsius", readingList);
        String designation = "";
        boolean result = tempSensor.setSensorDesignation (designation);
        assertFalse (result);
    }


    @Test
    public void testIfRenamingASensorWithValidStringReturnsTrue() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        ReadingList readingList = new ReadingList ();
        InternalSensor tempSensor = new InternalSensor("", "Sensor", startDate, type1, "Celsius", readingList);
        String designation = "KitchenSensor";
        boolean result = tempSensor.setSensorDesignation (designation);
        assertTrue (result);
    }


    @Test
    public void testIfRenamingASensorWithAnEmptyStringIsIgnored() {
        SensorType type1 = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        ReadingList readingList = new ReadingList ();
        InternalSensor tempSensor = new InternalSensor("", "Sensor01TempMat", startDate, type1, "Celsius", readingList);
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
        ReadingList readingList = new ReadingList ();
        InternalSensor tempSensor = new InternalSensor("", " ", startDate, type1, "meters", readingList);
        String designation = "SensorVisibilityRoom1";
        tempSensor.setSensorDesignation (designation);
        String expectedResult = "SensorVisibilityRoom1";
        String result = tempSensor.getDesignation ();
        assertEquals (expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation() {
        SensorType type1 = new SensorType ("visibility");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        ReadingList readingList = new ReadingList ();
        InternalSensor tempSensor = new InternalSensor("", "sensor", startDate, type1, "meters", readingList);
        String designation = "SensorVisibility";
        tempSensor.setSensorDesignation (designation);
        String expectedResult = "SensorVisibility";
        String result = tempSensor.getDesignation ();
        assertEquals (expectedResult, result);
    }


    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation() {
        SensorType type1 = new SensorType ("wind");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("", "WindSensor", startDate, type1, "meters", readingList);
        String designation = "WindSensorRoom";
        sensor.setSensorDesignation (designation);
        String expectedResult = "WindSensor";
        String result = sensor.getDesignation ();
        assertNotEquals (expectedResult, result);
    }


    @Test
    @DisplayName("Comparison of not equal sensors")
    void sensorsComparison() {
        //Arrange
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        SensorType type1 = new SensorType ("humidity");
        SensorType type2 = new SensorType ("temperature");
        ReadingList readings = new ReadingList ();

        InternalSensor sensor1 = new InternalSensor("P2355", "HumiditySensor", startDate, type1, "%", readings);
        InternalSensor sensor2 = new InternalSensor("TT1023", "TemperatureSensor", startDate, type2, "C", readings);

        assertNotEquals (sensor1, sensor2);
        assertNotEquals (sensor2, type1);
        assertNotEquals (sensor1.hashCode (), sensor2.hashCode ());
    }

    @Test
    void getStartDate() {
        SensorType type1 = new SensorType ("humidity");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        ReadingList readings = new ReadingList ();
        InternalSensor sensor1 = new InternalSensor("P2355", "HumiditySensor", startDate, type1, "%", readings);
        Calendar expected = new GregorianCalendar (2018, 8, 1, 9, 0);
        Calendar result = sensor1.getStartDate ();
        assertEquals (expected, result);
    }

    @Test
    void getUnit() {
        SensorType type1 = new SensorType ("precipitation");
        GregorianCalendar startDate = new GregorianCalendar (2018, 8, 1, 9, 0);
        ReadingList readings = new ReadingList ();
        InternalSensor sensor1 = new InternalSensor("P2355", "GardenSensor", startDate, type1, "l/m2", readings);
        String expected = "l/m2";
        String result = sensor1.getUnit ();
        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set a sensor Id with success")
    void setIdSuccess() {
        SensorType rain = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate, rain, "l/m2", readingList);

        assertTrue (sensor.setId("P233"));
    }

    @Test
    @DisplayName("Set a sensor Id without success")
    void setIdWithoutSuccess() {
        SensorType rain = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate, rain, "l/m2", readingList);

        assertFalse (sensor.setId(" "));
    }

    @Test
    @DisplayName("Check if a sensor is active when is created and return true")
    void activeSensor() {
        SensorType sensorType = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "BedroomSensor", startDate, sensorType, "C", readingList);

        assertTrue (sensor.isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is deactivated with success")
    void deactivateSensor() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate, sensorType, "l/m2", readingList);

        assertTrue (sensor.deactivate (pauseDate));

        assertFalse (sensor.isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is deactivated when we try to deactivated again")
    void deactivateSensorTwice() {
        SensorType sensorType = new SensorType ("temperature");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "PrecipitationSensor", startDate, sensorType, "C", readingList);

        assertTrue(sensor.deactivate (pauseDate));
        assertFalse(sensor.deactivate (pauseDate));

        assertFalse (sensor.isActive ());
    }

    @Test
    @DisplayName("Check if a sensor is reactivated with success")
    void reactivateSensor() {
        SensorType sensorType = new SensorType ("Presence");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "PresenceSensor", startDate, sensorType, "People", readingList);

        assertTrue(sensor.deactivate (pauseDate));
        assertTrue(sensor.reactivate ());

        assertTrue(sensor.isActive ());
    }

    @Test
    @DisplayName("Try unsuccessfully to reactivate a sensor before deactivation")
    void reactivateSensorFirst() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate, sensorType, "l/m2", readingList);

        assertFalse(sensor.reactivate ());

        assertTrue(sensor.isActive ());
    }

    @Test
    @DisplayName("Get pause date if deactivated with success with posterior date")
    void getDeactivateWithSuccess() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 3, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate,  sensorType, "l/m2", readingList);

        assertTrue (sensor.deactivate (pauseDate));

        Calendar result = sensor.getPauseDate ();

        assertEquals (pauseDate,result);
    }

    @Test
    @DisplayName("Get pause date if deactivated without success withs previous date")
    void getDeactivateWithoutSuccess() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        GregorianCalendar pauseDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 1, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate, sensorType, "l/m2", readingList);

        assertFalse (sensor.deactivate (pauseDate));

        Calendar expected = null;
        Calendar result = sensor.getPauseDate ();

        assertEquals (expected,result);

    }


    @Test
    @DisplayName("Try to unsuccessfully set an Id")
    void setIdWithoutSuccess2() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate, sensorType, "l/m2", readingList);

        assertFalse(sensor.setId(" "));
    }

    @Test
    @DisplayName("Try to successfully set an Id")
    void setIdWithSuccess() {
        SensorType sensorType = new SensorType ("rain");
        GregorianCalendar startDate = new GregorianCalendar (2019, Calendar.FEBRUARY, 2, 2, 1, 1);
        ReadingList readingList = new ReadingList ();
        InternalSensor sensor = new InternalSensor("P2355", "GardenSensor", startDate, sensorType, "l/m2", readingList);

        assertTrue(sensor.setId("P235"));
    }

}