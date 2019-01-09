package smarthome.controller;

import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.Room;
import smarthome.model.RoomList;

import java.util.ArrayList;
import java.util.List;

public class USAttachRoomToGridAndListCTRL {
    private House mHouse;
    private RoomList mRoomList;

    public USAttachRoomToGridAndListCTRL(House house) {
        mHouse = house;
        mRoomList = mHouse.getRoomListFromHouse();
    }


    /**
     * @return shows the list of houseGrids in a single string for the user to select a HouseGrid
     */
    public String showHouseGridListInString() {
        List<HouseGrid> list = mHouse.getHouseGridList();
        StringBuilder result = new StringBuilder();
        int number = 1;
        for (HouseGrid houseGrid : list) {
            result.append(number++);
            result.append(" - ");
            result.append(houseGrid.getGridID());
            result.append(" | Nominal Power: ");
            result.append(houseGrid.getContractedMaximumPower());
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
        for (Room r : mRoomList.getRoomList()) {
            if (r.getmHouseGrid() == null) {
                listOfRoomsWithoutHouseGrid.add(r);
            }
        }
        return listOfRoomsWithoutHouseGrid;
    }

    /**
     * @return the list of rooms without HouseGrid
     */
    public List<Room> getListOfRooms() {
        return mRoomList.getRoomList();
    }

    /**
     * @return shows the list of rooms without houseGrid in a single string
     */
    public String showRoomsWithoutHouseGridInStr() {
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
            Room r = listOfRoomsWithoutHouseGrid.get(indexOfRoom - 1);
            r.setmHouseGrid(mHouse.getHouseGridList().get(indexOfHouseGrid - 1));
            return true;
        } else return false;
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
        for (Room r : mRoomList.getRoomList()) {
            if (r.getmHouseGrid() != null) {
                if (r.getmHouseGrid().equals(mHouse.getHouseGridList().get(indexOfHouseGrid - 1))) {
                    listOfRoomsWithHouseGrid.add(r);
                }
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
    public String showRoomsWithHouseGridInStr(int indexOfHouseGrid) {
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

    /**
     * Method to detach room from HouseGrid
     *
     * @param indexOfHouseGrid
     * @param indexOfRoom      room in the index position selected by the user
     * @return true if room in index position of RoomList has its HouseGrid set as null, false otherwise.
     */
    public boolean detachRoomFromHouseGrid(int indexOfHouseGrid, int indexOfRoom) {
        List<Room> listOfRoomsWithHouseGrid = getListOfRoomsWithHouseGrid(indexOfHouseGrid);
        if (listOfRoomsWithHouseGrid.size() != 0) {
            Room r = listOfRoomsWithHouseGrid.get(indexOfRoom - 1);
            r.setmHouseGrid(mHouse.getHouseGridList().get(indexOfHouseGrid - 1));
            return true;
        } else return false;
    }

}
