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

public class MicrowaveOven implements Device, Metered, Programmable {

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "MicrowaveOven";
    private double nominalPower;
    private boolean activityStatus;
    private ReadingList activityLog;
    private List<Program> programList;
    private NameValidations nameValidation = new NameValidations();

    /**
     * Microwave Oven constructor
     *
     * @param deviceName
     * @param deviceSpecs
     * @param deviceNominalPower
     */

    public MicrowaveOven(String deviceName, DeviceSpecs deviceSpecs, double deviceNominalPower) {
        this.name = deviceName;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = deviceNominalPower;
        this.activityStatus = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<>();

    }

    /**
     * Get the Microwave Oven name
     *
     * @return Device name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the Microwave Oven specs
     *
     * @return Device specs
     */
    @Override
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    /**
     * Get the Microwave Oven type
     *
     * @return Device type
     */
    @Override
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Check if the device is active
     *
     * @return True if active
     */
    @Override
    public boolean isActive() {
        return this.activityStatus;
    }

    /**
     * Get the Microwave Oven activity log
     *
     * @return Device reading list
     */
    @Override
    public ReadingList getActivityLog() {
        return this.activityLog;
    }

    /**
     * Get the Microwave Oven nominal power
     *
     * @return Device nominal power
     */
    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    /**
     * Set the Microwave Oven name
     *
     * @param name Name of the device
     */
    @Override
    public void setDeviceName(String name) {
        if (nameValidation.alphanumericName(name)) {
            this.name = name;
        }
    }

    /**
     * Set the Microwave nominal power
     *
     * @param nominalPower Nominal power of the device
     */
    @Override
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower)) {
            this.nominalPower = nominalPower;
        }
    }

    /**
     * Deactivate Microwave Oven
     *
     * @return True if deactivated
     */
    @Override
    public boolean deactivateDevice() {
        if (!this.activityStatus) {
            return false;
        }
        this.activityStatus = false;
        return true;
    }

    /**
     * Get energy consumption of the Microwave Oven in time interval
     * @param startTimeStamp Beginning of the interval
     * @param endTimeStamp End of the interval
     * @return Energy consumption of the device
     */
    @Override
    public double getEnergyConsumption(Calendar startTimeStamp, Calendar endTimeStamp) {
        Configuration c = new Configuration();

        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = activityLog.getValueOfReadingsInTimeIntervalDevices(startTimeStamp, endTimeStamp);
        }
        return energyConsumption;
    }

    /**
     * Create a Microwave Oven program
     * @param name Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return The created program
     */
    @Override
    public Program createProgram(String name, double value) {
        return new Program(name, "Nominal Power", value);
    }

    /**
     * Add program to the program list of the Microwave Oven
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
     * Get program list of the Microwave Oven
     * @return Program list of the device
     */
    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }
}
