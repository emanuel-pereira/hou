package smarthome.controller;

import smarthome.model.*;

public class AddRoomToHouseCTRL {

    private House currentHouse;
    private RoomList mRoomList;


    public AddRoomToHouseCTRL(House house) {
        currentHouse = house;
        mRoomList = currentHouse.getRoomList();

    }

    /**
     * Method to create a Room object and add to a list in the House
     * @param name Name of the floor (string)
     * @param floor Number of the floor (integer)
     * @param length Height of the room (double) to calculate the area
     * @param width Height of the room (double) to calculate the area
     * @return
     */
    public boolean newRoom(String name, Integer floor, double length, double width, double height) {
        Room room = mRoomList.createNewRoom (name, floor, length, width, height);
               return mRoomList.addRoom (room);
    }


}
