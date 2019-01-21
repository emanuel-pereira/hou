package smarthome.model.Validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Validations.NumberValidations;

import static org.junit.jupiter.api.Assertions.*;

class NumberValidationsTest {

    @Test
    @DisplayName("Ensure that negative value returns false")
    void negativeValueReturnsFalse() {
        NumberValidations n= new NumberValidations();
        double value= -0.5;
        boolean result= n.valueIsPositive(value);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that 0 returns false")
    void zeroReturnsFalse() {
        NumberValidations n= new NumberValidations();
        double value= 0;
        boolean result= n.valueIsPositive(value);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that positive value returns true")
    void zeroReturnsTrue() {
        NumberValidations n= new NumberValidations();
        double value= 15;
        boolean result= n.valueIsPositive(value);
        assertTrue(result);
    }

}