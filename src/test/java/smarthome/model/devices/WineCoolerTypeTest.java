package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WineCoolerTypeTest {

    @Test
    @DisplayName("Get correct device type")
    void getCorrectDeviceType() {
        WineCoolerType type = new WineCoolerType();
        type.createDevice("Vivinus", 20);

        String expected = "WineCooler";
        String result = type.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get incorrect device type")
    void getIncorrectDeviceType() {
        WineCoolerType type = new WineCoolerType();
        type.createDevice("Vivinus", 20);

        String expected = "Incorrect Name";
        String result = type.getDeviceType();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Create device with success")
    void createCorrectDevice() {
        WineCoolerType type = new WineCoolerType();
        Device wineCooler = type.createDevice("Vivinus", 200);

        String expected = "Vivinus";
        String result = wineCooler.getDeviceName();

        assertEquals(expected, result);
    }
}