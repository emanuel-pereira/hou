package smarthome.model;

public class FridgeType implements DeviceType {

    private String deviceType = "Fridge";
    private String path = "smarthome.model."; // TODO: this should be removed from here. The path should be returned from the Configuration class


    public String getDeviceType() {
        return this.deviceType;
    }


    public Device createDevice() {

        DeviceSpecs aFridgeSpecs = (DeviceSpecs) new FridgeSpecs(deviceType);

         Device aFridge = (Device) new Fridge(aFridgeSpecs);

        return
    }
}
