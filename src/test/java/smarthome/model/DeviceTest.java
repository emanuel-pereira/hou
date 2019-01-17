package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    @Test
    @DisplayName("Ensure getRoom method from microwave returns kitchen")
    void getRoom() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices otherDevices = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", otherDevices, kitchen, 0.8,DeviceType.MICROWAVE_OVEN);

        Room expected = kitchen;
        Room result = microwave.getRoom();

        assertEquals(expected, result);
    }

    @Test
    void getNominalPower() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices otherDevices = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", otherDevices, kitchen, 0.8,DeviceType.MICROWAVE_OVEN);

        double expected = 0.8;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getDeviceSpecs() returns fridge as Device Specs")
    void getDeviceSpecs() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, kitchen, 1.5,DeviceType.FRIDGE);

        DeviceSpecs expectedResult = dFridge.getDeviceSpecs();
        Fridge result = fridge;

        assertEquals(expectedResult, result);
    }

    @Test
    void setDeviceAttributesTest() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Room livingRoom = new Room("Living Room", 0, 10, 20, 2.5);
        OtherDevices otherDevices = new OtherDevices();
        Device d1 = new Device("device1", otherDevices, kitchen, 40,DeviceType.STOVE);
        d1.setDeviceName("TV");
        d1.setRoom(livingRoom);
        d1.setNominalPower(47);

        String result = d1.showDeviceListAttributesInString();
        String expected = "1 - Device name : " + d1.getName() + "\n2 - Device room : " + d1.getRoom().getName() + "\n3 - Nominal Power : " +d1.getNominalPower()+ " kW\n";
        assertEquals(expected, result);
    }
    @Test
    void showDeviceSpecsListAttributesInString(){
        Room livingRoom = new Room("Living Room", 0, 10, 20, 2.5);
        Lamp lamp = new Lamp(6);
        Device d1 = new Device("Lamp1", lamp,livingRoom, 40,DeviceType.LAMP);
        String result = d1.showDeviceSpecsListAttributesInString();
        String expected = "4 - Luminous Flux : 6";
        assertEquals(expected,result);



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