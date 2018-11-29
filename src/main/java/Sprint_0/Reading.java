package Sprint_0;

import java.util.Date;

public class Reading {

    private Date _dateAndTime;
    private double _value;


    //create a constructor method to define the time and the value
    //connect a reading or multiple readings to a sensor
    //a reading corresponds to a value in a certain date/hour

//how to define date/hour?

    public Reading(){

    }
    //Reading Constructor to define a value of a reading

    public Reading(double readValue) {

        _value = readValue;

    }


    //Method to return the value of a certain reading


    public double getReadingValue() {
        return _value;
    }

    /*//Time Constructor to define a date of a reading

    public Time (Date readingtime) {

        _dateandtime = readingtime;

    }*/



}
