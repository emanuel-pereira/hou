package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class WineCoolerType implements DeviceType {
    private String deviceType = "WineCooler";

    /**
     * @return getter method for deviceType
     */
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Creator method for a device of type Wine Cooler, in which is created an instance of its DeviceSpecs where
     * are defined its specific attributes and then creates an instance of a Wine Cooler give
     * @param deviceName name of the device
     * @param nominalPower specific attributes of the device WineCooler
     * @return an instance of a WineCooler
     */
    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();
        String[] attributeNames = {"Number of bottles","Annual Energy Consumption"};
        String[] attributeUnits = {"bottles","kWh"};

        DeviceSpecs devSpecs = new GenericSpecs(devType, attributeNames, attributeUnits);

        return new WineCooler(deviceName, devSpecs, nominalPower);
    }
}
