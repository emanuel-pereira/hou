package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Dishwasher implements DeviceSpecs, Metered {
    private DeviceType mDeviceType;
    private int mCapacity;
    private ProgramList mProgramListInDW;
    private ReadingList mActivityLog;


    public Dishwasher(DeviceType deviceType,int capacity) {
        mDeviceType=deviceType;
        mCapacity = capacity;
        mProgramListInDW = new ProgramList();
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


    public void setCapacity(int newCapacity) {

        mCapacity = newCapacity;
    }

    public int getCapacity() {

        return mCapacity;
    }

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String deviceType = "3 - Device Type : " + this.mDeviceType.getTypeString();
        String capacity = "4 - Dishwater Capacity : " + this.mCapacity;
        result.add(deviceType);
        result.add(capacity);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String capacity = "4 - Dishwater Capacity : " + this.mCapacity;
        if (attribute.equals(capacity))
            setCapacity(parseInt(newValue));
    }

    @Override
    public double getEnergyConsumption() {
        return 0;
    }


}
