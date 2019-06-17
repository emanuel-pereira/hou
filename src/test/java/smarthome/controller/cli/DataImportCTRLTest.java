package smarthome.controller.cli;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xml.sax.SAXException;
import smarthome.model.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static smarthome.model.House.getHouseRoomList;
import static smarthome.model.TypeGAList.addTypeGA;
import static smarthome.model.TypeGAList.newTypeGA;

class DataImportCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);
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
    @DisplayName("Ensure that GAList has 2 GAs after executing loadJSON method")
    void loadGeoAreas() throws ParserConfigurationException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        addTypeGA(new TypeGA("city"));
        addTypeGA(new TypeGA("urban area"));
        String filepath = "resources_tests/DataSet_sprint05_GA.json";
        Path path = Paths.get(filepath);
        try {
            ctrl.importGeoAreasFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
        }
        int expected = 2;
        int result = ctrl.getGaListInFileSize(path);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that GAList has 0 GAs after executing loadJSON method when json file is not found in specified path")
    void loadGeoAreasFileNotFound() throws ParserConfigurationException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources_tests/JsonFile1.json";
        try {
            Path path = Paths.get(filepath);
            ctrl.importGeoAreasFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
            e.getMessage();
        }

        int expected = 0;
        int result = gaList.size();
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Get total number of sensors in file")
    void getAllSensorsInFile() throws ParserConfigurationException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources_tests/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);

        int expected = 4;
        int result = ctrl.getAllSensorsInFileSize(path);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add the GeoAreas and respective Sensors to the GaList/Repo")
    void importGeoAreasFromFile() throws ParserConfigurationException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);

        addTypeGA(new TypeGA("city"));
        addTypeGA(new TypeGA("urban area"));
        String filepath = "resources_tests/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);

        int expected = 2;
        int result = ctrl.getImportedGaListSize(path);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Number of GeoAreas that were not added - either there was an equal id or the TypeGA did not previously exist")
    void failToAdd() throws ParserConfigurationException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources_tests/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);
        ctrl.importGeoAreasFromFile(path);

        int expected = 2;
        int result = ctrl.failedToAdd();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("No GeoAreas failed to add")
    void failToAddIsZero() throws ParserConfigurationException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        addTypeGA(new TypeGA("city"));
        addTypeGA(new TypeGA("urban area"));
        String filepath = "resources_tests/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);


        int expected = 0;
        int result = ctrl.failedToAdd();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Make sure the number of GeoAreas that failed to add were is not stacking from previous US runs")
    void failToAddDoesNotStack() throws ParserConfigurationException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources_tests/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);
        ctrl.importGeoAreasFromFile(path);

        int expected = 2;
        int result = ctrl.failedToAdd();

        assertEquals(expected, result);
    }



    @Test
    void importReadingZero() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, ParserConfigurationException, SAXException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources_tests/DataSet_sprint05_SD.json";

        Path path = Paths.get(filepath);
        ctrl.importReadingsFromFile(path, gaList);

        int expected = 0;
        int result = gaList.getAllReadings().size();

        assertEquals(expected, result);
    }

    @Test
    void importReading() throws java.text.ParseException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, ParserConfigurationException, SAXException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl1 = new DataImportCTRL(gaList);
        addTypeGA(new TypeGA("city"));
        addTypeGA(new TypeGA("urban area"));
        String filepath1 = "resources_tests/DataSet_sprint05_GA.json";
        Path path1 = Paths.get(filepath1);
        ctrl1.importGeoAreasFromFile(path1);


        DataImportCTRL ctrl2 = new DataImportCTRL(gaList);
        String filepath2 = "resources_tests/DataSet_sprint05_SD.json";
        Path path2 = Paths.get(filepath2);
        ctrl2.importReadingsFromFile(path2, gaList);

        int expected = 61;
        int result = gaList.getAllReadings().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Count all the sensors of the file")
    void getSizeOfSensorsFile() throws IllegalAccessException, ParseException, InstantiationException, java.text.ParseException, ClassNotFoundException, IOException {
        RoomList roomList = getHouseRoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));
        Room room1 = new Room("R1", "B405", 3, 2, 3, 1);
        Room room2 = new Room("R2", "B106", 3, 2, 3, 1);
        Room room3 = new Room("R3", "B107", 3, 2, 3, 1);
        Room room4 = new Room("R5", "B109", 3, 2, 3, 1);
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        roomList.addRoom(room3);
        roomList.addRoom(room4);
        String filepath = "resources_tests/fakeImportFilesForTests/DataSet_sprint06_HouseSensors-DifferentSensors.json";
        Path path = Paths.get(filepath);
        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);

        int result = ctrl.getSizeOfSensorsFile(path);

        int expected = 4 ;

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure that the number of invalid readings is 4")
    void getNrOfInvalidReadings() throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        SensorList kitchenSL = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        SensorType temperature = new SensorType("temperature");
        InternalSensor tSensor0 = new InternalSensor("T0000", "Temp A", s0StDate,  temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        InternalSensor tSensor1 = new InternalSensor("TT3000", "Temp B", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        InternalSensor tSensor2 = new InternalSensor("TT3000", "Temp C", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        InternalSensor tSensor3 = new InternalSensor("TT3000", "Temp D", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        InternalSensor brSensor = new InternalSensor("TT1000", "Temp E", s1StDate, temperature, "C", new ReadingList());
        bedroomSL.addSensor(brSensor);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);
        DataImportCTRL ctrl = new DataImportCTRL(roomList);
        String filepath1 = "resources_tests/fakeImportFilesForTests/RoomSensorsReadings01.json";
        Path path = Paths.get(filepath1);
        ctrl.importReadingsFromFile(path, roomList);

        int expectedInvalidReadings = 4;
        int resultingInvalidReadings = ctrl.getNrOfInvalidReadings();
        assertEquals(expectedInvalidReadings, resultingInvalidReadings);
    }


    @Test
    @DisplayName("Ensure that the number of imported readings is 2")
    void getNrOfImportedReadings() throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        SensorList kitchenSL = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        SensorType temperature = new SensorType("temperature");
        InternalSensor tSensor0 = new InternalSensor("T0000", "Temp A", s0StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        InternalSensor tSensor1 = new InternalSensor("TT3000", "Temp B", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        InternalSensor tSensor2 = new InternalSensor("TT3000", "Temp C", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new InternalSensor("TT3000", "Temp D", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Sensor brSensor = new InternalSensor("TT1000", "Temp E", s1StDate, temperature, "C", new ReadingList());
        bedroomSL.addSensor(brSensor);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);
        DataImportCTRL ctrl = new DataImportCTRL(roomList);
        String filepath1 = "resources_tests/fakeImportFilesForTests/RoomSensorsReadings01.json";
        Path path = Paths.get(filepath1);
        ctrl.importReadingsFromFile(path, roomList);

        int expected = 2;
        int result = ctrl.getNrOfImportedReadings();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the number of imported readings is 0")
    void getNrOfImportedReadingsIsZeroInEmptyFile() throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        SensorList kitchenSL = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new InternalSensor("T0000", "Temp A", s0StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Sensor tSensor1 = new InternalSensor("TT3000", "Temp B", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new InternalSensor("TT3000", "Temp C", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new InternalSensor("TT3000", "Temp D", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Sensor brSensor = new InternalSensor("TT1000", "Temp E", s1StDate, temperature, "C", new ReadingList());
        bedroomSL.addSensor(brSensor);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);
        DataImportCTRL ctrl = new DataImportCTRL(roomList);
        String filepath1 = "resources_tests/fakeImportFilesForTests/RoomSensorsReadings02.json";
        Path path = Paths.get(filepath1);
        ctrl.importReadingsFromFile(path, roomList);

        int expected = 0;
        int result = ctrl.getNrOfImportedReadings();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the number of invalid readings is 0 in empty file")
    void getNrOfInvalidReadingsIsZeroInEmptyFile() throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        SensorList kitchenSL = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new InternalSensor("T0000", "Temp A", s0StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new InternalSensor("TT3000", "Temp B", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new InternalSensor("TT3000", "Temp C", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new InternalSensor("TT3000", "Temp D", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Sensor brSensor = new InternalSensor("TT1000", "Temp E", s1StDate, temperature, "C", new ReadingList());
        bedroomSL.addSensor(brSensor);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);
        DataImportCTRL ctrl = new DataImportCTRL(roomList);
        String filepath1 = "resources_tests/fakeImportFilesForTests/RoomSensorsReadings02.json";
        Path path = Paths.get(filepath1);
        ctrl.importReadingsFromFile(path, roomList);

        int expected = 0;
        int result = ctrl.getNrOfImportedReadings();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the number of imported readings is 0 for invalid file format")
    void getNrOfImportedReadingsIsZeroForInvalidFormatFile() throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        SensorList kitchenSL = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new InternalSensor("T0000", "Temp A", s0StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Sensor tSensor1 = new InternalSensor("TT3000", "Temp B", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new InternalSensor("TT3000", "Temp C", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new InternalSensor("TT3000", "Temp D", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Sensor brSensor = new InternalSensor("TT1000", "Temp E", s1StDate, temperature, "C", new ReadingList());
        bedroomSL.addSensor(brSensor);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);
        DataImportCTRL ctrl = new DataImportCTRL(roomList);
        String filepath1 = "resources_tests/fakeImportFilesForTests/RoomSensorsReadings03.json";
        Path path = Paths.get(filepath1);
        ctrl.importReadingsFromFile(path, roomList);

        int expected = 0;
        int result = ctrl.getNrOfImportedReadings();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the number of imported readings is 0 for invalid file format")
    void getNrOfInvalidReadingsIsZeroForInvalidFormatFile() throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        SensorList kitchenSL = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new InternalSensor("T0000", "Temp A", s0StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Sensor tSensor1 = new InternalSensor("TT3000", "Temp B", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new InternalSensor("TT3000", "Temp C", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new InternalSensor("TT3000", "Temp D", s1StDate, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Sensor brSensor = new InternalSensor("TT1000", "Temp E", s1StDate, temperature, "C", new ReadingList());
        bedroomSL.addSensor(brSensor);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);
        DataImportCTRL ctrl = new DataImportCTRL(roomList);
        String filepath1 = "resources_tests/fakeImportFilesForTests/RoomSensorsReadings03.json";
        Path path = Paths.get(filepath1);
        ctrl.importReadingsFromFile(path, roomList);

        int expected = 0;
        int result = ctrl.getNrOfInvalidReadings();
        assertEquals(expected, result);


    }

    @Test
    void checkRoomListSizeIsZero() {

        RoomList roomList = new RoomList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(roomList);

        int expected = 0;
        int result = dataImportCTRL.roomListSize();

        assertEquals(expected, result);

    }

    @Test
    void checkRoomListSizeIsTwo() {

        RoomList roomList = new RoomList();
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);
        DataImportCTRL dataImportCTRL = new DataImportCTRL(roomList);

        int expected = 2;
        int result = dataImportCTRL.roomListSize();

        assertEquals(expected, result);

    }

    @Test
    void importHouseSensors() throws IllegalAccessException, ParseException, InstantiationException, java.text.ParseException, ClassNotFoundException, IOException {
        RoomList roomList = getHouseRoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));
        Room room1 = new Room("B405", "B405", 3, 2, 3, 1);
        Room room2 = new Room("B106", "B106", 3, 2, 3, 1);
        Room room3 = new Room("B107", "B107", 3, 2, 3, 1);
        Room room4 = new Room("B109", "B109", 3, 2, 3, 1);
        Room room5 = new Room("B110", "B110", 1, 1, 1, 1);
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        roomList.addRoom(room3);
        roomList.addRoom(room4);
        roomList.addRoom(room5);
        String filepath = "resources_tests/fakeImportFilesForTests/DataSet_sprint06_HouseSensors-AllSensorsSameRoom.json";
        Path path = Paths.get(filepath);
        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);
        ctrl.importHouseSensors(path);

        int result = roomList.getRoomList().get(0).getSensorListInRoom().getSensorList().size();

        assertEquals(4, result);
    }

    @Test
    void checkIfRepeatedSensorsAreNotImported() throws IllegalAccessException, ParseException, InstantiationException, java.text.ParseException, ClassNotFoundException, IOException {
        RoomList roomList = getHouseRoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));
        Room room1 = new Room("B405", "B405", 3, 2, 3, 1);
        Room room2 = new Room("B106", "B106", 3, 2, 3, 1);
        Room room3 = new Room("B107", "B107", 3, 2, 3, 1);
        Room room4 = new Room("B109", "B109", 3, 2, 3, 1);
        Room room5 = new Room("B110", "B110", 1, 1, 1, 1);
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        roomList.addRoom(room3);
        roomList.addRoom(room4);
        roomList.addRoom(room5);
        String filepath = "resources_tests/fakeImportFilesForTests/DataSet_sprint06_HouseSensors-AllSensorsSameID.json";
        Path path = Paths.get(filepath);
        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);
        ctrl.importHouseSensors(path);

        int result = roomList.getRoomList().get(0).getSensorListInRoom().getSensorList().size();

        assertEquals(1, result);
    }

    /*
    @Test
    @DisplayName("Count the sensors that are correct and incorrectly added ")
    void getSizeOfSensorsAddandNotAdded() throws IllegalAccessException, ParseException, InstantiationException, java.text.ParseException, ClassNotFoundException, IOException {
        RoomList roomList = getHouseRoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));
        Room room1 = new Room("R1", "B405", 3, 2, 3, 1);
        Room room2 = new Room("R2", "B106", 3, 2, 3, 1);
        Room room3 = new Room("R3", "B107", 3, 2, 3, 1);
        Room room4 = new Room("R5", "B109", 3, 2, 3, 1);
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        roomList.addRoom(room3);
        roomList.addRoom(room4);
        String filepath = "resources_tests/fakeImportFilesForTests/DataSet_sprint06_HouseSensors-DifferentSensors.json";
        Path path = Paths.get(filepath);
        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);

        int[] result = ctrl.importHouseSensors(path);

        int[] expected = new int[]{3, 1};

        assertArrayEquals(expected, result);
    } */

    @Test
    @DisplayName("Check the size of the room list")
    void roomListSize() {
        RoomList roomList = new RoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));

        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);

        int result = ctrl.roomListSize();

        assertEquals(0, result);
    }

    @Test
    @DisplayName("Check the size of the sensor type list")
    void sensorTypeListSize() {


        RoomList roomList = getHouseRoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));

        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);

        int result = ctrl.sensorTypeListSize();

        assertEquals(1, result);
    }


    @Test
    void getGaListInFileSize() throws ParserConfigurationException, IllegalAccessException, ParseException, IOException, InstantiationException, java.text.ParseException, ClassNotFoundException {

        GAList gaList = new GAList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(gaList);
        String filepath1 = "resources_tests/DataSet_sprint05_GA.json";
        Path path1 = Paths.get(filepath1);
        dataImportCTRL.getImportedGaListSize(path1);

        int expected = 0;
        int result = dataImportCTRL.failedToAdd();

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get number of GeoAreas that were successfully added to GAList/repo")
    void getImportedGaListSize() throws ParserConfigurationException, IllegalAccessException, ParseException, IOException, InstantiationException, java.text.ParseException, ClassNotFoundException {

        GAList gaList = new GAList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(gaList);
        String filepath1 = "resources_tests/DataSet_sprint05_GA.json";

        addTypeGA(newTypeGA("city"));
        addTypeGA(newTypeGA("urban area"));

        Path path1 = Paths.get(filepath1);
        dataImportCTRL.importGeoAreasFromFile(path1);

        int expected = 2;
        int result = dataImportCTRL.getImportedGaListSize(path1);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get zero GeoAreas that were successfully added to GAList/repo")
    void getImportedGaListSizeZero() throws ParserConfigurationException,IllegalAccessException, ParseException, IOException, InstantiationException, java.text.ParseException, ClassNotFoundException {
        GAList gaList = new GAList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(gaList);
        String filepath1 = "resources_tests/DataSet_sprint05_GA.json";

        Path path1 = Paths.get(filepath1);
        dataImportCTRL.importGeoAreasFromFile(path1);
        dataImportCTRL.importGeoAreasFromFile(path1);


        int expected = 0;
        int result = dataImportCTRL.getImportedGaListSize(path1);

        assertEquals(expected, result);
    }


    @Test
    void getSizeSensorListInHouseRoomsIsEmpty() {

        RoomList roomList = new RoomList();
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);

        DataImportCTRL dataImportCTRL = new DataImportCTRL(roomList);

        int expected = 0;

        int result = dataImportCTRL.nrOfSensorsInAllRooms();

        assertEquals(expected, result);
    }

    @Test
    void getSizeSensorListInHouseRoomsHasTwoSensors() {

        RoomList roomList = new RoomList();
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        roomList.addRoom(kitchen);
        roomList.addRoom(bedroom);

        SensorList kSensorList = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new InternalSensor("T0000", "Temp A", s0StDate, temperature, "C", new ReadingList());
        kSensorList.addSensor(tSensor0);
        SensorList bSensorList = bedroom.getSensorListInRoom();
        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Sensor tSensor1 = new InternalSensor("TT3000", "Temp B", s1StDate, temperature, "C", new ReadingList());

        bSensorList.addSensor(tSensor1);

        DataImportCTRL dataImportCTRL = new DataImportCTRL(roomList);

        int expected = 2;

        int result = dataImportCTRL.nrOfSensorsInAllRooms();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Number Zero of GeoArea's Types")
    void getTypeGAListSizeTest(){
        GAList gaList = new GAList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(gaList);

        addTypeGA(newTypeGA("city"));
        addTypeGA(newTypeGA("urban area"));


        int expected = 2;
        int result = dataImportCTRL.typeGAListSize();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Get Number Zero of GeoArea's Types")
    void getTypeGAListSizeZeroTest(){
        GAList gaList = new GAList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(gaList);

        int expected = 0;
        int result = dataImportCTRL.typeGAListSize();

        assertEquals(expected,result);
    }
}
