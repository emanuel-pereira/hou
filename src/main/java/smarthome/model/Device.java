package smarthome.model;

public class Device implements DeviceSpecs, Powered {

    private String mName;
    private DeviceSpecs mDeviceSpecs;
    private DeviceType mDeviceType;
    private Room mRoom;
    private double mNominalPower;
    private Metered mEnergyConsumption;

    /**
     * Constructor set for devices with their own specific features
     *
     * @param name         device name
     * @param deviceSpecs  device specific features, including, for example, device type
     * @param room         where the device is installed
     * @param nominalPower of the device
     */
    public Device(String name, DeviceSpecs deviceSpecs, Room room, double nominalPower, DeviceType deviceType) {
        mName = name;
        mDeviceSpecs = deviceSpecs;
        mRoom = room;
        mNominalPower = nominalPower;
        mDeviceType = deviceType;
    }

    public Device(String name, DeviceType deviceType, Room room, double nominalPower) {
        mName = name;
        mRoom = room;
        mNominalPower = nominalPower;
        mDeviceType = deviceType;
    }

    /**
     * @return the device name
     */
    public String getName() {
        return mName;
    }

    /**
     * @return the device specifications
     */
    public DeviceSpecs getDeviceSpecs() {
        return mDeviceSpecs;
    }

    /**
     * @return the room where the device is installed
     */
    public Room getRoom() {
        return mRoom;
    }

    /**
     * @return the nominal power of the device
     */
    public DeviceType getDeviceType() {
        return mDeviceType;
    }

    public double getNominalPower() {
        return mNominalPower;
    }

    public void setDeviceName(String name) {
        this.mName = name;
    }

    public void setRoom(Room room) {
        mRoom.getDeviceList().getDeviceList().remove(this);
        mRoom = room;
        mRoom.getDeviceList().addDevice(this);
    }

    public void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }


    public String showDeviceListAttributesInString() {
        StringBuilder result = new StringBuilder();
        result.append("1 - Device name : " + this.getName());
        result.append("\n");
        result.append("2 - Device room : " + this.getRoom().getName());
        result.append("\n");
        result.append("3 - Nominal Power : " + this.getNominalPower() + " kW");
        result.append("\n");
        result.append(this.showDeviceSpecsListAttributesInString());
        return result.toString();
    }

    public String showDeviceSpecsListAttributesInString() {
        return mDeviceSpecs.showDeviceSpecsListAttributesInString();
    }

   /* @Override
    public double getEnergyConsumption() {
        return mEnergyConsumption.getEnergyConsumption();

    }*/
}
