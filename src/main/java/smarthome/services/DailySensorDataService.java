package smarthome.services;

import org.modelmapper.ModelMapper;
import smarthome.dto.ReadingDTO;
import smarthome.model.*;
import smarthome.model.validations.Name;
import smarthome.repository.Repositories;
import smarthome.model.validations.Utils;

import java.text.ParseException;
import java.util.Calendar;


public class DailySensorDataService {

    private ModelMapper mapper;
    private String temp = "temperature";
    private String rain = "rainfall";

    public DailySensorDataService() {
        this.mapper = new ModelMapper();
    }


    public ReadingList getBestSensorReadings(String startDate, String endDate, String type) throws ParseException {
        Calendar calendarS = convertStringToCalendar(startDate);
        Calendar calendarE = convertStringToCalendar(endDate);
        calendarE.add(Calendar.DATE, 1);
        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name(type));
        Sensor sensor = House.getClosestSensorWithLatestReading(sType);
        if (sensor != null) {
            return Repositories.getReadingsExternalSensorInInterval(sensor.getId(), calendarS, calendarE);
        } else {
            return null;
        }
    }


    public boolean checkIfRainSensorHasReadings() {
        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name(rain));
        Sensor sensor = House.getClosestSensorWithLatestReading(sType);
        if (sensor != null) {
            String idSensor = sensor.getId();
            return Repositories.getReadingsExternalSensor(idSensor).size() != 0;
        } else {
            return false;
        }
    }

    public boolean checkIfRainSensorHasReadingsInPeriod(String day) throws ParseException {
        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name(rain));
        Sensor sensor = House.getClosestSensorWithLatestReading(sType);
        if (sensor != null) {
            String idSensor = sensor.getId();
            Calendar calendar = convertStringToCalendar(day);
            Calendar calendar1 = calendar;
            calendar1.add(Calendar.DATE,1);
            return Repositories.getReadingsExternalSensorInInterval(idSensor, calendar, calendar1).size() != 0;
        }
        else {
            return false;
        }
    }

    public ReadingDTO getTotalRainfall(String day) throws ParseException {
        Calendar calendar = convertStringToCalendar(day);
        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name(rain));
        String idSensor = House.getClosestSensorWithLatestReading(sType).getId();
        ReadingList readings = Repositories.getReadingsExternalSensor(idSensor);
        double value = readings.totalValueInGivenDay(calendar);
        Reading totalRain = new Reading(value, calendar);
        return mapper.map(totalRain, ReadingDTO.class);

    }

    public ReadingDTO getCurrentTemperature() {
        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name(temp));
        String idSensor = House.getClosestSensorWithLatestReading(sType).getId();
        Reading currentTemp = Repositories.getReadingsExternalSensor(idSensor).getLastReading();
        return mapper.map(currentTemp, ReadingDTO.class);
    }

    public ReadingDTO displayAmplitude(String startDate, String endDate) throws ParseException, IllegalAccessException {

        Reading maxDailyAmp = getBestSensorReadings(startDate, endDate, temp).dailyAmplitude().maxValueInInterval();

        return mapper.map(maxDailyAmp, ReadingDTO.class);
    }

    public ReadingDTO displayMaximum(String startDate, String endDate) throws ParseException, IllegalAccessException {
        Reading maxDailyTemp = getBestSensorReadings(startDate, endDate, temp).dailyMaximumReadings().maxValueInInterval();

        return mapper.map(maxDailyTemp, ReadingDTO.class);
    }

    public ReadingDTO displayMinimum(String startDate, String endDate) throws ParseException, IllegalAccessException {
        Reading minDailyTemp = getBestSensorReadings(startDate, endDate, temp).dailyMaximumReadings().minValueInInterval();

        return mapper.map(minDailyTemp, ReadingDTO.class);
    }

    public Calendar convertStringToCalendar(String input) throws ParseException {
        return Utils.convertStringToCalendar(input);
    }
}
