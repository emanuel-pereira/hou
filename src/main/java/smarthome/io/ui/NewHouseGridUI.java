package smarthome.io.ui;

import smarthome.controller.NewHouseGridCTRL;
import smarthome.model.House;
import smarthome.model.HouseGridList;

import java.util.Scanner;

public class NewHouseGridUI {

    private Scanner keyboard = new Scanner (System.in);
    private House mHouse;
    private HouseGridList mHGList;
    private NewHouseGridCTRL mCtrlUS130;

    public NewHouseGridUI(House house, HouseGridList hglist) {
        this.mHouse = house;
        this.mHGList = hglist;
        mCtrlUS130 = new NewHouseGridCTRL (house);
    }

    /**
     * Main method of the UI which presents the user's with the option to add a
     * new House Grid to the House Grids List
     * After the information is presented we return to the Main menu screen
     **/
    public void run() {
        String ID = null;
        if (mCtrlUS130.getHouseGridListSize () == 0)//todo create controller method
            System.out.println ("List of House Grids is empty, please insert one first. \n");
        else if (mCtrlUS130.getHouseGridListSize () != 0)
            System.out.println ("There already exist the following grids:\n" + mCtrlUS130.showGridsListInString ());
        while (ID == null) {
            System.out.println ("What is the grid name ID?");
            ID = keyboard.next ();
        }

        if (mCtrlUS130.createNewHouseGrid (ID)) {
            System.out.println ("It was created a new electric Grid successfully with the name: " + ID);
            System.out.println (mCtrlUS130.showGridsListInString ());
        }
    }
}
