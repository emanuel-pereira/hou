package Sprint_0;

import java.util.Date;

public class Reading {

    private double mValue;
    private Date mDateAndTime;



    public Reading(){

    }


    //Reading Constructor to define a value and a time of a reading

    public Reading(double readValue,Date timeOfReading ) {

        registerReading(readValue, timeOfReading);

    }


    //Method to register a new reading

    public void registerReading (double readValue, Date timeOfReading) {
//int year, int month, int day, int hour, int minutes
        mValue = readValue;
        mDateAndTime = timeOfReading;
    }



    //Method to return the value of the registered reading

    public double returnValueOfReading () {
        return mValue;
    }
}
