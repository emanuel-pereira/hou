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
public class Configuration {

    private static boolean isInitialized = false; // Thou shall run once, and only once... but you must lock the class.

    private static String configFilePath;
    private static int deviceMeteringPeriod;
    private static int gridMeteringPeriod;
    private static List<String> deviceTypes = new ArrayList<>();

    public static void init() {
        run("resources/config.properties", true); //default, provided for simplicity.
    }

    public static void init(boolean lock) {
        run("resources/config.properties", lock);
    }

    public static void init(String path) {
        run(path, true); // The setting of a different source config file is important for testing purposes.
    }

    public static void init(String path, boolean lock) {
        run(path, lock);
    }


    private static void run(String path, boolean lock) {
        boolean setLock = lock;

        if (isInitialized) {
            return;
        }

        deviceMeteringPeriod = -1;
        gridMeteringPeriod = -1;
        deviceTypes.clear();

        if (!path.isEmpty()) {
            configFilePath = path;
        }
        deviceMeteringPeriod = getMeteringPeriod("devicesMeteringPeriod");
        gridMeteringPeriod = getMeteringPeriod("gridMeteringPeriod");

        //
        if (!isMeteringPeriodValid()) { // checks for all multiple conditions that must be met for the metering period is to be considered valid
            deviceMeteringPeriod = -1;
            gridMeteringPeriod = -1;
        }

        deviceTypes = fetchDeviceTypes();

        if (setLock) {
            isInitialized = true;
        }
    }


    private static String getConfigValue(String key) {
        String value = "";

        Properties properties = new Properties();
        InputStream inputStream = null;

        //Make sure the config file is available and can be read
        try {
            inputStream = new FileInputStream(configFilePath);
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
        return gridMeteringPeriod;
    }


    /**
     * This method returns the metering period in minutes for the devices.
     *
     * @return the metering period in minutes in the interval [1,1440]. -1 denotes an error.
     */
    public static int getDevicesMeteringPeriod() {
        return deviceMeteringPeriod;
    }


    public static List<String> getDeviceTypes() {
        return deviceTypes;
    }

    private static List<String> fetchDeviceTypes() {

        deviceTypes.clear(); // ensures the list is emptied if the initialization is done more than once


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
                deviceTypes.add(i - 1, currentDevice);
            }
        }

        return deviceTypes;
    }


    private static boolean isGridMeteringPeriodValid() {

        return (gridMeteringPeriod <= 1440 && gridMeteringPeriod >= 0);
    }


    private static boolean isDeviceMeteringPeriodValid() {

        return (deviceMeteringPeriod <= 1440 && deviceMeteringPeriod >= 0);
    }


    private static boolean areMeteringPeriodsMultiple() {

        return (deviceMeteringPeriod % gridMeteringPeriod == 0);
    }

    private static boolean isMeteringPeriodValid() {

        return (isGridMeteringPeriodValid() && isDeviceMeteringPeriodValid() && areMeteringPeriodsMultiple());
    }

}