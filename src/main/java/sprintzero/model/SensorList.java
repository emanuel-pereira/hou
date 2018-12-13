package sprintzero.model;

import java.util.ArrayList;
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
     * Method to create sensor object and assign a designation to it
     *
     * @param sensorDesignation - String that names the sensor
     * @return new sensor object with designation
     */
    public Sensor newSensor(String sensorDesignation) {
        return new Sensor(sensorDesignation);
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


}
