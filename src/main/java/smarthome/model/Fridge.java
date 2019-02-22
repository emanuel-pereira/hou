package smarthome.model;

import smarthome.io.ui.UtilsUI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Fridge implements DeviceSpecs {
    public int mFreezerCapacity;
    public int mRefrigeratorCapacity;
    private int mAnnualEnergyConsumption;
    private DeviceType mDeviceType;

    public Fridge() {
    }

    public Fridge(int freezerCapacity, int refrigeratorCapacity, int annualEnergyConsumption) {
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mAnnualEnergyConsumption = annualEnergyConsumption;
    }

    @Override
    public DeviceType getDeviceType() {
        return mDeviceType;
    }

    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }

    public List<String> getAttributesNames() {
        List<String> attributes = new ArrayList<>();
        for (Field field : getAttributesFields()){
            String attributeName = field.getName();
            attributes.add(attributeName);}
        return attributes;
    }

    public Field[] getAttributesFields() {
        Field[] fields = this.getClass().getFields();
        return fields;
    }

    public int getAttributesValues(String attribute) throws IllegalAccessException {
        int i=0;
        int value=0;
        for (String attributeName : getAttributesNames()) {
            if (attribute.matches(attributeName))
                value = getAttributesFields()[i].getInt(this);
            i++;
        }
        return value;
    }

    @Override
    public void setAttributeValue(String attribute, String newValue) throws IllegalAccessException{
        int i=0;
        for (String attributeName : getAttributesNames()) {
            if (attribute.matches(attributeName))
                getAttributesFields()[i].set(this, parseInt(newValue));
            i++;
        }
    }

    public String showDeviceAttributeNamesAndValues() throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        int number = 3;
        for (String attribute : getAttributesNames()) {
            String s = UtilsUI.splitCamelCaseString(attribute);
            result.append(number);
            result.append(" -");
            result.append(s.concat(": " + getAttributesValues(attribute)));
            result.append("\n");
            number++;
        }
        return result.toString();
    }

    @Override
    public double getEnergyConsumption() {
        return (double) mAnnualEnergyConsumption / 365.00;
    }

    public void setFreezerCapacity(int freezerCapacity) {
        mFreezerCapacity = freezerCapacity;
    }

    public int getFreezerCapacity() {
        return mFreezerCapacity;
    }

    public void setRefrigeratorCapacity(int refrigeratorCapacity) {
        mRefrigeratorCapacity = refrigeratorCapacity;
    }

    public int getRefrigeratorCapacity() {
        return mRefrigeratorCapacity;
    }
}

