package smarthome.mapper;

import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorType;
import smarthome.model.validations.Name;

public class SensorTypeMapper {
    /**
     * Converts a sensorType into a sensorTypeDTO to be used as a model view.
     * @param sensorType to be converted in sensorTypeDTO
     * @return a sensorTypeDTO
     */
    public SensorTypeDTO toDto(SensorType sensorType) {
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO();
        Name type = sensorType.getType();
        String typeStr = type.getName();
        sensorTypeDTO.setType(typeStr);

        return sensorTypeDTO;
    }

    public SensorType toEntity(SensorTypeDTO sensorTypeDTO){

        SensorType sensorType= new SensorType();
        sensorType.setType(sensorTypeDTO.getType());
        return sensorType;
    }
}
