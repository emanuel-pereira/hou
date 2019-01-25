package smarthome.model;

import java.util.Objects;

public class HouseGrid {
    private double mContractedMaximumPower = Double.NaN;
    private String mDesignation;
    private PowerSourceList mPSListInHG;
    private RoomList mRoomList;


    public HouseGrid(String designation) {
        mDesignation = designation;
        mPSListInHG = new PowerSourceList ();
        mRoomList = new RoomList ();
    }


    public double getContractedMaximumPower() {
        return mContractedMaximumPower;
    }

    public void setContractedMaximumPower(double contractedMaximumPower) {
        if (valueIsPositive (contractedMaximumPower))
            this.mContractedMaximumPower = contractedMaximumPower;
    }

    private boolean valueIsPositive(double contractedMaximumPower) {
        return (contractedMaximumPower > 0);
    }

    public String getGridID() {
        return mDesignation;
    }

    public PowerSourceList getPSListInHG() {
        return mPSListInHG;
    }

    /**
     * Attach a room to a house grid if the room doesn't exists in the grid room list
     *
     * @param inputRoom Room that will be attached to a grid
     * @return true if the room is added
     */
    public boolean attachRoomToGrid(Room inputRoom) {
        if (mRoomList.getRoomList ().contains (inputRoom)) {
            return false;
        } else mRoomList.addRoom (inputRoom);
        return true;
    }

    public boolean detachRoomFromGrid(Room inputRoom) {
        if (mRoomList.getRoomList ().contains (inputRoom)) {
            mRoomList.getRoomList ().remove (inputRoom);
            return true;
        } else return false;
    }


    /**
     * Get a copy of the list of rooms in a grid
     *
     * @return list of rooms in a grid
     */
    public RoomList getRoomListInAGrid() {
        RoomList roomList = new RoomList ();
        for (Room room : mRoomList.getRoomList ()) {
            roomList.addRoom (room);
        }
        return roomList;
    }

    public int getRoomListInAGridSize() {
        return mRoomList.getRoomListSize ();
    }


    /**
     * Nominal power of a grid is the sum of nominal power of all the rooms in tat grid
     *
     * @return nominal power of a grid
     */
    public double getNominalPower() {
        double sum = 0;
        for (Room room : getRoomListInAGrid ().getRoomList ()) {
            sum += room.getNominalPower ();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HouseGrid)){
            return false;
        }
        HouseGrid houseGrid = (HouseGrid) o;
        return Objects.equals (mDesignation, houseGrid.mDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash (mDesignation);
    }
}
