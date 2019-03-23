package smarthome.dto;

import smarthome.model.SensorType;

public class SensorTypeDTO {
    private String sensorType;



    public SensorTypeDTO(String sensorType) {
        this.sensorType = sensorType;

    }

    public String getSensorType() {
        return sensorType;
    }

    public SensorType fromDTO() {
        return new SensorType(this.sensorType);
    }
}