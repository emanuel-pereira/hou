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
        Path path = Paths.get("resources/DataSet_sprint05_SD.csv");
        List<String[]> result;
        result = csvReading.importData(path);

        assertEquals(62,result.size());
    }
}