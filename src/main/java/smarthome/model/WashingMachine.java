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

    /**
     * Washing Machine constructor
     * @param name Washing Machine name
     * @param deviceSpecs Washing Machine specs
     * @param nominalPower Washing Machine nominal power
     */
    public WashingMachine(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        this.name = name;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;
        this.active = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<> ();
    }

    /**
     * Get the Washing Machine name
     * @return Device name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the Washing Machine specs
     * @return Device specs
     */
    @Override
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    /**
     * Get the Washing Machine type
     * @return Device type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Get the Washing Machine nominal power
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
     * Get the Washing Machine activity log
     * @return Device reading list
     */
    @Override
    public ReadingList getActivityLog() {
        return this.activityLog;
    }

    /**
     * Set the Washing Machine name
     * @param name Name of the device
     */
    @Override
    public void setDeviceName(String name) {
        if (nameValidation.alphanumericName(name))
            this.name = name;
    }

    /**
     * Set the Washing Machine nominal power
     * @param nominalPower Nominal power of the device
     */
    @Override
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }

    /**
     * Deactivate Washing Machine
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
     * Get energy consumption of the Washing Machine in time interval
     * @param startHour Beginning of the interval
     * @param endHour End of the interval
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


    /**
     * Create a Washing Machine program
     * @param name Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return The created program
     */
    @Override
    public Program createProgram(String name, double value) {
        return new Program(name, "Energy Consumption", value);
    }

    /**
     * Add program to the program list of the Washing Machine
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
     * Get program list of the Washing Machine
     * @return Program list of the device
     */
    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }

}
