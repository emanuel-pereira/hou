package smarthome.model;

public class TvType implements DeviceType {

    private String deviceType = "Tv";

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new TvSpecs(devType);

        return new Tv(deviceName, devSpecs, nominalPower);
    }
}
