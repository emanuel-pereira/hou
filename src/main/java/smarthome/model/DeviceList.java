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
     * @param room         where the device will be installed
     * @param nominalPower of the device
     * @return a new Device instance
     */
    public Device newDevice(String name, DeviceType deviceType, DeviceSpecs deviceSpecs, Room room, double nominalPower) {
        return new Device(name, deviceSpecs, room, nominalPower, deviceType);
    }

    public Device newDevice(String name, DeviceType deviceType, Room room, double nominalPower) {
        return new Device(name, deviceType, room, nominalPower);
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
     * @return the device list
     */
    public List<Device> getDeviceList() {
        return mDeviceList;
    }

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
            result.append(device.getDeviceType());
            result.append("\n");
        }
        return result.toString();
    }
    public String showElectricWaterHeaterList() {
        DeviceList electricWaterHeaterList = getElectricWaterHeaterList();
        StringBuilder result = new StringBuilder();
        for (Device device : electricWaterHeaterList.getDeviceList()) {
            result.append(device.showDeviceListAttributesInString());
            result.append("\n");
        }
        return result.toString();
    }


    //show electric list in string for each room
    public DeviceList getElectricWaterHeaterList() {
        DeviceList electricWaterHeaterList = new DeviceList();
        for (Device device : mDeviceList) {
            if (device.getDeviceType().getTypeString().equals("Electric Water Heater"))
                electricWaterHeaterList.addDevice(device);
        }
        return electricWaterHeaterList;
    }



    /**
     * @return the total energy consumed by all electric water heaters in a specific device list.
     */
    public double getEnergyConsumptionOfEWHList() {
        DeviceList listOfEWH = getElectricWaterHeaterList();
        double totalEnergyConsumption = 0;
        for (Device device : listOfEWH.getDeviceList()) {
            totalEnergyConsumption += ((ElectricWaterHeater) device.getDeviceSpecs()).getEnergyConsumption();
        }
        return totalEnergyConsumption;
    }

    /**
     * Sets volume of water for all devices of type Electric Water Heater that may be installed in a device list.
     *
     * @param volumeOfWater double value parameter to set the volume of water of every Electric Water Heater installed in all rooms of the house.
     */
    public void setVolumeOfWaterEWHList(double volumeOfWater) {
        DeviceList listOfEWH = getElectricWaterHeaterList();
        for (Device device : listOfEWH.getDeviceList()) {
            ((ElectricWaterHeater) device.getDeviceSpecs()).setVolumeOfWater(volumeOfWater);
        }
    }

    /**
     * Sets cold water temperature for all devices of type Electric Water Heater that may be installed in a device list.
     *
     * @param coldWaterTemperature double value parameter to set the volume of water of every Electric Water Heater installed in a device list.
     */
    public void setColdWaterTemperatureEWHList(double coldWaterTemperature) {
        DeviceList listOfEWH = getElectricWaterHeaterList();
        for (Device device : listOfEWH.getDeviceList()) {
            ((ElectricWaterHeater) device.getDeviceSpecs()).setColdWaterTemperature(coldWaterTemperature);
        }
    }

    public int size(){
        return mDeviceList.size();
    }
}
