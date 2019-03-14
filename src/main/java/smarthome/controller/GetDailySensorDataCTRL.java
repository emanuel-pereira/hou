package smarthome.controller;

import smarthome.dto.ReadingDTO;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class GetDailySensorDataCTRL {


    private House house;


    public GetDailySensorDataCTRL(House house) {
        this.house = house;

    }


    public SensorList getGARainfallSensors(SensorType sensorType) {

        GeographicalArea houseGA = this.house.getHouseGA();
        SensorList gaSensorList = houseGA.getSensorListInGA();

        return gaSensorList.getListOfSensorsByType(sensorType);
    }


    public SensorList filterByTypeAndInterval(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return this.house.filterListByTypeByIntervalAndDistance(sensorType, startDate, endDate);
    }

    public boolean checkIfClosestSensorsHasReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return house.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate);
    }

    public double calculateAverageOfRainfallReadings(SensorType sensorType, Calendar startDate, Calendar endDate) {
        double dailyAverageRainfall = Double.NaN;
        if (filterByTypeAndInterval(sensorType, startDate, endDate).size() == 1) {
            dailyAverageRainfall = this.house.averageOfReadingsInPeriod(sensorType, startDate, endDate);
        } else if (filterByTypeAndInterval(sensorType, startDate, endDate).size() > 1) {
            //this.house.filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
            dailyAverageRainfall = this.house.averageOfReadingsInPeriod(sensorType, startDate, endDate);
        }
        return dailyAverageRainfall;
    }

    public ReadingDTO displayMaximum(SensorType sensorType, Calendar startDate, Calendar endDate) {
        Sensor sensor = this.house.filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        ReadingList sensorReadings = sensor.getReadingList().dailyMaximumReadings();
        Reading reading = sensorReadings.maxValueInInterval();
        return reading.toDTO();
    }


    public ReadingDTO displayMinimum(SensorType sensorType, GregorianCalendar startDate, GregorianCalendar endDate) {
        Sensor sensor = this.house.filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        ReadingList sensorReadings = sensor.getReadingList().dailyMaximumReadings();
        Reading reading = sensorReadings.minValueInInterval();
        return reading.toDTO();
    }

    public ReadingDTO displayAmplitude(SensorType sensorType, GregorianCalendar startDate, GregorianCalendar endDate) {
        Sensor sensor = this.house.filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        ReadingList sensorReadings = sensor.getReadingList().dailyAmplitude();
        Reading reading = sensorReadings.maxValueInInterval();
        return reading.toDTO();
    }
}