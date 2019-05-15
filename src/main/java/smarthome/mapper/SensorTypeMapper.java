package smarthome.mapper;

import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorType;
import smarthome.model.validations.Name;

import java.util.List;
import java.util.stream.Collectors;

public class SensorTypeMapper {


    /**
     * Converts a sensorType into a sensorTypeDTO to be used as a model view.
     * @param sensorType to be converted in sensorTypeDTO
     * @return a sensorTypeDTO
     */
    public SensorTypeDTO toDto(SensorType sensorType) {
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO();
        Name type=sensorType.getType();
        String typeStr=type.getName();
        sensorTypeDTO.setSensorType(typeStr);
        return sensorTypeDTO;
    }

    public SensorType toEntity(SensorTypeDTO sensorTypeDTO){

        SensorType sensorType= new SensorType();
        sensorType.setType(sensorTypeDTO.getSensorType());
        return sensorType;
    }

    /**
     * Converts a list of sensor types into a list of sensor DTOs to be used as a model view
     * @param sensorTypes to be converted into a list of sensor types DTOs
     * @return a list of sensor type DTOs
     */
    public List<SensorTypeDTO> toDtoList(List<SensorType> sensorTypes) {
        return sensorTypes.stream().map(this::toDto).collect(Collectors.toList());
    }


}
