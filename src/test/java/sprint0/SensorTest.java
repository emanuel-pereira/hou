package sprint0;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class SensorTest {

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
        GregorianCalendar cL1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Date date2 = cL2.getTime();
        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("PrecipitationSensor",date1,locationSensor1,DataType.PRECIPITATION);
        Sensor sensor2= new Sensor("TemperatureSensor",date2,locationSensor2,DataType.TEMPERATURE);
        double expectedResult=18.708286933869708;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void calculateLinearDistanceBetweenTwoSensorsInTheSamePositionReturnsZero() {
        GregorianCalendar cL1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Date date2 = cL2.getTime();
        Location locationSensor1=new Location(10,10,10);
        Location locationSensor2=new Location(10,10,10);
        Sensor sensor1= new Sensor("TemperatureSensor1",date1,locationSensor1,DataType.TEMPERATURE);
        Sensor sensor2= new Sensor("TemperatureSensor2",date2,locationSensor2,DataType.TEMPERATURE);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsDoesNotReturnZero() {
        GregorianCalendar cL1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Date date2 = cL2.getTime();
        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("WindSensor1",date1,locationSensor1,DataType.WIND);
        Sensor sensor2= new Sensor("WindSensor2",date2,locationSensor2,DataType.WIND);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }

    @Test
    void addReadingValue() {
        GregorianCalendar cL1 = new GregorianCalendar(2018, 11, 27, 9, 0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018, 11, 28, 12, 0);
        Date date2 = cL2.getTime();
        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("WindSensor1",date1,locationSensor1,DataType.WIND);
        Sensor sensor2= new Sensor("WindSensor2",date2,locationSensor2,DataType.WIND);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }



    @Test
    void getLastReadingOfSensorIfThreeReadings() {
        GregorianCalendar cL1 = new GregorianCalendar(2018,11,27,21,0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018,11,27,22,0);
        Date date2 = cL2.getTime();
        GregorianCalendar cL3 = new GregorianCalendar(2018,11,27,23,0);
        Date date3 = cL3.getTime();

        Reading readingDate1 = new Reading (11, date1);
        Reading readingDate2 = new Reading (10, date2);
        Reading expectedReading = new Reading (8, date3);

        Sensor sensor2 = new Sensor ("Temp131");
        sensor2.addReading(readingDate1);
        sensor2.addReading(readingDate2);
        sensor2.addReading(expectedReading);

        Reading result = sensor2.getLastReadingPerSensor();

        assertEquals(expectedReading, result);
    }

    @Test
    void getLastReadingOfSensorIfWrongExpectedResult() {
        GregorianCalendar cL1 = new GregorianCalendar(2018,11,27,21,0);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018,11,27,22,0);
        Date date2 = cL2.getTime();
        GregorianCalendar cL3 = new GregorianCalendar(2018,11,27,23,0);
        Date date3 = cL3.getTime();

        Reading readingDate1 = new Reading (11, date1);
        Reading readingDate2 = new Reading (10, date2);
        Reading expectedReading = new Reading (8, date3);

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
        Date date1 = cL1.getTime();

        Reading expectedReading = new Reading (8, date1);

        Sensor sensor1 = new Sensor ("Temp132");
        sensor1.addReading (expectedReading);

        Reading result = sensor1.getLastReadingPerSensor ();

        assertEquals(expectedReading, result);
    }

}