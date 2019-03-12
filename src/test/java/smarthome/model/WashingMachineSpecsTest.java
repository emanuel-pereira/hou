package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashingMachineSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");

        String expected = "Washing Machine";
        String result = washingMachineSpecs.getDeviceType ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Get all the correct attribute names")
    void getAttributesNames() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");

        List<String> expected = Collections.singletonList ("Capacity");
        List<String> result = washingMachineSpecs.getAttributesNames ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set and get all the correct attribute values")
    void getAttributeValues() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");

        washingMachineSpecs.setAttributeValue ("Capacity", 200);

        List<Double> expected = Collections.singletonList (200.0);
        List<Double> result = washingMachineSpecs.getAttributeValues ();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get all the correct attribute units")
    void getAttributeUnits() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");

        washingMachineSpecs.setAttributeUnit ("Capacity", "ml");

        List<String> expected = Collections.singletonList ("ml");
        List<String> result = washingMachineSpecs.getAttributeUnits ();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get one correct attribute value")
    void getAttributeValue() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");

        washingMachineSpecs.setAttributeValue ("Capacity", 200);

        double expected = 200.0;
        double result = washingMachineSpecs.getAttributeValue ("Capacity");

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get one correct attribute unit")
    void getAttributeUnit() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");

        washingMachineSpecs.setAttributeUnit ("Capacity", "ml");

        String expected = "ml";
        String result = washingMachineSpecs.getAttributeUnit ("Capacity");

        assertEquals(expected, result);
    }


}