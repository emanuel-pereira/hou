package smarthome.model.devices;

import smarthome.model.Configuration;
import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.Metered;
import smarthome.model.ReadingList;
import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.Calendar;

public class Fridge implements Device, Metered {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Fridge";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;


    /**
     * Constructs a Device with a name.
     *
     * @param deviceName name given by the user to the device (requested during runtime).
     */

    public Fridge(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) { // deviceName is the name given by the user
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
    public String getName() {
        return this.name;
    }

    /**
     * @return the device specifications
     */
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    public String getDeviceType() {
        return this.deviceType;
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

    @Override
    public double getEnergyConsumption(Calendar startDate, Calendar endDate) {
        Configuration c = new Configuration();

        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {  // -1 signals an error in the config file
            energyConsumption = activityLog.getValueOfReadingsInTimeIntervalDevices(startDate, endDate);
        }
        return Utils.round(energyConsumption, 2);
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        double energy;
        energy = this.nominalPower/365;
        return energy;
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
        if (Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }

    public void setAttributeValue(String attribute, Double newValue) {
        this.getDeviceSpecs().setAttributeValue(attribute, newValue);
    }

    /**
     * set Device status to false
     *
     * @return true result
     */
    public boolean deactivateDevice() {
        this.active = false;
        return true;
    }
}