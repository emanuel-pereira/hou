package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

public class Tv extends GenericDevice {

    public Tv(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {
        super(deviceName, deviceSpecs, nominalPower);
    }
}