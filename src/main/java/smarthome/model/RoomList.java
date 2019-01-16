package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class RoomList {
    private List<Room> mRoomList;

    /**
     * Constructor method that creates a new list to save Room objects
     */
    public RoomList() {
        mRoomList = new ArrayList<> ();
    }


    public Room createNewRoom(String name, int floor, double length, double width, double height) {
        if (this.roomNameValid (name)) {
            return new Room (name, floor, length, width, height);
        }
        return null;
    }

    public boolean roomNameValid(String name) {
        return name != null && !name.trim ().isEmpty ();
    }

    /**
     * Method to add a room object to Room list, if it is not on the list yet
     *
     * @param newRoom - new Room object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addRoom(Room newRoom) {
        if (newRoom != null && !mRoomList.contains (newRoom)) {
            mRoomList.add (newRoom);
            return true;
        } else return false;
    }

    public boolean removeRoom(Room newRoom) {
        if (mRoomList.contains (newRoom)) {
            mRoomList.remove (newRoom);
            return true;
        } else return false;
    }

    /**
     * Method to get a specific Room in index position i
     *
     * @param i index position of the Room's List
     * @return the specific requested Room
     */
    public Room get(int i) {
        return this.mRoomList.get (i);
    }

    public int size(){
        return this.mRoomList.size();
    }

    /**
     * Method to return the rooms included in the list
     *
     * @return list of rooms created
     */
    public List<Room> getRoomList() {
        return mRoomList;
    }

    /**
     * Transforms a list of rooms in a numbered list of strings with thw names of the rooms
     * @return List of room sin string
     */
    public String showRoomListInString() {
        List<Room> list = getRoomList ();
        StringBuilder result = new StringBuilder ();
        String element = " - ";
        int number = 1;
        for (Room room : list) {
            result.append (number++);
            result.append (element);
            result.append (room.getName ());
            result.append ("\n");
        }
        return result.toString ();
    }

    public double getNominalPower(HouseGrid houseGrid) {
        double sum = 0;
        for (Room room : this.getRoomList ()) {
            if (room.getmHouseGrid ().equals (houseGrid)){
                sum += room.getNominalPower ();
            }
        }
        return sum;
    }


}
