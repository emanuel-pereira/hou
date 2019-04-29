package smarthome.dto;

import java.util.Calendar;

public class ReadingDTO {
    private final double readingValue;
    private final Calendar readingDateAndTime;

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
