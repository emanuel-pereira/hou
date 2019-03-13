package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    @DisplayName("Create and add new devices and get all correct")
    void newDevice() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device kettle1 = deviceList.newDevice ("Kettle1", "Kettle", 200);
        Device kettle2 = deviceList.newDevice ("Kettle2", "Kettle", 210);
        deviceList.addDevice (kettle1);
        deviceList.addDevice (kettle2);

        List<Device> expected = Arrays.asList (kettle1, kettle2);
        List<Device> result = deviceList.getDeviceList ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Create and add new devices and get one correctly")
    void get() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Kettle", "Kettle", 120);
        deviceList.addDevice (lamp);
        deviceList.addDevice (kettle);

        Device expected = lamp;
        Device result = deviceList.get (0);

        assertEquals (expected, result);
    }

/* TODO: Rewrite this test
    @Test
    @DisplayName("Create and add new devices and get a string with the information of the device list")
    void showDeviceListInString() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Kettle", "Kettle", 120);
        deviceList.addDevice (lamp);
        deviceList.addDevice (kettle);

        String expected = "1 - Device: Lamp | Type: Lamp | Active: true\n2 - Device: Kettle | Type: Kettle | Active: true\n";
        String result = deviceList.showDeviceListInString ();

        assertEquals (expected, result);
    }
*/

    @Test
    @DisplayName("Create and add new devices and remove one correctly")
    void removeDevice() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle1 = deviceList.newDevice ("Kettle1", "Kettle", 200);
        Device kettle2 = deviceList.newDevice ("Kettle2", "Kettle", 210);        deviceList.addDevice (lamp);
        deviceList.addDevice (lamp);
        deviceList.addDevice (kettle1);
        deviceList.addDevice (kettle2);

        int expected1 = 3;
        int result1 = deviceList.size ();

        assertEquals (expected1, result1);

        deviceList.removeDevice (lamp);

        int expected2 = 2;
        int result2 = deviceList.size ();

        assertEquals (expected2, result2);
    }


    @Test
    @DisplayName("Create and add new devices and deactivate one correctly")
    void deactivateDevice() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Kettle", "Kettle", 120);
        deviceList.addDevice (lamp);
        deviceList.addDevice (kettle);
        deviceList.deactivateDevice (kettle);

        boolean result = kettle.isActive ();

        assertFalse (result);

        assertTrue(lamp.isActive ());

        assertEquals (2,deviceList.size ());
    }

    @Test
    @DisplayName("Create and add new metered devices and get list correctly")
    void getMeteredDevices() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Kettle", "Kettle", 120);
        deviceList.addDevice (lamp);
        deviceList.addDevice (kettle);

        List<Metered> expected = Arrays.asList ((Metered) lamp, (Metered) kettle);
        List<Metered> result = deviceList.getMeteredDevices ();

        assertEquals (expected, result);
    }
}