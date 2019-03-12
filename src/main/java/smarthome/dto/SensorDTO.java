package smarthome.dto;

public class SensorDTO {

    private String id;
    private String designation;

    public SensorDTO(String id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

}
