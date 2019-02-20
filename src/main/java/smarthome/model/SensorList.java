package smarthome.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SensorList {
    private List<Sensor> mSensorList;


    /**
     * Constructor method that creates a new list to save sensor objects
     */
    public SensorList() {
        mSensorList = new ArrayList<>();
    }

    /**
     * Method to add a sensor object to Sensor list, if it is not on the list yet
     *
     * @param newSensor - new Sensor object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addSensor(Sensor newSensor) {
        if (!mSensorList.contains(newSensor)) {
            mSensorList.add(newSensor);
            return true;
        } else return false;
    }


    /**
     * Method to return the sensors included in the list
     *
     * @return list of sensors created
     */
    public List<Sensor> getSensorList() {
        return mSensorList;
    }

    /**
     * @param inputName name of Sensor
     * @param startDate startDate of Sensor
     * @param geoLocation  gps coordinates in which the user wants to place the sensor

     * @return
     */
    public Sensor newSensor(String inputName, GregorianCalendar startDate,Location geoLocation, SensorType sensorType, String inputUnit, ReadingList readings) {
        return new Sensor(inputName, startDate, geoLocation, sensorType, inputUnit, readings);
    }

    /**
     * @param name
     * @param startDate
     * @param sensorType
     * @param unit
     * @return
     */
    public Sensor createNewInternalSensor(String name, GregorianCalendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        return new Sensor(name, startDate, sensorType, unit, readings);
    }

    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     *
     * @param sensorType Sensor type designation
     * @return Sensor type designation
     */
    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        for (Sensor s : mSensorList) {
            if (s.getSensorType().getType().equals(sensorType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a sensor of one specific type
     *
     * @param type Sensor type designation
     * @return A specific type sensor
     */
    public Sensor getRequiredSensorPerType(String type) {
        Sensor requiredSensor = null;
        for (Sensor sensor : mSensorList)
            if (sensor.getSensorType().getType().equals(type))
                requiredSensor = sensor;
        return requiredSensor;
    }


    /**
     * Transforms a list of sensors as a numbered list of strings with the names of the sensors
     *
     * @return List of sensors as string
     */
    public String showSensorListInString() {
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Sensor sensor : mSensorList) {
            result.append(number++);
            result.append(element);
            result.append(sensor.getDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    public SensorList getListOfSensorsByType(SensorType sensorType) {
        SensorList listOfSensorsByType = new SensorList();
        for (Sensor sensor : mSensorList) {
            if (sensor.getSensorType().equals(sensorType))
                listOfSensorsByType.addSensor(sensor);
        }
        return listOfSensorsByType;
    }

    public int size() {
        return mSensorList.size();
    }


    public SensorList getSensorListOfRequiredSensorPerType(String type) {
        SensorList typeSensorList = new SensorList();
        for (Sensor sensor : mSensorList) {
            if (sensor.getSensorType().getType().equals(type)) {
                typeSensorList.addSensor(sensor);
            }
        }
        return typeSensorList;
    }


    public Sensor getLastSensor() {
        return mSensorList.get(mSensorList.size() - 1);
    }

}