package smarthome.controller;

import smarthome.model.*;

public class US172TotalNominalPowerInGridCTRL {

    private House mHouse;
    private RoomList mRoomList;
    private HouseGridList mHouseGridList;

    public US172TotalNominalPowerInGridCTRL(House house) {
        mHouse = house;
        mRoomList = mHouse.getRoomListFromHouse ();
        mHouseGridList = mHouse.getHGListInHouse ();
    }

    /**
     * Get the house grid list to check if there is any grid
     * @return house grid list
     */
    public HouseGridList getHouseGridList(){
        return mHouse.getHGListInHouse ();
    }

    /**
     * Get the room list to check if there is any room
     * @return room list
     */
    public RoomList getRoomList(){
        return mHouse.getRoomListFromHouse ();
    }

    /**
     * Show house grid list in string
     * @return list in string
     */
    public String showHouseGridListInString(){
        return mHouseGridList.showHouseGridListInString ();
    }

    /**
     * Check if there are rooms attached to a chosen grid
     * @param indexOfHouseGrid Position of the chosen option
     * @return list of rooms in a grid
     */
    public RoomList getListOfRoomsWithThisGrid(int indexOfHouseGrid) {
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
     * Get the device list of a chosen room to check if there are devices
     * @param indexRoom Position of the chosen option
     * @return list of devices in a room
     */
    public DeviceList getDeviceList(int indexRoom){
        return mHouse.getRoomListFromHouse().getRoomWithIndex(indexRoom-1).getDeviceList ();
    }

    /**
     * Show the total nominal power of a chosen grid
     * @param indexGrid Position of the chosen grid
     * @return total nominal power in a grid (sum rooms, sum devices in room)
     */
    public double getTotalNominalPowerInGrid(int indexGrid){
        HouseGrid hg = mHouse.getHGListInHouse ().get (indexGrid-1);
        return mHouse.getRoomListFromHouse ().getNominalPower (hg);
    }

}
