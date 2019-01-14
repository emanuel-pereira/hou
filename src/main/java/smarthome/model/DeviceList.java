package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceList {
    private List<Device> mDeviceList;

    /**
     * Constructor initializing an empty device list.
     */
    public DeviceList(){
        mDeviceList=new ArrayList<>();
    }

    /**
     * Method to add a new device instance to a DeviceList
     * @param newDevice instance to be added to the device list
     * @return
     */
    public boolean addDevice(Device newDevice){
        if(!mDeviceList.contains(newDevice)){
            mDeviceList.add(newDevice);
            return true;
        } else return false;
    }

    /**
     * Method that creates a local instance of a device a device whose type has its own specific characteristics
     * @param name of the device
     * @param deviceSpecs specific characteristics of the device, including, for example, device type
     * @param room where the device will be installed
     * @param nominalPower of the device
     * @return a new Device instance
     */
    public Device newDeviceWithSpecs(String name,DeviceSpecs deviceSpecs, Room room, double nominalPower ){
        return new Device(name, deviceSpecs,room,nominalPower);
    }

    /**
     * Method that creates a local instance of a device that only require generic features to any type of device
     * @param name of the device
     * @param room where the device will be installed
     * @param deviceType the type of device
     * @param nominalPower of the device
     * @return a new Device instance
     */
    public Device newDeviceWithoutSpecs(String name, Room room, String deviceType, double nominalPower ){
        return new Device(name,room,deviceType,nominalPower);
    }

    /**
     * @param index position in the device list
     * @return the device in index position in the device list
     */
    public Device get(int index) {
        return mDeviceList.get(index);
    }

    /**
     * @return the device in the last index position of the device list
     */
    public Device getLastElement(){
        return mDeviceList.get(mDeviceList.size()-1);
    }

    /**
     * @return the device list
     */
    public List<Device> getDeviceList() {
        return mDeviceList;
    }
}
