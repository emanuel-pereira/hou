package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


        List<Reading> result3 = readingList.getReadingList();
        List<Reading> expected3 = Arrays.asList(r1);
        assertEquals(expected3, result3);
    }

    @Test
    @DisplayName("Tests if the size of the list of readings is returned")
    void getReadingListSize() {

        ReadingList readingList = new ReadingList();
        Reading r1 = readingList.newReading(15, new GregorianCalendar(2019, 2, 2));
        Reading r2 = readingList.newReading(16, new GregorianCalendar(2019, 2, 2));
        Reading r3 = readingList.newReading(18, new GregorianCalendar(2019, 2, 2));
        Reading r4 = readingList.newReading(20, new GregorianCalendar(2018, 3, 3));


        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);

        int result = readingList.size();

        assertEquals(4, result);

    }


    @Test
    @DisplayName("Ensure that returns the total values of reading list in a specific day")
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
    void getReadingsInSpecificDay() {
        Calendar date = new GregorianCalendar(2018, 1, 1);
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(25, new GregorianCalendar(2018, 1, 1, 15, 30));
        Reading r2 = new Reading(25, new GregorianCalendar(2018, 1, 1, 17, 30));
        Reading r3 = new Reading(25, new GregorianCalendar(2018, 1, 1, 15, 30));
        Reading r4 = new Reading(25, new GregorianCalendar(2018, 1, 1, 15, 30));
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 1, 1, 15, 30));
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);
        readingList.addReading(r5);
        int result = readingList.getReadingsInSpecificDay(date).getReadingList().size();
        int expected = 5;
        assertEquals(expected, result);

    }

    @Test
    void getTotalOfReadingsInTimeInterval() {
        ReadingList readingList = new ReadingList();

        Reading r2 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 15));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r4 = new Reading(27, new GregorianCalendar(2018, 11, 5, 0, 45));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 6, 1, 00));

        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);
        readingList.addReading(r5);
        double expected = 67;
        double result = readingList.getValueOfReadingsInTimeInterval(new GregorianCalendar(2018, 11, 5, 0, 15), new GregorianCalendar(2018, 11, 5, 1, 0));
        assertEquals(expected, result);
    }

    @Test
    void getTotalOfReadingsInTimeIntervalDevices() {
        ReadingList readingList = new ReadingList();

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 5, 0, 15);
        GregorianCalendar endDate = new GregorianCalendar(2018, 11, 5, 1, 0);

        Reading r2 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 15));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r4 = new Reading(27, new GregorianCalendar(2018, 11, 5, 0, 45));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 6, 1, 00));

        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);
        readingList.addReading(r5);
        double expected = 49;
        double result = readingList.getValueOfReadingsInTimeIntervalDevices(startDate,endDate);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if the readings in a readingList are in the given time period")
    void getReadingsInTimePeriod() {

        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 4);

        GregorianCalendar date1 = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 3);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 5);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        List<Reading> expectedResult = Arrays.asList(r1, r2, r3);

        List<Reading> result = rL1.getReadingsInPeriod(startDate, endDate).getReadingList();

        assertEquals(expectedResult, result);
    }

}
