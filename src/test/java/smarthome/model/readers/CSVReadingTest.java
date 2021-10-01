package smarthome.model.readers;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVReadingTest {

    @Test
    void importDataSizeListTest() throws FileNotFoundException {
        CSVReading csvReading = new CSVReading();
        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.csv");

        int result = csvReading.importData(path).size();

        assertEquals(61,result);
    }
}