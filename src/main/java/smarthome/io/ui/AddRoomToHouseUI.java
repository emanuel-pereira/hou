package smarthome.io.ui;

import smarthome.controller.AddRoomToHouseCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class AddRoomToHouseUI {

    Scanner read = new Scanner (System.in);

    private AddRoomToHouseCTRL uS105CTRL;

    private String name;
    private Integer floor;
    private double length;
    private double width;
    private double height;


    public AddRoomToHouseUI(House house) {
        uS105CTRL = new AddRoomToHouseCTRL (house);
    }

    /**
     * The first requested input is the name of the room. When the name is entered there is a confirmation to determine
     * if a room with the same name has already been created.
     * The method validates the user input according to the text validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The name is used to create a new Room.
     */
    void addRoomToHouse() {
        boolean condition = true;
        while (condition) {
            System.out.println ("Insert the name of the room:");
            name = UtilsUI.requestText ("Please insert alphanumeric characters!\nInsert the name of the room:", "^[A-Za-z0-9 -]+$");
            if (!uS105CTRL.checkIfRoomNameExists (name)) {
                condition = false;
                this.insertFloor ();
            } else
                System.out.println ("There's a room with that name");
        }
    }

    /**
     * The second requested input is the floor of the room.
     * The method validates the user input according to the integer validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The floor is used to create a new Room.
     */
    private void insertFloor() {
        System.out.println ("Insert the floor where the room is:");
        floor = UtilsUI.requestInteger ("Please insert a number\nInsert the floor where the room is:");
        this.insertLength ();
    }

    /**
     * The third requested input is the length of the room.
     * The method validates the user input according to the double validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The length is used to create a new Room
     */
    private void insertLength() {
        System.out.println ("Insert the length of the room (in meters):");
        length = UtilsUI.requestDoubleInInterval (1, 1000, "Please insert a number (higher than zero)\nInsert the length of the room (in meters):");
        this.insertWidth ();
    }

    /**
     * The fourth requested input is the width of the room.
     * The method validates the user input according to the double validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The width is used to create a new Room.
     */
    private void insertWidth() {
        System.out.println ("Insert the width of the room (in meters):");
        width = UtilsUI.requestDoubleInInterval (1, 1000, "Please insert a number (higher than zero)\nInsert the width of the room (in meters):");
        this.insertHeight ();
    }

    /**
     * The fourth requested input is the height of the room.
     * The method validates the user input according to the double validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The height is used to create a new Room.
     */
    private void insertHeight() {
        System.out.println ("Insert the height of the room (in meters). If exterior insert 0:");
        height = UtilsUI.requestDoubleInInterval (0, 1000, "Please insert a number (higher than zero)\nInsert the height of the room (in meters):");
        this.addNewRoom ();
    }

    /**
     * The final method uses the previous inputs has parameters to create a new Room.
     * A success message is shown with all the details of the room.
     */
    private void addNewRoom() {
        if (uS105CTRL.newAddRoom (name, floor, length, width, height)) {
            System.out.println ("Success. The " + name + " on the " + floor + " floor with " + height + "m of height and " + length * width + "m² was created.\n");
        } else {
            System.out.println ("Fail! Please try again.");
        }
    }

}
