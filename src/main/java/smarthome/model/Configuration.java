package smarthome.model;

import java.io.FileInputStream;
import java.io.InputStream;
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
    private static final String ERROR = "ERROR";
    private static final String GRIDMP = "gridMeteringPeriod";
    private static final String DEVICESMP = "devicesMeteringPeriod";


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
            value = ERROR;
            return value;
        }

        value = properties.getProperty(key);

        if (value == null) {
            value = ERROR;
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
        return getMeteringPeriod(GRIDMP);
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
        return getMeteringPeriod(DEVICESMP);
    }


    public List<String> getDeviceTypes() {

        List<String> deviceTypes = new ArrayList<>();
        String currentDevice;

        int i = 1;

        while (true) {

            currentDevice = getConfigValue("devicetype" + i);
            if (currentDevice.equals(ERROR)) {
                break;
            }
            deviceTypes.add(currentDevice);
            i++;

        }

        return deviceTypes;
    }

    private boolean isMeteringPeriodValid() {
        int gmp = getMeteringPeriod(GRIDMP);
        int dmp = getMeteringPeriod(DEVICESMP);

        boolean validGMP = (gmp <= 1440) && (gmp >= 1);
        boolean validDMP = (dmp <= 1440) && (dmp >= 1);

        boolean areMeteringPeriodsMultiple = (dmp % gmp == 0);
        boolean areMeteringPeriodsMultipleOf1440 = ((1440 % dmp == 0) && (1440 % gmp == 0));
        return (validGMP && validDMP && areMeteringPeriodsMultiple&&areMeteringPeriodsMultipleOf1440);
    }

}