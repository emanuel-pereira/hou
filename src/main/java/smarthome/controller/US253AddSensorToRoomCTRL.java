package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;

public class US253AddSensorToRoomCTRL {

    private SensorTypeList mSensorTypeList;
    private House mHouse;
    private RoomList mRoomList;

    public US253AddSensorToRoomCTRL(House house, SensorTypeList sensorTypeList) {
        mHouse = house;
        mRoomList = mHouse.getRoomListFromHouse();
        mSensorTypeList = sensorTypeList;
    }

    public void addNewSensorToRoom(String inputName, GregorianCalendar startDate, int sensorTypeIndex, int indexOfRoom, String unit, ReadingList readingList) {
        Room r = mRoomList.getRoomWithIndex(indexOfRoom - 1);
        Sensor s= r.getSensorListInRoom().createNewInternalSensor(inputName, startDate, mSensorTypeList.getSensorTypeList().get(sensorTypeIndex-1),unit,readingList);
        r.getSensorListInRoom().addSensor(s);
    }
}
