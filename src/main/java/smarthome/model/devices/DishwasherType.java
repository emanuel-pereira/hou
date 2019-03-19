package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class DishwasherType implements DeviceType {

    private String deviceType = "Dishwasher";

    /**
     * Get device type
     * @return Dishwasher Type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Create a Device
     * @param deviceName Dishwasher name
     * @param nominalPower Dishwasher nominal power
     * @return A new Dishwasher
     */
    @Override
    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new WashingMachineSpecs(devType);

        return new WashingMachine(deviceName, devSpecs, nominalPower);
    }
}
