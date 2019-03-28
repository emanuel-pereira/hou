package smarthome.model.devices;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericNoSpecsTest {

    @Test
    void getDeviceType() {

        GenericNoSpecs specs = new GenericNoSpecs("A device");
        String expected = "A device";
        String result = specs.getDeviceType();
        assertEquals(expected, result);
    }

    @Test
    void getAttributeValue() {
        GenericNoSpecs specs = new GenericNoSpecs("A device");

        specs.setAttributeValue("A", 1);
        specs.setAttributeValue("B", 2);
        specs.setAttributeValue("C", 3);

        double expected = Double.NaN;
        double result = specs.getAttributeValue("A");
        assertEquals(expected, result);

        expected = Double.NaN;
        result = specs.getAttributeValue("B");
        assertEquals(expected, result);

        expected = Double.NaN;
        result = specs.getAttributeValue("C");
        assertEquals(expected, result);

    }

    @Test
    void getAttributeUnit() {

        GenericNoSpecs specs = new GenericNoSpecs("A device");

        String expected = null;
        String result = specs.getAttributeUnit("A");
        assertEquals(expected, result);

        result = specs.getAttributeUnit("B");
        assertEquals(expected, result);

        result = specs.getAttributeUnit("C");
        assertEquals(expected, result);
    }

    @Test
    void getAttributesNames() {
        GenericNoSpecs specs = new GenericNoSpecs("A device");

        List<String> expected = new ArrayList<String>();

        List<String> result = specs.getAttributesNames();

        assertEquals(expected, result);
    }

    @Test
    void getAttributeValues() {
    }

    @Test
    void getAttributeUnits() {
        GenericNoSpecs specs = new GenericNoSpecs("A device");

        List<String> expected = new ArrayList<>();

        List<String> result = specs.getAttributeUnits();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeUnit() {
        GenericNoSpecs specs = new GenericNoSpecs("A device");

        specs.setAttributeUnit("A", "New unit");
        String expected = null;
        String result = specs.getAttributeUnit("A");
        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        GenericNoSpecs specs = new GenericNoSpecs("A device");

        specs.setAttributeValue("A", 3.1415926);
        double expected = Double.NaN;
        double result = specs.getAttributeValue("A");
        assertEquals(expected, result);

    }
}