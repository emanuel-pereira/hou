package smarthome.io.ui;

import smarthome.controller.NewHouseGridCTRL;

import java.util.Scanner;

public class NewHouseGridUI {

    private Scanner keyboard = new Scanner (System.in);
    private NewHouseGridCTRL mCtrlUS130;

    public NewHouseGridUI() {
        mCtrlUS130 = new NewHouseGridCTRL();
    }

    /**
     * Main method of the UI which presents the user's with the option to add a
     * new House Grid to the House Grids List
     * After the information is presented we return to the Main menu screen
     **/
    public void run() {
        String id = null;
        if (mCtrlUS130.getHouseGridListSize () == 0)
            System.out.println ("List of House Grids is empty, please insert one first. \n");
        else if (mCtrlUS130.getHouseGridListSize () != 0)
            System.out.println ("There already exist the following grids:\n" + mCtrlUS130.showGridsListInString ());
        while (id == null) {
            System.out.println ("What is the grid name ID?");
            id = keyboard.next ();
        }

        if (mCtrlUS130.createNewHouseGrid (id)) {
            System.out.println ("It was created a new electric Grid successfully with the name: " + id);
            System.out.println (mCtrlUS130.showGridsListInString ());
        }
    }
}
