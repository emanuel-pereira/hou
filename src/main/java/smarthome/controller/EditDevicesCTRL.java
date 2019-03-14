package smarthome.controller;

import smarthome.model.*;
import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.validations.NameValidations;

import java.util.ArrayList;
import java.util.List;

public class EditDevicesCTRL {

    private House house;
    private RoomList roomList;
    private NameValidations nameValidations;

    public EditDevicesCTRL(House house) {
        this.house = house;
        this.roomList = this.house.getRoomList();
        this.nameValidations = new NameValidations();
    }


    /**
     * Getters
     **/

    public RoomList getRoomList() {
        return roomList;
    }

    public DeviceList getDeviceList(Room room) {
        return room.getDeviceList();
    }

    public String getDeviceAttribute(Device device, int indexAttribute) {
        DeviceSpecs ds = device.getDeviceSpecs();
        List<String> attributes = ds.getAttributesNames();
        return attributes.get(indexAttribute);
    }

    public Room getRoomFromListIndex(int roomIndex) {
        return house.getRoomList().getRoomList().get(roomIndex - 1);
    }

    public Device getDeviceFromIndex(int indexOfRoom, int indexOfDevice) {
        return getRoomFromListIndex(indexOfRoom).getDeviceList().get(indexOfDevice - 1);
    }


    public List<String> getDeviceAttributesListInString(Device device) {
        DeviceSpecs ds = device.getDeviceSpecs();
        List<String> names = ds.getAttributesNames();
        List<String> units = ds.getAttributeUnits();
        List<Double> values = ds.getAttributeValues();

        List<String> output = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            output.add(names.get(i) + " [" + units.get(i) + "]: " + values.get(i));
        }
        return output;
    }

    public int getRoomListSize() {
        return roomList.getRoomListSize();
    }

    public String getDeviceTypeFromIndex(int deviceTypeIndex) {
        return house.getListOfDeviceTypes().get(deviceTypeIndex - 1);
    }

    /**
     * Setters
     **/
    public void setAttribute(Device device, String attribute, Double newValue) {
        DeviceSpecs ds = device.getDeviceSpecs();
        ds.setAttributeValue(attribute, newValue);
    }


    public Device createDevice(Room room, String deviceType, String deviceName, double nominalPower) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DeviceList deviceList = room.getDeviceList();
        Device device = deviceList.newDevice(deviceName, deviceType, nominalPower);
        deviceList.addDevice(device);
        return device;
    }

    /**
     * Creates a local instance of a device by invoking the newDevice method and adds it to the room
     * in the index position(-1) in the RoomList selected by the user
     *
     * @param indexOfRoom  index-1 position of room where the new device will be added
     * @param inputName    String variable to name the device
     * @param nominalPower double variable where the nominal power of the device is inputted
     * @return true if a new instance of a device is created, and then, added to the device list of the room in the index position
     */
    public boolean addDevice(int indexOfRoom, String inputName, String deviceType, double nominalPower) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Room room = this.roomList.get(indexOfRoom - 1);
        Device device;
        device = room.getDeviceList().newDevice(inputName, deviceType, nominalPower);

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
        Room room = roomList.get(indexOfRoom - 1);
        Device device = room.getDeviceList().get(deviceIndex);
        return room.getDeviceList().removeDevice(device);
    }

    public boolean removeDeviceFromRoom(Device device, int indexOfRoom) {
        return roomList.removeDeviceFromRoom(device, indexOfRoom);
    }

    public boolean addDeviceToRoom(Device device, int indexOfRoom) {
        return roomList.addDeviceToRoom(device, indexOfRoom);
    }

    /**
     * Method that calls the DeviceList method to set the Device status flag to false (false meaning deactivated)
     *
     * @param indexOfRoom Room Index
     * @param deviceIndex Device Index
     * @return boolean result true if device status successfully set to false, deactivated
     */
    public boolean deactivateDevice(int indexOfRoom, int deviceIndex) {
        Room room = roomList.get(indexOfRoom - 1);
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

        return nameValidations.alphanumericName(inputName);
    }

    public String showDeviceTypesListInString() {
        return house.showDeviceTypesList();
    }

    public String showRoomListInString() {
        return this.roomList.showRoomListInString();
    }

    public List<String> showDeviceListInString(int indexOfRoom) {
        Room room = this.roomList.get(indexOfRoom - 1);
        return room.getDeviceList().showDeviceListInString();
    }

    public List<String> showDeviceAttributesInString(Device device) {
        return device.getDeviceSpecs().getAttributesNames();
    }
}

