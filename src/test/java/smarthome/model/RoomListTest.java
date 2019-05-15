package smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.*;
import smarthome.repository.Repositories;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseRoomList;
import static smarthome.model.TypeGAList.getTypeGAListInstance;


public class RoomListTest {

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
        Field instance1 = House.class.getDeclaredField("theHouse");
        instance1.setAccessible(true);
        instance1.set(null, null);
        Field instance2 = TypeGAList.class.getDeclaredField("typeGaList");
        instance2.setAccessible(true);
        instance2.set(null, null);
    }

    @Test
    @DisplayName("Tests if a new room is created")
    void createNewRoomTest() {
        //Arrange
        RoomList roomList = new RoomList();

        //Act
        Room r1 = new Room("R01", "BedRoom", 2, 3, 4, 1);
        roomList.addRoom(r1);

        //Assert
        assertEquals("BedRoom", roomList.getRoomList().get(0).getMeteredDesignation());
    }

    @Test
    @DisplayName("Tests if a new room is created")
    void createNewRoomNullTest() {
        //Arrange
        RoomList roomList = new RoomList();

        //Act
        Room result = roomList.createNewRoom(" ", " ", 2, 3, 4, 1);


        //Assert
        assertEquals(null, result);
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


    /*@Test
    @DisplayName("Ensure that the room is saved in the repository when it is added, handles nullpointer exception")
    void addRoomToListAndRepoNullPointer(){
        RoomList roomList = new RoomList();
        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);

        roomList.addRoom(bedroom);

        boolean thrown = false;

        try{
            Repositories.getRoomRepository().count();
        }

        catch(NullPointerException e){
            thrown = true;
        }

        assertTrue(thrown);
    }*/


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
    void checkIffRoomIdExists() {
        RoomList list = new RoomList();
        Room kitchen = list.createNewRoom("R01", "kitchen", 1, 3, 3.5, 2);
        list.addRoom(kitchen);

        boolean result = list.checkIfRoomIDExists("R01");
        assertTrue(result);
    }

    @Test
    void checkIffRoomIdNotExists() {
        RoomList list = new RoomList();
        Room kitchen = list.createNewRoom("R01", "kitchen", 1, 3, 3.5, 2);
        list.addRoom(kitchen);

        boolean result = list.checkIfRoomIDExists("R02");
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
    @DisplayName("Get a Room in the Room List by its ID")
    void getRoomFromRoomListByID() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        Room expected = r2;
        Room result = roomList.getRoomById("R02");

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get a Room in the Room List by its ID")
    void getRoomFromRoomListByIDFails() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("R01", "LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("R02", "Garage", 0, 1, 3, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        Room result = roomList.getRoomById(" ");

        assertEquals(null, result);
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
    void getEnergyConsumptionByDeviceTypeTest() {
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
    void getEnergyConsumptionByDeviceTypeNotEquals() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH = ewh.createDevice("EWH DAIKIN1", 15);
        TvType tv = new TvType ();
        Device dTv = tv.createDevice("Samsung TV", 15);
        FridgeType fridge = new FridgeType();
        Device dFridge = fridge.createDevice("Fridge1", 100);
        Device dOtherFridge = fridge.createDevice("Fridge2", 80);

        roomList.addDeviceToRoom (dEWH, 1);
        roomList.addDeviceToRoom (dTv, 1);
        roomList.addDeviceToRoom (dFridge, 1);
        roomList.addDeviceToRoom (dOtherFridge, 1);

        ReadingList activityLog = dEWH.getActivityLog ();
        Reading r2 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading r3 = new Reading (22, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading r4 = new Reading (37, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading r5 = new Reading (31, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading r6 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading r7 = new Reading (22, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading r8 = new Reading (37, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLog.addReading (r2);
        activityLog.addReading (r3);
        activityLog.addReading (r4);
        activityLog.addReading (r5);
        activityLog.addReading (r6);
        activityLog.addReading (r7);
        activityLog.addReading (r8);

        ReadingList activityLogFridge = dFridge.getActivityLog ();
        Reading rA2 = new Reading (20, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading rA3 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading rA4 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading rA5 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading rA6 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading rA7 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading rA8 = new Reading (20, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLogFridge.addReading (rA2);
        activityLogFridge.addReading (rA3);
        activityLogFridge.addReading (rA4);
        activityLogFridge.addReading (rA5);
        activityLogFridge.addReading (rA6);
        activityLogFridge.addReading (rA7);
        activityLogFridge.addReading (rA8);

        ReadingList activityLogOtherFridge = dOtherFridge.getActivityLog ();
        Reading rB2 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading rB3 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading rB4 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading rB5 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading rB6 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading rB7 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading rB8 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLogOtherFridge.addReading (rB2);
        activityLogOtherFridge.addReading (rB3);
        activityLogOtherFridge.addReading (rB4);
        activityLogOtherFridge.addReading (rB5);
        activityLogOtherFridge.addReading (rB6);
        activityLogOtherFridge.addReading (rB7);
        activityLogOtherFridge.addReading (rB8);

        double expected = 55;
        double result = roomList.getEnergyConsumptionByDeviceType("Fridge");
        assertNotEquals(expected, result);
    }

    @Test
    void getEstimatedEnergyConsumptionByDeviceTypeTest() {
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

        double expected = 2.64;
        double result = roomList.getEstimatedEnergyConsumptionByDeviceType("Fridge");
        assertEquals(expected, result);
    }

    @Test
    void getEstimatedEnergyConsumptionByDeviceTypeNotEquals() {
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
        double result = roomList.getEstimatedEnergyConsumptionByDeviceType("Fridge");
        assertNotEquals(expected, result);
    }

    @Test
    void getMeteredDevicesListTest() {
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

        int expected = 3;
        int result = roomList.getMeteredDevicesList().size();
        assertEquals(expected, result);
    }

    @Test
    void getAllSensorsTest(){
        RoomList roomList = new RoomList();

        Room r1 = new Room("R01","bedroom", 1, 2, 3, 2);
        Room r2 = new Room("R02","toilet", 1, 2, 3, 2);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        GregorianCalendar sDate = new GregorianCalendar(2019,05,03);
        SensorType type1 = new SensorType("temperature");
        ReadingList readingList = new ReadingList();

        InternalSensor s1 = new InternalSensor("s1","meteo1",sDate,type1,"C",readingList);
        InternalSensor s2 = new InternalSensor("s2","meteo2",sDate,type1,"C",readingList);
        InternalSensor s3 = new InternalSensor("s3","meteo3",sDate,type1,"C",readingList);
        InternalSensor s4 = new InternalSensor("s4","meteo4",sDate,type1,"C",readingList);

        r1.getSensorListInRoom().addSensor(s1);
        r1.getSensorListInRoom().addSensor(s2);

        r2.getSensorListInRoom().addSensor(s3);
        r2.getSensorListInRoom().addSensor(s4);

        List<Sensor> expected = Arrays.asList(s1,s2,s3,s4);
        List<Sensor> result = roomList.getAllSensors();

        assertEquals(expected,result);
    }


}