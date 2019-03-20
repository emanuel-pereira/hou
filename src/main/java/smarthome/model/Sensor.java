package smarthome.model;

import smarthome.dto.ReadingDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.validations.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class Sensor {
    private String id;
    private String designation;
    private Location location;
    private SensorType sensorType;
    private Calendar startDate;
    private String unit;
    private boolean active;
    private ReadingList readingList;

    /**
     * Constructor used to create internal sensors which, unlike external sensors, don't require location coordinates.
     *
     * @param designation String parameter to specify sensor's designation
     * @param startDate   specifies the sensor start date as a Calendar dataType
     * @param sensorType  specifies the sensor type as a SensorType instance
     * @param unit        String parameter to specify sensor's unit of measure
     * @param readings    specifies the sensor's readingList
     */
    public Sensor(String designation, Calendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        if (nameIsValid (designation)) {
            this.designation = designation;
            this.startDate = startDate;
            this.sensorType = sensorType;
            this.unit = unit;
            this.active = true;
            this.readingList = readings;
        }
    }

    /**
     * Constructor used to create external sensors which require location coordinates.
     *
     * @param id          String parameter to specify sensor's id
     * @param designation String parameter to specify sensor's designation
     * @param startDate   specifies the sensor start date as a Calendar dataType
     * @param geoLocation specifies the sensor GPS coordinates
     * @param sensorType  specifies the sensor start date as a Calendar variable
     * @param unit        String parameter to specify sensor's unit of measure
     * @param readings    specifies the sensor's readingList
     */
    public Sensor(String id, String designation, Calendar startDate, Location geoLocation, SensorType sensorType, String unit, ReadingList readings) {
        if (nameIsValid (designation)) {
            this.id = id;
            this.designation = designation;
            this.startDate = startDate;
            this.location = geoLocation;
            this.sensorType = sensorType;
            this.unit = unit;
            this.active = true;
            this.readingList = readings;
        }
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
    public boolean nameIsValid(String name) {
        if (name.trim ().isEmpty ()) {
            return false;
        }
        return name.matches ("[A-Za-z0-9 \\-]*");
    }

    /**
     * Changes the sensorDesignation of the sensor to the one inputted by the user.
     *
     * @param sensorDesignation sensor's name String
     */
    public boolean setSensorDesignation(String sensorDesignation) {
        if (nameIsValid (sensorDesignation)) {
            this.designation = sensorDesignation;
            return true;
        }
        return false;
    }

    /**
     * Returns the designation of the sensor
     *
     * @return is the sensor's name designation
     */
    public String getDesignation() {
        return this.designation;
    }

    /**
     * Changes the location of the sensor to the new location coordinates inputted by the user.
     *
     * @param location , of the object Location type, to update the location of the sensor
     */
    public void setSensorLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the location coordinates of the sensor
     *
     * @return object Location
     */
    public Location getLocation() {
        return this.location;
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
     * Method to calculate linear distance between two sensors
     *
     * @param sensor1 object sensor 1
     * @param sensor2 object sensor 2
     * @return calculated distance betwwen both objects
     */
    public double calcLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {
        return Utils.round (this.location.calcLinearDistanceBetweenTwoPoints (sensor1.getLocation (), sensor2.getLocation ()), 2);
    }


    /**
     * This method looks for the last reading within a list of readings for a sensor.
     *
     * @return the last reading of a list of readings
     */
    public Reading getLastReadingPerSensor() {
        return this.readingList.getLastReading ();
    }

    public double getLastReadingValuePerSensor() {
        double lastValue;
        lastValue = this.readingList.getReadingsList ().get (this.readingList.getReadingsList ().size () - 1).returnValueOfReading ();
        return lastValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) o;
        return Objects.equals (this.designation, sensor.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash (this.designation, this.location, this.sensorType);
    }

    public Calendar getStartDate() {
        return this.startDate;
    }

    public String getUnit() {
        return this.unit;
    }

    public SensorDTO toDTO() {
        List<ReadingDTO> readingListDTO = new ArrayList<> ();
        for (Reading reading : this.readingList.getReadingsList ()) {
            ReadingDTO readingDTO = reading.toDTO ();
            readingListDTO.add (readingDTO);
        }
        return new SensorDTO (this.id, this.designation, readingListDTO);
    }

    /**
     * Deactivate sensor if active
     * @return True if deactivated
     */
    public boolean deactivate() {
        if (!this.active)
            return false;
        this.active = false;
        return true;
    }

    /**
     * Reactivate sensor if not active
     * @return True if reactivated
     */
    public boolean reactivate(){
        if (this.active)
            return false;
        this.active = true;
        return true;
    }

    /**
     * Check if sensor is active
     * @return True if active. False if not active
     */
    public boolean isActive() {
        return this.active;
    }

}
