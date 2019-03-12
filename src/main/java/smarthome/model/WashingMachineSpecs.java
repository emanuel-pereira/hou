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

    /**
     * Washing Machine constructor
     * @param deviceType Device type
     */
    public WashingMachineSpecs(String deviceType) {
        this.deviceType = deviceType;
        initializeClass ();
    }

    /**
     * Private initialization method of the constructor(s). Copies the string arrays containing the attributes names and units
     * to the internal Map representation of them.
     */
    private void initializeClass() {
        int items = this.attributeNames.length;
        attributeNamesList.addAll (Arrays.asList (this.attributeNames));
        for (int j = 0; j < items; j++) {
            attributeUnitsMap.put (this.attributeNames[j], this.attributeUnits[j]);
            attributeValuesMap.put (this.attributeNames[j], NaN);
        }
    }

    /**
     * Get device type
     * @return Device type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Get all the attribute names
     * @return List of attribute names
     */
    @Override
    public List<String> getAttributesNames() {
        return this.attributeNamesList;
    }

    /**
     * Get all the attribute values
     * @return List of attribute values
     */
    @Override
    public List<Double> getAttributeValues() {
        List<Double> attributeValues = new ArrayList<> ();
        for (String key : attributeNamesList
        ) {
            attributeValues.add (attributeValuesMap.get (key));
        }
        return attributeValues;
    }

    /**
     * Get all the attribute units
     * @return List of attribute units
     */
    @Override
    public List<String> getAttributeUnits() {
        List<String> unitsList = new ArrayList<> ();
        for (String key : attributeNamesList
        ) {
            unitsList.add (attributeUnitsMap.get (key));
        }
        return unitsList;
    }

    /**
     * Get a specific attribute value
     * @param attribute Attribute name associated to the value
     * @return Attribute value
     */
    @Override
    public Double getAttributeValue(String attribute) {
        return this.attributeValuesMap.get (attribute);
    }

    /**
     * Get a specific attribute unit
     * @param attribute Attribute name associated to the unit
     * @return Attribute unit
     */
    @Override
    public String getAttributeUnit(String attribute) {
        return this.attributeUnitsMap.get (attribute);
    }

    /**
     * Set a specific attribute value
     * @param attribute Attribute name associated to the value
     * @param newValue New attribute value
     */
    @Override
    public void setAttributeValue(String attribute, double newValue) {
        if (attributeValuesMap.containsKey (attribute)) {
            attributeValuesMap.replace (attribute, newValue);
        }
    }

    /**
     * Set a specific attribute unit
     * @param attribute Attribute name associated to the unit
     * @param unit New attribute unit
     */
    @Override
    public void setAttributeUnit(String attribute, String unit) {
        if (attributeValuesMap.containsKey (attribute)) {
            attributeUnitsMap.put (attribute, unit);
        }
    }

}
