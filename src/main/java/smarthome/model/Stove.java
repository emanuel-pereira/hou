package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Stove implements Device, Metered, Programmable {

    private NameValidations nameValidation = new NameValidations();

    private String name;
    private DeviceSpecs deviceSpecs;
    private String deviceType = "Stove";
    private double nominalPower;
    private boolean active;
    private boolean isMetered;
    private ReadingList activityLog;
    private List<Program> programList;


    /**
     * Constructs a Device with a name.
     *
     * @param deviceName name given by the user to the device (requested during runtime).
     */

    Stove(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {

        this.name = deviceName;
        this.deviceSpecs = deviceSpecs;
        this.nominalPower = nominalPower;

        this.active = true;
        this.isMetered = true;
        this.activityLog = new ReadingList();
        this.programList = new ArrayList<>();


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
    @Override
    public void setNominalPower(double nominalPower) {

        if (Utils.valueIsPositive(nominalPower))
            this.nominalPower = nominalPower;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DeviceSpecs getDeviceSpecs() {
        return this.deviceSpecs;
    }

    @Override
    public String getDeviceType() {
        return this.deviceType;
    }


    public double getNominalPower() {

        return nominalPower;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isMetered() {

        return isMetered;
    }

    @Override
    public ReadingList getActivityLog() {

        return activityLog;
    }

    @Override
    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        Configuration c = new Configuration();

        double energyConsumption = 0;

        if (c.getDevicesMeteringPeriod() != -1 && this.isMetered()) {

            energyConsumption = activityLog.getValueOfReadingsInTimeInterval(startHour, endHour);
        }
        return Utils.round(energyConsumption, 2);
    }


    public void setAttributeValue(String attribute, Double newValue) {
        this.getDeviceSpecs().setAttributeValue(attribute, newValue);
    }

    @Override
    public boolean deactivateDevice() {
        this.active = false;
        return true;
    }


    @Override
    public Program createProgram(String name, double value) {
        return new Program(name, "energy consumption", value);
    }

    @Override
    public boolean addProgramToList(Program newProgram) {
        if (!this.programList.contains(newProgram)) {
            this.programList.add(newProgram);
            return true;
        } else return false;

    }

    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }
}
