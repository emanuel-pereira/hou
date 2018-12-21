package smarthome.model;

import java.util.Objects;

public class Room {

    private String mName;
    private Integer mFloor;
    private OccupationArea mArea;
    private HouseGrid mHouseGrid; //each room has only a house grid


    /**
     * This constructor sets up the Room
     * @param name Unique name of the room
     * @param floor The number of the floor
     * @param height The height of the room to calculate the area
     * @param width The width of the room to calculate the area
     */
    public Room(String name, Integer floor, double height, double width) {
        if (this.validateName ( name )) {
            mName = name;
        }
        mFloor = floor;
        mArea = new OccupationArea ( height, width );
    }

    /**
     * Method to get the name of the room
     * @return Name of the room
     */
    public String getName() {
        return mName;
    }

    /**
     * Method to get the floor of the room
     * @return Floor number
     */
    public int getFloor() {
        return mFloor;
    }

    /**
     * Method to get the area (from the OccupationArea class)
     * @return Area of the room (height*width)
     */
    public OccupationArea getArea() {
        return mArea;
    }

    /**
     * Method that checks if a text is only spaces
     * @param roomName Name of the floor
     * @return False if only spaces. True if words and numbers
     */
    public boolean validateName(String roomName) {
        if (roomName.trim ().isEmpty ()) {
            return false;
        }
        mName = roomName;
        return true;
    }

    /**
     * When two objects (o1 and o2) with the same data are compare, the result is that they are different objects.
     * If there's the need to check for equality of values inside the objects the inherit equals method need to be override.
     * First: check if the argument is a reference to this object.
     * Second: check if o is an instance of Room or not, it allows for subclasses to be equal.
     * Final: typecast o to Room so that we can compare data member (cast the argument to the correct type so that
     * we can compare data members). Then compare the data members and return accordingly.
     * @param o Any kind of object
     * @return If the object is compared with itself then return true. Check if the argument has the correct type. If not, return false.
     * Check if that field of the argument matches the corresponding field of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals ( mName, room.mName );
    }

    /**
     Equal objects may get different hash-values, so when equal() is override, the hash value must also be override.
     * @return Equal objects must produce the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash ( mName );
    }
}
