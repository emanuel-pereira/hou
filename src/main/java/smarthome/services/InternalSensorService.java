package smarthome.services;

import smarthome.mapper.SensorMapper;
import smarthome.dto.SensorDTO;
import smarthome.model.Room;
import smarthome.model.Sensor;
import smarthome.model.InternalSensor;

import smarthome.repository.Repositories;


import java.util.ArrayList;
import java.util.List;




//This class implements WebService<SensorDTO, String> as an experimental approach
public class InternalSensorService implements WebService<SensorDTO, String>{

    private SensorMapper sensorMapper = new SensorMapper();

    public List<SensorDTO> findAllBy(String roomId) {
        List<SensorDTO> sl = new ArrayList<>();

        Room r = Repositories.getRoomRepository().findById(roomId).get();
        List<Sensor> sensorList = r.getSensorListInRoom().getSensorList();

        for (Sensor s : sensorList
        ) {
            sl.add(sensorMapper.toDto(s));
        }

        return sl;
    }

    public List<SensorDTO> findAllBy() {
        return new ArrayList<>();
    }

    public SensorDTO findById(String id) {
        Sensor s = Repositories.getInternalSensorRepository().
                findById(id).
                get();
        return sensorMapper.toDto(s);
    }

    public void deleteById(String id) {
        Repositories.getInternalSensorRepository().deleteById(id);
    }

    public SensorDTO replace(SensorDTO sensor, String id) {
        return null;
    }

    public SensorDTO create(SensorDTO newSensor) {
        Sensor s = sensorMapper.toEntity(newSensor);
        Repositories.getInternalSensorRepository().save((InternalSensor)s);
        return sensorMapper.toDto(s);
    }
}
