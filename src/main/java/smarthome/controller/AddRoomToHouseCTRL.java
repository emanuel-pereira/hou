package smarthome.controller;

import smarthome.model.Room;
import smarthome.model.RoomList;
import smarthome.repository.Repositories;

import java.util.logging.Logger;

import static smarthome.model.House.getHouseRoomList;

public class AddRoomToHouseCTRL {

    private RoomList roomList;

    static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AddRoomToHouseCTRL.class);

    /**
     * Controller constructor
     *
     */
    public AddRoomToHouseCTRL() {
        this.roomList = getHouseRoomList();

    }

    /**
     * Method to create a Room object and add it to a list in the House
     * @param id   Id of the room (string)
     * @param name   Name of the room (string)
     * @param floor  Number of the floor (integer)
     * @param length Length of the room (double) to calculate the area
     * @param width  Width of the room (double) to calculate the area
     * @param height Height of the room (double)
     * @return True if the Room is created and added with success
     */
    public boolean newAddRoom(String id, String name, Integer floor, double length, double width, double height) {
        Room room = this.roomList.createNewRoom (id,name, floor, length, width, height);
        if (!this.roomList.addRoom (room)) {
            return false;
        } else {
            //Repository call
            try {
                Repositories.saveRoom(room);
            } catch (NullPointerException e) {
                log.info("Repository unreachable");
            }
            return true;
        }
    }


    /**
     * Checks if a room with the same ID was already created
     *
     * @param id Designation of the room
     * @return True if the room ID exist and false if not
     */
    public boolean checkIfRoomIdExists(String id) {
        return this.roomList.checkIfRoomIDExists(id);
    }

    /**
     * Checks if a room with the same name was already created
     *
     * @param name Designation of the room
     * @return True if the room name exist and false if not
     */
    public boolean checkIfRoomNameExists(String name) {
        return this.roomList.checkIfRoomNameExists (name);
    }

}
