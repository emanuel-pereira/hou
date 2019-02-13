package smarthome.model;


import java.util.List;

public interface DeviceSpecs {

    DeviceType getDeviceType();

    void setType(DeviceType deviceType);

    List <String> getAttributesNames();

    void setAttributeValue(String attribute, String newValue);

    String showDeviceAttributeNamesAndValues();

    double getEnergyConsumption();
}