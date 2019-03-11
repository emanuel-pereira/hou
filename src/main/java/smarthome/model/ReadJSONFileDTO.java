package smarthome.model;

import java.io.IOException;
import java.text.ParseException;

public class ReadJSONFileDTO {


    public ReadJSONFileDTO() {
    }

    public ReadJSONFileDTO loadJSON(String filePath) throws ParseException, org.json.simple.parser.ParseException, IOException {
        ReadJSONFile jsonFile= new ReadJSONFile(filePath);
        jsonFile.readGAs();
        return new ReadJSONFileDTO();
    }

}
