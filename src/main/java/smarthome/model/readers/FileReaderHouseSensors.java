package smarthome.model.readers;

import smarthome.model.Sensor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReaderHouseSensors {

    List<Sensor> loadData(Path path)throws org.json.simple.parser.ParseException, java.text.ParseException, IOException;
}
