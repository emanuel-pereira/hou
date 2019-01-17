package smarthome.controller;

import smarthome.model.Device;
import smarthome.model.House;
import smarthome.model.Room;
import smarthome.model.RoomList;

import java.util.List;

public class US230TotalNominalPowerRoomCTRL {

    private RoomList mRoomList;
    private House mHouse;

    public US230TotalNominalPowerRoomCTRL (House house) {
        mHouse = house;
        mRoomList = house.getRoomListFromHouse ();
    }

    public List<Room> getRoomListCtrl() {
        return mHouse.getRoomListFromHouse().getRoomList();
    }

    public String getRoomNameCtrl (int indexRoom) {
        return mHouse.getRoomListFromHouse().get(indexRoom - 1).getName();
    }

    public String showListRoomInString() {
        return mRoomList.showRoomListInString ();
    }

    public List<Device> getDeviceListInRoomCtrl (int indexRoom) {
        return mHouse.getRoomListFromHouse().get(indexRoom-1).getDeviceList().getDeviceList();
    }

    public double getNominalPowerRoomCtrl(int indexRoom){

        return mHouse.getRoomListFromHouse().get(indexRoom - 1).getNominalPower();
    }




}

