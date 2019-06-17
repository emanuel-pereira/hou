package smarthome.model;

import smarthome.model.validations.Utils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Room")
public class Room implements Metered {

    @Id
    @Column(name = "ID")
    private String id;

    private String description;

    private Integer floor;

    @Embedded
    private OccupationArea area;

    private double height;

    @Transient
    private SensorList sensorListInRoom;

    @Transient
    private DeviceList deviceList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GRID_ID")
    private HouseGrid houseGrid;

    @Transient
    private double time;


    public Room() {
        this.sensorListInRoom = new SensorList();
        this.deviceList = new DeviceList();
    }


    /**
     * This constructor sets up the Room
     *
     * @param id The unique id of the room
     * @param description   Description of the room
     * @param floor  The number of the floor
     * @param length The height of the room to calculate the area
     * @param width  The width of the room to calculate the area
     * @param height The height of the room
     */
    public Room(String id, String description, Integer floor, double length, double width, double height) {
        this.id = id;
        this.description = description;
        this.floor = floor;
        this.area = new OccupationArea(length, width);
        this.height = height;
        this.sensorListInRoom = new SensorList();
        this.deviceList = new DeviceList();
    }

    /**
     * Method to get the name of the room
     *
     * @return Name of the room
     */
    public String getDesignation() {
        return this.description;
    }

    /**
     * Method to set the name of String mName.
     *
     * @param nameRoom name of the room of the house.
     */
    public void setDescription(String nameRoom) {
        this.description = nameRoom;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setSensorListInRoom(SensorList sensorListInRoom) {
        this.sensorListInRoom = sensorListInRoom;
    }

    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }

    public double getTime() {
        return time;
    }

    /**
     * Method to get the floor of the room
     *
     * @return Floor number
     */
    public int getFloor() {
        return this.floor;
    }


    public String getId() {
        return this.id;
    }

    /**
     * Method to set the floor of the room on the house.
     *
     * @param floor set the floor room the house.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Method to get the area (from the OccupationArea class)
     *
     * @return Area of the room (height*width)
     */
    public OccupationArea getArea() {
        return this.area;
    }

    /**
     * Method to set the dimensions of the room on the house.
     *
     * @param area set the dimensions of the the room on the house.
     */
    public void setArea(OccupationArea area) {
        this.area = area;
    }

    /**
     * Method to get the height
     *
     * @return Height of the room
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Method to set the height of the room on the house.
     *
     * @param height of the room
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the nominal power of a room by adding all the devices in the room device list
     *
     * @return nominal power sum
     */
    public double getNominalPower() {
        double sum = 0;
        for (Device device : this.deviceList.getDeviceList()) {
            sum += device.getNominalPower();
        }
        return sum;
    }

    /**
     * Get the total energy consumption of a room by adding all the devices in the room device list
     *
     * @return energy consumption sum
     */
    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        double sum = 0;
        for (Metered device : this.deviceList.getMeteredDevices()) {
            sum += device.getEnergyConsumption(startHour, endHour);
        }
        return Utils.round(sum, 2);
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        double sum = 0;
        for (Metered device : this.deviceList.getMeteredDevices()) {
            device.setTime(this.time);
            sum += device.getEstimatedEnergyConsumption();
        }
        return Utils.round(sum, 2);
    }

    @Override
    public void setTime(double duration) {
        this.time = duration;
    }


    /**
     * Method that checks if a text is only spaces
     *
     * @param description Name of the floor
     * @return False if only spaces. True if words and numbers
     */
    public boolean validateDescription(String description) {
        if (description.trim().isEmpty()) {
            return false;
        }
        this.description = description;
        return true;
    }

    /**
     * Changes the Id of the room to the one inputted by the user.
     *
     * @param roomId Room's id String
     * @return True if correctly validate
     */
    public boolean setId(String roomId) {
        if (this.roomIdIsValid(roomId)) {
            this.id = roomId;
            return true;
        }
        return false;
    }

    /**
     * Accept alphanumeric input without spaces
     *
     * @param id Unique identification
     * @return True if validate correctly
     */
    private boolean roomIdIsValid(String id) {
        if (id.trim().isEmpty()) {
            return false;
        }
        return id.matches("^[a-zA-Z0-9]*$");
    }

    public SensorList getSensorListInRoom() {
        return this.sensorListInRoom;
    }

    /**
     * Checks if a sensor type exists in a room
     *
     * @param input Sensors type designation
     * @return True if the sensor type exist in the room or false if not
     */
    public boolean checkIfSensorTypeExistsInRoom(String input) {
        List<Sensor> list = this.getSensorListInRoom().getSensorList();
        for (Sensor s : list) {
            SensorType sensorType=s.getSensorBehavior().getSensorType();
            if (sensorType.getType().getName().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public DeviceList getDeviceList() {
        return this.deviceList;
    }

    public int getSizeDeviceListInRoom() {
        return this.deviceList.size();
    }


    public HouseGrid getHouseGrid() {
        return houseGrid;
    }

    public void setHouseGrid(HouseGrid houseGrid) {
        this.houseGrid = houseGrid;
    }

    public void detachHouseGrid() {
        this.houseGrid = null;
    }


    /**
     * When two objects (o1 and o2) with the same data are compare, the result is that they are different objects.
     * If there's the need to check for equality of values inside the objects the inherit equals method need to be override.
     * First: check if the argument is a reference to this object.
     * Second: check if o is an instance of Room or not, it allows for subclasses to be equal.
     * Final: typecast o to Room so that we can compare data member (cast the argument to the correct type so that
     * we can compare data members). Then compare the data members and return accordingly.
     *
     * @param o Any kind of object
     * @return If the object is compared with itself then return true. Check if the argument has the correct type. If not, return false.
     * Check if that field of the argument matches the corresponding field of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(this.id, room.id);
    }

    /**
     * Equal objects may get different hash-values, so when equal() is override, the hash value must also be override.
     *
     * @return Equal objects must produce the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
