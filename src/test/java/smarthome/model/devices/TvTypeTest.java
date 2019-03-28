package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TvTypeTest {
    @Test
    @DisplayName("Obtain the correct DeviceType for the Tv")
    void getDeviceType() {
        TvType tvType = new TvType();
        String result = tvType.getDeviceType();

        assertEquals("Tv", result);
    }

    @Test
    @DisplayName("Tests if creates new TvType device with correct name")
    void createDevice() {
        TvType tvType = new TvType();

        Device tv = tvType.createDevice("SmartTV", 450);

        String result = tv.getDeviceType();

        assertEquals("Tv", result);
    }

    @Test
    @DisplayName("Tests if creates new TvType device with the name Tv although the given name is invalid")
    void createDeviceInvalid() {
        TvType tvType = new TvType();

        Device tv = tvType.createDevice("MyTV", 452);

        String result = tv.getDeviceType();

        assertNotEquals("Television", result);
    }
}
