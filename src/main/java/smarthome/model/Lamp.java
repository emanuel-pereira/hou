package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Lamp implements DeviceSpecs {
    private int mLuminousFlux;
    private DeviceType mDeviceType;
    private ReadingList mActivityLog;

    public Lamp(DeviceType deviceType, int luminousFlux) {
        this.mDeviceType = deviceType;
        this.mLuminousFlux = luminousFlux;
        mActivityLog=new ReadingList();
    }

    public double getEnergyConsumptionInPeriod(Calendar startHour, Calendar endHour) {
        double energyConsumption=0;
        if (ReadConfigFile.getDevicesMeteringPeriod()!=-1){
            energyConsumption=mActivityLog.getValueOfReadingsInTimeInterval(startHour,endHour);
        }
        return energyConsumption;
    }

    public ReadingList getActivityLog() {
        return mActivityLog;
    }

    public void setLuminousFlux(int newLuminousFlux) {
        mLuminousFlux = newLuminousFlux;
    }

    public int getLuminousFlux() {
        return mLuminousFlux;
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String LuminousFlux = "4 - Luminous Flux : " + this.mLuminousFlux;
        result.add(LuminousFlux);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String LuminousFLux = "4 - Luminous Flux : " + this.mLuminousFlux;
        if (attribute.equals(LuminousFLux))
            setLuminousFlux(parseInt(newValue));
    }

    @Override
    public double getEnergyConsumption() {
        return 0;
    }

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }
}

