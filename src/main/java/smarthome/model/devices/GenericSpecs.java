package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Double.NaN;


/**
 * This is a "template" class for classes that implement DeviceSpecs and should be appropriate for generic devices, i.e.,
 * those that don't require a very specific interface implementation. Most devices should fall in this category, with
 * specific energy consumption calculations the only thing setting them apart.
 */

public class GenericSpecs implements DeviceSpecs {


    /* ------- Private fields ------- */

    private final String[] attributeNames;
    private final String[] attributeUnits;
    private final String deviceTypeDesignation;

    private final List<String> attributeNamesList = new ArrayList<>();

    private final HashMap<String, Double> attributeValuesMap = new HashMap<>();
    private final HashMap<String, String> attributeUnitsMap = new HashMap<>();

    /* ------- Public fields ------- */

    //Nothing to see here!

    /* ------ Constructors ------- */

    public GenericSpecs(String deviceTypeDesignation, String[] specsList, String[] specsUnitsList) {

        this.deviceTypeDesignation = deviceTypeDesignation;
        this.attributeNames = specsList;
        this.attributeUnits = specsUnitsList;

        initializeClass();
    }

    /**
     * Private initialization method of the constructor(s). Copies the string arrays containing the attributes names and units
     * to the internal Map representation of them. This is a design choice, as Maps are more easily managed than
     * arrays of strings and because this allows one to create an instance of the class using the device type only and
     * deferring the setting of the attributes to later if needed.
     */
    private void initializeClass() {

        int items = this.attributeNames.length;

        for (int i = 0; i < items; i++) {
            this.attributeNamesList.add(this.attributeNames[i]);
            this.attributeUnitsMap.put(this.attributeNames[i], this.attributeUnits[i]);
            this.attributeValuesMap.put(this.attributeNames[i], NaN); // values are not part of the constructor

        }
    }

    /* ------ Interface methods ------- */

    public String getDeviceType() {
        return this.deviceTypeDesignation;
    }

    public Double getAttributeValue(String attribute) {
        return this.attributeValuesMap.get(attribute);
    }

    public String getAttributeUnit(String attribute) {
        return this.attributeUnitsMap.get(attribute);
    }

    public List<String> getAttributesNames() {
        return this.attributeNamesList;
    }

    public List<Double> getAttributeValues() {
        List<Double> attributeValues = new ArrayList<>();

        for (String key : this.attributeNamesList) {
            attributeValues.add(this.attributeValuesMap.get(key));
        }
        return attributeValues;
    }

    public List<String> getAttributeUnits() {

        List<String> unitsList = new ArrayList<>();

        for (String key : this.attributeNamesList) {
            unitsList.add(this.attributeUnitsMap.get(key));
        }
        return unitsList;
    }

    public void setAttributeUnit(String attribute, String unit) {
        if (this.attributeValuesMap.containsKey(attribute)) {
            this.attributeUnitsMap.put(attribute, unit);
        }
    }

    public void setAttributeValue(String attribute, double newValue) {
        if (this.attributeValuesMap.containsKey(attribute)) {
            this.attributeValuesMap.put(attribute, newValue);
        }
    }
}