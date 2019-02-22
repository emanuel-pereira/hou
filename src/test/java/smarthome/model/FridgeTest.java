package smarthome.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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
    public void getDeviceSpecsListAttributesInString() {
        Fridge f = new Fridge(2, 3, 6);
        List<String> result = f.getAttributesNames();
        List<String> expected = Arrays.asList("mFreezerCapacity", "mRefrigeratorCapacity");
        assertEquals(expected, result);
    }

    @Test
    void setAttributeValueTest() throws IllegalAccessException{
        Fridge f = new Fridge(2, 3, 6);
        String freezerCapacity = "mFreezerCapacity";
        String refrigeratorCapacity = "mRefrigeratorCapacity";
        f.setAttributeValue(freezerCapacity, "20");
        assertEquals(20, f.getFreezerCapacity());
        f.setAttributeValue(refrigeratorCapacity, "5");
        assertEquals(5, f.getRefrigeratorCapacity());
    }

    @Test
    void getEnergyConsumption() {
        DeviceSpecs fridgeSpecs = new Fridge(25, 50, 1500);
        double expected = 4.1;
        double result = fridgeSpecs.getEnergyConsumption();
        assertEquals(expected, result, 0.1);
    }

    @Test
    void showDeviceAttributeNamesAndValues() throws IllegalAccessException{
        Fridge fr = new Fridge();
        fr.setAttributeValue("mRefrigeratorCapacity", "8");
        String result = fr.showDeviceAttributeNamesAndValues();
        String expected = "3 - Freezer Capacity : 0\n4 - Refrigerator Capacity : 8\n";
        assertEquals(expected, result);
    }

    @Test
    void getAtt() throws IllegalAccessException {
        Fridge f = new Fridge(2, 3, 6);
        Field[] result = f.getAttributesFields();
        result[1].set(f, 20);
        Object value = result[1].get(f);
        assertEquals(20, value);
    }
}