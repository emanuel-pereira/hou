package sprint0;

import java.util.Date;

public class Reading {

    private double mValue;
    private Date mDateAndTime;


    /**
     * Reading class Constructor
     * It determines that a reading always has an associated value and a date and time
     *
     * @param readValue the number that corresponds to a reading
     * @param timeOfReading the date and time of a reading
     */
    public Reading(double readValue,Date timeOfReading ) {

        mValue = readValue;
        mDateAndTime = timeOfReading;

    }

    /**
     * @return the registered value of a reading
     */
    public double returnValueOfReading () {
        return mValue;
    }

}
