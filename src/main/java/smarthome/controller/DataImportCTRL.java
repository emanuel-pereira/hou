package smarthome.controller;

import org.xml.sax.SAXException;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;
import smarthome.model.readers.DataImport;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataImportCTRL {

    private GAList gaList;

    public DataImportCTRL(GAList gaList) {
        this.gaList = gaList;
    }

    /**
     */
    private List<GeographicalArea> readGeoAreasFromFile (Path filePath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        DataImport dataImport = new DataImport(gaList);
        return dataImport.loadGeoAreaFiles(filePath);
    }
    public int getGaListInFileSize (Path filePath)throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        return this.readGeoAreasFromFile(filePath).size();
    }

    public int getAllSensorsInFileSize(Path filePath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        List<Sensor> allSensors = new ArrayList<>();
        DataImport dataImport = new DataImport(gaList);
        List<GeographicalArea> gaListInFile = dataImport.loadGeoAreaFiles(filePath);
        for(GeographicalArea ga: gaListInFile){
            allSensors.addAll(ga.getSensorListInGA().getSensorList());
        }
        return allSensors.size();
    }

    public void importGeoAreasFromFile(Path filePath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException{
        DataImport dataImport = new DataImport(gaList);
        this.gaList.getNotAdded().clear();
        dataImport.importFromFileGeoArea(this.readGeoAreasFromFile(filePath));
    }

    public int failedToAdd (){
        return this.gaList.getNotAdded().size();
    }

    public int getImportedGaListSize (Path filepath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        return getGaListInFileSize(filepath) - failedToAdd();
    }

    public void importReadingsFromFile(Path filePath) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, ParserConfigurationException, SAXException {
        DataImport dataImport = new DataImport(gaList);
        dataImport.importReadingsFromFile(filePath);
    }
    /**
     *Method that iterates the geographical area list and converts each geographical area to a Data Transfer Object
     * @return a list of geographical area DTOs
     */
    public List<GeographicalAreaDTO> getGAListDTO() {
        List<GeographicalAreaDTO> gaListDTO=new ArrayList<>();
        for (GeographicalArea ga : gaList.getGAList()) {
            GeographicalAreaDTO gaDTO=ga.toDTO();
            gaListDTO.add(gaDTO);
        }
        return gaListDTO;
    }
}
