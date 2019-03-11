package smarthome.controller;

import smarthome.model.*;

import java.util.Calendar;
import java.util.List;

public class GetEnergyConsumptionInPeriodCTRL {
    private House house;
    private RoomList roomList;
    private HouseGridList houseGridList;

    public GetEnergyConsumptionInPeriodCTRL(House house) {
        this.house=house;
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
     * @return the number of elements in the list of houseGrids as an integer value
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
        return houseGrid.getName();
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
     * Method that gets the selected metered object name in String format
     *
     * @param indexOfMetered device in the index position of the device list of metered devices
     * @return the device in the index position of the device list of metered devices
     */
    public String getMeteredName(int indexOfMetered) {
        List<Metered> meteredDeviceList = this.house.getMetered();
        Metered metered = meteredDeviceList.get(indexOfMetered);
        return metered.getName();
    }


    /**
     * @return lists all metered elements in a string format
     */
    public String showMetered() {return this.house.showMetered();}

    /**
     * @return an integer value representing the number of elements in the list of rooms
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
     * Returns the energy consumption for the selected metered object in a specific time interval
     *
     * @param indexOfMetered index position of the selected metered object
     * @param startDate     starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endDate       ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval for the selected metered object
     */
    public double getEnergyConsumptionInPeriod(int indexOfMetered, Calendar startDate, Calendar endDate) {
        List<Metered> meteredList = this.house.getMetered();
        Metered selectedMetered = meteredList.get(indexOfMetered);
        return selectedMetered.getEnergyConsumptionInTimeInterval(startDate, endDate);
    }

    /**
     *
     * @return an integer value representing the number of elements in the list of metered objects
     */
    public int meteredListSize(){
        return this.house.getMetered().size();
    }

}
