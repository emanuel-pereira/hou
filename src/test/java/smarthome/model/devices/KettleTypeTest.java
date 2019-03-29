package smarthome.model.devices;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KettleTypeTest {

    @Test
    void getDeviceType() {
        KettleType kt = new KettleType();
        String result = kt.getDeviceType();

        assertEquals("Kettle", result);
    }

    @Test
    void createDevice() {
        KettleType kt = new KettleType();
        kt.createDevice("Best kettle in the world", 12);
        String result = kt.getDeviceType();

        assertEquals("Kettle", result);
    }

    @Test
    void createDeviceInvalid() {
        KettleType kt = new KettleType();
        kt.createDevice("1231231231", 12);
        String result = kt.getDeviceType();
        //the test passes accepting input name alphanumerical as the the validations are not in the constructor but in
        // the device list prior to the constructor method call
        assertEquals("Kettle", result);
    }
}
