package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Fridge implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mFreezerCapacity;
    private int mRefrigeratorCapacity;
    private int mAnnualEnergyConsumption;
    private ReadingList mActivityLog;


    public Fridge(DeviceType deviceType, int freezerCapacity, int refrigeratorCapacity, int annualEnergyConsumption) {
        this.mDeviceType = deviceType;
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mAnnualEnergyConsumption = annualEnergyConsumption;
        mActivityLog=new ReadingList();
    }

    public ReadingList getActivityLog() {
        return mActivityLog;
    }

    public double getEnergyConsumptionInPeriod(Calendar startHour, Calendar endHour) {
        double energyConsumption=0;
        if (ReadConfigFile.getDevicesMeteringPeriod()!=-1){
            energyConsumption=mActivityLog.getValueOfReadingsInTimeInterval(startHour,endHour);
        }
        return energyConsumption;
    }

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String deviceType = "3 - Device Type : " + this.mDeviceType.getTypeString();
        String freezerCapacity = "4 - Freezer Capacity : " + this.mFreezerCapacity;
        String refrigeratorCapacity = "5 - Refrigerator Capacity : " + this.mRefrigeratorCapacity;
        result.add(deviceType);
        result.add(freezerCapacity);
        result.add(refrigeratorCapacity);
        return result;
    }

    @Override
    public void setAttributeValue(String attribute, String newValue) {
        String freezerCapacity = "4 - Freezer Capacity : " + this.mFreezerCapacity;
        String refrigeratorCapacity = "5 - Refrigerator Capacity : " + this.mRefrigeratorCapacity;
        if (attribute.equals(freezerCapacity)) {
            setFreezerCapacity(parseInt(newValue));
        }
        if (attribute.equals(refrigeratorCapacity)) {
            setRefrigeratorCapacity(parseInt(newValue));
        }
    }

    @Override
    public double getEnergyConsumption() {
        return (double) mAnnualEnergyConsumption / 365;
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
