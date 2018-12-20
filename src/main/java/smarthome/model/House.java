package smarthome.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House {

    private Address mAddress;
    private GeographicalArea mGA;
    private List<Room> mRoomList;


    public House(Address houseAddress, GeographicalArea GA) {
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new ArrayList<> ();
    }


    public Address getAddress() {

        return mAddress;
    }


    public GeographicalArea getGA() {
        return mGA;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals ( mAddress, house.mAddress );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( mAddress );
    }


    //RoomList

    public Room newRoom(String name, int floor, double height, double width) {
        if (this.roomNameValid ( name )) {
            return new Room ( name, floor, height, width );
        }
        return null;
    }

    public boolean addRoom(Room inputRoom) {
        if (inputRoom != null && !mRoomList.contains ( inputRoom )) {
            mRoomList.add ( inputRoom );
            return true;
        } else return false;
    }


    public List<Room> getRoomList() {
        return mRoomList;
    }


    public boolean roomNameValid(String name) {
        return name != null && !name.trim ().isEmpty ();
    }



}



