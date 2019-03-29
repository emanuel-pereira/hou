package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FridgeTest {
    @Test
    @DisplayName("Create device with a name and set it to another")
    void setDeviceNameTest() {
        DeviceType dt = new FridgeType();

        Device d = dt.createDevice("foo", 100);
        d.setDeviceName("bar");
        String result = d.getName();
        String expected = "bar";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Create a device and get DeviceSpecs")
    void getDeviceSpecsTest() {
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("foo", 100);
        DeviceSpecs ds = d.getDeviceSpecs();
        boolean result = ds instanceof DeviceSpecs ? true : false;
        assertTrue(result);
    }

    @Test
    @DisplayName("Create a device and get the Device Type name")
    void getDeviceTypeTest() {
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("foo", 100);
        String result = d.getDeviceType();
        String expected = "Fridge";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check if device is active")
    void checkDeviceIsActiveTest() {
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("foo", 100);
        boolean result = d.isActive();

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Deactivate device and check if it is inactive")
    void checkDeactivateDevice() {
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("foo", 100);
        boolean deactivate = d.deactivateDevice();
        boolean result = d.isActive();
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Create device with a nominal power and set it to another")
    void setDeviceNominalPowerTest() {
        DeviceType dt = new FridgeType();

        Device d = dt.createDevice("foo", 100);
        d.setNominalPower(200);
        double result = d.getNominalPower();
        double expected = 200;
        assertEquals(expected, result);
    }

    @Test
    void getEstimatedEnergyConsumption(){
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("foo", 3650);
        Fridge fridge = (Fridge) d;
        double expected = 10;
        double result = fridge.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }

}