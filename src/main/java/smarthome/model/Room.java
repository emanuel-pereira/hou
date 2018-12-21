package smarthome.model;

import java.util.Objects;

public class Room {

    private String mName;
    private int mFloor;
    private OccupationArea mArea;
    private HouseGrid mHouseGrid; //each room has only a house grid


    public Room(String name, int floor, double height, double width) {
        if (this.validateName ( name )) {
            mName = name;
        }
        mFloor = floor;
        mArea = new OccupationArea ( height, width );
    }


    public String getName() {
        return mName;
    }

    public int getFloor() {
        return mFloor;
    }

    public OccupationArea getArea() {
        return mArea;
    }

    public boolean validateName(String roomName) {
        if (roomName.trim ().isEmpty ()) {
            return false;
        }
        mName = roomName;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals ( mName, room.mName );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( mName );
    }
}
