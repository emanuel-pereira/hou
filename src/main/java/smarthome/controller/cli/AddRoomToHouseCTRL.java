package smarthome.controller.cli;

import org.apache.log4j.Logger;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.Room;
import smarthome.model.RoomList;
import smarthome.services.RoomService;

import java.util.ArrayList;

import static smarthome.model.House.getHouseRoomList;

public class AddRoomToHouseCTRL {

    private final RoomService roomList;

    static final Logger log = Logger.getLogger(AddRoomToHouseCTRL.class);

    /**
     * Controller constructor
     *
     */
    public AddRoomToHouseCTRL() {
        this.roomList = new RoomService();

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
        RoomDetailDTO room = this.roomList.createRoom (id,description, floor, length, width, height);
        //remove 3 lines above when DDD is stable
        RoomList oldList = getHouseRoomList();
        Room r = oldList.createNewRoom (id,description, floor, length, width, height);
        oldList.addRoom (r);
        return this.roomList.save (room);
    }


    /**
     * Checks if a room with the same ID was already created
     *
     * @param id Designation of the room
     * @return True if the room ID exist and false if not
     */
    public boolean checkIfRoomIdExists(String id) {
        return this.roomList.checkIfIDExists(id);
    }

}
