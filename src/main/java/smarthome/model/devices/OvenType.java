package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class OvenType implements DeviceType {

    private static final String DEVICE_TYPE = "Oven";

    @Override
    public String getDeviceType() {
        return DEVICE_TYPE;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new GenericNoSpecs(devType);
        return new Oven(devName,devSpecs,nominalPower);
    }

}
