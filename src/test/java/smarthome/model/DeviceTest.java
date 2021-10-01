package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    @DisplayName("Verify the nominal power in a devices list)")
    @Test
    void devicesListNominalPower() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Device> devices = new ArrayList<>();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");

        Room kitchen1 = new Room("R01", "Kitchen1", 0, 5, 5, 3);
        grid1.attachRoomToGrid(kitchen1);
        Room kitchen2 = new Room("R02", "Kitchen2", 0, 6, 4, 3);
        grid2.attachRoomToGrid(kitchen2);

        DeviceList k1DeviceList = kitchen1.getDeviceList();
        DeviceList k2DeviceList = kitchen2.getDeviceList();

        Device fridgeA = k1DeviceList.newDevice("FridgeA", "Fridge", 150);
        k1DeviceList.add(fridgeA);
        Device kettle = k1DeviceList.newDevice("KettleA", "Kettle", 1500);
        k1DeviceList.add(kettle);
        Device lamp = k1DeviceList.newDevice("LampA", "Lamp", 15);
        k1DeviceList.add(lamp);

        Device fridgeB = k2DeviceList.newDevice("FridgeB", "Fridge", 150);
        k2DeviceList.add(fridgeB);


        devices.add(fridgeA);
        devices.add(fridgeB);
        double total = 0.0;
        for (Device device : devices)
            total += device.getNominalPower();

        assertEquals(300, total);
    }

}