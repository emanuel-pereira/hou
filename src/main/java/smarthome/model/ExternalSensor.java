package smarthome.model;

import smarthome.model.validations.Utils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class ExternalSensor implements Sensor {

    @Id
    private String id;
    @Embedded
    private Location location;
    @Embedded
    private SensorBehavior sensorBehavior;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AREA_ID")
    private GeographicalArea geographicalArea;

    protected ExternalSensor() {
    }

    /**
     * Constructor used to create external sensors which require location coordinates.
     *
     * @param id          String parameter to specify sensor's id
     * @param name        String parameter to specify sensor's name
     * @param startDate   specifies the sensor start date as a Calendar dataType
     * @param geoLocation specifies the sensor GPS coordinates
     * @param sensorType  specifies the sensor start date as a Calendar variable
     * @param unit        String parameter to specify sensor's unit of measure
     * @param readings    specifies the sensor's readingList
     */
    public ExternalSensor(String id, String name, Calendar startDate, Location geoLocation, SensorType sensorType, String unit, ReadingList readings) {
        this.id = id;
        this.location = geoLocation;
        GenericName sName = new GenericName(name);
        this.sensorBehavior = new SensorBehavior(sName, startDate, sensorType, unit, readings);
    }

    /**
     * @return the sensor's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Needed to access all the sensor methods common to a Sensor
     *
     * @return SensorBehavior (acees
     */
    public SensorBehavior getSensorBehavior() {
        return sensorBehavior;
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

    public GeographicalArea getGeographicalArea() {
        return geographicalArea;
    }

    public void setGeographicalArea(GeographicalArea geographicalArea) {
        this.geographicalArea = geographicalArea;
    }

    /**
     * Method to calculate linear distance between two sensors
     *
     * @param sensor1 object sensor 1
     * @param sensor2 object sensor 2
     * @return calculated distance between both objects
     */
    public double calcLinearDistanceBetweenTwoSensors(ExternalSensor sensor1, ExternalSensor sensor2) {

        return Utils.round(getLocation().calcLinearDistanceBetweenTwoPoints(sensor1.getLocation(), sensor2.getLocation()), 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExternalSensor)) return false;
        ExternalSensor that = (ExternalSensor) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}