package smarthome.io.ui;

import smarthome.controller.US1CreateTypeGACTRL;
import smarthome.model.TypeGAList;

import java.util.Scanner;

public class US1CreateTypesGAUI {

    Scanner read = new Scanner ( System.in );

    private TypeGAList mTypeGAList;
    private US1CreateTypeGACTRL mCtrlUS1;

    /**
     * US1CreateTypesGAUI Constructor
     * @param inputList Is the list where the newTypeGA is added
     */
    public US1CreateTypesGAUI(TypeGAList inputList) {
        mTypeGAList = inputList;
        mCtrlUS1 = new US1CreateTypeGACTRL (mTypeGAList);
    }

    /**
     * Run the US1
     * With the while statement is possible to add several TypeGAs. By writing the r letter the user is redirected to
     * the SystemAdministrationUI and leaves the US1UI.
     * First the method that receives and validates the user input is called. If valid, the input goes to the variable
     * name, if not, the valid name is set to null.
     * If the input is valid a newTypeGA by that name is added. If not, nothing is added.
     * The Failure message appears when the String is not valid and if the list already contains the exact same type name.
     */
    public void createTypesGA() {
        while (true) {
            System.out.println ("Insert the name of the new type of geographical area (or click r to return to Main Menu):");
            String name = typeInputIsValid();
            if ("r".equals (name)) {
                System.out.println ("Return to Main Menu");
                break;
            }
            if (mCtrlUS1.newTypeGA (name)) //Same as if (mCtrlUS1.newTypeGA (name)==true)
                System.out.println ("Success: " + name + " added."); //System.out.println (mTypeGAList.getTypeGAList ());
            else //when name is not valid, = null, the method enters in this condition
                System.out.println ("Error. ");
        }
    }

    /**
     * Validates the input name of the user
     * @return null if input is not valid (eg. blank spaces, special characters like ç or punctuation).
     * If valid returns the input name, in lower case format to avoid multiple input of the same String
     */
    public String typeInputIsValid() {
        String inputTypeName = read.nextLine();
        if (inputTypeName == null || inputTypeName.trim ().isEmpty ()) {//verification of empty and null parameters
            return null;
        }
        //match returns true if the input string "inputTypeName" matches the given regular expression
        if (!inputTypeName.matches("^[A-Za-z ]+$"))
            return null;
        return inputTypeName.toLowerCase();//set input name to lower case
    }

}
