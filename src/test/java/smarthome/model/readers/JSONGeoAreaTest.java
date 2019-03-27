package smarthome.model.readers;

import org.junit.jupiter.api.Test;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONGeoAreaTest {

    @Test
    void importGAListSize() throws org.json.simple.parser.ParseException, java.text.ParseException, IOException{
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        int expected = 2;
        int result = reader.importData(path).size();

        assertEquals(expected,result);
    }

    @Test
    void checkIfImportGANotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.importData(path);

        GeographicalArea porto = gaListInFile.get(1);
        String expected = "city";
        String result = porto.getType();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportOccupationAreaNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.importData(path);

        GeographicalArea porto = gaListInFile.get(1);
        double expected = 10.09;
        double result = porto.getOccupation().getWidth();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportLocationNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.importData(path);

        GeographicalArea porto = gaListInFile.get(1);

        double expected = 41.149935;
        double result = porto.getLocation().getLatitude();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorListNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.importData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGA().getSensorList();

        int expected = 2;
        int result = sensorList.size();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.importData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGA().getSensorList();
        Sensor sensor = sensorList.get(1);

        String expected = "TT1236A";
        String result = sensor.getId();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorDateNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.importData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGA().getSensorList();
        Sensor sensor = sensorList.get(1);

        GregorianCalendar expected = new GregorianCalendar(2017,Calendar.NOVEMBER,16);
        Calendar result = sensor.getStartDate();
        assertEquals(expected, result);
    }

    @Test
    void getAllSensorsInFile () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources/JsonFile.json");
        JSONGeoArea reader = new JSONGeoArea();

        reader.importData(path);

        int expected = 4 ;
        int result = reader.getAllSensors().size();

        assertEquals(expected,result);
    }
}