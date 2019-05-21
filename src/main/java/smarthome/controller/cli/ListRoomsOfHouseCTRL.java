package smarthome.controller.cli;

import smarthome.dto.RoomDTO;
import smarthome.services.RoomService;

import java.util.List;

public class ListRoomsOfHouseCTRL {

    private final RoomService roomService;

    public ListRoomsOfHouseCTRL() {
        this.roomService = new RoomService();
    }

    public long roomListSize(){
        return this.roomService.size();
    }

    public List<RoomDTO> findAll() {
        return this.roomService.findAll();
    }

}


