package smarthome.model;

import smarthome.model.Validations.NameValidations;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class Device {

    NameValidations nameValidation = new NameValidations();

    private String mName;
    private DeviceSpecs mDeviceSpecs;
    private double mNominalPower;
    private boolean mStatus;
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
        mStatus = true;
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
        for (String s : getDeviceAttributesInString()) {
            result.append(s);
            result.append("\n");
        }
        return result.toString();
    }


    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String deviceName = "1 - Device Name : " + this.getName();
        String deviceNominalPower = "2 - Device Nominal Power : " + this.mNominalPower;
        result.add(deviceName);
        result.add(deviceNominalPower);
        for (String deviceSpecs : this.getDeviceSpecs().getDeviceAttributesInString())
            result.add(deviceSpecs);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String deviceName = "2 - Device Name : " + this.mName;
        String deviceNominalPower = "3 - Device Nominal Power : " + this.mNominalPower;
        if (attribute.equals(deviceName)) {
            setName(newValue);
        }
        if (attribute.equals(deviceNominalPower)) {
            setNominalPower(parseDouble(newValue));
        }
        this.getDeviceSpecs().setAttributeValue(attribute, newValue);
    }
    public double getEnergyConsumption(){
        return mDeviceSpecs.getEnergyConsumption();
    }

    /**
     * set Device status to false
     *
     * @return true result
     */
    public boolean deactivateDevice() {
        this.mStatus = false;
        return true;
    }

    /**
     * return device status flag
     *
     * @return device status flag
     */
    public boolean status() {
        return mStatus;
    }

    /**
     * return device activity log
     *
     * @return device activity log registry
     */
    public ReadingList getActivityLog() {
        return mActivityLog;
    }

}


