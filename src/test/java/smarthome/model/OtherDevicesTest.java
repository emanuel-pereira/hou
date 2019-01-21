package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherDevicesTest {
    @Test
    void getType() {
        OtherDevices stove = new OtherDevices(DeviceType.STOVE);
        DeviceType expected = DeviceType.STOVE;
        DeviceType result = stove.getType();
        assertEquals(expected, result);
    }

    @Test
    void getDeviceAttributesInString() {
    }

    @Test
    void setAttributeValue() {
    }
}