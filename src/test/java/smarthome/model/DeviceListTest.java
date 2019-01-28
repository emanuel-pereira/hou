package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    @DisplayName("Ensure same device instance is only added once to device list")
    void addDevice() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 0.8);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        assertTrue(kitchenDevList.addDevice(microwave));
        int expectedResult1 = 1;
        int result1 = kitchenDevList.size();
        assertEquals(expectedResult1, result1);

        assertFalse(kitchenDevList.addDevice(microwave));
        int expectedResult2 = 1;
        int result2 = kitchenDevList.size();
        assertEquals(expectedResult2, result2);
    }

    @Test
    @DisplayName("Ensure newDevice method creates local instance of device with specs and that addDevice method adds it deviceList")
    void newDevice() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        Device device = kitchenDevList.newDevice("LG Fridge", fridge, 1.2);
        assertTrue(kitchenDevList.addDevice(device));

        Device expected = device;
        Device result = kitchenDevList.get(0);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getLastElement().getName() returns last element from device list and respective device name")
    void getLastElement() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();

        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 0.8);
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);

        assertTrue(kitchenDevList.addDevice(microwave));
        assertTrue(kitchenDevList.addDevice(dFridge));
        String expectedResult = kitchenDevList.getLastElement().getName();
        String result = "LG Fridge";

        assertEquals(expectedResult, result);
    }


    @Test
    void getDevicesOfType() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        DeviceSpecs fridgeSpecs = new Fridge(DeviceType.FRIDGE, 25, 50, 1500);
        Device fridge = new Device("LG Fridge", fridgeSpecs, 100);
        DeviceSpecs ewhSpecs = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 150, 65, 0.9);
        Device electricWaterHeater = new Device("EWH 1", ewhSpecs, 2);
        kitchenDevList.addDevice(fridge);
        kitchenDevList.addDevice(electricWaterHeater);
        List<Device> expected = Arrays.asList(electricWaterHeater);
        List<Device> result = kitchenDevList.getDevicesOfType(DeviceType.ELECTRIC_WATER_HEATER);
        assertEquals(expected,result);
    }

    @Test
    void deactivateDevice() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 0.8);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(microwave);
        int expectedResult1 = 1;
        int result1 = kitchenDevList.size();
        assertEquals(expectedResult1, result1);

        assertTrue(kitchenDevList.deactivateDevice(microwave));
        int expectedResult2 = 1;
        int result2 = kitchenDevList.size();
        assertEquals(expectedResult2, result2);
    }

    @Test
    void removeDevice() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 0.8);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        assertTrue(kitchenDevList.addDevice(microwave));

        assertTrue(kitchenDevList.removeDevice(microwave));
        int expectedResult2 = 0;
        int result2 = kitchenDevList.size();
        assertEquals(expectedResult2, result2);
    }
}