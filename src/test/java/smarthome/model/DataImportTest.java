package smarthome.model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import smarthome.model.readers.DataImport;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataImportTest {

    @Test
    void getFileExtensionTest() {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.json");
        String result = dataImport.getFileExtension(path);
        String expected = "json";
        assertEquals(expected, result);
    }

    @Test
    void getJsonClassNameTest() throws IOException, ParseException {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        String result = dataImport.getClassName("readings", "json");
        String expected = "smarthome.model.readers.JSONReading";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Ga List size in file")
    void getGAListInFileSize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");

        int expected = 2;
        int result = dataImport.loadGeoAreaFiles(path).size();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Get Ga List size after import")
    void getGAListImportedSize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");

        List<GeographicalArea> inFile = dataImport.loadGeoAreaFiles(path);
        dataImport.importFromFileGeoArea(inFile);

        int expected = 2;
        int result = gaList.size();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Get number of not added GAs")
    void getGAListNotAddedSize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");

        List<GeographicalArea> inFile1 = dataImport.loadGeoAreaFiles(path);
        List<GeographicalArea> inFile2 = dataImport.loadGeoAreaFiles(path);
        List<GeographicalArea> inFile3 = dataImport.loadGeoAreaFiles(path);
        dataImport.importFromFileGeoArea(inFile1);
        dataImport.importFromFileGeoArea(inFile2);
        dataImport.importFromFileGeoArea(inFile3);

        int expected = 2;
        int result = dataImport.notAddedNumber();

        assertEquals(expected,result);
    }

    @Test
    void getReadingValueAfterImportingTest() throws IllegalAccessException,ClassNotFoundException,InstantiationException,IOException,ParseException, SAXException, ParserConfigurationException {
        GAList gaList = new GAList();
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        gaList.addGA(ga);
        GregorianCalendar startDate = new GregorianCalendar(2018, Calendar.MARCH, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorISEP = new Sensor("TT12346", "SensorISEP", startDate, location, temp, "C", new ReadingList());
        Sensor sensorPorto = new Sensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGA().addSensor(sensorISEP);
        ga.getSensorListInGA().addSensor(sensorPorto);

        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.json");
        dataImport.importReadingsFromFile(path);

        List<Reading> rList = ga.getSensorListInGA().getSensorList().get(0).getReadingList().getReadingsList();
        double r = rList.get(3).returnValueOfReading();
        assertEquals(15.1, r);
    }

    @Test
    void checkIfInvalidReadsAreWrittenOnLogger() throws SAXException, ParserConfigurationException, IllegalAccessException,ClassNotFoundException,InstantiationException,IOException,ParseException {
        GAList gaList = new GAList();
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        gaList.addGA(ga);
        GregorianCalendar startDate = new GregorianCalendar(2020, Calendar.MARCH, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorISEP = new Sensor("TT12346", "SensorISEP", startDate, location, temp, "C", new ReadingList());
        Sensor sensorPorto = new Sensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGA().addSensor(sensorISEP);
        ga.getSensorListInGA().addSensor(sensorPorto);

        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.json");
        dataImport.importReadingsFromFile(path);

        List<Reading> rList = ga.getSensorListInGA().getSensorList().get(0).getReadingList().getReadingsList();
        int size = rList.size();
        assertEquals(0, size);
    }

}

