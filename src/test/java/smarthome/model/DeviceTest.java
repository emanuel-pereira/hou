package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    @Test
    void getNominalPower() {
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 0.8);

        double expected = 0.8;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getDeviceSpecs() returns fridge as Device Specs")
    void getDeviceSpecs() {
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);

        DeviceSpecs expectedResult = dFridge.getDeviceSpecs();
        Fridge result = fridge;

        assertEquals(expectedResult, result);
    }

   /* @Test
    void setDeviceAttributesTest() {

        OtherDevices stove= new OtherDevices(DeviceType.STOVE);
        Device d1 = new Device("device1",stove, 40);
        d1.setName("TV");
        d1.setNominalPower(47);

        String result = d1.showDeviceAttributesInString();
        String expected = "1 - Device Name : TV\n" +
                "2 - Device Room : Living Room\n" +
                "3 - Device Nominal Power : 47.0\n";
        assertEquals(expected, result);
    }*/

   /* @Test
    void showDeviceSpecsListAttributesInString() {
        Lamp lamp = new Lamp(DeviceType.LAMP,6);
        Device d1 = new Device("Lamp1", lamp, 40);
        String result = d1.showDeviceAttributesInString();
        String expected = "1 - Device Name : Lamp1\n" +
                "2 - Device Room : Living Room\n" +
                "3 - Device Nominal Power : 40.0\n" +
                "4 - Luminous Flux : 6\n";
        assertEquals(expected, result);


    }*/

    @Test
    void setAttributeValue() {
        Lamp lamp = new Lamp(DeviceType.LAMP, 6);
        Device d1 = new Device("Lamp1", lamp, 40);
        String name = "2 - Device Name : " + d1.getName();
        d1.setAttributeValue(name,"Lamp 55");
        assertEquals("Lamp 55", d1.getName());
        String nominalPower = "3 - Device Nominal Power : " + d1.getNominalPower();
        d1.setAttributeValue(nominalPower, "50");
        assertEquals(50, d1.getNominalPower());
    }



    /*@Test
    void getEnergyConsumption() {
        Room kitchen= new Room("Kitchen",0,5,2,3);
        ElectricWaterHeater ewh= new ElectricWaterHeater(65,1);
        ewh.setColdWaterTemperature(20);
        ewh.setVolumeOfWater(65);
        Device device = new Device("Daikin EWH",ewh,kitchen,7,DeviceType.ELECTRIC_WATER_HEATER);
        double expected=0;
        double result=device.getEnergyConsumption();
        assertEquals(expected,result);


    }*/
    }