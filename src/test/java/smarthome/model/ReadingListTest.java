package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ReadingListTest {

    @Test
    @DisplayName("Ensure newReading method creates local instance of reading and that addReading method adds it readingList")
    void newReading() {
        ReadingList readingList = new ReadingList();
        Reading r1 = readingList.newReading(15, new GregorianCalendar(2019, 2, 2));
        readingList.addReading(r1);
        Reading expected1 = r1;
        Reading result1 = readingList.getReadingList().get(0);
        assertEquals(expected1, result1);

        readingList.addReading(r1);
        int expected2 = 1;
        int result2 = readingList.getReadingList().size();
        assertEquals(expected2, result2);


    }


    @Test
    @DisplayName("Ensure that returns the total values of reading list in a  specific day")
    public void totalValueInGivenDay() {


        ReadingList readingList = new ReadingList();
        Reading r1 = readingList.newReading(15, new GregorianCalendar(2019, 2, 2));
        Reading r2 = readingList.newReading(16, new GregorianCalendar(2019, 2, 2));
        Reading r3 = readingList.newReading(18, new GregorianCalendar(2019, 2, 2));
        Reading r4 = readingList.newReading(20, new GregorianCalendar(2018, 3, 3));


        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);

        double expected1 = 49;

        double result1 = readingList.totalValueInGivenDay(new GregorianCalendar(2019, 2, 2));

        assertEquals(expected1, result1);


    }
}