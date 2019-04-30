package smarthome.controller;

import smarthome.model.Room;
import smarthome.model.RoomList;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.List;

import static smarthome.model.House.getHouseRoomList;

public class GetCurrentTemperatureInRoomCTRL {

    private final SensorTypeList sensorTypeList;
    private final RoomList roomList;


    public GetCurrentTemperatureInRoomCTRL(SensorTypeList sensorTypeList) {
        this.roomList = getHouseRoomList();
        this.sensorTypeList = sensorTypeList;
    }

    /**
     * Checks with the Sensors Type List if the required sensor type Temperature was created by the Administrator
     * @param sensorType Sensors type required
     * @return True if the type exist or false if not
     */
    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        return this.sensorTypeList.checkIfSensorTypeExists(sensorType);
    }

    /**
     * Gets the list of room from the RoomList
     * @return List of rooms
     */
    public List<Room> getRoomList() {
        return this.roomList.getRoomList();
    }

    /**
     * Shows the the list of room from the RoomList in String
     * @return List of rooms in string
     */
    public String showRoomListInString() {
        return this.roomList.showRoomListInString();
    }

    /**
     * Gets the list of sensor types from the SensorTypeList
     * @return List of types of sensors
     */
    public List<SensorType> getSensorTypeList() {
        return this.sensorTypeList.getSensorTypeList();
    }

    /**
     * Checks if teh required sensor type exists in a room
     * @param roomIndex Position of the room in the list
     * @param temp Required sensor type designation
     * @return True if sensor type exist in the room or false if not
     */
    public boolean checkIfSensorTypeExistsInRoom(int roomIndex, String temp) {
        return this.roomList.get(roomIndex - 1).checkIfSensorTypeExistsInRoom(temp);
    }

    /**
     * Shows the current temperature in the selected room
     * @param roomIndex Position of the room in the list
     * @return Current temperature in the room
     */
    public double getCurrentTemp(int roomIndex) {
        String temp = "temperature";
        Room r = this.roomList.getRoomList().get(roomIndex - 1);
        return r.getSensorListInRoom ().getRequiredSensorPerType (temp).getLastReadingValuePerSensor ();
    }


}