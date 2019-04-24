package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class FridgeType implements DeviceType {

    private final String deviceType = "Fridge"; // The device type can be anything -- fridge, water heater, etc. Use as identifier.

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {

        String devType = getDeviceType();

        String[] attributeNames = {"Freezer Capacity", "Refrigerator Capacity", "Annual Energy Consumption"};
        String[] attributeUnits = {"liters", "liters", "kWh"};

        DeviceSpecs devSpecs = new GenericSpecs(devType, attributeNames, attributeUnits);

        return new Fridge(deviceName, devSpecs, nominalPower);
    }
}
