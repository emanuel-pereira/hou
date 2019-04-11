package smarthome.model.readers;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.TypeGAList;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONReadingTest {

    @Test
    void importDataSizeListTest() throws IOException, ParseException {
        JSONReading jsonReading = new JSONReading();
        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.json");
        List<String[]> result;
        result = jsonReading.importData(path);

        assertEquals(result.size(),61);
    }
}