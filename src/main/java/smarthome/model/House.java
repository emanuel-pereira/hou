package smarthome.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House {

    private Address mAddress;
    private String mID;
    private GeographicalArea mGA;
    private RoomList mRoomList;
    private HouseGridList mHGListInHouse;


    public House() {
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }

    public House(Address houseAddress, GeographicalArea GA) {
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }

    public House(String id, Address houseAddress, GeographicalArea GA) {
        mID = id;
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new RoomList();
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

    public RoomList getRoomListFromHouse() {
        return mRoomList;
    }

    public HouseGridList getHGListInHouse () {
        return mHGListInHouse;
    }

}



