package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

public class FanSpecs implements DeviceSpecs {

    private String deviceType;

    /**
     * Fan constructor
     *
     * @param deviceType Device type
     */
    public FanSpecs(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Get device type
     *
     * @return Device type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Get all the attribute names
     *
     * @return List of attribute names
     */
    @Override
    public List<String> getAttributesNames() {
        return new ArrayList<> ();
    }

    /**
     * Get all the attribute values
     *
     * @return List of attribute values
     */
    @Override
    public List<Double> getAttributeValues() {
        return new ArrayList<> ();

    }

    /**
     * Get all the attribute units
     *
     * @return List of attribute units
     */
    @Override
    public List<String> getAttributeUnits() {
        return new ArrayList<> ();

    }

    /**
     * Get a specific attribute value
     *
     * @param attribute Attribute name associated to the value
     * @return Attribute value
     */
    @Override
    public Double getAttributeValue(String attribute) {
        return NaN;
    }

    /**
     * Get a specific attribute unit
     *
     * @param attribute Attribute name associated to the unit
     * @return Attribute unit
     */
    @Override
    public String getAttributeUnit(String attribute) {
        return null;
    }

    /**
     * Set a specific attribute value
     *
     * @param attribute Attribute name associated to the value
     * @param newValue  New attribute value
     */
    @Override
    public void setAttributeValue(String attribute, double newValue) {
        //This device doesn't have attributes
    }

    /**
     * Set a specific attribute unit
     *
     * @param attribute Attribute name associated to the unit
     * @param unit      New attribute unit
     */
    @Override
    public void setAttributeUnit(String attribute, String unit) {
        //This device doesn't have attributes
    }

}
