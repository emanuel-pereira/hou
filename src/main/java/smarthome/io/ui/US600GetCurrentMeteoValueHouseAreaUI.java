package smarthome.io.ui;

import smarthome.controller.US600GetCurrentMeteoValueHouseAreaCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class US600GetCurrentMeteoValueHouseAreaUI {

    private House mHouse;
    private SensorTypeList mSensorTypeList;
    private GAList mGAList;
    private US600GetCurrentMeteoValueHouseAreaCTRL mCTRL600;
    Scanner read = new Scanner(System.in);
    private int mIndexOfSensorType;
    private SensorType mSensorType;
    private Sensor mClosestSensor;
    private double mCurrentReading;

    public US600GetCurrentMeteoValueHouseAreaUI(House house, SensorTypeList sensorTypeList, GAList gaList) {
        mHouse = house;
        mCTRL600 = new US600GetCurrentMeteoValueHouseAreaCTRL(house, sensorTypeList);
        mSensorTypeList = sensorTypeList;
        mGAList = gaList;
    }



    public void run() {
        if (mGAList.getGAList().size() != 0) {
            if (mSensorTypeList.getSensorTypeList().size() != 0) {
                while (true) {
                    System.out.println("Choose the meteorologic condition in the house area, you want to check the current value from the available sensor types:");
                    System.out.println(mCTRL600.getSensorTypeListInString());
                    mIndexOfSensorType = read.nextInt();
                    read.nextLine();
                    if (mIndexOfSensorType > mSensorTypeList.getSensorTypeList().size())
                        UtilsUI.printLnInsertValidOption();
                    else break;
                }
                mSensorType = mCTRL600.getSensorTypeByIndex(mIndexOfSensorType);
                while (true) {
                    if (mHouse.getHouseGA() == null)
                        System.out.println("No location");
                    else break;
                }

                while (true) {
                    if (mCTRL600.getListSensorsOfOneType(mSensorType).isEmpty()) {
                        System.out.println("No sensors of that type in the house area");
                        break;
                    } else break;
                }
                mClosestSensor = mCTRL600.getTheClosestSensorToGA(mCTRL600.getListSensorsOfOneType(mSensorType));

                mCurrentReading = mCTRL600.getLastReadingOfSensor(mClosestSensor);
                System.out.println("The current" + mSensorType.getSensorTypeDesignation() + " in the House Area is " + mCurrentReading);

            } else
                System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US5.");
        } else
            System.out.println("List of Geographical Areas is empty. Please insert at least one Geographical Area in US3.");
    }
}




