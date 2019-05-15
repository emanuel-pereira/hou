package smarthome.controller.CLI;

import smarthome.model.RoomList;

import static smarthome.model.House.getHouseRoomList;

public class ListRoomsOfHouseCTRL {

    private final RoomList roomList;

    public ListRoomsOfHouseCTRL() {
        this.roomList = getHouseRoomList();
    }

    public int roomListSize(){
        return this.roomList.getRoomListSize();
    }

    public String showListRoomInString() {
        return this.roomList.showRoomListInString();
    }

}


