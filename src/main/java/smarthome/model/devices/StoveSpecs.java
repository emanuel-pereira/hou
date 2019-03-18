package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Double.NaN;


public class StoveSpecs implements DeviceSpecs {


    private String deviceType;

    private String[] attributeNames = {"capacity"};
    private String[] attributeUnits = {"Kw/h"};

    private HashMap<String, Double> attributeValuesMap = new HashMap<>();
    private HashMap<String, String> attributeUnitsMap = new HashMap<>();
    private List<String> attributeNamesList = new ArrayList<>();


    public StoveSpecs(String deviceType) {

        this.deviceType = deviceType;
        initializeClass();
    }

    private void initializeClass() {

        int items = this.attributeNames.length;

        for (int i = 0; i < items; i++) {
            attributeNamesList.add(this.attributeNames[i]);
        }

        attributeValuesMap.clear();
        attributeUnitsMap.clear();

        for (int j = 0; j < items; j++) {


            attributeUnitsMap.put(this.attributeNames[j], this.attributeUnits[j]);
            attributeValuesMap.put(this.attributeNames[j], NaN); // values are not part of the constructor
        }
    }

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
        if (attributeValuesMap.containsKey(attribute)) {
            attributeUnitsMap.put(attribute, unit);
        }
    }

    public void setAttributeValue(String attribute, double newValue) {
        if (attributeValuesMap.containsKey(attribute)) {
            attributeValuesMap.replace(attribute, newValue);
        }
    }
}
