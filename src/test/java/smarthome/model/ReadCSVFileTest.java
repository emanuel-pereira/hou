package smarthome.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadCSVFileTest {
    
    @Test
    void getReadingsTest() throws FileNotFoundException {
        ReadCSVFile csv = new ReadCSVFile();
        csv.readCsvFile("resources/DataSet_sp04_SensorData.csv");
        int result = csv.getValuesFromCSVFile().size();
        int expected = 62;
        assertEquals(expected, result);
    }

    @Test
    void writeOnCSVFileTest() throws IOException {
        ReadCSVFile csv = new ReadCSVFile();
        csv.writeCSVFile("test.csv.txt");
        csv.writeStringOnCSVFile("Test");
        csv.writeStringOnCSVFile("A123,203,12-01-2018");
        csv.writeStringOnCSVFile("sensorID,78,2");
        csv.readCsvFile("resources/test.csv.txt");
        int result = csv.getValuesFromCSVFile().size();
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    void writeOnCSVFileFailTest() throws IOException {
        ReadCSVFile csv = new ReadCSVFile();
        csv.writeCSVFile("nofile.txt");
        csv.writeStringOnCSVFile("Test");
    }
}

