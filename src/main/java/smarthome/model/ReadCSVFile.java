package smarthome.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReadCSVFile {

    Scanner scanner;
    String COMMA_DELIMITER = ",";
    FileWriter fileWriter;


    public void readCsvFile(String fileName) throws FileNotFoundException {
        scanner = null;
        scanner = new Scanner(new File(fileName));
        scanner.useDelimiter(COMMA_DELIMITER);
    }

    public List<String[]> getValuesFromCSVFile() {
        List<String[]> csvValues = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(COMMA_DELIMITER);
            csvValues.add(tokens);
        }
        //csvValues.remove(0);
        return csvValues;
    }

    public void writeCSVFile(String fileName) throws IOException {
        fileWriter = new FileWriter("resources/" + fileName);
    }

    public void writeStringOnCSVFile(String toWriteOnFile) throws IOException {
        fileWriter.write(toWriteOnFile);
        fileWriter.write(System.lineSeparator());
        fileWriter.flush();
    }
}
