package smarthome.controller;

import smarthome.model.Device;
import smarthome.model.House;
import smarthome.model.RoomList;
import smarthome.model.Validations.DateValidations;

import java.util.Calendar;
import java.util.List;

public class EnergyConsumptionInPeriodCTRL {
    private House mHouse;
    private RoomList mRoomList;
    private DateValidations mDateValidations;

    public EnergyConsumptionInPeriodCTRL(House house) {
        mHouse = house;
        mRoomList = house.getRoomList();
        mDateValidations= new DateValidations();
    }

    public String showMeteredDevicesInStr() {
        return mRoomList.showMeteredDevicesInStr();
    }

    public int getMeteredDevicesInHouseSize() {
        return mRoomList.getMeteredDevicesInHouseSize();
    }

    public double getEnergyConsumptionInPeriod(int indexOfDevice, Calendar startHour, Calendar endHour) {
        List<Device> meteredDevices = mRoomList.getMeteredDevicesInHouse();
        Device selectedDevice = meteredDevices.get(indexOfDevice - 1);
        return selectedDevice.getEnergyConsumptionInPeriod(startHour, endHour);
    }

    /**
     * Method to check if year inputted by the user is considered a valid year
     * @param year inputted by the user
     * @return year if it is within [2010,2099] range, otherwise returns  to ask again for valid input
     */
    public boolean yearIsValid(String year){
        return mDateValidations.yearIsValid(year);
    }

    /**
     * Method to check if month inputted by the user is considered a valid month
     * @param month inputted by the user
     * @return month if it is a valid month, otherwise returns  to ask again for valid input
     */
    public boolean monthIsValid(String month){
        return mDateValidations.monthIsValid(month);
    }

    /**
     * Method that checks if day inputted by the user is a valid day considering previously inputted month and year
     * considering leap years.
     * @param day inputted by the user
     * @param inputMonth previously inputted by the user parsed from string to integer
     * @param inputYear previously inputted by the user parsed from string to integer
     * @return day if input meets regex criteria, otherwise returns
     */
    public boolean dayIsValid(String day, int inputMonth, int inputYear){
        return mDateValidations.dayIsValid(day, inputMonth,inputYear);
    }

    /**
     * Method that checks if hour inputted by the user is a valid
     * @param hour input hour
     * @return hour if input meets regex criteria, otherwise returns
     */
    public boolean hourIsValid(String hour){
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
