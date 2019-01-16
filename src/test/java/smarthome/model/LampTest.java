package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LampTest {

    @Test
    @DisplayName("Ensure getType from Lamp instance returns correct type")
    void getType() {
        Lamp lamp = new Lamp(DeviceType.LAMP,450);

        String expected= "Lamp";
        String result=lamp.getType();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure getTypeFromIndex from Lamp instance returns correct type")
    void getTypeFromIndex() {
        Lamp lamp = new Lamp(DeviceType.LAMP,450);

        String expected= "Lamp";
        String result=lamp.getTypeFromIndex(12);

        assertEquals(expected,result);
    }
    @Test
    void setNewLuminousFluxTest(){
        Lamp l = new Lamp(DeviceType.LAMP,2);
        l.setLuminousFlux(5);
        int result = l.getLuminousFlux();
        assertEquals(5,result);
    }
    @Test
    void showDeviceSpecsListAttributesInString() {
        Lamp l = new Lamp(DeviceType.LAMP,2);
        String result = l.showDeviceSpecsListAttributesInString();
        assertEquals("4 - Luminous Flux : " +l.getLuminousFlux(),result);
    }

}