package smarthome.controller;


import smarthome.model.Room;

import java.util.List;

public class US108ListTheRoomsOfHouseCTRL {


    private List<Room> mRoomList;

    /**
     * Method to call the RoomList
     *
     * @param inputRoomList
     */

    public US108ListTheRoomsOfHouseCTRL(List<Room> inputRoomList) {
        mRoomList = inputRoomList;
    }



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


