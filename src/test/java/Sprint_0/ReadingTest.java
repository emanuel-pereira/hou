package Sprint_0;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class ReadingTest {

//Tests if a sensor is reading
//Tests if a reading has a date and an hour


    //Tests if a reading has a value
    @Test
    public void readValueConstructor(){

        //Arrange
       double inputValue = 13;
       double result;
       double expectedResult = 13;

        //Act
        Reading r1 = new Reading(inputValue);
        result = r1.getReadingValue();


        //Assert
        assertEquals(expectedResult,result);

    }

    //Tests if a reading returns null when no value is defined

    @Test
    public void getValueEmptyConstructor(){
        Reading r1 = new Reading();

        //Arrange
        double result;
        double expectedResult = 0.0;

        //Act
        result = r1.getReadingValue();


        //Assert
        assertEquals(expectedResult,result);

    }




    /*@Test
    public void TimeConstructor(){

        //Arrange
        Date inputtime = ;
        double result;
        double expectedResult = 13;

        //Act
        Reading r1 = new Reading(inputtime);
        result = r1.getReadingValue();


        //Assert
        assertEquals(expectedResult,result);

    }*/

}