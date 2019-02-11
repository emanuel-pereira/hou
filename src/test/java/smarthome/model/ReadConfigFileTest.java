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
    void isMeteringPeriodValidTestFailValueTooLowGrid() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooLowGrid.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLowGridNotEquals() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooLowGrid.properties");

        boolean expectedResult = true;
        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLowDevice() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooLowDevice.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLowDeviceNotEquals() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooLowDevice.properties");

        boolean expectedResult = true;
        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighGrid() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooHighGrid.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighGridNotEquals() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooHighGrid.properties");

        boolean expectedResult = true;
        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighDevice() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooHighDevice.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighDeviceNotEquals() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseTooHighDevice.properties");

        boolean expectedResult = true;
        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }


    @Test
    void isMeteringPeriodValidTestFailValueNotMultiple() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseNotMultiple.properties");

        boolean result = ReadConfigFile.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueNotMultipleOF1440Device() {
        ReadConfigFile.setConfigPath("resources/configFilesForTests/configFalseNotMultipleOf1440.properties");

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

    @Test
    void getDeviceTypesFailValueTooLowTotalDevices() {
        ReadConfigFile.setConfigPath("resources/configFiles/configFalseTooLowTotal.properties");

        List<String> expectedResult = Arrays.asList();
        List<String> result = ReadConfigFile.getDeviceTypes();

        assertEquals(expectedResult,result);

    }

}