package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadConfigFileTest {

    @Test
    void getConfigValueTest() {
        ReadConfigFile.setConfigPath("resources/config.properties");

        String expectedResult = "ElectricWaterHeater";
        String result = ReadConfigFile.getConfigValue("devicetype1");

        assertEquals(expectedResult,result);
    }

    @Test
    void getConfigValueTestError() {
        ReadConfigFile.setConfigPath("resources/config.properties");

        String expectedResult = "ERROR";
        String result = ReadConfigFile.getConfigValue("blah");

        assertEquals(expectedResult,result);
    }

    @Test
    void getConfigValueTestErrorFileNotFound() {
        ReadConfigFile.setConfigPath("resources/config.prolongies");

        String expectedResult = "ERROR";
        String result = ReadConfigFile.getConfigValue("devicetype1");

        assertEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestTrue() {
        ReadConfigFile.setConfigPath("resources/config.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertTrue(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLow() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooLow.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHigh() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooHigh.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }


    @Test
    void isMeteringPeriodValidTestFailValueNotMultiple() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseNotMultiple.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void getMeteringPeriodTestFailValueNotAnInt() {
        ReadConfigFile.setConfigPath("resources/configFiles/configFalseNotAnInt.properties");

        int expectedResult = -1;
        int result = ReadConfigFile.getMeteringPeriod("MeteringPeriod");

        assertEquals(expectedResult,result);
    }

    @Test
    void getGridMeteringPeriodTest() {
        ReadConfigFile.setConfigPath("resources/config.properties");

        int expectedResult = 10;
        int result = ReadConfigFile.getGridMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDevicesMeteringPeriod() {
        ReadConfigFile.setConfigPath("resources/config.properties");

        int expectedResult = 10;
        int result = ReadConfigFile.getDevicesMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceTypes() {
        ReadConfigFile.setConfigPath("resources/config.properties");

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater","WashingMachine","Dishwasher","Fridge","Kettle","Oven","Stove","MicrowaveOven","WallElectricHeater","PortableElectricOilHeater","PortableElectricConvectionHeater","WallTowelHeater","Lamp","Television");
        List<String> result = ReadConfigFile.getDeviceTypes();

        assertEquals(expectedResult,result);

    }

    @Test
    void getDeviceTypesFailValueNotAnInt() {
        ReadConfigFile.setConfigPath("resources/configFiles/configFalseNotAnInt.properties");

        List<String> expectedResult = Arrays.asList();
        List<String> result = ReadConfigFile.getDeviceTypes();

        assertEquals(expectedResult,result);

    }

}