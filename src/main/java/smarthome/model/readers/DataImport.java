package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import smarthome.io.ui.UtilsUI;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Reading;
import smarthome.model.Sensor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

import static java.lang.Double.parseDouble;

public class DataImport {
    private JSONParser parser = new JSONParser();
    private Path configFilePath;
    private GAList gaList;

    public DataImport(GAList gaList) {
        this.gaList = gaList;
    }


    public void importFromFileReadings(Path filePathAndName, String dataType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        this.configFilePath = filePathAndName;
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName(dataType, fileExtension);
        FileReaderReadings reader = (FileReaderReadings) Class.forName(className).newInstance();
        List<String[]> dataToImport = reader.importData(filePathAndName);
        loadReadingFiles(dataToImport);
    }

    public List<GeographicalArea> importFromFileGeoArea(Path filePathAndName, String dataType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName(dataType, fileExtension);
        FileReaderGeoArea reader = (FileReaderGeoArea) Class.forName(className).newInstance();
        List<GeographicalArea> dataToImport = reader.importData(filePathAndName);
        return dataToImport;
    }

    public void loadReadingFiles(List<String[]> dataToImport) {
        for (GeographicalArea ga : gaList.getGAList()) {
            for (String[] field : dataToImport) {
                String sensorID = field[0];
                for (Sensor sensor : ga.getSensorListInGA().getSensorList())
                    if (sensorID.equals(sensor.getId())) {

                        String dateAndTimeString = field[1];
                        Calendar readingDate = UtilsUI.parseDateToImportReadings(dateAndTimeString);
                        double readingValue = parseDouble(field[2]);
                        String unit = field[3];

                        Reading reading = new Reading(readingValue, readingDate, unit);

                        if (readingDate.after(sensor.getStartDate()))
                            sensor.getReadingList().addReading(reading);
                    }
            }

        }
    }

    public void loadGeoAreaFiles(List<GeographicalArea> dataToImport){
        for(GeographicalArea ga: dataToImport){
            this.gaList.addGA(ga);
        }
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
        java.io.FileReader fileReader = new java.io.FileReader(configFilePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public String getClassName(String dataType, String fileExtension) throws ParseException, IOException {
        String className = null;
        this.configFilePath = Paths.get("resources/ImportFileConfig.json");
        JSONObject jsonObject = (JSONObject) this.readFile().get("object_type");
        JSONArray jsonTypes = (JSONArray) jsonObject.get(dataType);
        for (Object types : jsonTypes) {
            JSONObject jsonReading = (JSONObject) types;
            className = (String) jsonReading.get(fileExtension);
        }
        return className;
    }
}
