package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class FanType implements DeviceType {

    private static final String DEVICE_TYPE = "Fan";

    /**
     * Get device type
     *
     * @return Fan Type
     */
    @Override
    public String getDeviceType() {
        return DEVICE_TYPE;
    }

    /**
     * Create a Device
     *
     * @param deviceName   Fan name
     * @param nominalPower Fan nominal power
     * @return A new Fan
     */
    @Override
    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType ();

        DeviceSpecs devSpecs = new GenericNoSpecs(devType);

        return new Fan (deviceName, devSpecs, nominalPower);
    }
}
