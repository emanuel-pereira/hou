package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

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
    @DisplayName("Ensure newReading method creates local instance of reading and that addReading method is not add to readingList")
    void cantAddSameReadingTwice() {
        ReadingList readingList = new ReadingList();
        Reading r1 = readingList.newReading(15, new GregorianCalendar(2019, 2, 2));
        readingList.addReading(r1);
        Reading expected1 = r1;
        Reading result1 = readingList.getReadingList().get(0);
        assertEquals(expected1, result1);

        boolean result2 = readingList.addReading(r1);
        assertEquals(false, result2);


        List<Reading> result3 = readingList.getReadingList ();
        List<Reading> expected3 = Arrays.asList(r1);
        assertEquals(expected3, result3);
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

    @Test
    @DisplayName("Verify that the getMaxReading() method returns the highest recorded reading")
    void maxReading() {
        ReadingList readingList = new ReadingList();
        Reading r1 = readingList.newReading(15, new GregorianCalendar(2019, 2, 2));
        readingList.addReading(r1);
        Reading r2 = readingList.newReading(21, new GregorianCalendar(2019, 1, 12));
        readingList.addReading(r2);
        Reading r3 = readingList.newReading(-5, new GregorianCalendar(2019, 2, 2));
        readingList.addReading(r3);

        double expected = 21;
        double result = readingList.getMaxReading();
        assertEquals(expected,result);
    }
}