package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;

public class US620GetTotalRainfallOnADayOfHouseCTRL {

    private US600GetCurrentMeteoValueHouseAreaCTRL mCTRL600;
    private GeographicalArea mGA;
    private SensorTypeList mSensorTypeList;
    private House mhouse;

    public US620GetTotalRainfallOnADayOfHouseCTRL(House house, SensorTypeList sensorTypeList) {

        mSensorTypeList = sensorTypeList;
        mCTRL600 = new US600GetCurrentMeteoValueHouseAreaCTRL(house, sensorTypeList);
        mhouse = house;
        mGA = house.getHouseGA();
    }

    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        return mSensorTypeList.checkIfRequiredSensorTypeExists(sensorType);
    }

    public double requestReadingRainfall(GregorianCalendar inputDate) {
        Sensor closestSensor = mCTRL600.getTheClosestSensorToGA(mGA.getGASensorsByType("rainfall").getSensorList());
        return closestSensor.getReadingList().totalValueInGivenDay(inputDate);
    }

}
