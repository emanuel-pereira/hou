package smarthome.model;

import org.junit.jupiter.api.Test;

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

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater", "WashingMachine", "Dishwasher", "Fridge", "Kettle", "Oven", "Stove", "MicrowaveOven", "WallElectricHeater", "PortableElectricOilHeater", "PortableElectricConvectionHeater", "WallTowelHeater", "Lamp", "Television");
        List<String> result = c.getDeviceTypes();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceSpecsAttributesTest() {
        Configuration c = new Configuration();
        List<String> expectedResult = Arrays.asList("Freezer Capacity", "Refrigerator Capacity");
        List<String> result = c.getDeviceSpecsAttributes("Fridge");

        assertEquals(expectedResult, result);
    }

    @Test
    void getGridMeteringPeriodTestFAIL() {
        Configuration c = new Configuration("smarthome/configFalseNotAnInt.properties");
        c.getGridMeteringPeriod();

        int expectedResult = -1;
        int result = c.getGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDevicesMeteringPeriodFAIL() {

        Configuration c = new Configuration("smarthome/configFalseNotAnInt.properties");
        c.getDevicesMeteringPeriod();

        int expectedResult = -1;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceTypesFAIL() {

        Configuration c = new Configuration("smarthome/configFalseNotAnInt.properties");
        c.getDeviceTypes();

        List<String> expectedResult = Arrays.asList("ERROR");
        List<String> result = c.getDeviceTypes();

        assertEquals(expectedResult, result);
    }

    @Test
    void fileNotFound(){

        Configuration c = new Configuration("smarthome/nofile.properties");
        c.getDevicesMeteringPeriod();

        int expectedResult = -1;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expectedResult, result);
    }

}

