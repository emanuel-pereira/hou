package smarthome.dto;

import java.util.List;

public class GridDTO {

    private Long id;

    private String designation;

    private List<String> rooms;

    public GridDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(String newRoom){
        this.rooms.add(newRoom);
    }
}
