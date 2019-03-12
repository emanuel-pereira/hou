package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FanTypeTest {

    @Test
    @DisplayName("Get correct device type")
    void getCorrectDeviceType() {
        FanType type = new FanType ();
        type.createDevice ("Singer", 200);

        String expected = "Fan";
        String result = type.getDeviceType ();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get incorrect device type")
    void getIncorrectDeviceType() {
        FanType type = new FanType ();
        type.createDevice ("Singer", 200);

        String expected = "Washing Machine";
        String result = type.getDeviceType ();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Create device with success")
    void createCorrectDevice() {
        FanType type = new FanType ();
        Device wM = type.createDevice ("Singer", 200);

        String expected = "Singer";
        String result = wM.getDeviceName ();

        assertEquals(expected, result);
    }

}