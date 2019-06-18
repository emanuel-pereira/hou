package smarthome.dto;

public class ExternalSensorDTO {
    private String id;
    private LocationDTO locationDTO;
    private SensorBehaviorDTO sensorBehaviorDTO;
    private String idGA;

    public ExternalSensorDTO() {
        this.locationDTO= new LocationDTO();
        this.sensorBehaviorDTO= new SensorBehaviorDTO();
    }

    public ExternalSensorDTO(String id, LocationDTO locationDTO, SensorBehaviorDTO sensorBehaviorDTO, String idGA) {
        this.id = id;
        this.locationDTO = locationDTO;
        this.sensorBehaviorDTO = sensorBehaviorDTO;
        this.idGA = idGA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public SensorBehaviorDTO getSensorBehaviorDTO() {
        return sensorBehaviorDTO;
    }

    public void setSensorBehaviorDTO(SensorBehaviorDTO sensorBehaviorDTO) {
        this.sensorBehaviorDTO = sensorBehaviorDTO;
    }

    public String getIdGA() {
        return idGA;
    }

    public void setIdGA(String idGA) {
        this.idGA = idGA;
    }
}
