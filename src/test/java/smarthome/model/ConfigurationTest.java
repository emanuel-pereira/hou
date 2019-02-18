package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void getConfigValueTest() {
        Configuration.setConfigPath("resources/config.properties");

        String expectedResult = "ElectricWaterHeater";
        String result = Configuration.getConfigValue("devicetype1");

        assertEquals(expectedResult,result);
    }

    @Test
    void getConfigValueTestError() {
        Configuration.setConfigPath("resources/config.properties");

        String expectedResult = "ERROR";
        String result = Configuration.getConfigValue("blah");

        assertEquals(expectedResult,result);
    }

    @Test
    void getConfigValueTestErrorFileNotFound() {
        Configuration.setConfigPath("resources/config.prolongies");

        String expectedResult = "ERROR";
        String result = Configuration.getConfigValue("devicetype1");

        assertEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestTrue() {
        Configuration.setConfigPath("resources/config.properties");

        boolean result = Configuration.isMeteringPeriodValid();

        assertTrue(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLowGrid() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooLowGrid.properties");

        boolean result = Configuration.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLowGridNotEquals() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooLowGrid.properties");

        boolean expectedResult = true;
        boolean result = Configuration.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLowDevice() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooLowDevice.properties");

        boolean result = Configuration.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooLowDeviceNotEquals() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooLowDevice.properties");

        boolean expectedResult = true;
        boolean result = Configuration.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighGrid() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooHighGrid.properties");

        boolean result = Configuration.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighGridNotEquals() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooHighGrid.properties");

        boolean expectedResult = true;
        boolean result = Configuration.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighDevice() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooHighDevice.properties");

        boolean result = Configuration.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueTooHighDeviceNotEquals() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseTooHighDevice.properties");

        boolean expectedResult = true;
        boolean result = Configuration.isMeteringPeriodValid();

        assertNotEquals(expectedResult,result);
    }


    @Test
    void isMeteringPeriodValidTestFailValueNotMultiple() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseNotMultiple.properties");

        boolean result = Configuration.isMeteringPeriodValid();

        assertFalse(result);
    }

    @Test
    void isMeteringPeriodValidTestFailValueNotMultipleOF1440Device() {
        Configuration.setConfigPath("resources/configFilesForTests/configFalseNotMultipleOf1440.properties");

        boolean result = Configuration.isMeteringPeriodValid();

        assertFalse(result);
    }


    @Test
    void getMeteringPeriodTestFailValueNotAnInt() {
        Configuration.setConfigPath("resources/configFiles/configFalseNotAnInt.properties");

        int expectedResult = -1;
        int result = Configuration.getMeteringPeriod("MeteringPeriod");

        assertEquals(expectedResult,result);
    }

    @Test
    void getGridMeteringPeriodTest() {
        Configuration.setConfigPath("resources/config.properties");

        int expectedResult = 10;
        int result = Configuration.getGridMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDevicesMeteringPeriod() {
        Configuration.setConfigPath("resources/config.properties");

        int expectedResult = 10;
        int result = Configuration.getDevicesMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceTypes() {
        Configuration.setConfigPath("resources/config.properties");

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater","WashingMachine","Dishwasher","Fridge","Kettle","Oven","Stove","MicrowaveOven","WallElectricHeater","PortableElectricOilHeater","PortableElectricConvectionHeater","WallTowelHeater","Lamp","Television");
        List<String> result = Configuration.getDeviceTypes();

        assertEquals(expectedResult,result);

    }

    @Test
    void getDeviceTypesFailValueNotAnInt() {
        Configuration.setConfigPath("resources/configFiles/configFalseNotAnInt.properties");

        List<String> expectedResult = Arrays.asList();
        List<String> result = Configuration.getDeviceTypes();

        assertEquals(expectedResult,result);

    }

    @Test
    void getDeviceTypesFailValueTooLowTotalDevices() {
        Configuration.setConfigPath("resources/configFiles/configFalseTooLowTotal.properties");

        List<String> expectedResult = Arrays.asList();
        List<String> result = Configuration.getDeviceTypes();

        assertEquals(expectedResult,result);

    }

}