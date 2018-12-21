package smarthome.controller;

import smarthome.model.House;
import smarthome.model.Room;

public class US105AddNewRoomToHouseCTRL {

    private House mRoomList;

    public US105AddNewRoomToHouseCTRL(House inputList) {
        mRoomList = inputList;
    }

    /**
     * Method to create a Room object and add to a list in the House
     * @param name Name of the floor (string)
     * @param floor Number of the floor (integer)
     * @param length Height of the room (double) to calculate the area
     * @param width Height of the room (double) to calculate the area
     * @return
     */
    public boolean newRoom(String name, Integer floor, double length, double width) {
        Room room = mRoomList.newRoom (name, floor, length, width);
               return mRoomList.addRoom (room);
    }


}
