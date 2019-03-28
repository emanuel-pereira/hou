package smarthome.mapper;

import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorType;

public class SensorTypeMapper {


    /**
     * Converts a sensorType into a sensorTypeDTO to be used as a model view.
     * @param sensorType to be converted in sensorTypeDTO
     * @return a sensorTypeDTO
     */
    public SensorTypeDTO toDto(SensorType sensorType) {
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO();
        sensorTypeDTO.setSensorType(sensorType.getType());
        return sensorTypeDTO;
    }
}
