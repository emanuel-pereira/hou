package smarthome.model;

public class Device implements DeviceSpecs, Powered {

    private String mName;
    private DeviceSpecs mDeviceSpecs;
    private Room mRoom;
    private double mNominalPower;

    /**
     * Constructor set for devices with their own specific features
     * @param name device name
     * @param deviceSpecs device specific features, including, for example, device type
     * @param room where the device is installed
     * @param nominalPower of the device
     */
    public Device(String name, DeviceSpecs deviceSpecs, Room room, double nominalPower){
        mName=name;
        mDeviceSpecs=deviceSpecs;
        mRoom=room;
        mNominalPower=nominalPower;
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
    public double getNominalPower() {
        return mNominalPower;
    }

    /**
     * @return the type of device for a device types that have their own specific features
     */
    @Override
    public String getType() {
        return mDeviceSpecs.getType();
    }

    /**
     * @param index device type in the specified index enum position
     * @return the type of device for a device types that have their own specific features
     */
    @Override
    public String getTypeFromIndex(int index) {
        return mDeviceSpecs.getTypeFromIndex(index);
    }
}
