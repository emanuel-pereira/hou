package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.ReadingDTO;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


public class ReadingTest {


    @Test
    @DisplayName("Constructor with date and value")
    public void getReadingDateAndValue() {

        //Arrange
        GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 27, 21, 30);

        //Act
        Reading reading1 = new Reading(13, calendar1);
        double value = reading1.returnValue();

        //Assert
        assertEquals(13, value, 0.1);
    }


    @Test
    @DisplayName("Ensure that method getDateAndTime returns Reading1 dateAndTime attribute")
    void getDateAndTime() {
        //Arrange
        GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 27, 21, 30);

        //Act
        Reading reading1 = new Reading(13, calendar1);
        Calendar dateAndTime = reading1.getDateAndTime();
        //Assert

        assertEquals(calendar1, dateAndTime);
    }

    @Test
    @DisplayName("Ensure get month of reading returns 11.")
    void getMonthOfReading() {
        GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 27, 21, 30);
        Reading reading1 = new Reading(13, calendar1);
        int expected = 11;
        int result = reading1.getMonthOfReading();
        assertEquals(expected, result);

    }

    //TODO: display name
    @Test
    void compareYearMonthDay() {
        GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 27);
        GregorianCalendar calendar2 = new GregorianCalendar(2018, 11, 27);

        Reading reading1 = new Reading(13, calendar1);

        boolean result = reading1.isSameDay(calendar2);

        assertTrue(result);

    }

    //TODO: display name
    @Test
    void compareDifferentYearMonthDay() {
        GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 27);
        GregorianCalendar calendar2 = new GregorianCalendar(2018, 11, 28);

        Reading reading1 = new Reading(13, calendar1);

        boolean result = reading1.isSameDay(calendar2);

        assertFalse(result);
    }


    @Test
    void getDateOfReadingAsString() {
        GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 31, 21, 30);
        Reading reading1 = new Reading(13, calendar1);

        String expected = "2018-12-31";
        String result = reading1.getDateOfReadingAsString();
        assertEquals(expected, result);
    }

    @Test
    void getUnitValueOfReading() {
        GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 31, 21, 30);
        Reading reading1 = new Reading(12, calendar1, "Celsius");
        String expected = "Fahrenheit";
        reading1.setUnit(expected);
        String result = reading1.getUnit();
        assertEquals(expected, result);
    }

    @Test
    void toDTOTest(){
        GregorianCalendar date = new GregorianCalendar(2018, 11, 31, 21, 30);
        Reading reading = new Reading();
        reading.setReadingValue(12);
        reading.setDateAndTime(date);
        reading.setUnit("F");

        ReadingDTO dto = reading.toDTO();

        double expected = reading.getReadingValue();
        double result = dto.getReadingValue();

        assertEquals(expected,result);
    }

    @Test
    void convertToCelsiusValueTest () {
        GregorianCalendar date = new GregorianCalendar(2018, 11, 31, 21, 30);
        Reading reading = new Reading();
        reading.setReadingValue(40);
        reading.setDateAndTime(date);
        reading.setUnit("F");

        double expected = 4.44 ;
        double result = reading.convertToCelsius();

        assertEquals(expected,result);
    }

    @Test
    void convertToCelsiusUnitTest () {
        GregorianCalendar date = new GregorianCalendar(2018, 11, 31, 21, 30);
        Reading reading = new Reading();
        reading.setReadingValue(40);
        reading.setDateAndTime(date);
        reading.setUnit("F");

        reading.convertToCelsius();

        String expected = "C";
        String result = reading.getUnit();

        assertEquals(expected,result);
    }

    @Test
    void convertToCelsiusTestFalse() {
        GregorianCalendar date = new GregorianCalendar(2018, 11, 31, 21, 30);
        Reading reading = new Reading();
        reading.setReadingValue(40);
        reading.setDateAndTime(date);
        reading.setUnit("C");

        double expected = 40 ;
        double result = reading.convertToCelsius();

        assertEquals(expected,result);
    }

    @Test
    void extractYearMonthDayTest() {
        GregorianCalendar date = new GregorianCalendar(2018, 11,0,0,0);
        Reading reading = new Reading();
        reading.setReadingValue(40);
        reading.setDateAndTime(date);
        reading.setUnit("C");

        Calendar expected = reading.getDateAndTime();//.getTime();
        GregorianCalendar result = reading.extractYearMonthDay();//.getTime();

        assertEquals(expected,result);
    }

}
