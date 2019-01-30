package smarthome.controller;

import smarthome.model.*;
import smarthome.model.Validations.DateValidations;
import java.util.Calendar;
import java.util.List;

public class EnergyConsumptionInPeriodCTRL {
    private House mHouse;
    private RoomList mRoomList;
    private HouseGridList mHouseGridList;
    private DateValidations mDateValidations;

    public EnergyConsumptionInPeriodCTRL(House house) {
        mHouse = house;
        mRoomList = house.getRoomList();
        mHouseGridList=house.getHGListInHouse();
        mDateValidations = new DateValidations();
    }


    /**
     * @return the houseGridList in a string format to use in the UI
     */
    public String showHouseGridListInString() {
        return mHouseGridList.showHouseGridListInString();
    }

    /**
     * @return the size of a houseGrid
     */
    public int getHouseGridListSize() {
        return mHouseGridList.getSize();
    }

    /**
     * Returns the String given to name the houseGrid in the index position of the houseGridList
     *
     * @param indexOfHG index of the houseGrid in the houseGridList
     * @return the houseGrid name
     */
    public String getHGName(int indexOfHG) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHG - 1);
        return houseGrid.getGridID();
    }

    /**
     * Method to display a list of all metered devices in string format
     * @return list of all metered devices in string format
     */
    public String showMeteredDevicesInStr() {
        return mRoomList.showMeteredDevicesInStr();
    }

    /**
     * @return size, as an integer value, of the list of devices that are metered
     */
    public int getMeteredDevicesInHouseSize() {
        return mRoomList.getMeteredDevicesInHouseSize();
    }

    /**
     * Method that gets the selected device name in String format
     * @param indexOfDevice device in the index position of the device list of metered devices
     * @return the device in the index position of the device list of metered devices
     */
    public String getDeviceName(int indexOfDevice) {
        List<Device> meteredDeviceList = mRoomList.getMeteredDevicesInHouse();
        Device device= meteredDeviceList.get(indexOfDevice-1);
        return device.getName();
    }

    /**
     * Display a list of all rooms in string format
     * @return list of rooms in a string
     */
    public String showRoomListInStr() {
        return mRoomList.showRoomListInString ();
    }

    /**
     * The size of the room list to check if there are rooms in that list
     * @return size of the list of rooms
     */
    public int getRoomListSize() {
        return mRoomList.getRoomListSize ();
    }

    /**
     * Shows the name given to the Room
     * @param indexOfHG the index position of the RoomList
     * @return the room name (string)
     */
    public String getRoomName(int indexOfHG) {
        Room room = mRoomList.get(indexOfHG - 1);
        return room.getName ();
    }

    /**
     * Returns the energy consumption for the selected metered device in a specific time interval
     * @param indexOfDevice index position of the selected device
     * @param startDate starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endDate ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval
     */
    public double getEnergyConsumptionInPeriod(int indexOfDevice, Calendar startDate, Calendar endDate) {
        List<Device> meteredDevices = mRoomList.getMeteredDevicesInHouse();
        Device selectedDevice = meteredDevices.get(indexOfDevice - 1);
        return selectedDevice.getEnergyConsumptionInTimeInterval(startDate, endDate);
    }

    /**
     * Returns the energy consumption for the selected houseGrid in a specific time interval
     * @param indexOfHouseGrid index position of the selected houseGrid in the houseGridList
     * @param startDate starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endDate ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval
     */
    public double getHouseGridEnergyConsumptionInPeriod(int indexOfHouseGrid, Calendar startDate, Calendar endDate) {
        HouseGrid selectedHouseGrid = mHouseGridList.get(indexOfHouseGrid - 1);
        return selectedHouseGrid.getEnergyConsumptionInTimeInterval(startDate, endDate);
    }


    /**
     * Returns the energy consumption for the selected room in a specific time interval
     * @param indexOfRoom index position of the selected room in the RoomList
     * @param startHour starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endHour ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval
     */
    public double getRoomEnergyConsumptionInPeriod(int indexOfRoom, Calendar startHour, Calendar endHour) {
        List<Room> roomL = mRoomList.getRoomList ();
        Room room = roomL.get(indexOfRoom - 1);
        return room.getEnergyConsumptionInTimeInterval (startHour, endHour);
    }

    /**
     * Method to check if year inputted by the user is considered a valid year
     *
     * @param year inputted by the user
     * @return year if it is within [2010,2099] range, otherwise returns  to ask again for valid input
     */
    public boolean yearIsValid(String year) {
        return mDateValidations.yearIsValid(year);
    }

    /**
     * Method to check if month inputted by the user is considered a valid month
     *
     * @param month inputted by the user
     * @return month if it is a valid month, otherwise returns  to ask again for valid input
     */
    public boolean monthIsValid(String month) {
        return mDateValidations.monthIsValid(month);
    }

    /**
     * Method that checks if day inputted by the user is a valid day considering previously inputted month and year
     * considering leap years.
     *
     * @param day        inputted by the user
     * @param inputMonth previously inputted by the user parsed from string to integer
     * @param inputYear  previously inputted by the user parsed from string to integer
     * @return day if input meets regex criteria, otherwise returns
     */
    public boolean dayIsValid(String day, int inputMonth, int inputYear) {
        return mDateValidations.dayIsValid(day, inputMonth, inputYear);
    }

    /**
     * Method that checks if hour inputted by the user is a valid
     *
     * @param hour input hour
     * @return hour if input meets regex criteria, otherwise returns
     */
    public boolean hourIsValid(String hour) {
        return mDateValidations.hourIsValid(hour);
    }


    /**
     * Method that checks if minute inputted by the user is valid
     *
     * @param minute input hour
     * @return hour if input meets regex criteria, otherwise returns false
     */
    public boolean minuteIsValid(String minute) {
        return mDateValidations.minuteIsValid(minute);
    }




}
