
package smarthome.controller;

import smarthome.model.*;

public class AttachDetachAndListRoomsInGridCTRL {
    private House mHouse;
    private HouseGridList mHouseGridList;

    public AttachDetachAndListRoomsInGridCTRL(House house) {
        mHouse = house;
        mHouseGridList = house.getHGListInHouse();
    }

    /**
     * Returns the String given to name the room in the index position of the roomList attached to a houseGrid in the index position of the houseGridList.
     *
     * @param indexOfHG   index of the houseGrid in the houseGridList
     * @param indexOfRoom index of the room in the roomList
     * @return the room name
     */
    public String getRoomOfHGName(int indexOfHG, int indexOfRoom) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHG - 1);
        Room room = houseGrid.getRoomListInAGrid().get(indexOfRoom - 1);
        return room.getName();

    }

    /**
     * Returns the String given to name the houseGrid in the index position of the houseGridList
     *
     * @param indexOfHG index of the houseGrid in the houseGridList
     * @return the houseGrid name
     */
    public String getHGName(int indexOfHG) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHG - 1);
        return houseGrid.getGridID();
    }

    /**
     * @return the size of the house's roomList
     */
    public int getListOfRoomsSize() {
        return mHouse.getRoomList().getRoomListSize();
    }


    /**
     * Method to get the size of the roomList belonging to the houseGrid in the index position of the houseGridList
     *
     * @param indexOfHouseGrid index of the houseGrid selected to get the size of the respective roomList
     * @return the size of the roomList in the chosen houseGrid
     */
    public int getRoomListOfHGSize(int indexOfHouseGrid) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHouseGrid - 1);
        return houseGrid.getRoomListInAGridSize();
    }


    /**
     * Method to get the name of the lastRoom in the houseGrid
     *
     * @param indexOfHouseGrid index position of selected houseGrid
     * @return name of the room in the last position of the roomList belonging to a houseGrid
     */
    public String getNameOfLastRoomInHG(int indexOfHouseGrid) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHouseGrid - 1);
        RoomList roomListInGH = houseGrid.getRoomListInAGrid();
        int sizeOfRoomList = getRoomListOfHGSize(indexOfHouseGrid);
        Room lastRoom = roomListInGH.get(sizeOfRoomList - 1);
        return lastRoom.getName();
    }


    /**
     * @return the houseGridList in a string format to use in the UI
     */
    public String showHouseGridListInString() {
        return mHouseGridList.showHouseGridListInString();
    }

    /**
     * @return the size of a houseGrid
     */
    public int getHouseGridListSize() {
        return mHouseGridList.getSize();
    }

    /**
     * @param indexOfHouseGrid index position of selected houseGrid in HouseGridList
     * @return list of rooms not contained in the specified houseGrid
     */
    private RoomList getRoomsWithoutGrid(int indexOfHouseGrid) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHouseGrid - 1);
        return mHouse.getRoomsWithoutGrid(houseGrid);
    }

    /**
     * @param indexOfHouseGrid index position of selected houseGrid in HouseGridList
     * @return the size of the list of rooms not contained in the specified houseGrid
     */
    public int getRoomsWithoutGridSize(int indexOfHouseGrid){
        RoomList roomList=getRoomsWithoutGrid(indexOfHouseGrid);
        return roomList.getRoomListSize();
    }

    /**
     * @param indexOfHouseGrid index position of selected houseGrid in HouseGridList
     * @return a string containing all rooms not contained in the selected houseGrid
     */
    public String showRoomsWithoutHouseGrid(int indexOfHouseGrid) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHouseGrid - 1);
        return mHouse.showRoomsWithoutHouseGrid(houseGrid);
    }

    /**
     * Method to attach a room to a selected houseGrid, which will add that room to the RoomList of the chosen HouseGrid.
     * @param indexOfHouseGrid index position of selected houseGrid in HouseGridList
     * @param indexOfRoom index position of selected room in the RoomList of the HouseGrid
     * @return true if room is attached to a houseGrid or false otherwise
     */
    public boolean attachRoomToHouseGrid(int indexOfHouseGrid, int indexOfRoom) {
        HouseGrid selectedHG = mHouseGridList.get(indexOfHouseGrid - 1);
        RoomList roomListWithoutGrid = getRoomsWithoutGrid(indexOfHouseGrid);
        Room selectedRoom = roomListWithoutGrid.get(indexOfRoom - 1);
        return selectedHG.attachRoomToGrid(selectedRoom);
    }

    /**
     *
     * @param indexOfHouseGrid index position of selected houseGrid in HouseGridList
     * @return a string containing all rooms attached to the selected houseGrid
     */
    public String showRoomsInHouseGrid(int indexOfHouseGrid) {
        HouseGrid selectedHG = mHouseGridList.get(indexOfHouseGrid - 1);
        return selectedHG.showRoomsInHouseGrid();
    }


    /**
     * SubMethod to get the list of rooms attached to a grid which is invoked in the detachRoomFromGrid method
     *
     * @param indexOfHouseGrid index position of selected houseGrid in the houseGridList
     * @return the list of rooms attached to the specified houseGrid.
     */
    private RoomList getListOfRoomsInGrid(int indexOfHouseGrid) {
        HouseGrid houseGrid = mHouseGridList.get(indexOfHouseGrid - 1);
        return houseGrid.getRoomListInAGrid();
    }

    /**
     * Method to detach a room to from the selected houseGrid, which will remove that room from the RoomList of the chosen HouseGrid.
     * @param indexOfHouseGrid index position of selected houseGrid in HouseGridList
     * @param indexOfRoom index position of selected room in the RoomList of the HouseGrid
     * @return true if room is dettached from a houseGrid or false otherwise
     */
    public boolean detachRoomFromGrid(int indexOfHouseGrid, int indexOfRoom) {
        HouseGrid selectedHG = mHouseGridList.get(indexOfHouseGrid - 1);
        RoomList roomListWithoutGrid = getListOfRoomsInGrid(indexOfHouseGrid);
        Room selectedRoom = roomListWithoutGrid.get(indexOfRoom - 1);
        return selectedHG.detachRoomFromGrid(selectedRoom);
    }
}