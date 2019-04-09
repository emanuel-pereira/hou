package smarthome.model.readers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReaderHouseSensors {

    List<String[]> loadData(Path path)throws org.json.simple.parser.ParseException, java.text.ParseException, IOException;
}
