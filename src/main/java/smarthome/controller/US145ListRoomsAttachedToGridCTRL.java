package smarthome.controller;

import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;

public class US145ListRoomsAttachedToGridCTRL {
    private House mHouse;

    public US145ListRoomsAttachedToGridCTRL(House house) {
        mHouse = house;
    }

    /**
     * @return shows the list of houseGrids in a single string for the user to select a HouseGrid
     */
    public String showHouseGridListInString() {
        List<HouseGrid> list = mHouse.getHouseGridList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (HouseGrid houseGrid : list) {
            result.append(number++);
            result.append(element);
            result.append("Nominal Power: ");
            result.append(houseGrid.getContractedMaximumPower());
            result.append("\n");
        }
        return result.toString();
    }
    public List<HouseGrid> getHouseGridList(){
        return mHouse.getHouseGridList();
    }

    /**
     * Method to get a list of rooms with the house grid in the index position of the HouseGridList
     * and shows the list of rooms with that grid.
     *
     * @param indexOfHouseGrid index position of the house grid in the list of house grids of the house instance
     * @return the list of rooms with the house grid chosen by the user
     */
    public List<Room> getListOfRoomsWithHouseGrid(int indexOfHouseGrid) {
        List<Room> listOfRoomsWithHouseGrid = new ArrayList<>();
        for (Room r : mHouse.getRoomList()) {
            if (r.getmHouseGrid().equals(mHouse.getHouseGridList().get(indexOfHouseGrid - 1))) {
                listOfRoomsWithHouseGrid.add(r);
            }
        }
        return listOfRoomsWithHouseGrid;
    }

    /**
     * Method to show a list of rooms with the house grid in the index position of the HouseGridList
     * and shows the list of rooms with that grid.
     *
     * @param indexOfHouseGrid
     * @return
     */
    public String showListOfRoomsWithHouseGridInString(int indexOfHouseGrid) {
        List<Room> listOfRoomsWithHouseGrid = getListOfRoomsWithHouseGrid(indexOfHouseGrid);
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsWithHouseGrid) {
            result.append(number++);
            result.append(element);
            result.append(r.getName());
            result.append("\n");
        }
        return result.toString();
    }
}
