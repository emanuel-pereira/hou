package smarthome.model.readers;

import org.json.simple.parser.ParseException;
import smarthome.model.GAList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReaderReadings {

    List<String[]> importData(Path filePath, GAList gaList) throws ParseException, IOException;
}
