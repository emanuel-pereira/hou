package smarthome.services;

import org.modelmapper.ModelMapper;
import smarthome.dto.ExternalReadingDTO;
import smarthome.model.*;
import smarthome.model.validations.Name;
import smarthome.repository.Repositories;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DailySensorDataService {

    private ModelMapper mapper;
    private Name type = new Name("temperature");

    public DailySensorDataService(){
        this.mapper = new ModelMapper();
    }


    public ReadingList getBestSensorReadings(String startDate, String endDate)throws ParseException{
        Calendar calendarS = convertStringToCalendar(startDate);
        Calendar calendarE = convertStringToCalendar(endDate);
        calendarE.add(Calendar.DATE, 1);
        SensorType sType= Repositories.getSensorTypeRepository().findByType(type);
        String idSensor = House.getClosestSensorWithLatestReading(sType).getId();
        return Repositories.getReadingsExternalSensor(idSensor,calendarS,calendarE);
    }


    public ExternalReadingDTO displayAmplitude(String startDate, String endDate) throws ParseException{

        Reading maxDailyAmp = getBestSensorReadings(startDate,endDate).dailyAmplitude().maxValueInInterval();

        return mapper.map(maxDailyAmp,ExternalReadingDTO.class);
    }

    public ExternalReadingDTO displayMaximum (String startDate, String endDate) throws ParseException{
        Reading maxDailyTemp = getBestSensorReadings(startDate,endDate).dailyMaximumReadings().maxValueInInterval();

        return mapper.map(maxDailyTemp,ExternalReadingDTO.class);
    }

    public ExternalReadingDTO displayMinimum (String startDate, String endDate) throws ParseException{
        Reading minDailyTemp = getBestSensorReadings(startDate,endDate).dailyMaximumReadings().minValueInInterval();

        return mapper.map(minDailyTemp,ExternalReadingDTO.class);
    }


    public Calendar convertStringToCalendar (String input) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try{
        Date date = df.parse(input);
        }

        catch (ParseException e){
            return new GregorianCalendar(0000,00,00);
        }

        Date date = df.parse(input);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
