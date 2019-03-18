package smarthome.model.devices;

import java.util.ArrayList;
import java.util.List;
import smarthome.model.DeviceSpecs;

import static java.lang.Double.NaN;

public class MicrowaveOvenSpecs implements DeviceSpecs {

    private String deviceType;


    public MicrowaveOvenSpecs(String deviceType){
        this.deviceType = deviceType;
    }

    @Override
    public String getDeviceType() {
        return this.deviceType;
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
