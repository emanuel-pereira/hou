package smarthome.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House {

    private Address mAddress;
    private String mID;
    private GeographicalArea mGA;
    private RoomList mRoomList;
    private List<HouseGrid> mHouseGridList;


    public House() {
        mRoomList = new RoomList();
        mHouseGridList = new ArrayList<>();
    }

    public House(Address houseAddress, GeographicalArea GA) {
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new RoomList();
        mHouseGridList = new ArrayList<>();
    }

    public House(String id, Address houseAddress, GeographicalArea GA) {
        mID = id;
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new RoomList();
        mHouseGridList = new ArrayList<>();
    }


    public void setHouseGA(GeographicalArea houseGA) {
        mGA = houseGA;
    }

    public GeographicalArea getHouseGA() {
        return mGA;
    }


    public void setHouseAddress(String streetName, String houseNumber, String zipCode, double latitude, double longitude, double altitude) {
        mAddress = new Address(streetName, houseNumber, zipCode, latitude, longitude, altitude);
    }

    public Address getAddress() {
        return mAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals(mAddress, house.mAddress) &&
                Objects.equals(mID, house.mID) &&
                Objects.equals(mGA, house.mGA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAddress, mID, mGA);
    }

    //RoomList
    public RoomList getRoomListFromHouse() {
        return mRoomList;
    }

    //HouseGridList

    public HouseGrid newHouseGrid(double inputContractedMaximumPower, String ID) {
        if (isValidContractedPower(inputContractedMaximumPower)) {
            return new HouseGrid(inputContractedMaximumPower, ID);
        }
        return null;
    }

    /**
     * Adds a house grid to the list of grids of a house if it isn't null or
     * already contained in the houseGrid list of the respective house instance.
     *
     * @param inputHouseGrid houseGrid to be added to list of HouseGrids of a house instance.
     * @return true if houseGrid is added to list or false otherwise.
     */
    public boolean addHouseGrid(HouseGrid inputHouseGrid) {
        if (inputHouseGrid != null && !mHouseGridList.contains(inputHouseGrid)) {
            mHouseGridList.add(inputHouseGrid);
            return true;
        } else return false;
    }

    /**
     * @return the list of housegrids in the house
     */
    public List<HouseGrid> getHouseGridList() {
        return mHouseGridList;
    }

    /**
     * checks if the inputted Maximum Power is Positive
     *
     * @param inputContractedMaximumPower Double number
     * @return
     */
    public boolean isValidContractedPower(double inputContractedMaximumPower) {
        return inputContractedMaximumPower > 0;
    }
}



