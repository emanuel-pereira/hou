package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    @DisplayName("Ensure nameIsValid returns false to String containing numbers")
    void nameIsValid() {
        Name n = new Name("");
        String name ="15adas";
        boolean result=n.nameIsValid(name);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure nameIsValid returns false to empty input")
    void nameIsValid1() {
        Name n = new Name("");
        String name=" ";
        boolean result=n.nameIsValid(name);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        Name n = new Name ("");
        String name="Fridge1 - LG";
        boolean result=n.alphanumericName(name);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does not accept special characters such as + or /")
    void invalidNameReturnsFalse() {
        Name n = new Name("");
        String name="Fridge1 / LG++";
        boolean result=n.alphanumericName(name);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does accept empty string")
    void emptyStringReturnsFalse() {
        Name n = new Name("");
        String name=" ";
        boolean result=n.alphanumericName(name);
        assertFalse(result);
    }
}