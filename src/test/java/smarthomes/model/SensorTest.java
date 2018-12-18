package smarthomes.model;

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
        result=tempSensor.getSensorDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor(" ");
        String designation= "SensorVisibilityLisbon";
        String expectedResult="SensorVisibilityLisbon";
        String result;
        tempSensor.setSensorDesignation(designation);
        result=tempSensor.getSensorDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor("SensorVisibilityLisboa");
        String designation= "SensorVisibilityLisboa";
        String expectedResult="SensorVisibilityLisboa";
        String result;
        tempSensor.setSensorDesignation(designation);
        result=tempSensor.getSensorDesignation();
        assertEquals(expectedResult,result);
    }


    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation(){
        Sensor tempSensor= new Sensor("WindSensorSantarem");
        String designation= "WindSensorLisboa";
        String expectedResult="WindSensorSantarem";
        String result;
        tempSensor.setSensorDesignation(designation);
        result=tempSensor.getSensorDesignation();
        assertNotEquals(expectedResult,result);
    }


    @Test
    void checkIfGetLocationReturnsNullBeforeAnyValueIsInputted() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfNowhere");
        Location expectedResult= null;
        Location result;
        result=rainfallSensor.getSensorLocation();
        assertEquals(expectedResult,result);}


    @Test
    void checkIfLocationOfRainfallSensorIsUpdated() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfPorto");
        Location loc1 = new Location(30,-12,62);
        Location expectedResult= loc1;
        Location result;
        rainfallSensor.setSensorLocation(loc1);
        result=rainfallSensor.getSensorLocation();
        assertEquals(expectedResult,result);
    }


    @Test
    void checkIfGetDataTypeMethodReturnsDataTypeDefault() {
        Sensor visibilitySensor=new Sensor("WindSensorOfCoimbra");
        DataType expectedResult= null;
        DataType result;
        result=visibilitySensor.getSensorDataType();
        assertEquals(expectedResult,result);
    }


   @Test
    void checkIfSetAndGetDataTypeSensorDataTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfCoimbra");

        DataType visibility = new DataType("visibility");
        DataType expectedDataType = visibility;
        DataType resultDataType;
        String resultDesignation;
        String expectedDesignation="SensorOfCoimbra";

        visibilitySensor.setSensorDataType(visibility);
        resultDataType=visibilitySensor.getSensorDataType();
        resultDesignation=visibilitySensor.getSensorDesignation();


        assertEquals(expectedDataType,resultDataType);
        assertEquals(expectedDesignation,resultDesignation);

   }



    @Test
    void checkIfSetAndGetMethodReturnsSecondUpdateOfDataTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfViseu");

        DataType oldType = new DataType ("humidity");
        DataType newType =  new DataType ("precipitation");

        DataType expectedResult= newType;
        DataType result;

        visibilitySensor.setSensorDataType(oldType);
        visibilitySensor.setSensorDataType(newType);
        result=visibilitySensor.getSensorDataType();

        assertEquals(expectedResult,result);
    }


    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsReturnsExpectedResult() {
        GregorianCalendar rTime1 = new GregorianCalendar(2018, 11, 27, 9, 0);

        GregorianCalendar rTime2 = new GregorianCalendar(2018, 11, 28, 12, 0);

        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);

        DataType type1 = new DataType ("precipitation");
        DataType type2 =  new DataType ("temperature");

        Sensor sensor1= new Sensor("PrecipitationSensor",rTime1,locationSensor1, type1);
        Sensor sensor2= new Sensor("TemperatureSensor",rTime2,locationSensor2,type2);

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

        DataType type1 = new DataType ("temperature");

        Sensor sensor1= new Sensor("TemperatureSensor1",rTime1,locationSensor1,type1);
        Sensor sensor2= new Sensor("TemperatureSensor2",rTime2,locationSensor2,type1);

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

        DataType type1 = new DataType ("wind");

        Sensor sensor1= new Sensor("WindSensor1",rTime1,locationSensor1,type1);
        Sensor sensor2= new Sensor("WindSensor2",rTime2,locationSensor2,type1);

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

        DataType type1 = new DataType ("wind");

        Sensor sensor1= new Sensor("WindSensor1",rTime1,locationSensor1,type1);
        Sensor sensor2= new Sensor("WindSensor2",rTime2,locationSensor2,type1);

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
    @DisplayName("Check monthly average value in September is 31.7")
    void getMonthlyAverageReadings() {
        //Arrange
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,0), new Location(40, 20,10),type1);



        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 0));

        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);

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
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,0), new Location(40, 20,10),type1);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 0));

        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);

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
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,0), new Location(40, 20,10),type1);

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
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,0), new Location(40, 20,10),type1);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(54, new GregorianCalendar(2018, 10, 20, 11, 0));

        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);

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
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,0), new Location(40, 20,10),type1);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(54, new GregorianCalendar(2018, 10, 20, 11, 0));

        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);

        //Act
        double result = s1.getMonthlyMinimumReading(10);
        double expectedResult = 10;

        //Assert
        assertNotEquals(expectedResult,result);
    }
   @Test
    public void getMonthlyAverageReadingListTest() {
        //Arrange
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",
                new GregorianCalendar(2018,8,1,9,0),
                new Location(40, 20,10),type1);

        Reading r1 = new Reading(50, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(54, new GregorianCalendar(2018, 10, 20, 11, 0));

        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);

        //Act
        double[] result = s1.getMonthlyAverageReadingEachMonth();
        double [] expectedResult = new double[]{Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,50,31.7,33.7,Double.NaN,Double.NaN};

        //Assert
        assertArrayEquals(expectedResult,result);
    }

    @DisplayName("Test the input of 0 in the readings")
    @Test
    public void getMonthlyAverageReadingListTest2() {
        //Arrange
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",
                new GregorianCalendar(2018,8,1,9,0),
                new Location(40, 20,10),type1);

        Reading r1 = new Reading(0, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(13.4, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(50, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(13.4, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(54, new GregorianCalendar(2018, 10, 20, 11, 0));
        Reading r6 = new Reading(0, new GregorianCalendar(2018, 11, 20, 11, 0));

        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);
        s1.addReadingToList(r6);

        //Act
        double[] result = s1.getMonthlyAverageReadingEachMonth();
        double [] expectedResult = new double[]{Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,0,31.7,33.7,0,Double.NaN};

        //Assert
        assertArrayEquals(expectedResult,result);
    }


    @Test
    @DisplayName("Test if minimum monthly average returns 18.5")
    void getMinimumAverageReading() {
        //Arrange
        DataType type1 = new DataType ("wind");
        Sensor s1 = new Sensor("WindSensor", new GregorianCalendar(2018,8,4,11,0),new Location(40,-5,25),type1);
        Reading r1 = new Reading(60.9, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(62.5, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(30.2, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(25.8, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(10.7, new GregorianCalendar(2018, 10, 20, 11, 0));
        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);
        //Act
        double result=s1.getMinimumAverageReading();
        double expectedResult=18.25;
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Test if minimum monthly average does not return zero")
    void getMinimumAverageReading1() {
        //Arrange
        DataType type1 = new DataType ("wind");
        Sensor s1 = new Sensor("WindSensor", new GregorianCalendar(2018,8,4,11,0),new Location(40,-5,25),type1);
        Reading r1 = new Reading(60.9, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(62.5, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(30.2, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(25.8, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(10.7, new GregorianCalendar(2018, 10, 20, 11, 0));
        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);
        //Act
        double result=s1.getMinimumAverageReading();
        double expectedResult=0;
        assertNotEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Test if maximum monthly average returns 18.5")
    void getMaximumAverageReading() {
        //Arrange
        DataType type1 = new DataType ("wind");
        Sensor s1 = new Sensor("WindSensor", new GregorianCalendar(2018,8,4,11,0),new Location(40,-5,25),type1);
        Reading r1 = new Reading(60.9, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(62.5, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(30.2, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(25.8, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(10.7, new GregorianCalendar(2018, 10, 20, 11, 0));
        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);
        //Act
        double result=s1.getMaximumAverageReading();
        double expectedResult=60.9;
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Test if maximum monthly average does not return zero")
    void getMaximumAverageReading1() {
        //Arrange
        DataType type1 = new DataType ("wind");
        Sensor s1 = new Sensor("WindSensor", new GregorianCalendar(2018,8,4,11,0),new Location(40,-5,25),type1);
        Reading r1 = new Reading(30.2, new GregorianCalendar(2018, 8, 4, 11, 0));
        Reading r2 = new Reading(25.8, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r3 = new Reading(60.9, new GregorianCalendar(2018, 9, 4, 11, 0));
        Reading r4 = new Reading(62.5, new GregorianCalendar(2018, 10, 4, 11, 0));
        Reading r5 = new Reading(10.7, new GregorianCalendar(2018, 10, 20, 11, 0));
        s1.addReadingToList(r1);
        s1.addReadingToList(r2);
        s1.addReadingToList(r3);
        s1.addReadingToList(r4);
        s1.addReadingToList(r5);
        //Act
        double result=s1.getMaximumAverageReading();
        double expectedResult=0;
        assertNotEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Comparison of not equal sensors")
    void sensorsComparison() {
        //Arrange
        DataType type1 = new DataType ("visibility");
        Sensor s1 = new Sensor("Visibility Sensor",new GregorianCalendar(2018,8,1,9,0), new Location(40, 20,10),type1);

        DataType type2 = new DataType ("wind speed");
        Sensor s2 = new Sensor("Wind Sensor",new GregorianCalendar(2018,8,1,9,0), new Location(40, 20,10),type2);

        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(type1));
        assertNotEquals(s1.hashCode(),s2.hashCode());
    }



}