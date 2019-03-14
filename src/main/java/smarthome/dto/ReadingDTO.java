package smarthome.dto;

import java.util.Calendar;

public class ReadingDTO {
    private double readingValue;
    private Calendar readingDateAndTime;

    public ReadingDTO(double readingValue,Calendar readingDateAndTime) {
        this.readingDateAndTime=readingDateAndTime;
        this.readingValue=readingValue;
    }
    public double getReadingValue(){
        return readingValue;
    }
    public Calendar getReadingDateAndTime(){
        return readingDateAndTime;
    }


}
