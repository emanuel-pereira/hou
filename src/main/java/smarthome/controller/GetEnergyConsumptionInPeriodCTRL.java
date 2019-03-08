package smarthome.controller;

import smarthome.model.*;

import java.util.Calendar;
import java.util.List;

public class GetEnergyConsumptionInPeriodCTRL {
    private RoomList roomList;
    private HouseGridList houseGridList;

    public GetEnergyConsumptionInPeriodCTRL(House house) {
        this.roomList = house.getRoomList();
        this.houseGridList = house.getHGListInHouse();
    }


    /**
     * @return the houseGridList in a string format to use in the UI
     */
    public String showHouseGridListInString() {
        return this.houseGridList.showHouseGridListInString();
    }

    /**
     * @return the size of a houseGrid
     */
    public int getHouseGridListSize() {
        return this.houseGridList.getSize();
    }

    /**
     * Returns the String given to name the houseGrid in the index position of the houseGridList
     *
     * @param indexOfHG index of the houseGrid in the houseGridList
     * @return the houseGrid name
     */
    public String getHGName(int indexOfHG) {
        HouseGrid houseGrid = this.houseGridList.get(indexOfHG);
        return houseGrid.getGridID();
    }

    /**
     * Method to display a list of all metered devices in string format
     *
     * @return list of all metered devices in string format
     */
    public String showMeteredDevicesInStr() {
        return this.roomList.showMeteredDevicesInStr();
    }

    /**
     * @return size, as an integer value, of the list of devices that are metered
     */
    public int getMeteredDevicesInHouseSize() {
        return this.roomList.getMeteredDevicesInHouseSize();
    }

    /**
     * Method that gets the selected device name in String format
     *
     * @param indexOfDevice device in the index position of the device list of metered devices
     * @return the device in the index position of the device list of metered devices
     */
    public String getDeviceName(int indexOfDevice) {
        List<Device> meteredDeviceList = this.roomList.getMeteredDevicesInHouse();
        Device device = meteredDeviceList.get(indexOfDevice);
        return device.getDeviceName();
    }

    /**
     * Display a list of all rooms in string format
     *
     * @return list of rooms in a string
     */
    public String showRoomListInStr() {
        return this.roomList.showRoomListInString();
    }

    /**
     * The size of the room list to check if there are rooms in that list
     *
     * @return size of the list of rooms
     */
    public int getRoomListSize() {
        return this.roomList.getRoomListSize();
    }

    /**
     * Shows the name given to the Room
     *
     * @param indexOfRoom the index position of the RoomList
     * @return the room name (string)
     */
    public String getRoomName(int indexOfRoom) {
        Room room = this.roomList.get(indexOfRoom);
        return room.getName();
    }

    /**
     * Returns the energy consumption for the selected metered device in a specific time interval
     *
     * @param indexOfDevice index position of the selected device
     * @param startDate     starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endDate       ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval
     */
    public double getEnergyConsumptionInPeriod(int indexOfDevice, Calendar startDate, Calendar endDate) {
        List<Device> meteredDevices = this.roomList.getMeteredDevicesInHouse();
        Device selectedDevice = meteredDevices.get(indexOfDevice);
        return selectedDevice.getEnergyConsumption(startDate, endDate);
    }

    /**
     * Returns the energy consumption for the selected houseGrid in a specific time interval
     *
     * @param indexOfHouseGrid index position of the selected houseGrid in the houseGridList
     * @param startDate        starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endDate          ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval
     */
    public double getHouseGridEnergyConsumptionInPeriod(int indexOfHouseGrid, Calendar startDate, Calendar endDate) {
        HouseGrid selectedHouseGrid = this.houseGridList.get(indexOfHouseGrid);
        return selectedHouseGrid.getEnergyConsumption(startDate, endDate);
    }


    /**
     * Returns the energy consumption for the selected room in a specific time interval
     *
     * @param indexOfRoom index position of the selected room in the RoomList
     * @param startHour   starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endHour     ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval
     */
    public double getRoomEnergyConsumptionInPeriod(int indexOfRoom, Calendar startHour, Calendar endHour) {
        Room room = this.roomList.get(indexOfRoom);
        return room.getEnergyConsumption(startHour, endHour);
    }

}
