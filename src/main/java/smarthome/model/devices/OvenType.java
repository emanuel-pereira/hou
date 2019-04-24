package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class OvenType implements DeviceType {

    private final String deviceType = "Oven";

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new GenericNoSpecs(devType);
        return new Oven(devName,devSpecs,nominalPower);
    }

}
