package sprintzero.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sprintzero.model.DataType;
import sprintzero.model.Location;
import sprintzero.model.Reading;
import sprintzero.model.Sensor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class SensorTest {


    @Test
    public void testIfRenamingASensorWithINVALIDStringReturnsFalse(){
        Sensor tempSensor= new Sensor ("");
        String designation= "";
        boolean expectedResult= false;
        boolean result;
        result=tempSensor.setDesignation(designation);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringReturnsTrue(){
        Sensor tempSensor= new Sensor ("Sensor004TempPorto");
        String designation= "Sensor004TempPorto";
        boolean expectedResult= true;
        boolean result;
        result=tempSensor.setDesignation(designation);
        assertEquals(expectedResult, result);
    }


    @Test
    public void testIfRenamingASensorWithAnEmptyStringIsIgnored(){
        Sensor tempSensor= new Sensor("Sensor01TempMat");
        String designation= "";
        String expectedResult="Sensor01TempMat";
        String result;
        tempSensor.setDesignation (designation);
        result=tempSensor.getDesignation ();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor(" ");
        String designation= "SensorVisibilityLisbon";
        String expectedResult="SensorVisibilityLisbon";
        String result;
        tempSensor.setDesignation (designation);
        result=tempSensor.getDesignation ();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor("SensorVisibilityLisboa");
        String designation= "SensorVisibilityLisboa";
        String expectedResult="SensorVisibilityLisboa";
        String result;
        tempSensor.setDesignation (designation);
        result=tempSensor.getDesignation ();
        assertEquals(expectedResult,result);
    }


    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation(){
        Sensor tempSensor= new Sensor("WindSensorSantarem");
        String designation= "WindSensorLisboa";
        String expectedResult="WindSensorSantarem";
        String result;
        tempSensor.setDesignation (designation);
        result=tempSensor.getDesignation ();
        assertNotEquals(expectedResult,result);
    }


    @Test
    void checkIfGetLocationReturnsNullBeforeAnyValueIsInputted() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfNowhere");
        Location expectedResult= null;
        Location result;
        result=rainfallSensor.getLocation ();
        assertEquals(expectedResult,result);}


    @Test
    void checkIfLocationOfRainfallSensorIsUpdated() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfPorto");
        Location loc1 = new Location(30,-12,62);
        Location expectedResult= loc1;
        Location result;
        rainfallSensor.setLocation (loc1);
        result=rainfallSensor.getLocation ();
        assertEquals(expectedResult,result);
    }


    @Test
    void checkIfGetDataTypeMethodReturnsDataTypeDefault() {
        Sensor visibilitySensor=new Sensor("WindSensorOfCoimbra");
        DataType expectedResult= null;
        DataType result;
        result=visibilitySensor.getDataTypeDesignation ();
        assertEquals(expectedResult,result);
    }

   @Test
    void checkIfSetAndGetDataTypeMethodUpdatesSensorDataTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfCoimbra");
       DataType newType = DataType.VISIBILITY;
        DataType expectedResult= newType;
        DataType result;
        visibilitySensor.setDataTypeDesignation (newType);
        result=visibilitySensor.getDataTypeDesignation ();
        assertEquals(expectedResult,result);

   }

    @Test
    void checkIfSetAndGetMethodReturnsSecondUpdateOfDataTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfViseu");
        DataType oldType = DataType.HUMIDITY;
        DataType newType = DataType.PRECIPITATION;
        DataType expectedResult= newType;
        DataType result;
        visibilitySensor.setDataTypeDesignation (oldType);
        visibilitySensor.setDataTypeDesignation (newType);
        result=visibilitySensor.getDataTypeDesignation ();
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsReturnsExpectedResult() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("PrecipitationSensor",rTime1,locationSensor1,DataType.PRECIPITATION);
        Sensor sensor2= new Sensor("TemperatureSensor",rTime2,locationSensor2,DataType.TEMPERATURE);
        double expectedResult=18.708286933869708;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void calculateLinearDistanceBetweenTwoSensorsInTheSamePositionReturnsZero() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        Location locationSensor1=new Location(10,10,10);
        Location locationSensor2=new Location(10,10,10);
        Sensor sensor1= new Sensor("TemperatureSensor1",rTime1,locationSensor1,DataType.TEMPERATURE);
        Sensor sensor2= new Sensor("TemperatureSensor2",rTime2,locationSensor2,DataType.TEMPERATURE);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsDoesNotReturnZero() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("WindSensor1",rTime1,locationSensor1,DataType.WIND);
        Sensor sensor2= new Sensor("WindSensor2",rTime2,locationSensor2,DataType.WIND);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }

    @Test
    void addReadingValue() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("WindSensor1",rTime1,locationSensor1,DataType.WIND);
        Sensor sensor2= new Sensor("WindSensor2",rTime2,locationSensor2,DataType.WIND);
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
        sensor2.addReading(readingDate1);
        sensor2.addReading(readingDate2);
        sensor2.addReading(expectedReading);

        Reading result = sensor2.getLastReadingPerSensor();

        assertEquals(expectedReading, result);
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
        sensor1.addReading(readingDate1);
        sensor1.addReading(readingDate2);
        sensor1.addReading(expectedReading);

        Reading result = sensor1.getLastReadingPerSensor();

        assertNotEquals(readingDate2, result);
    }

    @Test
    void getLastReadingOfSensorIfOneReading() {
        GregorianCalendar cL1 = new GregorianCalendar(2018,11,27,21,0);


        Reading expectedReading = new Reading (8, cL1);

        Sensor sensor1 = new Sensor ("Temp132");
        sensor1.addReading (expectedReading);

        Reading result = sensor1.getLastReadingPerSensor ();

        assertEquals(expectedReading, result);
    }


    @Test
    @DisplayName("Check monthly average value in September is 31.7")
    void getMonthlyAverageReadings() {
        //Arrange
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,00), new Location(40, 20,10),DataType.VISIBILITY);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 00));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 00));

        s1.addReading(r1);
        s1.addReading(r2);
        s1.addReading(r3);
        s1.addReading(r4);

        //Act
        double result = s1.getMonthlyAverageReadings(9);
        double expectedResult= 31.7;

        //Assert
        assertEquals(expectedResult,result,0.1);
    }

    @Test
    @DisplayName("Check if monthly average value is not equal to expected")
    void getMonthlyAverageReadingsNotEquals() {
        //Arrange
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,00), new Location(40, 20,10),DataType.VISIBILITY);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 00));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 00));

        s1.addReading(r1);
        s1.addReading(r2);
        s1.addReading(r3);
        s1.addReading(r4);

        //Act
        double result = s1.getMonthlyAverageReadings(9);
        double expectedResult= 20;

        //Assert
        assertNotEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Check if monthly average returns zero if there are no readings")
    void getMonthlyAverageReadingsNoReadings() {
        //Arrange
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,00), new Location(40, 20,10),DataType.VISIBILITY);

        //Act
        double result = s1.getMonthlyAverageReadings(9);
        double expectedResult= Double.NaN;

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Test to verify the minimum reading in October")
    public void getMonthlyMinimumReading() {


        //Arrange
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,00), new Location(40, 20,10),DataType.VISIBILITY);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 00));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 00));
        Reading r5 = new Reading(54, new GregorianCalendar(2018, 10, 20, 11, 00));

        s1.addReading(r1);
        s1.addReading(r2);
        s1.addReading(r3);
        s1.addReading(r4);
        s1.addReading(r5);

        //Act
        double result = s1.getMonthlyMinimumReading(10);
        double expectedResult = 13.4;

        //Assert
        assertEquals(expectedResult,result,0.1);
    }

    @Test
    @DisplayName("Test to verify the minimum reading in October")
    public void getMonthlyMinimumReadingNotEquals() {


        //Arrange
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,00), new Location(40, 20,10),DataType.VISIBILITY);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 00));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 00));
        Reading r5 = new Reading(54, new GregorianCalendar(2018, 10, 20, 11, 00));

        s1.addReading(r1);
        s1.addReading(r2);
        s1.addReading(r3);
        s1.addReading(r4);
        s1.addReading(r5);

        //Act
        double result = s1.getMonthlyMinimumReading(10);
        double expectedResult = 10;

        //Assert
        assertNotEquals(expectedResult,result);
    }
    @Test
    public void getMonthlyAverageReadingListTest() {


        //Arrange
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,00), new Location(40, 20,10),DataType.VISIBILITY);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 00));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 00));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 00));
        Reading r5 = new Reading(54, new GregorianCalendar(2018, 10, 20, 11, 00));

        s1.addReading(r1);
        s1.addReading(r2);
        s1.addReading(r3);
        s1.addReading(r4);
        s1.addReading(r5);

        //Act
        double[] result = s1.getMonthlyAverageReadingEachMonth();
        double [] expectedResult = new double[]{0,0,0,0,0,0,0,50,31.7,33.7,0,0};

        //Assert
        assertArrayEquals(expectedResult,result);
    }


}