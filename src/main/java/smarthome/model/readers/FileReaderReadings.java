package smarthome.model.readers;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReaderReadings {

    List<String[]> importData(Path filePath) throws SAXException, ParserConfigurationException, ParseException, IOException;
}