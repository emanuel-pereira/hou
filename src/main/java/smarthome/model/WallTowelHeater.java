package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.Calendar;

public class WallTowelHeater implements Device, Metered {

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "WallTowelHeater";
    private double nominalPower;
    private boolean activityStatus;
    private ReadingList activityLog;
    private NameValidations nameValidation = new NameValidations();

    public WallTowelHeater (String deviceName, DeviceSpecs deviceSpecs, double deviceNominalPower){
        this.name = deviceName;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = deviceNominalPower;
        this.activityStatus = true;
        this.activityLog = new ReadingList();

    }

    @Override
    public String getDeviceName() {
        return this.name;
    }

    @Override
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override
    public boolean isActive() {
        return this.activityStatus;
    }

    @Override
    public ReadingList getActivityLog() {
        return this.activityLog;
    }

    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    @Override
    public void setDeviceName(String name) {
        if (nameValidation.alphanumericName(name)){
            this.name = name;
        }
    }

    @Override
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower)){
            this.nominalPower = nominalPower;
        }
    }

    @Override
    public boolean deactivateDevice() {
        if(!this.activityStatus){
            return false;
        }
        this.activityStatus = false;
        return true;
    }

    @Override
    public double getEnergyConsumption(Calendar startTimeStamp, Calendar endTimeStamp) {
        Configuration c = new Configuration();

        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1 && this instanceof Metered) {
            energyConsumption = activityLog.getValueOfReadingsInTimeIntervalDevices(startTimeStamp, endTimeStamp);
        }
        return energyConsumption;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
