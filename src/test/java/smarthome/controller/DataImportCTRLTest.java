package smarthome.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import smarthome.model.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataImportCTRLTest {
    @Test
    @DisplayName("Ensure that GAList has 2 GAs after executing loadJSON method")
    void loadGeoAreas() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/DataSet_sprint05_GA.json";
        try {
            Path path = Paths.get(filepath);
            ctrl.importGeoAreasFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
        }
        int expected = 2;
        int result = gaList.size();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that GAList has 0 GAs after executing loadJSON method when json file is not found in specified path")
    void loadGeoAreasFileNotFound() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/JsonFile1.json";
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
        String filepath = "resources/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);

        int expected = 4;
        int result = ctrl.getAllSensorsInFileSize(path);

        assertEquals(expected, result);
    }

    @Test
    void failToAdd() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/DataSet_sprint05_GA.json";

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
        String filepath = "resources/DataSet_sprint05_GA.json";

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
        String filepath = "resources/DataSet_sprint05_GA.json";

        Path path = Paths.get(filepath);
        ctrl.importGeoAreasFromFile(path);
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
        String filepath = "resources/DataSet_sprint05_SD.json";

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
        String filepath1 = "resources/DataSet_sprint05_GA.json";
        Path path1 = Paths.get(filepath1);
        ctrl1.importGeoAreasFromFile(path1);


        DataImportCTRL ctrl2 = new DataImportCTRL(gaList);
        String filepath2 = "resources/DataSet_sprint05_SD.json";
        Path path2 = Paths.get(filepath2);
        ctrl2.importReadingsFromFile(path2, gaList);

        int expected = 61;
        int result = gaList.getAllReadings().size();

        assertEquals(expected, result);
    }

    @Test
    void importHouseSensors() throws IllegalAccessException, ParseException, InstantiationException, java.text.ParseException, ClassNotFoundException, IOException {
        House house = new House();
        RoomList roomList = house.getRoomList();
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

        int result = house.getRoomList().getRoomList().get(0).getSensorListInRoom().getSensorList().size();

        assertEquals(4, result);
    }

    @Test
    void checkIfRepeatedSensorsAreNotImported() throws IllegalAccessException, ParseException, InstantiationException, java.text.ParseException, ClassNotFoundException, IOException {
        House house = new House();
        RoomList roomList = house.getRoomList();
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

        int result = house.getRoomList().getRoomList().get(0).getSensorListInRoom().getSensorList().size();

        assertEquals(1, result);
    }

    @Test
    @DisplayName("Count the sensors that are correct and incorrectly added ")
    void getSizeOfSensorsAddandNotAdded() throws IllegalAccessException, ParseException, InstantiationException, java.text.ParseException, ClassNotFoundException, IOException {
        House house = new House();
        RoomList roomList = house.getRoomList();
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
        House house = new House();
        RoomList roomList = house.getRoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));

        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);

        int result = ctrl.roomListSize();

        assertEquals(0, result);
    }

    @Test
    @DisplayName("Check the size of the sensor list")
    void sensorTypeListSize() {
        House house = new House();
        RoomList roomList = house.getRoomList();
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(new SensorType("temperature"));

        DataImportCTRL ctrl = new DataImportCTRL(roomList, sensorTypeList);

        int result = ctrl.sensorTypeListSize();

        assertEquals(1, result);
    }



/*
    @Test
    void getGAListDTO() throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException {
        GAList gaList = new GAList();
        DataImportCTRL ctrl = new DataImportCTRL(gaList);
        String filepath = "resources/DataSet_sprint05_GA.json";
        try {
            Path path = Paths.get(filepath);

            ctrl.importGeoAreasFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified file path: " + filepath);
        }
        ctrl.getGAListDTO();
        int expected = 2;
        int result = ctrl.getGAListDTO().size();
        assertEquals(expected, result);
    }
    */
}