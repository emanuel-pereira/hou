package smarthome.controller;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.ReadJSONFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JSONImportCTRL {

    private GAList gaList;

    public JSONImportCTRL(GAList gaList) {
        this.gaList = gaList;
    }


    public void loadJSON(String filePath) throws ParseException, org.json.simple.parser.ParseException, IOException {
        ReadJSONFile jsonFile = new ReadJSONFile(filePath, gaList);
        jsonFile.readGAs();
    }


    public List<GeographicalAreaDTO> getGAListDTO() {
        List<GeographicalAreaDTO> gaListDTO=new ArrayList<>();
        for (GeographicalArea ga : gaList.getGAList()) {
            GeographicalAreaDTO gaDTO=ga.toDTO();
            gaListDTO.add(gaDTO);
        }
        return gaListDTO;
    }
}
