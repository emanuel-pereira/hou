package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DishwasherTest {
    @Test
    void setCapacityTest() {
        Dishwasher dw = new Dishwasher( 8);
        int newCapacity = 7;
        dw.setCapacity(newCapacity);
        assertEquals(dw.getCapacity(), newCapacity);
    }

    @Test
    void showDeviceSpecsListAttributesInStringTest() {
        Dishwasher dw = new Dishwasher( 10);

        List<String> result = dw.getAttributesNames();
        List<String> expected = Arrays.asList("Dishwasher Capacity");
        assertEquals(expected, result);

    }

    @Test
    void setAttributeValue() {
        Dishwasher dw = new Dishwasher( 10);
        dw.setAttributeValue("Dishwasher Capacity", "20");
        assertEquals(20, dw.getCapacity());

    }

    @Test
    void setFailAttributeValue() {
        Dishwasher dw = new Dishwasher( 10);
        dw.setAttributeValue("Wrong Capacity", "20");
        assertEquals(10, dw.getCapacity());

    }


    @Test
    void setAndGetTypeTest() {
        Dishwasher dishwasher = new Dishwasher();
        DeviceType dish = new DeviceType("Dishwasher");
        dishwasher.setType(dish);
        String result = dishwasher.getDeviceType().getDeviceTypeName();
        assertEquals("Dishwasher", result);
    }
    @Test
    void showDeviceAttributeNamesAndValuesTest(){
        Dishwasher dishwasher = new Dishwasher();
        String result = dishwasher.showDeviceAttributeNamesAndValues();
        String expected ="3 - Dishwasher Capacity : 0\n";
        assertEquals(expected,result);
    }
    @Test
    void getEnergyConsumptionTest(){
        Dishwasher dishwasher = new Dishwasher();
        double result = dishwasher.getEnergyConsumption();
        double expected = 0;
        assertEquals(expected,result);
    }

}