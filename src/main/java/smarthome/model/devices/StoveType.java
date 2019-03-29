package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class StoveType implements DeviceType {

    private String deviceType = "Stove";

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {

        String devType = getDeviceType();

        String[] attributeNames = {"capacity"};
        String[] attributeUnits = {"Kw/h"};

        DeviceSpecs deviceSpecs = new GenericSpecs(devType,attributeNames,attributeUnits);
        return new Stove(deviceName, deviceSpecs, nominalPower);
    }
}
