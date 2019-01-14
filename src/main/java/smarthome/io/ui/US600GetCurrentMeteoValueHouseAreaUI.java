package smarthome.io.ui;

import smarthome.controller.US600GetCurrentMeteoValueHouseAreaCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class US600GetCurrentMeteoValueHouseAreaUI {

    private House mHouse;
    private SensorTypeList mSensorTypeList;
    private GAList mGAList;
    private US600GetCurrentMeteoValueHouseAreaCTRL mCTRL600;

    public US600GetCurrentMeteoValueHouseAreaUI(House house, SensorTypeList sensorTypeList, GAList gaList) {
        mHouse = house;
        mCTRL600 = new US600GetCurrentMeteoValueHouseAreaCTRL(house, sensorTypeList);
        mSensorTypeList = sensorTypeList;
        mGAList = gaList;
    }

    Scanner read = new Scanner(System.in);
    private int indexOfSensorType;
    private SensorType sensorType;
    private Sensor closestSensor;
    private double currentReading;

    public void run() {
        if (mGAList.getGAList().size() != 0) {
            if (mSensorTypeList.getSensorTypeList().size() != 0) {
                while (true) {
                    System.out.println("Choose the meteorologic condition in the house area, you want to check the current value from the available sensor types:");
                    System.out.println(mCTRL600.getSensorTypeListInString());
                    indexOfSensorType = read.nextInt();
                    read.nextLine();
                    if (indexOfSensorType > mSensorTypeList.getSensorTypeList().size())
                        System.out.println("Please insert a valid option \n.");
                    else break;
                }
                sensorType = mCTRL600.getSensorTypeByIndex(indexOfSensorType);
                while (true) {
                    if (mHouse.getHouseGA() == null)
                        System.out.println("No location");
                    else break;
                }

                while (true) {
                    if (mCTRL600.getListSensorsOfOneType(sensorType).isEmpty()) {
                        System.out.println("No sensors of that type in the house area");
                        break;
                    } else break;
                }
                closestSensor = mCTRL600.getTheClosestSensorToGA(mCTRL600.getListSensorsOfOneType(sensorType));

                currentReading = mCTRL600.getLastReadingOfSensor(closestSensor);
                System.out.println("The current" + sensorType.getSensorTypeDesignation() + " in the House Area is " + currentReading);

            } else
                System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US5.");
        } else
            System.out.println("List of Geographical Areas is empty. Please insert at least one Geographical Area in US3.");
    }
}




