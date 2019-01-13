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


    @Override
    public String getType() {
        return mDeviceType.getType();
    }

    @Override
    public String getTypeFromIndex(int index) {
        return mDeviceType.getTypeFromIndex(index);
    }
}
