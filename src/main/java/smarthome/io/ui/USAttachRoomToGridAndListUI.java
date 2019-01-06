package smarthome.io.ui;

import smarthome.controller.USAttachRoomToGridAndListCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class USAttachRoomToGridAndListUI {
    private House mHouse;
    private USAttachRoomToGridAndListCTRL mCtrlUS147;

    public USAttachRoomToGridAndListUI(House house) {
        mHouse = house;
        mCtrlUS147 = new USAttachRoomToGridAndListCTRL(house);
    }

    Scanner read = new Scanner(System.in);

    public void run() {
        int indexOfHouseGrid;
        int indexOfRoom;
        while (true) {
            if (mCtrlUS147.getHouseGridList().size() != 0) {
                System.out.println("Choose a house grid from the list below to attach a room to it:");
                System.out.println(mCtrlUS147.showHouseGridListInString());
                indexOfHouseGrid = read.nextInt();
                read.nextLine();

                if (indexOfHouseGrid > mCtrlUS147.getHouseGridList().size()) {
                    System.out.println("Please insert a valid option \n.");
                    break;
                }

                if (mCtrlUS147.getListOfRoomsWithoutHouseGrid().size() != 0) {
                    System.out.println("Choose a room to attach from the list below:");
                    System.out.println(mCtrlUS147.showRoomsWithoutHouseGridInStr());
                    indexOfRoom = read.nextInt();
                    mCtrlUS147.attachRoomToHouseGrid(indexOfHouseGrid, indexOfRoom);
                    System.out.println("Room " + mHouse.getRoomList().get(indexOfRoom - 1).getName() + " was successfully attached to HouseGrid: " + mHouse.getHouseGridList().get(indexOfHouseGrid - 1).getGridID() + " | Nominal Power: " + mHouse.getHouseGridList().get(indexOfHouseGrid - 1).getContractedMaximumPower());
                    System.out.println("List of rooms attached to HouseGrid: ");
                    System.out.println(mCtrlUS147.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                    break;
                }
                System.out.println("There are no rooms without HouseGrid. Please detach one first.");
                break;
            }
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.");
            break;
        }
    }
}
