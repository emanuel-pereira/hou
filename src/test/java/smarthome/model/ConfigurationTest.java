package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationTest {


    @Test
    void getGridMeteringPeriodTest() {
        Configuration c = new Configuration();
        c.getGridMeteringPeriod();

        int expectedResult = 10;
        int result = c.getGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDevicesMeteringPeriod() {

        Configuration c = new Configuration();
        c.getDevicesMeteringPeriod();

        int expectedResult = 10;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceTypes() {
        Configuration c = new Configuration();
        c.getDeviceTypes();

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater", "WashingMachine", "Dishwasher", "Fridge", "Freezer", "WineCooler", "Kettle", "Oven", "Stove", "MicrowaveOven", "WallElectricHeater", "PortableElectricOilHeater", "PortableElectricConvectionHeater", "WallTowelHeater", "Lamp", "Fan", "Tv");
        List<String> result = c.getDeviceTypes();

        assertEquals(expectedResult, result);
    }


    @Test
    void getGridMeteringPeriodTestFailNotAnInt() {
        Configuration c = new Configuration("resources/configFilesForTests/configFalseNotAnInt.properties");
        c.getGridMeteringPeriod();

        int expectedResult = -1;
        int result = c.getGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDevicesMeteringPeriodFailNNotMultipleOf1440() {

        Configuration c = new Configuration("resources/configFilesForTests/configFalseNotMultipleOf1440.properties");
        c.getDevicesMeteringPeriod();

        int expectedResult = -1;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getGridMeteringPeriodTestFailNotMultipleOf1440() {
        Configuration c = new Configuration("resources/configFilesForTests/configFalseNotMultipleOf1440.properties");
        c.getGridMeteringPeriod();

        int expectedResult = -1;
        int result = c.getGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }


    @Test
    void getDeviceTypesFAIL() {

        Configuration c = new Configuration("resources/configFilesForTests/configFalseNotAnInt.properties");

        List<String> expectedResult = new ArrayList<>();

        List<String> result = c.getDeviceTypes();

        assertEquals(expectedResult, result);
    }

    @Test
    void fileNotFound() {

        Configuration c = new Configuration("resources/nofile.properties");
        c.getDevicesMeteringPeriod();

        int expectedResult = -1;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expectedResult, result);


    }

    @Test
    void checkBoundaries_A() {

        Configuration c = new Configuration("resources/configFilesForTests/configLimits.properties");

        int expected = -1;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expected, result);
    }

    @Test
    void checkBoundaries_B() {

        Configuration c = new Configuration("resources/configFilesForTests/configLimits.properties");

        int expected = -1;
        int result = c.getGridMeteringPeriod();

        assertEquals(expected, result);
    }

    @Test
    void checkBoundaries_C() {

        Configuration c = new Configuration("resources_tests/configFilesForTests/config_DMP1440_GMP1.properties");

        int expected = 1440;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expected, result);
    }

    @Test
    void checkBoundaries_D() {

        Configuration c = new Configuration("resources_tests/configFilesForTests/config_DMP1440_GMP1.properties");

        int expected = 1;
        int result = c.getGridMeteringPeriod();

        assertEquals(expected, result);
    }


}

