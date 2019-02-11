package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LampTest {

    @Test
    void setNewLuminousFluxTest() {
        Lamp l = new Lamp(2);
        String luminousFlux = "Luminous Flux";
        l.setAttributeValue(luminousFlux, "16");
        int expected = 16;
        int result = l.getLuminousFlux();
        assertEquals(expected, result);
    }

    @Test
    void showDeviceSpecsListAttributesInString() {
        Lamp l = new Lamp(2);
        List<String> expected = Arrays.asList("Luminous Flux");
        List<String> result = l.getAttributesNames();
        assertEquals(expected, result);
    }

    @Test
    void showDeviceAttributeNamesAndValuesTest() {
        Lamp lamp = new Lamp();
        String result = lamp.showDeviceAttributeNamesAndValues();
        String expected ="3 - Luminous Flux : 0\n";
        assertEquals(expected,result);
    }

    @Test
    void getEnergyConsumptionTest() {
        Lamp lamp = new Lamp();
        double result = lamp.getEnergyConsumption();
        double expected = 0;
        assertEquals(expected,result);
    }

    @Test
    void setAndGetType(){
        Lamp lamp = new Lamp();
        DeviceType lampType = new DeviceType("Lamp");
        lamp.setType(lampType);
        String result = lamp.getDeviceType().getDeviceTypeName();
        assertEquals("Lamp",result);

    }
}
