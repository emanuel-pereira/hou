package smarthome.controller;

import smarthome.model.*;
import smarthome.model.validations.NameValidations;

import java.util.ArrayList;
import java.util.List;

public class EditDevicesCTRL {

    private House mHouse;
    private RoomList mRoomList;
    private NameValidations mNameValidations;


    public EditDevicesCTRL(House house) {
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
    public boolean addDevice(int indexOfRoom, String inputName, String deviceType, double nominalPower) {
        Room room = mRoomList.get(indexOfRoom - 1);
        Device device = null;
        try {
            device = room.getDeviceList().newDevice(inputName, deviceType, nominalPower);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return room.getDeviceList().addDevice(device);
    }

    /**
     * Method that call the DeviceList method to remove a Device from the the Device's List
     *
     * @param indexOfRoom Room Index
     * @param deviceIndex Device Index
     * @return boolean result true if device successfully removed from the list
     */
    public boolean removeDevice(int indexOfRoom, int deviceIndex) {
        Room room = mRoomList.get(indexOfRoom - 1);
        Device device = room.getDeviceList().get(deviceIndex);
        return room.getDeviceList().removeDevice(device);
    }

    /**
     * Method that calls the DeviceList method to set the Device status flag to false (false meaning deactivated)
     *
     * @param indexOfRoom Room Index
     * @param deviceIndex Device Index
     * @return boolean result true if device status successfully set to false, deactivated
     */
    public boolean deactivateDevice(int indexOfRoom, int deviceIndex) {
        Room room = mRoomList.get(indexOfRoom - 1);
        Device device = room.getDeviceList().get(deviceIndex);
        return room.getDeviceList().deactivateDevice(device);
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

    public String getDeviceAttribute(Device device, int indexAttribute) {
        DeviceSpecs ds = device.getDeviceSpecs();
        List<String> attributes = ds.getAttributesNames();
        return attributes.get(indexAttribute);

    }

    public void setAttribute(Device device, String attribute, Double newValue) throws IllegalAccessException {
        DeviceSpecs ds = device.getDeviceSpecs();
        ds.setAttributeValue(attribute, newValue);
    }

    public boolean removeDeviceFromRoom(Device device, int indexOfRoom) {
        return mRoomList.removeDeviceFromRoom(device, indexOfRoom);
    }

    public boolean addDeviceToRoom(Device device, int indexOfRoom) {
        return mRoomList.addDeviceToRoom(device, indexOfRoom);
    }

    public Room getRoomFromListIndex(int roomIndex) {
        return mHouse.getRoomList().getRoomList().get(roomIndex - 1);
    }

    public String showDeviceTypesListInString() {
        return mHouse.showDeviceTypesList();
    }

    public String getDeviceTypeFromIndex(int deviceTypeIndex) {
        return mHouse.getListOfDeviceTypes().get(deviceTypeIndex - 1);
    }

    public Device createDevice(Room room, String deviceName, String deviceType, double nominalPower) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Device device = room.getDeviceList().newDevice(deviceName, deviceType, nominalPower);
        room.getDeviceList().addDevice(device);
        return device;
    }

    public String showDeviceAttributesInString(Device device) throws IllegalAccessException {
        return device.showDeviceAttributesInString();
    }

    public Device getDeviceFromIndex(int indexOfRoom, int indexOfDevice) {
        return getRoomFromListIndex(indexOfRoom).getDeviceList().get(indexOfDevice - 1);

    }

    public List<String> getDeviceAttributesListInString(Device device) {
        return device.getDeviceSpecs().getAttributesNames();
    }

    public int getRoomListSize() {
        return mRoomList.getRoomListSize();
    }

    public Device createDevice(Room selectedRoom, String deviceType) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        DeviceList deviceList = selectedRoom.getDeviceList();

        Device d = deviceList.newDevice("", deviceType, 0);

        return d;
    }
}