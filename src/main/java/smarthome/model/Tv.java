package smarthome.model;

import java.util.Calendar;

public class Tv implements Device, Metered{

    private smarthome.model.validations.NameValidations nameValidation = new smarthome.model.validations.NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Tv";
    private double nominalPower;
    private boolean active;
    private ReadingList activityLog;


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
    public String getDeviceName() {
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
        return nominalPower;
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
        return activityLog;
    }


    @Override
    public double getEnergyConsumption(Calendar startDate, Calendar endDate) {
        Configuration c = new Configuration();

        double energyConsumption = 0;
        if (c.getDevicesMeteringPeriod() != -1) {
            energyConsumption = activityLog.getValueOfReadingsInTimeInterval(startDate, endDate);
        }
        return smarthome.model.validations.Utils.round(energyConsumption, 2);
    }

    @Override
    public String getName() {
        return  this.name;
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
