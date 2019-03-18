package smarthome.model;

import org.junit.jupiter.api.Test;
import smarthome.model.devices.OvenSpecs;
import smarthome.model.devices.OvenType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OvenSpecsTest {

    @Test
    void getDeviceType() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());

        String expected = "Oven";
        String result = specs.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void getAttributesNames() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());

        List<String> expected = new ArrayList<>();
        List<String> result = specs.getAttributesNames();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeValues() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());

        specs.setAttributeValue(null,Double.NaN);

        List<Double> expected = new ArrayList<>();
        List<Double> result = specs.getAttributeValues();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeUnits() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());

        specs.setAttributeUnit(null,null);

        List<String> expected = new ArrayList<>();
        List<String> result = specs.getAttributeUnits();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeValue() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());

        specs.setAttributeValue(null,Double.NaN);

        double expected = Double.NaN;
        double result = specs.getAttributeValue(null);

        assertEquals(expected,result);
    }

    @Test
    void getAttributeUnit() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        specs.setAttributeUnit(null,null);

        String result = specs.getAttributeUnit(null);

        assertNull(result);
    }

}