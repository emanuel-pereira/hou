package smarthome.model.validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidationsTest {

    @Test
    @DisplayName("Ensure nameIsValid returns false to String containing numbers")
    void nameIsValid() {
        NameValidations n = new NameValidations ();
        String name="15adas";
        boolean result=n.nameIsValid(name);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure nameIsValid returns false to empty input")
    void nameIsValid1() {
        NameValidations n = new NameValidations ();
        String name=" ";
        boolean result=n.nameIsValid(name);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        NameValidations n = new NameValidations ();
        String name="Fridge1 - LG";
        boolean result=n.alphanumericName(name);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does not accept special characters such as + or /")
    void invalidNameReturnsFalse() {
        NameValidations n = new NameValidations ();
        String name="Fridge1 / LG++";
        boolean result=n.alphanumericName(name);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does accept empty string")
    void emptyStringReturnsFalse() {
        NameValidations n = new NameValidations ();
        String name=" ";
        boolean result=n.alphanumericName(name);
        assertFalse(result);
    }



}