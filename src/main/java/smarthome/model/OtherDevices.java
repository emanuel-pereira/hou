package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class OtherDevices implements DeviceSpecs {
    private DeviceType mDeviceType;


    public OtherDevices() {
    }

    public DeviceType getDeviceType() {
        return mDeviceType;
    }

    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }

    @Override
    public List<String> getAttributesNames() {
        List<String> result = new ArrayList<>();
        return result;
    }

    @Override
    public void setAttributeValue(String attribute, String newValue) {
    }

    @Override
    public double getEnergyConsumption() {
        return 0;
    }

    public String showDeviceAttributeNamesAndValues() {
        return "";
    }
}