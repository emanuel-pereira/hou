package smarthome.io.ui;

import org.xml.sax.SAXException;
import smarthome.controller.DataImportCTRL;
import smarthome.model.GAList;
import smarthome.model.RoomList;
import smarthome.model.SensorTypeList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataImportUI {
    private DataImportCTRL ctrl;
    private Path filePath;

    /**
     * Constructor for importing data related to GAList.
     * Creates an instance of the DataImportUI with GAList passed as parameter when DataImportUI is invoked through
     * SystemAdministration menu, i.e, when the user wants to import information related to GAList,
     * such as sensors or readings.
     *
     * @param gaList parameter to be updated with imported data
     */
    public DataImportUI(GAList gaList) {
        this.ctrl = new DataImportCTRL(gaList);
    }

    /**
     * Constructor for importing data related to RoomList.
     * Creates an instance of the DataImportCTRL with RoomList passed as parameter when DataImportUI is invoked through
     * HouseAdministration menu, i.e, when the user wants to import information related to RoomList, such as sensors
     * or readings.
     *
     * @param roomList parameter to be updated with imported data
     */
    public DataImportUI(RoomList roomList, SensorTypeList sensorTypeList) {
        this.ctrl = new DataImportCTRL(roomList, sensorTypeList);
    }

    public void loadGeoAreaFile() {
        System.out.println("Please enter the file path to import geographical areas and sensors (eg: resources/DataSet_sprint05_GA.json):");
        String filepath = UtilsUI.requestText("Invalid filepath.", ".*");

        try {
            this.filePath = Paths.get(filepath);
            this.showGAsNumberInFile();
        } catch (Exception e) {
            UtilsUI.showError("File not found.", "File not found in the specified file path: " + filepath);
            UtilsUI.backToMenu();
        }
    }

    public void showGAsNumberInFile() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        System.out.println("In the file there are\n");
        System.out.println(" - " + ctrl.getGaListInFileSize(this.filePath) + " geographical area(s).");
        System.out.println(" - " + ctrl.getAllSensorsInFileSize(this.filePath) + " sensor(s).");
        this.importGAs();
    }

    public void importGAs() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        System.out.println("\n------");
        if (UtilsUI.confirmOption("Do you wish to import this data?(y/n)", "Please type y for Yes or n for No.")) {
            ctrl.importGeoAreasFromFile(this.filePath);
            int notImported = ctrl.failedToAdd();
            int imported = ctrl.getImportedGaListSize(this.filePath);
            if (notImported > 0 && imported > 0) {
                System.out.println("Success! " + imported + " geographical areas and respective sensors were imported.");
                System.out.println("Warning: " + notImported + " geographical areas and respective sensors were not imported");
                UtilsUI.backToMenu();
            }
            if (notImported > 0 && imported < 1) {
                System.out.println("Warning: no geographical areas nor their sensors were imported");
                UtilsUI.backToMenu();
            } else {
                System.out.println("Success! " + imported + " geographical areas and respective sensors were imported.");
                UtilsUI.backToMenu();
            }
        } else {
            System.out.println("Import has been canceled.");
            UtilsUI.backToMenu();
        }
    }


    public void importReadings(Object object) throws org.json.simple.parser.ParseException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException {
        boolean loop = true;
        while (loop) {
            System.out.println("Please insert the directory and the name of the file (eg: resources/DataSet_sprint05_SD.csv):");
            String filepath = UtilsUI.requestText("Invalid filepath.", "[A-Za-z0-9/._]*");
            Path path = Paths.get(filepath);
            try {
                ctrl.importReadingsFromFile(path, object);
                loop = false;
                System.out.println("Readings imported!");
                /*TODO: replace this method to show nr of imported and nr of non-imported readings
                this.showReadings();*/
            } catch (FileNotFoundException e) {
                System.out.println("File not found in the specified file path: " + filepath);
            }
        }
    }

    public void importHouseSensors() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        int[] counters;
        System.out.println("\n------");
        if (UtilsUI.confirmOption("Do you wish to import this data?(y/n)", "Please type y for Yes or n for No.")) {
            counters = ctrl.importHouseSensors(this.filePath);
            int sensorsAdded = counters[0];
            int sensorsNotAdded = counters[1];
            if (sensorsNotAdded > 0 && sensorsAdded > 0) {
                System.out.println("Success! " + sensorsAdded + " sensors were imported.");
                System.out.println("Warning: " + sensorsNotAdded + " sensors were not imported. Check log file for details.");
                UtilsUI.backToMenu();
            }
            if (sensorsNotAdded > 0 && sensorsAdded < 1) {
                System.out.println("Info: no sensors were imported. Check log file for details.");
                UtilsUI.backToMenu();
            }
            if (sensorsNotAdded < 1 && sensorsAdded > 0) {
                System.out.println("Success! " + sensorsAdded + " sensors were imported.");
                UtilsUI.backToMenu();
            }
        } else {
            System.out.println("Import has been canceled.");
            UtilsUI.backToMenu();
        }
    }


    public void loadHouseSensorsFile() {
        if (this.roomListEmpty()) {
            System.out.println("Before importing sensors please add rooms first.\n");
            return;
        }
        if (this.sensorTypeListEmpty()) {
            System.out.println("Before importing sensors please add sensor types first.\n");
            return;
        }
        System.out.println("Please enter the file path to import sensors (eg: resources/DataSet_sprint06_HouseSensors.json):");
        String filepath = UtilsUI.requestText("Invalid filepath.", ".*");
        try {
            this.filePath = Paths.get(filepath);
            this.numberOfSensorsInFile();
        } catch (Exception e) {
            UtilsUI.showError("File not found.", "File not found in the specified file path: " + filepath);
            UtilsUI.backToMenu();
        }
    }

    public void numberOfSensorsInFile() throws
            IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        System.out.println("In the file there are:\n");
        System.out.println(" - " + ctrl.sizeOfSensorsFile(this.filePath) + " sensor(s).");
        this.importHouseSensors();
    }

    private boolean roomListEmpty() {
        return this.ctrl.roomListSize() == 0;
    }

    private boolean sensorTypeListEmpty() {
        return this.ctrl.sensorTypeListSize() == 0;
    }

}


    /*public void showReadings() {
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
}*/
