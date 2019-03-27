package smarthome.controller;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.ReadJSONFile;
import smarthome.repository.LocationRepository;
import smarthome.repository.OccupationAreaRepository;
import smarthome.repository.SensorRepository;
import smarthome.repository.SensorTypeRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataImportCTRL {

    private GAList gaList;

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
   public List<GeographicalAreaDTO> loadJSON(Path filePath) throws ParseException,org.json.simple.parser.ParseException, IOException {
        ReadJSONFile jsonFile = new ReadJSONFile(filePath, gaList);

        return jsonFile.importGAs();

    }

    public List<GeographicalAreaDTO> loadJSON(Path filePath, OccupationAreaRepository occupationRep, LocationRepository locationRep, SensorRepository sensorRep, SensorTypeRepository sensorTypeRep) throws ParseException, org.json.simple.parser.ParseException, IOException {
        ReadJSONFile jsonFile = new ReadJSONFile(filePath, gaList);
        jsonFile.setLocationRepository(locationRep);
        jsonFile.setOccupationAreaRepository(occupationRep);
        jsonFile.setSensorRepository(sensorRep);
        jsonFile.setSensorTypeRep(sensorTypeRep);
        return jsonFile.importGAs();

    }

    public void importReadingsFromCSVFile(String filePath) throws IOException {
        gaList.importDataFromCSVFileForEachGA(filePath);
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
