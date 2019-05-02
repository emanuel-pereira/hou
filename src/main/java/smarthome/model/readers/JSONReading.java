package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JSONReading implements FileReaderReadings {

    private final JSONParser parser = new JSONParser();
    private Path filePath;

    /**
     * FileReaderReadings to read JSON file and pass it to parser.
     *
     * @throws IOException
     * @throws ParseException
     */
    private JSONObject readFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public List<String[]> importData(Path filePath) throws ParseException, IOException {
        this.filePath = filePath;
        List<String[]> dataToImport = new ArrayList<>();
        JSONArray jsonReadings = (JSONArray) this.readFile().get("readings");
        for (Object reading : jsonReadings) {
            String[] tokens = new String[4];
            JSONObject jsonReading = (JSONObject) reading;
            Object id = jsonReading.get("id") == null
                    ? jsonReading.get("SensorID")
                    : jsonReading.get("id");
            tokens[0] = (String) id;
            String date = (String) jsonReading.get("timestamp/date");
            tokens[1] = date;
            Object value = jsonReading.get("value") == null
                    ? jsonReading.get("Aux_Value")
                    : jsonReading.get("value");
            tokens[2] = (String) value;
            String unit = (String) jsonReading.get("unit");
            tokens[3] = unit;
            dataToImport.add(tokens);
        }
        return dataToImport;
    }
}
