package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Fridge implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mFreezerCapacity;
    private int mRefrigeratorCapacity;
    private int mEnergyConsumption;

    public Fridge(DeviceType deviceType, int freezerCapacity, int refrigeratorCapacity, int annualEnergyConsumption) {
        this.mDeviceType=deviceType;
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mEnergyConsumption = annualEnergyConsumption / 365;
    }

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String FreezerCapacity = "4 - Freezer Capacity : " + this.mFreezerCapacity;
        String RefrigeratorCapacity = "5 - Refrigerator Capacity : " + this.mRefrigeratorCapacity;
        result.add(FreezerCapacity);
        result.add(RefrigeratorCapacity);
        return result;
    }

    @Override
    public void setAttributeValue(String attribute, String newValue) {
        String freezerCapacity = "4 - Freezer Capacity : " + this.mFreezerCapacity;
        String refrigeratorCapacity = "5 - Refrigerator Capacity : " + this.mRefrigeratorCapacity;
        if (attribute.equals(freezerCapacity))
            setFreezerCapacity(parseInt(newValue));
        if (attribute.equals(refrigeratorCapacity))
            setRefrigeratorCapacity(parseInt(newValue));
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
