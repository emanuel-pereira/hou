package smarthome.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SensorList {
    private List<Sensor> mSensor;


    /**
     * Constructor method that creates a new list to save sensor objects
     */
    public SensorList() {
        mSensor = new ArrayList<>();
    }

    /**
     * Method to add a sensor object to Sensor list, if it is not on the list yet
     *
     * @param newSensor - new Sensor object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addSensor(Sensor newSensor) {
        if (!mSensor.contains(newSensor)) {
            mSensor.add(newSensor);
            return true;
        } else return false;
    }



    /**
     * Method to return the sensors included in the list
     *
     * @return list of sensors created
     */
    public List<Sensor> getSensorList() {
        return mSensor;
    }

    /**
     *
     * @param inputName name of Sensor
     * @param startDate startDate of Sensor
     * @param latitude latitude in which the user wants to place the sensor
     * @param longitude longitude in which the user wants to place the sensor
     * @param altitude altitude in which the user wants to place the sensor
     * @param inputDataType dataType the user wants to assign for the sensor.
     * @return
     */
    public Sensor newSensor(String inputName, GregorianCalendar startDate, double latitude, double longitude, double altitude, DataType inputDataType) {
        return new Sensor(inputName, startDate, latitude, longitude, altitude,inputDataType);
    }
}

