package smarthome.io.ui;

import smarthome.controller.NewTypeGACTRL;
import smarthome.model.TypeGAList;

public class NewTypeGAUI {

    private NewTypeGACTRL controller;
    private String name;
    private boolean condition;

    /**
     * User interface constructor
     *
     */
    public NewTypeGAUI() {
        this.controller = new NewTypeGACTRL();
    }

    /**
     * First the method that receives and validates the user input is called (a message error appears if the input content is not the correct one).
     * If the input is valid a type by that name is added and a success message is shown.
     * If the list already contains the same type name another error message appears.
     */
    public void run() {
        condition = true;
        while (condition) {
            System.out.println("Insert the name of the new type of geographical area:");
            this.name = UtilsUI.requestText("Please use only characters");
            this.createTypeGA();
        }
    }

    private void createTypeGA() {
        if (this.controller.createTypeGA(this.name)) {
            System.out.println("Success: " + this.name + " added.\n");
            condition = false;
        } else
            System.out.println("This type already exists.");
    }

}
