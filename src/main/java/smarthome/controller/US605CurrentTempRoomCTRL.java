package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class US605CurrentTempRoomCTRL {

    private House mHouse;
    private SensorList mSensorList;
    private SensorTypeList mSensorTypeList;
    private RoomList mRoomList;
    private Sensor mSensor;
    private Room mRoom;


    public US605CurrentTempRoomCTRL(House house, SensorTypeList sensorTypeList) {
        mHouse = house;
        mRoomList = mHouse.getRoomListFromHouse();
        mSensorTypeList = sensorTypeList;
    }

    public String showRoomListInString() {
        return mRoomList.showRoomListInString ();
    }

    public double getCurrentTemp(int roomIndex) {
        double currentTemp = 0;
        String temp = "temperature";
        Room r = mRoomList.getRoomList().get(roomIndex - 1);
        if (r.getSensorListInRoom ().checkIfRequiredSensorTypeExists (temp)) {
            currentTemp = r.getSensorListInRoom ().getRequiredSensorPerType (temp).getLastReadingValuePerSensor ();
        }
        return currentTemp;
    }



    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        return mSensorTypeList.checkIfRequiredSensorTypeExists (sensorType);
    }



}