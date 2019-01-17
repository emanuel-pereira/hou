package smarthome.model;

public class ElectricWaterHeater implements DeviceSpecs,Metered {


    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mPerformanceRatio;
    private double mEnergyConsumption;


    public ElectricWaterHeater(double hotWaterTemperature, double performanceRatio) {
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mPerformanceRatio = performanceRatio;
        this.mEnergyConsumption=getEnergyConsumption();
    }

    public void setVolumeOfWater(double newVolumeOfWater) {
        mVolumeOfWater = newVolumeOfWater;
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
        result.append("4 - Volume of water : " + this.mVolumeOfWater);
        result.append("\n");
        result.append("5 - Hot water temperature : " + this.mHotWaterTemperature);
        result.append("\n");
        result.append("6 - Cold water temperature : " + this.mColdWaterTemperature);
        result.append("\n");
        result.append("7 - Performance Ratio : " + this.mPerformanceRatio);
        result.append("\n");
        result.append("8 - Daily Energy Consumption: "+this.getEnergyConsumption()+" KWh");
        result.append("\n");
        return result.toString();
    }

    public double getEnergyConsumption() {
        return mEnergyConsumption=(1.163 * mVolumeOfWater * (mHotWaterTemperature - mColdWaterTemperature) * mPerformanceRatio)*24;
    }
}
