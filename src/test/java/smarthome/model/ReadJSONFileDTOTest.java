package smarthome.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

class ReadJSONFileDTOTest {

    @Test
    void loadJSON() throws ParseException, org.json.simple.parser.ParseException, IOException {
        ReadJSONFileDTO jsonDTO= new ReadJSONFileDTO();
        String filePath="resources/JsonFile.json";
        try {
            jsonDTO.loadJSON(filePath);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: "+filePath);
    }
}}