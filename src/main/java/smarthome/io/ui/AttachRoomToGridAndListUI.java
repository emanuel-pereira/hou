
package smarthome.io.ui;

import smarthome.controller.AttachRoomToGridAndListCTRL;
import smarthome.model.House;
import smarthome.model.Room;

import java.util.Scanner;

public class AttachRoomToGridAndListUI {
    private House mHouse;
    private AttachRoomToGridAndListCTRL mCtrl;

    public AttachRoomToGridAndListUI(House house) {
        mHouse = house;
        mCtrl = new AttachRoomToGridAndListCTRL(house);
    }


    Scanner read = new Scanner(System.in);
    private int mIndexOfHG;
    private int mIndexOfRoom;
    private String mInsertValidOptionMsg = "Please insert a valid option \n.";


    public void checkIfHGListIsEmtpy() {

        if (mCtrl.getHouseGridListSize() == 0) {
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.");
            return;
        }
        this.checkIfRoomListIsEmtpy();
    }

    private void checkIfRoomListIsEmtpy() {
        if (mCtrl.getListOfRoomsSize() == 0) {
            System.out.println("List of rooms is empty. Please insert a room in US105.");
            return;
        }
        this.selectOption();
    }

    private void selectOption() {
        int option = -1;
        while (option != 0) {
            //attach and list rooms attached to that grid
            System.out.println("Choose an option from the list below:");
            System.out.println("1 - Attach a Room to a House Grid");
            //user selects grid and shows list of rooms attached to that grid
            System.out.println("2 - List Rooms attached to a House Grid");
            //detach and list rooms attached to that grid
            System.out.println("3 - Detach a Room from a House Grid");
            System.out.println("0 - Exit");
            option = read.nextInt();
            read.nextLine();
            switch (option) {
                case 1:
                    this.attachRoomToHouseGrid();
                    break;
                case 2:
                    this.selectHGAndListRooms();
                    break;
                case 3:
                    this.detachRoomFromHouseGrid();
                    break;
                default:
                    System.out.println("Please choose a valid option.");
            }
        }
    }

    private void attachRoomToHouseGrid() {
        while (true) {
            if (selectHouseGrid("attach a room")) break;
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
        System.out.println("Choose a room to attach from the list below:");
        System.out.println(mCtrl.showRoomsWithoutHouseGrid(mIndexOfHG));
        readIndexOfRoom();
        mCtrl.attachRoomToHouseGrid(mIndexOfHG, mIndexOfRoom);
        System.out.println("Room " + mCtrl.getNameOfLastRoomInHG(mIndexOfHG) + " was successfully attached to HouseGrid: " + mCtrl.getHGName(mIndexOfHG) +".\n");
        showRoomsInHG();
        return;
    }

    private void readIndexOfRoom() {
        mIndexOfRoom = read.nextInt();
        read.nextLine();
    }

    private void showRoomsInHG() {
        System.out.println("List of rooms attached to "+mCtrl.getHGName(mIndexOfHG)+":");
        System.out.println(mCtrl.showRoomsInHouseGrid(mIndexOfHG));
    }


    public void selectHGAndListRooms() {
        while (true) {
            if (selectHouseGrid("list the rooms attached")) break;
            if (HGRoomListIsEmpty()) break;
            showRoomsInHG();
            return;
        }
    }

    private boolean HGRoomListIsEmpty() {
        if (mCtrl.getRoomListOfHGSize(mIndexOfHG) == 0) {
            System.out.println("List of rooms in HouseGrid is empty. Please attach one first.");
            return true;
        }
        return false;
    }


    private void detachRoomFromHouseGrid() {
        while (true) {
            if (selectHouseGrid("detach a room from it")) break;
            if (mCtrl.getListOfRoomsSize() == 0) {
                System.out.println("All rooms in the house are already attached to the selected HouseGrid: " + mCtrl.getHGName(mIndexOfHG) + ".");
                break;
            }
            if (HGRoomListIsEmpty()) break;
            System.out.println("Choose a room to detach from the list below:");
            System.out.println(mCtrl.showRoomsInHouseGrid(mIndexOfHG));
            readIndexOfRoom();
            String roomName=mCtrl.getRoomOfHGName(mIndexOfHG, mIndexOfRoom);
            mCtrl.detachRoomFromGrid(mIndexOfHG, mIndexOfRoom);
            System.out.println("The room "+roomName+" was successfully detached from the houseGrid: " + mCtrl.getHGName(mIndexOfHG)+".\n");
            showRoomsInHG();
            return;
        }
    }

    private boolean selectHouseGrid(final String action) {
        System.out.println("Choose a house grid from the list below to " + action + " to it:");
        System.out.println(mCtrl.showHouseGridListInString());
        mIndexOfHG = read.nextInt();
        read.nextLine();

        if (mIndexOfHG > mCtrl.getHouseGridListSize()) {
            System.out.println(mInsertValidOptionMsg);
            return true;
        }
        return false;
    }


}
