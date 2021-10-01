package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class KettleType implements DeviceType {

    private static final String DEVICE_TYPE = "Kettle";

    public String getDeviceType() {
        return DEVICE_TYPE;
    }

    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();
        String[] attributeNames = {"Capacity"};
        String[] attributeUnits = {"l"};

        DeviceSpecs devSpecs = new GenericSpecs(devType, attributeNames, attributeUnits);

        return new Kettle(deviceName, devSpecs, nominalPower);
    }
}
