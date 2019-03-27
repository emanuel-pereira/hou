package smarthome.controller;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;
import smarthome.model.readers.DataImport;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataImportCTRL {

    private GAList gaList;
    private DataImport dataImportR = new DataImport(gaList);
    private DataImport dataImportG = new DataImport(gaList);

    public DataImportCTRL(GAList gaList) {
        this.gaList = gaList;
    }

    /**
     * Method that reads a JSON file in the specified file path and imports all geographical areas and respective sensors, updating the gaList
     * @param filePath specified by the user
     * @throws ParseException
     * @throws org.json.simple.parser.ParseException
     * @throws IOException
     */
    public List<GeographicalArea> readGeoAreasFromFile (Path filePath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        DataImport dataImport = new DataImport(gaList);
        List<GeographicalArea> gaListInFile = dataImport.importFromFileGeoArea(filePath,"geographical_area");

        return gaListInFile;
    }

    public int getAllSensorsInFile (Path filePath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        List<Sensor> allSensors = new ArrayList<>();
        DataImport dataImport = new DataImport(gaList);
        List<GeographicalArea> gaListInFile = dataImport.importFromFileGeoArea(filePath,"geographical_area");
        for(GeographicalArea ga: gaListInFile){
            allSensors.addAll(ga.getSensorListInGA().getSensorList());
        }
        return allSensors.size();
    }

    public void importGeoAreasFromFile(Path filePath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException{
        DataImport dataImport = new DataImport(gaList);
        this.gaList.getNotAdded().clear();
        dataImport.loadGeoAreaFiles(this.readGeoAreasFromFile(filePath));
    }

    public int failedToAdd (){
        return this.gaList.getNotAdded().size();
    }

    public void importReadingsFromFile(Path filePath) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException {
        DataImport dataImport = new DataImport(gaList);
        dataImport.importFromFileReadings(filePath,"readings");
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
