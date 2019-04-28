package smarthome.mapper;

import smarthome.dto.SensorDTO;
import smarthome.model.Sensor;
import smarthome.model.SensorList;

import java.util.List;
import java.util.stream.Collectors;

public class SensorMapper {

    private final SensorTypeMapper sensorTypeMapper = new SensorTypeMapper();

    /**
     * Converts a sensor into a sensorDTO to be used as a model view, containing only Id, Designation and SensorType as attributes.
     *
     * @param sensor to be converted in sensorDTO
     * @return a sensorDTO
     */
    public SensorDTO toDto(Sensor sensor) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setId(sensor.getId());
        sensorDTO.setDesignation(sensor.getDesignation());
        sensorDTO.setStartDate(sensor.getStartDate());
        sensorDTO.setSensorType(sensorTypeMapper.toDto(sensor.getSensorType()));
        return sensorDTO;
    }

    /**
     * Converts a list of sensors into a list of sensor DTOs to be used as a model view, containing only Id, Designation and type as attributes
     * @param sensorList to be converted into a list of sensors DTOs
     * @return a list of sensorDTOs
     */
    public List<SensorDTO> toDtoList(SensorList sensorList) {
        List<Sensor> listOfSensors=sensorList.getSensorList();
        return listOfSensors.stream().map(this::toDto).collect(Collectors.toList());
    }

}