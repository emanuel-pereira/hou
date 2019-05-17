package smarthome.io.ui;

import smarthome.controller.cli.GetTotalRainfallForDayInHouseAreaCTRL;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.GregorianCalendar;

public class GetTotalRainfallForDayInHouseAreaUI {

    private static final String RAINFALL = "rainfall";
    private final GetTotalRainfallForDayInHouseAreaCTRL ctrl;
    private final SensorType sensorType = new SensorType(RAINFALL);


    public GetTotalRainfallForDayInHouseAreaUI(SensorTypeList sensorType) {

        this.ctrl = new GetTotalRainfallForDayInHouseAreaCTRL(sensorType);
    }

    public void run() {

        if (this.ctrl.checkIfSensorTypeExists(RAINFALL)) {
            this.checkIfHouseLocationIsConfigured();
        } else System.out.println("Please ask the Administrator to create the RAINFALL sensor type");
    }

    private void checkIfHouseLocationIsConfigured() {
        if (!this.ctrl.isHouseGAConfigured()) {
            System.out.println("The house configuration is incomplete. Please configure the house location first.");
            return;
        }
        this.selectDate();
    }

    private void selectDate() {
        System.out.println("Insert the date (YYYY-MM-DD) on which you want to check the total RAINFALL in the house area.");
        GregorianCalendar date = UtilsUI.requestDate("Please insert a valid date in YYYY-MM-DD format.");
        if (this.ctrl.closestSensorsWithLatestReadingsInDate(date, this.sensorType)) {
            System.out.print("The total RAINFALL in " + UtilsUI.dateToString(date));
            System.out.print(" is " + this.ctrl.showTotalValueInADay(date, this.sensorType) + " l/m3.\n");
        } else {
            System.out.println("The available " + RAINFALL + " sensors in the house area don't have readings in the specified date: " + UtilsUI.dateToString(date));
            System.out.println("Please select a date with registered readings.");
        }
    }
}


