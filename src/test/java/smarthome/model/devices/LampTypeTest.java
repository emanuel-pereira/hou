package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LampTypeTest {

    @Test
    void getDeviceType() {
        LampType lt = new LampType();
        Device d = lt.createDevice("Best Lamp in the world",12);
        String result = d.getDeviceType();

        assertEquals("Lamp", result);
    }

    @Test
    void createDevice() {
        LampType kt = new LampType();
        kt.createDevice("Best Lamp in the world", 12);
        String result = kt.getDeviceType();

        assertEquals("Lamp", result);
    }
}
