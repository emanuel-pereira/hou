package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    @Test
    @DisplayName("Ensure getRoom method from microwave returns kitchen")
    void getRoom() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Device microwave = new Device("Samsung Microwave", DeviceType.MICROWAVE_OVEN, kitchen, 0.8);

        Room expected = kitchen;
        Room result = microwave.getRoom();

        assertEquals(expected, result);
    }

    @Test
    void getNominalPower() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Device microwave = new Device("Samsung Microwave", DeviceType.MICROWAVE_OVEN, kitchen, 0.8);

        double expected = 0.8;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getDeviceSpecs() returns fridge as Device Specs")
    void getDeviceSpecs() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, kitchen, 1.5, DeviceType.FRIDGE);

        DeviceSpecs expectedResult = dFridge.getDeviceSpecs();
        Fridge result = fridge;

        assertEquals(expectedResult, result);
    }

    @Test
    void setDeviceAttributesTest() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Room livingRoom = new Room("Living Room", 0, 10, 20, 2.5);
        Device d1 = new Device("device1", DeviceType.STOVE, kitchen, 40);
        d1.setDeviceName("TV");
        d1.setRoom(livingRoom);
        d1.setNominalPower(47);

        String result = d1.showDeviceAttributesInString();
        String expected = "1 - Device Name : TV\n" +
                "2 - Device Room : Living Room\n" +
                "3 - Device Nominal Power : 47.0\n";
        assertEquals(expected, result);
    }

    @Test
    void showDeviceSpecsListAttributesInString() {
        Room livingRoom = new Room("Living Room", 0, 10, 20, 2.5);
        Lamp lamp = new Lamp(6);
        Device d1 = new Device("Lamp1", lamp, livingRoom, 40, DeviceType.LAMP);
        String result = d1.showDeviceAttributesInString();
        String expected = "1 - Device Name : Lamp1\n" +
                "2 - Device Room : Living Room\n" +
                "3 - Device Nominal Power : 40.0\n" +
                "4 - Luminous Flux : 6\n";
        assertEquals(expected, result);


    }

    @Test
    void setAttributeValue() {
        Room livingRoom = new Room("Living Room", 0, 10, 20, 2.5);
        Lamp lamp = new Lamp(6);
        Device d1 = new Device("Lamp1", lamp, livingRoom, 40, DeviceType.LAMP);
        String expected = "1 - Device Name : Lamp1\n" +
                "2 - Device Room : Living Room\n" +
                "3 - Device Nominal Power : 40.0\n" +
                "4 - Luminous Flux : 6\n";
        d1.setAttributeValue("3 - Device Nominal Power : " +d1.getNominalPower(), "50");
        assertEquals(50,d1.getNominalPower());
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