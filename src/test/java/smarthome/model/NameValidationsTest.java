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

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        NameValidations n = new NameValidations();
        String name="Fridge1 - LG";
        String expected="Fridge1 - LG";
        String result=n.alphanumericName(name);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does not accept special characters such as + or /")
    void invalidNameReturnsNull() {
        NameValidations n = new NameValidations();
        String name="Fridge1 / LG++";
        String expected=null;
        String result=n.alphanumericName(name);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does not empty string")
    void emptyStringReturnsNull() {
        NameValidations n = new NameValidations();
        String name=" ";
        String expected=null;
        String result=n.alphanumericName(name);
        assertEquals(expected,result);
    }



}