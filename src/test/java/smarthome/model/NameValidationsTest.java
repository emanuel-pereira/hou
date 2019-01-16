package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameValidationsTest {

    @Test
    @DisplayName("Ensure nameIsValid returns null to String containing numbers")
    void nameIsValid() {
        NameValidations n = new NameValidations();
        String name="15adas";
        String expected=null;
        String result=n.nameIsValid(name);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure nameIsValid returns null to empty input")
    void nameIsValid1() {
        NameValidations n = new NameValidations();
        String name=" ";
        String expected=null;
        String result=n.nameIsValid(name);
        assertEquals(expected,result);
    }
}