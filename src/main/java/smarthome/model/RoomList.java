package smarthome.model;

import org.apache.log4j.Logger;
import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class RoomList {

    private final List<Room> listOfRooms;
    static final Logger log = Logger.getLogger(RoomList.class);


    /**
     * Constructor method that creates a new list to save Room objects
     */
    public RoomList() {
        this.listOfRooms = new ArrayList<>();
    }

    /**
     * Method to create a new Room object
     *
     * @param id     Id of the room (string)
     * @param name   Name of the room (string)
     * @param floor  Number of the floor (integer)
     * @param length Length of the room (double) to calculate the area
     * @param width  Width of the room (double) to calculate the area
     * @param height Height of the room (double)
     * @return Room with the previous parameters
     */
    public Room createNewRoom(String id, String name, int floor, double length, double width, double height) {
        NameValidations validation = new NameValidations();
        if (validation.alphanumericName(name) && validation.alphanumericName(id)) {
            return new Room(id, name, floor, length, width, height);
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
        if (newRoom != null && !this.listOfRooms.contains(newRoom)) {
            this.listOfRooms.add(newRoom);

            //Repository call
            try {
                Repositories.saveRoom(newRoom);
            } catch (NullPointerException e) {
                log.warn("Repository unreachable"); }
            return true;
        } else return false;
    }


    /**
     * Checks if the room ID exists in the room list, so the ID is not repeated
     *
     * @param id Room ID
     * @return True if the room ID exists
     */
    public boolean checkIfRoomIDExists(String id) {
        for (Room r : this.getRoomList()) {
            if (r.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the room name exists in the room list, so the name is not repeated
     *
     * @param name Room name
     * @return True if the room name exists
     */
    public boolean checkIfRoomNameExists(String name) {
        for (Room r : this.getRoomList()) {
            if (r.getMeteredDesignation().equals(name)) {
                return true;
            }
        }
        return false;
    }


    public boolean removeRoom(Room newRoom) {
        if (this.listOfRooms.contains(newRoom)) {
            this.listOfRooms.remove(newRoom);
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
        return this.listOfRooms.get(i);
    }

    public Room getRoomById(String inputId) {
        Room matchedRoom = null;
        for (Room room : this.listOfRooms)
            if (room.getId().equals(inputId))
                matchedRoom = room;
        return matchedRoom;

    }

    /**
     * Method to check the list size, normally to confirm if the list size is zero, witch means the list is empty
     *
     * @return The size of the list
     */
    public int getRoomListSize() {
        return this.listOfRooms.size();
    }

    public List<Room> getRoomList() {
        return this.listOfRooms;
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
            result.append(room.getId() + ", " + room.getMeteredDesignation());
            result.append("\n");
        }
        return result.toString();
    }


    public boolean removeDeviceFromRoom(Device device, int indexOfRoom) {
        DeviceList roomDeviceList = this.listOfRooms.get(indexOfRoom - 1).getDeviceList();
        return roomDeviceList.getDeviceList().remove(device);
    }

    public boolean addDeviceToRoom(Device device, int indexOfRoom) {
        DeviceList roomDeviceList = this.listOfRooms.get(indexOfRoom - 1).getDeviceList();
        return roomDeviceList.getDeviceList().add(device);
    }

    public List<Device> getDevicesInAllRoomsByType(String deviceType) {
        List<Device> deviceList;
        List<Device> deviceListByType = new ArrayList<>();
        for (Room room : this.listOfRooms) {
            deviceList = room.getDeviceList().getDeviceList();
            for (Device device : deviceList)
                if (device.getDeviceType().equals(deviceType)) {
                    deviceListByType.add(device);
                }
        }
        return deviceListByType;
    }

    public Room getDeviceLocation(Device inputDevice) {
        Room location = this.listOfRooms.get(0);
        for (Room room : this.listOfRooms) {
            List<Device> deviceListInRoom = room.getDeviceList().getDeviceList();
            if (deviceListInRoom.contains(inputDevice)) {
                location = room;
            }
        }
        return location;
    }


    public double getEnergyConsumptionByDeviceType(String deviceType) {
        DeviceSpecs deviceSpecs;
        double totalEnergyConsumption = 0;
        double deviceAnnualConsumption;
        double deviceDailyConsumption;

        for (Device device : getDevicesInAllRoomsByType(deviceType)) {
            deviceSpecs = device.getDeviceSpecs();
            deviceAnnualConsumption = deviceSpecs.getAttributeValue("Annual Energy Consumption");
            deviceDailyConsumption = deviceAnnualConsumption / 365;
            totalEnergyConsumption += deviceDailyConsumption;
        }
        return Utils.round(totalEnergyConsumption, 2);
    }

    public double getEstimatedEnergyConsumptionByDeviceType(String deviceType) {
        double totalEnergyConsumption = 0;
        for (Device device : getDevicesInAllRoomsByType(deviceType)) {
            Metered metered = (Metered) device;
            totalEnergyConsumption += metered.getEstimatedEnergyConsumption();
        }
        return Utils.round(totalEnergyConsumption, 2);
    }

    public List<Metered> getMeteredDevicesList() {
        List<Metered> meteredDevListInHouse = new ArrayList<>();
        for (Room room : this.listOfRooms) {
            List<Metered> meteredDevicesInRoom = room.getDeviceList().getMeteredDevices();
            meteredDevListInHouse.addAll(meteredDevicesInRoom);
        }
        return meteredDevListInHouse;
    }

    /**
     * @return a global list of sensors containing all sensors within each room.
     */
    public List<Sensor> getAllSensors() {
        List<Sensor> sensors = new ArrayList<>();
        for (Room room : this.listOfRooms) {
            SensorList roomSensorList = room.getSensorListInRoom();
            sensors.addAll(roomSensorList.getSensorList());

        }
        return sensors;
    }
}
