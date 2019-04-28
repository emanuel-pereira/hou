package smarthome.controller;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import smarthome.model.*;
import smarthome.model.readers.DataImport;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataImportCTRL {

    private GAList gaList;
    private RoomList roomList;
    private SensorTypeList sensorTypeList;
    private DataImport dataImport;

    public DataImportCTRL(GAList gaList) {
        this.gaList = gaList;
        this.dataImport = new DataImport(gaList);
    }

    /**
     * Constructor for importing data related to RoomList.
     * Creates an instance of the DataImportCTRL with RoomList passed as parameter when DataImportUI is invoked through
     * HouseAdministration menu, i.e, when the user wants to import information related to RoomList, such as sensors
     * or readings.
     *
     * @param roomList parameter to be updated with imported data
     */
    public DataImportCTRL(RoomList roomList) {
        this.roomList = roomList;
        this.dataImport = new DataImport(roomList);
    }

    /**
     * Constructor for importing data related to RoomList and SensorTypeList.
     * Creates an instance of the DataImportCTRL with RoomList and SensorTypeList passed as parameters when DataImportUI is invoked through
     * HouseAdministration menu, i.e, when the user wants to import information related to RoomList, such as sensors
     * or readings.
     *
     * @param roomList parameter to be updated with imported data
     * @param sensorTypeList parameter to be updated with imported data
     */
    public DataImportCTRL(RoomList roomList, SensorTypeList sensorTypeList) {
        this.roomList = roomList;
        this.sensorTypeList = sensorTypeList;
    }

    /**
     * @return the size of the room list to check in the UI if there are already rooms configured.
     */
    public int roomListSize() {
        return this.roomList.getRoomListSize();
    }


    /**
     *
     * @return the nr of sensors in the room list
     */
    public int nrOfSensorsInAllRooms() {
        int size = 0;
        for (Room r : roomList.getRoomList()) {
            size += r.getSensorListInRoom().size();
        }
        return size;
    }

    private List<GeographicalArea> readGeoAreasFromFile (Path filePath) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException, ParserConfigurationException {
        return dataImport.loadGeoAreaFiles(filePath);
    }

    public int getGaListInFileSize (Path filePath) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException, ParserConfigurationException {
        return this.readGeoAreasFromFile(filePath).size();
    }

    public int getAllSensorsInFileSize(Path filePath) throws ParserConfigurationException,IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        List<Sensor> allSensors = new ArrayList<>();
        List<GeographicalArea> gaListInFile = dataImport.loadGeoAreaFiles(filePath);
        for (GeographicalArea ga : gaListInFile) {
            allSensors.addAll(ga.getSensorListInGA().getSensorList());
        }
        return allSensors.size();
    }

    public void importGeoAreasFromFile(Path filePath) throws ParserConfigurationException, IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException{
        dataImport.importFromFileGeoArea(this.readGeoAreasFromFile(filePath));
    }

    public int failedToAdd (){
        return dataImport.notAddedNumber();
    }

    public int getImportedGaListSize (Path filepath) throws ParserConfigurationException, IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        return getGaListInFileSize(filepath) - failedToAdd();
    }

    public void importReadingsFromFile(Path filePath, Object object) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, ParserConfigurationException, SAXException {
        if (object.equals(gaList)) {
            dataImport.importReadingsFromFile(filePath, object);
        }
        if (object.equals(roomList)) {
            dataImport.importReadingsFromFile(filePath, object);
        }
    }

    public int[] importHouseSensors(Path filePath) throws IllegalAccessException, ParseException, InstantiationException, IOException, java.text.ParseException, ClassNotFoundException {
        DataImport dataImport = new DataImport(roomList, sensorTypeList);
        List<String[]> dataToImport = dataImport.loadHouseSensorsFiles(filePath);
        dataImport.importHouseSensors(dataToImport);
        int[] counters = new int[2];
        counters[0] = dataImport.getSizeOfSensorsAdded();
        counters[1] = dataImport.getSizeOfSensorsNotAdded();
        return counters;
    }

    public int getSizeOfSensorsFile(Path filePath) throws IllegalAccessException, ParseException, InstantiationException, IOException, java.text.ParseException, ClassNotFoundException {
        DataImport dataImport = new DataImport(roomList, sensorTypeList);
        return dataImport.loadHouseSensorsFiles(filePath).size();
    }

    public int sensorTypeListSize(){
        return this.sensorTypeList.getSensorTypeList().size();
    }

    /**
     * @return the number of imported readings
     */
    public int getNrOfImportedReadings(){
        return dataImport.getNrOfAddedReadings();
    }

    /**
     * @return the number of invalid readings which includes readings outside the sensor's operation
     * period or readings that don't match any sensorId
     */
    public int getNrOfInvalidReadings(){
        return dataImport.getNrOfInvalidReadings();
    }

    public int typeGAListSize() {
        return TypeGAList.size();
    }
}