package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class DishwasherType implements DeviceType {

    private String deviceType = "Dishwasher";

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new DishwasherSpecs(devType);

        return new Dishwasher(deviceName, devSpecs, nominalPower);
    }
}
