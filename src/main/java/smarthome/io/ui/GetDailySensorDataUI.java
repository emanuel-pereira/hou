package smarthome.io.ui;

import smarthome.controller.GetDailySensorDataCTRL;
import smarthome.model.House;
import smarthome.model.Sensor;
import smarthome.model.SensorList;
import smarthome.model.SensorType;

import java.util.GregorianCalendar;
import java.util.List;

public class GetDailySensorDataUI {

    private GetDailySensorDataCTRL superCTRL;
    private String rainfall = "rainfall";
    private String temperature = "temperature";
    private SensorType rainfallSensorType = new SensorType(this.rainfall);
    private SensorType temperatureSensorType = new SensorType(this.temperature);
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;


    public GetDailySensorDataUI(House house) {
        this.superCTRL = new GetDailySensorDataCTRL(house);
    }


    public void getAverageDailyRainfallForInterval() {

        SensorList gaRainfallSensors = this.superCTRL.getGARainfallSensors(this.temperatureSensorType);
        List<Sensor> gaRainfallSensorList = gaRainfallSensors.getSensorList();

        SensorList gaRainfallSensorsWithReadingsInPeriod = this.superCTRL.filterByTypeAndInterval(this.temperatureSensorType, startDate, endDate);
        List<Sensor> gaRainfallSensorListWithReadingsInPeriod = gaRainfallSensorsWithReadingsInPeriod.getSensorList();


        if (!gaRainfallSensorList.isEmpty()) {

            requestTimePeriod();

            if (!gaRainfallSensorListWithReadingsInPeriod.isEmpty()) {

                requestTimePeriod();

            } else System.out.println("There are no readings for the given time period");


        } else System.out.println("There are no rainfall sensors in the house area");
    }


    private void requestTimePeriod() {

        System.out.println("Insert the start date (YYYY-MM-DD)");
        this.startDate = UtilsUI.requestDate("Please insert a valid date in YYYY-MM-DD format.");

        System.out.println("Insert the end date (YYYY-MM-DD)");
        this.endDate = UtilsUI.requestDate("Please insert a valid date in YYYY-MM-DD format.");

        calculations(this.startDate, this.endDate);
    }

    private void calculations(GregorianCalendar startDate, GregorianCalendar endDate) {
        if (this.superCTRL.checkIfClosestSensorsHasReadingsInTimePeriod(this.temperatureSensorType, startDate, endDate)) {

            System.out.println("The average daily rainfall between " + UtilsUI.dateToString(startDate) + " and " + UtilsUI.dateToString(endDate) +
                    " is " + superCTRL.calculateAverageOfRainfallReadings(this.temperatureSensorType, startDate, endDate));
        } else
            System.out.println("The available " + this.rainfall + " sensors in the house area don't have readings in the specified date.\nPlease select a date with registered readings.");

    }

    public void displayFirstMaximum() {

        SensorList filteredSensorsByTypeInGA = this.superCTRL.getGARainfallSensors(this.temperatureSensorType);
        SensorList filterByInterval = this.superCTRL.filterByTypeAndInterval(this.temperatureSensorType, startDate, endDate);

        if (filteredSensorsByTypeInGA.size() != 0) {

            requestTimePeriod();

            if (filterByInterval.size() != 0) {

                requestTimePeriod();

            } else System.out.println("There are no readings for the given time period");


        } else System.out.println("There are no rainfall sensors in the house area");
    }
}


