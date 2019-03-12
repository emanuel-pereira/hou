package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.Calendar;

public class Lamp implements Device, Metered, Powered {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Lamp";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;


    /**
     * Constructs a Device with a name.
     *
     * @param deviceName name given by the user to the device (requested during runtime).
     */

    public Lamp(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) { // deviceName is the name given by the user
        this.name = deviceName;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;

        active = true;
        activityLog = new ReadingList();
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
        if (c.getDevicesMeteringPeriod() != -1 && this instanceof Metered) {
            energyConsumption = activityLog.getValueOfReadingsInTimeInterval(startDate, endDate);
        }
        return Utils.round(energyConsumption, 2);
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
}