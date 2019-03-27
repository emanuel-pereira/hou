package smarthome.model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import smarthome.model.readers.DataImport;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataImportTest {

    @Test
    void getFileExtensionTest() {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources/DataSet_sprint05_SensorData.json");
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
    void getReadingValueAfterImportingTest() throws IllegalAccessException,ClassNotFoundException,InstantiationException,IOException,ParseException {
        GAList gaList = new GAList();
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        gaList.addGA(ga);
        GregorianCalendar startDate = new GregorianCalendar(2018, 2, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorISEP = new Sensor("TT12346", "SensorISEP", startDate, location, temp, "C", new ReadingList());
        Sensor sensorPorto = new Sensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGA().addSensor(sensorISEP);
        ga.getSensorListInGA().addSensor(sensorPorto);

        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources/DataSet_sprint05_SensorData.json");
        dataImport.importFromFileReadings(path,"readings");

        List<Reading> rList = ga.getSensorListInGA().getSensorList().get(0).getReadingList().getReadingsList();
        double r = rList.get(3).returnValueOfReading();
        assertEquals(15.1, r);
    }

    @Test
    void checkIfInvalidReadsAreWrittenOnLogger() throws IllegalAccessException,ClassNotFoundException,InstantiationException,IOException,ParseException {
        GAList gaList = new GAList();
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        gaList.addGA(ga);
        GregorianCalendar startDate = new GregorianCalendar(2020, 2, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorISEP = new Sensor("TT12346", "SensorISEP", startDate, location, temp, "C", new ReadingList());
        Sensor sensorPorto = new Sensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGA().addSensor(sensorISEP);
        ga.getSensorListInGA().addSensor(sensorPorto);

        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources/DataSet_sprint05_SensorData.json");
        dataImport.importFromFileReadings(path,"readings");

        List<Reading> rList = ga.getSensorListInGA().getSensorList().get(0).getReadingList().getReadingsList();
        int size = rList.size();
        assertEquals(0, size);
    }

}

