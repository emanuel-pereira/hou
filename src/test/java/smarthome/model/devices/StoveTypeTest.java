package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.devices.StoveType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoveTypeTest {


    @Test
    public void getDeviceType() {

        StoveType stoveType = new StoveType();
        String result = stoveType.getDeviceType();
        assertEquals("Stove", result);

    }

    @Test
    public void createDevice() {

        StoveType stoveType = new StoveType();
        stoveType.createDevice("kitchen stove", 1500.0);
        String result = stoveType.getDeviceType();
        assertEquals(stoveType.getDeviceType(), result);
    }

    @Test
    public void createDeviceInvalid() {

        StoveType stoveType = new StoveType();
        stoveType.createDevice("736456377", 1500.0);
        String result = stoveType.getDeviceType();
        assertEquals("Stove", result);
    }
}
