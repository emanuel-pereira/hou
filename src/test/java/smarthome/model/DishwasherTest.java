package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DishwasherTest {
    @Test
    void setCapacityTest() {
        Dishwasher dw = new Dishwasher(8);
        int newCapacity = 7;
        dw.setCapacity(newCapacity);
        assertEquals(dw.getCapacity(),newCapacity);
    }
    @Test
    void showDeviceSpecsListAttributesInStringTest(){
        Dishwasher dw = new Dishwasher(10);

        String result = dw.showDeviceSpecsListAttributesInString();
        assertEquals("4 - Dishwater Capacity : "+dw.getCapacity(),result);

    }
}