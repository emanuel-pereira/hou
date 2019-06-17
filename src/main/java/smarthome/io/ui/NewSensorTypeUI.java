package smarthome.io.ui;

import org.apache.log4j.Logger;
import smarthome.controller.cli.NewSensorTypeCTRL;
import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorTypeList;
import smarthome.services.SensorTypeService;

import java.text.ParseException;

public class NewSensorTypeUI {


    private final NewSensorTypeCTRL ctrl;
    private String type;
    static final Logger log = Logger.getLogger(SensorTypeService.class);


    /**
     * NewSensorTypeUI Constructor
     * @param sensorTypeList
     */
    public NewSensorTypeUI(SensorTypeList sensorTypeList) {
        this.ctrl = new NewSensorTypeCTRL(sensorTypeList);
    }

    public void inputSensorType() {
        System.out.println("Insert a new type of sensor:");
        boolean condition = true;
        while (condition) {
            try {
                this.type = UtilsUI.requestText("Please insert only alphanumeric characters");
                condition = false;
                this.createType();
            } catch (Exception e) {
                UtilsUI.showError("Illegal Argument", "Type must contain a valid alphabetic regular expression.");
                log.warn(e.getMessage());
            }
        }
    }

    private void createType() throws ParseException {
        if (!ctrl.existsByType(this.type)) {
            ctrl.createSensorType(this.type);
            System.out.println("Success! " + this.type + " was added to the list of sensor types:");
            for (SensorTypeDTO sensorTypeDTO : ctrl.listOfSensorTypesDTOs()) {
                System.out.println(sensorTypeDTO.getType());
            }
        } else {
            System.out.println("This type already exists.\n");
            if (UtilsUI.confirmOption("Do you wish to add another?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
                inputSensorType();
            }
        }
    }
}