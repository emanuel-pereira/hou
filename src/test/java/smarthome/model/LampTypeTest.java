package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LampTypeTest {

    @Test
    void getDeviceType() {
        LampType kt = new LampType();
        String result = kt.getDeviceType();

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
