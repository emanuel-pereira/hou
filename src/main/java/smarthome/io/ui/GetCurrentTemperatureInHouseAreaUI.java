package smarthome.io.ui;

import smarthome.controller.GetCurrentTemperatureInHouseAreaCTRL;
import smarthome.model.Sensor;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public class GetCurrentTemperatureInHouseAreaUI {

    private final GetCurrentTemperatureInHouseAreaCTRL ctrl;
    Scanner read = new Scanner(System.in);
    private SensorType sensorType;

    public GetCurrentTemperatureInHouseAreaUI(SensorTypeList sensorTypeList) {
        this.ctrl = new GetCurrentTemperatureInHouseAreaCTRL(sensorTypeList);
    }


    public void run() {

        if (this.ctrl.getSensorTypeListSize() == 0) {
            System.out.println("The list of sensor types is empty. Please ask the System Administrator to add these.\n");
            return;
        }
        this.checkHouseGA();
    }

    private void checkHouseGA() {
        if (this.ctrl.getHouseGACTRL() == null) {
            System.out.println("The house configuration does not have a geographical area. Please ask the System Administrator to configure this.\n");
            return;
        }
        this.chooseSensorType();
    }

    private void chooseSensorType() {

        System.out.println("Choose the meteorologic condition in the house area for which you want to check its current value:");
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
        Sensor closestSensor = this.ctrl.getClosestSensorWithLatestReadingCTRL(this.sensorType);
        double currentReadingValue = this.ctrl.getLastReadingOfSensor(closestSensor);
        System.out.println("The current " + this.sensorType.getType() + " in the House Area is " + currentReadingValue);
    }
}





