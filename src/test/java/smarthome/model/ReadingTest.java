package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReadingTest {


    @Test
    @DisplayName("Constructor with date and value")
    public void getReadingDateAndValue(){

        //Arrange
        GregorianCalendar calendar1 = new GregorianCalendar(2018,11,27,21,30);

        //Act
        Reading reading1 = new Reading(13,calendar1);
        double value = reading1.returnValueOfReading();

        //Assert
        assertEquals(13,value,0.1);
    }


    @Test
    @DisplayName("Ensure that method getDateAndTime returns Reading1 dateAndTime attribute")
    void getDateAndTime() {
        //Arrange
        GregorianCalendar calendar1 = new GregorianCalendar(2018,11,27,21,30);

        //Act
        Reading reading1 = new Reading(13,calendar1);
        Calendar dateAndTime = reading1.getDateAndTime();

        //Assert
        assertEquals(calendar1,dateAndTime);
    }

    @Test
    @DisplayName("Ensure get month of reading returns 11.")
    void getMonthOfReading() {
        GregorianCalendar calendar1 = new GregorianCalendar(2018,11,27,21,30);
        Reading reading1 = new Reading(13,calendar1);
        int expected= 11;
        int result = reading1.getMonthOfReading();
        assertEquals(expected,result);

    }
}