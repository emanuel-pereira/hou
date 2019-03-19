package smarthome.model.devices;

import smarthome.model.Configuration;
import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.Metered;
import smarthome.model.ReadingList;
import java.util.Calendar;

public class Tv implements Device, Metered {

    private smarthome.model.validations.NameValidations nameValidation = new smarthome.model.validations.NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Tv";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;
    private double time;


    /**
     * Constructs a Device with a name.
     *
     * @param deviceName name given by the user to the device (requested during runtime).
     */

    public Tv(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) { // deviceName is the name given by the user
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
    @Override
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    public String getDeviceType() {
        return this.deviceType;
    }


    /**
     * @return the device nominal Power
     */

    @Override
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

    @Override
    public ReadingList getActivityLog() {
        return this.activityLog;
    }


    @Override
    public double getEnergyConsumption(Calendar startDate, Calendar endDate) {
        Configuration c = new Configuration();

        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = this.activityLog.getValueOfReadingsInTimeIntervalDevices(startDate, endDate);
        }
        return smarthome.model.validations.Utils.round(energyConsumption, 2);
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        double energy;
        energy = this.nominalPower*this.time;
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
    @Override
    public void setNominalPower(double nominalPower) {
        if (smarthome.model.validations.Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }


    /**
     * set Device status to false
     *
     * @return true result
     */
    @Override
    public boolean deactivateDevice() {
        this.active = false;
        return true;
    }


}
