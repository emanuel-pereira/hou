package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.devices.StoveSpecs;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoveSpecsTest {


    @Test
    public void getDeviceType() {

        StoveSpecs stove = new StoveSpecs("Stove");

        String expected = "Stove";
        String result = stove.getDeviceType();

        assertEquals(expected, result);

    }

    @Test
    public void getAttributeValue() {

        StoveSpecs stove = new StoveSpecs("Stove");

        stove.setAttributeValue("capacity", 1500);
        double capacity = stove.getAttributeValue("capacity");

        assertEquals(1500, capacity);
    }

    @Test
    public void getAttributeUnit() {

        StoveSpecs stove = new StoveSpecs("Stove");

        stove.setAttributeUnit("capacity", "kw/h");
        String capacity = stove.getAttributeUnit("capacity");

        assertEquals("kw/h", capacity);

    }

    @Test
    public void getAttributesNames() {

        StoveSpecs stove = new StoveSpecs("Stove");

        List<String> attributeNames = stove.getAttributesNames();
        List<String> expected = Arrays.asList("capacity");

        assertEquals(expected, attributeNames);

    }

    @Test
    public void getAttributeValuesList() {

        StoveSpecs stove = new StoveSpecs("Stove");

        stove.setAttributeValue("capacity", 1500.0);

        List<Double> StoveSpecsValues = stove.getAttributeValues();
        List<Double> expectedStoveSpecsValues = Arrays.asList(1500.0);

        assertEquals(expectedStoveSpecsValues, StoveSpecsValues);
    }

    @Test
    public void getAttributeUnitsList() {

        StoveSpecs stove = new StoveSpecs("Stove");

        stove.setAttributeValue("capacity", 1500.00);

        List<String> StoveSpecsUnits = stove.getAttributeUnits();
        List<String> expectedStoveSpecsUnits = Arrays.asList("Kw/h");
        assertEquals(expectedStoveSpecsUnits, StoveSpecsUnits);
    }

}
