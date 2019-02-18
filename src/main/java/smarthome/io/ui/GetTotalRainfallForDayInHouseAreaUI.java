package smarthome.io.ui;

import smarthome.controller.GetTotalRainfallForDayInHouseAreaCTRL;
import smarthome.model.House;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.GregorianCalendar;

public class GetTotalRainfallForDayInHouseAreaUI {

    private GetTotalRainfallForDayInHouseAreaCTRL mCtrl;
    private String mRainfall = "rainfall";
    private GregorianCalendar mDate;

    public GetTotalRainfallForDayInHouseAreaUI(House house, SensorTypeList sensorType) {

        mCtrl = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorType);
    }

    public void run() {

        if (mCtrl.checkIfSensorTypeExists(mRainfall)) {
            this.checkIfHouseLocationIsConfigured();
        } else System.out.println("Please ask the Administrator to create the rainfall sensor type");
    }

    private void checkIfHouseLocationIsConfigured() {
        if (!mCtrl.isHouseGAConfigured()) {
            System.out.println("The house configuration is incomplete. Please configure the house location first.");
            return;
        }
        this.selectDate();
    }

    private void selectDate() {
        System.out.println("Insert the day for which you want to check the total rainfall in the house area.");
        mDate = UtilsUI.requestDate("Please insert a valid date in yyyy-MM-dd format.");
        if (mCtrl.closestSensorsHaveReadingsInDate(mDate, new SensorType(mRainfall))) {
            System.out.println("The total rainfall in "
                    + UtilsUI.dateInString(mDate) + " is " + mCtrl.showTotalValueInADay(mDate, new SensorType(mRainfall)) + " l/m3.");
        } else
            System.out.println("The available " + mRainfall + " sensors in the house area don't have readings in the specified date.\nPlease select a date with registered readings.");
    }
}


