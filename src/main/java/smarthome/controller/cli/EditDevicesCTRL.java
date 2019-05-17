package smarthome.controller.cli;


import smarthome.model.*;
import smarthome.model.validations.NameValidations;

import java.util.ArrayList;
import java.util.List;

import static smarthome.model.House.*;

public class EditDevicesCTRL {

    private final RoomList roomList;
    private final NameValidations nameValidations;

    public EditDevicesCTRL() {
        this.roomList = getHouseRoomList();
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
        return getRoomList().getRoomList().get(roomIndex - 1);
    }

    public Device getDeviceFromIndex(int indexOfRoom, int indexOfDevice) {
        return getRoomFromListIndex(indexOfRoom).getDeviceList().get(indexOfDevice - 1);
    }


    public List<String> getDeviceAttributesListInString(Device device) {
        DeviceSpecs ds = device.getDeviceSpecs();
        return ds.getAttributesNames();
    }

    public int getRoomListSize() {
        return roomList.getRoomListSize();
    }

    public String getDeviceTypeFromIndex(int deviceTypeIndex) {
        return getListOfDeviceTypes().get(deviceTypeIndex - 1);
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
        deviceList.add(device);
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

        return room.getDeviceList().add(device);
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
        return showDeviceTypesList();
    }

    public String showRoomListInString() {
        return this.roomList.showRoomListInString();
    }

    public List<String> showDeviceListInString(int indexOfRoom) {
        Room room = this.roomList.get(indexOfRoom - 1);
        return room.getDeviceList().showDeviceListInString();
    }

    public boolean checkIfDeviceHasAttributes(Device device){
        List<String> list = device.getDeviceSpecs().getAttributesNames();
        return !list.isEmpty();
    }

    public List<String> showDeviceAttributesInString(Device device) {

        List<String> list = device.getDeviceSpecs().getAttributesNames();
        List<String> output = new ArrayList<>();
        if (checkIfDeviceHasAttributes(device)) {

            StringBuilder sb = new StringBuilder();

            for (String s : list) {
                sb.append(s);
                sb.append(": ");
                sb.append(device.getDeviceSpecs().getAttributeValue(s));
                sb.append(" ");
                sb.append(device.getDeviceSpecs().getAttributeUnit(s));
                output.add(sb.toString());
                sb.delete(0, 1000);
            }
        }
        return output;
    }
}

