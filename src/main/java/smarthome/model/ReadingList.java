package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ReadingList {

    private List<Reading> mReadingList;

    public ReadingList() {
        mReadingList = new ArrayList<>();
    }

    public Reading newReading(double readValue, Calendar timeOfReading) {
        return new Reading(readValue, timeOfReading);
    }

    public boolean addReading(Reading newReading) {
        if (mReadingList.contains(newReading) || (newReading == null))
            return false;
        return mReadingList.add(newReading);
    }

    public List<Reading> getReadingList() {
        return mReadingList;
    }

    public Reading getLastReading() {
        return mReadingList.get(mReadingList.size() - 1);
    }

    public int size() {
        return mReadingList.size();
    }


    /**
     * Method to get total rainfall in a given day
     *
     * @param inputDate
     * @return totalRainfallValue
     */
    public double totalValueInGivenDay(Calendar inputDate) {
        double totalRainfallValue = 0;

        for (Reading reading : mReadingList) {
            if (reading.getDateAndTime().get(Calendar.DATE) == inputDate.get(Calendar.DATE)) {
                totalRainfallValue = reading.returnValueOfReading() + totalRainfallValue;
            }
        }
        return totalRainfallValue;
    }


    public double dailyAvgOfReadings(int day) {
        double sum = 0;
        int numberOfReadings = 0;

        for (Reading reading : mReadingList) {
            if (reading.getDateAndTime().get(Calendar.DAY_OF_MONTH) == day) {
                sum += reading.returnValueOfReading();
                numberOfReadings++;
            }
        }
        if (numberOfReadings == 0) {
            return -1000;
        }
        return sum / numberOfReadings;

    }


    public double getValueOfReadingsInTimeInterval(Calendar startDate, Calendar endDate) {
        double totalValue = 0;
        for (Reading reading : mReadingList) {
            Calendar readingDate = reading.getDateAndTime();

            if (readingDate.after(startDate) && readingDate.before(endDate)
                    || readingDate.equals(startDate)
                    || readingDate.equals(endDate)) {
                totalValue += reading.returnValueOfReading();
            }
        }
        return totalValue;
    }


    public ReadingList getReadingsInSpecificDay(Calendar date) {
        ReadingList readingListInDate = new ReadingList();

        for (Reading reading : mReadingList) {
            int rYear = reading.getDateAndTime().get(Calendar.YEAR);
            int rMonth = reading.getDateAndTime().get(Calendar.MONTH);
            int rDay = reading.getDateAndTime().get(Calendar.DAY_OF_MONTH);
            Calendar date1 = new GregorianCalendar(rYear, rMonth, rDay);
            if (date.equals(date1))
                readingListInDate.addReading(reading);
        }
        return readingListInDate;
    }

    /**
     * Method to get a list of readings that have a date that is in a given time period
     *
     * @param startDate
     * @param endDate
     * @return list of the readings with dates in a time interval
     */

    public ReadingList getReadingsInPeriod(Calendar startDate, Calendar endDate) {
        ReadingList readingListInPeriod = new ReadingList();

        for (Reading reading : mReadingList) {
            Calendar readingDate = reading.getDateAndTime();

            if (readingDate.after(startDate) && readingDate.before(endDate)
                    || readingDate.equals(endDate)
                    || readingDate.equals((startDate))) {
                readingListInPeriod.addReading(reading);
            }
        }
        return readingListInPeriod;
    }


}
