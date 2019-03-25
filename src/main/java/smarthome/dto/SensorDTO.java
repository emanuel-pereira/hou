package smarthome.dto;

import smarthome.model.Location;
import smarthome.model.SensorType;

import java.util.List;


public class SensorDTO {

    private String id;
    private String designation;
    private SensorTypeDTO sensorTypeDTO;
    private Location location;
    private List<ReadingDTO> readingListDTO;

    public SensorDTO(){}
    public SensorDTO(String id, String designation, List<ReadingDTO> readingListDTO) {
        this.id = id;
        this.designation = designation;
        this.readingListDTO = readingListDTO;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SensorTypeDTO getSensorType() {
        return sensorTypeDTO;
    }

    public void setSensorType(SensorTypeDTO sensorTypeDTO) {
        this.sensorTypeDTO = sensorTypeDTO;
    }
}
