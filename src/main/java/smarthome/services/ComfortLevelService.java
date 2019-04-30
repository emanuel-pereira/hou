package smarthome.services;

import smarthome.dto.RoomDTO;
import smarthome.model.*;

import java.util.Calendar;
import java.util.List;

import static smarthome.model.House.*;

public class ComfortLevelService {

    private final RoomList roomList;
    private final GeographicalArea geographicalArea;
    private ReadingList readingList;

    public ComfortLevelService() {
        this.roomList = getHouseRoomList();
        this.geographicalArea = getHouseGA();
    }

    public List<Room> getRoomList() {
        return roomList.getListOfRoomsFiltred("temperature");
    }

    public Room getRoomByIndex(RoomDTO roomDTO) {
        Room selectedRoom = null;
        for (Room room : this.getRoomList())
            if (roomDTO.getID().equals(room.getId()))
                selectedRoom = room;
        return selectedRoom;
    }

    public Sensor getSensorOnRoomByType(SensorType sensorType, RoomDTO roomDTO) {
        Sensor selectedSensor;
        Room room = getRoomByIndex(roomDTO);
        selectedSensor = room.getSensorListInRoom().getListOfSensorsByType(sensorType).getSensorList().get(0);
        return selectedSensor;
    }

    public List<Reading> getReadingsOnInterval(Calendar startDate, Calendar endDate, SensorType sensorType, RoomDTO roomDTO) {
        Sensor sensor = getSensorOnRoomByType(sensorType, roomDTO);
        readingList = sensor.getReadingList().filterByDate(startDate, endDate);
        return readingList.getReadingsList();
    }
}
