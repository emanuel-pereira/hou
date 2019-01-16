package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTest {

    @Test
    @DisplayName("Ensure getType from Dishwasher instance returns correct type")
    void getType() {
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,15);

        String expected= "Dishwasher";
        String result=dishwasher.getType();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure getTypeFromIndex from Dishwasher instance returns correct type")
    void getTypeFromIndex() {
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,15);

        String expected= "Dishwasher";
        String result=dishwasher.getTypeFromIndex(2);

        assertEquals(expected,result);
    }
    @Test
    void setCapacityTest() {
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER,8);
        int newCapacity = 7;
        dw.setCapacity(newCapacity);
        assertEquals(dw.getCapacity(),newCapacity);
    }
    @Test
    void showDeviceSpecsListAttributesInStringTest(){
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER,10);

        String result = dw.showDeviceSpecsListAttributesInString();
        assertEquals("4 - Dishwater Capacity : "+dw.getCapacity(),result);

    }
}