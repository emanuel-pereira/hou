package smarthome.model.readers;

import org.apache.log4j.Logger;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.lang.Double.parseDouble;

public class DataImport {
    private final JSONParser parser = new JSONParser();
    private Path configFilePath;
    private final Path configHouseFilePath = Paths.get("resources/DataSet_sprint06_House.json");
    private GAList gaList;
    private RoomList roomList;
    private int nrOfAddedReadings = 0;
    private int nrOfInvalidReadings = 0;
    private List<Sensors> sensors = new ArrayList<>();
    private List<GeographicalArea> notAdded;
    private SensorTypeList sensorTypeList;
    private int sensorsNotAdded;
    private int sensorsAdded;
    static final Logger log = Logger.getLogger(DataImport.class);


    /**
     * Constructor for importing data related to GAList
     *
     * @param gaList to be updated with data imported through SystemAdministration menu
     */
    public DataImport(GAList gaList) {
        this.gaList = gaList;
        this.notAdded = new ArrayList<>();
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
     * Constructor for importing data related to RoomList
     *
     * @param roomList to be updated with data imported through HouseAdministration menu
     */
    public DataImport(RoomList roomList, SensorTypeList sensorTypeList) {
        this.roomList = roomList;
        this.sensorTypeList = sensorTypeList;
    }

    public int getNrOfAddedReadings() {
        return this.nrOfAddedReadings;
    }

    public int getNrOfInvalidReadings() {
        return this.nrOfInvalidReadings;
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
        try {
            List<String[]> dataToImport = reader.importData(filePath);
            loadReadingFiles(dataToImport, object);

        } catch (NullPointerException e) {
            log.error("Invalid file structure");
        }
    }

    /**
     * This method updates the reading List for each sensor contained in the object passed as parameter
     * which can only be a RoomList or a GAList.
     *
     * @param readingsToImport array of strings containing sensor's id and its readings' attributes value
     * @param object           GAList or RoomList which will have its sensors' readings updated
     */
    public void loadReadingFiles(List<String[]> readingsToImport, Object object) {
        if (object.equals(roomList)) {
            sensors = roomList.getAllSensors();
            importSensorsReadings(readingsToImport);
        }
        if (object.equals(gaList)) {
            sensors = gaList.getAllSensors();
            importSensorsReadings(readingsToImport);
        }
    }

    //fazer for each sensor in all sensors se sensorid existe então não adiciona
    private void importSensorsReadings(List<String[]> readingsToImport) {
        for (String[] reading : readingsToImport) {
            String sensorID = reading[0];
            if (importReading(reading, sensorID)) {
                this.nrOfAddedReadings++;
            } else {
                this.nrOfInvalidReadings++;
            }
        }
    }

    /**
     * This method imports a reading for the sensor with sensorID passed as parameter.
     * Only readings with dateAndTime after the sensor's startDate will be imported otherwise they will be registered in a log file.
     *
     * @param field Position in the array
     * @param sensorID Id of the sensor
     */
    private boolean importReading(String[] field, String sensorID) {
        for (Sensors sensor : sensors) {
            if (sensorID.equals(sensor.getId())) {

                String dateAndTimeString = field[1];
                Calendar readingDate = null;
                try{
                readingDate = UtilsUI.parseDateToImportReadings(dateAndTimeString);}
                catch (DateTimeParseException e){
                    String message = "Reading not added to the DB - sensor: " + sensorID +
                            " value: " + field[2] + " " + field[3] + " date: " + dateAndTimeString + "\nreason: " + e.getMessage();
                    log.error(message);
                    return false;
                }
                double readingValue = parseDouble(field[2]);
                String unit = field[3];
                Reading reading = new Reading(readingValue, readingDate, unit);
                if (readingDate.after(sensor.getStartDate())||reading.isSameDay(sensor.getStartDate())) {
                    reading.setSensor(sensor);
                    //dataImport
                    if (sensor.getReadingList().addReading(reading)) {

                        return true;
                    }
                } else {
                    String message = "Reading not added to the DB - sensor: " + sensorID +
                            " value: " + readingValue + " " + unit + " date: " + dateAndTimeString + "\nreason: reading date after sensor start date";
                    log.error(message);
                    return false;
                }
            }
        }
        return false;
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

    public List<GeographicalArea> loadGeoAreaFiles(Path filePathAndName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException, ParserConfigurationException {
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName("geographical_area", fileExtension);
        FileReaderGeoArea reader = (FileReaderGeoArea) Class.forName(className).newInstance();
        return reader.loadData(filePathAndName);
    }

    public void importFromFileGeoArea(List<GeographicalArea> dataToImport) {
        this.notAdded.clear();
        for (GeographicalArea ga : dataToImport) {
            if (this.gaList.addGA(ga)) {
                try {
                    //repository call
                    Repositories.saveGA(ga);
                    log.info("New Geographical Area '" + ga.getGAName() + "' imported into the systems DB");
                } catch (Exception e) {
                    log.warn(e.getMessage());
                }
            } else {
                this.notAdded.add(ga);
                log.warn("No Geographical Areas were imported into the systems DB");
            }
        }
    }

    //House Sensors
    public List<String[]> loadHouseSensorsFiles(Path filePathAndName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        String fileExtension = getFileExtension(filePathAndName);
        String className = getClassName("house_sensors", fileExtension);
        FileReaderHouseSensors reader = (FileReaderHouseSensors) Class.forName(className).newInstance();
        return reader.loadData(filePathAndName);
    }

    public void importHouseSensors(List<String[]> dataToImport) throws java.text.ParseException {
        sensorsAdded = 0;
        sensorsNotAdded = 0;
        for (String[] string : dataToImport) {
            String roomID = string[0];
            Room room = roomList.getRoomIfIDMatchesAnyExistingRoom(roomID);

            String sensorID = string[1];
            String sensorDesignation = string[2];
            String startDate = string[3];

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(startDate);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            String type = string[4];
            SensorType sensorType = this.sensorTypeList.getSensorTypeMatchedWithString(type);

            String unit = string[5];

            Sensors newSensor = new Sensors(sensorID, sensorDesignation, calendar, sensorType, unit, new ReadingList());

            //Needs to be improved

            if (room == null) {
                String message = "Sensors not added to the DB - sensor: " + sensorID +
                        " designation: " + sensorDesignation + "\nreason: The sensor was not imported because the room do not exists";
                log.error(message);
                sensorsNotAdded++;
            } else if (sensorType == null) {
                String message = "Sensors not added to the DB - sensor: " + sensorID +
                        " designation: " + sensorDesignation + "start date: " + calendar +
                        " sensorType: " + sensorType + "unit: " + unit + "\nreason: The sensor type do not exists";
                log.error(message);
                sensorsNotAdded++;
            } else if (!room.getSensorListInRoom().addSensor(newSensor)) {
                String message = "Sensors not added to the DB - sensor: " + sensorID +
                        " designation: " + sensorDesignation + "start date: " + calendar +
                        " sensorType: " + sensorType + "unit: " + unit + "\nreason: The sensor already exists";
                log.error(message);
                sensorsNotAdded++;
            } else {
                room.getSensorListInRoom().addSensor(newSensor);
                sensorsAdded++;
            }
        }
    }

    public int getSizeOfSensorsAdded() {
        return sensorsAdded;
    }

    public int notAddedNumber() {
        return this.notAdded.size();
    }

    /**
     * US100 CONFIGURE HOUSE BY FILE IMPORT
     */
    private FileReaderHouse getHouseConfigFileReader() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        String fileExtension = getFileExtension(this.configHouseFilePath);
        String className = getClassName("house", fileExtension);
        return (FileReaderHouse) Class.forName(className).newInstance();
    }

    public void importHouse() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        getHouseConfigFileReader().importHouseConfiguration(this.configHouseFilePath);
    }

    public int getSizeOfSensorsNotAdded() {
        return sensorsNotAdded;
    }
}