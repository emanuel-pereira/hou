package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.GAList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class DataImportCTRLTest {

    @Test
    @DisplayName("Ensure that GAList has 2 GAs after executing loadJSON method")
    void loadJSON() throws ParseException, org.json.simple.parser.ParseException, IOException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile.json";
        try {
            Path path = Paths.get(filepath);
            ctrl.loadJSON(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
        }
        int expected = 2;
        int result = gaList.size();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that GAList has 0 GAs after executing loadJSON method when json file is not found in specified path")
    void loadJSON1() throws ParseException, org.json.simple.parser.ParseException, IOException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile1.json";
        try {
            Path path = Paths.get(filepath);
            ctrl.loadJSON(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
            e.getMessage();
        }

        int expected = 0;
        int result = gaList.size();
        assertEquals(expected, result);
    }

    @Test
    void getGAListDTO() throws ParseException, org.json.simple.parser.ParseException, IOException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile.json";
        try {
            Path path = Paths.get(filepath);

            ctrl.loadJSON(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
        }
        ctrl.getGAListDTO();
        int expected = 2;
        int result = ctrl.getGAListDTO().size();
        assertEquals(expected, result);
    }

    @Test
    void importReadingsFromCSVFileTest() throws IOException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        ctrl.importReadingsFromCSVFile("resources/ReadingsRegistry");
    }

}