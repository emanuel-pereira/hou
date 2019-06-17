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
        if (sensorType.getId()!=null) {
            sensorTypeDTO.setId(sensorType.getId());
        }
        sensorTypeDTO.setType(typeStr);

        return sensorTypeDTO;
    }

    public SensorType toEntity(SensorTypeDTO sensorTypeDTO){

        SensorType sensorType= new SensorType();
        if(sensorTypeDTO.getId()!=0){
            sensorType.setId(sensorTypeDTO.getId());
        }
        sensorType.setType(sensorTypeDTO.getType());
        return sensorType;
    }
}
