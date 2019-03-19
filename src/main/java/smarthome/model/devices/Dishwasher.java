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

public class Dishwasher implements Device, Metered, Programmable {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Dishwasher";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;
    private List<Program> programList;


    /**
     * Constructs a Device with a name.
     *
     * @param deviceName name given by the user to the device (requested during runtime).
     */

    public Dishwasher(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {
        this.name = deviceName;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;
        this.active = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<>();
    }


    /**
     * Create a Dishwasher program
     *
     * @param name  Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return the created program
     */
    @Override
    public Program createProgram(String name, double value) {
        return new Program(name, "Energy Consumption", value);
    }

    /**
     * Add program to the program list of the Dishwasher
     *
     * @param newProgram Previous created program
     * @return true if added with success
     */
    @Override
    public boolean addProgramToList(Program newProgram) {
        if (!this.programList.contains(newProgram)) {
            this.programList.add(newProgram);
            return true;
        } else return false;
    }

    /* ----- Setters ----- */

    /**
     * Method to set name as the one inputted by the user if it complies with alphanumericName method criteria
     *
     * @param name String inputted by the user to name the device
     */
    public void setDeviceName(String name) {
        if (nameValidation.alphanumericName(name))
            this.name = name;
    }

    /**
     * Method to set nominal power as the one inputted by the user if it is a positive value method criteria
     *
     * @param nominalPower double inputted as nominal power
     */
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }

    /**
     * set Device status to false
     *
     * @return true result
     */
    public boolean deactivateDevice() {
        if (!this.active)
            return false;
        this.active = false;
        return true;
    }


    /* ----- Getters ----- */

    /**
     * @return the device name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the device nominal Power
     */
    public double getNominalPower() {
        return nominalPower;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * @return the device specifications
     */
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * return device activity log
     *
     * @return device activity log registry
     */
    public ReadingList getActivityLog() {
        return activityLog;
    }


    @Override
    public double getEnergyConsumption(Calendar startDate, Calendar endDate) {
        Configuration c = new Configuration();

        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = activityLog.getValueOfReadingsInTimeInterval(startDate, endDate);
        }
        return Utils.round(energyConsumption, 2);
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


}