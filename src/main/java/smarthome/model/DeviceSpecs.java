package smarthome.model;


import java.util.List;

public interface DeviceSpecs {

    List<String> getDeviceAttributesInString();

    void setAttributeValue(String attribute, String newValue);
}
