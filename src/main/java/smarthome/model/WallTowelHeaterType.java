package smarthome.model;

public class WallTowelHeaterType implements DeviceType {

    private String deviceType = "WallTowelHeater";

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new WallTowelHeaterSpecs(devType);
        return new WallTowelHeater(devName,devSpecs,nominalPower);
    }
}
