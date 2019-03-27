package smarthome.model;

import smarthome.dto.ReadingDTO;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double value;
    private Calendar dateAndTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENSOR_ID")
    private Sensor sensor;


    /**
     * Reading class Constructor
     * It determines that a reading always has an associated value and a date and time
     *
     * @param readValue     the number that corresponds to a reading
     * @param timeOfReading the date and time of a reading
     */
    public Reading(double readValue, Calendar timeOfReading) {
        this.value = readValue;
        this.dateAndTime = timeOfReading;
    }

    public Reading(double readValue, Calendar timeOfReading, Sensor sensor) {
        this.value = readValue;
        this.dateAndTime = timeOfReading;
        this.sensor = sensor;
    }

    /**
     * @return the registered value of a reading
     */
    public double returnValueOfReading() {
        return this.value;
    }

    public int getMonthOfReading() {
        return this.dateAndTime.get(Calendar.MONTH);
    }

    public Calendar getDateAndTime() {
        return this.dateAndTime;
    }


    public boolean isSameDay(Calendar date) {
        int rYear = getDateAndTime().get(Calendar.YEAR);
        int rMonth = getDateAndTime().get(Calendar.MONTH);
        int rDay = getDateAndTime().get(Calendar.DAY_OF_MONTH);
        Calendar date1 = new GregorianCalendar(rYear, rMonth, rDay);
        return date.equals(date1);
    }

    public GregorianCalendar extractYearMonthDay() {
        return new GregorianCalendar(this.getDateAndTime().get(Calendar.YEAR), this.getDateAndTime().get(Calendar.MONTH), this.getDateAndTime().get(Calendar.DAY_OF_MONTH));
    }


    //US610

    /**
     * @return the date of a reading as a string in YYYY-MM-DD format
     */
    public String getDateOfReadingAsString() {
        int year = this.dateAndTime.get(Calendar.YEAR);
        int month = this.dateAndTime.get(Calendar.MONTH) + 1;
        int day = this.dateAndTime.get(Calendar.DAY_OF_MONTH);

        StringBuilder output = new StringBuilder();
        output.append(year);
        output.append("-");
        output.append(month);
        output.append("-");
        output.append(day);
        return output.toString();
    }

    public ReadingDTO toDTO() {
        return new ReadingDTO(this.value, this.dateAndTime);
    }

    public void setSensor(Sensor newSensor) {
        this.sensor = newSensor;
    }
}
