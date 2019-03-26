package smarthome.controller;

import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;
import smarthome.repository.SensorTypeRepository;

import java.util.List;


public class NewSensorTypeCTRL {

    private SensorTypeList mSensorTypeList;

    /**
     * Controller constructor
     * @param sensorTypeList the list object on which the user will be able to create new type of GA's
     */
    public NewSensorTypeCTRL(SensorTypeList sensorTypeList) {
        mSensorTypeList = sensorTypeList;
    }

    /**
     * Method to create an object of the type GA with the a user inputted String
     * @param newSensorType user inputted String to a type of GA
     * @return true if it was possible to add the user's chosen new type of GA
     * false if it was not possible to add the new type of GA, eg. if the type already exists
     */
    public boolean newSensorType(String newSensorType) {
        SensorType sensorType = mSensorTypeList.newSensorType(newSensorType);
        if (sensorType != null)
            return mSensorTypeList.addSensorType(sensorType);
        return false;
    }

    public boolean newSensorType(String newSensorType, SensorTypeRepository rep) {
        SensorType sensorType = mSensorTypeList.newSensorType(newSensorType);
        if (sensorType != null)
            return mSensorTypeList.addSensorType(sensorType, rep);
        return false;
    }


    public String returnSensorTypeList() {
        List<SensorType> sensorTypeList = mSensorTypeList.getSensorTypeList();
        StringBuilder result = new StringBuilder();

        for (SensorType sensorType: sensorTypeList){
            result.append(sensorType.getType());
            result.append("\n");
        }
        return result.toString();
    }
}
