package smarthome.io.ui;

import smarthome.controller.NewSensorTypeCTRL;
import smarthome.model.SensorTypeList;

public class NewSensorTypeUI {


    private SensorTypeList sensorTypeList;
    private NewSensorTypeCTRL ctrl;
    private String type;


    /**
     * NewSensorTypeUI Constructor
     *
     * @param inputSensorType - list where the newSensorType is added
     */
    public NewSensorTypeUI(SensorTypeList inputSensorType) {
        this.sensorTypeList = inputSensorType;
        this.ctrl = new NewSensorTypeCTRL(sensorTypeList);
    }


    public void createNewSensorType() {
        System.out.println("Insert a new type of sensor:");

        this.type = UtilsUI.requestText("Please insert only alphanumeric characters");
        this.newType();

    }

    public void newType(){
        if (ctrl.newSensorType(this.type)) {
            System.out.println("Success! " + this.type + " was added to the list of sensor types:");
            System.out.print(ctrl.returnSensorTypeList() + "\n");
        } else {
            System.out.println("This type already exists.\n");
            if (UtilsUI.confirmOption("Do you wish to add another?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
                createNewSensorType();
            }
        }
    }
}
