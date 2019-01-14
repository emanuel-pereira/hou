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
}