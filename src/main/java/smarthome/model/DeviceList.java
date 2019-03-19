package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceList {
    private List<Device> devicesList;

    /**
     * Constructor initializing an empty device list.
     */
    public DeviceList() {
        this.devicesList = new ArrayList<>();
    }

    /**
     * Method to add a new device instance to a DeviceList
     *
     * @param device instance to be added to the device list
     * @return boolean result of the device addition
     */
    public boolean addDevice(Device device) {
        if (!this.devicesList.contains(device)) {
            this.devicesList.add(device);
            return true;
        } else return false;
    }

    /**
     * @param deviceName   name given to the device by the user, e.g. "Cool Plasma TV 9000"
     * @param deviceType   a string containing the designation of a given device type e.g. "Fridge". The device list is
     * @param nominalPower the nominal power of the device (in kW).
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Device newDevice(String deviceName, String deviceType, double nominalPower) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String path = "smarthome.model.devices."; // Maybe this should be removed and the path returned from the Configuration class and "injected" here.

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
        return this.devicesList.get(index);
    }


    public List<String> showDeviceListInString() {
        List<Device> list = getDeviceList();
        List<String> result = new ArrayList<>();
        String active = "[Active]";
        String notActive = "[Not active]";
        StringBuilder sb = new StringBuilder();

        for (Device device : list) {
            sb.append(device.getDeviceSpecs().getDeviceType());
            sb.append(" (");
            sb.append(device.getName());
            sb.append(") ");
            String fragment = device.isActive() ? active : notActive;
            sb.append(fragment);
            result.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return result;
    }

    public List<Device> getDeviceList() {
        return this.devicesList;
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
    List<Metered> getMeteredDevices() {
        List<Metered> meteredDeviceList = new ArrayList<>();

        for (Device device : this.devicesList) {
            Metered meteredDevice = (Metered) device;
            meteredDeviceList.add(meteredDevice);
        }
        return meteredDeviceList;
    }

}

