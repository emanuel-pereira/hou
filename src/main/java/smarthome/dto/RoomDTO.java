package smarthome.dto;

public class RoomDTO {
    private String id;
    private String name;

    public RoomDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomDTO(){}

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
