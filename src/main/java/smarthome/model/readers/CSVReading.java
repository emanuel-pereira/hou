package smarthome.model.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReading implements FileReaderReadings {
    String commaDelimiter = ",";
    Path filePathAndName;


    public List<String[]> importData(Path filePathAndName) throws FileNotFoundException {
        this.filePathAndName = filePathAndName;
        List<String[]> dataToImport = new ArrayList<>();
        File file = new File(filePathAndName.toString());
        Scanner scanner = new Scanner(file);
        //to ignore csv file header
        scanner.nextLine();
        scanner.useDelimiter(this.commaDelimiter);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] fields = line.split(this.commaDelimiter);
            dataToImport.add(fields);
        }
        scanner.close();
        return dataToImport;
    }
}
