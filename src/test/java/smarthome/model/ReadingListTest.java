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
        Reading expected1= r1;
        Reading result1= readingList.getReadingList().get(0);
        assertEquals(expected1,result1);

        readingList.addReading(r1);
        int expected2= 1;
        int result2= readingList.getReadingList().size();
        assertEquals(expected2,result2);


    }
}