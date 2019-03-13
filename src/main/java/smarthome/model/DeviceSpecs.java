package smarthome.model;

import java.util.List;

public interface DeviceSpecs {

    // Suggested constructor includes device type, attribute name list and attribute units.

    /* ---- Getters ---- */
    String getDeviceType();

    List<String> getAttributesNames();

    List<Double> getAttributeValues();

    List<String> getAttributeUnits();

    Double getAttributeValue(String attribute);

    String getAttributeUnit(String attribute);

    /* ---- Setters ---- */
    void setAttributeValue(String attribute, double newValue);

    void setAttributeUnit(String attribute, String unit);
}