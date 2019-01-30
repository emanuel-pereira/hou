package smarthome.controller;

import smarthome.model.House;
import smarthome.model.HouseGrid;

public class NewHouseGridCTRL {

    private House mHouse;

    public NewHouseGridCTRL(House house) {
        mHouse = house;
    }

    /**
     * Method to get the house grid size
     *
     * @return list of objects of HouseGrid type
     */
    public int getHouseGridListSize() {
        return mHouse.getHGListInHouse().getSize();
    }

    /**
     * Method to return the list of house grids in String format
     *
     * @return String Result List
     */
    public String showGridsListInStringString() {
        return mHouse.getHGListInHouse().showHouseGridListInString();
    }

    /**
     * Method that invokes the house grid list method that creates new house grids
     * After being created the grid object is added to the HG list in the house
     *
     * @param id String object that represents the name of the new electric grid
     * @return boolean true if new electric grid is successfully added,
     * false if not
     */
    public boolean createNewHouseGrid(String id) {
        HouseGrid newHouseGrid = mHouse.getHGListInHouse().newHouseGrid(id);
        return mHouse.getHGListInHouse().addHouseGrid(newHouseGrid);
    }
}
