package smarthome.controller;

import smarthome.model.House;
import smarthome.model.Room;

public class US105AddNewRoomToHouseCTRL {

    private House mRoomList;

    public US105AddNewRoomToHouseCTRL(House inputList) {
        mRoomList = inputList;
    }

    public boolean newRoom(String name, int floor, double height, double width) {
        Room room = mRoomList.newRoom (name, floor, height, width);
               return mRoomList.addRoom (room);
    }


}
