package smarthome.services;


import org.modelmapper.ModelMapper;
import smarthome.dto.InternalReadingDTO;
import smarthome.model.*;
import smarthome.model.validations.Name;
import smarthome.model.validations.Utils;
import smarthome.repository.Repositories;

import java.text.ParseException;
import java.util.Calendar;

public class RoomTemperatureService {

    private ModelMapper mapper;
    private Name temp = new Name("temperature");
    private InternalSensorService sensorService;
    private SensorTypeService sensorTypeService;




    public RoomTemperatureService() {
        this.mapper = new ModelMapper();
        this.sensorService = new InternalSensorService();
        this.sensorTypeService= new SensorTypeService();
    }


    public SensorList getRoomSensors(String id) {
        return sensorService.findByRoom(id);
    }

    public ReadingList getBestSensorReadings(String idRoom){
        SensorType type =sensorTypeService.findByType(temp.getName());
        Sensor sensor = getRoomSensors(idRoom).getInternalSensorByTypeWithLatestReadings(type);
        if(sensor != null){
            return Repositories.getReadingsInternalSensor(sensor.getId());
        }
        else{
            return null;
        }
    }

    public boolean checkIfSensorHasReadings(String idRoom) {
        ReadingList readings = getBestSensorReadings(idRoom);

        if (readings != null) {
            return readings.size() != 0;
        } else {
            return false;
        }
    }

    public boolean checkIfSensorHasReadingsInDay(String idRoom, String givenDay) throws ParseException {
        Calendar calendar = convertStringToCalendar(givenDay);
        ReadingList readings = getBestSensorReadings(idRoom);
        if (readings != null) {
            return readings.getReadingsInSpecificDay(calendar).size() != 0;
        }
        else{
            return false;
        }
    }

    public InternalReadingDTO getCurrentTempInRoom(String idRoom) {
        Reading last = getBestSensorReadings(idRoom).getLastReading();

        return mapper.map(last, InternalReadingDTO.class);

    }

    public InternalReadingDTO getMaxTempInRoom(String idRoom, String givenDay) throws ParseException {
        Calendar calendar = convertStringToCalendar(givenDay);
        Reading maxTemp = getBestSensorReadings(idRoom).getReadingsInSpecificDay(calendar).maxValueInInterval();
        return mapper.map(maxTemp, InternalReadingDTO.class);

    }

    public Calendar convertStringToCalendar(String input) throws ParseException {
        return Utils.convertStringToCalendar(input);
    }


}


