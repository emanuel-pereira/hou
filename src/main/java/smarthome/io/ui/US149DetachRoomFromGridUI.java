package smarthome.io.ui;

import smarthome.controller.US149DettachRoomFromHouseGridCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class US149DetachRoomFromGridUI {
    private House mHouse;
    private US149DettachRoomFromHouseGridCTRL mCtrlUS149;


    public US149DetachRoomFromGridUI(House house) {
        mHouse = house;
        mCtrlUS149 = new US149DettachRoomFromHouseGridCTRL(house);
    }

    Scanner read = new Scanner(System.in);

    public void run() {
        int indexOfHouseGrid;
        int indexOfRoom;
        while (true) {
            if (mCtrlUS149.getHouseGridList().size() != 0) {
                System.out.println("Choose a house grid from the list below to view the list of existing rooms attached to it:");
                System.out.println(mCtrlUS149.showHouseGridListInString());
                indexOfHouseGrid = read.nextInt();
                if (mCtrlUS149.getListOfRoomsWithHouseGrid(indexOfHouseGrid).size() != 0) {

                    if (indexOfHouseGrid > mHouse.getHouseGridList().size()) {
                        System.out.println("Please insert a valid option \n.");
                        break;
                    }
                    System.out.println("Choose a room to detach from the list below:");
                    mCtrlUS149.getListOfRoomsWithHouseGrid(indexOfHouseGrid);
                    indexOfRoom = read.nextInt();
                    mCtrlUS149.detachRoomFromHouseGrid(indexOfHouseGrid, indexOfRoom);
                    System.out.println("Success.");
                    break;

                }
                System.out.println("There are no rooms attached to this HouseGrid. Please attach one first in US147UI.");
                break;
            }
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.");
            break;
        }
    }
}
