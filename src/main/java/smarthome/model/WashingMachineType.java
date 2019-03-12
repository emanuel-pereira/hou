package smarthome.model;

public class WashingMachineType implements DeviceType {

    private String deviceType = "Washing Machine";

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new WashingMachineSpecs(devType);

        return new WashingMachine(deviceName, devSpecs, nominalPower);    }
}
