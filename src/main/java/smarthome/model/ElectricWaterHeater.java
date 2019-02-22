package smarthome.model;

import smarthome.model.validations.Utils;

import java.util.List;

import static java.lang.Double.parseDouble;

public class ElectricWaterHeater implements DeviceSpecs {
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mVolumeOfWaterToHeat;
    private double mPerformanceRatio;
    private DeviceType mDeviceType;

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

   /** public String showDeviceSpecsListAttributesInString() {
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
    */

    public DeviceType getDeviceType() {
        return mDeviceType;
    }

    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }

    public List<String> getAttributesNames() {
        String classNameString = this.getClass().getSimpleName();
        return Configuration.getDeviceSpecsAttributes(classNameString);
    }

    public double getAttributesValues(String attribute) {
        double value=0;
        switch (attribute) {
            case "Volume of water capacity":
                value = getVolumeOfWater();
                break;
            case "Hot water temperature":
                value = getHotWaterTemperature();
                break;
            case "Cold water temperature":
                value = getColdWaterTemperature();
                break;
            case "Performance Ratio":
                value = getPerformanceRatio();
                break;
            case "Volume of water to heat":
                value = getVolumeOfWaterToHeat();
                break;
        }
        return value;
    }

    public void setAttributeValue(String attribute, String newValue) {
        switch (attribute) {
            case "Volume of water capacity":
                setVolumeOfWater(parseDouble(newValue));
                break;
            case "Hot water temperature":
                setHotWaterTemperature(parseDouble(newValue));
                break;
            case "Cold water temperature":
                setColdWaterTemperature(parseDouble(newValue));
                break;
            case "Performance Ratio":
                setPerformanceRatio(parseDouble(newValue));
                break;
            case "Volume of water to heat":
                setVolumeOfWaterToHeat(parseDouble(newValue));
                break;
        }
    }

    public String showDeviceAttributeNamesAndValues() {
        StringBuilder result = new StringBuilder();
        int number = 3;
        for (String s : getAttributesNames()) {
            result.append(number);
            result.append(" - ");
            result.append(s.concat(" : " + getAttributesValues(s)));
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