package smarthome.io.ui;

import smarthome.controller.US145ListRoomsAttachedToGridCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class US145ListOfRoomsAttachedToGridUI {
    private House mHouse;
    private US145ListRoomsAttachedToGridCTRL mCtrlUS145;


    public US145ListOfRoomsAttachedToGridUI(House house) {
        mCtrlUS145 = new US145ListRoomsAttachedToGridCTRL(house);
        mHouse = house;
    }

    Scanner read = new Scanner(System.in);

    public void run() {
        int indexOfHouseGrid;
        while (true) {
            if (mCtrlUS145.getHouseGridList().size() != 0) {
                System.out.println("Choose a house grid from the list below to view the list of existing rooms attached to it:");
                System.out.println(mCtrlUS145.showHouseGridListInString());
                indexOfHouseGrid = read.nextInt();
                if (mCtrlUS145.getListOfRoomsWithHouseGrid(indexOfHouseGrid).size() != 0) {

                    if (indexOfHouseGrid > mHouse.getHouseGridList().size()) {
                        System.out.println("Please insert a valid option.\n");
                        break;
                    }
                    System.out.println("List of rooms attached to the HouseGrid:");
                    mCtrlUS145.showListOfRoomsWithHouseGridInString(indexOfHouseGrid);
                    System.out.println("Success");
                    break;
                }
                System.out.println("There are no rooms attached to this HouseGrid. Please attach one first in US147.");
                break;
            }
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.\n");
            break;
        }
    }
}
