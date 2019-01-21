package smarthome.model;

import java.util.List;

public class OtherDevices implements DeviceSpecs {
    private DeviceType mDeviceType;

    public OtherDevices(DeviceType inputDeviceType){
        mDeviceType=inputDeviceType;
    }

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }

    @Override
    public List<String> getDeviceAttributesInString() {
        return null;
    }

    @Override
    public void setAttributeValue(String attribute, String newValue) {
        return;
    }
}
