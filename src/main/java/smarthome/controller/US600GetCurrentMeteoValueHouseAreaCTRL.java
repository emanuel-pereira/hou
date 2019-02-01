package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class US600GetCurrentMeteoValueHouseAreaCTRL {

    private House mHouse;
    private SensorTypeList mSensorTypeList;

    public US600GetCurrentMeteoValueHouseAreaCTRL(House house, SensorTypeList sensorTypeList) {
        mHouse = house;
        mSensorTypeList = sensorTypeList;
    }

    public int getSensorTypeListSize(){
        return mSensorTypeList.size();
    }

    public GeographicalArea getHouseGA(){
        return mHouse.getHouseGA();
    }
    public String showSensorTypeList() {
        return mSensorTypeList.showSensorTypeListInString();
    }

    public SensorType getSensorTypeByIndex(int indexOfSensorType) {
        return mSensorTypeList.getSensorTypeList().get(indexOfSensorType - 1);
    }

    public int getSensorListOfTypeSize(SensorType sensorType) {
        GeographicalArea houseGA= mHouse.getHouseGA();
        SensorList gaOfHouseSensorList=houseGA.getSensorListInGA();
        return gaOfHouseSensorList.getListOfSensorsByType(sensorType).size();

    }

    public Sensor getClosestSensorByType(SensorType sensorType) {
        return mHouse.getHouseGA().getSensorWithLatestReadingsByType(sensorType);
    }
    public double getLastReadingOfSensor(Sensor sensor){
        return sensor.getLastReadingValuePerSensor();
    }
}
