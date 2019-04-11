package smarthome.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import smarthome.model.*;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Room;
import smarthome.model.RoomList;

import javax.sql.rowset.CachedRowSet;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static smarthome.model.House.getHouseRoomList;

class DataImportCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    @DisplayName("Ensure that GAList has 2 GAs after executing loadJSON method")
    void loadGeoAreas() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
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
    void loadGeoAreasFileNotFound() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
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
    void getAllSensorsInFile() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
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
    void importGeoAreasFromFile() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources_tests/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);

        int expected = 2;
        int result = ctrl.getImportedGaListSize(path);
        assertEquals(expected, result);
    }

    @Test
    void failToAdd() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
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
    void failToAddIsZero() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources_tests/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);


        int expected = 0;
        int result = ctrl.failedToAdd();

        assertEquals(expected, result);
    }

    @Test
    void failToAddDoesNotStack() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
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
    @DisplayName("Ensure that the number of invalid readings is 4")
    void getNrOfInvalidReadings() throws IllegalAccessException, ParseException, IOException, InstantiationException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Room kitchen = new Room("K1", "Kitchen", 0, 5, 2, 2);
        SensorList kitchenSL = kitchen.getSensorListInRoom();
        GregorianCalendar s0StDate = new GregorianCalendar(2018, 1, 15);
        Location s0Location = new Location(12, 25, 32);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new Sensor("T0000", "Temp A", s0StDate, s0Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new Sensor("TT3000", "Temp B", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new Sensor("TT3000", "Temp C", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new Sensor("TT3000", "Temp D", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Location location = new Location(12, 32, 15);
        Sensor brSensor = new Sensor("TT1000", "Temp E", s1StDate, location, temperature, "C", new ReadingList());
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
        Location s0Location = new Location(12, 25, 32);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new Sensor("T0000", "Temp A", s0StDate, s0Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new Sensor("TT3000", "Temp B", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new Sensor("TT3000", "Temp C", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new Sensor("TT3000", "Temp D", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Location location = new Location(12, 32, 15);
        Sensor brSensor = new Sensor("TT1000", "Temp E", s1StDate, location, temperature, "C", new ReadingList());
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
        Location s0Location = new Location(12, 25, 32);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new Sensor("T0000", "Temp A", s0StDate, s0Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new Sensor("TT3000", "Temp B", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new Sensor("TT3000", "Temp C", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new Sensor("TT3000", "Temp D", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Location location = new Location(12, 32, 15);
        Sensor brSensor = new Sensor("TT1000", "Temp E", s1StDate, location, temperature, "C", new ReadingList());
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
        Location s0Location = new Location(12, 25, 32);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new Sensor("T0000", "Temp A", s0StDate, s0Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new Sensor("TT3000", "Temp B", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new Sensor("TT3000", "Temp C", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new Sensor("TT3000", "Temp D", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Location location = new Location(12, 32, 15);
        Sensor brSensor = new Sensor("TT1000", "Temp E", s1StDate, location, temperature, "C", new ReadingList());
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
        Location s0Location = new Location(12, 25, 32);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new Sensor("T0000", "Temp A", s0StDate, s0Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new Sensor("TT3000", "Temp B", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new Sensor("TT3000", "Temp C", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new Sensor("TT3000", "Temp D", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Location location = new Location(12, 32, 15);
        Sensor brSensor = new Sensor("TT1000", "Temp E", s1StDate, location, temperature, "C", new ReadingList());
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
        Location s0Location = new Location(12, 25, 32);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new Sensor("T0000", "Temp A", s0StDate, s0Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor0);

        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new Sensor("TT3000", "Temp B", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor1);

        Sensor tSensor2 = new Sensor("TT3000", "Temp C", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor2);

        Sensor tSensor3 = new Sensor("TT3000", "Temp D", s1StDate, s1Location, temperature, "C", new ReadingList());
        kitchenSL.addSensor(tSensor3);

        Room bedroom = new Room("B1", "Bedroom", 1, 5, 3, 2);
        SensorList bedroomSL = bedroom.getSensorListInRoom();
        Location location = new Location(12, 32, 15);
        Sensor brSensor = new Sensor("TT1000", "Temp E", s1StDate, location, temperature, "C", new ReadingList());
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
    }

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
    void getGaListInFileSize() throws IllegalAccessException, ParseException, IOException, InstantiationException, java.text.ParseException, ClassNotFoundException {

        GAList gaList = new GAList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(gaList);
        String filepath1 = "resources/DataSet_sprint05_GA.json";
        Path path1 = Paths.get(filepath1);
        dataImportCTRL.getImportedGaListSize(path1);

        int expected = 0;
        int result = dataImportCTRL.failedToAdd();

        assertEquals(expected, result);

    }

    @Test
    void getImportedGaListSize() throws IllegalAccessException, ParseException, IOException, InstantiationException, java.text.ParseException, ClassNotFoundException {

        GAList gaList = new GAList();
        DataImportCTRL dataImportCTRL = new DataImportCTRL(gaList);
        String filepath1 = "resources/DataSet_sprint05_GA.json";

        Path path1 = Paths.get(filepath1);
        dataImportCTRL.importGeoAreasFromFile(path1);


        int expected = 2;
        int result = dataImportCTRL.getGaListInFileSize(path1);

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

        dataImportCTRL.getSizeSensorListInHouseRooms();

        int expected = 0;

        int result = dataImportCTRL.getSizeSensorListInHouseRooms();

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
        Location s0Location = new Location(12, 25, 32);
        SensorType temperature = new SensorType("temperature");
        Sensor tSensor0 = new Sensor("T0000", "Temp A", s0StDate, s0Location, temperature, "C", new ReadingList());
        kSensorList.addSensor(tSensor0);
        SensorList bSensorList = bedroom.getSensorListInRoom();
        GregorianCalendar s1StDate = new GregorianCalendar(2018, 1, 15);
        Location s1Location = new Location(12, 25, 32);
        Sensor tSensor1 = new Sensor("TT3000", "Temp B", s1StDate, s1Location, temperature, "C", new ReadingList());

        bSensorList.addSensor(tSensor1);

        DataImportCTRL dataImportCTRL = new DataImportCTRL(roomList);

        dataImportCTRL.getSizeSensorListInHouseRooms();

        int expected = 2;

        int result = dataImportCTRL.getSizeSensorListInHouseRooms();

        assertEquals(expected, result);
    }
}