package smarthome.io.ui;

import smarthome.controller.GetTotalNominalPowerCTRL;

public class GetTotalNominalPowerUI {

    private GetTotalNominalPowerCTRL controller;
    private int indexGrid;
    private int indexRoom;

    /**
     * User interface constructor
     */
    public GetTotalNominalPowerUI() {
        controller = new GetTotalNominalPowerCTRL();
    }

    /**
     * Shows the total nominal power of a chosen grid.
     * But first this method checks if the grid list and the room list are empty.
     * If not, a string list is shown and the user selects the grid.
     * Before the total nominal power of the grid is shown, two more validation are made: check if there are rooms
     * and devices in the chosen grid.
     */
    public void getGridTotalNominalPower() {
        if (this.checkIfGridListEmpty()) {
            return;
        }
        if (this.checkIfRoomListEmpty()) {
            return;
        }
        this.selectGrid();
        if (this.checkIfRoomsExistsInGrid()) {
            return;
        }
        if (this.checkIfDevicesExistsInGrid()) {
            return;
        }
        System.out.println("The total Nominal Power of this Grid is: " + this.controller.getGridTotalNominalPower(indexGrid) + "kW\n");
    }

    /**
     * Shows the total nominal power of a chosen room.
     * But first this method checks if the room list is empty.
     * If not, a string list is shown and the user selects the room.
     * Before the total nominal power of the grid is shown, one more validation is made: check if there are devices
     * in the chosen room.
     */
    public void getRoomTotalNominalPower() {
        if (this.checkIfRoomListEmpty()) {
            return;
        }
        this.selectRoom();
        if (this.checkIfDevicesExistsInRoom()) {
            return;
        }
        System.out.println("The total Nominal Power of this Room is: " + this.controller.getRoomTotalNominalPower(indexRoom) + "kW\n");
    }

    /**
     * Checks if the grid list is empty by confirming if the size list is zero
     */
    private boolean checkIfGridListEmpty() {
        if (this.controller.getGridListSize() == 0) {
            System.out.println("Please ask the Administrator to create a House Grid\n");
            return true;
        }
        return false;
    }

    /**
     * Checks if the room list is empty by confirming if the size list is zero
     */
    private boolean checkIfRoomListEmpty() {
        if (this.controller.getRoomListSize() == 0) {
            System.out.println("Please ask the House Administrator to add Rooms\n");
            return true;
        }
        return false;
    }

    /**
     * A string list of grids is showed. A validation of the input is done by checking the size os the presented list.
     * The user selects the grid from this list.
     */
    private void selectGrid() {
        System.out.println("Choose a grid from the list below:");
        System.out.println(this.controller.showGridListInString());
        this.indexGrid = UtilsUI.requestIntegerInInterval(1, this.controller.getGridListSize(),
                "Not a valid option. Please select a grid from the list below:\n" + this.controller.showGridListInString());
        this.indexGrid--;
    }

    /**
     * A string list of rooms is showed. A validation of the input is done by checking the size os the presented list.
     * The user selects the room from this list.
     */
    private void selectRoom() {
        System.out.println("Choose a room from the list below:");
        System.out.println(this.controller.showRoomListInString());
        this.indexRoom = UtilsUI.requestIntegerInInterval(1, this.controller.getRoomListSize(),
                "Not a valid option. Please select a room from the list below:\n" + this.controller.showRoomListInString());
        this.indexRoom--;
    }

    /**
     * Before showing the total nominal power this method checks if there are any rooms attached to the grid
     */
    private boolean checkIfRoomsExistsInGrid() {
        if (this.controller.getSizeRoomListInGrid(this.indexGrid) == 0) {
            System.out.println("Please ask the House Administrator to attach Rooms to a Grid\n");
            return true;
        }
        return false;
    }

    /**
     * Before showing the total nominal power this method checks if there are devices added to the rooms in that grid
     */
    private boolean checkIfDevicesExistsInGrid() {
        if (this.controller.getSizeDeviceListInGrid(this.indexGrid) == 0) {
            System.out.println("Please ask the House Administrator to add devices to a Room\n");
            return true;
        }
        return false;
    }

    /**
     * Before showing the total nominal power this method checks if there are devices added to the rooms
     */
    private boolean checkIfDevicesExistsInRoom() {
        if (this.controller.getSizeDeviceListInRoom(this.indexRoom) == 0) {
            System.out.println("Please ask the House Administrator to add devices to the Room\n");
            return true;
        }
        return false;
    }

}
