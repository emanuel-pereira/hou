package smarthome.controller.cli;

import smarthome.dto.RoomDTO;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;
import smarthome.services.RoomService;

import java.util.List;

public class ListRoomsOfHouseCTRL {

    private final RoomService roomService;


    /**
     * ListRoomsOfHouseCTRL constructor
     */
    public ListRoomsOfHouseCTRL() {
        this.roomService = new RoomService();
    }

    /**
     * Test constructor
     * @param rRepository RoomRepository
     * @param gRepository HouseGridRepository
     */
    public  ListRoomsOfHouseCTRL(RoomRepository rRepository, HouseGridRepository gRepository) {
        this.roomService =new RoomService(rRepository,gRepository);
    }

    /**
     * Check the number of rooms saved in the repository
     * @return Number of rooms
     */
    public long roomListSize(){
        return this.roomService.size();
    }

    /**
     * Check if the repository is empty
     * @return True if empty
     */
    public boolean roomListEmpty(){
        return this.roomService.checkIfRoomRepositoryEmpty();
    }

    /**
     * Find all the rooms in the repository
     * @return List of RoomDTOs
     */
    public List<RoomDTO> findAll() {
        return this.roomService.findAll();
    }

}


