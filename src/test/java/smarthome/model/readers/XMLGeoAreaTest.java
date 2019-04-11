package smarthome.model.readers;

import org.junit.jupiter.api.Test;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class XMLGeoAreaTest {

    @Test
    void importData() {

        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        int result = xmlReader.loadData(path).size();

        assertEquals(2, result);

    }

    @Test
    void importDataFailNoGAs() {

        Path path = Paths.get("resources_tests/fakeImportFilesForTests/DataSet_sprint05_GA_FAKE_01.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        int result = xmlReader.loadData(path).size();

        assertEquals(0, result);

    }

    @Test
    void importDataGaNodeLengthTest() {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        int expected = 3;
        int result = gaList.size();

        assertNotEquals(expected, result);
    }

    @Test
    void checkGAImport() {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(0);

        String expected = "urban area";
        String result = ga.getTypeName();

        assertEquals(expected, result);
    }

    @Test
    void checkOccupationAreaImport() {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(0);
        double expected = 0.261;
        double result = ga.getOccupation().getWidth();

        double expected2 = 0.249;
        double result2 = ga.getOccupation().getLength();

        assertEquals(expected, result);
        assertEquals(expected2, result2);
    }

    @Test
    void checkLocationImport() {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(1);

        double expected = 41.149937;
        double result = ga.getLocation().getLatitude();

        double expected2 = -8.610857;
        double result2 = ga.getLocation().getLongitude();

        double expected3 = 118;
        double result3 = ga.getLocation().getAltitude();


        assertEquals(expected, result);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    @Test
    void checkGASensorsImport() {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(1);
        List<Sensor> sensorList = ga.getSensorListInGA().getSensorList();

        int expected = 2;
        int result = sensorList.size();

        assertEquals(expected, result);
    }

    @Test
    void checkGASensorTimeImport() {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(1);
        List<Sensor> sensorList = ga.getSensorListInGA().getSensorList();
        Sensor sensor = sensorList.get(1);

        GregorianCalendar expected = new GregorianCalendar(2017, Calendar.NOVEMBER,16);
        Calendar result = sensor.getStartDate();
        assertEquals(expected, result);
    }
}