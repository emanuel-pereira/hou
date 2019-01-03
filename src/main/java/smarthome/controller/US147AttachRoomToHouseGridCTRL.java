package smarthome.controller;

import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;

public class US147AttachRoomToHouseGridCTRL {
    private House mHouse;

    public US147AttachRoomToHouseGridCTRL(House house) {
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
     * @return the list of rooms without HouseGrid
     */
    public List<Room> getListOfRoomsWithoutHouseGrid() {
        List<Room> listOfRoomsWithoutHouseGrid = new ArrayList<>();
        for (Room r : mHouse.getRoomList()) {
            if (r.getmHouseGrid() == null) {
                listOfRoomsWithoutHouseGrid.add(r);
            }
        }
        return listOfRoomsWithoutHouseGrid;
    }

    /**
     * @return shows the list of rooms without houseGrid in a single string
     */
    public String listOfRoomsWithoutHouseGridInString() {
        List<Room> listOfRoomsWithoutHouseGrid = getListOfRoomsWithoutHouseGrid();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsWithoutHouseGrid) {
            result.append(number++);
            result.append(element);
            result.append(r.getName());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Method to attach a room to a HouseGrid.
     *
     * @param indexOfHouseGrid HouseGrid in the index position selected by the user
     * @param indexOfRoom      room in index position of list of rooms with HpuseGrid as null
     * @return true if houseGrid previously selected by the user is set as room's HouseGrid
     */
    public boolean attachRoomToHouseGrid(int indexOfHouseGrid, int indexOfRoom) {
        List<Room> listOfRoomsWithoutHouseGrid = getListOfRoomsWithoutHouseGrid();
        if (listOfRoomsWithoutHouseGrid.size() != 0) {
            Room r = listOfRoomsWithoutHouseGrid.get(indexOfRoom);
            r.setmHouseGrid(mHouse.getHouseGridList().get(indexOfHouseGrid - 1));
            return true;
        } else return false;
    }
}
