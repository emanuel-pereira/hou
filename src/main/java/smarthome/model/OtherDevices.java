package smarthome.model;

import java.util.ArrayList;
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
        List<String> result = new ArrayList<>();
        String deviceType = "Device Type : " + this.mDeviceType.getTypeString();
        result.add(deviceType);
        return result;
    }

    @Override
    public void setAttributeValue(String attribute, String newValue) {
        return;
    }

    @Override
    public double getEnergyConsumption() {
        return 0;
    }
}
