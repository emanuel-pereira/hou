package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.DishwasherSpecs;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        DishwasherSpecs dishwasher = new DishwasherSpecs("Dishwasher");

        String expected = "Dishwasher";
        String result = dishwasher.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set new values to all attributes and get all correct")
    void getAttributeValues() {
        DishwasherSpecs dishwasher = new DishwasherSpecs("Dishwasher");

        dishwasher.setAttributeValue("Capacity", 47);

        double capacity = dishwasher.getAttributeValue("Capacity");
        assertEquals(47, capacity);
    }

    @Test
    @DisplayName("Set new units to the two first attributes and get all correct")
    void getAttributeUnits() {
        DishwasherSpecs dishwasher = new DishwasherSpecs("Dishwasher");

        dishwasher.setAttributeUnit("Capacity", "Dish Sets");

        String capacity = dishwasher.getAttributeUnit("Capacity");
        assertEquals("Dish Sets", capacity);
    }

    @Test
    void getAttributesNames() {
        DishwasherSpecs dishwasher = new DishwasherSpecs("Dishwasher");

        List<String> attributesNames = dishwasher.getAttributesNames();
        List<String> expected = Arrays.asList ("Capacity");

        assertEquals(expected, attributesNames);
    }

    @Test
    void getAttributeValuesList() {
        DishwasherSpecs dishwasher = new DishwasherSpecs("Dishwasher");
        dishwasher.setAttributeValue("Capacity", 40);

        List<Double> dishwasherSpecsValues = dishwasher.getAttributeValues();
        List<Double> expectedDishwasherSpecsValues = Arrays.asList (40.0);
        assertEquals(expectedDishwasherSpecsValues, dishwasherSpecsValues);
    }

    @Test
    void getAttributeUnitsList() {
        DishwasherSpecs dishwasher = new DishwasherSpecs("Dishwasher");
        dishwasher.setAttributeValue("Capacity", 40);

        List<String> dishwasherSpecsUnits = dishwasher.getAttributeUnits();
        List<String> expectedDishwasherSpecsUnits = Arrays.asList ("Dish Sets");
        assertEquals(expectedDishwasherSpecsUnits, dishwasherSpecsUnits);
    }
}
