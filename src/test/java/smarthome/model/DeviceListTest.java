package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    @DisplayName("Ensure same device instance is only added once to device list")
    void addDevice() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices otherDevices = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", otherDevices, kitchen, 0.8, DeviceType.MICROWAVE_OVEN);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(microwave);
        int expectedResult1 = 1;
        int result1 = deviceList.getDeviceList().size();
        assertEquals(expectedResult1, result1);

        deviceList.addDevice(microwave);
        int expectedResult2 = 1;
        int result2 = deviceList.getDeviceList().size();
        assertEquals(expectedResult2, result2);
    }

    @Test
    @DisplayName("Ensure newDevice method creates local instance of device with specs and that addDevice method adds it deviceList")
    void newDeviceWithSpecs() {
        DeviceList deviceList = new DeviceList();
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Fridge fridge = new Fridge(50, 350, 50);
        Device device = deviceList.newDevice("LG Fridge", DeviceType.FRIDGE, fridge, kitchen, 1.2);
        deviceList.addDevice(device);

        Device expected = device;
        Device result = deviceList.getDeviceList().get(0);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getLastElement().getName() returns last element from device list and respective device name")
    void getLastElement() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices otherDevices = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", otherDevices, kitchen, 0.8, DeviceType.MICROWAVE_OVEN);
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, kitchen, 1.5, DeviceType.FRIDGE);

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(microwave);
        deviceList.addDevice(dFridge);
        String expectedResult = deviceList.getLastElement().getName();
        String result = "LG Fridge";

        assertEquals(expectedResult, result);
    }

    @Test
    void showDeviceListInString() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList deviceList = new DeviceList();
        OtherDevices otherDevices = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", otherDevices, kitchen, 0.8, DeviceType.MICROWAVE_OVEN);
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, kitchen, 1.5, DeviceType.FRIDGE);
        deviceList.addDevice(microwave);
        deviceList.addDevice(dFridge);
        String expected = "1 - Device: Samsung Microwave | Type: " + microwave.getDeviceType() + "\n2 - Device: LG Fridge | Type: " + dFridge.getDeviceType() + "\n";
        String result = deviceList.showDeviceListInString();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that electric water list has 2 electric water heaters in a global device list containing another device of other type")
    void getElectricWaterHeaterList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3, DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, kitchen, 2.5, DeviceType.ELECTRIC_WATER_HEATER);
        Device stove = new Device("Stove 1", DeviceType.STOVE, kitchen, 1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        deviceList.addDevice(stove);
        int expected = 2;
        int result = deviceList.getElectricWaterHeaterList().getDeviceList().size();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure t")
    void getEnergyConsumptionOfEWHList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3, DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, kitchen, 2.5, DeviceType.ELECTRIC_WATER_HEATER);
        Device stove = new Device("Stove 1", DeviceType.STOVE, kitchen, 1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        deviceList.addDevice(stove);
        deviceList.setVolumeOfWaterEWHList(65);
        deviceList.setColdWaterTemperatureEWHList(20);
        double expected = 146956.68;
        double result = deviceList.getEnergyConsumptionOfEWHList();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that volume of water is set to all devices of type Electric Water Heater")
    void setVolumeOfWaterEWHList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3, DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, kitchen, 2.5, DeviceType.ELECTRIC_WATER_HEATER);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        deviceList.setVolumeOfWaterEWHList(65);

        double expected = 65;
        double resultEWH1 = ((ElectricWaterHeater) dEWH1.getDeviceSpecs()).getVolumeOfWater();
        assertEquals(expected, resultEWH1);

        double resultEWH2 = ((ElectricWaterHeater) dEWH2.getDeviceSpecs()).getVolumeOfWater();
        assertEquals(expected, resultEWH2);
    }

    @Test
    @DisplayName("Ensure that cold water temperature is set to all devices of type Electric Water Heater")
    void setColdWaterTemperatureEWHList() {

        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(65, 1);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3, DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, kitchen, 2.5, DeviceType.ELECTRIC_WATER_HEATER);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        deviceList.setColdWaterTemperatureEWHList(25);

        double expected = 25;
        double resultEWH1 = ((ElectricWaterHeater) dEWH1.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected, resultEWH1);

        double resultEWH2 = ((ElectricWaterHeater) dEWH2.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected, resultEWH2);
    }

    @Test
    void showElectricWaterHeaterList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(65, 1);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3, DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, kitchen, 2.5, DeviceType.ELECTRIC_WATER_HEATER);
        Device stove = new Device("Stove 1", DeviceType.STOVE, kitchen, 1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        deviceList.addDevice(stove);
        String expected = "1 - Device name : Daikin - Electric Water Heater1\n" +
                "2 - Device room : Kitchen\n" +
                "3 - Nominal Power : 3.0 kW\n" +
                "4 - Volume of water : 0.0\n" +
                "5 - Hot water temperature : 65.0\n" +
                "6 - Cold water temperature : 0.0\n" +
                "7 - Performance Ratio : 1.0\n" +
                "8 - Daily Energy Consumption: 0.0 KWh\n" +
                "\n" +
                "1 - Device name : Daikin - Electric Water Heater2\n" +
                "2 - Device room : Kitchen\n" +
                "3 - Nominal Power : 2.5 kW\n" +
                "4 - Volume of water : 0.0\n" +
                "5 - Hot water temperature : 65.0\n" +
                "6 - Cold water temperature : 0.0\n" +
                "7 - Performance Ratio : 1.0\n" +
                "8 - Daily Energy Consumption: 0.0 KWh\n\n";
        String result = deviceList.showElectricWaterHeaterList();
        assertEquals(expected, result);

    }


    @Test
    @DisplayName("Ensure that isLowerThanHotWater() method does not accept cold water temperature values higher than any hot water temperature value")
    void isLowerThanHotWaterReturnsTrue() {

        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ;
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3, DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, kitchen, 2.5, DeviceType.ELECTRIC_WATER_HEATER);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        boolean result = deviceList.isLowerThanHotWater(58);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that isLowerThanHotWater() method does not accept cold water temperature values higher than any hot water temperature value")
    void isLowerThanHotWaterReturnsFalse() {

        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3, DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, kitchen, 2.5, DeviceType.ELECTRIC_WATER_HEATER);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        boolean result = deviceList.isLowerThanHotWater(66);
        assertFalse(result);
    }
}