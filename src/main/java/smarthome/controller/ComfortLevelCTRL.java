package smarthome.controller;

import smarthome.dto.RoomDTO;
import smarthome.mapper.RoomMapper;
import smarthome.model.Room;
import smarthome.services.ComfortLevelService;

import java.util.List;

public class ComfortLevelCTRL {

    private ComfortLevelService comfortLevelService;
    private RoomMapper roomMapper = new RoomMapper();

    public ComfortLevelCTRL() {
        this.comfortLevelService = new ComfortLevelService();
    }

    public List<RoomDTO> getListOfRooms(){
        List<Room> rooms = comfortLevelService.getRoomList();
        return this.roomMapper.toDtoList(rooms);
    }

    public RoomDTO getRoomByID(int roomIndex){
       return getListOfRooms().get(roomIndex);
    }
}
