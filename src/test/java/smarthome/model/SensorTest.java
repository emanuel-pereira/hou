package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

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
        boolean expectedResult= true;
        boolean result;
        result=tempSensor.setSensorDesignation(designation);
        assertEquals(expectedResult, result);
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
        Location expectedResult= loc1;
        Location result;
        rainfallSensor.setSensorLocation(loc1);
        result=rainfallSensor.getLocation();
        assertEquals(expectedResult,result);
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
        SensorType expectedSensorType = visibility;
        SensorType resultSensorType;
        String resultDesignation;
        String expectedDesignation="SensorOfCoimbra";

        visibilitySensor.setSensorDataType(visibility);
        resultSensorType=visibilitySensor.getSensorType();
        resultDesignation=visibilitySensor.getDesignation();


        assertEquals(expectedSensorType,resultSensorType);
        assertEquals(expectedDesignation,resultDesignation);

   }



    @Test
    void checkIfSetAndGetMethodReturnsSecondUpdateOfSensorTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfViseu");

        SensorType oldType = new SensorType("humidity");
        SensorType newType =  new SensorType("precipitation");

        SensorType expectedResult= newType;
        SensorType result;

        visibilitySensor.setSensorDataType(oldType);
        visibilitySensor.setSensorDataType(newType);
        result=visibilitySensor.getSensorType();

        assertEquals(expectedResult,result);
    }


    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsReturnsExpectedResult() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        SensorType type1 = new SensorType("precipitation");
        SensorType type2 =  new SensorType("temperature");

        Sensor sensor1= new Sensor("PrecipitationSensor",rTime1,20,10,15, type1);
        Sensor sensor2= new Sensor("TemperatureSensor",rTime2,30,25,20,type2);

        double expectedResult=18.708286933869708;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfValidNameIsNotValidToAnotherConstructor() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        SensorType type1 = new SensorType("precipitation");
        Sensor sensor1= new Sensor("   ",rTime1,20,10,15, type1);
        String designation = "   ";
        boolean expectedResult= false;
        boolean result;
        result=sensor1.setSensorDesignation(designation);
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfValidNameIsNotValidToFinalConstructor() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Sensor sensor1= new Sensor("   ",rTime1,20,10,15, "precipitation");
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

        Sensor sensor1= new Sensor("TemperatureSensor1",rTime1,10,10,10,type1);
        Sensor sensor2= new Sensor("TemperatureSensor2",rTime2,10,10,10,type1);

        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }


    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsDoesNotReturnZero() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        SensorType type1 = new SensorType("wind");
        Sensor sensor1= new Sensor("WindSensor1",rTime1,20,10,15,type1);
        Sensor sensor2= new Sensor("WindSensor2",rTime2,30,25,20,type1);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }



    @Test
    void addReadingValue() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Sensor sensor1= new Sensor("WindSensor1",rTime1,20,10,15,"wind");
        Sensor sensor2= new Sensor("WindSensor2",rTime2,30,25,20,"wind");
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }



    @Test
    void getLastReadingOfSensorIfThreeReadings() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018,11,27,21,0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018,11,27,22,0);
        GregorianCalendar rTime3 = new GregorianCalendar(2018,11,27,23,0);
        Reading readingDate1 = new Reading (11, rTime1);
        Reading readingDate2 = new Reading (10, rTime2);
        Reading expectedReading = new Reading (8, rTime3);
        Sensor sensor2 = new Sensor ("Temp131");
        sensor2.addReadingToList(readingDate1);
        sensor2.addReadingToList(readingDate2);
        sensor2.addReadingToList(expectedReading);
        Reading result = sensor2.getLastReadingPerSensor();
        assertEquals(expectedReading, result);
    }

    /**
     * Novo
     */
    @Test
    void getCorrectLastValueOfSensorIfThreeReadings() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018,11,27,21,0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018,11,27,22,0);
        GregorianCalendar rTime3 = new GregorianCalendar(2018,11,27,23,0);
        Reading readingDate1 = new Reading (11, rTime1);
        Reading readingDate2 = new Reading (10, rTime2);
        Reading readingDate3 = new Reading (8, rTime3);
        Sensor sensor2 = new Sensor ("Temp131");
        sensor2.addReadingToList(readingDate1);
        sensor2.addReadingToList(readingDate2);
        sensor2.addReadingToList(readingDate3);
        double result = sensor2.getLastReadingValuePerSensor ();
        assertEquals(8, result);
    }

    /**
     * Novo
     */
    @Test
    void getIncorrectLastValueOfSensorIfThreeReadingsIfWrong() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018,11,27,21,0);
        GregorianCalendar rTime2 = new GregorianCalendar(2018,11,27,22,0);
        GregorianCalendar rTime3 = new GregorianCalendar(2018,11,27,23,0);
        Reading readingDate1 = new Reading (11, rTime1);
        Reading readingDate2 = new Reading (10, rTime2);
        Reading readingDate3 = new Reading (8, rTime3);
        Sensor sensor2 = new Sensor ("Temp131");
        sensor2.addReadingToList(readingDate1);
        sensor2.addReadingToList(readingDate2);
        sensor2.addReadingToList(readingDate3);
        double result = sensor2.getLastReadingValuePerSensor ();
        assertNotEquals(10, result);
    }

    @Test
    void getLastReadingOfSensorIfWrongExpectedResult() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018,11,27,21,0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018,11,27,22,0);

        GregorianCalendar rTime3 = new GregorianCalendar(2018,11,27,23,0);

        Reading readingDate1 = new Reading (11, rTime1);
        Reading readingDate2 = new Reading (10, rTime2);
        Reading expectedReading = new Reading (8, rTime3);

        Sensor sensor1 = new Sensor ("Temp131");
        sensor1.addReadingToList(readingDate1);
        sensor1.addReadingToList(readingDate2);
        sensor1.addReadingToList(expectedReading);

        Reading result = sensor1.getLastReadingPerSensor();

        assertNotEquals(readingDate2, result);
    }

    @Test
    void getLastReadingOfSensorIfOneReading() {
        GregorianCalendar cL1 = new GregorianCalendar(2018,11,27,21,0);


        Reading expectedReading = new Reading (8, cL1);

        Sensor sensor1 = new Sensor ("Temp132");
        sensor1.addReadingToList(expectedReading);

        Reading result = sensor1.getLastReadingPerSensor ();

        assertEquals(expectedReading, result);
    }


    @Test
    @DisplayName("Comparison of not equal sensors")
    void sensorsComparison() {
        //Arrange
        SensorType type1 = new SensorType("visibility");
        Sensor sensor1 = new Sensor("VisibilitySensor",new GregorianCalendar(2018,8,1,9,0),40, 20,10,type1);

        SensorType wind = new SensorType("windspeed");
        Sensor sensor2 = new Sensor("windSensor",new GregorianCalendar(2018,8,1,9,0),40, 20,10,type1);

        assertFalse(sensor1.equals(sensor2));
        assertFalse(sensor2.equals(type1));
        assertNotEquals(sensor1.hashCode(),sensor2.hashCode());
    }

}