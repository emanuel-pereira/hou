package smarthome.controller.cli;

import smarthome.model.HouseGrid;
import smarthome.model.HouseGridList;
import smarthome.model.Room;
import smarthome.model.RoomList;

import static smarthome.model.House.getGridListInHouse;
import static smarthome.model.House.getHouseRoomList;

public class GetTotalNominalPowerCTRL {

    private final HouseGridList houseGridList;
    private final RoomList roomList;

    /**
     * Controller constructor
     *
     *
     */
    public GetTotalNominalPowerCTRL() {
        this.houseGridList = getGridListInHouse();
        this.roomList = getHouseRoomList();
    }

    /**
     * The size of the grid list to check if there are grids in that list
     *
     * @return the size of the houseGrid list
     */
    public int getGridListSize() {
        return this.houseGridList.getSize();
    }

    /**
     * The size of the room list to check if there are rooms in that list
     *
     * @return size of the list of rooms
     */
    public int getRoomListSize() {
        return this.roomList.getRoomListSize();
    }

    /**
     * Display a list of all grids in string format
     *
     * @return list of grids in a string
     */
    public String showGridListInString() {
        return this.houseGridList.showHouseGridListInString();
    }

    /**
     * Display a list of all rooms in string format
     *
     * @return list of rooms in a string
     */
    public String showRoomListInString() {
        return this.roomList.showRoomListInString();
    }

    /**
     * Shows the the grid total nominal power
     *
     * @param indexGrid the position on the grid in the list
     * @return total nominal power of the grid
     */
    public double getGridTotalNominalPower(int indexGrid) {
        return this.houseGridList.get(indexGrid).getNominalPower();
    }

    /**
     * Shows the room total nominal power
     *
     * @param indexRoom the position on the room in the list
     * @return total nominal power of the room
     */
    public double getRoomTotalNominalPower(int indexRoom) {
        return this.roomList.get(indexRoom).getNominalPower();
    }

    /**
     * Check if there are rooms attached to a chosen grid
     *
     * @param indexGrid Position of the chosen option
     * @return size of the list of rooms in a grid
     */
    public int getSizeRoomListInGrid(int indexGrid) {
        HouseGrid hg = this.houseGridList.get(indexGrid);
        int hgSize = hg.getRoomListInAGridSize();
        if (hgSize != 0) {
            return hgSize;
        }
        return 0;
    }

    /**
     * Check if the sum of all the devices of the rooms of the grid is zero
     *
     * @param indexGrid Position of the chosen option
     * @return size of the list of devices in a grid
     */
    public int getSizeDeviceListInGrid(int indexGrid) {
        int sum = 0;
        for (Room r : this.houseGridList.get(indexGrid).getRoomList().getRoomList()) {
            sum += r.getSizeDeviceListInRoom();
        }
        return sum;
    }

    /**
     * Check if there are devices added to a chosen room
     *
     * @param indexRoom Position of the chosen option
     * @return size of the list of devices in a room
     */
    public int getSizeDeviceListInRoom(int indexRoom) {
        return this.roomList.get(indexRoom).getDeviceList().size();
    }

}
