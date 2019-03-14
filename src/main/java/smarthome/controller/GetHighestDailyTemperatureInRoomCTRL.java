package smarthome.controller;

import smarthome.model.*;

import java.util.Calendar;
import java.util.List;


public class GetHighestDailyTemperatureInRoomCTRL {
    private House house = this.house;

    public RoomList getCurrentRoomList() {
        return this.house.getRoomList();
    }

    public SensorList getSensorsOfTypeInRoom(SensorType type, int room) {
        RoomList roomList = this.house.getRoomList();
        Room selectedRoom = roomList.get(room);
        SensorList sl = selectedRoom.getSensorListInRoom();
        return sl.getListOfSensorsByType(type);
    }

    public ReadingList getSelectedSensorReadings(Sensor sensor) {
        return sensor.getReadingList();
    }

}