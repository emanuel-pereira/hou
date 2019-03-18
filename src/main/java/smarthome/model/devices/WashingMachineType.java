package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class WashingMachineType implements DeviceType {

    private String deviceType = "Washing Machine";

    /**
     * Get device type
     * @return Washing Machine Type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Create a Device
     * @param deviceName Washing Machine name
     * @param nominalPower Washing Machine nominal power
     * @return A new Washing Machine
     */
    @Override
    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new WashingMachineSpecs(devType);

        return new WashingMachine(deviceName, devSpecs, nominalPower);
    }
}
