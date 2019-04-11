package smarthome.io.ui;

import org.json.simple.parser.ParseException;
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
    /**
     * Constructor for importing data related to RoomList.
     * Creates an instance of the DataImportCTRL with RoomList passed as parameter when DataImportUI is invoked through
     * HouseAdministration menu, i.e, when the user wants to import information related to RoomList, such as sensors
     * or readings.
     *
     * @param roomList parameter to be updated with imported data
     */
    public DataImportUI(RoomList roomList) {
        this.ctrl = new DataImportCTRL(roomList);
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
            }
            if (notImported < 1 && imported > 0) {
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
                if (UtilsUI.confirmOption("Please confirm if you want to import room sensors' readings. (y/n)", "Please type y for Yes or n for No.")) {
                    ctrl.importReadingsFromFile(path, object);
                    int invalidReadings = ctrl.getNrOfInvalidReadings();
                    int importedReadings = ctrl.getNrOfImportedReadings();
                    System.out.println("Readings import task completed:");
                    if (importedReadings == 0) {
                        System.out.println("No readings were imported. Please verify if the file contains valid readings.");
                    }
                    if (invalidReadings > 0) {
                        System.out.println(" - " + invalidReadings + "readings are invalid and saved on the activity log");
                    }
                    if (importedReadings > 0) {
                        System.out.println(" - " + ctrl.getNrOfImportedReadings() + " readings were imported\n");
                    }
                }
                loop = false;
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
            UtilsUI.showError("File not found.", "Incorrect file or invalid path");
            UtilsUI.backToMenu();
        }
    }

    private void numberOfSensorsInFile() throws
            IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        System.out.println("In the file there are:\n");
        System.out.println(" - " + ctrl.getSizeOfSensorsFile(this.filePath) + " sensor(s).");
        this.importHouseSensors();
    }

    private boolean roomListEmpty() {
        return this.ctrl.roomListSize() == 0;
    }

    private boolean sensorTypeListEmpty() {
        return this.ctrl.sensorTypeListSize() == 0;
    }

    public void checkIfRoomsAndSensorsExists(Object object) throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {

        if (ctrl.roomListSize() != 0 && ctrl.getSizeSensorListInHouseRooms() != 0) {
            this.importReadings(object);

        } else {
            System.out.println("There are no sensors or rooms in house. Please add one sensor on a room of the house for insert readings." +
                    "To import readings sensors, the house must have, at least, one room and one sensor.");
            UtilsUI.backToMenu();

        }
    }

}
