package smarthome.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReadConfigFile {




    private static String getConfigValue(String key) {

        boolean error = false;

        Properties properties = new Properties();
        InputStream inputStream = null;

        //Make sure the config file is available and can be read
        try {
            inputStream = new FileInputStream("resources/config.properties");

        } catch (FileNotFoundException e) {
            error = true;
        }

        //Loading the inputStream may cause an IOException. Let's handle that!
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            error = true;
        }

        //Return an error or the result
        if (error == true) {
            value = "ERROR";
        } else {
            value = properties.getProperty(key);
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

        if (output > 1440 || output <= 0) {
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
        String currentDevice ="";

        int numberOfDevices=0;

        try {
            numberOfDevices= Integer.parseInt(value);
        } catch (Exception e) {
            numberOfDevices = 0;
        }

        if (numberOfDevices > 0){
            for (int i=1;i<=numberOfDevices;i++){
                currentDevice=getConfigValue("Device"+i);
                devices.add(i-1,currentDevice);
            }
        }

        return devices;
    }

}