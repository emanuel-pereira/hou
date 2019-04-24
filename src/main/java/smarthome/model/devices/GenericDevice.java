package smarthome.model.devices;

import smarthome.model.*;
import smarthome.model.validations.NameValidations;

import java.util.Calendar;

public abstract class GenericDevice implements Device, Metered {
    private final NameValidations nameValidation = new NameValidations();
    private final DeviceSpecs deviceSpecs;
    private String name;
    private double nominalPower;
    private boolean active;
    private final ReadingList activityLog;
    private double time = 0;

    /**
     * Constructs a Device with a user given name.
     *
     * @param deviceName  name given by the user to the device (requested during runtime)
     * @param deviceSpecs is
     */
    public GenericDevice(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) { // deviceName is the name given by the user
        this.name = deviceName;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;
        this.active = true;
        this.activityLog = new ReadingList();
    }

    /* ----- Getters ----- */

    /**
     * @return the device name
     */
    @Override
    public String getDeviceName() {
        return this.name;
    }

    /**
     * @return the device specifications
     */
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    public String getDeviceType() {
        return this.getClass().getSimpleName();
    }

    /**
     * @return the device nominal Power
     */
    public double getNominalPower() {
        return this.nominalPower;
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
        return this.activityLog;
    }

    /* ----- Setters ----- */

    /**
     * Method to set name as the one inputted by the user if it complies with alphanumericName method criteria
     *
     * @param name String inputted by the user to name the device
     */
    public void setDeviceName(String name) {
        if (this.nameValidation.alphanumericName(name))
            this.name = name;
    }

    /**
     * Method to set nominal power as the one inputted by the user if it is a positive value method criteria
     *
     * @param nominalPower double inputted as nominal power
     */
    public void setNominalPower(double nominalPower) {
        if (nominalPower >= 0) {
            this.nominalPower = nominalPower;
        }
    }

    /**
     * set Device status to false
     *
     * @return true result
     */
    public boolean deactivateDevice() {

        if (!this.active) {
            return false;
        }

        this.active = false;
        return true;
    }


    // ------------- Metered interface implementation -------------

    public String getMeteredDesignation() {
        return this.getDeviceName();
    }

    public void setTime(double durationInHours) {
        this.time = durationInHours;
    }

    public double getEstimatedEnergyConsumption() {
        return this.nominalPower * this.time;
    }

    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        Configuration c = new Configuration();
        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = this.activityLog.getValueOfReadingsInTimeIntervalDevices(startHour, endHour);
        }
        return energyConsumption;
    }
}
