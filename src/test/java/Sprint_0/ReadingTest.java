package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ReadingTest {

//Tests if a sensor is reading
//Tests if a reading has a date and an hour


    @Test
    public void readValueConstructor(){

        //Arrange
       double inputvalue = 13;
       double result;
       double expectedResult = 13;

        //Act
        Reading r1 = new Reading(inputvalue);
        result = r1.getReading();


        //Assert
        assertEquals(expectedResult,result);

    }


}