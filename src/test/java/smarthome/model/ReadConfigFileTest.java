package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadConfigFileTest {

    @Test
    void getConfigValueTest() {

        String expectedResult = "Electric Water Heater";
        String result = ReadConfigFile.getConfigValue("devicetype1");

        assertEquals(expectedResult,result);
    }

    @Test
    void getConfigValueTestError() {
        String expectedResult = "ERROR";
        String result = ReadConfigFile.getConfigValue("b");

        assertEquals(expectedResult,result);
    }

    @Test
    void getGridMeteringPeriodTest() {


        int expectedResult = 30;
        int result = ReadConfigFile.getGridMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDevicesMeteringPeriod() {


        int expectedResult = 10;
        int result = ReadConfigFile.getDevicesMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceTypes() {

        List<String> expectedResult = Arrays.asList("Electric Water Heater","Washing Machine","Dishwasher","Fridge","Kettle","Oven","Stove","Microwave Oven","Wall Electric Heater","Portable Electric Oil Heater","Portable Electric Convection Heater","Wall Towel Heater","Lamp","Television");
        List<String> result = ReadConfigFile.getDeviceTypes();

        assertEquals(expectedResult,result);

    }
}