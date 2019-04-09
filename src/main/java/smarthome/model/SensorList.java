package smarthome.model;

import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

public class SensorList {
    private List<Sensor> listOfSensors;


    /**
     * Constructor method that creates a new list to save sensor objects
     */
    public SensorList() {
        this.listOfSensors = new ArrayList<>();
    }

    /**
     * Method to add a sensor object to Sensor list, if it is not on the list yet
     *
     * @param newSensor - new Sensor object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addSensor(Sensor newSensor) {
        if (!this.listOfSensors.contains(newSensor)) {
            return this.listOfSensors.add(newSensor);
        } else return false;
    }





    /**
     * Method to return the sensors included in the list
     *
     * @return list of sensors created
     */
    public List<Sensor> getSensorList() {
        return this.listOfSensors;
    }

    /**
     * @param id   id of the Sensor
     * @param inputName   name of Sensor
     * @param startDate   startDate of Sensor
     * @param geoLocation gps coordinates in which the user wants to place the sensor
     * @return List of sensors
     */
    public Sensor newSensor(String id, String inputName, GregorianCalendar startDate, Location geoLocation, SensorType sensorType, String inputUnit, ReadingList readings) {
        return new Sensor(id, inputName, startDate, geoLocation, sensorType, inputUnit, readings);
    }

    /**
     * @param name       Name of the sensor
     * @param startDate  The first day the sensor starts to work
     * @param sensorType The sensor type
     * @param unit       The measurement unit
     * @return A new interior sensor
     */
    public Sensor createNewInternalSensor(String id, String name, GregorianCalendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        return new Sensor(id, name, startDate, sensorType, unit, readings);
    }

    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     *
     * @param sensorType Sensor type designation
     * @return Sensor type designation
     */
    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        for (Sensor s : this.listOfSensors) {
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
        for (Sensor sensor : this.listOfSensors)
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
        for (Sensor sensor : this.listOfSensors) {
            result.append(number++);
            result.append(element);
            result.append(sensor.getDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    public SensorList getListOfSensorsByType(SensorType sensorType) {
        SensorList listOfSensorsByType = new SensorList();
        for (Sensor sensor : this.listOfSensors) {
            if (sensor.getSensorType().equals(sensorType))
                listOfSensorsByType.addSensor(sensor);
        }
        return listOfSensorsByType;
    }

    public int size() {
        return this.listOfSensors.size();
    }

    public void removeSensor(Sensor sensor) {
        this.listOfSensors.remove(sensor);

    }

    public Sensor getLastSensor() {
        return this.listOfSensors.get(this.listOfSensors.size() - 1);
    }


    /**
     * Get all active sensors
     *
     * @return List of sensors
     */
    public SensorList getActiveSensors() {
        SensorList activeSensors = new SensorList();
        for (Sensor s : this.getSensorList()) {
            if (s.isActive()) {
                activeSensors.addSensor(s);
            }
        }
        return activeSensors;
    }

    /**
     * Deactivate sensor and save new status in the sensorRepository (DB)
     *
     * @param sensorID  id of the sensor
     * @param pauseDate Deactivation date
     */
    public void deactivateSensor(String sensorID, Calendar pauseDate) {
        for (Sensor s : this.getSensorList())
            if (s.getId().matches(sensorID)) {
                s.deactivate(pauseDate);
                //Repository call
                try {
                    Repositories.getSensorRepository().save(s);
                } catch (Exception e) {
                    Logger.getLogger("Repository unreachable");

                }
            }


    }
}