package smarthome.model.devices;

import smarthome.model.*;
import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fan implements Device, Metered, Programmable {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Fan";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;
    private List<Program> programList;
    private ProgramMode meteredProgram;


    /**
     * Fan constructor
     * @param name Fan name
     * @param deviceSpecs Fan specs
     * @param nominalPower Fan nominal power
     */
    public Fan(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        this.name = name;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;
        this.active = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<> ();
    }

    /**
     * Get the Fan name
     * @return Fan name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the Fan specs
     * @return Device specs
     */
    @Override
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    /**
     * Get the Fan type
     * @return Device type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Get the Fan nominal power
     * @return Device nominal power
     */
    @Override
    public double getNominalPower(){
        return this.nominalPower;
    }

    /**
     * Check if the device is active
     * @return True if active
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * Get the Fan activity log
     * @return Device reading list
     */
    @Override
    public ReadingList getActivityLog() {
        return this.activityLog;
    }

    /**
     * Set the Fan name
     * @param name Name of the device
     */
    @Override
    public void setDeviceName(String name) {
        if (this.nameValidation.alphanumericName(name))
            this.name = name;
    }

    /**
     * Set the Fan nominal power
     * @param nominalPower Nominal power of the device
     */
    @Override
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }

    /**
     * Deactivate Fan
     * @return True if deactivated
     */
    @Override
    public boolean deactivateDevice() {
        if (!this.active)
            return false;
        this.active = false;
        return true;
    }

    /**
     * Get energy consumption of the Fan in time interval
     * @param startHour Beginning of the interval
     * @param endHour End of the interval
     * @return Energy consumption of the device
     */
    @Override
    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        Configuration c = new Configuration();
        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = this.activityLog.getValueOfReadingsInTimeIntervalDevices(startHour, endHour);
        }
        return Utils.round(energyConsumption, 2);
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

    /**
     * Create a Fan program
     * @param name Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return The created program
     */
    @Override
    public ProgramMode createProgram(String name, double value) {
        return new ProgramMode(name, value);
    }

    /**
     * Add program to the program list of the Fan
     * @param newProgram Previous created program
     * @return True if added with success
     */
    @Override
    public boolean addProgramToList(Program newProgram) {
        if (!this.programList.contains(newProgram)) {
            this.programList.add(newProgram);
            return true;
        } else return false;
    }

    /**
     * Get program list of the Fan
     * @return Program list of the device
     */
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