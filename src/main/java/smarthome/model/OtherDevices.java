package smarthome.model;

public class OtherDevices implements DeviceSpecs {

    private DeviceType mDeviceType;

    public OtherDevices(DeviceType deviceType) {
        this.mDeviceType = deviceType;
    }

    @Override
    public String getType() {
        return mDeviceType.getType();
    }

    @Override
    public String getTypeFromIndex(int index) {
        return mDeviceType.getTypeFromIndex(index);
    }

    public String showDeviceSpecsListAttributesInString(){
        return null;
    }
}
