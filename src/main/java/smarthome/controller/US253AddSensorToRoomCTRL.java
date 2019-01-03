package smarthome.controller;

import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public class US253AddSensorToRoomCTRL {

    private DataTypeList mDataTypeList;
    private List<Room> mRoomList;

    public US253AddSensorToRoomCTRL(DataTypeList dataTypeList, List<Room> inputList) {
        mRoomList = inputList;
        mDataTypeList = dataTypeList;
    }

    public String showDataTypeListInString() {
        List<DataType> list = mDataTypeList.getDataTypeList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (DataType dataType : list) {
            result.append(number++);
            result.append(element);
            result.append(dataType.getDataTypeDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    public void addNewSensorToRoom(String inputName, GregorianCalendar startDate, int dataTypeIndex, int indexOfRoom) {
        Room r = mRoomList.get(indexOfRoom - 1);
        Sensor s = new Sensor(inputName, startDate, mDataTypeList.getDataTypeList().get(dataTypeIndex - 1).toString());
        s.setRoom(r);
    }

    public String showRoomListInString() {
        List<Room> list = mRoomList;
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
