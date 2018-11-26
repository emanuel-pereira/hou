package Sprint_0;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class SensorTest {

    @Test
    public void testIfGet_DesignationReturnsNullBeforeUserInputtingAnyDesignation(){
        Sensor tempSensor= new Sensor(null);
        String expectedResult=null;
        String result;
        result=tempSensor.get_designation();
        assertEquals(expectedResult,result);
    }


    @Test
    public void testIfRenamingASensorWithAnEmptyStringIsIgnored(){
        Sensor tempSensor= new Sensor("Sensor01TempMat");
        String designation= "";
        String expectedResult="Sensor01TempMat";
        String result;
        tempSensor.set_designation(designation);
        result=tempSensor.get_designation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingASensorWithValidStringUpdatesSensorDesignation(){
        Sensor tempSensor= new Sensor("");
        String designation= "SensorVisibilityLisbon";
        String expectedResult="SensorVisibilityLisbon";
        String result;
        tempSensor.set_designation(designation);
        result=tempSensor.get_designation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingSensorWithInvalidStringDoesNotChangeSensorOriginalDesignation(){
        Sensor tempSensor= new Sensor("WindSensorSantarem");
        String designation= "Wind_SensorSantarém";
        String expectedResult="WindSensorSantarem";
        String result;
        tempSensor.set_designation(designation);
        result=tempSensor.get_designation();
        assertEquals(expectedResult,result);
    }

    @Test
    public void testIfRenamingSensorWithValidStringDoesNotReturnTheOriginalStringDesignation(){
        Sensor tempSensor= new Sensor("WindSensorSantarem");
        String designation= "WindSensorLisboa";
        String expectedResult="WindSensorSantarem";
        String result;
        tempSensor.set_designation(designation);
        result=tempSensor.get_designation();
        assertNotEquals(expectedResult,result);
    }



}