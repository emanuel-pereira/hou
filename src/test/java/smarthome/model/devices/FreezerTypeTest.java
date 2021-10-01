package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FreezerTypeTest {

    @Test
    @DisplayName("Get correct device type")
    void getCorrectDeviceType() {
        FreezerType type = new FreezerType();
        type.createDevice("LG X100 Freezer", 20);

        String expected = "Freezer";
        String result = type.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get incorrect device type")
    void getIncorrectDeviceType() {
        FreezerType type = new FreezerType();
        type.createDevice("LG X100 Freezer", 20);

        String expected = "Incorrect Name";
        String result = type.getDeviceType();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Create device with success")
    void createCorrectDevice() {
        FreezerType type = new FreezerType();
        Device ehw = type.createDevice("LG X100 Freezer", 20);

        String expected = "LG X100 Freezer";
        String result = ehw.getDeviceName();

        assertEquals(expected, result);
    }

}