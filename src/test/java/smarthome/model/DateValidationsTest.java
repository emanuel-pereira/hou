package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateValidationsTest {

    @Test
    @DisplayName("Ensure that yearIsValid returns null to empty string")
    void ensureYearIsValidReturnsNullWithEmptyInput() {
        DateValidations d = new DateValidations();
        String year="";
        String expected=null;
        String result=d.yearIsValid(year);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that yearIsValid returns null to invalid year 1999")
    void ensureYearIsValidReturnsNullForInvalidYear() {
        DateValidations d = new DateValidations();
        String year="1999";
        String expected=null;
        String result=d.yearIsValid(year);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that monthIsValid returns null to empty input")
    void monthIsValidReturnsNullForEmptyInput() {
        DateValidations d = new DateValidations();
        String month="";
        String expected=null;
        String result=d.monthIsValid(month);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that monthIsValid returns null to invalid month")
    void monthIsValidReturnsNullForEmptyMonth() {
        DateValidations d = new DateValidations();
        String month="15";
        String expected=null;
        String result=d.monthIsValid(month);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns null to empty input")
    void dayIsValid() {
        DateValidations d = new DateValidations();
        String day="";
        String expected=null;
        String result=d.dayIsValid(day,1,2019);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns null to invalid input")
    void dayIsValidReturnsNullToInvalidDay() {
        DateValidations d = new DateValidations();
        String day="35";
        String expected=null;
        String result=d.dayIsValid(day,11,2018);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns null to day 31 in months with only 30 days")
    void dayIsValidReturnsNullTo31InSpecificMonths() {
        DateValidations d = new DateValidations();

        String dayApril="31";
        String expected=null;
        String result=d.dayIsValid(dayApril,4,2018);
        assertEquals(expected,result);

        String dayJune="31";
        String result1=d.dayIsValid(dayJune,6,2018);
        assertEquals(expected,result1);

        String daySeptember="31";
        String result2=d.dayIsValid(daySeptember,9,2018);
        assertEquals(expected,result2);

        String dayNovember="31";
        String result3=d.dayIsValid(dayNovember,9,2018);
        assertEquals(expected,result3);
    }

    @Test
    @DisplayName("Ensure that dayIsValid returns null to invalid days of February in leap years and normal years")
    void dayIsValidReturnsNullInInvalidDaysOfFebruary() {
        DateValidations d = new DateValidations();
        String day="29";
        String expected=null;
        String result=d.dayIsValid(day,2,2018);
        assertEquals(expected,result);

        String day1="29";
        String expected1="29";
        String result1=d.dayIsValid(day1,2,2020);
        assertEquals(expected1,result1);

        String day2="30";
        String expected2=null;
        String result2=d.dayIsValid(day2,2,2020);
        assertEquals(expected2,result2);
    }


    @Test
    @DisplayName("Ensure hour is valid does not accept invalid hour 29")
    void hourIsValid() {
        DateValidations d = new DateValidations();
        String hour="29";
        String expected=null;
        String result=d.hourIsValid(hour);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure hour is valid does not accept empty input")
    void hourIsValidReturnsNullToEmptyInput() {
        DateValidations d = new DateValidations();
        String hour=" ";
        String expected=null;
        String result=d.hourIsValid(hour);
        assertEquals(expected,result);
    }
}