package smarthome.model;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Room implements Powered, Metered {

    private String mName;
    private Integer mFloor;
    private OccupationArea mArea;
    private double mHeight;
    private SensorList mSensorListInRoom = new SensorList();
    private DeviceList mDeviceList;


    /**
     * This constructor sets up the Room
     * @param name Unique name of the room
     * @param floor The number of the floor
     * @param length The height of the room to calculate the area
     * @param width The width of the room to calculate the area
     * @param height The height of the room
     */
    public Room(String name, Integer floor, double length, double width, double height) {
        mName = name;
        mFloor = floor;
        mArea = new OccupationArea ( length, width );
        mHeight = height;
        mDeviceList= new DeviceList();
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
     * Method to get the height
     * @return Height of the room
     */
    public double getHeight() {
        return mHeight;
    }

    /**
     * Get the nominal power of a room by adding all the devices in the room device list
     * @return nominal power sum
     */
    public double getNominalPower(){
        double sum = 0;
            for (Device device : mDeviceList.getDeviceList ()){
                sum += device.getNominalPower ();
             }
            return sum;
    }

    /**
     * Method to set the name of String mName.
     * @param setNameRoom  name of the room of the house.
     */
    public void setName(String setNameRoom) {
        this.mName = setNameRoom;
    }

    /**
     * Method to set the floor of the room on the house.
     * @param mFloor set the floor room the house.
     */
    public void setFloor(int mFloor) {
        this.mFloor = mFloor;
    }

    /**
     * Method to set the dimensions of the room on the house.
     * @param mArea set the dimensions of the the room on the house.
     */
    public void setArea(OccupationArea mArea) {
        this.mArea = mArea;
    }

    /**
     * Method to set the height of the room on the house.
     * @param height of the room
     */
    public void setHeight(double height) {
        this.mHeight = height;
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


    public SensorList getSensorListInRoom() {
        return mSensorListInRoom;
    }

    /**
     * Checks if a sensor type exists in a room
     * @param input Sensor type designation
     * @return True if the sensor type exist in the room or false if not
     */
    public boolean checkIfSensorTypeExistsInRoom (String input) {
        List<Sensor> list = this.getSensorListInRoom ().getSensorList ();
        for (Sensor s : list) {
            if (s.getSensorType ().getSensorTypeDesignation ().equals (input)) {
                return true;
            }
        }
        return false;
    }

    public DeviceList getDeviceList() {
        return mDeviceList;
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
        if (this == o){
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
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

    @Override
    public double getEnergyConsumptionInTimeInterval(Calendar startHour, Calendar endHour) {
        double total=0;
        for(Device device:mDeviceList.getMeteredDevices())
        total+=device.getEnergyConsumptionInTimeInterval(startHour,endHour);
        return total;
    }
}
