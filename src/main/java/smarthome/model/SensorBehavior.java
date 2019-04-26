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

    private Sensor sensor;

    public SensorBehavior() {
    }


    /**
     * Returns the location coordinates of the sensor
     *
     * @return object Location
     */
    public Location getLocation() {
        return sensor.getLocation();
    }

    /**
     * Method to calculate linear distance between two sensors
     *
     * @param sensor1 object sensor 1
     * @param sensor2 object sensor 2
     * @return calculated distance betwwen both objects
     */
    public double calcLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {
        return Utils.round(getLocation().calcLinearDistanceBetweenTwoPoints(sensor1.getLocation(), sensor2.getLocation()), 2);
    }

    /**
     * This method looks for the last reading within a list of readings for a sensor.
     *
     * @return the last reading of a list of readings
     */
    public Reading getLastReadingPerSensor() {
        return sensor.getReadingList().getLastReading();
    }

    public double getLastReadingValuePerSensor() {
        double lastValue;
        lastValue = sensor.getReadingList().getReadingsList().get(sensor.getReadingList().getReadingsList().size() - 1).returnValueOfReading();
        return lastValue;
    }





}
