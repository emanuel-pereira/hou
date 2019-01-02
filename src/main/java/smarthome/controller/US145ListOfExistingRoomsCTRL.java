package smarthome.controller;

import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;

public class US145ListOfExistingRoomsCTRL {
    private House mHouse;
    private Room mRoom;

    public US145ListOfExistingRoomsCTRL(House inputHouse) {
        mHouse = inputHouse;
    }


    /**
     * @return shows the list of houseGrids in a single string
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

    /* /**
     *
     * @param indexOfHouseGrid
     * @return
     *//*
    public String showListOfRoomsWithHouseGridInString(int indexOfHouseGrid){
    List<Room> listOfRoomsWithHouseGrid = getListRoomsWithHouseGrid(indexOfHouseGrid);
    StringBuilder result=new StringBuilder();
    String element = " - ";
    int number=1;
    for(Room r:listOfRoomsWithHouseGrid){
    result.append(number++);
    result.append(element);
    result.append(r.getName());
    result.append("\n");}
    return result.toString();
}

public boolean removeRoom (int indexOfHouseGrid, int indexOfRoom){
    List<Room> listOfRoomsWithHouseGrid = getListRoomsWithHouseGrid(indexOfHouseGrid);
    return mHouse.removeRoom(mHouse.getRoomList().get(indexOfRoom-1));
    }

    public boolean attachRoom (int indexOfHouseGrid, int indexOfRoom){
        List<Room> listOfRoomsWithHouseGrid = getListRoomsWithHouseGrid(indexOfHouseGrid);
        return mHouse.addRoom(mHouse.getRoomList().get(indexOfRoom-1));
    }*/

    /**
     * Method to get a list of rooms with the house grid in the index position of the HouseGridList
     * and shows the list of rooms with that grid.
     *
     * @param indexOfHouseGrid index position of the house grid in the list of house grids of the house instance
     * @return the list of rooms with the house grid chosen by the user
     */
    public List<Room> getListRoomsWithHouseGrid(int indexOfHouseGrid) {
        //  HouseGrid grid = mHouse.getHouseGridList().get(indexOfHouseGrid - 1);
        List<Room> listOfRoomsWithHouseGrid = new ArrayList<>();
        for (Room r : mHouse.getRoomList()) {
            if (r.getmHouseGrid().equals(mHouse.getHouseGridList().get(indexOfHouseGrid - 1))) {
                listOfRoomsWithHouseGrid.add(r);
            }
        }
        return listOfRoomsWithHouseGrid;
    }

}
