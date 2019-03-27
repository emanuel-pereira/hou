package smarthome.model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import smarthome.model.readers.DataImport;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataImportTest {

    @Test
    void getFileExtensionTest() {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources/DataSet_sprint05_SensorData.json");
        String result = dataImport.getFileExtension(path);
        String expected = "json";
        assertEquals(expected, result);
    }

    @Test
    void getClassNameTest() throws IOException, ParseException {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources/DataSet_sprint05_SensorData.json");
        String result = dataImport.getClassName("readings","json");
        String expected = "smarthome.model.readers.JSONReading";
        assertEquals(expected, result);
    }
}

