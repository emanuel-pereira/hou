package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.DishwasherSpecs;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DishwasherSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");

        String expected = "Dishwasher";
        String result = dishwasherSpecs.getDeviceType ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Get all the correct attribute names")
    void getAttributesNames() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");

        List<String> expected = Collections.singletonList ("Capacity");
        List<String> result = dishwasherSpecs.getAttributesNames ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set and get all the correct attribute values")
    void getAttributeValues() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");

        dishwasherSpecs.setAttributeValue ("Capacity", 20);

        List<Double> expected = Collections.singletonList (20.0);
        List<Double> result = dishwasherSpecs.getAttributeValues ();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get all the correct attribute units")
    void getAttributeUnits() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");

        dishwasherSpecs.setAttributeUnit ("Capacity", "ml");

        List<String> expected = Collections.singletonList ("ml");
        List<String> result = dishwasherSpecs.getAttributeUnits ();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get one correct attribute value")
    void getAttributeValue() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");

        dishwasherSpecs.setAttributeValue ("Capacity", 20);

        double expected = 20.0;
        double result = dishwasherSpecs.getAttributeValue ("Capacity");

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get one correct attribute unit")
    void getAttributeUnit() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");

        dishwasherSpecs.setAttributeUnit ("Capacity", "Dish Sets");

        String expected = "Dish Sets";
        String result = dishwasherSpecs.getAttributeUnit ("Capacity");

        assertEquals(expected, result);
    }


}