package smarthome.io.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

    public static void main(String[] args) {
        Properties prop = new Properties (); // This class is available in java
        InputStream ip = null;
        try {
            ip = new FileInputStream ("resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
        try {
            prop.load (ip);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        System.out.println (prop.getProperty ("meteringPeriodDevices"));
        System.out.println (prop.getProperty ("meteringPeriodGrids"));
        System.out.println (prop.keySet ());

    }


}