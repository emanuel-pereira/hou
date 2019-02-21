package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeList {

    private List<SensorType> sensorTypeList;

    /**
     * Constructor method that creates a new list to save data type objects
     */
    public SensorTypeList() {
        this.sensorTypeList = new ArrayList<>();
    }


    /**
     * Method to create Data Type object and assign a designation to it
     *
     * @param sensorTypeDesignation - String that names the type of data
     * @return new data type object with designation
     */
    public SensorType newSensorType(String sensorTypeDesignation) {
        String sensorTypeDesignationLowerCase = null;
        if (sensorTypeDesignation != null)
            sensorTypeDesignationLowerCase = sensorTypeDesignation.toLowerCase();
        if (this.sensorTypeDesignationIsValid(sensorTypeDesignationLowerCase))
            return new SensorType(sensorTypeDesignationLowerCase);
        return null;
        //TODO both if statement's can be combined
    }


    /**
     * Method to add a data type object to Data Type list, if it is not on the list yet
     *
     * @param newSensorType - new Data Type object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addSensorType(SensorType newSensorType) {
        if (this.sensorTypeList.contains(newSensorType) || (newSensorType == null))
            return false;
        return this.sensorTypeList.add(newSensorType);
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size(){
        return this.sensorTypeList.size();

    }


    /**
     * Method to return the data types included in the list
     *
     * @return list of data types created
     */
    public List<SensorType> getSensorTypeList() {
        return this.sensorTypeList;
    }


    /**
     * Method to validate the designation given to a type of data
     *
     * @param sensorTypeDesignation - String that names the type of data
     * @return true if the designation is not null or empty after trimming the spaces
     */
    private boolean sensorTypeDesignationIsValid(String sensorTypeDesignation) {
         if(sensorTypeDesignation == null || sensorTypeDesignation.trim().isEmpty())
            return false;
        return sensorTypeDesignation.matches("[a-zA-Z]*");
    }

    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     * @param input sensor type designation
     * @return true if exists and false if not
     */
    public boolean checkIfSensorTypeExists(String input) {
        for (SensorType type : this.sensorTypeList) {
            if (type.getType().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public String showSensorTypeListInString() {
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (SensorType sensorType : this.sensorTypeList) {
            result.append(number++);
            result.append(element);
            result.append(sensorType.getType());
            result.append("\n");
        }
        return result.toString();
    }





}

