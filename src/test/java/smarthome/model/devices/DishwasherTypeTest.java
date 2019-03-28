package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;
import smarthome.model.devices.DishwasherType;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTypeTest {

    @Test
    @DisplayName("Get correct device type")
    void getCorrectDeviceType() {
        DishwasherType type = new DishwasherType ();
        type.createDevice ("Ariston", 20);

        String expected = "Dishwasher";
        String result = type.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get incorrect device type")
    void getIncorrectDeviceType() {
        DishwasherType type = new DishwasherType ();
        type.createDevice ("Ariston", 20);

        String expected = "Dishwashr";
        String result = type.getDeviceType();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Create device with success")
    void createCorrectDevice() {
        DishwasherType type = new DishwasherType ();
        Device dishwasher = type.createDevice ("Ariston", 20);

        String expected = "Ariston";
        String result = dishwasher.getName ();

        assertEquals(expected, result);
    }

}