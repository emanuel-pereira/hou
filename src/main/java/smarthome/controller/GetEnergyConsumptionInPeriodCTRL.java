package smarthome.controller;

import smarthome.model.House;
import smarthome.model.Metered;

import java.util.Calendar;
import java.util.List;

public class GetEnergyConsumptionInPeriodCTRL {
    private House house;


    public GetEnergyConsumptionInPeriodCTRL(House house) {
        this.house = house;
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
        return metered.getMeteredDesignation();
    }

    /**
     * @return lists all metered elements in a string format
     */
    public String showMetered() {
        return this.house.showMetered();
    }


    /**
     * Returns the energy consumption for the selected metered object in a specific time interval
     *
     * @param indexOfMetered index position of the selected metered object
     * @param startDate      starting period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @param endDate        ending period in calendar format(yyyy-MM-dd HH:mm) to consider the energy consumption calculation
     * @return the energy consumed in the specified time interval for the selected metered object
     */
    public double getEnergyConsumptionInPeriod(int indexOfMetered, Calendar startDate, Calendar endDate) {
        List<Metered> meteredList = this.house.getMetered();
        Metered selectedMetered = meteredList.get(indexOfMetered);
        return selectedMetered.getEnergyConsumption(startDate, endDate);
    }

    /**
     * @return an integer value representing the number of elements in the list of metered objects
     */
    public int meteredListSize() {
        return this.house.getMetered().size();
    }

}
