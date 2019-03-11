package smarthome.io.ui;

import smarthome.controller.JSONImportCTRL;
import smarthome.model.GAList;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class JSONImportUI {
    private JSONImportCTRL ctrl;

    public JSONImportUI() {
    }

    public JSONImportUI(GAList gaList) {
        this.ctrl = new JSONImportCTRL(gaList);
    }


    public void loadJSON() throws ParseException, org.json.simple.parser.ParseException, IOException {
        boolean loop = true;
        while (loop) {
            System.out.println("Please enter the file path to import geographical areas and sensors from a JSON file:");
            String filepath = UtilsUI.requestText("Please enter a valid directory.", "[A-Za-z0-9/.]*");
            try {
                ctrl.loadJSON(filepath);
                loop = false;
            } catch (FileNotFoundException e) {
                System.out.println("File not found in the specified file path: " + filepath);
                System.out.println("Please enter the correct file path.");
            }
        }

    }
}
