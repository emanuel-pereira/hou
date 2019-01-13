package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public class US6CreateSensorCTRL {

    private SensorTypeList mSensorTypeList;
    private GAList mGAList;


    public US6CreateSensorCTRL(SensorTypeList sensorTypeList, GAList listOfGA) {
        mSensorTypeList = sensorTypeList;
        mGAList = listOfGA;
    }

    /**
     *
     * @return Method that shows the list of dataTypes inputted by the user in a unique string
     */
    public String showSensorTypeListInString() {
        List<SensorType> list = mSensorTypeList.getSensorTypeList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (SensorType sensorType : list) {
            result.append(number++);
            result.append(element);
            result.append(sensorType.getSensorTypeDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    public List<GeographicalArea> getGAList() {
        return mGAList.getGAList();
    }

    /**
     * @return Method that shows the list of Geographical Areas in a unique string
     */
    public String showGAListInString() {
        List<GeographicalArea> list = this.getGAList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (GeographicalArea ga : list) {
            result.append(number++);
            result.append(element);
            result.append(ga.getGeographicalAreaDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Method that adds a new sensor to a Geographical Area in the
     * index position of the List of Geographical Areas chosen by the user
     *
     * @param inputName       name inputted by the user for the sensor
     * @param startDate       start date inputted by the user for the sensor
     * @param sensorTypeIndex index position of data type chosen by the user
     * @param indexOfGA       index position of Geographical Areas List to which the user wants to add the Geographical Area
     * @return adds the sensor created to the Geographical Area chosen by the user
     */

    public boolean addNewSensorToGA(String inputName, GregorianCalendar startDate, int sensorTypeIndex, String inputUnit, double latitude, double longitude, double altitude, int indexOfGA, List<Reading> readings) {
        Sensor sensor = mGAList.getGAList().get(indexOfGA - 1).getSensorListInGA().newSensor(inputName, startDate,
                mSensorTypeList.getSensorTypeList().get(sensorTypeIndex - 1).toString(), inputUnit, latitude, longitude, altitude, readings);
        return mGAList.getGAList().get(indexOfGA - 1).getSensorListInGA().addSensor(sensor);
    }
}