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
    public void testIfRenamingASensorWithINVALIDStringReturnsFalse(){
        Sensor tempSensor= new Sensor ("");
        String designation= "";
        boolean expectedResult= false;
        boolean result;
        result=tempSensor.setSensorDesignation(designation);
        assertEquals(expectedResult, result);
    }


    @Test
    public void testIfRenamingASensorWithValidStringReturnsTrue(){
        Sensor tempSensor= new Sensor ("Sensor04TempPorto");
        String designation= "Sensor04TempPorto";
        boolean result;
        result=tempSensor.setSensorDesignation(designation);
        assertTrue(result);
    }


    @Test
    public void testIfRenamingASensorWithAnEmptyStringIsIgnored(){
        Sensor tempSensor= new Sensor("Sensor01TempMat");
        String designation= "";
        String expectedResult="Sensor01TempMat";
        String result;
        tempSensor.setSensorDesignation(designation);
        result=tempSensor.getDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor(" ");
        String designation= "SensorVisibilityLisbon";
        String expectedResult="SensorVisibilityLisbon";
        String result;
        tempSensor.setSensorDesignation(designation);
        result=tempSensor.getDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor("SensorVisibilityLisboa");
        String designation= "SensorVisibilityLisboa";
        String expectedResult="SensorVisibilityLisboa";
        String result;
        tempSensor.setSensorDesignation(designation);
        result=tempSensor.getDesignation();
        assertEquals(expectedResult,result);
    }


    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation(){
        Sensor tempSensor= new Sensor("WindSensorSantarem");
        String designation= "WindSensorLisboa";
        String expectedResult="WindSensorSantarem";
        String result;
        tempSensor.setSensorDesignation(designation);
        result=tempSensor.getDesignation();
        assertNotEquals(expectedResult,result);
    }


    @Test
    void checkIfGetLocationReturnsNullBeforeAnyValueIsInputted() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfNowhere");
        Location expectedResult= null;
        Location result;
        result=rainfallSensor.getLocation();
        assertEquals(expectedResult,result);}


    @Test
    void checkIfLocationOfRainfallSensorIsUpdated() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfPorto");
        Location loc1 = new Location(30,-12,62);
        Location result;
        rainfallSensor.setSensorLocation(loc1);
        result=rainfallSensor.getLocation();
        assertEquals(loc1,result);
    }


    @Test
    void checkIfGetSensorTypeMethodReturnsSensorTypeDefault() {
        Sensor visibilitySensor=new Sensor("WindSensorOfCoimbra");
        SensorType expectedResult= null;
        SensorType result;
        result=visibilitySensor.getSensorType();
        assertEquals(expectedResult,result);
    }


   @Test
    void checkIfSetAndGetSensorTypeAndSensorTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfCoimbra");

        SensorType visibility = new SensorType("visibility");
        SensorType resultSensorType;
        String resultDesignation;
        String expectedDesignation="SensorOfCoimbra";

        visibilitySensor.setSensorType(visibility);
        resultSensorType=visibilitySensor.getSensorType();
        resultDesignation=visibilitySensor.getDesignation();


        assertEquals(visibility,resultSensorType);
        assertEquals(expectedDesignation,resultDesignation);

   }



    @Test
    void checkIfSetAndGetMethodReturnsSecondUpdateOfSensorTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfViseu");

        SensorType oldType = new SensorType("humidity");
        SensorType newType =  new SensorType("precipitation");

        SensorType result;

        visibilitySensor.setSensorType(oldType);
        visibilitySensor.setSensorType(newType);
        result=visibilitySensor.getSensorType();

        assertEquals(newType,result);
    }


    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsReturnsExpectedResult() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        SensorType type1 = new SensorType("precipitation");
        SensorType type2 =  new SensorType("temperature");

        Location l1= new Location(0,10,15);
        Location l2= new Location(30,25,20);

        Sensor sensor1= new Sensor("PrecipitationSensor",rTime1,l1, type1);
        Sensor sensor2= new Sensor("TemperatureSensor",rTime2,l2,type2);

        double expectedResult=33.91;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfValidNameIsNotValidToAnotherConstructor() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        SensorType type1 = new SensorType("precipitation");
        Location l1= new Location(20,10,15);
        Sensor sensor1= new Sensor("   ",rTime1,l1, type1);
        String designation = "   ";
        boolean expectedResult= false;
        boolean result;
        result=sensor1.setSensorDesignation(designation);
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfValidNameIsNotValidToFinalConstructor() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Location loc = new Location(20,10,15);
        SensorType type = new SensorType("precipitation");
        Sensor sensor1= new Sensor("   ",rTime1,loc, type);
        String designation = "   ";
        boolean expectedResult= false;
        boolean result;
        result=sensor1.setSensorDesignation(designation);
        assertEquals(expectedResult, result);
    }


    @Test
    void calculateLinearDistanceBetweenTwoSensorsInTheSamePositionReturnsZero() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        SensorType type1 = new SensorType("temperature");

        Location loc = new Location(10,10,10);
        Sensor sensor1= new Sensor("TemperatureSensor1",rTime1,loc,type1);
        Sensor sensor2= new Sensor("TemperatureSensor2",rTime2,loc,type1);

        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }


    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsDoesNotReturnZero() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        SensorType type1 = new SensorType("wind");
        Location loc1= new Location(20,10,15);
        Location loc2= new Location(30,25,20);
        Sensor sensor1= new Sensor("WindSensor1",rTime1,loc1,type1);
        Sensor sensor2= new Sensor("WindSensor2",rTime2,loc2,type1);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }



    @Test
    void addReadingValue() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Location loc1= new Location(20,10,15);
        Location loc2= new Location(30,25,20);
        SensorType type = new SensorType("wind");
        Sensor sensor1= new Sensor("WindSensor1",rTime1,loc1,type);
        Sensor sensor2= new Sensor("WindSensor2",rTime2,loc2,type);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }



    @Test
    @DisplayName("Comparison of not equal sensors")
    void sensorsComparison() {
        //Arrange
        SensorType type1 = new SensorType("visibility");
        GregorianCalendar startdate = new GregorianCalendar(2018,8,1,9,0);
        Location loc= new Location(40, 20,10);
        Sensor sensor1 = new Sensor("VisibilitySensor",startdate,loc,type1);

        SensorType wind = new SensorType("windspeed");
        Sensor sensor2 = new Sensor("windSensor",startdate,loc,wind);

        assertFalse(sensor1.equals(sensor2));
        assertFalse(sensor2.equals(type1));
        assertNotEquals(sensor1.hashCode(),sensor2.hashCode());
    }

    @Test
    void getStartDate() {
        SensorType type1 = new SensorType("visibility");
        GregorianCalendar startdate = new GregorianCalendar(2018,8,1,9,0);
        Location loc= new Location(40, 20,10);
        Sensor sensor1 = new Sensor("VisibilitySensor",startdate,loc,type1);
        Calendar expected= new GregorianCalendar(2018,8,1,9,0);
        Calendar result= sensor1.getStartDate();
        assertEquals(expected,result);
    }

    @Test
    void getUnit() {
        SensorType type1 = new SensorType("visibility");
        GregorianCalendar startdate = new GregorianCalendar(2018,8,1,9,0);
        Location loc= new Location(40, 20,10);
        ReadingList readingList= new ReadingList();
        Sensor sensor1 = new Sensor("VisibilitySensor",startdate,loc,type1,"Celsius",readingList);
        String expected= "Celsius";
        String result= sensor1.getUnit();
        assertEquals(expected,result);
    }

}