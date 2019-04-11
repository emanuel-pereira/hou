package smarthome.model.readers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.GeographicalArea;
import smarthome.model.House;
import smarthome.model.Sensor;
import smarthome.model.TypeGAList;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONGeoAreaTest {

    TypeGAList typeGAList = TypeGAList.getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
        Field instance2 = TypeGAList.class.getDeclaredField("typeGaList");
        instance2.setAccessible(true);
        instance2.set(null, null);
    }

    @Test
    void importGAListSize() throws org.json.simple.parser.ParseException, java.text.ParseException, IOException{
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        int expected = 2;
        int result = reader.loadData(path).size();

        assertEquals(expected,result);
    }

    @Test
    void checkIfImportGANotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);

        GeographicalArea porto = gaListInFile.get(1);
        String expected = "city";
        String result = porto.getTypeName();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportOccupationAreaNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);

        GeographicalArea porto = gaListInFile.get(1);
        double expected = 10.09;
        double result = porto.getOccupation().getWidth();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportLocationNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);

        GeographicalArea porto = gaListInFile.get(1);

        double expected = 41.149935;
        double result = porto.getLocation().getLatitude();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorListNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGA().getSensorList();

        int expected = 2;
        int result = sensorList.size();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGA().getSensorList();
        Sensor sensor = sensorList.get(1);

        String expected = "TT1236A";
        String result = sensor.getId();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorDateNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGA().getSensorList();
        Sensor sensor = sensorList.get(1);

        GregorianCalendar expected = new GregorianCalendar(2017,Calendar.NOVEMBER,16);
        Calendar result = sensor.getStartDate();
        assertEquals(expected, result);
    }
}