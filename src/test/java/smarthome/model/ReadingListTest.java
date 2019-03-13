package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
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


        List<Reading> result3 = readingList.getReadingList();
        List<Reading> expected3 = Arrays.asList(r1);
        assertEquals(expected3, result3);
    }

    @Test
    @DisplayName("Ensure newReading method creates local instance of reading and that addReading null is not add to readingList")
    void cantAddSameReadingNull() {
        ReadingList readingList = new ReadingList();
        Reading r1 = readingList.newReading(15, new GregorianCalendar(2019, 2, 2));
        readingList.addReading(r1);
        Reading expected1 = r1;
        Reading result1 = readingList.getReadingList().get(0);
        assertEquals(expected1, result1);

        Reading r2 = null;
        boolean result2 = readingList.addReading(r2);
        assertFalse(result2);


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
    void totalValueInGivenDay() {


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


    //TODO: criar testes adicionais
    @Test
    void dailyAverageOfReadings(){

        ReadingList readingList = new ReadingList();

        GregorianCalendar date = new GregorianCalendar(2018,11,5);
        GregorianCalendar date2 = new GregorianCalendar(2019,12,3);

        Reading r2 = new Reading(18, date);
        Reading r3 = new Reading(22, date);
        Reading r4 = new Reading(27, date);
        Reading r5 = new Reading(31, date2);

        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);
        readingList.addReading(r5);


        double result = readingList.dailyAverageOfReadings(date);
        assertEquals(22.3, result, 0.1);

    }

    @Test
    void dailyAverageOfReadingsNaN(){

        ReadingList readingList = new ReadingList();

        GregorianCalendar date = new GregorianCalendar(2018,11,5);
        GregorianCalendar date2 = new GregorianCalendar(2019,12,3);

        Reading r2 = new Reading(18, date);
        Reading r3 = new Reading(22, date);
        Reading r4 = new Reading(27, date);
        Reading r5 = new Reading(31, date2);

        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);
        readingList.addReading(r5);


        double result = readingList.dailyAverageOfReadings(new GregorianCalendar(2018,12,12));
        assertEquals(Double.NaN, result, 0.1);

    }


    @Test
    void getTotalOfReadingsInTimeInterval() {
        ReadingList readingList = new ReadingList();

        Reading r2 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 15));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r4 = new Reading(27, new GregorianCalendar(2018, 11, 5, 0, 45));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 5, 1, 00));

        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList.addReading(r4);
        readingList.addReading(r5);
        double expected = 80;
        double result = readingList.getValueOfReadingsInTimeInterval(new GregorianCalendar(2018, 11, 5, 0, 15), new GregorianCalendar(2018, 11, 5, 1, 0));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if the readings in a readingList are in the given time period")
    void getReadingsInTimePeriod() {

        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 4);

        GregorianCalendar date1 = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 4);
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

        List<Reading> result = rL1.filterByDate(startDate, endDate).getReadingList();

        assertEquals(expectedResult, result);
    }

    @Test
    void maxValueInInterval() {
        GregorianCalendar date1 = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 4);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 6);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date3);
        Reading r4 = new Reading(22.0, date4);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        //Reading List Crop
        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 4);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 5);
        ReadingList croppedList = rL1.filterByDate(startDate, endDate);

        //Get the max value of the Reading List
        Reading maxReading = croppedList.maxValueInInterval();
        assertEquals(34.2, maxReading.returnValueOfReading());
    }

    @Test
    void minValueInInterval() {
        GregorianCalendar date1 = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 4);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 6);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date3);
        Reading r4 = new Reading(22.0, date4);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        //Reading List Crop
        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 4);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 5);
        ReadingList croppedList = rL1.filterByDate(startDate, endDate);

        //Get the max value of the Reading List
        Reading minReading = croppedList.minValueInInterval();
        assertEquals(20.0, minReading.returnValueOfReading());
    }

    @Test
    void maxValueInInterval2() {
        GregorianCalendar date0 = new GregorianCalendar(2017, 5, 31);
        GregorianCalendar date1 = new GregorianCalendar(2017, 6, 1, 8, 0);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 1, 16, 0);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 1, 24, 0);
        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 2, 8, 0);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 2, 16, 0);
        GregorianCalendar date6 = new GregorianCalendar(2017, 6, 2, 24, 0);
        GregorianCalendar date7 = new GregorianCalendar(2017, 6, 3);

        Reading r1 = new Reading(10, date1);
        Reading r2 = new Reading(20, date2);
        Reading r3 = new Reading(5, date3);
        Reading r4 = new Reading(11, date4);
        Reading r5 = new Reading(21, date5);
        Reading r6 = new Reading(6, date6);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);
        rL1.addReading(r5);
        rL1.addReading(r6);

        //Reading List Crop
        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 3);
        ReadingList croppedList = rL1.filterByDate(startDate, endDate);

        //Get the max value of the Reading List
        Reading maxReading = croppedList.maxValueInInterval();
        assertEquals(21, maxReading.returnValueOfReading());
    }

    @Test
    void dailyMaximumReadings() {
        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r0);
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);
        rL1.addReading(r5);
        rL1.addReading(r6);
        rL1.addReading(r7);
        rL1.addReading(r8);
        rL1.addReading(r9);
        rL1.addReading(r10);
        rL1.addReading(r11);
        rL1.addReading(r12);
        rL1.addReading(r13);
        rL1.addReading(r14);
        rL1.addReading(r15);

        //Reading List Crop
        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 2);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 4);
        //ReadingList croppedList = rL1.filterByDate(startDate, endDate);

        //Get the max value of the Reading List
        ReadingList maxReadingList = rL1.dailyMaximumReadings();
        //assertEquals(21, maxReading);
        Reading max = maxReadingList.maxValueInInterval();
        Reading expected = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1));
        assertEquals(6, max.returnValueOfReading());
        assertTrue(expected.isSameDay(max.getDateAndTime()));
    }

    @Test
    void dailyMinimumReadings() {
        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r0);
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);
        rL1.addReading(r5);
        rL1.addReading(r6);
        rL1.addReading(r7);
        rL1.addReading(r8);
        rL1.addReading(r9);
        rL1.addReading(r10);
        rL1.addReading(r11);
        rL1.addReading(r12);
        rL1.addReading(r13);
        rL1.addReading(r14);
        rL1.addReading(r15);

        //Reading List Crop
        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 2);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 4);
        //ReadingList croppedList = rL1.filterByDate(startDate, endDate);

        //Get the max value of the Reading List
        ReadingList minReadingList = rL1.dailyMinimumReadings();

        Reading min = minReadingList.minValueInInterval();
        Reading expected = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1));
        assertEquals(4, min.returnValueOfReading());
        assertTrue(expected.isSameDay(min.getDateAndTime()));
    }

    @Test
    void dailyAmplitudes() {
        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r0);
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);
        rL1.addReading(r5);
        rL1.addReading(r6);
        rL1.addReading(r7);
        rL1.addReading(r8);
        rL1.addReading(r9);
        rL1.addReading(r10);
        rL1.addReading(r11);
        rL1.addReading(r12);
        rL1.addReading(r13);
        rL1.addReading(r14);
        rL1.addReading(r15);

        //Reading List Crop
        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 2);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 4);
        //ReadingList croppedList = rL1.filterByDate(startDate, endDate);

        //Get the max value of the Reading List
        ReadingList maxReadingList = rL1.dailyMaximumReadings();
        ReadingList minReadingList = rL1.dailyMinimumReadings();

        Reading min = minReadingList.minValueInInterval();
        Reading expected = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1));
        assertEquals(4, min.returnValueOfReading());
        assertTrue(expected.isSameDay(min.getDateAndTime()));
    }

    @Test
    void dailyAmplitude() {
        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r0);
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);
        rL1.addReading(r5);
        rL1.addReading(r6);
        rL1.addReading(r7);
        rL1.addReading(r8);
        rL1.addReading(r9);
        rL1.addReading(r10);
        rL1.addReading(r11);
        rL1.addReading(r12);
        rL1.addReading(r13);
        rL1.addReading(r14);
        rL1.addReading(r15);

        //Reading List Crop
        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 2);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 4);
        //ReadingList croppedList = rL1.filterByDate(startDate, endDate);

        //Get the amplitude values of the Reading List
        List<Reading> ampReadingList = rL1.dailyAmplitude();

        assertEquals(4, ampReadingList.size());
    }
}
