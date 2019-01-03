package smarthome.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House {

    private Address mAddress;
    private GeographicalArea mGA;
    private List<Room> mRoomList;
    private List<HouseGrid> mHouseGridList;

    public House() {
    }

    public House(Address houseAddress, GeographicalArea GA) {
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new ArrayList<>();
        mHouseGridList = new ArrayList<>();
    }


    public void setHouseGA(GeographicalArea houseGA) {
        mGA = houseGA;
    }

    public Address getAddress() {

        return mAddress;
    }

    public void setHouseAddress(String streetName, String houseNumber, String zipCode, double latitude, double longitude, double altitude) {
        mAddress = new Address(streetName, houseNumber, zipCode, latitude, longitude, altitude);
    }

    public GeographicalArea getGA() {
        return mGA;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals(mAddress, house.mAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAddress);
    }


    //RoomList

    public Room newRoom(String name, int floor, double height, double width) {
        if (this.roomNameValid(name)) {
            return new Room(name, floor, height, width);
        }
        return null;
    }

    public boolean addRoom(Room inputRoom) {
        if (inputRoom != null && !mRoomList.contains(inputRoom)) {
            mRoomList.add(inputRoom);
            return true;
        } else return false;
    }

    public boolean removeRoom(Room inputRoom) {
        if (mRoomList.contains(inputRoom)) {
            mRoomList.remove(inputRoom);
            return true;
        } else return false;
    }


    public List<Room> getRoomList() {
        return mRoomList;
    }


    public boolean roomNameValid(String name) {
        return name != null && !name.trim().isEmpty();
    }

    //HouseGridList

    public HouseGrid newHouseGrid(double inputContractedMaximumPower) {
        if (isValidContractedPower(inputContractedMaximumPower)) {
            return new HouseGrid(inputContractedMaximumPower);
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
     * checks if the inputted
     *
     * @param inputContractedMaximumPower
     * @return
     */
    public boolean isValidContractedPower(double inputContractedMaximumPower) {
        if (inputContractedMaximumPower > 0)
            return true;
        return false;
    }
}



