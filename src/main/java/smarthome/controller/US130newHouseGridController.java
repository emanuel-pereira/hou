package smarthome.controller;

import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

public class US130newHouseGridController {

    private House mHouse;

    public US130newHouseGridController(House house) {
        mHouse = house;
    }

    /**
     * Method to create invoke the new House grid model method
     *
     * @param inputMaxPower double max power
     * @param house         house class
     * @return boolean true or false if the new House Grid was added or not respectively
     */
    public boolean createNewHouseGrid(double inputMaxPower, House house, String ID) {
        HouseGrid housegrid = house.newHouseGrid(inputMaxPower, ID);
        return house.addHouseGrid(housegrid);
    }

    /**
     * method to return the list of house grids
     *
     * @return list of objects of HouseGrid type
     */
    public List<HouseGrid> getHouseGridList() {
        return mHouse.getHouseGridList();
    }

    /**
     * method to return the list of house grids in String format
     *
     * @return String Result List
     */
    public String showGridsListInString() {
        List<HouseGrid> gridList = getHouseGridList();
        StringBuilder result = new StringBuilder();
        int number = 1;
        for (HouseGrid HG : gridList) {
            result.append(number++);
            result.append(" - ");
            result.append(HG.getGridID());
            result.append("\n");
        }
        return result.toString();
    }

}