package smarthome.model.readers;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLReadingTest {


    @Test
    void totalReadingImports() throws FileNotFoundException {


        XMLReading xmlReading = new XMLReading();

        Path path = Paths.get("resources/DataSet_sprint05_SensorData_alt01.xml");
        List<String[]> result;
        result = xmlReading.importData(path);

        assertEquals(result.size(), 61);

    }


}