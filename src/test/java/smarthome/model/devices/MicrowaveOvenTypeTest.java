package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MicrowaveOvenTypeTest {

    @Test
    void getDeviceType() {
        MicrowaveOvenType type = new MicrowaveOvenType();

        String expected = "MicrowaveOven";
        String result = type.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void createDevice() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        type.createDevice("Samsung Microwave", 420);
        String result = type.getDeviceType();

        assertEquals("MicrowaveOven", result);
    }

    @Test
    void createDeviceNotValid() {
        MicrowaveOvenType type = new MicrowaveOvenType();

        Device microwaveoven = type.createDevice("132.5",420);
        String result = microwaveoven.getDeviceType();

        assertEquals("MicrowaveOven",result);
    }
}