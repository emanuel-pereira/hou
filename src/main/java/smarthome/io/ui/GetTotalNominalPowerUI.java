package smarthome.io.ui;

import smarthome.controller.GetTotalNominalPowerCTRL;
import smarthome.model.House;

public class GetTotalNominalPowerUI {

    private GetTotalNominalPowerCTRL controller;
    private int indexGrid;
    private int indexRoom;

    /**
     * User interface constructor
     *
     * @param house the current and only house
     */
    public GetTotalNominalPowerUI(House house) {
        controller = new GetTotalNominalPowerCTRL (house);
    }

    /**
     * Shows the total nominal power of a chosen grid.
     * But first this method checks if the grid list and the room list are empty.
     * If not, a string list is shown and the user selects the grid.
     * Before the total nominal power of the grid is shown, two more validation are made: check if there are rooms
     * and devices in the chosen grid.
     */
    public void getGridTotalNominalPowerUI() {
        this.checkIfGridListEmpty ();
        this.checkIfRoomListEmpty ();
        this.selectGrid ();
        this.checkIfRoomsExistsInGrid ();
        this.checkIfDevicesExistsInGrid ();
        System.out.println ("The total Nominal Power of this Grid is: " + this.controller.getGridTotalNominalPower (indexGrid) + "kW\n");
    }

    /**
     * Shows the total nominal power of a chosen room.
     * But first this method checks if the room list is empty.
     * If not, a string list is shown and the user selects the room.
     * Before the total nominal power of the grid is shown, one more validation is made: check if there are devices
     * in the chosen room.
     */
    public void getRoomTotalNominalPowerUI() {
        this.checkIfRoomListEmpty ();
        this.selectRoom ();
        this.checkIfDevicesExistsInRoom ();
        System.out.println ("The total Nominal Power of this Room is: " + this.controller.getRoomTotalNominalPower (indexRoom) + "kW\n");
    }

    /**
     * Checks if the grid list is empty by confirming if the size list is zero
     */
    private void checkIfGridListEmpty() {
        if (this.controller.getGridListSize () == 0) {
            System.out.println ("Please ask the Administrator to create a House Grid");
        }
    }

    /**
     * Checks if the room list is empty by confirming if the size list is zero
     */
    private void checkIfRoomListEmpty() {
        if (this.controller.getRoomListSize () == 0) {
            System.out.println ("Please ask the House Administrator to add Rooms");
        }
    }

    /**
     * A string list of grids is showed. A validation of the input is done by checking the size os the presented list.
     * The user selects the grid from this list.
     */
    private void selectGrid() {
        System.out.println ("Choose a grid from the list below:");
        System.out.println (this.controller.showGridListInString ());
        this.indexGrid = UtilsUI.requestIntegerInInterval (1, this.controller.getGridListSize (),
                "Not a valid option. Please select a grid from the list below:\n" + this.controller.showGridListInString ());
        this.indexGrid--;
    }

    /**
     * A string list of rooms is showed. A validation of the input is done by checking the size os the presented list.
     * The user selects the room from this list.
     */
    private void selectRoom() {
        System.out.println ("Choose a room from the list below:");
        System.out.println (this.controller.showRoomListInString ());
        this.indexRoom = UtilsUI.requestIntegerInInterval (1, this.controller.getRoomListSize (),
                "Not a valid option. Please select a room from the list below:\n" + this.controller.showRoomListInString ());
        this.indexRoom--;
    }

    /**
     * Before showing the total nominal power this method checks if there are any rooms attached to the grid
     */
    private void checkIfRoomsExistsInGrid() {

        if (this.controller.getSizeRoomListInGrid (this.indexGrid) == 0) {
            System.out.println ("Please ask the House Administrator to attach Rooms to a Grid ");
        }
    }

    /**
     * Before showing the total nominal power this method checks if there are devices added to the rooms in that grid
     */
    private void checkIfDevicesExistsInGrid() {
        if (this.controller.getSizeDeviceListInGrid (this.indexGrid) == 0) {
            System.out.println ("Please ask the House Administrator to add Devices to a Room");
        }
    }

    /**
     * Before showing the total nominal power this method checks if there are devices added to the rooms
     */
    private void checkIfDevicesExistsInRoom() {
        if (this.controller.getSizeDeviceListInRoom (this.indexRoom) == 0) {
            System.out.println ("Please ask the House Administrator to add Devices to the Room ");
        }
    }

}
