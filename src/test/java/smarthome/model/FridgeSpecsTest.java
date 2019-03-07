package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FridgeSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        FridgeSpecs f = new FridgeSpecs ("Fridge");

        String expected = "Fridge";
        String result = f.getDeviceType ();

        assertEquals (expected, result);
    }

    @Test // Está a dar um array vazio
    void getAttributesNames() {
        FridgeSpecs f = new FridgeSpecs("Fridge");

        List<String> expected = Arrays.asList ("Freezer Capacity", "Refrigerator Capacity", "Annual Energy Consumption");
        List<String> result = f.getAttributesNames ();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set attribute unit to milliliters and get correct Freezer Capacity unit")
    void setAttributeUnit() {
        FridgeSpecs f = new FridgeSpecs ("Fridge");

        String expected1 = "liters";
        String result1 = f.getAttributeUnit ("Freezer Capacity");

        assertEquals (expected1, result1);

        f.setAttributeUnit ("Freezer Capacity", "milliliters");

        String expected2 = "milliliters";
        String result2 = f.getAttributeUnit ("Freezer Capacity");

        assertEquals (expected2, result2);
    }

    @Test
    @DisplayName("Set new attribute value to 12 and get correct Refrigerator Capacity value")
    void setAttributeValue() {
        FridgeSpecs f = new FridgeSpecs ("Fridge");

        f.setAttributeValue ("Refrigerator Capacity", 12);

        double expected = 12;
        double result = f.getAttributeValue ("Refrigerator Capacity");

        assertEquals (expected, result);
    }

    @Test // Está a dar um array vazio
    @DisplayName("Set new values to all attributes and get all correct")
    void getAttributeValues() {
        FridgeSpecs f = new FridgeSpecs ("Fridge");

        f.setAttributeValue ("Freezer Capacity", 20.5);
        f.setAttributeValue ("Refrigerator Capacity", 12.0);
        f.setAttributeValue ("Annual Energy Consumption", 1575.8);

        List<Double> expected = Arrays.asList (20.5,12.0,1575.8);
        List<Double> result = f.getAttributeValues ();

        assertEquals (expected, result);
    }

    @Test // A ordem das unidades não está correcta
    @DisplayName("Set new units to the two first attributes and get all correct")
    void getAttributeUnits() {
        FridgeSpecs f = new FridgeSpecs ("Fridge");

        f.setAttributeUnit ("Freezer Capacity", "milliliters");
        f.setAttributeUnit ("Refrigerator Capacity", "milliliters");
        f.setAttributeUnit ("Annual Energy Consumption", "Wh");

        List<String> expected = Arrays.asList ("milliliters","milliliters","Wh");
        List<String> result = f.getAttributeUnits ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Get correct Energy Consumption")
    void getEnergyConsumption() {
        FridgeSpecs f = new FridgeSpecs ("Fridge");

        f.setAttributeValue ("Annual Energy Consumption", 1575);

        double expected = 4.31;
        double result = f.getEnergyConsumption ();

        assertEquals (expected, result,0.1);
    }

}