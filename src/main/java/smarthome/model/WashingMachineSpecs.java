package smarthome.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Double.NaN;

public class WashingMachineSpecs implements DeviceSpecs {

    private String deviceType;

    private String[] attributeNames = {"Capacity"};
    private String[] attributeUnits = {"L"};

    private HashMap<String, Double> attributeValuesMap = new HashMap<> ();
    private HashMap<String, String> attributeUnitsMap = new HashMap<> ();

    private List<String> attributeNamesList = new ArrayList<> ();

    public WashingMachineSpecs(String deviceType) {
        this.deviceType = deviceType;
        initializeClass ();
    }

    private void initializeClass() {
        int items = this.attributeNames.length;
        attributeNamesList.addAll (Arrays.asList (this.attributeNames));
        for (int j = 0; j < items; j++) {
            attributeUnitsMap.put (this.attributeNames[j], this.attributeUnits[j]);
            attributeValuesMap.put (this.attributeNames[j], NaN);
        }
    }

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public List<String> getAttributesNames() {
        return this.attributeNamesList;
    }

    @Override
    public List<Double> getAttributeValues() {
        List<Double> attributeValues = new ArrayList<> ();
        for (String key : attributeNamesList
        ) {
            attributeValues.add (attributeValuesMap.get (key));
        }
        return attributeValues;
    }

    @Override
    public List<String> getAttributeUnits() {
        List<String> unitsList = new ArrayList<> ();
        for (String key : attributeNamesList
        ) {
            unitsList.add (attributeUnitsMap.get (key));
        }
        return unitsList;
    }

    @Override
    public Double getAttributeValue(String attribute) {
        return this.attributeValuesMap.get (attribute);
    }

    @Override
    public String getAttributeUnit(String attribute) {
        return this.attributeUnitsMap.get (attribute);
    }

    @Override
    public void setAttributeValue(String attribute, double newValue) {
        if (attributeValuesMap.containsKey (attribute)) {
            attributeValuesMap.replace (attribute, newValue);
        }
    }

    @Override
    public void setAttributeUnit(String attribute, String unit) {
        if (attributeValuesMap.containsKey (attribute)) {
            attributeUnitsMap.put (attribute, unit);
        }
    }
}
