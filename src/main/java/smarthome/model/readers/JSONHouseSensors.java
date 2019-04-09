package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JSONHouseSensors implements FileReaderHouseSensors{
    private Path filePath;
    private JSONParser parser = new JSONParser();


    public JSONHouseSensors() {
    }

    private JSONObject readFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public List<String[]> loadData(Path filePath) throws IOException, ParseException {
        this.filePath = filePath;
        List<String[]> dataToImport = new ArrayList<>();
        JSONArray jsonSensors = (JSONArray) this.readFile().get("sensor");
        for (Object houseSensor : jsonSensors) {
            String[] tokens = new String[6];
            JSONObject jsonHouseSensor = (JSONObject) houseSensor;
            String roomID = (String) jsonHouseSensor.get("room");
            tokens[0] = roomID;
            String sensorID = (String) jsonHouseSensor.get("id");
            tokens[1] = sensorID;
            String sensorName = (String) jsonHouseSensor.get("name");
            tokens[2] = sensorName;
            String startDate = (String) jsonHouseSensor.get("start_date");
            tokens[3] = startDate;
            String sensorType = (String) jsonHouseSensor.get("type");
            tokens[4] = sensorType;
            String sensorUnits = (String) jsonHouseSensor.get("units");
            tokens[5] = sensorUnits;
            dataToImport.add(tokens);
        }
        return dataToImport;
    }

}