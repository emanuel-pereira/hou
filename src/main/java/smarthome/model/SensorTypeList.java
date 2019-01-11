package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeList {

    private List<SensorType> mSensorTypeList;

    /**
     * Constructor method that creates a new list to save data type objects
     */
    public SensorTypeList() {
        mSensorTypeList = new ArrayList<>();
    }


    /**
     * Method to create Data Type object and assign a designation to it
     *
     * @param sensorTypeDesignation - String that names the type of data
     * @return new data type object with designation
     */
    public SensorType newSensorType(String sensorTypeDesignation) {
        if (sensorTypeDesignation != null)
            sensorTypeDesignation = sensorTypeDesignation.toLowerCase();
        if (this.sensorTypeDesignationIsValid(sensorTypeDesignation))
            return new SensorType(sensorTypeDesignation);
        return null;
    }


    /**
     * Method to add a data type object to Data Type list, if it is not on the list yet
     *
     * @param newSensorType - new Data Type object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addSensorType(SensorType newSensorType) {
        if (mSensorTypeList.contains(newSensorType) || (newSensorType == null))
            return false;
        return mSensorTypeList.add(newSensorType);
    }



    /**
     * Method to return the data types included in the list
     *
     * @return list of data types created
     */
    public List<SensorType> getSensorTypeList() {
        return mSensorTypeList;
    }


    /**
     * Method to validate the designation given to a type of data
     *
     * @param sensorTypeDesignation - String that names the type of data
     * @return true if the designation is not null or empty after trimming the spaces
     */
    public boolean sensorTypeDesignationIsValid(String sensorTypeDesignation) {
         if(sensorTypeDesignation == null || sensorTypeDesignation.trim().isEmpty())
            return false;
        return sensorTypeDesignation.matches("[a-zA-Z]*");
    }

    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     * @param input sensor type designation
     * @return true if exists and false if not
     */
    public boolean checkIfRequiredSensorTypeExists (String input) {
        for (SensorType type : mSensorTypeList) {
            if (type.getSensorTypeDesignation ().toLowerCase ().equals (input)) {
                return true;
            }
        }
        return false;
    }



}

