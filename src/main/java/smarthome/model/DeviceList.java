package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceList {
    private List<Device> devicesList;

    /**
     * Constructor initializing an empty device list.
     */
    public DeviceList() {
        devicesList = new ArrayList<>();
    }

    /**
     * Method to add a new device instance to a DeviceList
     *
     * @param device instance to be added to the device list
     * @return boolean result of the device addition
     */
    public boolean addDevice(Device device) {
        if (!devicesList.contains(device)) {
            devicesList.add(device);
            return true;
        } else return false;
    }

    /**
     *
     * @param deviceName name given to the device by the user, e.g. "Cool Plasma TV 9000"
     * @param deviceType a string containing the designation of a given device type e.g. "Fridge". The device list is
     * @param nominalPower the nominal power of the device (in kW).
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Device newDevice(String deviceName, String deviceType, double nominalPower) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String path = "smarthome.model."; // TODO: this should be removed from here. The path should be returned from the Configuration class and "injected" here.

        String deviceTypeNameAndPath = path.concat(deviceType).concat("Type"); // by internal convention every device type is named as [Name]Type.

        // Return a new instance of the class implementing the DeviceType interface using reflection
        DeviceType dt = (DeviceType) Class.forName(deviceTypeNameAndPath).newInstance(); // e.g. FridgeType

        return dt.createDevice(deviceName, nominalPower); // The createDevice method returns a new Device based on the user given name. Done!
    }

    /**
     * @param index position in the device list
     * @return the device in index position in the device list
     */
    public Device get(int index) {
        return devicesList.get(index);
    }

    /**
     * @return the device in the last index position of the device list
     */
    Device getLastElement() {
        return devicesList.get(devicesList.size() - 1);
    }

    public String showDeviceListInString() {
        List<Device> list = getDeviceList();
        StringBuilder result = new StringBuilder();
        String element = " - Device: ";
        String typeStr = " | Type: ";
        String statusStr = " | Active: ";
        int number = 1;
        for (Device device : list) {
            result.append(number++);
            result.append(element);
            result.append(device.getDeviceName());
            result.append(typeStr);
            result.append(device.getDeviceSpecs().getDeviceType());
            result.append(statusStr);
            result.append(device.isActive());
            result.append("\n");
        }
        return result.toString();
    }

    public List<Device> getDeviceList() {
        return devicesList;
    }

    public int size() {
        return this.devicesList.size();
    }

    /**
     * Method that call the Java native method to remove an object from the device's list
     *
     * @param device object device that will be removed
     * @return boolean result of the device's removal
     */
    public boolean removeDevice(Device device) {
        return this.devicesList.remove(device);
    }

    /**
     * Method that disable the Device by setting it's status to false (false meaning deactivated)
     *
     * @param device Device to be deactivated
     * @return true result if Device successfully deactivated
     */
    public boolean deactivateDevice(Device device) {
        return device.deactivateDevice();
    }

    /**
     * @return a list of all devices that have isMetered parameter set as true
     */
    List<Device> getMeteredDevices() {
        List<Device> meteredDeviceList = new ArrayList<>();

        for (Device device : devicesList) {
            if (device.isMetered()) {
                meteredDeviceList.add(device);
            }
        }
        return meteredDeviceList;
    }

}

