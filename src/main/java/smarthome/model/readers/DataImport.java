package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import smarthome.io.ui.UtilsUI;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Reading;
import smarthome.model.Sensor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Double.parseDouble;

public class DataImport {
    private JSONParser parser = new JSONParser();
    private Path configFilePath;
    private GAList gaList;

    public DataImport(GAList gaList) {
        this.gaList = gaList;
    }


    public void importReadingsFromFile(Path filePathAndName, String dataType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, ParserConfigurationException, SAXException {
        this.configFilePath = filePathAndName;
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName(dataType, fileExtension);
        FileReaderReadings reader = (FileReaderReadings) Class.forName(className).newInstance();
        List<String[]> dataToImport = reader.importData(filePathAndName);
        loadReadingFiles(dataToImport);
    }

    public void loadReadingFiles(List<String[]> dataToImport) throws IOException {
        Logger logger = createLogFile("invalidReadingsLog.txt");
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

                        else {
                            String message = "READING NOT ADDED - VALUE: " + readingValue + " DATE: " + dateAndTimeString + "\nREASON: READING DATE AFTER SENSOR START DATE\n";
                            logger.log(Level.WARNING, message);
                        }
                    }
            }
        }
    }
    public Logger createLogFile(String fileName) throws IOException{
        Logger logger = Logger.getLogger(GeographicalArea.class.getName());
        FileHandler fileHandler = new FileHandler(fileName);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
        return logger;
    }

    public String getFileExtension(Path filePathAndName) {

        String filePathAndNameString = filePathAndName.toString();
        String fileExtension = null;
        CharSequence c = ".";
        if (filePathAndNameString.contains(c)) {
            fileExtension = filePathAndNameString.substring(filePathAndNameString.lastIndexOf(c.toString()) + 1);
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

    public List<GeographicalArea> importFromFileGeoArea(Path filePathAndName, String dataType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName(dataType, fileExtension);
        FileReaderGeoArea reader = (FileReaderGeoArea) Class.forName(className).newInstance();
        List<GeographicalArea> dataToImport = reader.importData(filePathAndName);
        return dataToImport;
    }

    public void loadGeoAreaFiles(List<GeographicalArea> dataToImport){
        for(GeographicalArea ga: dataToImport){
            this.gaList.addGA(ga);
        }
    }
}
