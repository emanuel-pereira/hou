package smarthome.dto;

import smarthome.model.GenericName;
import smarthome.model.Location;

import java.util.Calendar;
import java.util.List;

public class ExternalSensorDTO {
    private String id;
    private SensorBehaviorDTO sensorBehavior;
    private Location location;


    public ExternalSensorDTO(){

    }

    public ExternalSensorDTO(String id, String name,Calendar startDate,Location location,SensorTypeDTO sensorType,String unit, List<ExternalReadingDTO> readingListDTO) {
        this.id = id;
        this.location = location;
        GenericNameDTO sName = new GenericNameDTO(name);
        this.sensorBehavior = new SensorBehaviorDTO(sName,startDate,sensorType,unit,readingListDTO);
    }

    public SensorBehaviorDTO getSensorBehavior(){
        return this.sensorBehavior;
    }

    public void setSensorBehavior(SensorBehaviorDTO sensorBehavior){
        this.sensorBehavior = sensorBehavior;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

