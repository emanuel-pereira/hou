package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KettleSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        KettleSpecs Kettle = new KettleSpecs("Kettle");

        String expected = "Kettle";
        String result = Kettle.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set new values to all attributes and get all correct")
    void getAttributeValues() {
        KettleSpecs Kettle = new KettleSpecs("Kettle");

        Kettle.setAttributeValue("Capacity", 470);

        double capacity = Kettle.getAttributeValue("Capacity");
        assertEquals(470, capacity);
    }

    @Test
    @DisplayName("Set new units to the two first attributes and get all correct")
    void getAttributeUnits() {
        KettleSpecs kettle = new KettleSpecs("Kettle");

        kettle.setAttributeUnit("Capacity", "mL");

        String capacity = kettle.getAttributeUnit("Capacity");
        assertEquals("mL", capacity);
    }

    @Test
    void getAttributesNames() {
        KettleSpecs kettle = new KettleSpecs("Kettle");

        List<String> attributesNames = kettle.getAttributesNames();
        List<String> expected = Arrays.asList ("Capacity");

        assertEquals(expected, attributesNames);
    }

    @Test
    void getAttributeValuesList() {
        KettleSpecs kettle = new KettleSpecs("Kettle");
        kettle.setAttributeValue("Capacity", 4.5);

        List<Double> freezerSpecsValues = kettle.getAttributeValues();
        List<Double> expectedFreezerSpecsValues = Arrays.asList (4.5);
        assertEquals(expectedFreezerSpecsValues, freezerSpecsValues);
    }

    @Test
    void getAttributeUnitsList() {
        KettleSpecs kettle = new KettleSpecs("Kettle");
        kettle.setAttributeValue("Capacity", 4.5);

        List<String> freezerSpecsUnits = kettle.getAttributeUnits();
        List<String> expectedFreezerSpecsUnits = Arrays.asList ("L");
        assertEquals(expectedFreezerSpecsUnits, freezerSpecsUnits);
    }
}
