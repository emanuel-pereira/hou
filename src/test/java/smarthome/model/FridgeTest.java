package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridgeTest {

    @Test
    public void getAndSetAllAttributes() {
        Fridge fr = new Fridge(2, 3, 6);
        fr.setFreezerCapacity(6);
        int result = fr.getFreezerCapacity();
        assertEquals(6, result);

        fr.setRefrigeratorCapacity(9);
        int result1 = fr.getRefrigeratorCapacity();
        assertEquals(9, result1);
    }
    @Test
    public void showDeviceSpecsListAttributesInString() {
        Fridge f = new Fridge(2, 3, 6);
        String result = f.showDeviceSpecsListAttributesInString();
        String expected = "4 - Freezer Capacity : 2\n5 - Refrigerator Capacity : 3\n";
        assertEquals(expected,result);
    }

}
