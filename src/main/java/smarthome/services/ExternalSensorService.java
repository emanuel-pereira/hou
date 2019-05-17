package smarthome.services;

import smarthome.dto.SensorDTO;
import smarthome.mapper.SensorMapper;
import smarthome.model.ExternalSensor;
import smarthome.model.Sensor;
import smarthome.model.SensorList;
import smarthome.repository.Repositories;

import java.util.List;

public class ExternalSensorService {
    SensorMapper mapper;

    public ExternalSensorService() {
        this.mapper = new SensorMapper();
    }

    public List<SensorDTO> findAll() {
        Iterable<ExternalSensor> externalSensors = Repositories.getExternalSensorRepository().findAll();
        SensorList sensorList = new SensorList();
        for (Sensor eSensor : externalSensors) {
            sensorList.getSensorList().add(eSensor);
        }
        return mapper.toDtoList(sensorList);
    }
}
