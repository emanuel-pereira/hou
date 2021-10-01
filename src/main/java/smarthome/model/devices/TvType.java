package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class TvType implements DeviceType {

    private static final String DEVICE_TYPE = "Tv";

    public String getDeviceType() {
        return DEVICE_TYPE;
    }

    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new GenericNoSpecs(devType);

        return new Tv(deviceName, devSpecs, nominalPower);
    }
}
