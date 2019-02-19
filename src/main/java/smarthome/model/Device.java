package smarthome.model;

import smarthome.model.Validations.NameValidations;
import smarthome.model.Validations.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Double.parseDouble;

public class Device implements Metered {

    NameValidations nameValidation = new NameValidations();

    private String mName;
    private DeviceSpecs mDeviceSpecs;
    private double mNominalPower;
    private boolean mActive;
    private boolean mIsMetered;
    private ReadingList mActivityLog;


    /**
     * Constructs a Device with a name, device specifications(which include, at least, a device type) and a nominal power
     *
     * @param name         device name
     * @param deviceSpecs  device specific features, including, for example, device type
     * @param nominalPower double parameter to set the nominal power of the device
     */
    public Device(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        setName(name);
        mDeviceSpecs = deviceSpecs;
        setNominalPower(nominalPower);
        mActive = true;
        mIsMetered = true;
        mActivityLog = new ReadingList();
    }

    public Device(DeviceSpecs deviceSpecs) {
        mDeviceSpecs = deviceSpecs;
        mActive = true;
        mIsMetered = true;
        mActivityLog = new ReadingList();
    }

    /**
     * Method to set name as the one inputted by the user if it complies with alphanumericName method criteria
     *
     * @param name String inputted by the user to name the device
     */
    public void setName(String name) {
        if (nameValidation.alphanumericName(name))
            this.mName = name;
    }

    /**
     * Method to set nominal power as the one inputted by the user if it is a positive value method criteria
     *
     * @param nominalPower double inputted as nominal power
     */
    public void setNominalPower(double nominalPower) {
        if (Utils.valueIsPositive(nominalPower))
            this.mNominalPower = nominalPower;
    }

    /**
     * @return the device name
     */
    public String getName() {
        return mName;
    }

    /**
     * @return the device specifications
     */
    public DeviceSpecs getDeviceSpecs() {
        return mDeviceSpecs;
    }

    /**
     * @return the device nominal Power
     */
    public double getNominalPower() {
        return mNominalPower;
    }


    public String showDeviceAttributesInString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getDeviceSpecs().getDeviceType().getDeviceTypeName());
        result.append("\n");
        result.append("1 - Device Name : " + this.getName());
        result.append("\n");
        result.append("2 - Device Nominal Power : " + this.getNominalPower());
        result.append("\n");
        result.append(this.getDeviceSpecs().showDeviceAttributeNamesAndValues());
        return result.toString();
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String deviceName = "Device Name";
        String deviceNominalPower = "Device Nominal Power";
        result.add(deviceName);
        result.add(deviceNominalPower);
        for (String deviceSpecs : this.getDeviceSpecs().getAttributesNames())
            result.add(deviceSpecs);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String deviceName = "Device Name";
        String deviceNominalPower = "Device Nominal Power";
        if (attribute.contains(deviceName)) {
            setName(newValue);
        }
        if (attribute.contains(deviceNominalPower)) {
            setNominalPower(parseDouble(newValue));
        }
        this.getDeviceSpecs().setAttributeValue(attribute, newValue);
    }

    public double getEnergyConsumption() {
        return mDeviceSpecs.getEnergyConsumption();
    }

    /**
     * set Device status to false
     *
     * @return true result
     */
    public boolean deactivateDevice() {
        this.mActive = false;
        return true;
    }

    /**
     * return device status flag
     *
     * @return device status flag
     */
    public boolean status() {
        return mActive;
    }

    @Override
    public double getEnergyConsumptionInTimeInterval(Calendar startDate, Calendar endDate) {
        double energyConsumption = 0;
        if (Configuration.getDevicesMeteringPeriod() != -1 && this.isMetered()) {
            energyConsumption = mActivityLog.getValueOfReadingsInTimeInterval(startDate, endDate);
        }
        return energyConsumption;
    }

    //put this method as private after reviewing create device US
    public void setIsMetered(boolean isMetered) {
        if (!isMetered)
            mActivityLog = null;
        this.mIsMetered = isMetered;
    }

    public boolean isMetered() {
        return mIsMetered;
    }

    /**
     * return device activity log
     *
     * @return device activity log registry
     */
    public ReadingList getActivityLog() {
        return mActivityLog;
    }

    /**
     * return device activity log values sum for test
     *
     * @return int values representative of the activity log values sum
     */
    public double getActivityLogSum() {
        double sum = 0;
        for (Reading reading : mActivityLog.getReadingList()) {
            sum += reading.returnValueOfReading();
        }
        return sum;
    }
}