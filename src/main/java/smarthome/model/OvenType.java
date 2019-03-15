package smarthome.model;

public class OvenType implements DeviceType {

    private String deviceType = "Oven";

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new OvenSpecs(devType);
        return new Oven(devName,devSpecs,nominalPower);
    }

}
