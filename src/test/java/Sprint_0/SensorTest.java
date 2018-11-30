package Sprint_0;

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
        tempSensor.setmDesignation(designation);
        result=tempSensor.getmDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor("");
        String designation= "SensorVisibilityLisbon";
        String expectedResult="SensorVisibilityLisbon";
        String result;
        tempSensor.setmDesignation(designation);
        result=tempSensor.getmDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIf1RenamingASensorWithValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor("SensorVisibilityLisbon");
        String designation= null;
        String expectedResult="SensorVisibilityLisbon";
        String result;
        tempSensor.setmDesignation(designation);
        result=tempSensor.getmDesignation();
        assertEquals(expectedResult,result);
    }

@Test
    public void testIfRenamingASensorWith1ValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor("SensorVisibilityLisboa");
        String designation= "SensorVisibilityLisbonnnnnnnnnnnnnnnnnnnn";
        String expectedResult="SensorVisibilityLisboa";
        String result;
        tempSensor.setmDesignation(designation);
        result=tempSensor.getmDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingSensorWithInvalidStringDoesNotChangeSensorOriginalDesignation(){
        Sensor tempSensor= new Sensor("WindSensorSantarem");
        String designation= "Wind_SensorSantarém";
        String expectedResult="WindSensorSantarem";
        String result;
        tempSensor.setmDesignation(designation);
        result=tempSensor.getmDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation(){
        Sensor tempSensor= new Sensor("WindSensorSantarem");
        String designation= "WindSensorLisboa";
        String expectedResult="WindSensorSantarem";
        String result;
        tempSensor.setmDesignation(designation);
        result=tempSensor.getmDesignation();
        assertNotEquals(expectedResult,result);
    }

    @Test
    void checkIfGetLocationReturnsNullBeforeAnyValueIsInputted() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfNowhere");
        Location expectedResult= null;
        Location result;
        result=rainfallSensor.getmLocation();
        assertEquals(expectedResult,result);}


    @Test
    void checkIfLocationOfRainfallSensorIsUpdated() {
        Sensor rainfallSensor = new Sensor("RainfallSensorOfPorto");
        Location loc1 = new Location(30,-12,62);
        Location expectedResult= loc1;
        Location result;
        rainfallSensor.setmLocation(loc1);
        result=rainfallSensor.getmLocation();
        assertEquals(expectedResult,result);
    }


    @Test
    void checkIfGetDataTypeMethodReturnsDataTypeDefault() {
        Sensor visibilitySensor=new Sensor("WindSensorOfCoimbra");
        DataType expectedResult= null;
        DataType result;
        result=visibilitySensor.getmDataTypeDesignation();
        assertEquals(expectedResult,result);
    }

   @Test
    void checkIfSetAndGetDataTypeMethodUpdatesSensorDataTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfCoimbra");
       DataType newType = DataType.PRECIPITATION;
        DataType expectedResult= newType;
        DataType result;
        visibilitySensor.setmDataTypeDesignation(newType);
        result=visibilitySensor.getmDataTypeDesignation();
        assertEquals(expectedResult,result);

   }

    @Test
    void checkIfSetAndGetMethodReturnsSecondUpdateOfDataTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfViseu");
        DataType oldType = DataType.HUMIDITY;
        DataType newType = DataType.PRECIPITATION;
        DataType expectedResult= newType;
        DataType result;
        visibilitySensor.setmDataTypeDesignation(oldType);
        visibilitySensor.setmDataTypeDesignation(newType);
        result=visibilitySensor.getmDataTypeDesignation();
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsReturnsExpectedResult() {
        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("RainfallSensor1",locationSensor1);
        Sensor sensor2= new Sensor("RainfallSensor2",locationSensor2);
        double expectedResult=18.708286933869708;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void calculateLinearDistanceBetweenTwoSensorsInTheSamePositionReturnsZero() {
        Location locationSensor1=new Location(10,10,10);
        Location locationSensor2=new Location(10,10,10);
        Sensor sensor1= new Sensor("TemperatureSensor1",locationSensor1);
        Sensor sensor2= new Sensor("TemperatureSensor2",locationSensor2);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfCalculateLinearDistanceBetweenTwoSensorsDoesNotReturnZero() {
        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("WindSensor1",locationSensor1);
        Sensor sensor2= new Sensor("WindSensor2",locationSensor2);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }

    @Test
    void addReadingValue() {
        Location locationSensor1=new Location(20,10,15);
        Location locationSensor2=new Location(30,25,20);
        Sensor sensor1= new Sensor("WindSensor1",locationSensor1);
        Sensor sensor2= new Sensor("WindSensor2",locationSensor2);
        double expectedResult=0;
        double result=sensor1.calcLinearDistanceBetweenTwoSensors(sensor1,sensor2);
        assertNotEquals(expectedResult,result);
    }

    //FUNCIONALIDADE 3

    @Test
    void getLastReadingOfSensor() {
        GregorianCalendar cL1 = new GregorianCalendar(2018,11,27,21,00);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018,11,27,20,00);
        Date date2 = cL2.getTime();
        GregorianCalendar cL3 = new GregorianCalendar(2018,11,27,20,00);
        Date date3 = cL3.getTime();

        Reading readingDate1 = new Reading (11, date1);
        Reading readingDate2 = new Reading (10, date2);
        Reading expectedReading = new Reading (8, date3);

        Sensor sensor1 = new Sensor ("Temp131");
        sensor1.addReadingValue (readingDate1);
        sensor1.addReadingValue (readingDate2);
        sensor1.addReadingValue (expectedReading);

        Reading result = sensor1.getListReadingLastValuePerSensor (sensor1);

        assertEquals(expectedReading, result);
    }

    @Test
    void getLastReadingOfSensorIfWrongExpectedResult() {
        GregorianCalendar cL1 = new GregorianCalendar(2018,11,27,21,00);
        Date date1 = cL1.getTime();
        GregorianCalendar cL2 = new GregorianCalendar(2018,11,27,20,00);
        Date date2 = cL2.getTime();
        GregorianCalendar cL3 = new GregorianCalendar(2018,11,27,20,00);
        Date date3 = cL3.getTime();

        Reading readingDate1 = new Reading (11, date1);
        Reading readingDate2 = new Reading (10, date2);
        Reading expectedReading = new Reading (8, date3);

        Sensor sensor1 = new Sensor ("Temp131");
        sensor1.addReadingValue (readingDate1);
        sensor1.addReadingValue (readingDate2);
        sensor1.addReadingValue (expectedReading);

        Reading result = sensor1.getListReadingLastValuePerSensor (sensor1);

        assertNotEquals(readingDate2, result);
    }

}