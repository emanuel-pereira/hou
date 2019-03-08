package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fridge implements Device, Metered {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Fridge";
    private double nominalPower;
    private boolean active;
    private boolean isMetered;
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

        active = true;
        isMetered = true;
        activityLog = new ReadingList();

    }

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
        return this.deviceType;
    }


    /**
     * @return the device nominal Power
     */
    public double getNominalPower() {
        return nominalPower;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public boolean isMetered() {
        return isMetered;
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
        if (c.getDevicesMeteringPeriod() != -1 && this.isMetered()) {
            energyConsumption = activityLog.getValueOfReadingsInTimeInterval(startDate, endDate);
        }
        return Utils.round(energyConsumption, 2);
    }

    public void setAttributeValue(String attribute, Double newValue) {
        this.getDeviceSpecs().setAttributeValue(attribute, newValue);
    }


    /* ----- Setters ----- */

    /**
     * set Device status to false
     *
     * @return true result
     */
    public boolean deactivateDevice() {
        this.active = false;
        return true;
    }
    //put this method as private after reviewing create device US

    public void setIsMetered(boolean isMetered) {
        if (!isMetered)
            activityLog = null;
        this.isMetered = isMetered;
    }

}