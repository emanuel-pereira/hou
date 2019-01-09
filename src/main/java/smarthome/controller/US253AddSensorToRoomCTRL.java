package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public class US253AddSensorToRoomCTRL {

    private SensorTypeList mSensorTypeList;
    private House mHouse;
    private RoomList mRoomList;

    public US253AddSensorToRoomCTRL(House house, SensorTypeList sensorTypeList) {
        mHouse = house;
        mRoomList = mHouse.getRoomListFromHouse();
        mSensorTypeList = sensorTypeList;
    }

    public String showDataTypeListInString() {
        List<SensorType> list = mSensorTypeList.getSensorTypeList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (SensorType dataType : list) {
            result.append(number++);
            result.append(element);
            result.append(dataType.getSensorTypeDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    public void addNewSensorToRoom(String inputName, GregorianCalendar startDate, int sensorTypeIndex, int indexOfRoom) {
        Room r = mRoomList.get(indexOfRoom - 1);
        Sensor s= r.getSensorListInRoom().createNewInternalSensor(inputName, startDate, mSensorTypeList.getSensorTypeList().get(sensorTypeIndex-1));
        r.getSensorListInRoom().addSensor(s);
    }

    public String showRoomListInString() {
        List<Room> list = mRoomList.getRoomList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room r : list) {
            result.append(number++);
            result.append(element);
            result.append(r.getName());
            result.append("\n");
        }
        return result.toString();
    }
}
