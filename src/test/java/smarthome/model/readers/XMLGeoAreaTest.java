package smarthome.model.readers;

import org.junit.jupiter.api.Test;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLGeoAreaTest {

    @Test
    void importData() {

        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        int result = xmlReader.importData(path).size();

        assertEquals(2, result);

    }

    @Test
    void checkGAImport() {
        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.importData(path);

        GeographicalArea ga = gaList.get(0);

        String expected = "urban area";
        String result = ga.getType();

        assertEquals(expected, result);
    }

    @Test
    void checkOccupationAreaImport() {
        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.importData(path);

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
        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.importData(path);

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
        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.importData(path);

        GeographicalArea ga = gaList.get(1);
        List<Sensor> sensorList = ga.getSensorListInGA().getSensorList();

        int expected = 2;
        int result = sensorList.size();

        assertEquals(expected, result);
    }
}