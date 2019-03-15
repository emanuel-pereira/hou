package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenSpecsTest {

    @Test
    void getDeviceType() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());

        String expected = "MicrowaveOven";
        String result = specs.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void getAttributesNames() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());

        List<String> expected = new ArrayList<>();
        List<String> result = specs.getAttributesNames();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeValues() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());

        specs.setAttributeValue(null,Double.NaN);

        List<Double> expected = new ArrayList<>();
        List<Double> result = specs.getAttributeValues();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeUnits() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());

        specs.setAttributeUnit(null,null);

        List<String> expected = new ArrayList<>();
        List<String> result = specs.getAttributeUnits();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeValue() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());

        specs.setAttributeValue(null,Double.NaN);

        double expected = Double.NaN;
        double result = specs.getAttributeValue(null);

        assertEquals(expected,result);
    }

    @Test
    void getAttributeUnit() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        specs.setAttributeUnit(null,null);

        String result = specs.getAttributeUnit(null);

        assertNull(result);
    }

}