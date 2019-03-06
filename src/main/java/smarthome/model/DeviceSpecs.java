package smarthome.model;


import java.util.Calendar;
import java.util.List;

public interface DeviceSpecs {

    // Suggested constructor includes device type, attribute name list and attribute units.

    /* ---- Getters ---- */
    String getDeviceType();

    List<String> getAttributesNames();

    List<String> getAttributeUnits();

    List<Double> getAttributeValues();

    Double getAttributeValue(String attribute);

    String getAttributeUnit(String attribute);

    double getEnergyConsumption();

    double getEnergyConsumptionInTimeInterval(Calendar beginTime, Calendar endTime);

    /* ---- Setters ---- */
    void setAttributeValue(String attribute, double newValue);

    void setAttributeUnit(String attribute, String unit);


    /* ---- Stuff that shouldn't be here and will be deprecated ---- */

    String showDeviceAttributeNamesAndValues(); // TODO remove this from here after checking what it breaks

    String showDeviceAttributesInString();

}