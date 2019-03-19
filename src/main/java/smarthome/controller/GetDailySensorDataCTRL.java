package smarthome.controller;

import smarthome.dto.ReadingDTO;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetDailySensorDataCTRL {
    private House house;
    private SensorTypeList sensorTypeList;

    public GetDailySensorDataCTRL(House house, SensorTypeList sensorTypeList) {
        this.house = house;
        this.sensorTypeList = sensorTypeList;
    }

    public boolean checkIfSensorTypeExists(String sensorType) {
        return this.sensorTypeList.checkIfSensorTypeExists(sensorType);
    }

    public boolean isHouseGAConfigured() {
        return this.house.getHouseGA() != null;
    }

    public int filterByType(SensorType sensorType) {
        GeographicalArea houseGA = this.house.getHouseGA();
        SensorList gaSensorList = houseGA.getSensorListInGA();
        SensorList listOfType = gaSensorList.getListOfSensorsByType(sensorType);
        return listOfType.size();
    }

    public SensorList filterByTypeAndInterval(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return this.house.filterListByTypeByIntervalAndDistance(sensorType, startDate, endDate);
    }

    public boolean checkIfClosestSensorsHasReadingsInTimePeriod(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return house.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate);
    }

    public ReadingDTO displayMaximum(SensorType sensorType, Calendar startDate, Calendar endDate) {
        Sensor sensor = this.house.filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        endDate.add(Calendar.DATE, 1);
        ReadingList sensorReadings = sensor.getReadingList().filterByDate(startDate, endDate);
        endDate.add(Calendar.DATE, -1);
        sensorReadings = sensorReadings.dailyMaximumReadings();
        Reading reading = sensorReadings.maxValueInInterval();
        return reading.toDTO();
    }

    public ReadingDTO displayMinimum(SensorType sensorType, GregorianCalendar startDate, GregorianCalendar endDate) {
        Sensor sensor = this.house.filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        endDate.add(Calendar.DATE, 1);
        ReadingList sensorReadings = sensor.getReadingList().filterByDate(startDate, endDate);
        endDate.add(Calendar.DATE, -1);
        sensorReadings = sensorReadings.dailyMaximumReadings();
        Reading reading = sensorReadings.minValueInInterval();
        return reading.toDTO();
    }

    public ReadingDTO displayAmplitude(SensorType sensorType, GregorianCalendar startDate, GregorianCalendar endDate) {
        Sensor sensor = this.house.filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        endDate.add(Calendar.DATE, 1);
        ReadingList sensorReadings = sensor.getReadingList().filterByDate(startDate, endDate);
        endDate.add(Calendar.DATE, -1);
        ReadingList sensorXPTO = sensorReadings.dailyAmplitude();
        Reading reading = sensorXPTO.maxValueInInterval();
        return reading.toDTO();
    }
}