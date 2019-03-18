package smarthome.model;

import org.junit.jupiter.api.Test;
import smarthome.model.devices.OvenType;

import static org.junit.jupiter.api.Assertions.*;

class OvenTypeTest {

    @Test
    void getDeviceType() {
        OvenType type = new OvenType();

        String expected = "Oven";
        String result = type.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void createDevice() {
        OvenType type = new OvenType();
        type.createDevice("baker", 420);
        String result = type.getDeviceType();

        assertEquals("Oven", result);
    }

    @Test
    void createDeviceNotValid() {
        OvenType type = new OvenType();

        Device oven = type.createDevice("132.5",420);
        String result = oven.getDeviceType();

        assertEquals("Oven",result);
    }
}