package smarthome.services;


import smarthome.dto.SensorDTO;
import smarthome.mapper.SensorMapper;
import smarthome.model.ExternalSensor;
import smarthome.model.Sensor;
import smarthome.model.SensorList;
import smarthome.repository.ExternalSensorRepository;
import smarthome.repository.Repositories;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;


public class ExternalSensorService {//implements WebService<SensorDTO, String> {

    private SensorMapper mapper;
    private static ExternalSensorRepository repo = Repositories.getExternalSensorRepository();

    public ExternalSensorService() {
        this.mapper = new SensorMapper();
    }


    public List<SensorDTO> findAll() {
        Iterable<ExternalSensor> externalSensors = repo.findAll();
        SensorList sensorList = new SensorList();
        for (Sensor eSensor : externalSensors) {
            sensorList.getSensorList().add(eSensor);
        }
        return mapper.toDtoList(sensorList);
    }


    public SensorDTO findById(String id) {
        Optional<ExternalSensor> s = repo.findById(id);
        if (s.isPresent()) {
            return mapper.toDto(repo.findById(id).get());
        }
        return null;
    }


    public SensorDTO create(SensorDTO sensorDTO) {
        Sensor s;
        s = mapper.toEntity(sensorDTO);
        repo.save((ExternalSensor) s);
        return mapper.toDto(s);
    }


    public void deleteById(String id) {
        boolean exists = repo.existsById(id);
        if (exists) {
            repo.deleteById(id);
        }
    }


    public SensorDTO replace(SensorDTO newSensorDTO, String id) {

        ExternalSensor oldSensor;
        Optional<ExternalSensor> s = repo.findById(id);

        if (s.isPresent()) {
            oldSensor = repo.findById(id).get();
        }

        ExternalSensor newSensor = (ExternalSensor) mapper.toEntity(newSensorDTO);

        oldSensor = newSensor;

        repo.save(oldSensor);

        newSensorDTO = mapper.toDto(newSensor);

        return newSensorDTO;
    }

    //User story specific methods

    public SensorDTO deactivate(SensorDTO sensorDTO, String id) {

        ExternalSensor sensor = repo.findById(id).get();
        sensor.getSensorBehavior().deactivate(new GregorianCalendar()); //deactivate "now"
        repo.save(sensor);

        return mapper.toDto(sensor);
    }

    public void remove(SensorDTO sensorDTO) {
        String id = sensorDTO.getId();
        deleteById(id);
    }
}
