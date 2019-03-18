package smarthome.model.devices;

import smarthome.model.Configuration;
import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.Metered;
import smarthome.model.Programmable;
import smarthome.model.Program;
import smarthome.model.ReadingList;
import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Oven implements Device, Metered, Programmable{

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Oven";
    private double nominalPower;
    private boolean activityStatus;
    private ReadingList activityLog;
    private List<Program> programList;
    private NameValidations nameValidation = new NameValidations();

    public Oven (String deviceName, DeviceSpecs deviceSpecs, double deviceNominalPower){
        this.name = deviceName;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = deviceNominalPower;
        this.activityStatus = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<>();

    }

    @Override
    public String getName() {
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
    public Program createProgram(String name, double value) {
        return new Program(name, "Nominal Power", value);
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
