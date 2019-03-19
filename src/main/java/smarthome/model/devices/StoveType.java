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
        DeviceSpecs deviceSpecs = (DeviceSpecs) new StoveSpecs(devType);
        return new Stove(deviceName, deviceSpecs, nominalPower);
    }
}
