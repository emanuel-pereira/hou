package smarthome.controller;

import smarthome.model.House;
import smarthome.model.HouseGrid;
import smarthome.model.HouseGridList;
import smarthome.model.Room;

public class TotalNominalPowerInGridCTRL {

    private House mHouse;
    private HouseGridList mHouseGridList;
    private HouseGrid mGrid;
    private Room mRoom;

    public TotalNominalPowerInGridCTRL(House house) {
        mHouse = house;
        mHouseGridList = mHouse.getHGListInHouse ();
    }


    /**
     * Get the house grid list size to check if there is any grid
     *
     * @return house grid list size
     */
    public int getHouseGridListSize() {
        return mHouse.getHGListInHouse ().getSize ();
    }


    /**
     * Get the room list size to check if there is any room
     *
     * @return room list size
     */
    public int getRoomListSize() {
        return mHouse.getRoomList ().getRoomListSize ();
    }


    /**
     * Show house grid list in string
     *
     * @return list in string
     */
    public String showHouseGridListInString() {
        return mHouseGridList.showHouseGridListInString ();
    }


    /**
     * Check if there are rooms attached to a chosen grid
     *
     * @param indexGrid Position of the chosen option
     * @return size of the list of rooms in a grid
     */
    public int getRoomListInAGridSize(int indexGrid) {
        int size = 0;
        for (HouseGrid houseGrid : mHouseGridList.getHouseGridList ()) {
            if (mHouseGridList.get (indexGrid - 1).getRoomListInAGridSize () != 0) {
                size = houseGrid.getRoomListInAGridSize ();
            }
        }
        return size;
    }


    /**
     * Check if the sum of all the device list in the rooms of the grid are all empty
     *
     * @param indexGrid Position of the chosen option
     * @return
     */
    public boolean deviceListSizeInGridIsNotEmpty(int indexGrid) {
        int sum = 0;
        for (Room r : mHouseGridList.get (indexGrid).getRoomListInAGrid ().getRoomList ()) {
            sum += r.getDeviceList ().size ();
        }
        return sum > 0;
    }


    public double getTotalNominalPowerInGrid(int indexGrid) {
        return mHouseGridList.get (indexGrid - 1).getNominalPower ();
    }

}