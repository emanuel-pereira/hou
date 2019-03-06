package smarthome.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class for the retrieval of metering periods and device types at startup. Should only be run once.
 * Public methods do not provide any kind of change in the class behaviour under any condition.
 * Instances may point to a different configuration file.
 * Only preset variables may be retrieved.
 */

public class Configuration {
    private final String currentFile;
    private final String errorMessage = "ERROR";

    public Configuration(String file) {
        currentFile = file;
    }

    public Configuration() {
        currentFile = "resources/config.properties"; //hard coded as it is the expected default behaviour.
    }

    private String getConfigValue(String key) {
        String value;

        Properties properties = new Properties();
        InputStream inputStream;

        //Make sure the config file is available and can be read. Loading the inputStream may also cause an IOException.
        //This is also handled from this block.
        try {
            inputStream = new FileInputStream(this.currentFile);
            properties.load(inputStream);
        } catch (Exception e) {
            value = errorMessage;
            return value;
        }

        value = properties.getProperty(key);

        if (value == null) {
            value = errorMessage;
        }

        return value;
    }

    /**
     * Private method that returns a metering period in minutes
     *
     * @param key is the required metering period
     * @return the metering period in minutes in the interval [1,1440]. -1 denotes an error.
     */
    private int getMeteringPeriod(String key) {
        int output;


        String value = getConfigValue(key);

        try {
            output = Integer.parseInt(value);
        } catch (Exception e) {
            output = -1;
        }

        return output;
    }

    /**
     * This method returns the metering period in minutes for the grid.
     *
     * @return the metering period in minutes in the interval [1,1440]. -1 denotes an error.
     */
    public int getGridMeteringPeriod() {
        if (!isMeteringPeriodValid()) {
            return -1;
        }
        return getMeteringPeriod("gridMeteringPeriod");
    }

    /**
     * This method returns the metering period in minutes for the devices.
     *
     * @return the metering period in minutes in the interval [1,1440]. -1 denotes an error.
     */
    public int getDevicesMeteringPeriod() {
        if (!isMeteringPeriodValid()) {
            return -1;
        }
        return getMeteringPeriod("devicesMeteringPeriod");
    }


    public List<String> getDeviceTypes() {

        List<String> deviceTypes = new ArrayList<>();
        String currentDevice;
        String value = getConfigValue("TotalDevices");

        int numberOfDevices;
        if (value.equals(errorMessage)) {
            deviceTypes.add(0, value);
            return deviceTypes;
        }

        try {
            numberOfDevices = Integer.parseInt(value);
        } catch (Exception e) {
            numberOfDevices = 0;
        }

        if (numberOfDevices > 0) {
            for (int i = 1; i <= numberOfDevices; i++) {
                currentDevice = getConfigValue("devicetype" + i + "");
                deviceTypes.add(i - 1, currentDevice);
            }
        }

        return deviceTypes;
    }

    public List<String> getDeviceSpecsAttributes(String deviceSpec) {
        List<String> devicesAttributes = new ArrayList<>();
        String key = deviceSpec.concat("TotalAttributes");
        String value = getConfigValue(key);
        String deviceAttributes;

        int numberOfAttributes;
        try {
            numberOfAttributes = Integer.parseInt(value);
        } catch (Exception e) {
            numberOfAttributes = 0;
        }
        if (numberOfAttributes > 0) {
            for (int i = 1; i <= numberOfAttributes; i++) {
                String key2 = deviceSpec.concat("Attribute");
                deviceAttributes = getConfigValue(key2 + i + "");
                devicesAttributes.add(deviceAttributes);
            }
        }
        return devicesAttributes;
    }


    private boolean isMeteringPeriodValid() {
        boolean isGridMeteringPeriodValid = (getMeteringPeriod("gridMeteringPeriod") <= 1440 && getMeteringPeriod("gridMeteringPeriod") >= 1);
        boolean isDeviceMeteringPeriodValid = (getMeteringPeriod("devicesMeteringPeriod") <= 1440 && getMeteringPeriod("devicesMeteringPeriod") >= 1);
        boolean areMeteringPeriodsMultiple = (getMeteringPeriod("devicesMeteringPeriod") % getMeteringPeriod("gridMeteringPeriod") == 0);

        return (isGridMeteringPeriodValid && isDeviceMeteringPeriodValid && areMeteringPeriodsMultiple);
    }

}