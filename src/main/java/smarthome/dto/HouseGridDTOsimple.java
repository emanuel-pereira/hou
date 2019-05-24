package smarthome.dto;

import java.util.ArrayList;
import java.util.List;

public class HouseGridDTOsimple {

    private String name;

    private List<String> rooms;

    public HouseGridDTOsimple() {
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRooms() {
        return this.rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public void addRoomId(String newroom){
        this.rooms.add(newroom);
    }
}
