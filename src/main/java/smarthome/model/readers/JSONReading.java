package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import smarthome.model.GAList;


import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JSONReading implements FileReaderReadings {

    private JSONParser parser = new JSONParser();
    private Path filePath;
    private GAList gaList;

    public JSONReading(GAList gaList) {
        this.gaList = gaList;
    }

    public JSONReading() {
    }

    /**
     * FileReaderReadings to read JSON file and pass it to parser.
     *
     * @return JSONObject
     * @throws IOException
     * @throws ParseException
     */
    private JSONObject readFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public List<String> importData(Path filePathAndName, GAList gaList) throws ParseException, IOException {
        this.filePath = filePathAndName;
        this.gaList = gaList;
        List<String[]> data = extractData();
    }

    public List<String[]> extractData() throws ParseException, IOException {
        List<String[]> data = new ArrayList<>();
        JSONArray jsonReadings = (JSONArray) this.readFile().get("readings");
        for (Object reading : jsonReadings) {
            String[] tokens = new String[4];
            JSONObject jsonReading = (JSONObject) reading;
            String id = (String) jsonReading.get("id");
            tokens[0] = id;
            String date = (String) jsonReading.get("timestamp/date");
            tokens[1] = date;
            String value = (String) jsonReading.get("value");
            tokens[2] = value;
            String unit = (String) jsonReading.get("unit");
            tokens[3] = unit;
            data.add(tokens);
        }
        return data;
    }
}
