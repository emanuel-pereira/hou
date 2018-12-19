package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public class US6CreateSensorCTRL {

    private DataTypeList mDataTypeList;
    private GAList mGAList;
    private SensorList mSensorList;

    /**
     * @param dataTypeList
     * @param listOfGA
     */

    public US6CreateSensorCTRL(DataTypeList dataTypeList, GAList listOfGA, SensorList sensorList) {
        mDataTypeList = dataTypeList;
        mGAList = listOfGA;
        mSensorList = sensorList;
    }

    /**
     *
     * @return Method that shows the list of dataTypes inputted by the user in a unique string
     */
    public String showDataTypeListInString() {
        List<DataType> list = mDataTypeList.getDataTypeList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (DataType dataType : list) {
            result.append(number++);
            result.append(element);
            result.append(dataType.getDataTypeDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    public List<GeographicalArea> getGAList() {
        return mGAList.getGAList();
    }

    /**
     *
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
     * Method that creates a sensor object before the user chooses which area he wants to add it.
     *
     * @param inputName name for the instance of sensor created
     * @param startDate date when the sensor will start
     * @param dataTypeIndex data type the sensor will read
     * @return adds the sensor to a list of sensors
     */

    public boolean newSensor(String inputName, GregorianCalendar startDate, int dataTypeIndex) {
        String inputDataType = mDataTypeList.getDataTypeList().get(dataTypeIndex - 1).toString();
        Sensor sensor=mSensorList.newSensor2(inputName,startDate,inputDataType);
        return mSensorList.addSensor(sensor);
    }

    /**
     * Method that adds the Sensor created to the Geographical Area in the
     * index position of the List of Geographical Areas inputted by the user
     * @param indexOfGA index position of Geographical Areas List to which the user wants to add the Geographical Area
     * @return adds the sensor created to the Geographical Area chosen by the user
     */
    public boolean addNewSensorToGA(int indexOfGA) {
        return mGAList.get(indexOfGA-1).addSensor(mSensorList.getSensorList().get(mSensorList.getSensorList().size()-1));
    }
}