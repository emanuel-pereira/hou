package smarthome.model;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Reading {

    private double mValue;
    private Calendar mDateAndTime;

    /**
     * Reading class Constructor
     * It determines that a reading always has an associated value and a date and time
     *
     * @param readValue     the number that corresponds to a reading
     * @param timeOfReading the date and time of a reading
     */
    public Reading(double readValue, Calendar timeOfReading) {

        mValue = readValue;
        mDateAndTime = timeOfReading;

    }

    /**
     * @return the registered value of a reading
     */
    public double returnValueOfReading() {
        return mValue;
    }

    public int getMonthOfReading() {
        return mDateAndTime.get(Calendar.MONTH);
    }

    public Calendar getDateAndTime() {
        return mDateAndTime;
    }


    public boolean compareYearMonthDay(Calendar date){

        int rYear = this.getDateAndTime().get(Calendar.YEAR);
        int rMonth = this.getDateAndTime().get(Calendar.MONTH);
        int rDay = this.getDateAndTime().get(Calendar.DAY_OF_MONTH);
        Calendar date1 = new GregorianCalendar(rYear, rMonth, rDay);
        return date.equals(date1);
    }


    //US610
    /**
     * @return the date of a reading as a string in YYYY-MM-DD format
     */
    public String getDateOfReadingAsString() {
        int year = mDateAndTime.get(Calendar.YEAR);
        int month = mDateAndTime.get(Calendar.MONTH);
        int day = mDateAndTime.get(Calendar.DAY_OF_MONTH);

        StringBuilder output = new StringBuilder();
        output.append(year);
        output.append("-");
        output.append(month);
        output.append("-");
        output.append(day);
        return output.toString();
    }

}
