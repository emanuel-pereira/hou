package smarthome.controller;

import smarthome.model.*;
import smarthome.model.Validations.NameValidations;

import java.util.List;

public class USAddSetAndListDevicesInRoomCTRL {

    private House mHouse;
    private RoomList mRoomList;
    private NameValidations mNameValidations;


    public USAddSetAndListDevicesInRoomCTRL(House house) {
        mHouse = house;
        mRoomList = mHouse.getRoomList();
        mNameValidations = new NameValidations();
    }

    public String showRoomListInString() {
        return mRoomList.showRoomListInString();
    }

    public String showDeviceListInString(int indexOfRoom) {
        Room room = mRoomList.get(indexOfRoom - 1);
        return room.getDeviceList().showDeviceListInString();
    }

    /**
     * Creates a local instance of a device by invoking the newDevice method and adds it to the room
     * in the index position(-1) in the RoomList selected by the user
     *
     * @param indexOfRoom  index-1 position of room where the new device will be added
     * @param inputName    String variable to name the device
     * @param deviceSpecs  includes the device specifications which, at least, must have a type
     * @param nominalPower double variable where the nominal power of the device is inputted
     * @return true if a new instance of a device is created, and then, added to the device list of the room in the index position
     */
    public boolean addDevice(int indexOfRoom, String inputName, DeviceSpecs deviceSpecs, double nominalPower) {
        Room room = mRoomList.get(indexOfRoom - 1);
        Device device = room.getDeviceList().newDevice(inputName, deviceSpecs, nominalPower);
        return room.getDeviceList().addDevice(device);
    }

    /**
     * Method to validate Strings only accepting alphanumeric characters as well as spaces and hyphens.
     *
     * @param inputName valid name
     * @return name if if is
     */
    public boolean alphanumericName(String inputName) {

        return mNameValidations.alphanumericName(inputName);
    }


    public RoomList getRoomList() {
        return mRoomList;
    }

    public DeviceList getDeviceList(Room room) {

        return room.getDeviceList();
    }

    public List<String> getDeviceAttributesListInString(Device device) {

        return device.getDeviceAttributesInString();
    }

    public String showDeviceAttributesInString(Device device) {
        return device.showDeviceAttributesInString();
    }

    public String getDeviceAttribute(Device device, int indexAttribute) {
        return device.getDeviceAttributesInString().get(indexAttribute);
    }

    public void setAttribute(Device device, String attribute, String newValue) {
        device.setAttributeValue(attribute, newValue);
    }

    public boolean removeDeviceFromRoom(Device device, int indexOfRoom) {
        return mRoomList.removeDeviceFromRoom(device, indexOfRoom);
    }

    public boolean addDeviceToRoom(Device device, int indexOfRoom) {
        return mRoomList.addDeviceToRoom(device, indexOfRoom);
    }
}



