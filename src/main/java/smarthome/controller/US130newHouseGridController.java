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
     * @param inputMaxPower double max power
     * @param house house class
     * @return boolean true or false if the new House Grid was added or not respectively
     */
    public boolean createNewHouseGrid(double inputMaxPower, House house) {
        HouseGrid housegrid = house.newHouseGrid(inputMaxPower);
        return house.addHouseGrid(housegrid);
    }

    /**
     * method to return the list of house grids in String format
     * @return String Result List
     */
    /*public String getHouseGridListString() {
        StringBuilder houseGridList = new StringBuilder();
        for (int i = 0; i < mHouse.getHouseGridList().size(); i++) {
            houseGridList.append(mHouse.getHouseGridList().get(i).getGridID());
            houseGridList.append(";");
        }
        return houseGridList.toString();
    }*/
    /**
     * method to return the list of house grids
     * @return list of objects of HouseGrid type
     */
    public List<HouseGrid> getHouseGridList() {
        return mHouse.getHouseGridList();
    }
}