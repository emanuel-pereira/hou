package smarthome.model.readers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.model.*;
import smarthome.repository.Repositories;

import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest

class XMLGeoAreaTest {

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
        Repositories.getSensorTypeRepository().deleteAll();
        Repositories.getSensorTypeRepository().save(new SensorType("temperature"));
        Repositories.getSensorTypeRepository().save(new SensorType("rainfall"));
    }

    @Test
    void importData() throws ParserConfigurationException {

        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        int result = xmlReader.loadData(path).size();

        assertEquals(2, result);

    }

    @Test
    void importDataFailNoGAs() throws ParserConfigurationException {

        Path path = Paths.get("resources_tests/fakeImportFilesForTests/DataSet_sprint05_GA_FAKE_01.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        int result = xmlReader.loadData(path).size();

        assertEquals(0, result);

    }

    @Test
    void importDataGaNodeLengthTest() throws ParserConfigurationException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        int expected = 3;
        int result = gaList.size();

        assertNotEquals(expected, result);
    }

    @Test
    void checkGAImport() throws ParserConfigurationException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(0);

        String expected = "urban area";
        String result = ga.getName();

        assertEquals(expected, result);
    }

    @Test
    void checkOccupationAreaImport() throws ParserConfigurationException {
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
    void checkLocationImport() throws ParserConfigurationException {
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
    void checkGASensorsImport() throws ParserConfigurationException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(1);
        List<Sensor> sensorList = ga.getSensorListInGa().getSensorList();

        int expected = 2;
        int result = sensorList.size();

        assertEquals(expected, result);
    }

    @Test
    void checkGASensorTimeImport() throws ParserConfigurationException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.loadData(path);

        GeographicalArea ga = gaList.get(1);
        List<Sensor> sensorList = ga.getSensorListInGa().getSensorList();
        Sensor sensor = sensorList.get(1);

        GregorianCalendar expected = new GregorianCalendar(2017, Calendar.NOVEMBER,16);
        Calendar result = sensor.getSensorBehavior().getStartDate();
        assertEquals(expected, result);
    }
}