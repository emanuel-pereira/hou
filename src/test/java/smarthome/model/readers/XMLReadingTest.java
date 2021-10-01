package smarthome.model.readers;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLReadingTest {


    @Test
    void totalReadingImports() throws IOException, SAXException, ParserConfigurationException {


        XMLReading xmlReading = new XMLReading();

        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.xml");
        List<String[]> result;
        result = xmlReading.importData(path);

        assertEquals(61, result.size());

    }

}
