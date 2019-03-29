package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FridgeTypeTest {

    @Test
    void getDeviceType() {
        FridgeType kt = new FridgeType();
        String result = kt.getDeviceType();

        assertEquals("Fridge", result);
    }

    @Test
    void createDevice() {
        FridgeType kt = new FridgeType();
        Device fridge = kt.createDevice("Best Fridge in the world", 12);

        String result = kt.getDeviceType();

        assertEquals("Fridge", result);
    }

    }
