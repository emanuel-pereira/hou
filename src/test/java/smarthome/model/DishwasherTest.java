package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DishwasherTest {
    @Test
    void setCapacityTest() {
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER, 8);
        int newCapacity = 7;
        dw.setCapacity(newCapacity);
        assertEquals(dw.getCapacity(), newCapacity);
    }

    @Test
    void showDeviceSpecsListAttributesInStringTest() {
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER, 10);

        List<String> result = dw.getDeviceAttributesInString();
        List<String> expected = Arrays.asList("3 - Device Type : "+dw.getType().getTypeString(),"4 - Dishwater Capacity : " + dw.getCapacity());
        assertEquals(expected, result);

    }

    @Test
    void setAttributeValue() {
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER, 10);
        String Capacity = "4 - Dishwater Capacity : " + dw.getCapacity();

        dw.setAttributeValue(Capacity, "20");
        assertEquals(20, dw.getCapacity());

    }

    @Test
    void getType() {
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER, 10);
        DeviceType result = dw.getType();
        assertEquals(DeviceType.DISHWASHER, result);
    }
}