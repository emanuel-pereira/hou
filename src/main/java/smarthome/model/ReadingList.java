package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ReadingList {

    private List<Reading> listOfReadings;

    public ReadingList() {
        this.listOfReadings = new ArrayList<>();
    }

    public Reading newReading(double readValue, Calendar timeOfReading) {
        return new Reading(readValue, timeOfReading);
    }

    public boolean addReading(Reading newReading) {
        if (this.listOfReadings.contains(newReading))
            return false;
        if (newReading == null)
            return false;
        if (!checkIfReadingHasNotSameValues(newReading))
            return false;
        return this.listOfReadings.add(newReading);
    }

    public Reading getLastReading() {
        int size = this.listOfReadings.size();
        return this.listOfReadings.get(size - 1);
    }

    public List<Reading> getReadingsList() {
        return this.listOfReadings;
    }

    public int size() {
        return this.listOfReadings.size();
    }


    /**
     * Method to get total rainfall in a given day
     *
     * @param inputDate
     * @return totalRainfallValue
     */
    public double totalValueInGivenDay(Calendar inputDate) {
        double totalRainfallValue = 0;

        for (Reading reading : this.listOfReadings) {
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
            for (Reading reading : getReadingsInSpecificDay(day).getReadingsList()) {
                if (reading.isSameDay(day)) {
                    sum = sum + reading.returnValueOfReading();
                }
            }
        }
        return sum / dailyReadings.size();
    }

    /**
     * @param startDateTime calendar parameter representing startDateTime
     * @param endDateTime   calendar parameter representing endDateTime
     * @return sums up the value of readings in the ]startDateTime - endDateTime] interval.
     */
    public double getValueOfReadingsInTimeInterval(Calendar startDateTime, Calendar endDateTime) {
        double totalValue = 0;
        for (Reading reading : this.listOfReadings) {
            Calendar readingDate = reading.getDateAndTime();

            if (readingDate.after(startDateTime) && readingDate.before(endDateTime)
                    || readingDate.equals(startDateTime) || readingDate.equals(endDateTime)) {
                totalValue += reading.returnValueOfReading();
            }
        }
        return totalValue;
    }

    /**
     * Method that sums up the value of readings after the startDateTime parameter and until the endDateTime parameter.
     * The time interval considered is the following ]startDateTime - endDateTime].
     * For example, if a device has a metering period of 10 minutes, then
     * a reading registered at dateAndTime of 09:00:00 AM, considers the metering period from 08:50:00 AM until 08:59:59 AM,
     * so the reading registered at dateAndTime of 09:10:00 AM, considers the metering period from 09:00:00 AM until 09:09:59 AM.
     *
     * @param startDate
     * @param endDate
     * @return sums up the value of readings in the ]startDateTime - endDateTime] interval.
     */
    public double getValueOfReadingsInTimeIntervalDevices(Calendar startDate, Calendar endDate) {
        double totalValue = 0;
        for (Reading reading : this.listOfReadings) {
            Calendar readingDate = reading.getDateAndTime();

            if (readingDate.after(startDate) && readingDate.before(endDate)
                    || readingDate.equals(endDate)) {
                totalValue += reading.returnValueOfReading();
            }
        }
        return totalValue;
    }


    public ReadingList getReadingsInSpecificDay(Calendar date) {
        ReadingList readingListInDate = new ReadingList();

        for (Reading reading : this.listOfReadings) {
            if (reading.isSameDay(date))
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

    public ReadingList filterByDate(Calendar startDate, Calendar endDate) {
        ReadingList readingListInPeriod = new ReadingList();
        for (Reading reading : this.listOfReadings) {
            Calendar readingDate = reading.getDateAndTime();

            if (readingDate.after(startDate) && readingDate.before(endDate)
                    || readingDate.equals(endDate)
                    || readingDate.equals(startDate)) {
                readingListInPeriod.addReading(reading);
            }
        }
        return readingListInPeriod;
    }

    public Reading maxValueInInterval() {
        Reading max = new Reading(Double.MIN_VALUE, new GregorianCalendar());
        double value;

        for (Reading reading : this.listOfReadings) {
            value = reading.returnValueOfReading();
            if (value > max.returnValueOfReading())
                max = reading;
        }
        return max;
    }

    public Reading minValueInInterval() {
        Reading min = new Reading(Double.MAX_VALUE, new GregorianCalendar());
        double value;

        for (Reading reading : this.listOfReadings) {
            value = reading.returnValueOfReading();
            if (value <= min.returnValueOfReading())
                min = reading;
        }
        return min;
    }

    public ReadingList dailyMaximumReadings() {
        //extract the first day of the list

        List<Calendar> dates = new ArrayList<>(); //this list saves all unique dates in the reading list

        ReadingList dailyMax = new ReadingList(); //this is a list containing the maximum reading of a single day
        ReadingList temp;

        //get all dates in the reading list and fill in the dates list. Move this to another method?

        for (Reading reading : this.listOfReadings) {
            if (!dates.contains(reading.extractYearMonthDay())) {
                dates.add(reading.extractYearMonthDay());
            }
        }


        // Iterate over the dates list, get the readings in those dates and add the maximum to the dailyMax list.
        for (Calendar date : dates) {
            temp = getReadingsInSpecificDay(date);
            double max = temp.maxValueInInterval().returnValueOfReading();
            Reading r = new Reading(max, date);
            dailyMax.addReading(r);
        }
        return dailyMax;
    }

    public ReadingList dailyMinimumReadings() {
        //extract the first day of the list

        List<Calendar> dates = new ArrayList<>(); //this list saves all unique dates in the reading list

        ReadingList dailyMin = new ReadingList(); //this is a list containing the maximum reading of a single day
        ReadingList temp;

        //get all dates in the reading list and fill in the dates list. Move this to another method?

        for (Reading reading : this.listOfReadings) {
            if (!dates.contains(reading.extractYearMonthDay())) {
                dates.add(reading.extractYearMonthDay());
            }
        }

        // Iterate over the dates list, get the readings in those dates and add the maximum to the dailyMax list.
        for (Calendar date : dates) {
            temp = getReadingsInSpecificDay(date);
            double min = temp.minValueInInterval().returnValueOfReading();
            Reading day = new Reading(min, date);
            dailyMin.addReading(day);
        }

        return dailyMin;
    }


    public ReadingList dailyAmplitude() {
        List<Reading> dailyMaximumReadings = dailyMaximumReadings().getReadingsList();
        List<Reading> dailyMinimumReadings = dailyMinimumReadings().getReadingsList();
        //extract the first day of the list

        ReadingList dailyAmp = new ReadingList(); //this is a list containing the amplitudes differences of a single day
        double tempReadingValue;
        Reading dayMaxReading;
        Reading dayMinReading;

        int size = dailyMaximumReadings.size();

        for (int i = 0; i < size; i++) {
            dayMaxReading = dailyMaximumReadings.get(i);
            dayMinReading = dailyMinimumReadings.get(i);
            tempReadingValue = dayMaxReading.returnValueOfReading() - dayMinReading.returnValueOfReading();
            dailyAmp.addReading(new Reading(tempReadingValue, dayMaxReading.getDateAndTime()));
        }
        return dailyAmp;
    }

    public Calendar getStartDateOfReadings() {
        Calendar startDate = this.listOfReadings.get(0).getDateAndTime();

        for (int i = 0; i < this.listOfReadings.size(); i++) {
            Reading r = this.listOfReadings.get(i);
            if (r.getDateAndTime().before(startDate)) {
                startDate = r.getDateAndTime();
            }
        }
        return startDate;
    }

    public Calendar getEndDateOfReadings() {
        Calendar endDate = this.listOfReadings.get(0).getDateAndTime();

        for (int i = 0; i < this.listOfReadings.size(); i++) {
            Reading r = this.listOfReadings.get(i);
            if (r.getDateAndTime().after(endDate)) {
                endDate = r.getDateAndTime();
            }
        }
        return endDate;
    }

    boolean checkIfReadingHasNotSameValues(Reading newReading) {
        for (Reading reading : this.listOfReadings) {
            try {
                if (newReading.getUnit().equals("F")) {
                    newReading.convertToCelsius();
                    newReading.setUnit("C");
                }
            } catch (NullPointerException e) {
            }
            if (newReading.returnValueOfReading() == reading.returnValueOfReading() &&
                    newReading.getDateAndTime().equals(reading.getDateAndTime()) &&
                    newReading.getUnit().equals(reading.getUnit()))
                return false;
        }
        return true;
    }
}

