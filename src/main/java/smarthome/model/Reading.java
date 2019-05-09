package smarthome.model;

import smarthome.dto.ReadingDTO;

import javax.persistence.Embeddable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The @Embeddable annotation allows to specify a class whose instances are stored as intrinsic part of the owning
 * entity. This annotation has no attributes.
 * The behaviour of the persistence of this class, is such as if this class attributes were from the parent class
 * were this Embeddable is Embedded(eg. Sensor.listOfReadings [List<Reading>] ).
 * What will happen is that there will be create a secondary list where there will be an embedded connection between
 * the sensor and its respective readings. This connection, in the secondary table will be present a id from the parent
 * object (Sensor) and the values of its Readings (value, date, unit)
 */
@Embeddable
public class Reading {

    private double value;
    private Calendar dateAndTime;
    private String unit;

    protected Reading() {
    }

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

    public Reading(double readValue, Calendar timeOfReading, String unitValue) {
        this.value = readValue;
        this.dateAndTime = timeOfReading;
        this.unit = unitValue;
    }

    /**
     * @return the registered value of a reading
     */
    public double returnValue() {
        return this.value;
    }

    public int getMonthOfReading() {
        return this.dateAndTime.get(Calendar.MONTH);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setDateAndTime(Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Calendar getDateAndTime() {
        return this.dateAndTime;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String newUnit) {
        this.unit = newUnit;

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

    public double convertToCelsius() {
        if (this.unit.equals("F")) {
            this.value = ((this.value - 32) / 1.8);
            this.value = Math.round(this.value*100.0)/100.0;
            setUnit("C");
        }
        return this.value;
    }

    public ReadingDTO toDTO() {
        return new ReadingDTO(this.value, this.dateAndTime);
    }
}
