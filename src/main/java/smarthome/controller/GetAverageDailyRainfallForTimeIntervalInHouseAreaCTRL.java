package smarthome.controller;

import smarthome.model.*;

import java.util.Calendar;


public class GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL {


    private House house;


    public GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(House house) {
        this.house = house;

    }


    public SensorList getGARainfallSensors(SensorType sensorType) {

        GeographicalArea houseGA = this.house.getHouseGA();
        SensorList gaSensorList = houseGA.getSensorListInGA();

        return gaSensorList.getListOfSensorsByType(sensorType);
    }


    public SensorList getClosestRainfallSensorsWithReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {

        return this.house.getClosestSensorsOfTypeWithReadingsInPeriod(sensorType, startDate, endDate);
    }

    public boolean checkIfClosestRainfallSensorsHaveReadingsInPeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return house.checkIfClosestSensorsHaveReadingsInTimePeriod(sensorType, startDate, endDate);
    }

    public double calculateAverageOfRainfallReadings(SensorType sensorType, Calendar startDate, Calendar endDate) {

        double dailyAverageRainfall = Double.NaN;

        if (getClosestRainfallSensorsWithReadingsInTimePeriod(sensorType, startDate, endDate).size() == 1) {
            dailyAverageRainfall = this.house.averageOfReadingsInPeriod(sensorType, startDate, endDate);
        } else if (getClosestRainfallSensorsWithReadingsInTimePeriod(sensorType, startDate, endDate).size() > 1) {

            this.house.getClosestSensorWithLatestReadingsInPeriod(sensorType, startDate, endDate);

            dailyAverageRainfall = this.house.averageOfReadingsInPeriod(sensorType, startDate, endDate);
        }

        return dailyAverageRainfall;


    }


}