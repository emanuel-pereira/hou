package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class WallTowelHeaterType implements DeviceType {

    private String deviceType = "WallTowelHeater";

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new WallTowelHeaterSpecs(devType);
        return new WallTowelHeater(devName,devSpecs,nominalPower);
    }
}
