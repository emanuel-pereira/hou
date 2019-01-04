package smarthome.io.ui;

import smarthome.controller.US130newHouseGridController;
import smarthome.model.*;

import java.util.Scanner;

public class US130newHouseGridUI {

    private Scanner keyboard = new Scanner(System.in);
    private House mHouse;
    private US130newHouseGridController mCtrlUS130;

    public US130newHouseGridUI(House house) {
        this.mHouse = house;
        mCtrlUS130 = new US130newHouseGridController(house);
    }

    /**
     * Main method of the UI which presents the user's with the option to add a
     * new House Grid to the House Grids List
     * After the information is presented we return to the Main menu screen
     */
    public void run() {
        String ID = null;
        if (mCtrlUS130.getHouseGridList().size() == 0)
            System.out.println("List of House Grids is empty, please insert one first. \n");
        else if (mCtrlUS130.getHouseGridList().size() != 0)
            System.out.println("There already exist the following grids:\n" + mCtrlUS130.showGridsListInString());
        System.out.println("What is the maximum power output of the new grid of this house? (Press 0 to return to Main Menu)");
        int inputMaxPower = keyboard.nextInt();
        if (inputMaxPower == 0) //input 0 returns to main US's menu
            System.out.println("Return to Main Menu");
        while (inputMaxPower < 0) {
            System.out.println("Invalid power, please a positive power value... ");
            inputMaxPower = keyboard.nextInt();
        }
        while (ID == null) {
            System.out.println("What is the grid name ID?");
            ID = keyboard.next();
        }

        if (mCtrlUS130.createNewHouseGrid(inputMaxPower, mHouse, ID)) {
            System.out.println("It was created a new House Grid set with " + inputMaxPower + " kVA of Maximum Power for the house");
            System.out.println(mCtrlUS130.showGridsListInString());
        }
    }
}