package smarthome.model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import smarthome.model.readers.DataImport;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static smarthome.model.House.*;
import static smarthome.model.TypeGAList.getTypeGAListInstance;

class DataImportTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);
    TypeGAList typeGAList = getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instanceHouse = House.class.getDeclaredField("theHouse");
        instanceHouse.setAccessible(true);
        instanceHouse.set(null, null);
        Field instanceTypeGA = TypeGAList.class.getDeclaredField("typeGaList");
        instanceTypeGA.setAccessible(true);
        instanceTypeGA.set(null, null);
    }

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
    void getGAListInFileSize() throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        TypeGAList.addTypeGA(new TypeGA("city"));
        TypeGAList.addTypeGA(new TypeGA("urban area"));
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");

        int expected = 2;
        int result = dataImport.loadGeoAreaFiles(path).size();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Get Ga List size after import")
    void getGAListImportedSize() throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);
        TypeGAList.addTypeGA(new TypeGA("city"));
        TypeGAList.addTypeGA(new TypeGA("urban area"));
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");

        List<GeographicalArea> inFile = dataImport.loadGeoAreaFiles(path);
        dataImport.importFromFileGeoArea(inFile);

        int expected = 2;
        int result = gaList.size();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Get number of not added GAs")
    void getGAListNotAddedSize() throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException, java.text.ParseException {
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

    //@Test
    void getReadingValueAfterImportingTest() throws IllegalAccessException,ClassNotFoundException,InstantiationException,IOException,ParseException, SAXException, ParserConfigurationException {
        GAList gaList = new GAList();
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        gaList.addGA(ga);
        GregorianCalendar startDate = new GregorianCalendar(2018, Calendar.MARCH, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorISEP = new ExternalSensor("TT12346", "SensorISEP", startDate, location, temp, "C", new ReadingList());
        Sensor sensorPorto = new ExternalSensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGa().addSensor(sensorISEP);
        ga.getSensorListInGa().addSensor(sensorPorto);

        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.json");
        dataImport.importReadingsFromFile(path,gaList);

        List<Reading> rList = ga.getSensorListInGa().getSensorList().get(0).getSensorBehavior().getReadingList().getReadingsList();
        double r = rList.get(3).returnValue();
        assertEquals(15.1, r);
    }

    //FIXME
    //@Test
    void checkIfInvalidReadsAreWrittenOnLogger() throws SAXException, ParserConfigurationException, IllegalAccessException,ClassNotFoundException,InstantiationException,IOException,ParseException {
        GAList gaList = new GAList();
        GeographicalArea ga = new GeographicalArea("001", "Porto", "city", new OccupationArea(3, 2), new Location(3, 30, 20));
        gaList.addGA(ga);
        GregorianCalendar startDate = new GregorianCalendar(2020, Calendar.MARCH, 3);
        Location location = new Location(3, 2, 1);
        SensorType temp = new SensorType("Temperature");
        Sensor sensorISEP = new ExternalSensor("TT12346", "SensorISEP", startDate, location, temp, "C", new ReadingList());
        Sensor sensorPorto = new ExternalSensor("TT1236A", "SensorPorto", startDate, location, temp, "C", new ReadingList());
        ga.getSensorListInGa().addSensor(sensorISEP);
        ga.getSensorListInGa().addSensor(sensorPorto);

        DataImport dataImport = new DataImport(gaList);
        Path path = Paths.get("resources_tests/DataSet_sprint05_SD.json");
        dataImport.importReadingsFromFile(path,gaList);

        List<Reading> rList = ga.getSensorListInGa().getSensorList().get(0).getSensorBehavior().getReadingList().getReadingsList();
        int size = rList.size();
        assertEquals(0, size);
    }

    //FIXME
    //@Test
    @DisplayName("Returns an Address Object from the information in the file that can be set as the house address")
    void loadHouseAddressTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException{
       GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);

        dataImport.importHouse();

        String expected = "R. Dr. António Bernardino de Almeida";
        String result = getAddress().getStreet();

        assertEquals(expected,result);
    }

    //FIXME
    //@Test
    @DisplayName("Returns a number of Rooms from the information in the file that can be set as the house's RoomList")
    void loadHouseRoomListTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException{
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);

        dataImport.importHouse();

        int expected = 7;
        int result = getHouseRoomList().getRoomListSize();

        assertEquals(expected,result);
    }

    //FIXME
    //@Test
    @DisplayName("Returns a number of Grids from the information in the file that can be set as the house's HouseGridList")
    void loadHouseGridListTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException{
        GAList gaList = new GAList();
        DataImport dataImport = new DataImport(gaList);

        dataImport.importHouse();

        int expected = 2;
        int result = getGridListInHouse().getSize();

        assertEquals(expected,result);
    }

    @Test
    void checkIfSensorAreImportedTest() throws IllegalAccessException, ParseException, InstantiationException, IOException, java.text.ParseException, ClassNotFoundException {
        RoomList roomList = getHouseRoomList();

        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));

        Room room1 = new Room("B405","B405",3,2,3,1);
        Room room2 = new Room("B106","B106",3,2,3,1);
        Room room3 = new Room("B107","B107",3,2,3,1);
        Room room4 = new Room("B109","B109",3,2,3,1);
        Room room5 = new Room("B109","B109",1,1,1,1);


        roomList.addRoom(room1);
        roomList.addRoom(room2);
        roomList.addRoom(room3);
        roomList.addRoom(room4);
        roomList.addRoom(room5);

        DataImport dataImport = new DataImport(roomList,sensorTypeList);
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseSensors.json");
        dataImport.importHouseSensors(dataImport.loadHouseSensorsFiles(path));

        List<Sensor> sensorList = roomList.getRoomList().get(0).getSensorListInRoom().getSensorList();
        int size = sensorList.size();

        assertEquals(1,size);
    }
}

