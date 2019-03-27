package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import smarthome.model.GAList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataImport {
    private JSONParser parser = new JSONParser();
    private Path filePath;
    private GAList gaList;

    public DataImport(GAList gaList) {
        this.gaList = gaList;
    }


    public List<String[]> importFromFileReadings(Path filePathAndName, String dataType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        this.filePath = filePathAndName;
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName(dataType, fileExtension);
        FileReaderReadings reader = (FileReaderReadings) Class.forName(className).newInstance();
        return reader.importData(filePathAndName, gaList);
    }


    public String getFileExtension(Path filePathAndName) {
        String filePathAndNameString = filePathAndName.toString();
        String fileExtension = null;
        if (filePathAndNameString.contains(".")) {
            fileExtension = filePathAndNameString.substring(filePathAndNameString.lastIndexOf(".") + 1);
        }
        return fileExtension;
    }

    private JSONObject readFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public String getClassName(String dataType, String fileExtension) throws ParseException, IOException {
        String className = null;
        this.filePath = Paths.get("resources/ImportFileConfig.json");
        JSONObject jsonObject = (JSONObject) this.readFile().get("object_type");
        JSONArray jsonTypes = (JSONArray) jsonObject.get(dataType);
        for (Object types : jsonTypes) {
            JSONObject jsonReading = (JSONObject) types;
            className = (String) jsonReading.get(fileExtension);
        }
        return className;
    }
}
