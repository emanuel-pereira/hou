package smarthome.controller;


import smarthome.model.House;
import smarthome.model.OccupationArea;
import smarthome.model.Room;

import java.util.List;

public class US108EditRoomOfHouseCTRL {


    private Room mRoom;
    private List<Room> mRoomList;
    private House mHouse;


    /**
     * Method to call the RoomList
     *
     * @param inputRoomList
     */

    public US108EditRoomOfHouseCTRL(List<Room> inputRoomList) {
        mRoomList = inputRoomList;
    }

    /**
     * Method to edit/set the features of the room.
     *
     * @param IndexOfRoom to list the room.
     * @param mName       to set the name of the room.
     * @param mFloor      to set the floor position on the house.
     * @param length      to set the dimensions of the room.
     * @param width       to set the dimensions of the room.
     * @param width       to set the height of the room.
     */
    public void setRoom(int IndexOfRoom, String mName, int mFloor, double length, double width, double height) {


        this.mRoom = mRoomList.get(IndexOfRoom);
        this.mRoom.setName(mName);
        this.mRoom.setFloor(mFloor);
        this.mRoom.setArea(new OccupationArea(length, width));
        this.mRoom.setHeight(height);
    }


    /**
     * Method that shows the rooms in string.
     *
     * @return the features of the room.
     */

    public String showListRoomInString() {
        List<Room> list = this.mRoomList;
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room mRoom : list) {
            result.append(number++);
            result.append(element);
            result.append(mRoom.getName());
            result.append("\n");
        }
        return result.toString();
    }

}


