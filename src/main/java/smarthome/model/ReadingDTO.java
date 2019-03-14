package smarthome.model;

import java.util.Calendar;

public class ReadingDTO {

    private double value;
    private Calendar dateAndTime;

    public ReadingDTO(double value, Calendar dateAndTime) {
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
