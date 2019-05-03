package smarthome.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class InternalSensor implements Sensor {

    @Id
    private String id;
    @Embedded
    private SensorBehavior sensorBehavior;


    protected InternalSensor() {
    }

    /**
     * Constructor used to create internal sensors which require location coordinates.
     *
     * @param id          String parameter to specify sensor's id
     * @param name String parameter to specify sensor's name
     * @param startDate   specifies the sensor start date as a Calendar dataType
     * @param sensorType  specifies the sensor start date as a Calendar variable
     * @param unit        String parameter to specify sensor's unit of measure
     * @param readings    specifies the sensor's readingList
     */
    public InternalSensor(String id, Name name, Calendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        this.id = id;
        this.sensorBehavior = new SensorBehavior(name, startDate, sensorType, unit, readings);
    }

    /**
     * @return the sensor's id
     */
    public String getId() {
        return this.id;
    }

    @Override
    public SensorBehavior getSensorBehavior() {
        return sensorBehavior;
    }

    /**
     * Changes the type of the sensor to the one inputted by the user.
     *
     * @param sensorType sensor's type
     */
    public void setSensorType(SensorType sensorType) {
        this.sensorBehavior.setSensorType(sensorType);
    }

    /**
     * Changes the start date of the sensor to the one inputted by the user.
     *
     * @param startDate date when the sensor started reading
     */
    public void setStartDate(Calendar startDate) {
        this.sensorBehavior.setStartDate(startDate);
    }

    /**
     * Changes the unit of the sensor to the one inputted by the user.
     *
     * @param unit sensor's unit
     */
    public void setUnit(String unit) {
        this.sensorBehavior.setUnit(unit);
    }

    /**
     * Changes the sensorDesignation of the sensor to the one inputted by the user.
     *
     * @param sensorDesignation sensor's name String
     */
    public boolean setSensorDesignation(String sensorDesignation) {
        return this.sensorBehavior.setSensorDesignation(sensorDesignation);
    }

    /**
     * Returns the designation of the sensor
     *
     * @return is the sensor's name designation
     */
    public String getDesignation() {
        return this.sensorBehavior.getDesignation();
    }

    /**
     * Returns the dataType of the sensor.
     *
     * @return object dataType
     */
    public SensorType getSensorType() {
        return this.sensorBehavior.getSensorType();
    }

    public ReadingList getReadingList() {
        return this.sensorBehavior.getReadingList();
    }


    /**
     * This method looks for the last reading within a list of readings for a sensor.
     *
     * @return the last reading of a list of readings
     */
    public Reading getLastReading() {
        return this.sensorBehavior.getLastReading();
    }

    public double getLastReadingValue() {
        return this.sensorBehavior.getLastReadingValue();
    }


    /**
     * Gets the start date
     *
     * @return Date
     */
    public Calendar getStartDate() {
        return this.sensorBehavior.getStartDate();
    }

    /**
     * A pause date that marks when a sensor is deactivated
     *
     * @return Date
     */
    public Calendar getPauseDate() {
        return this.sensorBehavior.getPauseDate();
    }

    public String getUnit() {
        return this.sensorBehavior.getUnit();
    }


    /**
     * Deactivate sensor if active
     *
     * @return True if deactivated
     */
    public boolean deactivate(Calendar pauseDate) {
        return this.sensorBehavior.deactivate(pauseDate);

    }

    /**
     * Reactivate sensor if not active
     *
     * @return True if reactivated
     */
    public boolean reactivate() {
        return this.sensorBehavior.reactivate();
    }

    /**
     * Check if sensor is active
     *
     * @return True if active. False if not active
     */
    public boolean isActive() {
        return this.sensorBehavior.isActive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternalSensor)) return false;
        InternalSensor that = (InternalSensor) o;
        return id.equals(that.id) ||
                sensorBehavior.getDesignation().equals(that.sensorBehavior.getDesignation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, this.sensorBehavior.getDesignation());
    }

}
