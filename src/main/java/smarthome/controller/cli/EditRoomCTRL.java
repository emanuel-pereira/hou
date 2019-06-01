package smarthome.controller.cli;

import smarthome.dto.RoomDetailDTO;
import smarthome.services.RoomService;

public class EditRoomCTRL {

    private final RoomService roomService;
    private RoomDetailDTO editRoom;


    public EditRoomCTRL() {
        this.roomService = new RoomService();
    }

    public RoomDetailDTO findById(String id) throws NoSuchFieldException {
        return this.roomService.findById(id);
    }

      public void setFloor(String id, Integer floor) throws NoSuchFieldException {
        this.roomService.setFloor(id,floor);
    }

    public void setLength(String id, double length) throws NoSuchFieldException {
        this.roomService.setLength(id, length);
    }

    public void setWidth(String id, double width) throws NoSuchFieldException {
        this.roomService.setWidth(id,width);
    }

    public void setHeight(String id, double height) throws NoSuchFieldException {
        this.roomService.setHeight(id,height);
    }

}


