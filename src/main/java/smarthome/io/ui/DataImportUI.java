package smarthome.io.ui;

import smarthome.controller.DataImportCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GAList;
import smarthome.model.OccupationAreaRepository;
import smarthome.repository.GeoRepository;
import smarthome.repository.LocationRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataImportUI {
    private DataImportCTRL ctrl;
    private List<GeographicalAreaDTO> gaListDTO = new ArrayList<>();
    private GeoRepository rep;
    private LocationRepository locRep;
    private OccupationAreaRepository ocRep;

    public DataImportUI(GAList gaList, LocationRepository repository, OccupationAreaRepository occupRep) {
        this.ctrl = new DataImportCTRL(gaList);
        this.locRep = repository;
        this.ocRep=occupRep;
    }


    public void loadJSON() throws ParseException, org.json.simple.parser.ParseException, IOException {
        boolean loop = true;
        while (loop) {
            System.out.println("Please enter the json file path to import geographical areas and sensors (eg: resources/JsonFile.json):");
            String filepath = UtilsUI.requestText("Invalid filepath.", ".*");

            try {
                Path path = Paths.get(filepath);
                gaListDTO = ctrl.loadJSON(path, ocRep,locRep);
                loop = false;
                this.showGAsDTOs();
            } catch (FileNotFoundException e) {
                UtilsUI.showError("File not found.", "Json file not found in the specified file path: " + filepath);
            }
        }
    }

    public void showGAsDTOs() {
        System.out.println("The number of geographical areas and sensors imported from JSON file is:");
        System.out.println(" - " + gaListDTO.size() + " geographical areas.");
        int nrOfSensors = 0;
        for (GeographicalAreaDTO geographicalAreaDTO : gaListDTO) {
            nrOfSensors += geographicalAreaDTO.getSensorListDTO().size();
        }
        System.out.println(" - " + nrOfSensors + " sensors.");

    }

    public void importDataFromCSVFile() throws IOException {
        boolean loop = true;
        while (loop) {
            System.out.println("Please insert the directory and the name of the file (eg: resources/DataSet_sp04_SensorData.csv):");
            String filepath = UtilsUI.requestText("Invalid filepath.", "[A-Za-z0-9/._]*");
            try {
                ctrl.importReadingsFromCSVFile(filepath);
                loop = false;
                System.out.println("Success!");
                this.showReadings();

            } catch (FileNotFoundException e) {
                System.out.println("CSV file not found in the specified file path: " + filepath);
            }
        }
    }

    public void showReadings() {
        System.out.println("The following geographical areas and respective sensors were imported from the selected JsonFile:");
        for (GeographicalAreaDTO geographicalAreaDTO : ctrl.getGAListDTO()) {
            System.out.println("GEOGRAPHICAL AREA");
            System.out.println(" Id: " + geographicalAreaDTO.getIdentification());
            System.out.println(" Name: " + geographicalAreaDTO.getDesignation());
            System.out.println(" SensorList: ");
            int counter = 1;
            for (SensorDTO sensorDTO : geographicalAreaDTO.getSensorListDTO()) {
                System.out.print("  " + counter + " - Sensor Id: " + sensorDTO.getId());
                System.out.println(" | Name " + sensorDTO.getDesignation());
                System.out.println("Number of readings imported: " + sensorDTO.getReadingListDTO().size());
                counter++;
            }
        }
    }
}
