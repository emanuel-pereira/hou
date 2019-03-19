package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class MicrowaveOvenType implements DeviceType {

    private String deviceType = "MicrowaveOven";

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new OvenSpecs(devType);
        return new MicrowaveOven(devName,devSpecs,nominalPower);
    }

}
