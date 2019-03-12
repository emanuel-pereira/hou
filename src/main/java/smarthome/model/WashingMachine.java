package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WashingMachine implements Device, Metered, Programmable {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Washing Machine";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;
    private List<Program> programList;

    public WashingMachine(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        this.name = name;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;
        this.active = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<> ();
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
    public double getNominalPower(){
        return this.nominalPower;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public ReadingList getActivityLog() {
        return this.activityLog;
    }

    @Override
    public void setDeviceName(String name) {
        if (nameValidation.alphanumericName(name))
            this.name = name;
    }

    @Override
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }

    @Override
    public boolean deactivateDevice() {
        if (!this.active)
            return false;
        this.active = false;
        return true;
    }

    @Override
    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        Configuration c = new Configuration();
        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = activityLog.getValueOfReadingsInTimeInterval(startHour, endHour);
        }
        return Utils.round(energyConsumption, 2);
    }

    @Override
    public Program createProgram(String name, double value) {
        return new Program(name, "Energy Consumption", value);
    }

    @Override
    public boolean addProgramToList(Program newProgram) {
        if (!this.programList.contains(newProgram)) {
            this.programList.add(newProgram);
            return true;
        } else return false;
    }


    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }

}
