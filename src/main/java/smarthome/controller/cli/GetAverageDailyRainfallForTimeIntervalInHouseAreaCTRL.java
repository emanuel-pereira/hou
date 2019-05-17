package smarthome.controller.cli;

import smarthome.model.GeographicalArea;
import smarthome.model.SensorList;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.Calendar;

import static smarthome.model.House.*;


public class GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL {

    private final SensorTypeList sensorTypeList;


    public GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(SensorTypeList sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    public boolean checkIfSensorTypeExists(String sensorType) {
        return this.sensorTypeList.checkIfSensorTypeExists(sensorType);
    }

    //TODO: tests

    public boolean isHouseGAConfigured() {
        return getHouseGA() != null;
    }

    public SensorList getGARainfallSensors(SensorType sensorType) {

        GeographicalArea houseGA = getHouseGA();
        SensorList gaSensorList = houseGA.getSensorListInGA();

        return gaSensorList.getListOfSensorsByType(sensorType);
    }


    public boolean checkIfClosestRainfallSensorsHaveReadingsInPeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate);
    }

    public SensorList getClosestRainfallSensorsWithReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {

        return filterListByTypeByIntervalAndDistance(sensorType, startDate, endDate);
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