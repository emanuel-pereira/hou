package smarthome.controller;

import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.List;


public class NewSensorTypeCTRL {

    private SensorTypeList sensorTypeList;

    /**
     * Controller constructor
     * @param sensorTypeList the list object on which the user will be able to create new type of GA's
     */
    public NewSensorTypeCTRL(SensorTypeList sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    /**
     * Method to create an object of the type GA with the a user inputted String
     * @param newSensorType user inputted String to a type of GA
     * @return true if it was possible to add the user's chosen new type of GA
     * false if it was not possible to add the new type of GA, eg. if the type already exists
     */
    public boolean newSensorType(String newSensorType) {
        SensorType sensorType = this.sensorTypeList.newSensorType(newSensorType);
        if (sensorType != null)
            return this.sensorTypeList.addSensorType(sensorType);
        return false;
    }


    public String returnSensorTypeList() {
        List<SensorType> sensorTypeList = this.sensorTypeList.getSensorTypeList();
        StringBuilder result = new StringBuilder();

        for (SensorType sensorType: sensorTypeList){
            result.append(sensorType.getType());
            result.append("\n");
        }
        return result.toString();
    }
}
