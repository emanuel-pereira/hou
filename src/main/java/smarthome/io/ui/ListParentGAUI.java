package smarthome.io.ui;

import smarthome.controller.cli.ListParentGACTRL;
import smarthome.model.GAList;

import java.util.Scanner;

public class ListParentGAUI {

    private final Scanner keyboard = new Scanner(System.in);
    private final ListParentGACTRL mCtrlUS8;

    /**
     * This is the constructor method for the UI, it will receive the GA's list in order to
     * present to the user and do the proceeding methods
     *
     * @param gaList attribute List were all previously created GA's are listed
     */
    public ListParentGAUI(GAList gaList) {
        mCtrlUS8 = new ListParentGACTRL(gaList);
    }

    /**
     * Main method of the UI which presents the user's with the list of existing GA's
     * The user is asked to chose one from the list in order to verify its parent
     * If no parent is found a notice will be received
     * If there is a direct parent (attribute in every GA, defined in US7) this will be printed in the screen
     * After the information is presented we return to the Main menu screen
     */
    public void run() {
        //call the method that checks and prints the GA's List
        if (returnGAStringList()) {
            int inputIndex = keyboard.nextInt();
            if (inputIndex == 0) {//input 0 returns to main US's menu
                System.out.println("Return to Main Menu");
            } else {
                if (mCtrlUS8.isParentOf(inputIndex).isEmpty()){
                    System.out.println("No parent Geographical Area was defined yet for this Geographical Area, please select US7 to set up Parent GA's in the next menu \n");
                    UtilsUI.backToMenu();
                }
                else {
                    System.out.println("The Geographical Area was included to: " + mCtrlUS8.isParentOf(inputIndex));
                    UtilsUI.backToMenu();
                }
            }
        }
    }

    /**
     * Ask US8CTRL for the list and show each element in several lines.
     * Notice if size 0. Present list as empty.
     * If not, print the list, in which every position is converted from TypeGA to String.
     * The last statement allows the numbering of each element.
     */
    public boolean returnGAStringList() {
        if (mCtrlUS8.getGAListSize() == 0) {
            System.out.println("List of geographical areas is empty, please insert one first in US3 \n \n");
            UtilsUI.backToMenu();
            return false;
        } else {
            System.out.println("Current list of geographical areas: ");
            System.out.println(mCtrlUS8.showListInString());
            //ask the user for an number input to check the Parent GA of the GA he chooses
            System.out.println("Insert a GA index from the previous list to check it's parent GA (or press '0' to return to Main Menu)");
            System.out.println("\n");
            return true;
        }
    }

}
