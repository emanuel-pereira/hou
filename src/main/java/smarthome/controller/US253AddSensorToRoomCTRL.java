package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public class US253AddSensorToRoomCTRL {

    private SensorTypeList mSensorTypeList;
    private House mHouse;

    public US253AddSensorToRoomCTRL(SensorTypeList sensorTypeList, House house) {
        mHouse = house;
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
        Room r = mHouse.getRoomList().get(indexOfRoom-1);
        Sensor s = new Sensor(inputName, startDate, mSensorTypeList.getSensorTypeList().get(sensorTypeIndex - 1).toString());
        s.setRoom(r);
    }

    public String showRoomListInString() {
        List<Room> list = mHouse.getRoomList();
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
