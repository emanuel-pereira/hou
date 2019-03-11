package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.GAList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class JSONImportCTRLTest {

    @Test
    @DisplayName("Ensure that GAList has 2 GAs after executing loadJSON method")
    void loadJSON() throws ParseException, org.json.simple.parser.ParseException, IOException {
        GAList gaList = new GAList();
        JSONImportCTRL ctrl = new JSONImportCTRL(gaList);
        String filepath = "resources/JsonFile.json";
        try {
            ctrl.loadJSON(filepath);
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
        JSONImportCTRL ctrl = new JSONImportCTRL(gaList);
        String filepath = "resources/JsonFile1.json";
        String resultMsg="";
        try {
            ctrl.loadJSON(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
            resultMsg = e.getMessage();
        }
        String expected1="resources\\JsonFile1.json (O sistema não conseguiu localizar o ficheiro especificado)";
        assertEquals(expected1,resultMsg);
        int expected = 0;
        int result = gaList.size();
        assertEquals(expected, result);
    }
}