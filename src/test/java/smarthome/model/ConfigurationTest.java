package smarthome.model;

import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {


    @Test
    void getGridMeteringPeriodTest() {
        Configuration.init("resources/config.properties",false);

        int expectedResult = 10;
        int result = Configuration.getGridMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDevicesMeteringPeriod() {
        Configuration.init("resources/config.properties",false);

        int expectedResult = 10;
        int result = Configuration.getDevicesMeteringPeriod();

        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceTypes() {
        Configuration.init("resources/config.properties",false);

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater","WashingMachine","Dishwasher","Fridge","Kettle","Oven","Stove","MicrowaveOven","WallElectricHeater","PortableElectricOilHeater","PortableElectricConvectionHeater","WallTowelHeater","Lamp","Television");
        List<String> result = Configuration.getDeviceTypes();

        assertEquals(expectedResult,result);

    }

    @Test
    void getDeviceTypesFailValueNotAnInt() {
        Configuration.init("resources/configFiles/configFalseNotAnInt.properties",false);

        int expectedResult = -1;
        int result = Configuration.getDevicesMeteringPeriod();
        assertEquals(expectedResult,result);

    }



    @Test
    void checkIfLockIsWorking(){
        Configuration.init(true); // lock the class
        Configuration.init("resources/configFiles/configFalseTooLowTotal.properties",false);

        List<String> expectedResult = Arrays.asList("ElectricWaterHeater","WashingMachine","Dishwasher","Fridge","Kettle","Oven","Stove","MicrowaveOven","WallElectricHeater","PortableElectricOilHeater","PortableElectricConvectionHeater","WallTowelHeater","Lamp","Television");
        List<String> result = Configuration.getDeviceTypes();

        assertEquals(expectedResult,result);
    }

}