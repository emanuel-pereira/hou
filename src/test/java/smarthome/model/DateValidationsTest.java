package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Validations.DateValidations;

import static org.junit.jupiter.api.Assertions.*;

class DateValidationsTest {

    @Test
    @DisplayName("Ensure that yearIsValid returns false to empty string")
    void ensureYearIsValidReturnsfalseWithEmptyInput() {
        DateValidations d = new DateValidations();
        String year="";
        boolean result=d.yearIsValid(year);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that yearIsValid returns false to invalid year 1999")
    void ensureYearIsValidReturnsfalseForInvalidYear() {
        DateValidations d = new DateValidations();
        String year="1999";
        boolean result=d.yearIsValid(year);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that monthIsValid returns false to empty input")
    void monthIsValidReturnsFalseForEmptyInput() {
        DateValidations d = new DateValidations();
        String month="";
        boolean result=d.monthIsValid(month);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that monthIsValid returns false to invalid month")
    void monthIsValidReturnsFalseForEmptyMonth() {
        DateValidations d = new DateValidations();
        String month="15";
        boolean result=d.monthIsValid(month);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns false to empty input")
    void dayIsValid() {
        DateValidations d = new DateValidations();
        String day="";
        boolean result=d.dayIsValid(day,1,2019);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns false to invalid input")
    void dayIsValidReturnsFalseToInvalidDay() {
        DateValidations d = new DateValidations();
        String day="35";
        boolean result=d.dayIsValid(day,11,2018);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns false to day 31 in months with only 30 days")
    void dayIsValidReturnsFalseTo31InSpecificMonths() {
        DateValidations d = new DateValidations();

        String dayApril="31";
        boolean result=d.dayIsValid(dayApril,4,2018);
        assertFalse(result);

        String dayJune="31";
        boolean result1=d.dayIsValid(dayJune,6,2018);
        assertFalse(result1);

        String daySeptember="31";
        boolean result2=d.dayIsValid(daySeptember,9,2018);
        assertFalse(result2);

        String dayNovember="31";
        boolean result3=d.dayIsValid(dayNovember,9,2018);
        assertFalse(result3);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns false to invalid days of February in leap years and normal years")
    void dayIsValidReturnsFalseInInvalidDaysOfFebruary() {
        DateValidations d = new DateValidations();
        String day="29";
        boolean result=d.dayIsValid(day,2,2018);
        assertFalse(result);

        String day2="30";
        boolean result2=d.dayIsValid(day2,2,2020);
        assertFalse(result2);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns true to 29th of february in leap year")
    void dayIsValidReturnsTrueTo29ThOfFebruaryInLeapYear() {
        DateValidations d = new DateValidations();
        String day1="29";
        boolean result1=d.dayIsValid(day1,2,2020);
        assertTrue(result1);
    }


    @Test
    @DisplayName("Ensure hour is valid does not accept invalid hour 29")
    void hourIsValid() {
        DateValidations d = new DateValidations();
        String hour="29";
        boolean result=d.hourIsValid(hour);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure hour is valid does not accept empty input")
    void hourIsValidReturnsFalseToEmptyInput() {
        DateValidations d = new DateValidations();
        String hour=" ";
        boolean result=d.hourIsValid(hour);
        assertFalse(result);
    }
}