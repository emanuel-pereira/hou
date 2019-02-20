package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.ArrayList;
import java.util.List;

public class RoomList {
    private List<Room> roomList;


    /**
     * Constructor method that creates a new list to save Room objects
     */
    public RoomList() {
        this.roomList = new ArrayList<>();
    }

    /**
     * Method to create a new Room object
     * @param name   Name of the room (string)
     * @param floor  Number of the floor (integer)
     * @param length Length of the room (double) to calculate the area
     * @param width  Width of the room (double) to calculate the area
     * @param height Height of the room (double)
     * @return Room with the previous parameters
     */
    public Room createNewRoom(String name, int floor, double length, double width, double height) {
        NameValidations validation = new NameValidations ();
        if (validation.alphanumericName (name)) {
            return new Room(name, floor, length, width, height);
        }
        return null;
    }

    /**
     * Method to add a room object to the Room list, if it is not on the list yet
     *
     * @param newRoom New Room object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addRoom(Room newRoom) {
        if (newRoom != null && !this.roomList.contains(newRoom)) {
            this.roomList.add(newRoom);
            return true;
        } else return false;
    }


    /**
     * Checks if the room name exists in the room list, so the name is not repeated
     * @param name Room name
     * @return True if the room name exists
     */
    public boolean checkIfRoomNameExists(String name) {
        for (Room r : this.getRoomList ()) {
            if (r.getName ().equals (name)) {
                return true;
            }
        }
        return false;
    }


    public boolean removeRoom(Room newRoom) {
        if (this.roomList.contains(newRoom)) {
            this.roomList.remove(newRoom);
            return true;
        } else return false;
    }

    /**
     * Method to get a specific Room in index position i
     *
     * @param i index position of the Room's List
     * @return the specific requested Room
     */
    public Room get(int i) {
        return this.roomList.get(i);
    }

    /**
     * Method to check the list size, normally to confirm if the list size is zero, witch means the list is empty
     * @return The size of the list
     */
    public int getRoomListSize() {
        return this.roomList.size();
    }

    public List<Room> getRoomList() {
        return this.roomList;
    }

    /**
     * Transforms a list of rooms in a numbered list of strings with thw names of the rooms
     *
     * @return List of room sin string
     */
    public String showRoomListInString() {
        List<Room> list = getRoomList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room room : list) {
            result.append(number++);
            result.append(element);
            result.append(room.getName());
            result.append("\n");
        }
        return result.toString();
    }


    public boolean removeDeviceFromRoom(Device device, int indexOfRoom) {
        DeviceList roomDeviceList = this.roomList.get(indexOfRoom - 1).getDeviceList();
        return roomDeviceList.getDeviceList().remove(device);
    }

    public boolean addDeviceToRoom(Device device, int indexOfRoom) {
        DeviceList roomDeviceList = this.roomList.get(indexOfRoom - 1).getDeviceList();
        return roomDeviceList.getDeviceList().add(device);
    }


    public List<Device> getDevicesInAllRoomsByType(String deviceType) {
        List<Device> deviceList;
        List<Device> deviceListByType = new ArrayList<>();
        for (Room room : this.roomList) {
            deviceList = room.getDeviceList().getDeviceList();
            for (Device device : deviceList)
                if (device.getDeviceSpecs().getDeviceType().getDeviceTypeName().equals(deviceType)) {
                    deviceListByType.add(device);
                }
        }
        return deviceListByType;
    }


    public double getEnergyConsumptionByDeviceType(String deviceType) {
        double totalEnergyConsumption = 0;
        for (Device device : getDevicesInAllRoomsByType(deviceType)) {
            totalEnergyConsumption += device.getDeviceSpecs().getEnergyConsumption();
        }
        return Utils.round(totalEnergyConsumption,2);
    }


    public List<Device>getMeteredDevicesInHouse(){
        List<Device> meteredDevListInHouse=new ArrayList<>();
        for(Room room:this.roomList){
            List<Device> meteredDevicesInRoom=room.getDeviceList().getMeteredDevices();
            meteredDevListInHouse.addAll(meteredDevicesInRoom);
        }
        return meteredDevListInHouse;
    }

    public int getMeteredDevicesInHouseSize(){
        List<Device> meteredDeviceList = getMeteredDevicesInHouse();
        return meteredDeviceList.size();
    }

    public String showMeteredDevicesInStr() {
        List<Device> meteredDeviceList = getMeteredDevicesInHouse();
        StringBuilder result = new StringBuilder ();
        String element = " - ";
        int number = 1;
        for (Device device : meteredDeviceList) {
            result.append (number++);
            result.append (element);
            result.append (device.getName());
            result.append ("\n");
        }
        return result.toString ();
    }

}
