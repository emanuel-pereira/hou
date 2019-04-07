package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import smarthome.io.ui.UtilsUI;
import smarthome.model.*;
import smarthome.repository.Repositories;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Double.parseDouble;

public class DataImport {
    private JSONParser parser = new JSONParser();
    private Path configFilePath;
    private GAList gaList;
    private RoomList roomList;
    static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DataImport.class);

    /**
     * Constructor for importing data related to GAList
     *
     * @param gaList to be updated with data imported through SystemAdministration menu
     */
    public DataImport(GAList gaList) {
        this.gaList = gaList;
    }

    /**
     * Constructor for importing data related to RoomList
     *
     * @param roomList to be updated with data imported through HouseAdministration menu
     */
    public DataImport(RoomList roomList) {
        this.roomList = roomList;
    }

    /**
     * This method gets the file extension of the filePath inputted as parameter and then gets the className according to the file extension
     * so it can create an instance of the respective FileReaderReadings class through reflection.
     * Afterwards, this method invokes the importData method of the instantiated FileReaderReadings class so it can parse data
     * to a list of strings.
     * Finally, the loadReadingFiles method is invoked, which will update the reading List for each sensor contained in the object passed as parameter
     * which can only be a RoomList or a GAList.
     *
     * @param filePath Path object containing the relative or absolute path and file name of the file to be read.
     * @param object   GAList or RoomList which will have its sensors' readings updated
     */
    public void importReadingsFromFile(Path filePath, Object object) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, ParserConfigurationException, SAXException {
        this.configFilePath = filePath;
        String fileExtension = getFileExtension(filePath);
        String className = getClassName("readings", fileExtension);
        FileReaderReadings reader = (FileReaderReadings) Class.forName(className).newInstance();
        List<String[]> dataToImport = reader.importData(filePath);
        loadReadingFiles(dataToImport, object);

    }


    /**
     * This method updates the reading List for each sensor contained in the object passed as parameter
     * which can only be a RoomList or a GAList.
     *
     * @param dataToImport array of strings containing sensor's id and its readings' attributes value
     * @param object       GAList or RoomList which will have its sensors' readings updated
     */
    public void loadReadingFiles(List<String[]> dataToImport, Object object) {
        if (object.equals(gaList)) {
            for (GeographicalArea ga : gaList.getGAList()) {
                importGAsSensorsReadings(dataToImport, ga);
            }
        }
        if (object.equals(roomList)) {
            for (Room r : roomList.getRoomList()) {
                importRoomsSensorsReadings(dataToImport, r);
            }
        }
    }

    /**
     * This method updates the sensors' readings of the GeographicalArea object passed as parameter
     *
     * @param dataToImport     array of strings containing sensor's id and its readings' attributes value
     * @param geographicalArea object to be updated
     */
    private void importGAsSensorsReadings(List<String[]> dataToImport, GeographicalArea geographicalArea) {
        for (String[] field : dataToImport) {
            String sensorID = field[0];
            SensorList sensorList = geographicalArea.getSensorListInGA();
            importReadings(field, sensorID, sensorList);
        }
    }

    /**
     * This method updates the sensors' readings of the Room object passed as parameter
     *
     * @param dataToImport array of strings containing sensor's id and its readings' attributes value
     * @param room         object to be updated
     */
    private void importRoomsSensorsReadings(List<String[]> dataToImport, Room room) {
        for (String[] field : dataToImport) {
            String sensorID = field[0];
            SensorList sensorList = room.getSensorListInRoom();
            importReadings(field, sensorID, sensorList);
        }
    }

    /**
     * This method imports readings for the sensor with sensorID passed as parameter.
     * Only readings with dateAndTime after the sensor's startDate will be imported otherwise tghey will be registered in a log file.
     *
     * @param field
     * @param sensorID
     * @param sensorList
     */
    private void importReadings(String[] field, String sensorID, SensorList sensorList) {
        for (Sensor sensor : sensorList.getSensorList())
            if (sensorID.equals(sensor.getId())) {
                String dateAndTimeString = field[1];
                Calendar readingDate = UtilsUI.parseDateToImportReadings(dateAndTimeString);
                double readingValue = parseDouble(field[2]);
                String unit = field[3];

                Reading reading = new Reading(readingValue, readingDate, unit);

                if (readingDate.after(sensor.getStartDate())) {
                    reading.setSensor(sensor);
                    //dataImport
                    sensor.getReadingList().addReading(reading);
                } else {
                    String message = "Reading not added to the DB - sensor: " + sensorID +
                            " value: " + readingValue + " " + unit + " date: " + dateAndTimeString + "\nreason: reading date after sensor start date";
                    log.error(message);
                }
            }
    }

    public Logger createLogFile(String fileName) throws IOException {
        Logger logger = Logger.getLogger(GeographicalArea.class.getName());
        FileHandler fileHandler = new FileHandler(fileName);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);

        return logger;
    }

    /**
     * Method that converts a valid file path to string and returns a substring after the
     * dot of the path which represents the file extension
     *
     * @param filePath inputted for with the relative or absolute path of the file
     * @return the file extension. If, for some reason, the path parameter does not contain a dot, then this method
     * throws a null pointer exception. Nevertheless, in UIs filepaths requests always have a regex containing a dot.
     */
    public String getFileExtension(Path filePath) {
        String filePathStr = filePath.toString();
        CharSequence dot = ".";
        if (filePathStr.contains(".")) {
            return filePathStr.substring(filePathStr.lastIndexOf(dot.toString()) + 1);
        }
        throw new NullPointerException();
    }

    private JSONObject readConfigFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(configFilePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public String getClassName(String dataType, String fileExtension) throws ParseException, IOException {
        String className = null;
        this.configFilePath = Paths.get("resources/ImportFileConfig.json");
        JSONObject jsonObject = (JSONObject) this.readConfigFile().get("object_type");
        JSONArray jsonTypes = (JSONArray) jsonObject.get(dataType);
        for (Object types : jsonTypes) {
            JSONObject jsonReading = (JSONObject) types;
            className = (String) jsonReading.get(fileExtension);
        }
        return className;
    }

    public List<GeographicalArea> loadGeoAreaFiles(Path filePathAndName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName("geographical_area", fileExtension);
        FileReaderGeoArea reader = (FileReaderGeoArea) Class.forName(className).newInstance();
        return reader.loadData(filePathAndName);
    }

    public void importFromFileGeoArea(List<GeographicalArea> dataToImport) {
        for (GeographicalArea ga : dataToImport) {
            if (this.gaList.addGA(ga)) {
                try {
                    //repository call
                    Repositories.saveGA(ga);
                    log.info("New Geographical Area '" + ga.getGAName() + "' imported into the systems DB");
                } catch (NullPointerException e) {
                    log.warn("Repository unreachable");
                }
            } else log.info("No Geographical Areas were imported into the systems DB");
        }
    }

    //House Sensors

    public List<Sensor> loadHouseSensorsFiles(Path filePathAndName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName("house_sensors", fileExtension);
        FileReaderHouseSensors reader = (FileReaderHouseSensors) Class.forName(className).newInstance();
        return reader.loadData(filePathAndName);
    }

}
