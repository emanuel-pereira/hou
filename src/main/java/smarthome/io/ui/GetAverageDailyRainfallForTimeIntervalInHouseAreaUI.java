package smarthome.io.ui;

import smarthome.controller.GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL;
import smarthome.model.*;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.List;


public class GetAverageDailyRainfallForTimeIntervalInHouseAreaUI {

    /**
     * US623: As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),
     * as it is needed to assess the garden’s watering needs.
     *
     * 1. Check if there are any rainfall sensors in the Geographical Area of the House
     * a) if there are no rainfall sensors, return message
     *
     * b) if there are rainfall sensors:
     * 2. Request the user for the time interval which he wants to check the rainfall readings.
     * 3. Check if any of the sensors has readings for the given time period
     * a) if none has readings, return message
     *
     * b) if more than one has readings, check which is closer to the house
     * b.1) if two are equally distant, check which has the most recent readings and calculate average
     * b.2) if only one is closer, calculate distance
     *
     * c) if only one has readings, calculate the average
     */


    private GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623;
    private String rainfall = "rainfall";
    private SensorType sensorType = new SensorType(this.rainfall);
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;


    public GetAverageDailyRainfallForTimeIntervalInHouseAreaUI(House house) {

        this.ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(house);

    }


    public void getAverageDailyRainfallForInterval() {

        SensorList gaRainfallSensors = this.ctrl623.getGARainfallSensors(this.sensorType);
        List<Sensor> gaRainfallSensorList = gaRainfallSensors.getSensorList();

        SensorList gaRainfallSensorsWithReadingsInPeriod = this.ctrl623.getClosestRainfallSensorsWithReadingsInTimePeriod(this.sensorType, startDate, endDate);
        List<Sensor> gaRainfallSensorListWithReadingsInPeriod = gaRainfallSensorsWithReadingsInPeriod.getSensorList();


        if (!gaRainfallSensorList.isEmpty()) {

            requestTimePeriod();

            if (!gaRainfallSensorListWithReadingsInPeriod.isEmpty()) {

                requestTimePeriod();

            } else System.out.println("There are no readings for the given time period");


        } else System.out.println("There are no rainfall sensors in the house area");
    }


    private void requestTimePeriod() {

        System.out.println("Insert the start date");
        this.startDate = UtilsUI.requestDate("Please insert a valid date in yyyy-MM-dd format.");

        System.out.println("Insert the end date");
        this.endDate = UtilsUI.requestDate("Please insert a valid date in yyyy-MM-dd format.");


        if (this.ctrl623.checkIfClosestRainfallSensorsHaveReadingsInPeriod(this.sensorType, this.startDate, this.endDate)) {

            //DecimalFormat df = new DecimalFormat("####0.00");
            //double result =

            System.out.println("The average daily rainfall between " + UtilsUI.dateInString(this.startDate) + " and " + UtilsUI.dateInString(this.endDate) +
                    " is " + ctrl623.calculateAverageOfRainfallReadings(this.sensorType, this.startDate, this.endDate));
        } else
            System.out.println("The available " + this.rainfall + " sensors in the house area don't have readings in the specified date.\nPlease select a date with registered readings.");
    }


}