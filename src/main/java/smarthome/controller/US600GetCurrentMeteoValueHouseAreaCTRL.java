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

    public String getSensorTypeListInString() {
        return mSensorTypeList.showSensorTypeListInString();
    }

    public List <SensorType> getSensorTypeList() {
        return mSensorTypeList.getSensorTypeList();
    }

    public SensorType getSensorTypeByIndex(int indexOfSensorType) {
        return mSensorTypeList.getSensorTypeList().get(indexOfSensorType - 1);
    }

    public List<Sensor> getListSensorsOfOneType(SensorType sensorType) {
        return mHouse.getHouseGA().getSensorListInGA().getListOfSensorsByType(sensorType);
    }

    public Sensor getTheClosestSensorToGA(List<Sensor> sensorList) {
        return mHouse.getHouseGA().getTheClosestSensor(sensorList);
    }
    public double getLastReadingOfSensor(Sensor sensor){
        return sensor.getLastReadingValuePerSensor();
    }
}
