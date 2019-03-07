package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LampSpecsTest {

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        LampSpecs lamp = new LampSpecs("Lamp");

        String expected = "Lamp";
        String result = lamp.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set new values to all attributes and get all correct")
    void getAttributeValues() {
        LampSpecs lamp = new LampSpecs("Lamp");

        lamp.setAttributeValue("Illuminance", 470);

        double illuminance = lamp.getAttributeValue("Illuminance");
        assertEquals(470, illuminance);
    }

    @Test
    @DisplayName("Set new units to the two first attributes and get all correct")
    void getAttributeUnits() {
        LampSpecs lamp = new LampSpecs("Lamp");

        lamp.setAttributeValue("Illuminance", 470);

        String illuminance = lamp.getAttributeUnit("Illuminance");
        assertEquals("Lumens", illuminance);
    }

    @Test
    void getAttributesNames() {
        LampSpecs lamp = new LampSpecs("Lamp");

        List<String> attributesNames = lamp.getAttributesNames();
        List<String> expected = Arrays.asList ("Illuminance");

        assertEquals(expected, attributesNames);
    }

}