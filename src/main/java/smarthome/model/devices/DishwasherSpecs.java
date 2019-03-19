package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Double.NaN;


public class DishwasherSpecs implements DeviceSpecs {


    /* ------- Private fields ------- */

    private String deviceType;


    // Changing the following string arrays allows one to quickly develop device specs just by using different parameters.
    // Improvement (?) Create a distinct class for this bit.

    private String[] attributeNames = {"Capacity"};
    private String[] attributeUnits = {"Dish Sets"};

    private HashMap<String, Double> attributeValuesMap = new HashMap<>();
    private HashMap<String, String> attributeUnitsMap = new HashMap<>();

    private List<String> attributeNamesList = new ArrayList<>();

    /* ------- Public fields ------- */

    /* ------ Constructors ------- */

    public DishwasherSpecs(String deviceType) {
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

        int items = this.attributeNames.length;

        for (int i = 0; i < items; i++) {
            attributeNamesList.add(this.attributeNames[i]);
        }

        //TODO is this hashmap clearance necessary? If so how can we test it?
        /*attributeValuesMap.clear();
        attributeUnitsMap.clear();*/

        for (int j = 0; j < items; j++) {


            attributeUnitsMap.put(this.attributeNames[j], this.attributeUnits[j]);
            attributeValuesMap.put(this.attributeNames[j], NaN); // values are not part of the constructor
        }
    }

    /* ------ Interface methods ------- */

    public String getDeviceType() {
        return this.deviceType;
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

    @Override
    public List<Double> getAttributeValues() {
        List<Double> attributeValues = new ArrayList<>();

        for (String key : attributeNamesList
        ) {
            attributeValues.add(attributeValuesMap.get(key));
        }
        return attributeValues;
    }

    @Override
    public List<String> getAttributeUnits() {

        List<String> unitsList = new ArrayList<>();

        for (String key : attributeNamesList
        ) {
            unitsList.add(attributeUnitsMap.get(key));
        }
        return unitsList;
    }

    public void setAttributeUnit(String attribute, String unit) {
        if (attributeUnitsMap.containsKey(attribute)) {
            attributeUnitsMap.replace(attribute, unit);
        }
    }

    public void setAttributeValue(String attribute, double newValue) {
        if (attributeValuesMap.containsKey(attribute)) {
            attributeValuesMap.replace(attribute, newValue);
        }
    }
}