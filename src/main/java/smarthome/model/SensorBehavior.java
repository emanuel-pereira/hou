package smarthome.model;

import smarthome.dto.ReadingDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.validations.Utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static smarthome.model.House.getAddress;

public class SensorBehavior {

    private String id;
    private Name name;
    private SensorType sensorType;
    private Calendar startDate;
    private Calendar pauseDate;
    private String unit;
    private boolean active;
    private ReadingList readingList;

    /**
     * Constructor that creates sensor behaviour use in every sensor.
     *
     * @param id          String parameter to specify sensor's id
     * @param sensorName String parameter to specify sensor's designation
     * @param startDate   specifies the sensor start date as a Calendar dataType
     * @param sensorType  specifies the sensor start date as a Calendar variable
     * @param unit        String parameter to specify sensor's unit of measure
     * @param readings    specifies the sensor's readingList
     */
    public SensorBehavior(String id, String sensorName, Calendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        this.id = id;
        this.name = new Name(sensorName);
        this.startDate = startDate;
        this.sensorType = sensorType;
        this.unit = unit;
        this.active = true;
        this.readingList = readings;
    }

    /**
     * Method to check if the sensorDesignation given to name the sensor meets the criteria defined to be
     * considered a valid sensorDesignation, namely:
     * - name cannot be empty or null
     * - name must have only alphanumeric characters
     *
     * @param name sensor's name
     * @return true if name sensorDesignation is valid, if it is not null or empty
     */
    private boolean nameIsValid(String name) {
        if (name.trim().isEmpty()) {
            return false;
        }
        return name.matches("[A-Za-z0-9 \\-]*");
    }

    /**
     * Accept alphanumeric input without spaces
     *
     * @param id Unique identification
     * @return True if validate correctly
     */
    private boolean sensorIdIsValid(String id) {
        if (id.trim().isEmpty()) {
            return false;
        }
        return id.matches("^[a-zA-Z0-9]*$");
    }

    /**
     * Changes the Id of the sensor to the one inputted by the user.
     *
     * @param sensorId sensor's id String
     * @return True if correctly validate
     */
    public boolean setId(String sensorId) {
        if (this.sensorIdIsValid(sensorId)) {
            this.id = sensorId;
            return true;
        }
        return false;
    }

    /**
     * Changes the sensorDesignation of the sensor to the one inputted by the user.
     *
     * @param sensorName sensor's name String
     */
    public boolean setSensorDesignation(String sensorName) {
        if (this.nameIsValid(sensorName)) {
            this.name = new Name(sensorName);
            return true;
        }
        return false;
    }

    /**
     * Changes the type of the sensor to the one inputted by the user.
     *
     * @param sensorType sensor's type
     */

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * Changes the start date of the sensor to the one inputted by the user.
     *
     * @param startDate date when the sensor started reading
     */
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    /**
     * Changes the unit of the sensor to the one inputted by the user.
     *
     * @param unit sensor's unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Returns the designation of the sensor
     *
     * @return is the sensor's name designation
     */
    public String getDesignation() {

        return this.name.toString();
    }


    /**
     * @return the sensor's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the dataType of the sensor.
     *
     * @return object dataType
     */
    public SensorType getSensorType() {
        return this.sensorType;
    }

    public ReadingList getReadingList() {
        return this.readingList;
    }


    /**
     * This method looks for the last reading within a list of readings for a sensor.
     *
     * @return the last reading of a list of readings
     */
    public Reading getLastReadingPerSensor() {
        return this.readingList.getLastReading();
    }

    public double getLastReadingValuePerSensor() {
        double lastValue;
        lastValue = this.readingList.getReadingsList().get(this.readingList.getReadingsList().size() - 1).returnValueOfReading();
        return lastValue;
    }

    /**
     * Gets the start date
     *
     * @return Date
     */
    public Calendar getStartDate() {
        return this.startDate;
    }

    /**
     * A pause date that marks when a sensor is deactivated
     *
     * @return Date
     */
    public Calendar getPauseDate() {
        return this.pauseDate;
    }

    public String getUnit() {
        return this.unit;
    }

    /**
     * Deactivate sensor if active
     *
     * @return True if deactivated
     */
    public boolean deactivate(Calendar pauseDate) {
        if (this.active && pauseDate.after(this.startDate)) {
            this.active = false;
            this.pauseDate = pauseDate;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reactivate sensor if not active
     *
     * @return True if reactivated
     */
    public boolean reactivate() {
        if (this.active)
            return false;
        this.active = true;
        return true;
    }

    /**
     * Check if sensor is active
     *
     * @return True if active. False if not active
     */
    public boolean isActive() {
        return this.active;
    }

}
