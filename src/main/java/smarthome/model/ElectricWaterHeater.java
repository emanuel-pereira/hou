package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ElectricWaterHeater implements DeviceSpecs,Metered {


    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mVolumeOfWaterToHeat;
    private double mPerformanceRatio;
    private double mEnergyConsumption;


    public ElectricWaterHeater(int volumeOfWater, double hotWaterTemperature, double performanceRatio) {
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mPerformanceRatio = performanceRatio;
        this.mVolumeOfWater=volumeOfWater;
        this.mEnergyConsumption=getEnergyConsumption();
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

    public void setColdWaterTemperature(double newColdWaterTemp)
    {
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
        result.append("4 - Volume of water capacity (l) : " + this.mVolumeOfWater);
        result.append("\n");
        result.append("5 - Hot water temperature : " + this.mHotWaterTemperature);
        result.append("\n");
        result.append("6 - Cold water temperature : " + this.mColdWaterTemperature);
        result.append("\n");
        result.append("7 - Performance Ratio : " + this.mPerformanceRatio);
        result.append("\n");
        result.append("8 - Volume of water to heat: "+this.mVolumeOfWaterToHeat);
        result.append("\n");
        result.append("9 - Daily Energy Consumption: "+this.getEnergyConsumption()+" KWh");
        result.append("\n");
        return result.toString();
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String VolumeOfWater = "4 - Volume of water capacity (l): " + this.mVolumeOfWater;
        String HotWaterTemperature = "5 - Hot water temperature : " + this.mHotWaterTemperature;
        String ColdWaterTemperature = "6 - Cold Water temperature : " + this.mColdWaterTemperature;
        String PerformanceRatio = "7 - Performance Ratio : " + this.mPerformanceRatio;
        String VolumeOfWaterToHeat = "8 - Volume of water to heat : " + this.mVolumeOfWaterToHeat;
        String DailyEnergyConsumption = "9 - Daily Energy Consumption : "+this.getEnergyConsumption()+" KWh";
        result.add(VolumeOfWater);
        result.add(HotWaterTemperature);
        result.add(ColdWaterTemperature);
        result.add(PerformanceRatio);
        result.add(VolumeOfWaterToHeat);
        result.add(DailyEnergyConsumption);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String VolumeOfWater = "4 - Volume of water : " + this.mVolumeOfWater;
        String HotWaterTemperature = "5 - Hot water temperature : " + this.mHotWaterTemperature;
        String ColdWaterTemperature = "6 - Cold Water temperature : " + this.mColdWaterTemperature;
        String PerformanceRatio = "7 - Performance Ratio : " + this.mPerformanceRatio;
        String VolumeOfWaterToHeat = "8 - Volume of water to heat : " + this.mVolumeOfWaterToHeat;
        if (attribute.equals(VolumeOfWater))
            setVolumeOfWater(parseDouble(newValue));
        if (attribute.equals(HotWaterTemperature))
            setHotWaterTemperature(parseDouble(newValue));
        if (attribute.equals(ColdWaterTemperature))
            setColdWaterTemperature(parseDouble(newValue));
        if (attribute.equals(PerformanceRatio))
            setPerformanceRatio(parseDouble(newValue));
        if (attribute.equals(VolumeOfWaterToHeat))
            setVolumeOfWaterToHeat(parseDouble(newValue));
    }

    public double getEnergyConsumption() {
        return mEnergyConsumption=(0.01163 * mVolumeOfWaterToHeat * (mHotWaterTemperature - mColdWaterTemperature) * mPerformanceRatio)*24;
    }
}