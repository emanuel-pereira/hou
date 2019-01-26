
package smarthome.io.ui;

import smarthome.controller.AttachDetachAndListRoomsInGridCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class AttachDetachAndListRoomsInGridUI {

    private AttachDetachAndListRoomsInGridCTRL mCtrl;
    private Scanner mRead = new Scanner(System.in);
    private int mIndexOfHG;
    private int mIndexOfRoom;

    public AttachDetachAndListRoomsInGridUI(House house) {
        mCtrl = new AttachDetachAndListRoomsInGridCTRL(house);
    }

    public void checkIfHGListIsEmpty() {

        if (mCtrl.getHouseGridListSize() == 0) {
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.");
            return;
        }
        this.checkIfRoomListIsEmpty();
    }

    private void checkIfRoomListIsEmpty() {
        if (mCtrl.getListOfRoomsSize() == 0) {
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
            option = mRead.nextInt();
            mRead.nextLine();
            switch (option) {
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
                    printLnInsertValidOption();
            }
        }
    }

    private void selectHGToAttachRoom() {
        isValidIndexOfHG("attach a room");
        this.attachRoomToHouseGrid();
    }

    private void attachRoomToHouseGrid() {
        while (true) {
            if (mCtrl.getRoomsWithoutGridSize(mIndexOfHG) == 0) {
                System.out.println("All rooms in the house are already attached to the selected HouseGrid: " + mCtrl.getHGName(mIndexOfHG) + ".");
                break;
            }
            this.chooseRoomToAttach();
            break;
        }
        return;
    }

    private void chooseRoomToAttach() {
            isValidIndexOfRoom();
            mCtrl.attachRoomToHouseGrid(mIndexOfHG, mIndexOfRoom);
            System.out.println("Room " + mCtrl.getNameOfLastRoomInHG(mIndexOfHG) + " was successfully attached to HouseGrid: " + mCtrl.getHGName(mIndexOfHG) + ".\n");
            showRoomsInHG();
            return;
    }


    private void showRoomsInHG() {
        System.out.println("List of rooms attached to " + mCtrl.getHGName(mIndexOfHG) + ":");
        System.out.println(mCtrl.showRoomsInHouseGrid(mIndexOfHG));
    }


    private void selectHG() {
        isValidIndexOfHG("list the rooms attached");
        this.listRoomsOfHG();
    }

    private void listRoomsOfHG() {
        if (hgRoomListIsEmpty())
            return;
        showRoomsInHG();
        return;
    }

    private boolean hgRoomListIsEmpty() {
        if (mCtrl.getRoomListOfHGSize(mIndexOfHG) == 0) {
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
        return;
    }

    private void selectRoomToDetach() {
        isValidIndexOfRoomInHG();
        String roomName = mCtrl.getRoomOfHGName(mIndexOfHG, mIndexOfRoom);
        mCtrl.detachRoomFromGrid(mIndexOfHG, mIndexOfRoom);
        System.out.println("The room " + roomName + " was successfully detached from the houseGrid: " + mCtrl.getHGName(mIndexOfHG) + ".\n");
        showRoomsInHG();
        return;
    }


    private void printLnInsertValidOption() {
        System.out.println("Please insert a valid option.");
    }

    private boolean isValidIndexOfRoom() {
        while (true) {
            System.out.println("Choose a room to attach from the list below:");
            System.out.println(mCtrl.showRoomsWithoutHouseGrid(mIndexOfHG));
            mIndexOfRoom = mRead.nextInt();
            mRead.nextLine();
            if (!(mIndexOfRoom > mCtrl.getRoomsWithoutGridSize(mIndexOfHG))) {
                break;
            }
            printLnInsertValidOption();

        }
        return true;
    }

    private boolean isValidIndexOfRoomInHG() {
        while (true) {
            System.out.println("Choose a room to detach from the list below:");
            System.out.println(mCtrl.showRoomsInHouseGrid(mIndexOfHG));
            mIndexOfRoom = mRead.nextInt();
            mRead.nextLine();

            if (!(mIndexOfRoom > mCtrl.getRoomListOfHGSize(mIndexOfHG))) {
                break;
            }
            printLnInsertValidOption();
        }
        return true;
    }

    private boolean isValidIndexOfHG(final String action) {
        while (true) {
            System.out.println("Choose a house grid from the list below to " + action + " to it:");
            System.out.println(mCtrl.showHouseGridListInString());
            mIndexOfHG = mRead.nextInt();
            mRead.nextLine();
            if (!(mIndexOfHG > mCtrl.getHouseGridListSize())) {
                break;
            }
            printLnInsertValidOption();
        }
        return true;
    }
}
