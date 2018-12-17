package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


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




}