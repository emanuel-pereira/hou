package smarthome.controller;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.RoomList;
import smarthome.model.Sensor;
import smarthome.model.readers.DataImport;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataImportCTRL {

    private GAList gaList;
    private RoomList roomList;
    private DataImport dataImport;


    /**
     * Constructor for importing data related to GAList.
     * Creates an instance of the DataImportCTRL with GAList passed as parameter when DataImportUI is invoked through
     * SystemAdministration menu, i.e, when the user wants to import information related to GAList,
     * such as sensors or readings.
     *
     * @param gaList parameter to be updated with imported data
     */
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


    private List<GeographicalArea> readGeoAreasFromFile(Path filePath) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        return dataImport.loadGeoAreaFiles(filePath);
    }

    public int getGaListInFileSize(Path filePath) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        return this.readGeoAreasFromFile(filePath).size();
    }

    public int getAllSensorsInFileSize(Path filePath) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        List<Sensor> allSensors = new ArrayList<>();
        List<GeographicalArea> gaListInFile = dataImport.loadGeoAreaFiles(filePath);
        for (GeographicalArea ga : gaListInFile) {
            allSensors.addAll(ga.getSensorListInGA().getSensorList());
        }
        return allSensors.size();
    }

    public void importGeoAreasFromFile(Path filePath) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        this.gaList.getNotAdded().clear();
        dataImport.importFromFileGeoArea(this.readGeoAreasFromFile(filePath));
    }

    public int failedToAdd() {
        return this.gaList.getNotAdded().size();
    }

    public int getImportedGaListSize(Path filepath) throws IllegalAccessException, ParseException, IOException, InstantiationException, java.text.ParseException, ClassNotFoundException {
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

    public int getNrOfImportedReadings(){
        return dataImport.getNrOfAddedReadings();
    }

    public int getNrOfInvalidReadings(){
        return dataImport.getNrOfInvalidReadings();
    }
}
