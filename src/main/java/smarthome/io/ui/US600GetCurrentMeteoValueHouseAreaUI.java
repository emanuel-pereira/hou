package smarthome.io.ui;

import smarthome.controller.US600GetCurrentMeteoValueHouseAreaCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class US600GetCurrentMeteoValueHouseAreaUI {

    private House mHouse;
    private SensorTypeList mSensorTypeList;
    private GAList mGAList;
    private US600GetCurrentMeteoValueHouseAreaCTRL mCtrl;
    Scanner read = new Scanner(System.in);
    private int mIndexOfSensorType;
    private SensorType mSensorType;
    private Sensor mClosestSensor;
    private double mCurrentReading;

    public US600GetCurrentMeteoValueHouseAreaUI(House house, SensorTypeList sensorTypeList, GAList gaList) {
        mHouse = house;
        mCtrl = new US600GetCurrentMeteoValueHouseAreaCTRL(house, sensorTypeList);
        mSensorTypeList = sensorTypeList;
        mGAList = gaList;
    }


    public void run() {

        if (mCtrl.getSensorTypeListSize() == 0) {
            System.out.println("The list of sensor types is empty. Please insert one first in US005\n");
            return;
        }
        this.checkHouseGA();
    }

    private void checkHouseGA() {
        if (mCtrl.getHouseGA() == null) {
            System.out.println("The house configuration does not have a geographical area. Please configure the location of the house in US101.\n");
            return;
        }
        this.chooseSensorType();
    }

    private void chooseSensorType() {
        while (true) {
            System.out.println("Choose the meteorologic condition in the house area, you want to check the current value from the available sensor types:");
            System.out.println(mCtrl.showSensorTypeList());
            mIndexOfSensorType = read.nextInt();
            read.nextLine();
            if (mIndexOfSensorType > 0 && mIndexOfSensorType <= mCtrl.getSensorTypeListSize()){
                mSensorType = mCtrl.getSensorTypeByIndex(mIndexOfSensorType);
                this.checkLstSizeOfSensorType();
            }
            else
                UtilsUI.printLnInsertValidOption();
        }
    }

        private void checkLstSizeOfSensorType(){
        while (true) {
            if (mCtrl.getSensorListOfTypeSize(mSensorType) == 0) {
                System.out.println("No sensors of that type in the house area");
                return;
            }
            else break;
        }

        mClosestSensor = mCtrl.getClosestSensorByType(mSensorType);

        mCurrentReading = mCtrl.getLastReadingOfSensor(mClosestSensor);
        System.out.println("The current " + mSensorType.getSensorTypeDesignation() + " in the House Area is " + mCurrentReading);

    }
}




