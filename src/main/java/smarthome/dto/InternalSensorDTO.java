package smarthome.dto;

import java.util.Calendar;
import java.util.List;

public class InternalSensorDTO {
    private String id;
    private ExternalSensorBehaviorDTO sensorBehavior;


    public InternalSensorDTO(){

    }

    public InternalSensorDTO(String id, String name, Calendar startDate, SensorTypeDTO sensorType, String unit, List<ExternalReadingDTO> readingListDTO) {
        this.id = id;
        GenericNameDTO sName = new GenericNameDTO(name);
        this.sensorBehavior = new ExternalSensorBehaviorDTO(sName,startDate,sensorType,unit,readingListDTO);
    }

    public ExternalSensorBehaviorDTO getSensorBehavior(){
        return this.sensorBehavior;
    }

    public void setSensorBehavior(ExternalSensorBehaviorDTO sensorBehavior){
        this.sensorBehavior = sensorBehavior;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
