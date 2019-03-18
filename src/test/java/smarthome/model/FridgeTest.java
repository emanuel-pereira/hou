package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.FridgeType;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Create device with a nominal power and set it to another")
    void setDeviceNominalPowerTest() {
        DeviceType dt = new FridgeType();

        Device d = dt.createDevice("foo", 100);
        d.setNominalPower(200);
        double result = d.getNominalPower();
        double expected = 200;
        assertEquals(expected,result);
    }



}