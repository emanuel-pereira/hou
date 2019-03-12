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

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater", "WashingMachine", "Dishwasher", "Fridge", "Kettle", "Oven", "Stove", "MicrowaveOven", "WallElectricHeater", "PortableElectricOilHeater", "PortableElectricConvectionHeater", "WallTowelHeater", "Lamp", "Tv");
        List<String> result = c.getDeviceTypes();

        assertEquals(expectedResult, result);
    }


    @Test
    void getGridMeteringPeriodTestFailNotAnInt() {
        Configuration c = new Configuration("smarthome/configFalseNotAnInt.properties");
        c.getGridMeteringPeriod();

        int expectedResult = -1;
        int result = c.getGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getDevicesMeteringPeriodFailNNotMultipleOf1440() {

        Configuration c = new Configuration("smarthome/configFalseNotMultipleOf1440.properties");
        c.getDevicesMeteringPeriod();

        int expectedResult = -1;
        int result = c.getDevicesMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    void getGridMeteringPeriodTestFailNotMultipleOf1440() {
        Configuration c = new Configuration("smarthome/configFalseNotMultipleOf1440.properties");
        c.getGridMeteringPeriod();

        int expectedResult = -1;
        int result = c.getGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }


    @Test
    void getDeviceTypesFAIL() {

        Configuration c = new Configuration("smarthome/configFalseNotAnInt.properties");

        //TODO arrays returns empty as the currentDevice is the one that equals(ERROR) given so the array returns empty
        //List<String> expectedResult = Arrays.asList("ERROR");
        List<String> expectedResult = Arrays.asList();
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

