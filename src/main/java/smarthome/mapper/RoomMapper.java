package smarthome.mapper;

import smarthome.dto.RoomDTO;
import smarthome.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {

    public RoomDTO toDto(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setID(room.getId());
        roomDTO.setName(room.getMeteredDesignation());
        return roomDTO;
    }

    public List<RoomDTO> toDtoList(List<Room>roomList) {
        List<Room> listOfRooms=roomList;
        return listOfRooms.stream().map(this::toDto).collect(Collectors.toList());
    }
}
