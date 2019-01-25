package smarthome.model;


import java.util.List;

public interface DeviceSpecs {
//mandatórios:
    DeviceType getType();
    /*getAttributeNames();*/
    /*getAttributeValue();*/

    List<String> getDeviceAttributesInString();

    void setAttributeValue(String attribute, String newValue);
    double getEnergyConsumption();
}
