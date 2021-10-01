package smarthome.io.ui;

import smarthome.controller.cli.AddRoomToHouseCTRL;

public class AddRoomToHouseUI {

    private final AddRoomToHouseCTRL controller;

    private String id;
    private String description;
    private Integer floor;
    private double length;
    private double width;
    private double height;

    /**
     * User interface constructor
     *
     */
    public AddRoomToHouseUI() {
        this.controller = new AddRoomToHouseCTRL();
    }

    /**
     * The first requested input is the id of the room. When the id is entered there is a confirmation to determine
     * if a room with the same id has already been created.
     * The method validates the user input according to the text validation rules. If not, the cycle warns for the need
     * for a new input until correct validation.
     * The id is used to create a new Room.
     */
    void addRoomToHouse() {
        boolean condition = true;
        while (condition) {
            System.out.println("Insert the Id of the room:");
            this.id = UtilsUI.requestText("Please insert alphanumeric characters without spaces!\nInsert the Id of the room:", "^[A-Za-z0-9]+$");
            if (!this.controller.checkIfRoomIdExists(this.id)) {
                condition = false;
                this.insertDescription();
            } else{
                System.out.println("The Id already exists");
                UtilsUI.backToMenu();
            }
        }
    }

    /**
     * When the description is entered the method validates the user input according to the text validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     */
    private void insertDescription() {
            System.out.println("Insert the description of the room:");
            this.description = UtilsUI.requestText("Please insert alphanumeric characters!\nInsert the description of the room:", "^[A-Za-z0-9 -]+$");
            this.insertFloor();
    }

    /**
     * The second requested input is the floor of the room.
     * The method validates the user input according to the integer validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The floor is used to create a new Room.
     */
    private void insertFloor() {
        System.out.println("Insert the floor where the room is:");
        this.floor = UtilsUI.requestInteger("Please insert a number\nInsert the floor where the room is:");
        this.insertLength();
    }

    /**
     * The third requested input is the length of the room.
     * The method validates the user input according to the double validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The length is used to create a new Room
     */
    private void insertLength() {
        System.out.println("Insert the length of the room (in meters):");
        this.length = UtilsUI.requestDoubleInInterval(1, 1000, "Please insert a number (higher than zero)\nInsert the length of the room (in meters):");
        this.insertWidth();
    }

    /**
     * The fourth requested input is the width of the room.
     * The method validates the user input according to the double validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The width is used to create a new Room.
     */
    private void insertWidth() {
        System.out.println("Insert the width of the room (in meters):");
        this.width = UtilsUI.requestDoubleInInterval(1, 1000, "Please insert a number (higher than zero)\nInsert the width of the room (in meters):");
        this.typeOfRoom();
    }

    private void typeOfRoom() {
        int option;
        System.out.println("Choose the type of room:");
        System.out.println("Click 1. Interior space");
        System.out.println("Click 2. Exterior space (outdoor)");
        option = UtilsUI.requestIntegerInInterval(1, 2, "Please choose an option between 1 and 2");
        switch (option) {
            case 1:
                this.insertHeight();
                break;
            case 2:
                this.height = 0;
                this.addNewRoom();
                break;
            default:
        }
    }

    /**
     * The fourth requested input is the height of the room.
     * The method validates the user input according to the double validation rules. If not the cycle warns for the need
     * for a new input until correct validation.
     * The height is used to create a new Room.
     */
    private void insertHeight() {
        System.out.println("Insert the height of the room (in meters):");
        this.height = UtilsUI.requestDoubleInInterval(0, 1000, "Please insert a number (higher than zero)\nInsert the height of the room (in meters):");
        this.addNewRoom();
    }

    /**
     * The final method uses the previous inputs has parameters to create a new Room.
     * A success message is shown with all the details of the room.
     */
    private void addNewRoom() {
        if (this.controller.newAddRoom(this.id, this.description, this.floor, this.length, this.width, this.height)) {
            System.out.println("Success. The " + this.description + " with the Id " + this.id + " on the " + this.floor + " floor " + this.showHeight() + this.length * this.width + "m² was created.\n");
            UtilsUI.backToMenu();
        }
        else {
            System.out.println("Fail! Please try again.");
            UtilsUI.backToMenu();
        }
    }

    private String showHeight() {
        if (this.height > 0) {
            return ("with " + height + "m of height and ");
        }
        return ("with ");
    }

}
