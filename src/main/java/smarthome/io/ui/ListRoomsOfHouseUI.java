package smarthome.io.ui;

import smarthome.controller.cli.ListRoomsOfHouseCTRL;
import smarthome.dto.RoomDTO;

public class ListRoomsOfHouseUI {


    private final ListRoomsOfHouseCTRL mCtrlUS108;


    public ListRoomsOfHouseUI() {
        this.mCtrlUS108 = new ListRoomsOfHouseCTRL();

    }

    public void run() {
        if (mCtrlUS108.roomListSize () != 0) {
            System.out.println ("The Rooms of the house are:");
            System.out.println (this.showRooms());
            UtilsUI.backToMenu();
        }
        else {
            System.out.println ("There are no rooms. Please add a room to the house");
            UtilsUI.backToMenu();
        }
    }

    private String showRooms() {
        Iterable<RoomDTO> rooms = this.mCtrlUS108.findAll();
        StringBuilder result = new StringBuilder();
        for (RoomDTO room : rooms) {
            result.append("Id: ");
            result.append(room.getID());
            result.append(" | Description: ");
            result.append(room.getDescription());
            result.append("\n");
        }
        return result.toString();
    }
}




