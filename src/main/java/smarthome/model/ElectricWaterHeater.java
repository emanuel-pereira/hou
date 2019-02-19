package smarthome.model;

import smarthome.model.Validations.Utils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ElectricWaterHeater implements DeviceSpecs{
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mVolumeOfWaterToHeat;
    private double mPerformanceRatio;
    private DeviceType mDeviceType;
    private String volumeOfWater = "Volume of water capacity";
    private String hotWaterTemperature = "Hot water temperature";
    private String coldWaterTemperature = "Cold water temperature";
    private String performanceRatio = "Performance Ratio";
    private String volumeOfWaterToHeat = "Volume of water to heat";

    public ElectricWaterHeater() {
    }

    public ElectricWaterHeater(int volumeOfWater, double hotWaterTemperature, double performanceRatio) {
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mPerformanceRatio = performanceRatio;
        this.mVolumeOfWater = volumeOfWater;
    }

    public void setVolumeOfWater(double newVolumeOfWater) {
        mVolumeOfWater = newVolumeOfWater;
    }

    public void setVolumeOfWaterToHeat(double mVolumeOfWaterToHeat) {
        this.mVolumeOfWaterToHeat = mVolumeOfWaterToHeat;
    }

    public double getVolumeOfWaterToHeat() {
        return mVolumeOfWaterToHeat;
    }

    public double getVolumeOfWater() {
        return mVolumeOfWater;
    }

    public void setHotWaterTemperature(double newHotWaterTemp) {

        mHotWaterTemperature = newHotWaterTemp;
    }

    public double getHotWaterTemperature() {
        return mHotWaterTemperature;
    }

    public void setColdWaterTemperature(double newColdWaterTemp) {
        mColdWaterTemperature = newColdWaterTemp;
    }

    public double getColdWaterTemperature() {
        return mColdWaterTemperature;
    }

    public void setPerformanceRatio(double newPerformanceRatio) {
        mPerformanceRatio = newPerformanceRatio;
    }

    public double getPerformanceRatio() {
        return mPerformanceRatio;
    }

    public String showDeviceSpecsListAttributesInString() {
        StringBuilder result = new StringBuilder();
        result.append("3 - DeviceType : " + this.getDeviceType().getDeviceTypeName());
        result.append("\n");
        result.append("4 - Volume of water capacity (l) : " + this.mVolumeOfWater);
        result.append("\n");
        result.append("5 - Hot water temperature : " + this.mHotWaterTemperature);
        result.append("\n");
        result.append("6 - Cold water temperature : " + this.mColdWaterTemperature);
        result.append("\n");
        result.append("7 - Performance Ratio : " + this.mPerformanceRatio);
        result.append("\n");
        result.append("8 - Volume of water to heat: " + this.mVolumeOfWaterToHeat);
        result.append("\n");
        result.append("9 - Daily Energy Consumption: " + this.getEnergyConsumption() + " KWh");
        result.append("\n");
        return result.toString();
    }

    public DeviceType getDeviceType() {
        return mDeviceType;
    }
    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }

    public List<String> getAttributesNames() {
        List<String> result = new ArrayList<>();
        result.add(volumeOfWater);
        result.add(hotWaterTemperature);
        result.add(coldWaterTemperature);
        result.add(performanceRatio);
        result.add(volumeOfWaterToHeat);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        if (attribute.contains(volumeOfWater))
            setVolumeOfWater(parseDouble(newValue));
        if (attribute.contains(hotWaterTemperature))
            setHotWaterTemperature(parseDouble(newValue));
        if (attribute.contains(coldWaterTemperature))
            setColdWaterTemperature(parseDouble(newValue));
        if (attribute.contains(performanceRatio))
            setPerformanceRatio(parseDouble(newValue));
        if (attribute.contains(volumeOfWaterToHeat))
            setVolumeOfWaterToHeat(parseDouble(newValue));
    }

    public String showDeviceAttributeNamesAndValues() {
        StringBuilder result = new StringBuilder();
        int number=3;
        for (String s : getAttributesNames()) {
            result.append(number);
            result.append(" - ");
            if (s.contains(volumeOfWater))
                result.append(s.concat(" : " + this.getVolumeOfWater()));
            if (s.contains(hotWaterTemperature))
                result.append(s.concat(" : " + this.getHotWaterTemperature()));
            if (s.contains(coldWaterTemperature))
                result.append(s.concat(" : " + this.getColdWaterTemperature()));
            if (s.contains(performanceRatio))
                result.append(s.concat(" : " + this.getPerformanceRatio()));
            if (s.contains(volumeOfWaterToHeat))
                result.append(s.concat(" : " + this.getVolumeOfWaterToHeat()));
            result.append("\n");
            number++;
        }
        return result.toString();
    }

    public double getEnergyConsumption() {
        double energyConsumption = (0.001163 * mVolumeOfWaterToHeat * (mHotWaterTemperature - mColdWaterTemperature) * mPerformanceRatio);
        return Utils.round(energyConsumption, 2);
    }
}