package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.*;

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

    /**
     * Method to get total rainfall in a given day
     *
     * @param inputDate
     * @return totalRainfallValue
     */

    public double totalValueInGivenDay(GregorianCalendar inputDate) {
        GregorianCalendar date = inputDate;
        double totalRainfallValue = 0;

        for (Reading reading : mReadingList)
            if (reading.getDateAndTime().equals(inputDate))
                totalRainfallValue = reading.returnValueOfReading() + totalRainfallValue;

        return totalRainfallValue;
    }


    // US610

    /**
     * This method finds the highest recorded reading in a reading list
     *
     * @return maximum reading
     */

    public double getMaxReading() {
        Double maxReading = Double.MIN_VALUE;
        for (Reading foo : mReadingList
        ) {
            if (foo.returnValueOfReading() >= maxReading) {
                maxReading = foo.returnValueOfReading();
            }
        }
        return maxReading;
    }


    /**
     * This method finds the highest recorded reading in a reading list for a specific day
     *
     * @param date is the specific date in "YYYY-MM-DD" format, e.g. "2018-7-22"
     * @return maximum reading in the specified date
     */
    public double getMaxDailyReading(String date) {
        Double maxReading = Double.MIN_VALUE;
        for (Reading r : mReadingList) {
            if (r.getDateOfReadingAsString() == date && r.returnValueOfReading() >= maxReading) {
                maxReading = r.returnValueOfReading();
            }
        }
        return maxReading;
    }

    /**
     * This method outputs the readings as a list with the format "[YYYY-MM-DD] reading"
     * Needed for the UI (US610). Will be deprecated in the future but is needed for the time being.
     */

    public String getReadingsWithDateAsString() {
        StringBuilder output = new StringBuilder();
        for(Reading r : mReadingList) {
            output.append("["+r.getDateOfReadingAsString()+"] ");
            output.append(r.returnValueOfReading()+"\n");
        }
        return output.toString();
    }
}