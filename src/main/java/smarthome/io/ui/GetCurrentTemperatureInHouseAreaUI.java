package smarthome.io.ui;

import smarthome.controller.GetCurrentTemperatureInHouseAreaCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class GetCurrentTemperatureInHouseAreaUI {

    private GetCurrentTemperatureInHouseAreaCTRL mCtrl;
    Scanner read = new Scanner(System.in);
    private int mIndexOfSensorType;
    private SensorType mSensorType;
    private Sensor mClosestSensor;
    private double mCurrentReading;

    public GetCurrentTemperatureInHouseAreaUI(House house, SensorTypeList sensorTypeList) {
        mCtrl = new GetCurrentTemperatureInHouseAreaCTRL(house, sensorTypeList);
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

        System.out.println("Choose the meteorologic condition in the house area for which you want to check its current value:");
        System.out.println(mCtrl.showSensorTypeList());
        mIndexOfSensorType = UtilsUI.requestIntegerInInterval(1, mCtrl.getSensorTypeListSize(), "Please insert a valid input from the list.\n" + mCtrl.showSensorTypeList());
        mSensorType = mCtrl.getSensorTypeByIndex(mIndexOfSensorType);
        this.checkLstSizeOfSensorType();
    }

    private void checkLstSizeOfSensorType() {
        if (mCtrl.getSensorListOfTypeSize(mSensorType) == 0) {
            System.out.println("No sensors of that type in the house area");
            return;
        }
        mClosestSensor = mCtrl.getClosestSensorWithLatestReading(mSensorType);
        mCurrentReading = mCtrl.getLastReadingOfSensor(mClosestSensor);
        System.out.println("The current " + mSensorType.getSensorTypeDesignation() + " in the House Area is " + mCurrentReading);
    }
}





