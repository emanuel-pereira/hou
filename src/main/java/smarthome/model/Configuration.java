package smarthome.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utility class for the retrieval of metering periods and device types at startup. Only run once; variables are loaded at that time only.
 * Public methods do not provide any kind of change in the class behaviour under any condition. Only preset variables may be retrieved.
 * Note: as the configuration file is not changed at run time, the choice was made to make the class static.
 */

public final class Configuration {

    private Configuration() {
    }

    private static String getConfigValue(String key) {
        String value = "";

        Properties properties = new Properties();
        InputStream inputStream = null;

        //Make sure the config file is available and can be read
        try {
            inputStream = new FileInputStream("resources/config.properties");
        } catch (FileNotFoundException e) {
            value = "ERROR";
        }

        //Loading the inputStream may
        // cause an IOException. Let's handle that!
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            value = "ERROR";
        }

        if (value.equals("ERROR")) {
            return value;
        }

        value = properties.getProperty(key);

        if (value == null) {
            value = "ERROR";
        }

        return value;
    }

    /**
     * Private method that returns a metering period in minutes
     *
     * @param key is the required metering period
     * @return the metering period in minutes in the interval [1,1440]. -1 denotes an error.
     */
    private static int getMeteringPeriod(String key) {
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
    public static int getGridMeteringPeriod() {
        return getMeteringPeriod("gridMeteringPeriod");
    }


    /**
     * This method returns the metering period in minutes for the devices.
     *
     * @return the metering period in minutes in the interval [1,1440]. -1 denotes an error.
     */
    public static int getDevicesMeteringPeriod() {

        return getMeteringPeriod("devicesMeteringPeriod");
    }


    public static List<String> getDeviceTypes() {

        List<String> deviceTypes = new ArrayList<>();
        String currentDevice;
        String value = getConfigValue("TotalDevices");
        int numberOfDevices;

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

    private static boolean isMeteringPeriodValid() {
        boolean isGridMeteringPeriodValid = (getGridMeteringPeriod() <= 1440 && getGridMeteringPeriod() >= 0);
        boolean isDeviceMeteringPeriodValid =  (getDevicesMeteringPeriod() <= 1440 && getDevicesMeteringPeriod() >= 0);
        boolean areMeteringPeriodsMultiple = (getDevicesMeteringPeriod() % getGridMeteringPeriod() == 0);

        return (isGridMeteringPeriodValid && isDeviceMeteringPeriodValid && areMeteringPeriodsMultiple);
    }

}