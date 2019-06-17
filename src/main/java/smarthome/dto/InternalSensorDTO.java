package smarthome.dto;

public class InternalSensorDTO {
    private String id;
    private SensorBehaviorDTO sensorBehaviorDTO;
    private String roomId;


    public InternalSensorDTO(){

    }

    public SensorBehaviorDTO getSensorBehavior(){
        return this.sensorBehaviorDTO;
    }

    public void setSensorBehavior(SensorBehaviorDTO sensorBehaviorDTO){
        this.sensorBehaviorDTO = sensorBehaviorDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
