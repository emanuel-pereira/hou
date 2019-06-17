package smarthome.dto;

import java.util.Calendar;

public class InternalReadingDTO {
    private double readingValue;
    private Calendar dateAndTime;

    public InternalReadingDTO(){
    }

    public InternalReadingDTO(double readingValue, Calendar dateAndTime){
        this.readingValue = readingValue;
        this.dateAndTime = dateAndTime;
    }

    public double getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(double readingValue) {
        this.readingValue = readingValue;
    }

    public Calendar getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

}
