package smarthome.model;

public class StoveType implements DeviceType {

    private String deviceType = "Stove";

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {

        String devType = getDeviceType();
        DeviceSpecs deviceSpecs = new StoveSpecs(devType);
        return new Stove(deviceName, deviceSpecs, nominalPower);
    }
}
