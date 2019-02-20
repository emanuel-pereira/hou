package smarthome.controller;

import smarthome.model.*;
import smarthome.model.validations.GPSValidations;
import smarthome.model.validations.NameValidations;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class NewSensorCTRL {

    private SensorTypeList sensorTypeList;
    private GAList gaList;
    private GPSValidations gpsValidations;
    private NameValidations nameValidations;


    public NewSensorCTRL(SensorTypeList sensorTypeList, GAList listOfGA) {
        this.sensorTypeList = sensorTypeList;
        this.gaList = listOfGA;
        this.gpsValidations = new GPSValidations();
        this.nameValidations = new NameValidations();
    }

    /**
     * Method to check if altitude inputted by the user is valid
     *
     * @param altitude Double inputted by the user to represent altitude
     * @return altitude if it is within [-12.500,8848] range, otherwise returns  to ask again for valid input
     */
    public boolean altitudeIsValid(double altitude) {
        return this.gpsValidations.altitudeIsValid(altitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     *
     * @param longitude Double inputted by the user to represent longitude
     * @return longitude if it is within [-180,180] range, otherwise returns  to ask again for valid input
     */
    public boolean longitudeIsValid(double longitude) {
        return this.gpsValidations.longitudeIsValid(longitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     *
     * @param latitude Double inputted by the user to represent longitude
     * @return latitude if it is within [-90,90] range, otherwise returns  to ask again for valid input
     */
    public boolean latitudeIsValid(double latitude) {
        return this.gpsValidations.latitudeIsValid(latitude);
    }


    public boolean nameIsValid(String inputName) {
        return this.nameValidations.nameIsValid(inputName);
    }

    /**
     * @return Method that shows the list of dataTypes inputted by the user in a unique string
     */
    public String showSensorTypeListInString() {
        List<SensorType> list = this.sensorTypeList.getSensorTypeList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (SensorType sensorType : list) {
            result.append(number++);
            result.append(element);
            result.append(sensorType.getType());
            result.append("\n");
        }
        return result.toString();
    }

    public List<GeographicalArea> getGAList() {
        return this.gaList.getGAList();
    }

    /**
     * @return Method that shows the list of Geographical Areas in a unique string
     */
    public String showGAListInString() {
        return this.gaList.showGAListInString();
    }

    /**
     * index position of the List of Geographical Areas chosen by the user
     *
     * @param inputName       name inputted by the user for the sensor
     * @param startDate       start date inputted by the user for the sensor
     * @param sensorTypeIndex index position of data type chosen by the user
     * @param indexOfGA       index position of Geographical Areas List to which the user wants to add the Geographical Area
     * @return adds the sensor created to the Geographical Area chosen by the user
     */

    public boolean addNewSensorToGA(String inputName, GregorianCalendar startDate, int sensorTypeIndex, String inputUnit, Location location, int indexOfGA, ReadingList readings) {
        GeographicalArea geographicalArea = this.gaList.get(indexOfGA - 1);
        SensorType sensorType = this.sensorTypeList.getSensorTypeList().get(sensorTypeIndex);
        Sensor sensor = geographicalArea.getSensorListInGA().newSensor(inputName, startDate, location, sensorType, inputUnit, readings);
        return geographicalArea.getSensorListInGA().addSensor(sensor);
    }


    /**
     * @return the number of elements in the geographical areas list as an integer value
     */
    public int getGAListSize() {
        return this.gaList.size();
    }

    /**
     * @return the number of elements in the sensor type list as an integer value
     */
    public int getSensorTypeListSize() {
        return this.sensorTypeList.size();
    }

    /**
     * Method to get the name of the last element in the list of sensors of the geographical area selected as parameter
     *
     * @param indexOfGA geographical area in the index position of the list of geographical areas
     * @return the name of sensor in the last position of the list of sensors of the geographical area selected as parameter
     */
    public String getSensorType(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGA().getLastSensor();
        SensorType type = createdSensor.getSensorType();
        return type.getType();
    }

    /**
     * Method to get the name of the geographical area in the index position of the list of geographical areas
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the name of the geographical area in the index position of the list of geographical areas
     */
    public String getGAName(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        return ga.getGAName();
    }

    /**
     * Method to get the name of the sensor created
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the name of the sensor created in the selected geographical area
     */
    public String getSensorName(int indexOfGA){
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor=ga.getSensorListInGA().getLastSensor();
        return createdSensor.getDesignation();
    }

    /**
     * Method to get the start date of the sensor created
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the start date of the sensor created in the selected geographical area
     */
    public Calendar getStartDate(int indexOfGA){
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor=ga.getSensorListInGA().getLastSensor();
        return createdSensor.getStartDate();
    }

    /**
     * Method to get the unit of the sensor created
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the unit of the sensor created in the selected geographical area
     */
    public String getUnit(int indexOfGA){
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor=ga.getSensorListInGA().getLastSensor();
        return createdSensor.getUnit();
    }

}