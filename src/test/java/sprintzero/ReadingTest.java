package sprintzero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


public class ReadingTest {


    @Test
    @DisplayName("Constructor with date and value")
    public void getReadingDateAndValue(){

        //Arrange
        GregorianCalendar c1 = new GregorianCalendar(2018,11,27,21,30);

        //Act
        Reading r1 = new Reading(13,c1);
        double value = r1.returnValueOfReading();

        //Assert
        assertEquals(13,value,0.1);

    }




}