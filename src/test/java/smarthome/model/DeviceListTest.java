package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    @DisplayName("Ensure same device instance is only added once to device list")
    void addDevice() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices micro= new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave",micro, 0.8);
        DeviceList kitchenDevList=kitchen.getDeviceList();
        kitchenDevList.addDevice(microwave);
        int expectedResult1 = 1;
        int result1 = kitchenDevList.size();
        assertEquals(expectedResult1, result1);

        kitchenDevList.addDevice(microwave);
        int expectedResult2 = 1;
        int result2 = kitchenDevList.size();
        assertEquals(expectedResult2, result2);
    }

    @Test
    @DisplayName("Ensure newDevice method creates local instance of device with specs and that addDevice method adds it deviceList")
    void newDevice() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50, 350, 50);
        Device device = kitchenDevList.newDevice("LG Fridge", fridge, 1.2);
        kitchenDevList.addDevice(device);

        Device expected = device;
        Device result = kitchenDevList.get(0);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getLastElement().getName() returns last element from device list and respective device name")
    void getLastElement() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();

        OtherDevices micro= new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 0.8);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge,1.5);

        kitchenDevList.addDevice(microwave);
        kitchenDevList.addDevice(dFridge);
        String expectedResult = kitchenDevList.getLastElement().getName();
        String result = "LG Fridge";

        assertEquals(expectedResult, result);
    }

   @Test
    void showDeviceListInString() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        OtherDevices micro= new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave",micro,0.8);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        kitchenDevList.addDevice(microwave);
        kitchenDevList.addDevice(dFridge);
        String expected = "1 - Device: Samsung Microwave | Type: " + microwave.getDeviceSpecs().getType() + "\n2 - Device: LG Fridge | Type: " + dFridge.getDeviceSpecs().getType() + "\n";
        String result = kitchenDevList.showDeviceListInString();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that electric water list has 2 electric water heaters in a global device list containing another device of other type")
    void getElectricWaterHeaterList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,100, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,125, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1,3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2,2.5);
        OtherDevices stov=new OtherDevices(DeviceType.STOVE);
        Device stove = new Device("Stove 1",stov, 1);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(dEWH1);
        kitchenDevList.addDevice(dEWH2);
        kitchenDevList.addDevice(stove);
        int expected = 2;
        int result = kitchenDevList.getElectricWaterHeaterList().size();
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("Ensure t")
    void getEnergyConsumptionOfEWHList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,125, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,115, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        OtherDevices otherDevices= new OtherDevices(DeviceType.STOVE);
        Device stove = new Device("Stove 1",otherDevices, 1);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(dEWH1);
        kitchenDevList.addDevice(dEWH2);
        kitchenDevList.addDevice(stove);
        kitchenDevList.setVolumeOfWaterToHeat(65);
        kitchenDevList.setColdWaterTemperatureEWHList(20);
        double expected = 1469.5668;
        double result = kitchenDevList.getEnergyConsumptionOfEWHList();
        assertEquals(expected, result);
    }

  @Test
    @DisplayName("Ensure that volume of water is set to all devices of type Electric Water Heater")
    void setVolumeOfWaterEWHList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,130, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,175, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1,3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(dEWH1);
        kitchenDevList.addDevice(dEWH2);
        kitchenDevList.setVolumeOfWaterToHeat(65);

        double expected = 65;
        double resultEWH1 = ((ElectricWaterHeater) dEWH1.getDeviceSpecs()).getVolumeOfWaterToHeat();
        assertEquals(expected, resultEWH1);

        double resultEWH2 = ((ElectricWaterHeater) dEWH2.getDeviceSpecs()).getVolumeOfWaterToHeat();
        assertEquals(expected, resultEWH2);
    }

    @Test
    @DisplayName("Ensure that cold water temperature is set to all devices of type Electric Water Heater")
    void setColdWaterTemperatureEWHList() {

        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,130, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,135, 65, 1);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1,3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(dEWH1);
        kitchenDevList.addDevice(dEWH2);
        kitchenDevList.setColdWaterTemperatureEWHList(25);

        double expected = 25;
        double resultEWH1 = ((ElectricWaterHeater) dEWH1.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected, resultEWH1);

        double resultEWH2 = ((ElectricWaterHeater) dEWH2.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected, resultEWH2);
    }

    @Test
    void showElectricWaterHeaterList() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,150, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,220, 65, 1);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        OtherDevices other= new OtherDevices(DeviceType.STOVE);
        Device stove = new Device("Stove 1", other,1);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(dEWH1);
        kitchenDevList.addDevice(dEWH2);
        kitchenDevList.addDevice(stove);
        String expected = "2 - Device Name : Daikin - Electric Water Heater1\n" +
                "3 - Device Nominal Power : 3.0\n" +
                "4 - Volume of water capacity (l): 150.0\n" +
                "5 - Hot water temperature : 65.0\n" +
                "6 - Cold Water temperature : 0.0\n" +
                "7 - Performance Ratio : 1.0\n" +
                "8 - Volume of water to heat : 0.0\n" +
                "9 - Daily Energy Consumption : 0.0 KWh\n" +
                "\n" +
                "2 - Device Name : Daikin - Electric Water Heater2\n" +
                "3 - Device Nominal Power : 2.5\n" +
                "4 - Volume of water capacity (l): 220.0\n" +
                "5 - Hot water temperature : 65.0\n" +
                "6 - Cold Water temperature : 0.0\n" +
                "7 - Performance Ratio : 1.0\n" +
                "8 - Volume of water to heat : 0.0\n" +
                "9 - Daily Energy Consumption : 0.0 KWh\n\n";
        String result = kitchenDevList.showElectricWaterHeaterList();
        assertEquals(expected, result);

    }


    @Test
    @DisplayName("Ensure that isValidColdWaterTemperature() method does not accept cold water temperature values higher than any hot water temperature value")
    void isLowerThanHotWaterReturnsTrue() {

        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ;
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,200, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,150, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1,3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(dEWH1);
        kitchenDevList.addDevice(dEWH2);
        boolean result = kitchenDevList.isValidColdWaterTemperature(58);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that isValidColdWaterTemperature() method does not accept cold water temperature values higher than any hot water temperature value")
    void isLowerThanHotWaterReturnsFalse() {

        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,130, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1,  3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(dEWH1);
        kitchenDevList.addDevice(dEWH2);
        boolean result = kitchenDevList.isValidColdWaterTemperature(66);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that method isValidVolumeOfWater returns true when volume of water to heat is within" +
            "EWH capacity")
    void isValidVolumeOfWater() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater( DeviceType.ELECTRIC_WATER_HEATER,130, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1,  3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        DeviceList deviceList = kitchen.getDeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        boolean result= deviceList.isValidVolumeOfWater(25);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that method isValidVolumeOfWater returns false when exceeds volume of water to heat exceeds" +
            "EWH capacity")
    void isValidVolumeOfWaterReturnsFalseWhenExceedsCapacity() {
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,130, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, 2.5);
        DeviceList deviceList = kitchen.getDeviceList();
        deviceList.addDevice(dEWH1);
        deviceList.addDevice(dEWH2);
        boolean result= deviceList.isValidVolumeOfWater(125);
        assertFalse(result);
    }
}