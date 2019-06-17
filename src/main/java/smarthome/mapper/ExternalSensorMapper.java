package smarthome.mapper;

import smarthome.dto.ExternalSensorDTO;
import smarthome.model.ExternalSensor;

public class ExternalSensorMapper {

    private LocationMapper locationMapper= new LocationMapper();
    private SensorBehaviorMapper sensorBehaviorMapper = new SensorBehaviorMapper();

    /**
     * Converts an externalSensor into an externalSensorDTO
     *
     * @param externalSensor object to be converted into a DTO
     * @return externalSensorDTO
     */
    public ExternalSensorDTO toDto(ExternalSensor externalSensor){
        ExternalSensorDTO externalSensorDTO= new ExternalSensorDTO();
        externalSensorDTO.setId(externalSensor.getId());
        externalSensorDTO.setLocationDTO(locationMapper.toDTO(externalSensor.getLocation()));
        externalSensorDTO.setSensorBehaviorDTO(sensorBehaviorMapper.toDTO(externalSensor.getSensorBehavior()));
        externalSensorDTO.setIdGA(externalSensor.getIdGA());
        return externalSensorDTO;
    }

    public ExternalSensor toEntity (ExternalSensorDTO externalSensorDTO){
        ExternalSensor externalSensor= new ExternalSensor();
        externalSensor.setId(externalSensorDTO.getId());
        externalSensor.setLocation(locationMapper.toEntity(externalSensorDTO.getLocationDTO()));
        externalSensor.setSensorBehavior(sensorBehaviorMapper.toEntity(externalSensorDTO.getSensorBehaviorDTO()));
        externalSensor.setIdGA(externalSensorDTO.getIdGA());
        return externalSensor;
    }
}
