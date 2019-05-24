package smarthome.model;

import smarthome.model.validations.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "House_Grids")
public class HouseGrid implements Metered {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private double contractedMaximumPower = Double.NaN;

    @NotNull
    private String designation;
    @Transient
    private PowerSourceList psListInHG;

    @Transient
    private RoomList roomList;

    protected HouseGrid() {
    }


    public HouseGrid(String designation) {
        this.designation = designation;
        this.psListInHG = new PowerSourceList();
        this.roomList = new RoomList();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getContractedMaximumPower() {
        return this.contractedMaximumPower;
    }

    public void setContractedMaximumPower(double contractedMaximumPower) {
        if (Utils.valueIsPositive(contractedMaximumPower))
            this.contractedMaximumPower = contractedMaximumPower;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String newDesignation) {
        this.designation = newDesignation;
    }

    public PowerSourceList getPsListInHG() {
        return this.psListInHG;
    }

    public void setPsListInHG(PowerSourceList psListInHG) {
        this.psListInHG = psListInHG;
    }

    /**
     * Attach a room to a house grid if the room doesn't exists in the grid room list
     *
     * @param inputRoom Room that will be attached to a grid
     * @return true if the room is added
     */
    public boolean attachRoomToGrid(Room inputRoom) {
        if (this.roomList.getRoomList().contains(inputRoom)) {
            return false;
        } else this.roomList.addRoom(inputRoom);
        return true;
    }

    public boolean detachRoomFromGrid(Room inputRoom) {
        if (this.roomList.getRoomList().contains(inputRoom)) {
            this.roomList.getRoomList().remove(inputRoom);
            return true;
        } else return false;
    }


    /**
     * @return list of rooms in a grid
     */
    public RoomList getRoomList() {
        return this.roomList;
    }

    public void setRoomList(RoomList roomList) {
        this.roomList = roomList;
    }

    public int getRoomListInAGridSize() {
        return this.roomList.getRoomListSize();
    }

    public DeviceList getDeviceListInGrid() {
        DeviceList deviceListInGrid = new DeviceList();
        for (Room r : this.roomList.getRoomList()) {
            deviceListInGrid.getDeviceList().addAll(r.getDeviceList().getDeviceList());
        }
        return deviceListInGrid;
    }

    public DeviceList getDeviceListFromType(int type) {
        Configuration c = new Configuration();
        DeviceList deviceListFromType = new DeviceList();
        for (Device d : this.getDeviceListInGrid().getDeviceList()) {
            if (c.getDeviceTypes().get(type).equals(d.getDeviceType())) {
                deviceListFromType.add(d);
            }
        }
        return deviceListFromType;
    }

    public DeviceList getDeviceListInGridGroupBy() {
        DeviceList deviceListGroupByType = new DeviceList();
        int indexType;
        for (indexType = 0; indexType <= 16; indexType++) {
            deviceListGroupByType.getDeviceList().addAll(getDeviceListFromType(indexType).getDeviceList());
        }
        return deviceListGroupByType;
    }

    public String showGroupedDeviceListInGridString() {
        List<Device> list = getDeviceListInGridGroupBy().getDeviceList();
        StringBuilder result = new StringBuilder();
        String element = " - Device: ";
        String typeStr = " | Type: ";
        String locationStr = " | Location: ";
        String statusStr = " | Active: ";
        int number = 1;
        for (Device device : list) {
            String deviceLocation = this.roomList.getDeviceLocation(device).getDesignation();
            result.append(number++);
            result.append(element);
            result.append(device.getDeviceName());
            result.append(typeStr);
            result.append(device.getDeviceType());
            result.append(locationStr);
            result.append(deviceLocation);
            result.append(statusStr);
            result.append(device.isActive());
            result.append("\n");
        }
        return result.toString();
    }


    /**
     * Nominal power of a grid is the sum of nominal power of all the rooms in tat grid
     *
     * @return nominal power of a grid
     */
    public double getNominalPower() {
        double sum = 0;
        for (Room room : getRoomList().getRoomList()) {
            sum += room.getNominalPower();
        }
        return sum;
    }


    public String showRoomsInHouseGrid() {
        RoomList listOfRoomsInHG = getRoomList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsInHG.getRoomList()) {
            result.append(number++);
            result.append(element);
            result.append(r.getDesignation());
            result.append("\n");
        }
        return result.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HouseGrid)) {
            return false;
        }
        HouseGrid houseGrid = (HouseGrid) o;
        return Objects.equals(this.designation, houseGrid.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.designation);
    }

    @Override
    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        double total = 0;
        for (Room room : this.roomList.getRoomList()) {
            total += room.getEnergyConsumption(startHour, endHour);
        }
        return Utils.round(total, 2);
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        double sum = 0;
        for (Metered room : this.roomList.getRoomList()) {
            sum += room.getEstimatedEnergyConsumption();
        }
        return Utils.round(sum, 2);
    }

    @Override
    public void setTime(double duration) {
        // do nothing.
    }
}
