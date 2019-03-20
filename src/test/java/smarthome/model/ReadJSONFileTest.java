package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ReadJSONFileTest {

    @Test
    @DisplayName("Ensure the size of the GAList is 2 after reading the JSON file from relative path")
    void readGAsFromRelativePath() throws ParseException, IOException, org.json.simple.parser.ParseException {
        GAList gaList = new GAList();
        Path path = Paths.get("resources/JsonFile.json");
        ReadJSONFile js = new ReadJSONFile(path, gaList);
        js.importGAs();
        int expected = 2;
        int result = gaList.size();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure the size of the GAList isn't zero after reading the JSON file")
    void readGAsDoesNotHaveSizeZero() throws ParseException, IOException, org.json.simple.parser.ParseException {
        GAList gaList = new GAList();
        Path path = Paths.get("resources/JsonFile.json");
        ReadJSONFile js = new ReadJSONFile(path, gaList);
        js.importGAs();
        int expected = 0;
        int result = gaList.size();
        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that Porto is of city type")
    void ensurePortoIsOfCityType() throws ParseException, IOException, org.json.simple.parser.ParseException {
        GAList gaList = new GAList();
        Path path = Paths.get("resources/JsonFile.json");
        ReadJSONFile js = new ReadJSONFile(path, gaList);
        js.importGAs();
        GeographicalArea porto = gaList.getLastGA();
        String expected = "city";
        String result = porto.getType();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that ISEP contains two sensors")
    void ensureISEPHasTwoSensors() throws ParseException, IOException, org.json.simple.parser.ParseException {
        GAList gaList = new GAList();
        Path path = Paths.get("resources/JsonFile.json");
        ReadJSONFile js = new ReadJSONFile(path, gaList);
        js.importGAs();
        GeographicalArea porto = gaList.get(0);
        SensorList sensorList = porto.getSensorListInGA();
        String expected = "1 - Meteo station ISEP - rainfall\n" +
                "2 - Meteo station ISEP - temperature\n";
        String result = sensorList.showSensorListInString();
        assertEquals(expected, result);
    }
}