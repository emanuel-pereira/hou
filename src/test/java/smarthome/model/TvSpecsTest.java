package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.TvSpecs;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TvSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        TvSpecs tv = new TvSpecs("Tv");

        String expected = "Tv";
        String result = tv.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set new values to all attributes and get all correct")
    void getAttributeValue() {
        TvSpecs tv = new TvSpecs("Tv");

        tv.setAttributeValue("Capacity", 15);

        double result = tv.getAttributeValue("Capacity");
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Set new units to the two first attributes and get all correct")
    void getAttributeUnit() {
        TvSpecs tv = new TvSpecs("Tv");

        tv.setAttributeValue("Capacity", 15);

        String result = tv.getAttributeUnit("Capacity");
        assertEquals("Dish Sets", result);
    }

    @Test
    void getAttributesNames() {
        TvSpecs tv = new TvSpecs("Tv");

        List<String> attributesNames = tv.getAttributesNames();
        List<String> expected = Arrays.asList("Capacity");

        assertEquals(expected, attributesNames);
    }

    @Test
    void getAttributeValuesList() {
        TvSpecs tv = new TvSpecs("Tv");
        tv.setAttributeValue("Capacity", 20);

        List<Double> resultSpecsValues = tv.getAttributeValues();
        List<Double> expectedSpecsValues = Arrays.asList(20.0);
        assertEquals(expectedSpecsValues, resultSpecsValues);
    }

    @Test
    void getAttributeUnitsList() {
        TvSpecs tv = new TvSpecs("Tv");
        tv.setAttributeValue("Capacity", 25);

        List<String> resultSpecsUnits = tv.getAttributeUnits();
        List<String> expectedSpecsUnits = Arrays.asList("Dish Sets");
        assertEquals(expectedSpecsUnits, resultSpecsUnits);
    }

    @Test
    void setAttributeUnit() {
        TvSpecs tv = new TvSpecs("Tv");

        tv.setAttributeUnit("Capacity", "Dish");

        String result = tv.getAttributeUnit("Capacity");
        assertEquals("Dish", result);
    }
}