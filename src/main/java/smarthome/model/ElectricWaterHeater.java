package smarthome.model;

public class ElectricWaterHeater implements DeviceSpecs {


    private DeviceType mDeviceType;
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mPerformanceRatio;
    private double mEnergyConsumption;


    public ElectricWaterHeater(DeviceType deviceType, double volumeOfWater, double hotWaterTemperature, double coldWaterTemperature, double performanceRatio) {
        this.mDeviceType = deviceType;
        this.mVolumeOfWater = volumeOfWater;
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mColdWaterTemperature = coldWaterTemperature;
        this.mPerformanceRatio = performanceRatio;
        this.mEnergyConsumption = 1.163 * volumeOfWater * (hotWaterTemperature - coldWaterTemperature) * performanceRatio; //obter no getEnergyConsumption
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
    public double getHotWaterTemperature(){
        return mHotWaterTemperature;
    }

    public void setColdWaterTemperature(double newColdWaterTemp) {
        mColdWaterTemperature = newColdWaterTemp;
    }
    public double getColdWaterTemperature(){
        return mColdWaterTemperature;
    }

    public void setPerformanceRatio(double newPerformanceRatio) {
        mPerformanceRatio = newPerformanceRatio;
    }
    public double getPerformanceRatio() {
        return mPerformanceRatio;
    }


    @Override
    public String getType() {
        return mDeviceType.getType();
    }

    @Override
    public String getTypeFromIndex(int index) {
        return mDeviceType.getTypeFromIndex(index);
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
        return result.toString();
    }
}
