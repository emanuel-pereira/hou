package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class MicrowaveOvenType implements DeviceType {

    private static final String DEVICE_TYPE = "MicrowaveOven";

    @Override
    public String getDeviceType() {
        return DEVICE_TYPE;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new GenericNoSpecs(devType);
        return new MicrowaveOven(devName,devSpecs,nominalPower);
    }

}
