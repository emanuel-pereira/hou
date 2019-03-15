package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FanSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        FanSpecs fanSpecs = new FanSpecs("Fan");

        String expected = "Fan";
        String result = fanSpecs.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get empty list")
    void getAttributesNames() {
        FanSpecs fanSpecs = new FanSpecs("Fan");

        List<String> expected = Collections.emptyList();
        List<String> result = fanSpecs.getAttributesNames();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get empty list")
    void getAttributeValues() {
        FanSpecs fanSpecs = new FanSpecs("Fan");

        fanSpecs.setAttributeValue("Maximum Velocity", 200);

        List<Double> expected = new ArrayList<>();
        List<Double> result = fanSpecs.getAttributeValues();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get empty list")
    void getAttributeUnits() {
        FanSpecs fanSpecs = new FanSpecs("Fan");

        fanSpecs.setAttributeUnit("Maximum Velocity", "rpm");

        List<String> expected = Collections.emptyList();
        List<String> result = fanSpecs.getAttributeUnits();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get nonexistent attribute value")
    void getAttributeValue() {
        FanSpecs fanSpecs = new FanSpecs("Fan");

        fanSpecs.setAttributeValue("Maximum Velocity", 200);

        double result = fanSpecs.getAttributeValue("Maximum Velocity");

        assertEquals(NaN, result);
    }

    @Test
    @DisplayName("Set and get null unit")
    void getAttributeUnit() {
        FanSpecs fanSpecs = new FanSpecs("Fan");

        fanSpecs.setAttributeUnit("Maximum Velocity", "rpm");

        String result = fanSpecs.getAttributeUnit("Maximum Velocity");

        assertNull(result);
    }


}