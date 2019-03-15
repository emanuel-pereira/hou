package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTypeTest {

    @Test
    void getDeviceType() {
        DishwasherType dishwasher = new DishwasherType();
        String result = dishwasher.getDeviceType();

        assertEquals("Dishwasher", result);
    }

    @Test
    void createDevice() {
        DishwasherType dishwasher = new DishwasherType();
        dishwasher.createDevice("LG Washer", 2300);
        String result = dishwasher.getDeviceType();

        assertEquals("Dishwasher", result);
    }

    @Test
    void createDeviceInvalid() {
        DishwasherType dishwasher = new DishwasherType();
        dishwasher.createDevice("1231231231", 12);
        String result = dishwasher.getDeviceType();
        //the test passes accepting input name alphanumerical as the the validations are not in the constructor but in
        // the device list prior to the constructor method call
        assertEquals("Dishwasher", result);
    }
}
