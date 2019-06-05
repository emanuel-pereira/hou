package smarthome.controller.cli;

import smarthome.dto.RoomDetailDTO;
import smarthome.services.RoomService;

public class EditRoomCTRL {

    private final RoomService roomService;

    /**
     * Controller constructor
     *
     */
    public EditRoomCTRL() {
        this.roomService = new RoomService();
    }

    /**
     * Finds a specific room by the Id
     * @param id Id of the room
     * @return RoomDetailDTO with id, description, floor, length, width and height
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public RoomDetailDTO findById(String id) throws NoSuchFieldException {
        return this.roomService.findById(id);
    }

    /**
     * Changes the floor of a specific room
     * @param id Id of the room
     * @param floor New floor value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
      public void setFloor(String id, Integer floor) throws NoSuchFieldException {
        this.roomService.setFloor(id,floor);
    }

    /**
     * Changes the length of a room
     * @param id Id of a room
     * @param length New length value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void setLength(String id, double length) throws NoSuchFieldException {
        this.roomService.setLength(id, length);
    }

    /**
     * Changes the width of a room
     * @param id Id of a room
     * @param width New width value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void setWidth(String id, double width) throws NoSuchFieldException {
        this.roomService.setWidth(id,width);
    }

    /**
     * Depending on the changes made or not to the length and width, the area will be updated
     * @param id Id of a room
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void updateArea(String id) throws NoSuchFieldException {
        this.roomService.updateArea(id);
    }


    /**
     * Changes the height of the room
     * @param id Id of the room
     * @param height New height value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void setHeight(String id, double height) throws NoSuchFieldException {
        this.roomService.setHeight(id,height);
    }

}


