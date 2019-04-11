package smarthome.controller;

import smarthome.model.GeographicalArea;
import smarthome.model.SensorList;
import smarthome.model.SensorType;

import java.util.Calendar;

import static smarthome.model.House.*;


public class GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL {


    public GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL() {

    }


    public SensorList getGARainfallSensors(SensorType sensorType) {

        GeographicalArea houseGA = getHouseGA();
        SensorList gaSensorList = houseGA.getSensorListInGA();

        return gaSensorList.getListOfSensorsByType(sensorType);
    }


    public SensorList getClosestRainfallSensorsWithReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {

        return filterListByTypeByIntervalAndDistance(sensorType, startDate, endDate);
    }

    public boolean checkIfClosestRainfallSensorsHaveReadingsInPeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate);
    }

    public double calculateAverageOfRainfallReadings(SensorType sensorType, Calendar startDate, Calendar endDate) {

        double dailyAverageRainfall = Double.NaN;

        if (getClosestRainfallSensorsWithReadingsInTimePeriod(sensorType, startDate, endDate).size() == 1) {
            dailyAverageRainfall = averageOfReadingsInPeriod(sensorType, startDate, endDate);
        } else if (getClosestRainfallSensorsWithReadingsInTimePeriod(sensorType, startDate, endDate).size() > 1) {

            filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);

            dailyAverageRainfall = averageOfReadingsInPeriod(sensorType, startDate, endDate);
        }

        return dailyAverageRainfall;


    }


}