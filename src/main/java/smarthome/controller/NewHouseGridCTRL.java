package smarthome.controller;


import smarthome.model.HouseGrid;

import static smarthome.model.House.getGridListInHouse;

public class NewHouseGridCTRL {

    public NewHouseGridCTRL() {
        //Controller constructor, empty on purpose
    }

    /**
     * Method to get the house grid size
     *
     * @return list of objects of HouseGrid type
     */
    public int getHouseGridListSize() {
        return getGridListInHouse().getSize();
    }

    /**
     * Method to return the list of house grids in String format
     *
     * @return String Result List
     */
    public String showGridsListInString() {
        return getGridListInHouse().showHouseGridListInString();
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
        HouseGrid newHouseGrid = getGridListInHouse().newHouseGrid(id);
        return getGridListInHouse().addHouseGrid(newHouseGrid);
    }
}
