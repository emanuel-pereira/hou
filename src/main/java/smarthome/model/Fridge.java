package smarthome.model;

public class Fridge implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mFreezerCapacity;
    private int mRefrigeratorCapacity;
    private int mEnergyConsumption;

    public Fridge(DeviceType deviceType, int freezerCapacity, int refrigeratorCapacity, int annualEnergyConsumption) {
        this.mDeviceType = deviceType;
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mEnergyConsumption = annualEnergyConsumption / 365;
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
        result.append("4 - Freezer Capacity : " + this.mFreezerCapacity);
        result.append("\n");
        result.append("5 - Refrigerator Capacity : " + this.mRefrigeratorCapacity);
        result.append("\n");

        return result.toString();
    }

    public void setFreezerCapacity(int freezerCapacity) {
        mFreezerCapacity = freezerCapacity;
    }

    public int getFreezerCapacity() {
        return mFreezerCapacity;
    }

    public void setRefrigeratorCapacity(int refrigeratorCapacity) {
        mRefrigeratorCapacity = refrigeratorCapacity;
    }

    public int getRefrigeratorCapacity() {
        return mRefrigeratorCapacity;
    }
}
