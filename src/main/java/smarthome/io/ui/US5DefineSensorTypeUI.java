package smarthome.io.ui;

import smarthome.controller.US5DefineSensorTypeCTRL;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public class US5DefineSensorTypeUI {


    //US 05 - As System Admin, I want to specify meteorological characteristics
    // that the several sensors are able to measure/register


    private SensorTypeList mSensorTypeList;
    private US5DefineSensorTypeCTRL mCtrlUS5;

    Scanner keyboard = new Scanner(System.in);

    /**
     * US5DefineSensorTypeUI Constructor that assigns
     *
     * @param inputSensorType - list where the newSensorType is added
     */
    public US5DefineSensorTypeUI(SensorTypeList inputSensorType) {
        mSensorTypeList = inputSensorType;
        mCtrlUS5 = new US5DefineSensorTypeCTRL(mSensorTypeList);
    }

    /**
     * Method to runUS5 the US5's UI
     * With the while loop it is possible to add one or several data types
     * By writing "r", the user is redirected to the SystemAdministrationUI and leaves the US5UI
     * First, a new data type with designation is created
     * the method that receives and validates the designation is called
     * The Failure message appears when the String is not valid or if the list already contains the same type name.
     */


    public void runUS5() {
        while (true) {
            System.out.println("Insert a new meteorological characteristic OR Press 'R' to return to Main Menu");
            String inputDesignation = sensorTypeDesignationInputIsValid();

            if ("r".equals(inputDesignation)) {
                System.out.println("Return to Main Menu");
                break;
            }
            if (mCtrlUS5.newSensorType(inputDesignation)) {
                System.out.println("Success: " + inputDesignation + " was added to the list:");
                System.out.print(mCtrlUS5.returnSensorTypeList());
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
    public String sensorTypeDesignationInputIsValid() {
        String sensorTypeDesignationInput = keyboard.nextLine();

        if (sensorTypeDesignationInput == null || sensorTypeDesignationInput.trim().isEmpty()) {
            return null;
        }

        if (!sensorTypeDesignationInput.matches("[a-zA-Z]*"))
            return null;
        return sensorTypeDesignationInput.toLowerCase();
    }
}
