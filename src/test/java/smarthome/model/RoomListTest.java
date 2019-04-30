package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomListTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);


    @Test
    @DisplayName("Tests if a new room is created")
    void createNewRoom() {
        //Arrange
        RoomList roomList = new RoomList();

        //Act
        Room r1 = new Room("R01", "BedRoom", 2, 3, 4, 1);
        roomList.addRoom(r1);

        //Assert
        assertEquals("BedRoom", roomList.getRoomList().get(0).getMeteredDesignation());
    }

    @Test
    @DisplayName("Tests if a new room is added to the Room list")
    void addRoomToList() {
        //Arrange
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);

        //Act
        assertTrue(roomList.addRoom(r1));
        List<Room> expectedResult = Arrays.asList(r1);
        List<Room> result = roomList.getRoomList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @DisplayName("Tests if a Room is not added to the list if it is repeated")
    @Test
    public void notAddRepeatedRoom() {
        //Arrange
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "Hall", 0, 1, 1, 3);
        Room r2 = new Room("R02", "Garage", 1, 3, 3, 4);

        //Act
        assertEquals(0, roomList.getRoomList().size());
        assertTrue(roomList.addRoom(r1));
        assertEquals(1, roomList.getRoomList().size());
        assertFalse(roomList.addRoom(r1));

        List<Room> expectedResult = Arrays.asList(r1);
        List<Room> result = roomList.getRoomList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIffRoomNameExists() {
        RoomList list = new RoomList();
        Room kitchen = list.createNewRoom("R01", "kitchen", 1, 3, 3.5, 2);
        list.addRoom(kitchen);

        boolean result = list.checkIfRoomNameExists("kitchen");
        assertTrue(result);
    }

    @Test
    void checkIfRoomNameNotExists() {

        RoomList list = new RoomList();
        Room kitchen = list.createNewRoom("R01", "kitchen", 1, 3, 3.5, 2);
        list.addRoom(kitchen);

        boolean result = list.checkIfRoomNameExists("bedroom");
        assertFalse(result);
    }


    @Test
    @DisplayName("Tests if a room is removed from the Room list")
    void removeRoomFromRoomList() {
        //Arrange
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        //Act
        roomList.addRoom(r1);
        roomList.addRoom(r2);

        assertTrue(roomList.removeRoom(r1));

        List<Room> result = roomList.getRoomList();
        List<Room> expectedResult = Arrays.asList(r2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Tests if it allows to remove a room from the Room list, that was not preciously added")
    void removeRoomFromRoomListFalse() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);

        assertFalse(roomList.removeRoom(r2));
    }

    @Test
    @DisplayName("Get a Room in the Room List by its index")
    void getRoomFromRoomListByIndex() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        Room expected = r2;
        Room result = roomList.get(1);

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Tests if two sensor designations are different")
    public void checkIfRoomDesignationAreDifferent() {
        Room r1 = new Room("R01", "A", 1, 3, 3, 1);
        Room r2 = new Room("R02", "B", 2, 3, 4, 5);

        boolean result;

        result = r1.equals(r2);

        assertNotEquals(r1.hashCode(), r2.hashCode());
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that method getRoomListSize() of RoomList class returns 2")
    void size() {
        RoomList roomList = new RoomList();
        Room livingRoom = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room garage = new Room("R02", "Garage", 0, 1, 3, 1);
        roomList.addRoom(livingRoom);
        roomList.addRoom(garage);
        int expected = 2;
        int result = roomList.getRoomListSize();
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get a Room in the Room List by its index")
    void showRoomListInString() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        String expected = "1 - R01, LivingRoom\n" +
                "2 - R02, Garage\n";
        String result = roomList.showRoomListInString();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("add Device To Room and List All Devices From all Rooms by type")
    void addDeviceToRoom() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        assertTrue(roomList.addDeviceToRoom(d1, 1));
        assertTrue(roomList.addDeviceToRoom(d2, 1));
        assertTrue(roomList.addDeviceToRoom(d3, 1));
        assertTrue(roomList.addDeviceToRoom(d4, 2));
        assertTrue(roomList.addDeviceToRoom(d5, 2));

        List<Device> expected = Arrays.asList(d2, d5);
        List<Device> result = roomList.getDevicesInAllRoomsByType("Tv");

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Remove Device From Room and List All Devices From all Rooms by type")
    void removeDeviceFromRoom() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        assertTrue(roomList.addDeviceToRoom(d1, 1));
        assertTrue(roomList.addDeviceToRoom(d2, 1));
        assertTrue(roomList.addDeviceToRoom(d3, 1));
        assertTrue(roomList.addDeviceToRoom(d4, 2));
        assertTrue(roomList.addDeviceToRoom(d5, 2));

        assertTrue(roomList.removeDeviceFromRoom(d2, 1));

        List<Device> expected = Arrays.asList(d5);
        List<Device> result = roomList.getDevicesInAllRoomsByType("Tv");

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("See in which room the device is")
    void getDeviceLocation() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        assertTrue(roomList.addDeviceToRoom(d1, 1));
        assertTrue(roomList.addDeviceToRoom(d2, 1));
        assertTrue(roomList.addDeviceToRoom(d3, 1));
        assertTrue(roomList.addDeviceToRoom(d4, 2));
        assertTrue(roomList.addDeviceToRoom(d5, 2));

        Room expected = r1;
        Room result = roomList.getDeviceLocation(d2);

        assertEquals(expected, result);
    }

    @Test
    void getEnergyConsumptionByDeviceType() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "Kitchen", 0, 6, 3.5, 3);
        Room r2 = new Room("R02", "Garage", 0, 6, 4, 3);
        roomList.addRoom(r1);
        roomList.addRoom(r2);

        DeviceList r1DeviceList = r1.getDeviceList();
        DeviceList r2DeviceList = r2.getDeviceList();

        FridgeType fridgeType = new FridgeType();
        FanType fanType = new FanType();

        Device d1 = fridgeType.createDevice("Frigo", 733);
        Device d2 = fanType.createDevice("Windy", 50);
        Device d3 = fridgeType.createDevice("Mini Bar", 230);

        r1DeviceList.add(d1);
        r2DeviceList.add(d2);
        r2DeviceList.add(d3);

        double expected = 0;
        double result = roomList.getEnergyConsumptionByDeviceType("Fridge");
        assertEquals(expected, result);
    }

    @Test
    void getListOfRoomsFiltredTest() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "Kitchen", 0, 6, 3.5, 3);
        Room r2 = new Room("R02", "Garage", 0, 6, 4, 3);
        Room r3 = new Room("R03", "Room", 0, 6, 4, 3);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);
        GregorianCalendar startDate = new GregorianCalendar(2019, 1, 1);
        SensorType sensorType = new SensorType("temperature");
        SensorType sensorType2 = new SensorType("rainfall");
        ReadingList readingList = new ReadingList();
        GregorianCalendar readingDate = new GregorianCalendar(2019, 11, 31, 21, 30);
        Reading reading = new Reading(23, readingDate, "C");
        readingList.addReading(reading);
        Sensor sensor1 = new Sensor("101", "A", startDate, sensorType, "C", readingList);
        Sensor sensor2 = new Sensor("102", "B", startDate, sensorType2, "C", readingList);
        Sensor sensor3 = new Sensor("103", "C", startDate, sensorType, "C", readingList);

        r1.getSensorListInRoom().addSensor(sensor1);
        r1.getSensorListInRoom().addSensor(sensor2);
        r1.getSensorListInRoom().addSensor(sensor3);
        r3.getSensorListInRoom().addSensor(sensor1);

        int expected = 2;
        int result = roomList.getListOfRoomsFiltred("temperature").size();

        assertEquals(expected, result);
    }
}