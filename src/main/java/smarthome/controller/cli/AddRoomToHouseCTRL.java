package smarthome.controller.cli;

import org.apache.log4j.Logger;
import smarthome.dto.RoomDetailDTO;
import smarthome.mapper.RoomMapper;
import smarthome.model.Room;
import smarthome.model.RoomList;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;
import smarthome.services.RoomService;

import static smarthome.model.House.getHouseRoomList;

public class AddRoomToHouseCTRL {

    private final RoomService roomService;
    private final RoomList oldList; //this needs to be delete but is necessary due to the repercussions of being removed
    private final RoomMapper roomMapper; //used because of the oldList


    static final Logger log = Logger.getLogger(AddRoomToHouseCTRL.class);

    /**
     * Controller constructor
     *
     */
    public AddRoomToHouseCTRL() {
        this.roomService = new RoomService();
        this.oldList = getHouseRoomList();
        this.roomMapper = new RoomMapper();
    }

    public  AddRoomToHouseCTRL(RoomRepository rRepository, HouseGridRepository gRepository) {
        this.roomService =new RoomService(rRepository,gRepository);
        this.roomMapper = new RoomMapper();
        this.oldList = getHouseRoomList();


    }

    /**
     * Method to create a Room object and add it to a list in the House
     * @param id   Id of the room (string)
     * @param description   Description of the room (string)
     * @param floor  Number of the floor (integer)
     * @param length Length of the room (double) to calculate the area
     * @param width  Width of the room (double) to calculate the area
     * @param height Height of the room (double)
     * @return True if the Room is created and added with success
     */
    public boolean newAddRoom(String id, String description, Integer floor, double length, double width, double height) {
        RoomDetailDTO room = this.roomService.createRoom (id,description, floor, length, width, height);
        Room convertedRoom = this.roomMapper.toObject(room); //will be removed
        this.oldList.addRoom(convertedRoom); //will be removed
        return this.roomService.save (room);
    }

    /**
     * Checks if a room with the same ID was already created
     *
     * @param id Designation of the room
     * @return True if the room ID exist and false if not
     */
    public boolean checkIfRoomIdExists(String id) {
        return this.roomService.checkIfIDExists(id);
    }

}
