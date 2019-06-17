package smarthome.mapper;

import smarthome.dto.SensorBehaviorDTO;
import smarthome.model.GenericName;
import smarthome.model.SensorBehavior;

public class SensorBehaviorMapper {

    private SensorTypeMapper sensorTypeMapper= new SensorTypeMapper();

public SensorBehaviorDTO toDTO(SensorBehavior sensorBehavior){
    SensorBehaviorDTO dto= new SensorBehaviorDTO();
    dto.setName(sensorBehavior.getName());
    dto.setSensorType(sensorTypeMapper.toDto(sensorBehavior.getSensorType()));
    dto.setStartDate(sensorBehavior.getStartDate());
    dto.setPauseDate(sensorBehavior.getPauseDate());
    dto.setUnit(sensorBehavior.getUnit());
    dto.setActive(sensorBehavior.isActive());
    return dto;
}

    public SensorBehavior toEntity(SensorBehaviorDTO sensorBehaviorDTO) {
    SensorBehavior sensorBehavior= new SensorBehavior();
    sensorBehavior.setName(new GenericName(sensorBehaviorDTO.getName()));
    sensorBehavior.setSensorType(sensorTypeMapper.toEntity(sensorBehaviorDTO.getSensorType()));
    sensorBehavior.setStartDate(sensorBehaviorDTO.getStartDate());
    sensorBehavior.setPauseDate(sensorBehaviorDTO.getPauseDate());
    sensorBehavior.setUnit(sensorBehaviorDTO.getUnit());
    sensorBehavior.setActive(sensorBehaviorDTO.isActive());
    return sensorBehavior;

    }
}
