package smarthome.controller;

import smarthome.model.*;

public class US210AddNewDeviceToRoomCTRL {

    private House mHouse;


    public US210AddNewDeviceToRoomCTRL(House house) {
        mHouse=house;
    }

    public String showRoomListInString() {
        return mHouse.getRoomListFromHouse().showRoomListInString();
    }
    public boolean addDeviceWithSpecsToRoom(int indexOfRoom, String inputName, DeviceSpecs deviceSpecs, double nominalPower) {
        Device device = mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().newDeviceWithSpecs(inputName,deviceSpecs,mHouse.getRoomListFromHouse().get(indexOfRoom-1),nominalPower);
        return mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().addDevice(device);
    }

    public boolean addDeviceWithoutSpecsToRoom(String inputName, int indexOfRoom, String deviceType, double nominalPower){
        Device device = mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().newDeviceWithoutSpecs(inputName,mHouse.getRoomListFromHouse().get(indexOfRoom-1),deviceType,nominalPower);
        return mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().addDevice(device);
    }
}
