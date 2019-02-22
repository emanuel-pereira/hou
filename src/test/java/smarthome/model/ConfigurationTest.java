package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationTest {


    @Test
    void getGridMeteringPeriodTest() {
        Configuration.getGridMeteringPeriod();

        int expectedResult = 10;
        int result = Configuration.getGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDevicesMeteringPeriod() {
        Configuration.getDevicesMeteringPeriod();

        int expectedResult = 10;
        int result = Configuration.getDevicesMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceTypes() {
        Configuration.getDeviceTypes();

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater", "WashingMachine", "Dishwasher", "Fridge", "Kettle", "Oven", "Stove", "MicrowaveOven", "WallElectricHeater", "PortableElectricOilHeater", "PortableElectricConvectionHeater", "WallTowelHeater", "Lamp", "Television");
        List<String> result = Configuration.getDeviceTypes();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceSpecsAttributesTest() {

        List<String> expectedResult = Arrays.asList("Freezer Capacity", "Refrigerator Capacity");
        List<String> result = Configuration.getDeviceSpecsAttributes("Fridge");

        assertEquals(expectedResult, result);
    }
}

