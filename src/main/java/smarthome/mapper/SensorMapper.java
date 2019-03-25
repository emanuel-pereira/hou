package smarthome.mapper;

import smarthome.dto.SensorDTO;
import smarthome.model.Sensor;

public class SensorMapper {

    private SensorTypeMapper sensorTypeMapper= new SensorTypeMapper();

    /**
     * Converts a sensor into a sensorDTO to be used as a model view, containing only Id, Designation and SensorType as attributes.
     * @param sensor to be converted in sensorDTO
     * @return a sensorDTO
     */
    public SensorDTO toDto(Sensor sensor) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setId(sensor.getId());
        sensorDTO.setDesignation(sensor.getDesignation());
        sensorDTO.setSensorType(sensorTypeMapper.toDto(sensor.getSensorType()));
        return sensorDTO;
    }

}