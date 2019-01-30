package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LampTest {

    @Test
    void setNewLuminousFluxTest() {
        Lamp l = new Lamp(DeviceType.LAMP, 2);
        String luminousFlux = "4 - Luminous Flux : " + l.getLuminousFlux();

        l.setAttributeValue(luminousFlux, "16");

        int expected = 16;
        int result = l.getLuminousFlux();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER, 10);
        String Capacity = "4 - Dishwater Capacity : " + dw.getCapacity();

        dw.setAttributeValue(Capacity, "20");
        assertEquals(20, dw.getCapacity());

    }

    @Test
    void showDeviceSpecsListAttributesInString() {
        Lamp l = new Lamp(DeviceType.LAMP, 2);
        List<String> expected = Arrays.asList("4 - Luminous Flux : " + l.getLuminousFlux());
        List<String> result = l.getDeviceAttributesInString();
        assertEquals(expected, result);
    }

    @Test
    void getType() {
        Lamp l = new Lamp(DeviceType.LAMP, 2);
        DeviceType expected = DeviceType.LAMP;
        DeviceType result = l.getType();
        assertEquals(expected, result);
    }

}
