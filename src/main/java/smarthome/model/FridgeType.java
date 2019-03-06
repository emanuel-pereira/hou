package smarthome.model;

public class FridgeType implements DeviceType {

    private String deviceType = "Fridge"; // The device type can be anything -- fridge, water heater, etc. Use as identifier.


    private String path = "smarthome.model."; // TODO: this should be removed from here. The path should be returned from the Configuration class

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {

        String devType=getDeviceType();

        DeviceSpecs devSpecs = new FridgeSpecs(devType);

        return new Fridge(deviceName, devSpecs, nominalPower);
    }
}
