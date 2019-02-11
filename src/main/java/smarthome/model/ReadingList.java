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

    public double getValueOfReadingsInTimeInterval(Calendar startDate, Calendar endDate) {
        double totalValue = 0;
        for (Reading reading : mReadingList) {
            Calendar readingTime = reading.getDateAndTime();
            if (readingTime.after(startDate) && readingTime.before(endDate) || readingTime.equals(endDate)) {
                totalValue += reading.returnValueOfReading();
            }
        }
        return totalValue;

    }

    public ReadingList getReadingsInSpecificDay(Calendar date) {
        ReadingList readingListInDate = new ReadingList();

        for (Reading reading : mReadingList) {
            int rYear = reading.getDateAndTime().get(Calendar.YEAR);
            int rMonth=reading.getDateAndTime().get(Calendar.MONTH);
            int rDay=reading.getDateAndTime().get(Calendar.DAY_OF_MONTH);
            Calendar date1= new GregorianCalendar(rYear,rMonth,rDay);
            if (date.equals(date1))
                readingListInDate.addReading(reading);
        }
        return readingListInDate;
    }
}
