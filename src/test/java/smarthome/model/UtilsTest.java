package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    @DisplayName("Check if round function returns a valued rounded to two decimal places")

    void round() {
        double expected = 25.26;
        double result = Utils.round(25.2589,2);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Frontier test of round method")
    void roundToZeroDecimalPlacesReturnsInteger() {
        double expected = 25;
        double result = Utils.round(25.2589,0);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Check if Illegal Argument Exception is thrown with negative decimalPlaces parameter")
    void roundReturnsIllegalArgumentException() {
        boolean thrown = false;
        try {
            Utils.round(25.2589,-2);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}