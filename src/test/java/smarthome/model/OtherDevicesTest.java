package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherDevicesTest {

    @Test
    void getType() {
        OtherDevices tv = new OtherDevices(DeviceType.TV);
        Room r1 = new Room("Living Room", 0, 5, 4, 3);
        Device device = new Device("Sony TV", tv, r1, 2);
        String expected = DeviceType.TV.getType();
        String result = tv.getType();
        assertEquals(expected,result);
    }

    @Test
    void getTypeFromIndex() {
        OtherDevices tv = new OtherDevices(DeviceType.TV);
        Room r1 = new Room("Living Room", 0, 5, 4, 3);
        Device device = new Device("Sony TV", tv, r1, 2);
        String expected = DeviceType.TV.getType();
        String result = tv.getTypeFromIndex(13);
        assertEquals(expected,result);
    }
}