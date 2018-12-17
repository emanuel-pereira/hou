package smarthome.model;

import java.util.Calendar;


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



}
