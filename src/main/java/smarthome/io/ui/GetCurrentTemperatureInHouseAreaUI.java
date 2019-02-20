package smarthome.io.ui;

import smarthome.controller.GetCurrentTemperatureInHouseAreaCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class GetCurrentTemperatureInHouseAreaUI {

    private GetCurrentTemperatureInHouseAreaCTRL ctrl;
    Scanner read = new Scanner(System.in);
    private SensorType sensorType;

    public GetCurrentTemperatureInHouseAreaUI(House house, SensorTypeList sensorTypeList) {
        this.ctrl = new GetCurrentTemperatureInHouseAreaCTRL(house, sensorTypeList);
    }


    public void run() {

        if (this.ctrl.getSensorTypeListSize() == 0) {
            System.out.println("The list of sensor types is empty. Please insert one first in US005\n");
            return;
        }
        this.checkHouseGA();
    }

    private void checkHouseGA() {
        if (this.ctrl.getHouseGA() == null) {
            System.out.println("The house configuration does not have a geographical area. Please configure the location of the house in US101.\n");
            return;
        }
        this.chooseSensorType();
    }

    private void chooseSensorType() {

        System.out.println("Choose the meteorologic mCondition in the house area for which you want to check its current value:");
        System.out.println(this.ctrl.showSensorTypeList());
        int indexOfSensorType = UtilsUI.requestIntegerInInterval(1, this.ctrl.getSensorTypeListSize(), "Please insert a valid input from the list.\n" + this.ctrl.showSensorTypeList());
        indexOfSensorType--;
        this.sensorType = this.ctrl.getSensorTypeByIndex(indexOfSensorType);
        this.checkLstSizeOfSensorType();
    }

    private void checkLstSizeOfSensorType() {
        if (this.ctrl.getSensorListOfTypeSize(this.sensorType) == 0) {
            System.out.println("No sensors of that type in the house area");
            return;
        }
        Sensor closestSensor = this.ctrl.getClosestSensorWithLatestReading(this.sensorType);
        double currentReadingValue = this.ctrl.getLastReadingOfSensor(closestSensor);
        System.out.println("The current " + this.sensorType.getType() + " in the House Area is " + currentReadingValue);
    }
}





