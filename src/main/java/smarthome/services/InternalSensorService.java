package smarthome.services;

import org.modelmapper.ModelMapper;
import smarthome.mapper.SensorMapper;
import smarthome.dto.SensorDTO;
import smarthome.model.*;

import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;


//This class implements WebService<SensorDTO, String> as an experimental approach
public class InternalSensorService implements WebService<SensorDTO, String>{

    private SensorMapper sensorMapper = new SensorMapper();
    private ModelMapper mapper;

    public List<SensorDTO> findAllBy(String roomId) {
        List<SensorDTO> sl = new ArrayList<>();

        List<Sensor> sensorList = findByRoom(roomId).getSensorList();

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



    public InternalSensorService(){
        this.mapper = new ModelMapper();
    }

    public SensorList findByRoom (String idRoom){
        Iterable<InternalSensor> internalSensors = Repositories.getInternalSensorRepository().findAll();
        SensorList sensorList = new SensorList();
        for (InternalSensor iSensor :internalSensors){
            if(iSensor.getRoom().getId().equals(idRoom)){
                sensorList.addSensor(iSensor);
            }
        }
        return sensorList;
    }

    public ReadingList getReadingsInSensor(String idSensor){
        return Repositories.getReadingsInternalSensor(idSensor);
    }


}
