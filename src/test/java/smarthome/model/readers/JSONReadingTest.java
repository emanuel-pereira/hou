package smarthome.model.readers;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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

        assertEquals(61, result.size());
    }

    @DisplayName("Testing multiple keys for each reading")
    @Test
    void importDataSet() throws IOException, ParseException {
        JSONReading jsonReading = new JSONReading();
        Path path = Paths.get("resources_tests/DataSet_sprint07_GAData.json");
        List<String[]> result;
        result = jsonReading.importData(path);

        assertEquals(5616, result.size());
    }
}