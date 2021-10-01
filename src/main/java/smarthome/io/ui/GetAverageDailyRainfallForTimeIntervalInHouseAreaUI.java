package smarthome.io.ui;

import smarthome.controller.cli.GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL;
import smarthome.model.Sensor;
import smarthome.model.SensorList;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.GregorianCalendar;
import java.util.List;


public class GetAverageDailyRainfallForTimeIntervalInHouseAreaUI {

    private static final String RAINFALL = "rainfall";
    private static final String MSG_TITLE = "Data not found";
    private final GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private final SensorType sensorType = new SensorType(RAINFALL);


    public GetAverageDailyRainfallForTimeIntervalInHouseAreaUI(SensorTypeList sensorTypeList) {

        this.ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sensorTypeList);
    }

    public void getAverageDailyRainfallForInterval() {

        if (ctrl623.checkIfSensorTypeExists(this.sensorType.getType().getName())) {
            checkIfHouseLocationIsConfigured();
        } else {
            String msg = "Please ask the Administrator to create the " + this.sensorType.getType() + " sensor type";
            UtilsUI.showError(MSG_TITLE, msg);
        }

    }

    private void checkIfHouseLocationIsConfigured() {
        if (!ctrl623.isHouseGAConfigured()) {
            String msg = "The house configuration is incomplete. Please configure the house location first.";
            UtilsUI.showError(MSG_TITLE, msg);
        } else checkIfGaHasRainfallSensors();
    }

    private void checkIfGaHasRainfallSensors() {

        SensorList gaRainfallSensors = this.ctrl623.getGARainfallSensors(this.sensorType);
        List<Sensor> gaRainfallSensorList = gaRainfallSensors.getSensorList();

        if (!gaRainfallSensorList.isEmpty()) {

            requestTimePeriod();

        } else {
            String msg = "There are no RAINFALL sensors in the house area";
            UtilsUI.showError(MSG_TITLE, msg);

        }
    }

    private void requestTimePeriod() {

        System.out.println("Insert the start date (YYYY-MM-DD)");
        this.startDate = UtilsUI.requestDate("Please insert a valid date in YYYY-MM-DD format.");

        System.out.println("Insert the end date (YYYY-MM-DD)");
        this.endDate = UtilsUI.requestDate("Please insert a valid date in YYYY-MM-DD format.");

        checkIfRainfallSensorsHaveReadingsInPeriod();

    }

    private void checkIfRainfallSensorsHaveReadingsInPeriod() {

        SensorList gaRainfallSensorsWithReadingsInPeriod = this.ctrl623.getClosestRainfallSensorsWithReadingsInTimePeriod(this.sensorType, startDate, endDate);
        List<Sensor> gaRainfallSensorListWithReadingsInPeriod = gaRainfallSensorsWithReadingsInPeriod.getSensorList();

        if (gaRainfallSensorListWithReadingsInPeriod.isEmpty()) {

            String msg = "There are no readings for the given time period.";
            UtilsUI.showError(MSG_TITLE, msg);

        } else calculateAverageOfReadings();

    }

    private void calculateAverageOfReadings() {
        System.out.println("The average daily RAINFALL between " + UtilsUI.dateToString(this.startDate) + " and " + UtilsUI.dateToString(this.endDate) +
                " is " + ctrl623.calculateAverageOfRainfallReadings(this.sensorType, this.startDate, this.endDate) + "mm.\n");

    }
}