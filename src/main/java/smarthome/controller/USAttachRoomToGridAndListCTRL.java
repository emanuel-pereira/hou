package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class USAttachRoomToGridAndListCTRL {
    private House mHouse;

    public USAttachRoomToGridAndListCTRL(House house) {
        mHouse = house;
    }


    /**
     * @return shows the list of houseGrids in a single string for the user to select a HouseGrid
     */
    public String showHouseGridListInString() {
        HouseGridList hgList = mHouse.getHGListInHouse();
        StringBuilder result = new StringBuilder();
        int number = 1;
        for (HouseGrid houseGrid : hgList.getHouseGridList()) {
            result.append(number++);
            result.append(" - ");
            result.append(houseGrid.getGridID());
            result.append(" | Nominal Power: ");
            result.append(houseGrid.getContractedMaximumPower());
            result.append("\n");
        }
        return result.toString();
    }

    public HouseGridList getHouseGridList() {
        return mHouse.getHGListInHouse();
    }

    /**
     * @return the list of rooms without HouseGrid
     */
    public RoomList getListOfRoomsWithoutHouseGrid() {
        RoomList listOfRoomsWithoutHouseGrid = new RoomList();
        for (Room r : mHouse.getRoomListFromHouse().getRoomList()) {
            if (r.getmHouseGrid() == null) {
                listOfRoomsWithoutHouseGrid.addRoom(r);
            }
        }
        return listOfRoomsWithoutHouseGrid;
    }

    /**
     * @return the list of rooms without HouseGrid
     */
    public RoomList getListOfRooms() {
        return mHouse.getRoomListFromHouse();
    }

    /**
     * @return shows the list of rooms without houseGrid in a single string
     */
    public String showRoomsWithoutHouseGridInStr() {
        RoomList listOfRoomsWithoutHouseGrid = getListOfRoomsWithoutHouseGrid();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsWithoutHouseGrid.getRoomList()) {
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
        RoomList listOfRoomsWithoutHouseGrid = getListOfRoomsWithoutHouseGrid();
        if (listOfRoomsWithoutHouseGrid.getRoomList().size() != 0) {
            Room r = listOfRoomsWithoutHouseGrid.get(indexOfRoom - 1);
            r.setmHouseGrid(mHouse.getHGListInHouse().get(indexOfHouseGrid - 1));
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
    public RoomList getListOfRoomsWithHouseGrid(int indexOfHouseGrid) {
        RoomList listOfRoomsWithHouseGrid = new RoomList();
        for (Room r : mHouse.getRoomListFromHouse().getRoomList()) {
            if (r.getmHouseGrid() != null) {
                if (r.getmHouseGrid().equals(mHouse.getHGListInHouse().get(indexOfHouseGrid - 1))) {
                    listOfRoomsWithHouseGrid.addRoom(r);
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
        RoomList listOfRoomsWithHouseGrid = getListOfRoomsWithHouseGrid(indexOfHouseGrid);
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsWithHouseGrid.getRoomList()) {
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
        RoomList listOfRoomsWithHouseGrid = getListOfRoomsWithHouseGrid(indexOfHouseGrid);
        if(listOfRoomsWithHouseGrid.getRoomList().size()!=0){

            Room r = listOfRoomsWithHouseGrid.get(indexOfRoom-1);
            r.setmHouseGrid(null);
            return true;}
        else return false;}

}
