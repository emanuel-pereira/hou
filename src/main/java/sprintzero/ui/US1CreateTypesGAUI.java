package sprintzero.ui;

import sprintzero.controllers.US1CreateTypeGACTRL;
import sprintzero.model.TypeGAList;

import java.util.Scanner;

public class US1CreateTypesGAUI {

    //Atributos

    TypeGAList mTypeGAList;
    US1CreateTypeGACTRL mCtrlUS1;


    //Contrutor

    /**
     *
     * @param inputList Is the list where the newTypeGA is added
     */
    public US1CreateTypesGAUI(TypeGAList inputList) {
        mTypeGAList = inputList;
        mCtrlUS1 = new US1CreateTypeGACTRL (mTypeGAList);
    }

    //Métodos
    Scanner read = new Scanner (System.in);

    /**
     * Run the US1
     * With the while statement is possible to add several TypeGAs. By writing the r letter the user is redirected to
     * the MainUI and leaves the US1UI.
     * First the method that receives and validates the user input is called. If valid, the input goes to the variable
     * name, if not, the valid name is set to null.
     * If the input is valid a newTypeGA by that name is added. If not, nothing is added.
     * The Failure message appears when the String is not valid and if the list already contains the exact same type name.
     */
    public void run() {
        while (true) {
            System.out.println ("Insert the name of the new type of geographical area (or click r to return to Main Menu):");
            String name = typeInputIsValid ();
            if (mCtrlUS1.newTypeGA (name)) //Same as if (mCtrlUS1.newTypeGA (name)==true)
                System.out.println ("Success: " + name + " added.");
            else //when name is not valid, = null, the method enters in this condition
                System.out.println ("Failure. Please try again.");
            if ("r".equals (name)) {
                System.out.println ("Return to Main Menu");
                MainUI.main (null);
                break;
            }
        }
    }

    /**
     * Validates the input name of the user
     *
     * @return null if input is not valid (eg. blank spaces). If valid returns the input name
     */
    public String typeInputIsValid() {
        String inputTypeName = read.nextLine ();
        if (inputTypeName == null || inputTypeName.trim ().isEmpty ()) {
            return null;
        }
        return inputTypeName;
    }

}
