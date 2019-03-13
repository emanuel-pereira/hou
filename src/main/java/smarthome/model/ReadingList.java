package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ReadingList {

    private List<Reading> readingList;

    public ReadingList() {
        readingList = new ArrayList<>();
    }

    public Reading newReading(double readValue, Calendar timeOfReading) {
        return new Reading(readValue, timeOfReading);
    }

    public boolean addReading(Reading newReading) {
        if (readingList.contains(newReading) || (newReading == null))
            return false;
        return readingList.add(newReading);
    }

    public List<Reading> getReadingList() {
        return readingList;
    }

    public Reading getLastReading() {
        return readingList.get(readingList.size() - 1);
    }

    public int size() {
        return readingList.size();
    }


    /**
     * Method to get total rainfall in a given day
     *
     * @param inputDate
     * @return totalRainfallValue
     */
    public double totalValueInGivenDay(Calendar inputDate) {
        double totalRainfallValue = 0;

        for (Reading reading : readingList) {
            if (reading.getDateAndTime().get(Calendar.DATE) == inputDate.get(Calendar.DATE)) {
                totalRainfallValue = reading.returnValueOfReading() + totalRainfallValue;
            }
        }
        return totalRainfallValue;
    }

    public boolean checkNumberOfReadingsIsZero(ReadingList readingList) {
        return readingList.size() == 0;
    }

    public double dailyAverageOfReadings(Calendar day) {
        double sum = 0;
        ReadingList dailyReadings = getReadingsInSpecificDay(day);

        if (!checkNumberOfReadingsIsZero(dailyReadings)) {
            for (Reading reading : getReadingsInSpecificDay(day).getReadingList()) {
                if (reading.compareYearMonthDay(day)) {
                    sum = sum + reading.returnValueOfReading();
                }
            }
        }
        return sum / dailyReadings.size();
    }


    public double getValueOfReadingsInTimeInterval(Calendar startDate, Calendar endDate) {
        double totalValue = 0;
        for (Reading reading : readingList) {
            Calendar readingDate = reading.getDateAndTime();

            if (readingDate.after(startDate) && readingDate.before(endDate) || readingDate.equals(endDate)) {
                totalValue += reading.returnValueOfReading();
            }
        }
        return totalValue;
    }


    public ReadingList getReadingsInSpecificDay(Calendar date) {
        ReadingList readingListInDate = new ReadingList();

        for (Reading reading : readingList) {
            if (reading.compareYearMonthDay(date))
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

        for (Reading reading : readingList) {
            Calendar readingDate = reading.getDateAndTime();

            if (readingDate.after(startDate) && readingDate.before(endDate)
                    /*|| readingDate.equals(endDate)*/
                    || readingDate.equals(startDate)) {
                readingListInPeriod.addReading(reading);
            }
        }
        return readingListInPeriod;
    }

    public Reading maxValueInInterval() {
        Reading max = newReading(Double.MIN_VALUE, new GregorianCalendar());
        double value;

        for (Reading reading : this.readingList) {
            value = reading.returnValueOfReading();
            if (value > max.returnValueOfReading())
                max = reading;
        }
        return max;
    }

    public Reading minValueInInterval() {
        Reading min = newReading(Double.MAX_VALUE, new GregorianCalendar());
        double value;

        for (Reading reading : this.readingList) {
            value = reading.returnValueOfReading();
            if (value < min.returnValueOfReading())
                min = reading;
        }
        return min;
    }

    /*public Reading latestMaximumInInterval() {
        Reading max = this.maxValueInInterval();
        Calendar day = Calendar.;
        for (Reading reading : this.readingList) {
            (reading.getDateAndTime().DAY_OF_MONTH)
            max.getDateAndTime().DAY_OF_MONTH
            getReadingsInPeriod(max.getDateAndTime(), max.getDateAndTime());
        }
        return max;
        getDateofReading
    }*/

    public ReadingList dailyMaximumReadings() {
        //extract the first day of the list

        List<Calendar> dates = new ArrayList<>(); //this list saves all unique dates in the reading list

        ReadingList dailyMax = new ReadingList(); //this is a list containing the maximum reading of a single day
        ReadingList temp = new ReadingList();

        //get all dates in the reading list and fill in the dates list. Move this to another method?

        for (Reading reading : this.readingList) {
            if (!dates.contains(reading.extractYearMonthDay())) {
                dates.add(reading.extractYearMonthDay());
            }
        }


        // Iterate over the dates list, get the readings in those dates and add the maximum to the dailyMax list.
        for (Calendar date : dates) {
            temp = getReadingsInSpecificDay(date);
            double max = temp.maxValueInInterval().returnValueOfReading();
            Reading r = newReading(max, date);
            dailyMax.addReading(r);
        }

        return dailyMax;
    }

}
