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
            this.attributeNamesList.add(this.attributeNames[i]);
        }

        this.attributeValuesMap.clear();
        this.attributeUnitsMap.clear();

        for (int j = 0; j < items; j++) {


            this.attributeUnitsMap.put(this.attributeNames[j], this.attributeUnits[j]);
            this.attributeValuesMap.put(this.attributeNames[j], NaN); // values are not part of the constructor
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

        for (String key : this.attributeNamesList
        ) {
            attributeValues.add(this.attributeValuesMap.get(key));
        }
        return attributeValues;
    }

    @Override
    public List<String> getAttributeUnits() {

        List<String> unitsList = new ArrayList<>();

        for (String key : this.attributeNamesList
        ) {
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
            this.attributeValuesMap.replace(attribute, newValue);
        }
    }
}
