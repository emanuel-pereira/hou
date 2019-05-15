package smarthome.io.ui;

import smarthome.controller.CLI.GetTypeGAListCTRL;

public class GetTypeGAListUI {


    GetTypeGAListCTRL ctrlUS2;


    public GetTypeGAListUI() {
        ctrlUS2 = new GetTypeGAListCTRL();
    }


    /**
     * Ask US2CTRL for the list and show each element in several lines.
     * If size 0 print message empty.
     * If not, print the list, in which every position is converted from TypeGA to String.
     * The last statement allows the numbering of each element.
     */
    public void run() {
        if (ctrlUS2.getTypeGAListCTRL().isEmpty()) {
            System.out.println ("List of type of geographical area is empty, please insert one first");
            UtilsUI.backToMenu();
        }
        else {
            System.out.println ("Current list of types of geographical area:");
            System.out.println (ctrlUS2.showListInString ());
            UtilsUI.backToMenu();
            }
        }
}
