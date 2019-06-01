package smarthome.io.ui;

import smarthome.controller.cli.EditRoomCTRL;
import smarthome.controller.cli.ListRoomsOfHouseCTRL;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;

import java.util.List;

public class EditRoomsUI {


    private final ListRoomsOfHouseCTRL ctrlUS108;
    private  EditRoomCTRL ctrlUS109;
    private RoomDTO room;
    private List<RoomDTO > roomList;
    private String roomId;
    private RoomDetailDTO editRoom;
    private Integer floor;
    private double length;
    private double width;
    private double height;



    public EditRoomsUI() {
        this.ctrlUS108 = new ListRoomsOfHouseCTRL();
        this.ctrlUS109 = new EditRoomCTRL();

    }

    public void run() throws NoSuchFieldException {
        if (this.ctrlUS108.roomListSize() == 0) {
            System.out.println ("There are no rooms. Please add a room to the house first.\n");
            UtilsUI.backToMenu();
            return;
        }
        this.selectRoom ();
    }

    private void selectRoom() throws NoSuchFieldException {
        System.out.println ("Choose a Room from the list below:");
        int counter = 1;
        for (RoomDTO room : this.ctrlUS108.findAll()) {
            System.out.print(counter++ + " Id: " + room.getID() + " | Description: " + room.getDescription() + "\n");
        }
        int index = UtilsUI.requestIntegerInInterval (1, (int) this.ctrlUS108.roomListSize (), "Please choose a valid option");
        index--;

        this.room = this.ctrlUS108.findAll().get(index);
        this.roomId = this.room.getID ();
        this.editFloor();

    }


    private void editFloor() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the floor?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the floor where the room is:");
            this.floor = UtilsUI.requestInteger("Please insert a number\nInsert the floor where the room is:");
            this.ctrlUS109.setFloor(this.roomId,this.floor);
            this.editLength();
        } else {

            this.editLength();
        }
    }

    private void editLength() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the length?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the length of the room (in meters):");
            this.length = UtilsUI.requestDoubleInInterval(1, 1000, "Please insert a number (higher than zero)\nInsert the length of the room (in meters):");
            this.ctrlUS109.setLength(this.roomId,this.length);
            this.editWidth();
        } else {
            this.editWidth();
        }
    }

    private void editWidth() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the width?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the width of the room (in meters):");
            this.width = UtilsUI.requestDoubleInInterval(1, 1000, "Please insert a number (higher than zero)\nInsert the width of the room (in meters):");
            this.ctrlUS109.setWidth(this.roomId,this.width);
            this.editHeight();
        } else {
            this.editHeight();
        }
    }

    private void editHeight() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the height?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the height of the room (in meters):");
            this.height = UtilsUI.requestDoubleInInterval(0, 1000, "Please insert a number (higher than zero)\nInsert the height of the room (in meters):");
            this.ctrlUS109.setHeight(this.roomId, this.height);
            UtilsUI.backToMenu();
        } else {
            UtilsUI.backToMenu();
        }
    }

}