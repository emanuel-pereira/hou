
package smarthome.io.ui;

import smarthome.controller.AttachDetachAndListRoomsInGridCTRL;

public class AttachDetachAndListRoomsInGridUI {

    private AttachDetachAndListRoomsInGridCTRL ctrl;
    private int indexOfHG;
    private int indexOfRoom;

    public AttachDetachAndListRoomsInGridUI() {
        this.ctrl = new AttachDetachAndListRoomsInGridCTRL();
    }

    public void checkIfHGListIsEmpty() {

        if (this.ctrl.getHouseGridListSize() == 0) {
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.");
            return;
        }
        this.checkIfRoomListIsEmpty();
    }

    private void checkIfRoomListIsEmpty() {
        if (this.ctrl.getListOfRoomsSize() == 0) {
            System.out.println("List of rooms is empty. Please insert a room in US105.");
            return;
        }
        this.selectOption();
    }

    private void selectOption() {
        int option = -1;
        while (option != 0) {
            System.out.println("Choose an option from the list below:");
            System.out.println("1 - Attach a Room to a House Grid");
            System.out.println("2 - List Rooms attached to a House Grid");
            System.out.println("3 - Detach a Room from a House Grid");
            System.out.println("0 - Exit");
            option = UtilsUI.requestIntegerInInterval(0, 3, "Please choose a valid option between 1 and 3, or 0 to exit.");
            switch (option) {
                case 0:
                    break;
                case 1:
                    this.selectHGToAttachRoom();
                    break;
                case 2:
                    this.selectHG();
                    break;
                case 3:
                    this.selectHGToDetachRoom();
                    break;
                default:
                    UtilsUI.printLnInsertValidOptionMsg();
            }
        }
    }

    private void selectHGToAttachRoom() {
        isValidIndexOfHG("attach a room");
        this.attachRoomToHouseGrid();
    }

    private void attachRoomToHouseGrid() {
        if (this.ctrl.getRoomsWithoutGridSize(this.indexOfHG) == 0) {
            System.out.println("All rooms in the house are already attached to the selected HouseGrid: " + this.ctrl.getHGName(this.indexOfHG) + ".");
            return;
        }
        this.chooseRoomToAttach();
    }

    private void chooseRoomToAttach() {
        isValidIndexOfRoom();
        this.ctrl.attachRoomToHouseGrid(this.indexOfHG, this.indexOfRoom);
        System.out.println("Room " + this.ctrl.getNameOfLastRoomInHG(this.indexOfHG) + " was successfully attached to HouseGrid: " + this.ctrl.getHGName(this.indexOfHG) + ".\n");
        showRoomsInHG();
    }


    private void showRoomsInHG() {
        System.out.println("List of rooms attached to " + this.ctrl.getHGName(this.indexOfHG) + ":");
        System.out.println(this.ctrl.showRoomsInHouseGrid(this.indexOfHG));
    }


    private void selectHG() {
        isValidIndexOfHG("list the rooms attached");
        this.listRoomsOfHG();
    }

    private void listRoomsOfHG() {
        if (hgRoomListIsEmpty())
            return;
        this.showRoomsInHG();
    }

    private boolean hgRoomListIsEmpty() {
        if (this.ctrl.getRoomListOfHGSize(this.indexOfHG) == 0) {
            System.out.println("List of rooms in HouseGrid is empty. Please attach one first.");
            return true;
        }
        return false;
    }


    private void selectHGToDetachRoom() {

        isValidIndexOfHG("detach a room from it");
        this.checkIfRoomListInHGIsEmpty();
    }

    private void checkIfRoomListInHGIsEmpty() {
        hgRoomListIsEmpty();
        this.selectRoomToDetach();
    }

    private void selectRoomToDetach() {
        isValidIndexOfRoomInHG();
        String roomName = this.ctrl.getRoomOfHGName(this.indexOfHG, this.indexOfRoom);
        this.ctrl.detachRoomFromGrid(this.indexOfHG, this.indexOfRoom);
        System.out.println("The room " + roomName + " was successfully detached from the houseGrid: " + this.ctrl.getHGName(this.indexOfHG) + ".\n");
        showRoomsInHG();
    }


    private void isValidIndexOfRoom() {
        System.out.println("Choose a room to attach from the list below:");
        System.out.println(this.ctrl.showRoomsWithoutHouseGrid(this.indexOfHG));
        this.indexOfRoom = UtilsUI.requestIntegerInInterval(1, this.ctrl.getRoomsWithoutGridSize(this.indexOfHG),
                "\"Please insert a valid option.\n" + this.ctrl.showRoomsWithoutHouseGrid(this.indexOfHG));
        indexOfRoom--;
    }

    private void isValidIndexOfRoomInHG() {
        System.out.println("Choose a room to detach from the list below:");
        System.out.println(this.ctrl.showRoomsInHouseGrid(this.indexOfHG));
        this.indexOfRoom = UtilsUI.requestIntegerInInterval(1, this.ctrl.getRoomListOfHGSize(this.indexOfHG),
                "\"Please insert a valid option.\n" + this.ctrl.showRoomsInHouseGrid(this.indexOfHG));
        indexOfRoom--;
    }

    private void isValidIndexOfHG(final String action) {

        System.out.println("Choose a house grid from the list below to " + action + " to it:");
        System.out.println(this.ctrl.showHouseGridListInString());
        this.indexOfHG = UtilsUI.requestIntegerInInterval(1, ctrl.getHouseGridListSize(),
                "Please insert a valid option.\n" + this.ctrl.showHouseGridListInString());
        indexOfHG--;
    }
}
