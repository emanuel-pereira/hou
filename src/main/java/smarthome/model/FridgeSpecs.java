package smarthome.model;

import java.util.*;

import static java.lang.Double.NaN;


/**
 * This is a "template" class for classes that implement DeviceSpecs and should be appropriate for generic devices, i.e.,
 * those that don't require a very specific interface implementation. Most devices should fall in this category, with
 * specific energy consumption calculations the only thing setting them apart.
 */

public class FridgeSpecs implements DeviceSpecs {


    /* ------- Private fields ------- */

    private String deviceType;

    public boolean isInitialized; // Maybe?...

    private String[] attributeNames = {"Freezer Capacity", "Refrigerator Capacity", "Annual Energy Consumption"};
    private String[] attributeUnits = {"liters", "liters", "kWh"};

    private HashMap<String, Double> attributeValuesMap;
    private HashMap<String, String> attributeUnitsMap;

    private List<String> attributeNamesList = new ArrayList<>();

    private double energyConsumption;

    /* ------- Public fields ------- */

    // No public fields.

    /* ------ Constructors ------- */

    public FridgeSpecs(String deviceType) {
       this.deviceType = deviceType;
        initializeClass();
    }

    /**
     * Private initialization method of the constructor(s). Copies the string arrays containing the attributes names and units
     * to the internal Map representation of them. This is a design choice, as Maps are more easily managed than
     * arrays of strings and because this allows one to create an instance of the class using the device type only and
     * deferring the setting of the attributes to later. The isInitialized field is used to quickly assert if the
     * instance is valid or not.
     */
    private void initializeClass() {

        if (this.attributeNames.length == 0
                || this.attributeUnits.length == 0) {
            this.isInitialized = false;
            return;
        }

        int items = this.attributeNames.length;
        if (this.attributeUnits.length < items) {
            items = this.attributeUnits.length;
        }


        for (int i = 0; i < items; i++) {

            this.attributeUnitsMap.put(this.attributeNames[i], this.attributeUnits[i]);
            this.attributeValuesMap.put(this.attributeNames[i], NaN); // values are not part of the constructor
        }

        this.isInitialized = true;
    }

    /* ------ Interface methods ------- */

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setAttributeNames(String[] attributeNames) {

        attributeValuesMap.clear();
        attributeUnitsMap.clear();

        for (int i = 0; i < attributeNames.length; i++) {
            attributeNamesList.add(attributeNames[i]);
            attributeValuesMap.put(attributeNames[i], NaN);
            attributeUnitsMap.put(attributeNames[i], null);
        }
    }

    public void setType(String deviceType) {
        this.deviceType = deviceType;
    }

    public List<String> getAttributesNames() {
        return this.attributeNamesList;
    }

    public void setAttributeUnit(String attribute, String unit) {
        if (attributeValuesMap.containsKey(attribute)) {
            attributeUnitsMap.put(attribute, unit);
        }
    }

    public void setAttributeValue(String attribute, double newValue) {
        if (attributeValuesMap.containsKey(attribute)) {
            attributeValuesMap.replace(attribute, newValue);
        }
    }

    public Double getAttributeValue(String attribute) {
        return this.attributeValuesMap.get(attribute);
    }

    public List<Double> getAttributeValues() {
        List<Double> attributeValues = new ArrayList<>();

        for (String s : attributeNamesList
        ) {
            attributeValues.add(attributeValuesMap.get(s));
        }
        return attributeValues;
    }

    public String getAttributeUnit(String attribute) {
        return this.attributeUnitsMap.get(attribute);
    }

    public List<String> getAttributeUnits() {

        List<String> unitsList = new ArrayList<>();

        for (String key : attributeUnitsMap.keySet()
        ) {
            unitsList.add(attributeUnitsMap.get(key));
        }
        return unitsList;
    }

    public String showDeviceAttributeNamesAndValues() {
        return "";
    }


    public double getEnergyConsumption() {


        return this.energyConsumption;
    }

    /* ------ Other methods ------- */

}

