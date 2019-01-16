package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public class US6CreateSensorCTRL {

    private SensorTypeList mSensorTypeList;
    private GAList mGAList;
    private GPSValidations mGPSValidations;
    private DateValidations mDateValidations;
    private NameValidations mNameValidations;


    public US6CreateSensorCTRL(SensorTypeList sensorTypeList, GAList listOfGA) {
        mSensorTypeList = sensorTypeList;
        mGAList = listOfGA;
        mGPSValidations = new GPSValidations();
        mDateValidations= new DateValidations();
        mNameValidations = new NameValidations();
    }

    /**
     * Method to check if altitude inputted by the user is valid
     * @param altitude Double inputted by the user to represent altitude
     * @return altitude if it is within [-12.500,8848] range, otherwise returns null to ask again for valid input
     */
    public boolean altitudeIsValid(double altitude){
        return mGPSValidations.altitudeIsValid(altitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     * @param longitude Double inputted by the user to represent longitude
     * @return longitude if it is within [-180,180] range, otherwise returns null to ask again for valid input
     */
    public boolean longitudeIsValid(double longitude){
        return mGPSValidations.longitudeIsValid(longitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     * @param latitude Double inputted by the user to represent longitude
     * @return latitude if it is within [-90,90] range, otherwise returns null to ask again for valid input
     */
    public boolean latitudeIsValid(double latitude){
        return mGPSValidations.latitudeIsValid(latitude);
    }

    /**
     * Method to check if year inputted by the user is considered a valid year
     * @param year inputted by the user
     * @return year if it is within [2010,2099] range, otherwise returns null to ask again for valid input
     */
    public String yearIsValid(String year){
        return mDateValidations.yearIsValid(year);
    }

    /**
     * Method to check if month inputted by the user is considered a valid month
     * @param month inputted by the user
     * @return month if it is a valid month, otherwise returns null to ask again for valid input
     */
    public String monthIsValid(String month){
        return mDateValidations.monthIsValid(month);
    }

    /**
     * Method that checks if day inputted by the user is a valid day considering previously inputted month and year
     * considering leap years.
     * @param day inputted by the user
     * @param inputMonth previously inputted by the user parsed from string to integer
     * @param inputYear previously inputted by the user parsed from string to integer
     * @return day if input meets regex criteria, otherwise returns null
     */
    public String dayIsValid(String day, int inputMonth, int inputYear){
        return mDateValidations.dayIsValid(day, inputMonth,inputYear);
    }

    /**
     * Method that checks if hour inputted by the user is a valid
     * @param hour input hour
     * @return hour if input meets regex criteria, otherwise returns null
     */
    public String hourIsValid(String hour){
        return mDateValidations.hourIsValid(hour);
    }
    public String nameIsValid(String inputName){
        return mNameValidations.nameIsValid(inputName);
    }

    /**
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
        return mGAList.showGAListInString ();
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

    public boolean addNewSensorToGA(String inputName, GregorianCalendar startDate, int sensorTypeIndex, String inputUnit, double latitude, double longitude, double altitude, int indexOfGA, ReadingList readings) {
        Sensor sensor = mGAList.getGAList().get(indexOfGA - 1).getSensorListInGA().newSensor(inputName, startDate, latitude, longitude, altitude, mSensorTypeList.getSensorTypeList().get(sensorTypeIndex - 1), inputUnit, readings);
        return mGAList.getGAList().get(indexOfGA - 1).getSensorListInGA().addSensor(sensor);
    }

}