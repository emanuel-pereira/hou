package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Fridge implements DeviceSpecs {
    private int mFreezerCapacity;
    private int mRefrigeratorCapacity;
    private int mEnergyConsumption;

    public Fridge(int freezerCapacity, int refrigeratorCapacity, int annualEnergyConsumption) {
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mEnergyConsumption = annualEnergyConsumption / 365;
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
        String FreezerCapacity = "4 - Freezer Capacity : " + this.mFreezerCapacity;
        String RefrigeratorCapacity = "5 - Refrigerator Capacity : " + this.mRefrigeratorCapacity;
        if (attribute.equals(FreezerCapacity))
            setFreezerCapacity(parseInt(newValue));
        if (attribute.equals(RefrigeratorCapacity))
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
