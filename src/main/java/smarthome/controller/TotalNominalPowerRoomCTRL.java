package smarthome.controller;


import smarthome.model.House;
import smarthome.model.RoomList;


public class TotalNominalPowerRoomCTRL {

    private RoomList mRoomList;
    private House mHouse;

    public TotalNominalPowerRoomCTRL(House house) {
        mHouse = house;
        mRoomList = house.getRoomList ();
    }

    public int getRoomListSize(){
        return mRoomList.getRoomListSize ();
    }

    public String getRoomNameCtrl (int indexRoom) {
        return mHouse.getRoomList ().get(indexRoom - 1).getName();
    }

    public String showListRoomInString() {
        return mRoomList.showRoomListInString ();
    }

    public int getDeviceListSizeInRoom (int indexRoom) {
        return mHouse.getRoomList ().get(indexRoom-1).getDeviceList ().size (); //
    }

    public double getNominalPowerRoomCtrl(int indexRoom){

        return mHouse.getRoomList ().get(indexRoom - 1).getNominalPower();
    }




}

