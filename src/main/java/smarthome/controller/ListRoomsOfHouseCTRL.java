package smarthome.controller;

import smarthome.model.RoomList;

import static smarthome.model.House.getHouseRoomList;

public class ListRoomsOfHouseCTRL {

    private RoomList mRoomList;

    public ListRoomsOfHouseCTRL() {
        mRoomList = getHouseRoomList();
    }

    public int roomListSize(){
        return mRoomList.getRoomListSize ();
    }

    public String showListRoomInString() {
       return mRoomList.showRoomListInString ();
    }

}


