package smarthome.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class Configuration {

    /**
     * Private constructor of Configuration class, which is a collection of static members, hence is not meant to be instantiated.
     */
    private Configuration() {
    }

    static String mConfigFilePath = "resources/config.properties";

    public static void setConfigPath(String newConfigPath) {
        mConfigFilePath = newConfigPath;
    }

    public static String getConfigValue(String key) {
        String value;

        boolean error = false;

        Properties properties = new Properties();
        InputStream inputStream = null;

        //Make sure the config file is available and can be read
        try {
            inputStream = new FileInputStream(mConfigFilePath);
        } catch (FileNotFoundException e) {
            error = true;
        }

        //Loading the inputStream may
        // cause an IOException. Let's handle that!
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            error = true;
        }

        //Return an error or the result
        if (error) {
            value = "ERROR";
        } else {
            value = properties.getProperty(key);
        }
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
    public static int getMeteringPeriod(String key) {
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
        List<String> devices = new ArrayList<>();

        String value = getConfigValue("TotalDevices");
        String currentDevice;

        int numberOfDevices;

        try {
            numberOfDevices = Integer.parseInt(value);
        } catch (Exception e) {
            numberOfDevices = 0;
        }

        if (numberOfDevices > 0) {
            for (int i = 1; i <= numberOfDevices; i++) {
                currentDevice = getConfigValue("devicetype" + i + "");
                devices.add(i - 1, currentDevice);
            }
        }

        return devices;
    }

    public static boolean isGridMeteringPeriodValid() {
        int gridMeteringPeriod = getGridMeteringPeriod();

        return gridMeteringPeriod <= 1440 && gridMeteringPeriod >= 0;
    }

    public static boolean isDeviceMeteringPeriodValid() {
        int deviceMeteringPeriod = getDevicesMeteringPeriod();

        return deviceMeteringPeriod <= 1440 && deviceMeteringPeriod >= 0;
    }


    public static boolean areMeteringPeriodsMultiple() {
        int devicesMeteringPeriod = getDevicesMeteringPeriod();
        int gridMeteringPeriod = getGridMeteringPeriod();

        if (devicesMeteringPeriod % gridMeteringPeriod != 0) {
            return false;
        }

        return 1440 % devicesMeteringPeriod == 0 && 1440 % gridMeteringPeriod == 0;
    }


    public static boolean isMeteringPeriodValid() {
            if (!isGridMeteringPeriodValid()) {
                return false;
            }

            if (!isDeviceMeteringPeriodValid()) {
                return false;
            }

        return areMeteringPeriodsMultiple();
    }

}