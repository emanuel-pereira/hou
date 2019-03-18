package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.TvSpecs;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TvSpecsTest {

    @Test
    @DisplayName("Obtain the correct DeviceType for the Tv")
    void getDeviceType() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        String expected = "Tv";
        String result = tvSpecs.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set new value for one attribute of TV and obtain them correctly")
    void getAttributeValue() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        tvSpecs.setAttributeValue("Standby Power", 120.1);

        double result = tvSpecs.getAttributeValue("Standby Power");

        assertEquals(120.1, result);
    }

    @Test
    @DisplayName("Set new unit for one attribute of TV and obtain it correctly")
    void getAttributeUnit() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        tvSpecs.setAttributeUnit("Standby Power", "W");

        String result = tvSpecs.getAttributeUnit("Standby Power");

        assertEquals("W", result);
    }

    @Test
    @DisplayName("Obtain the list of all Tv's attributes' names")
    void getAttributesNames() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        List<String> result = tvSpecs.getAttributesNames();
        List<String> expected = Arrays.asList("Standby Power");

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Obtain the list of all Tv's attributes' values")
    void getAllAttributeValues() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        tvSpecs.setAttributeValue("Standby Power", 120.1);

        List<Double> result = tvSpecs.getAttributeValues();
        List<Double> expected = Arrays.asList(120.1);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Obtain the list of all Tv's attributes' units")
    void getAllAttributeUnits() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        tvSpecs.setAttributeUnit("Standby Power", "W");

        List<String> result = tvSpecs.getAttributeUnits();
        List<String> expected = Arrays.asList("W");

        assertEquals(expected, result);

    }



}