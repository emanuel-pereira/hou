package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class Device implements DeviceSpecs, Powered {

    private String mName;
    private DeviceSpecs mDeviceSpecs;
    private DeviceType mDeviceType;
    private Room mRoom;
    private double mNominalPower;

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

    public void setRoom(Room newRoom) {
        mRoom.getDeviceList ().getDeviceList ().remove (this);
        mRoom = newRoom;
        mRoom.getDeviceList ().addDevice (this);
    }

    public void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }

    public String showDeviceAttributesInString() {
        StringBuilder result = new StringBuilder ();
        for (String s : getDeviceAttributesInString ()) {
            result.append (s);
            result.append ("\n");
        }
        return result.toString ();
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<> ();
        String deviceName = "1 - Device Name : " + this.getName ();
        String deviceRoom = "2 - Device Room : " + this.mRoom.getName ();
        String deviceNominalPower = "3 - Device Nominal Power : " + this.mNominalPower;
        result.add (deviceName);
        result.add (deviceRoom);
        result.add (deviceNominalPower);
        if (this.mDeviceSpecs != null)
            for (String spec : this.getDeviceSpecs ().getDeviceAttributesInString ())
                result.add (spec);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String deviceName = "1 - Device Name : " + this.mName;
        String deviceRoom = "2 - Device Room : " + this.mRoom;
        String deviceNominalPower = "3 - Device Nominal Power : " + this.mNominalPower;
        if (attribute.equals (deviceName)) {
            setDeviceName (newValue);
        }
        if (attribute.equals (deviceNominalPower)) {
            setNominalPower (parseDouble (newValue));
        }
        if (this.mDeviceSpecs != null) {
            this.getDeviceSpecs ().setAttributeValue (attribute, newValue);
        }
    }
}


