package smarthome.model;


import java.util.List;

public interface DeviceSpecs {

    // Suggested constructor includes device type, attribute name list and attribute units.

    /* ---- Getters ---- */
    String getDeviceType(); // TODO remove this from here after checking what it breaks

    List<String> getAttributesNames();

    List<String> getAttributeUnits();

    List<Double> getAttributeValues();

    Double getAttributeValue(String attribute);

    String getAttributeUnit(String attribute);

    double getEnergyConsumption();


    /* ---- Setters ---- */

    void setType(String newDeviceType);

    void setAttributeValue(String attribute, double newValue);

    void setAttributeNames(String[] attributeNames);

    void setAttributeUnit(String attribute, String unit);


    /* ---- Stuff that shouldn't be here and will be deprecated ---- */

    String showDeviceAttributeNamesAndValues() throws IllegalAccessException; // TODO remove this from here after checking what it breaks


}