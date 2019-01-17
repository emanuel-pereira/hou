package smarthome.model;

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

    public double getEnergyConsumption() {
        return mEnergyConsumption=(0.01163 * mVolumeOfWaterToHeat * (mHotWaterTemperature - mColdWaterTemperature) * mPerformanceRatio)*24;
    }
}
