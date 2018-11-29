package Sprint_0;

import org.junit.jupiter.api.Test;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


public class ReadingTest {


    //Tests if a reading returns null when no value is defined

    @Test
    public void getRegisteredValueEmptyConstructor(){
        Reading r1 = new Reading();

        //Arrange
        double result;
        double expectedResult = 0.0;

        //Act
        result = r1.returnValueOfReading();


        //Assert
        assertEquals(expectedResult,result);

    }

    @Test
    public void getTimeOfReading(){

        //Arrange
        GregorianCalendar c1 = new GregorianCalendar(2018,11,27,21,30);


        Date endDate = c1.getTime();
        Date expected=c1.getTime();

        Reading r1 = new Reading(13,endDate);
        double value = r1.returnValueOfReading();
        assertEquals(13,value,0.1);
        assertEquals(expected,endDate);
    }


   /* @Test
    public void getTimeOfReadingNotEqual(){

        //Arrange
        GregorianCalendar c1 = new GregorianCalendar(2018,11,27,21,30);
        GregorianCalendar result;
        GregorianCalendar expectedResult;

        int year = c1.get(Calendar.YEAR);
        assertEquals(2018, year);
        int month = c1.get(Calendar.MONTH);      // 0 to 11
        assertEquals(11, month);
        int day = c1.get(Calendar.DAY_OF_MONTH);
        assertEquals(27, day);
        int hour = c1.get(Calendar.HOUR_OF_DAY);
        assertEquals(21, hour);
        int minute = c1.get(Calendar.MINUTE);
        assertEquals(30, minute);


        Reading r1 = new Reading(15,c1);
        double value = r1.returnValueOfReading();
        assertNotEquals(13,value);
    }
    //Tests if a registered reading has a value and a time

    @Test
    public void getTimeOfReadingUnexpectedDate(){

        //Arrange
        GregorianCalendar c1 = new GregorianCalendar(2000,11,27,21,30);
        GregorianCalendar result;
        GregorianCalendar expectedResult;

        int year = c1.get(Calendar.YEAR);
        assertNotEquals(2018, year);
        int month = c1.get(Calendar.MONTH);
        assertEquals(11, month);
        int day = c1.get(Calendar.DAY_OF_MONTH);
        assertEquals(27, day);
        int hour = c1.get(Calendar.HOUR_OF_DAY);
        assertEquals(21, hour);
        int minute = c1.get(Calendar.MINUTE);
        assertEquals(30, minute);


        Reading r1 = new Reading(13,c1);
        double value = r1.returnValueOfReading();
        assertEquals(13,value,0.1);
    }*/

}