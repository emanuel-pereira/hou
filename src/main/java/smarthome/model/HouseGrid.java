package smarthome.model;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class HouseGrid implements Metered{
    private double mContractedMaximumPower = Double.NaN;
    private String mDesignation;
    private PowerSourceList mPSListInHG;
    private RoomList mRoomList;


    public HouseGrid(String designation) {
        mDesignation = designation;
        mPSListInHG = new PowerSourceList ();
        mRoomList = new RoomList ();
    }


    public double getContractedMaximumPower() {
        return mContractedMaximumPower;
    }

    public void setContractedMaximumPower(double contractedMaximumPower) {
        if (Utils.valueIsPositive (contractedMaximumPower))
            this.mContractedMaximumPower = contractedMaximumPower;
    }

    public String getGridID() {
        return mDesignation;
    }

    public PowerSourceList getPSListInHG() {
        return mPSListInHG;
    }

    /**
     * Attach a room to a house grid if the room doesn't exists in the grid room list
     *
     * @param inputRoom Room that will be attached to a grid
     * @return true if the room is added
     */
    public boolean attachRoomToGrid(Room inputRoom) {
        if (mRoomList.getRoomList ().contains (inputRoom)) {
            return false;
        } else mRoomList.addRoom (inputRoom);
        return true;
    }

    public boolean detachRoomFromGrid(Room inputRoom) {
        if (mRoomList.getRoomList ().contains (inputRoom)) {
            mRoomList.getRoomList ().remove (inputRoom);
            return true;
        } else return false;
    }


    /**
     *
     * @return list of rooms in a grid
     */
    public RoomList getRoomListInAGrid() {
       return mRoomList;
    }

    public int getRoomListInAGridSize() {
        return mRoomList.getRoomListSize ();
    }


    /**
     * Nominal power of a grid is the sum of nominal power of all the rooms in tat grid
     *
     * @return nominal power of a grid
     */
    public double getNominalPower() {
        double sum = 0;
        for (Room room : getRoomListInAGrid ().getRoomList ()) {
            sum += room.getNominalPower ();
        }
        return sum;
    }

    public String showRoomsInHouseGrid() {
        RoomList listOfRoomsInHG = getRoomListInAGrid();
        StringBuilder result = new StringBuilder ();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsInHG.getRoomList ()) {
            result.append (number++);
            result.append (element);
            result.append (r.getName ());
            result.append ("\n");
        }
        return result.toString ();
    }

    public DeviceList getDeviceListInGrid(){ DeviceList deviceListInGrid = new DeviceList();
        for (int i = 0; i < this.getRoomListInAGrid().getRoomListSize(); i++) {
            deviceListInGrid.getDeviceList().addAll(getRoomListInAGrid().get(i).getDeviceList().getDeviceList());
        }
        return deviceListInGrid;
    }


    public DeviceList getDeviceListFromType(int indexType){
        DeviceList deviceListFromType = new DeviceList();
        for(Device d : this.getDeviceListInGrid().getDeviceList()){
            if(DeviceType.values()[indexType-1].getTypeString().equals(d.getDeviceSpecs().getType().getTypeString())){
                deviceListFromType.addDevice(d);
            }
        }
        return deviceListFromType;
    }

    public DeviceList getDeviceListInGridGroupBy(){
        DeviceList deviceListGroupByType = new DeviceList();
        int indexType;
        for(indexType = 1; indexType <= 13; indexType++){
            deviceListGroupByType.getDeviceList().addAll(getDeviceListFromType(indexType).getDeviceList());
        }
        return deviceListGroupByType;
    }

    public String showGroupedDeviceListInGridString(){
        List<Device> list = getDeviceListInGridGroupBy().getDeviceList();
        StringBuilder result = new StringBuilder();
        String element = " - Device: ";
        String typeStr = " | Type: ";
        String locationStr = " | Location: ";
        String statusStr = " | Active: ";
        int number = 1;
        for (Device device : list) {
            String deviceLocation = mRoomList.getDeviceLocation(device).getName();
            result.append(number++);
            result.append(element);
            result.append(device.getName());
            result.append(typeStr);
            result.append(device.getDeviceSpecs().getType());
            result.append(locationStr);
            result.append(deviceLocation);
            result.append(statusStr);
            result.append(device.status());
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HouseGrid)){
            return false;
        }
        HouseGrid houseGrid = (HouseGrid) o;
        return Objects.equals (mDesignation, houseGrid.mDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash (mDesignation);
    }

    @Override
    public double getEnergyConsumptionInTimeInterval(Calendar startHour, Calendar endHour) {
        double total=0;
        for(Room room : mRoomList.getRoomList()){
            total+=room.getEnergyConsumptionInTimeInterval(startHour,endHour);
        }
        return total;
    }
}
