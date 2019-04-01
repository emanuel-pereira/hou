package smarthome.io.ui;

import org.xml.sax.SAXException;
import smarthome.controller.DataImportCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GAList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataImportUI {
    private DataImportCTRL ctrl;
    private Path filePath;

    public DataImportUI(GAList gaList) {
        this.ctrl = new DataImportCTRL(gaList);
    }


    public void loadGeoAreaFile() {
            System.out.println("Please enter the file path to import geographical areas and sensors (eg: resources/JsonFile.json):");
            String filepath = UtilsUI.requestText("Invalid filepath.", ".*");

            try {
                this.filePath = Paths.get(filepath);
                ctrl.readGeoAreasFromFile(this.filePath);
                this.showGAsNumberInFile();
            } catch (Exception e) {
                UtilsUI.showError("File not found.", "File not found in the specified file path: " + filepath);
                UtilsUI.backToMenu();
            }
        }

    public void showGAsNumberInFile() throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException   {
        System.out.println("In the file there are\n");
        System.out.println(" - " + ctrl.getGaListInFileSize(this.filePath) + " geographical area(s).");
        System.out.println(" - " + ctrl.getAllSensorsInFileSize(this.filePath) + " sensor(s).");
        this.importGAs();
    }

    public void importGAs()throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        System.out.println("\n------");
        if(UtilsUI.confirmOption("Do you wish to import this data?(y/n)","Please type y for Yes or n for No.")){
            ctrl.importGeoAreasFromFile(this.filePath);
            int notImported = ctrl.failedToAdd();
            int imported = ctrl.getImportedGaListSize(this.filePath);
            if (notImported > 0 && imported > 0){
                System.out.println("Success! " + imported+ " geographical areas and respective sensors were imported.");
                System.out.println("Warning: " + notImported + " geographical areas and respective sensors were not imported");
                UtilsUI.backToMenu();
            }
            if(notImported > 0 && imported < 1){
                System.out.println("Warning: no geographical areas nor their sensors were imported");
                UtilsUI.backToMenu();
            }
            else {
                System.out.println("Success! " + imported+ " geographical areas and respective sensors were imported.");
                UtilsUI.backToMenu();
            }
        }
        else{
            System.out.println("Import has been canceled.");
            UtilsUI.backToMenu();
        }
    }

    public void importDataFromFile() throws org.json.simple.parser.ParseException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException {
        boolean loop = true;
        while (loop) {
            System.out.println("Please insert the directory and the name of the file (eg: resources/DataSet_sp05_SensorData.csv):");
            String filepath = UtilsUI.requestText("Invalid filepath.", "[A-Za-z0-9/._]*");
            Path path = Paths.get(filepath);
            try {
                ctrl.importReadingsFromFile(path);
                loop = false;
                System.out.println("Success!");
                this.showReadings();

            } catch (FileNotFoundException e) {
                System.out.println("File not found in the specified file path: " + filepath);
            }
        }
    }

    public void showReadings() {
        System.out.println("The following geographical areas and respective sensors were imported from the selected File:");
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
                UtilsUI.backToMenu();
    }
}
