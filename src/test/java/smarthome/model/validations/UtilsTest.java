package smarthome.model.validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    @Test
    @DisplayName("Ensure that negative value returns false")
    void negativeValueReturnsFalse() {
        double value= -0.5;
        boolean result= Utils.valueIsPositive(value);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that 0 returns false")
    void zeroReturnsFalse() {
        double value= 0;
        boolean result= Utils.valueIsPositive(value);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that positive value returns true")
    void zeroReturnsTrue() {
        double value= 15;
        boolean result= Utils.valueIsPositive(value);
        assertTrue(result);
    }

    @Test
    void seeIfConvertStringToCalendarWithExpectedDateFormatReturnsExpectedDate() throws ParseException {
        Calendar expected= new GregorianCalendar(2018,Calendar.NOVEMBER,15);
        Calendar result=Utils.convertStringToCalendar("20181115");
        assertEquals(expected,result);
    }

    @Test
    void seeIfConvertStringToCalendarWithUnexpectedDateFormatReturnsIncorrectDate() throws ParseException {
        Calendar expected= new GregorianCalendar(1000,Calendar.JANUARY,1);
        Calendar result=Utils.convertStringToCalendar("invalidDate");
        assertEquals(expected,result);
    }
}