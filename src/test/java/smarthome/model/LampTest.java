package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LampTest {

    @Test
    void setNewLuminousFluxTest(){
        Lamp l = new Lamp(2);
        l.setLuminousFlux(5);
        int result = l.getLuminousFlux();
        assertEquals(5,result);
    }
    @Test
    void showDeviceSpecsListAttributesInString() {
        Lamp l = new Lamp(2);
        String result = l.showDeviceSpecsListAttributesInString();
        assertEquals("4 - Luminous Flux : " +l.getLuminousFlux(),result);
    }

}