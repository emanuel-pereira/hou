package smarthome.io.ui;

import smarthome.controller.GetTotalRainfallForDayInHouseAreaCTRL;
import smarthome.model.House;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.GregorianCalendar;

public class GetTotalRainfallForDayInHouseAreaUI {

    private GetTotalRainfallForDayInHouseAreaCTRL ctrl;
    private String rainfall = "rainfall";
    private SensorType sensorType = new SensorType(this.rainfall);


    public GetTotalRainfallForDayInHouseAreaUI(House house, SensorTypeList sensorType) {

        this.ctrl = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorType);
    }

    public void run() {

        if (this.ctrl.checkIfSensorTypeExists(this.rainfall)) {
            this.checkIfHouseLocationIsConfigured();
        } else System.out.println("Please ask the Administrator to create the rainfall sensor type");
    }

    private void checkIfHouseLocationIsConfigured() {
        if (!this.ctrl.isHouseGAConfigured()) {
            System.out.println("The house configuration is incomplete. Please configure the house location first.");
            return;
        }
        this.selectDate();
    }

    private void selectDate() {
        System.out.println("Insert the date (yyyy-MM-dd) on which you want to check the total rainfall in the house area.");
        GregorianCalendar date = UtilsUI.requestDate("Please insert a valid date in yyyy-MM-dd format.");
        if (this.ctrl.closestSensorsWithLatestReadingsInDate(date, this.sensorType)) {
            System.out.print("The total rainfall in " + UtilsUI.dateToString(date));
            System.out.print(" is " + this.ctrl.showTotalValueInADay(date, this.sensorType) + " l/m3.\n");
        } else {
            System.out.println("The available " + this.rainfall + " sensors in the house area don't have readings in the specified date: "+UtilsUI.dateToString(date));
            System.out.println("Please select a date with registered readings.");
        }
    }
}


