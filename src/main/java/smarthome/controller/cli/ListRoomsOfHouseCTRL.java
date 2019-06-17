package smarthome.controller.cli;

import smarthome.dto.RoomDTO;
import smarthome.mapper.RoomMapper;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;
import smarthome.services.RoomService;

import java.util.List;

import static smarthome.model.House.getHouseRoomList;

public class ListRoomsOfHouseCTRL {

    private final RoomService roomService;

    public ListRoomsOfHouseCTRL() {
        this.roomService = new RoomService();
    }

    public  ListRoomsOfHouseCTRL(RoomRepository rRepository, HouseGridRepository gRepository) {
        this.roomService =new RoomService(rRepository,gRepository);
    }

    public long roomListSize(){
        return this.roomService.size();
    }

    public boolean roomListEmpty(){
        return this.roomService.checkIfRoomRepositoryEmpty();
    }

    public List<RoomDTO> findAll() {
        return this.roomService.findAll();
    }

}


