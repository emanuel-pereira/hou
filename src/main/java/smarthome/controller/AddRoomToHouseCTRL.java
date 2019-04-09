package smarthome.controller;

import smarthome.model.Room;
import smarthome.model.RoomList;

import static smarthome.model.House.getHouseRoomList;

public class AddRoomToHouseCTRL {

    private RoomList roomList;

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
        return this.roomList.addRoom (room);
    }

    /**
     * Checks if a room with the same name was already created
     *
     * @param name Designation of the room
     * @return True if the room name exist sand false if not
     */
    public boolean checkIfRoomNameExists(String name) {
        return this.roomList.checkIfRoomNameExists (name);
    }

}
