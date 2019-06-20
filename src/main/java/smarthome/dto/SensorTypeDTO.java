
package smarthome.dto;

public class SensorTypeDTO {

    private String type;

    public SensorTypeDTO(){}

    public SensorTypeDTO(String sensorType){
        this.type = sensorType;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}