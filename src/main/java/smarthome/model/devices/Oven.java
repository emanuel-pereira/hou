package smarthome.model.devices;

import smarthome.model.*;
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
    private ProgramMode meteredProgram;
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
        if (this.nameValidation.alphanumericName(name)){
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
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = this.activityLog.getValueOfReadingsInTimeIntervalDevices(startTimeStamp, endTimeStamp);
        }
        return energyConsumption;
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        if (this.meteredProgram != null) {
            double energy;
            energy = this.meteredProgram.getProgramEstimatedEC ();
            return energy;
        } else {
            return 0;
        }
    }

    @Override
    public ProgramMode createProgram(String name, double value) {
        return new ProgramMode(name, value);
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

    @Override
    public void setMeteredProgram(String programName) {
        for (Program program : this.programList) {
            if (program.getProgramName ().equals (programName)) {
                this.meteredProgram = (ProgramMode) program;
            }
        }
    }

    @Override
    public Program getMeteredProgram() {
        return this.meteredProgram;
    }

}
