package Sprint0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


public class ReadingTest {


    @Test
    @DisplayName("Constructor with date and value")
    public void getReadingDateAndValue(){

        //Arrange
        GregorianCalendar c1 = new GregorianCalendar(2018,11,27,21,30);


        Date endDate = c1.getTime();
        Date expected=c1.getTime();

        Reading r1 = new Reading(13,endDate);
        double value = r1.returnValueOfReading();
        assertEquals(13,value,0.1);
        assertEquals(expected,endDate);
    }




}