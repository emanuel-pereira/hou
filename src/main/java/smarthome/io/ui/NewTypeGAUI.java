package smarthome.io.ui;

import smarthome.controller.NewTypeGACTRL;
import smarthome.model.TypeGAList;

import java.util.Scanner;

public class NewTypeGAUI {

    Scanner read = new Scanner (System.in);

    private NewTypeGACTRL ctrlUS1;

    /**
     * NewTypeGAUI Constructor
     *
     * @param inputList Is the list where the run is added
     */
    public NewTypeGAUI(TypeGAList inputList) {
        this.ctrlUS1 = new NewTypeGACTRL (inputList);
    }

    /**
     * By writing the r letter the user is redirected to the SystemAdministrationUI.
     * First the method that receives and validates the user input is called (a message error appears if the input content is not the correct one).
     * If the input is valid a type by that name is added and a success message is shown.
     * If the list already contains the same type name another error message appears.
     */
    public void run() {
        boolean condition = true;
        while (condition) {
            System.out.println ("Insert the name of the new type of geographical area (or click r to return to Main Menu):");
            String name = UtilsUI.requestText ("Please use only characters");
            if ("r".equals (name)) {
                System.out.println ("Return to Main Menu\n");
                break;
            }
            if (this.ctrlUS1.createTypeGA (name)) {
                System.out.println ("Success: " + name + " added.\n");
                condition = false;
            } else
                System.out.println ("This type already exists.");
        }
    }

}
