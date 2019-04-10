package smarthome.model.readers;

import org.json.simple.parser.ParseException;
import smarthome.model.*;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReaderHouse {

    void importHouseConfiguration(Path path) throws IOException, ParseException;

}
