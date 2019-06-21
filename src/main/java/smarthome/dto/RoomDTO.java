package smarthome.dto;

public class RoomDTO {
    private String id;
    private String description;


    public RoomDTO(){}

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
