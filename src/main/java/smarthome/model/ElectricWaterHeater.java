package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ElectricWaterHeater implements DeviceSpecs, Metered{
    private DeviceType mDeviceType;
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mVolumeOfWaterToHeat;
    private double mPerformanceRatio;


    public ElectricWaterHeater(DeviceType deviceType, int volumeOfWater, double hotWaterTemperature, double performanceRatio) {
        this.mDeviceType = deviceType;
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
        result.append("3 - DeviceType : " + this.mDeviceType.getTypeString());
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

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String deviceType = "3 - Device Type : " + this.mDeviceType.getTypeString();
        String volumeOfWater = "4 - Volume of water capacity (l): " + this.mVolumeOfWater;
        String hotWaterTemperature = "5 - Hot water temperature : " + this.mHotWaterTemperature;
        String coldWaterTemperature = "6 - Cold Water temperature : " + this.mColdWaterTemperature;
        String performanceRatio = "7 - Performance Ratio : " + this.mPerformanceRatio;
        String volumeOfWaterToHeat = "8 - Volume of water to heat : " + this.mVolumeOfWaterToHeat;
        String dailyEnergyConsumption = "9 - Daily Energy Consumption : " + this.getEnergyConsumption() + " KWh";
        result.add(deviceType);
        result.add(volumeOfWater);
        result.add(hotWaterTemperature);
        result.add(coldWaterTemperature);
        result.add(performanceRatio);
        result.add(volumeOfWaterToHeat);
        result.add(dailyEnergyConsumption);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String volumeOfWater = "volumeOfWater";
        String hotWaterTemperature = "hotWaterTemperature";
        String coldWaterTemperature = "coldWaterTemperature";
        String performanceRatio = "performanceRatio";
        String volumeOfWaterToHeat = "volumeOfWaterToHeat";
        if (attribute.equals(volumeOfWater))
            setVolumeOfWater(parseDouble(newValue));
        if (attribute.equals(hotWaterTemperature))
            setHotWaterTemperature(parseDouble(newValue));
        if (attribute.equals(coldWaterTemperature))
            setColdWaterTemperature(parseDouble(newValue));
        if (attribute.equals(performanceRatio))
            setPerformanceRatio(parseDouble(newValue));
        if (attribute.equals(volumeOfWaterToHeat))
            setVolumeOfWaterToHeat(parseDouble(newValue));
    }

    public double getEnergyConsumption() {
        double energyConsumption = (0.001163 * mVolumeOfWaterToHeat * (mHotWaterTemperature - mColdWaterTemperature) * mPerformanceRatio);
        return Utils.round(energyConsumption, 2);
    }
}