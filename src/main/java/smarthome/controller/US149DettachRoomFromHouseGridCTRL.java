package smarthome.controller;

import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;

public class US149DettachRoomFromHouseGridCTRL {
    private House mHouse;

    public US149DettachRoomFromHouseGridCTRL(House house) {
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
            result.append(houseGrid.getmContractedMaximumPower());
            result.append("\n");
        }
        return result.toString();
    }

    public List<HouseGrid> getHouseGridList() {
        return mHouse.getHouseGridList();
    }


    /**
     * Method to detach room from HouseGrid
     *
     * @param indexOfHouseGrid
     * @param indexOfRoom      room in the index position selected by the user
     * @return true if room in index position of RoomList has its HouseGrid set as null, false otherwise.
     */
    public boolean detachRoomFromHouseGrid(int indexOfHouseGrid, int indexOfRoom) {
        List<Room> listOfRoomsWithHouseGrid = getListOfRoomsWithHouseGrid(indexOfHouseGrid);
        if(listOfRoomsWithHouseGrid.size()!=0){
        Room r = listOfRoomsWithHouseGrid.get(indexOfRoom-1);
        r.setmHouseGrid(mHouse.getHouseGridList().get(indexOfHouseGrid-1));
        return true;}
        else return false;}

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
}
