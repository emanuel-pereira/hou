package smarthome.model;

public class Fridge implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mFreezerCapacity;
    private int mRefrigeratorCapacity;
    private int mEnergyConsumption;

    public Fridge(DeviceType deviceType, int freezerCapacity, int refrigeratorCapacity, int annualEnergyConsumption ){
        this.mDeviceType=deviceType;
        this.mFreezerCapacity=freezerCapacity;
        this.mRefrigeratorCapacity=refrigeratorCapacity;
        this.mEnergyConsumption=annualEnergyConsumption/365;
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
