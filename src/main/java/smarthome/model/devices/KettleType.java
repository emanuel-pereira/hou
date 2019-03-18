package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class KettleType implements DeviceType {

    private String deviceType = "Kettle";

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new KettleSpecs(devType);

        return new Kettle(deviceName, devSpecs, nominalPower);
    }
}
