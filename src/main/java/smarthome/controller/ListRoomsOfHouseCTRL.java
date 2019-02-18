package smarthome.controller;

import smarthome.model.RoomList;

public class ListRoomsOfHouseCTRL {

    private RoomList mRoomList;

    public ListRoomsOfHouseCTRL(RoomList inputRoomList) {
        mRoomList = inputRoomList;
    }

    public int roomListSize(){
        return mRoomList.getRoomListSize ();
    }

    public String showListRoomInString() {
       return mRoomList.showRoomListInString ();
    }

}


