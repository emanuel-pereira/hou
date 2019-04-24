package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class FreezerType implements DeviceType {
    private String deviceType = "Freezer";

    /**
     * @return getter method for deviceType
     */
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Creator method for a device of type Freezer, in which is created an instance of its DeviceSpecs where
     * are defined its specific attributes and then creates an instance of a Freezer
     * @param deviceName name of the device
     * @param nominalPower specific attributes of the device WineCooler
     * @return an instance of a Freezer
     */
    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();
        String[] attributeNames = {"Freezer Capacity","Annual Energy Consumption"};
        String[] attributeUnits = {"liters", "kWh"};

        DeviceSpecs devSpecs = new GenericSpecs(devType, attributeNames, attributeUnits);

        return new Freezer(deviceName, devSpecs, nominalPower);
    }
}
