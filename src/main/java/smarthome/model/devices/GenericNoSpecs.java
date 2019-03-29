package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

public class GenericNoSpecs implements DeviceSpecs {

    private String deviceTypeDesignation;

    public GenericNoSpecs(String deviceType){
        this.deviceTypeDesignation = deviceType;
    }

    @Override
    public String getDeviceType() {
        return this.deviceTypeDesignation;
    }

    @Override
    public List<String> getAttributesNames() {
        return new ArrayList<>();
    }

    @Override
    public List<Double> getAttributeValues() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getAttributeUnits() {
        return new ArrayList<>();
    }

    @Override
    public Double getAttributeValue(String attribute) {
        return NaN;
    }

    @Override
    public String getAttributeUnit(String attribute) {
        return null;
    }

    @Override
    public void setAttributeValue(String attribute, double newValue) {
        //This Device has no attributes
    }

    @Override
    public void setAttributeUnit(String attribute, String unit) {
        //This Device has no attributes
    }

}
