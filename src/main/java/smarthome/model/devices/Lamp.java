package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

public class Lamp extends GenericDevice  {

    Lamp(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {
        super(deviceName, deviceSpecs, nominalPower);
    }
}

