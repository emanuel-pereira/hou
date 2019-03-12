package smarthome.io.ui;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.controller.JSONImportCTRL;
import smarthome.model.GAList;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class JSONImportUI {
    private JSONImportCTRL ctrl;


    public JSONImportUI(GAList gaList) {
        this.ctrl = new JSONImportCTRL(gaList);
    }


    public void loadJSON() throws ParseException, org.json.simple.parser.ParseException, IOException {
        boolean loop = true;
        while (loop) {
            System.out.println("Please enter the json file path to import geographical areas and sensors (eg: resources/JsonFile.json):");
            String filepath = UtilsUI.requestText("Invalid filepath.", "[A-Za-z0-9/.]*");
            try {
                ctrl.loadJSON(filepath);
                loop = false;
            } catch (FileNotFoundException e) {
                System.out.println("Json file not found in the specified file path: " + filepath);
            }
            this.showGAs();
        }
    }

    public void showGAs(){
        System.out.println("The following geographical areas and respective sensors were imported from the selected JsonFile:");
        for(GeographicalAreaDTO geographicalAreaDTO:ctrl.getGAListDTO()){
            System.out.println("GEOGRAPHICAL AREA");
            System.out.println(" Id: "+geographicalAreaDTO.getIdentification());
            System.out.println (" Name: "+geographicalAreaDTO.getDesignation());
            System.out.println(" SensorList: ");
            for(SensorDTO sensorDTO:geographicalAreaDTO.getSensorListDTO()){
                int counter =1;
                System.out.print("  "+ counter++ +" - Sensor Id: "+sensorDTO.getId());
                System.out.println(" | Name "+sensorDTO.getDesignation());
            }
        }
    }
}
