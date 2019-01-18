package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class US605CurrentTempRoomCTRL {

    private House mHouse;
    private SensorTypeList mSensorTypeList;
    private RoomList mRoomList;


    public US605CurrentTempRoomCTRL(House house, SensorTypeList sensorTypeList) {
        mHouse = house;
        mRoomList = mHouse.getRoomListFromHouse ();
        mSensorTypeList = sensorTypeList;
    }

    /**
     * Checks with the Sensor Type List if the required sensor type Temperature was created by the Administrator
     * @param sensorType Sensor type required
     * @return True if the type exist or false if not
     */
    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        return mSensorTypeList.checkIfRequiredSensorTypeExists (sensorType);
    }

    /**
     * Gets the list of room from the RoomList
     * @return List of rooms
     */
    public List<Room> getRoomList() {
        return mRoomList.getRoomList ();
    }

    /**
     * Shows the the list of room from the RoomList in String
     * @return List of rooms in string
     */
    public String showRoomListInString() {
        return mRoomList.showRoomListInString ();
    }

    /**
     * Gets the list of sensor types from the SensorTypeList
     * @return List of types of sensors
     */
    public List<SensorType> getSensorTypeList() {
        return mSensorTypeList.getSensorTypeList ();
    }

    /**
     * Checks if teh required sensor type exists in a room
     * @param roomIndex Position of the room in the list
     * @param temp Required sensor type designation
     * @return True if sensor type exist in the room or false if not
     */
    public boolean checkIfSensorTypeExistsInRoom(int roomIndex, String temp) {
        return mRoomList.getRoomWithIndex(roomIndex - 1).checkIfSensorTypeExistsInRoom (temp);
    }

    /**
     * Shows the current temperature in the selected room
     * @param roomIndex Position of the room in the list
     * @return Current temperature in the room
     */
    public double getCurrentTemp(int roomIndex) {
        String temp = "temperature";
        Room r = mRoomList.getRoomList ().get (roomIndex - 1);
        return r.getSensorListInRoom ().getRequiredSensorPerType (temp).getLastReadingValuePerSensor ();
    }


}