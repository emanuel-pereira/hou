package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class FanType implements DeviceType {

    private final String deviceType = "Fan";

    /**
     * Get device type
     *
     * @return Fan Type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
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
