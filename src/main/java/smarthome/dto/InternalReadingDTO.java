package smarthome.dto;

import java.util.Calendar;

public class InternalReadingDTO {
    private double value;
    private Calendar dateAndTime;

    public InternalReadingDTO(){
    }

    public InternalReadingDTO(double value, Calendar dateAndTime){
        this.value = value;
        this.dateAndTime = dateAndTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Calendar getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

}
