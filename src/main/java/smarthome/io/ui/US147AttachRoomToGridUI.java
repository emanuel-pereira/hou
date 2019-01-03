package smarthome.io.ui;

import smarthome.controller.US145ListRoomsAttachedToGridCTRL;
import smarthome.controller.US147AttachRoomToHouseGridCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class US147AttachRoomToGridUI {
    private House mHouse;
    private US147AttachRoomToHouseGridCTRL mCtrlUS147;

    public US147AttachRoomToGridUI(House house) {
        mHouse = house;
        mCtrlUS147 = new US147AttachRoomToHouseGridCTRL(house);
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

                if (indexOfHouseGrid > mCtrlUS147.getHouseGridList().size()) {
                    System.out.println("Please insert a valid option \n.");
                    break;
                }

                if (mCtrlUS147.getListOfRoomsWithoutHouseGrid().size() != 0) {
                    System.out.println("Choose a room to attach from the list below:");
                    mCtrlUS147.listOfRoomsWithoutHouseGridInString();
                    indexOfRoom = read.nextInt();
                    mCtrlUS147.attachRoomToHouseGrid(indexOfHouseGrid, indexOfRoom);
                    System.out.println("Success.");
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
