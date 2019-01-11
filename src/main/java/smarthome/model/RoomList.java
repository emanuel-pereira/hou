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

    /**
     * Method to return the rooms included in the list
     *
     * @return list of rooms created
     */
    public List<Room> getRoomList() {
        return mRoomList;
    }

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

    /**
     * Check if Sensor Type exist in Room. If the the result is 0, there's no defined Sensor Type
     *
     * @param type Name of the required Sensor Type
     * @return size of the list of defined Sensor Type
     */
    public int checkIfSensorTypeExistInRoomBySize(String type) {
        int result = 0;
        List<Room> list = getRoomList ();
        for (Room r : list) {
            r.getSensorListInRoom ().checkIfRequiredSensorTypeExists (type);
            result = r.getSensorListInRoom ().getSensorList ().size ();
        }
        return result;
    }

    public boolean checkIfSensorTypeExistsInRoom (String input) {
        List<Room> list = getRoomList ();
        for (Room r : list) {
            if (r.getSensorListInRoom ().checkIfRequiredSensorTypeExists (input)) {
                return true;
            }
        }
        return false;
    }


    public boolean checkIfRoomLisNotEmpty(){
        if (this.getRoomList ().size () == 0){
            return false;
        }
        return true;
    }


}
