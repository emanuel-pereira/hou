package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import smarthome.model.GAList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataImportCTRLTest {

    @Test
    @DisplayName("Ensure that GAList has 2 GAs after executing loadJSON method")
    void loadGeoAreas() throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile.json";
        try {
            Path path = Paths.get(filepath);
            ctrl.importGeoAreasFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
        }
        int expected = 2;
        int result = gaList.size();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that GAList has 0 GAs after executing loadJSON method when json file is not found in specified path")
    void loadGeoAreasFileNotFound()throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile1.json";
        try {
            Path path = Paths.get(filepath);
            ctrl.importGeoAreasFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
            e.getMessage();
        }

        int expected = 0;
        int result = gaList.size();
        assertEquals(expected, result);
    }

    @Test
    void getAllSensorsInFile()throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException{
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);

        int expected = 4;
        int result = ctrl.getAllSensorsInFileSize(path);

        assertEquals(expected,result);
    }

    @Test
    void failToAdd() throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException{
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);
        ctrl.importGeoAreasFromFile(path);

        int expected = 2;
        int result = ctrl.failedToAdd();

        assertEquals(expected,result);
    }

    @Test
    void importReading () throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, ParserConfigurationException, SAXException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/DataSet_sprint05_SensorData.json";

        Path path = Paths.get(filepath);
        ctrl.importReadingsFromFile(path);

        int expected = 0;
        int result = gaList.getAllReadings().size();

        assertEquals(expected,result);

    }

    @Test
    void getGAListDTO() throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile.json";
        try {
            Path path = Paths.get(filepath);

            ctrl.importGeoAreasFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
        }
        ctrl.getGAListDTO();
        int expected = 2;
        int result = ctrl.getGAListDTO().size();
        assertEquals(expected, result);
    }
}