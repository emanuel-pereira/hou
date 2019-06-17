package smarthome.mapper;

import smarthome.dto.InternalSensorDTO;
import smarthome.model.InternalSensor;

public class InternalSensorMapper {
    private SensorBehaviorMapper sensorBehaviorMapper = new SensorBehaviorMapper();

    /**
     * Converts an internalSensor into an internalSensorDTO
     *
     * @param internalSensor object to be converted into a DTO
     * @return internalSensorDTO
     */
    public InternalSensorDTO toDto(InternalSensor internalSensor){
        InternalSensorDTO internalSensorDTO= new InternalSensorDTO();
        internalSensorDTO.setId(internalSensor.getId());
        internalSensorDTO.setSensorBehavior(sensorBehaviorMapper.toDTO(internalSensor.getSensorBehavior()));
        internalSensorDTO.setRoomId(internalSensor.getRoomId());
        return internalSensorDTO;
    }

    /**
     * Converts an InternalSensorDTO into an InternalSensorObject
     * @param internalSensorDTO to be mapped to the respective model entity
     * @return InternalSensor
     */
    public InternalSensor toEntity (InternalSensorDTO internalSensorDTO){
        InternalSensor internalSensor= new InternalSensor();
        internalSensor.setId(internalSensorDTO.getId());
        internalSensor.setSensorBehavior(sensorBehaviorMapper.toEntity(internalSensorDTO.getSensorBehavior()));
        internalSensor.setRoom(internalSensorDTO.getRoomId());
        return internalSensor;
    }
}
