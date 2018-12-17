package sprintzero.ui;

import sprintzero.controllers.US5DefineSensorDataTypeCTRL;
import sprintzero.model.DataTypeList;

import java.util.Scanner;

public class US5DefineSensorDataTypeUI {


    //US 05 - As System Admin, I want to specify meteorological characteristics
    // that the several sensors are able to measure/register


    private DataTypeList mDataTypeList;
    private US5DefineSensorDataTypeCTRL mCtrlUS5;

    Scanner keyboard = new Scanner(System.in);

    /**
     * US5DefineSensorDataTypeUI Constructor that assigns
     *
     * @param inputDataType - list where the newTypeGA is added
     */
    public US5DefineSensorDataTypeUI(DataTypeList inputDataType) {
        mDataTypeList = inputDataType;
        mCtrlUS5 = new US5DefineSensorDataTypeCTRL(mDataTypeList);
    }

    /**
     * Method to runUS5 the US5's UI
     * With the while loop it is possible to add one or several data types
     * By writing "r", the user is redirected to the MainUI and leaves the US5UI
     * First, a new data type with designation is created
     * the method that receives and validates the designation is called
     * The Failure message appears when the String is not valid or if the list already contains the same type name.
     */


    public void runUS5() {
        while (true) {
            System.out.println("Insert a new meteorological characteristic OR Press 'R' to return to Main Menu");
            String inputDesignation = dataTypeDesignationInputIsValid();

            if ("r".equals(inputDesignation)) {
                System.out.println("Return to Main Menu");
                break;
            }
            if (mCtrlUS5.newDataType(inputDesignation)) {
                System.out.println("Success: " + inputDesignation + " was added to the list:");
                System.out.print(mCtrlUS5.returnDataTypeList());
            } else
                System.out.println("Failure. Please try again.");
        }

    }

    /**
     * Method to validate the designation written by the user
     * If the designation is valid, the input is stored in the variable
     * name, if not, the valid name is set to null.
     * If the input is valid a newTypeGA by that name is added. If not, nothing is added.
     *
     * @return null if input is not valid; return the designation if valid
     */
    public String dataTypeDesignationInputIsValid() {
        String dataTypeDesignationInput = keyboard.nextLine();

        if (dataTypeDesignationInput == null || dataTypeDesignationInput.trim().isEmpty()) {
            return null;
        }

        if (!dataTypeDesignationInput.matches("[a-zA-Z]*"))
            return null;
        return dataTypeDesignationInput.toLowerCase();
    }

}
