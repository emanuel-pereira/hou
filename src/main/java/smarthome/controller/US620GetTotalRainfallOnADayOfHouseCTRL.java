package smarthome.controller;

import smarthome.model.*;
import smarthome.model.Validations.DateValidations;

import java.util.GregorianCalendar;

public class US620GetTotalRainfallOnADayOfHouseCTRL {


    private SensorTypeList mSensorTypeList;
    private House mHouse;
    private DateValidations mDateValidations;

    public US620GetTotalRainfallOnADayOfHouseCTRL(House house, SensorTypeList sensorTypeList) {

        mSensorTypeList = sensorTypeList;
        mHouse = house;
        mDateValidations = new DateValidations();
    }

    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        return mSensorTypeList.checkIfRequiredSensorTypeExists(sensorType);
    }

    public double requestReadingRainfall(GregorianCalendar inputDate, SensorType sensorType) {
        Sensor sensor=mHouse.getSensorOfTypeWithReadingsInDate(inputDate,sensorType);
        return sensor.getReadingList().totalValueInGivenDay(inputDate);
    }

    public GeographicalArea getHouseGA(){
       return mHouse.getHouseGA();
    }

    /**
     * Method to check if year inputted by the user is considered a valid year
     *
     * @param year inputted by the user
     * @return year if it is within [2010,2099] range, otherwise returns  to ask again for valid input
     */
    public boolean yearIsValid(String year) {
        return mDateValidations.yearIsValid(year);
    }

    /**
     * Method to check if month inputted by the user is considered a valid month
     *
     * @param month inputted by the user
     * @return month if it is a valid month, otherwise returns  to ask again for valid input
     */
    public boolean monthIsValid(String month) {
        return mDateValidations.monthIsValid(month);
    }

    /**
     * Method that checks if day inputted by the user is a valid day considering previously inputted month and year
     * considering leap years.
     *
     * @param day        inputted by the user
     * @param inputMonth previously inputted by the user parsed from string to integer
     * @param inputYear  previously inputted by the user parsed from string to integer
     * @return day if input meets regex criteria, otherwise returns
     */
    public boolean dayIsValid(String day, int inputMonth, int inputYear) {
        return mDateValidations.dayIsValid(day, inputMonth, inputYear);
    }




}
