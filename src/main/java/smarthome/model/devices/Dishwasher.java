package smarthome.model.devices;

import smarthome.model.*;
import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Dishwasher implements Device, Metered, Programmable {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Dishwasher";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;
    private List<Program> programList;
    private ProgramWithTimer meteredProgram;

    /**
     * Dishwasher constructor
     *
     * @param name         Dishwasher name
     * @param deviceSpecs  Dishwasher specs
     * @param nominalPower Dishwasher nominal power
     */
    public Dishwasher(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        this.name = name;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;
        this.active = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<>();

    }

    /**
     * Get the Dishwasher name
     *
     * @return Device name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the Dishwasher specs
     *
     * @return Device specs
     */
    @Override
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    /**
     * Get the Dishwasher type
     *
     * @return Device type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Get the Dishwasher nominal power
     *
     * @return Device nominal power
     */
    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    /**
     * Check if the device is active
     *
     * @return True if active
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * Get the Dishwasher activity log
     *
     * @return Device reading list
     */
    @Override
    public ReadingList getActivityLog() {
        return this.activityLog;
    }

    /**
     * Set the Dishwasher name
     *
     * @param name Name of the device
     */
    @Override
    public void setDeviceName(String name) {
        if (nameValidation.alphanumericName(name))
            this.name = name;
    }

    /**
     * Set the Dishwasher nominal power
     *
     * @param nominalPower Nominal power of the device
     */
    @Override
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }

    /**
     * Deactivate Dishwasher
     *
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
     * Get energy consumption of the Dishwasher in time interval
     *
     * @param startHour Beginning of the interval
     * @param endHour   End of the interval
     * @return Energy consumption of the device
     */
    @Override
    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        Configuration c = new Configuration();
        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = activityLog.getValueOfReadingsInTimeIntervalDevices(startHour, endHour);
        }
        return Utils.round(energyConsumption, 2);
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        if (this.meteredProgram != null) {
            double energy;
            energy = this.meteredProgram.getProgramEstimatedEC();
            return energy;
        } else {
            return 0;
        }
    }


    /**
     * Create a Dishwasher program
     *
     * @param name  Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return The created program
     */
    @Override
    public ProgramWithTimer createProgram(String name, double value) {
        return new ProgramWithTimer(name, value);
    }

    /**
     * Add program to the program list of the Dishwasher
     *
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
     * Get program list of the Dishwasher
     *
     * @return Program list of the device
     */
    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }

    @Override
    public void setMeteredProgram(String programName) {
        for (Program program : this.programList) {
            if (program.getProgramName().equals(programName)) {
                this.meteredProgram = (ProgramWithTimer) program;
            }
        }
    }

    @Override
    public Program getMeteredProgram() {
        return this.meteredProgram;
    }

}
