package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceList {
    private List<Device> mDeviceList;

    /**
     * Constructor initializing an empty device list.
     */
    public DeviceList() {
        mDeviceList = new ArrayList<>();
    }

    /**
     * Method to add a new device instance to a DeviceList
     *
     * @param newDevice instance to be added to the device list
     * @return
     */
    public boolean addDevice(Device newDevice) {
        if (!mDeviceList.contains(newDevice)) {
            mDeviceList.add(newDevice);
            return true;
        } else return false;
    }

    /**
     * Method that creates a local instance of a device a device whose type has its own specific characteristics
     *
     * @param name         of the device
     * @param deviceSpecs  specific characteristics of the device, including, for example, device type
     * @param nominalPower of the device
     * @return a new Device instance
     */
    public Device newDevice(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        return new Device(name, deviceSpecs, nominalPower);
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
    public Device getLastElement() {
        return mDeviceList.get(mDeviceList.size() - 1);
    }

    /**
     * @param deviceType the type of device that the list returns
     * @return  a list of devices which have the same type as the one inputted as parameter
     */
    public List<Device> getDevicesOfType(DeviceType deviceType){
        List<Device> deviceListByType = new ArrayList<>();
        for (Device device:mDeviceList){
            if(device.getDeviceSpecs().getType().equals(deviceType))
                deviceListByType.add(device);
        }
        return deviceListByType;
    }

    /**
     * Shows a global device list in string
     * @return
     */
    public String showDeviceListInString() {
        List<Device> list = getDeviceList();
        StringBuilder result = new StringBuilder();
        String element = " - Device: ";
        String typeStr = " | Type: ";
        int number = 1;
        for (Device device : list) {
            result.append(number++);
            result.append(element);
            result.append(device.getName());
            result.append(typeStr);
            result.append(device.getDeviceSpecs().getType());
            result.append("\n");
        }
        return result.toString();
    }

    public List<Device> getDeviceList() {
        return mDeviceList;
    }

    public int size() {
        return mDeviceList.size();
    }
}
