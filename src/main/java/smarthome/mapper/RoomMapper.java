package smarthome.mapper;

import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.OccupationArea;
import smarthome.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {

    public RoomDTO toDto(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setID(room.getId());
        roomDTO.setDescription(room.getMeteredDesignation());
        return roomDTO;
    }

    public List<RoomDTO> toDtoList(List<Room>roomList) {
        return roomList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public RoomDetailDTO toDetailDto(Room room) {
        RoomDetailDTO roomDTO = new RoomDetailDTO();
        roomDTO.setId(room.getId());
        roomDTO.setDescription(room.getMeteredDesignation());
        roomDTO.setFloor(room.getFloor());
        roomDTO.setLength(room.getArea().getLength());
        roomDTO.setWidth(room.getArea().getWidth());
        roomDTO.setHeight(room.getHeight());
        return roomDTO;
    }

    public List<RoomDetailDTO> toDetailDtoList(List<Room>roomList) {
        return roomList.stream().map(this::toDetailDto).collect(Collectors.toList());
    }

    public Room toObject(RoomDetailDTO roomDto){

        Room room= new Room();
        room.setId(roomDto.getId());
        room.setDescription(roomDto.getDescription());
        room.setFloor(roomDto.getFloor());
        room.setArea(new OccupationArea(roomDto.getLength(),roomDto.getWidth()));
        room.setHeight(roomDto.getHeight());

        return room;
    }


}
