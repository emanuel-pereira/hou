package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class SensorTest {

   @Test
    public void testIfGet_DesignationReturnsNullBeforeUserInputtingAnyDesignation(){
        Sensor tempSensor= new Sensor("");
        String expectedResult="";
        String result;
        result=tempSensor.getmDesignation();
        assertEquals(expectedResult,result);
    }


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
        DataType newDataType = new DataType("Wind");
        DataType expectedResult= newDataType;
        DataType result;
        visibilitySensor.setmDataTypeDesignation(newDataType);
        result=visibilitySensor.getmDataTypeDesignation();
        assertEquals(expectedResult,result);
   }
    @Test
    void checkIfSetAndGetMethodReturnsSecondUpdateOfDataTypeDesignation() {
        Sensor visibilitySensor=new Sensor("SensorOfViseu");
        DataType dataType1= new DataType("Rainfall");
        DataType dataType2 = new DataType("");
        DataType expectedResult= dataType2;
        DataType result;
        visibilitySensor.setmDataTypeDesignation(dataType1);
        visibilitySensor.setmDataTypeDesignation(dataType2);
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

    // FUNCIONALIDADE 3 - TESTES

    @Test
    void checkifgetReadList() {




    }

}