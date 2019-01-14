package smarthome.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House {

    private Address mAddress;
    private String mID;
    private GeographicalArea mGA;
    private List<Room> mRoomList;
    private HouseGridList mHGListInHouse;


    public House() {
        mRoomList = new ArrayList<>();
        mHGListInHouse = new HouseGridList();
    }

    public House(Address houseAddress, GeographicalArea GA) {
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new ArrayList<>();
        mHGListInHouse = new HouseGridList();
    }

    public House(String id, Address houseAddress, GeographicalArea GA) {
        mID = id;
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new ArrayList<>();
        mHGListInHouse = new HouseGridList();
    }


    public void setHouseGA(GeographicalArea houseGA) {
        mGA = houseGA;
    }

    public GeographicalArea getHouseGA (){
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

    public Room newRoom(String name, int floor, double length, double width, double height) {
        if (this.roomNameValid(name)) {
            return new Room(name, floor, length, width, height);
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

    public boolean addHGinHGLinHouse (HouseGrid inputHG) {
        return mHGListInHouse.addHouseGrid(inputHG);
    }

    public HouseGridList getHGListInHouse () {
        return mHGListInHouse;
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



