package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.ReadJSONFile;

import java.io.IOException;
import java.text.ParseException;

public class JSONImportCTRL {

    private GAList gaList;

    public JSONImportCTRL(GAList gaList) {
        this.gaList = gaList;
    }

    public void loadJSON(String filePath) throws ParseException, org.json.simple.parser.ParseException, IOException {
        ReadJSONFile jsonFile= new ReadJSONFile(filePath, gaList);
        jsonFile.readGAs();
    }
}
