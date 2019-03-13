package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomListTest {

    @Test
    @DisplayName("Tests if a new room is created")
    void createNewRoom() {
        //Arrange
        RoomList roomList = new RoomList();

        //Act
        Room r1 = new Room("BedRoom", 2, 3, 4, 1);
        roomList.addRoom(r1);

        //Assert
        assertEquals("BedRoom", roomList.getRoomList().get(0).getName());
    }

    @Test
    @DisplayName("Tests if a new room is added to the Room list")
    void addRoomToList() {
        //Arrange
        RoomList roomList = new RoomList();
        Room r1 = new Room("LivingRoom", 1, 3, 4, 5);

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
        Room r1 = new Room("Hall", 0, 1, 1, 3);
        Room r2 = new Room("Garage", 1, 3, 3, 4);

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
        Address a1 = new Address("Rua Luis Pacheco", "3380-45", "Lisboa", 41, 12.3, 110);

        Location loc = new Location(25, 35, 15);
        OccupationArea oc = new OccupationArea(40, 45);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", oc, loc);

        House house2 = new House(a1, g1);

        RoomList list = house2.getRoomList();
        Room kitchen = list.createNewRoom("kitchen", 1, 3, 3.5, 2);
        list.addRoom(kitchen);

        boolean result = list.checkIfRoomNameExists("kitchen");
        assertTrue(result);
    }

    @Test
    void checkIfRoomNameNotExists() {
        Address a1 = new Address("Rua Luis Pacheco", "3380-45", "Lisboa", 41, 12.3, 110);

        Location loc = new Location(25, 35, 15);
        OccupationArea oc = new OccupationArea(40, 45);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", oc, loc);

        House house2 = new House(a1, g1);

        RoomList list = house2.getRoomList();
        Room kitchen = list.createNewRoom("kitchen", 1, 3, 3.5, 2);
        list.addRoom(kitchen);

        boolean result = list.checkIfRoomNameExists("bedroom");
        assertFalse(result);
    }


    @Test
    @DisplayName("Tests if a room is removed from the Room list")
    void removeRoomFromRoomList() {
        //Arrange
        RoomList roomList = new RoomList();
        Room r1 = new Room("LivingRoom", 1, 3, 4, 5);
        Room r2 = new Room("Garage", 0, 1, 3, 1);

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
    @DisplayName("Tests if two sensor designations are different")
    public void checkIfRoomDesignationAreDifferent() {
        Room r1 = new Room("A", 1, 3, 3, 1);
        Room r2 = new Room("B", 2, 3, 4, 5);

        boolean result;

        result = r1.equals(r2);

        assertNotEquals(r1.hashCode(), r2.hashCode());
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that method getRoomListSize() of RoomList class returns 2")
    void size() {
        RoomList roomList = new RoomList();
        Room livingRoom = new Room("LivingRoom", 1, 3, 4, 5);
        Room garage = new Room("Garage", 0, 1, 3, 1);
        roomList.addRoom(livingRoom);
        roomList.addRoom(garage);
        int expected = 2;
        int result = roomList.getRoomListSize();
        assertEquals(expected, result);

    }

    @Test
    void getMeteredDevicesInHouse() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("Kitchen1", 0, 5, 5, 3);
        DeviceList r1DevLst = r1.getDeviceList();
        DeviceSpecs fridge = new Fridge(25, 50, 25);
        Device dFridge = new Device("LG Fridge", fridge, 15);
        dFridge.setIsMetered(true);
        DeviceSpecs stove = new OtherDevices();
        Device dStove = new Device("XStove", stove, 15);
        r1DevLst.addDevice(dFridge);
        r1DevLst.addDevice(dStove);
        Room r2 = new Room("Kitchen2", 0, 6, 4, 3);
        DeviceList r2DevLst = r2.getDeviceList();
        DeviceSpecs fridge2 = new Fridge(25, 50, 25);
        Device dFridge2 = new Device("LG Fridge", fridge2, 15);
        dFridge2.setIsMetered(true);
        DeviceSpecs stove2 = new OtherDevices();
        Device dStove2 = new Device("XStove", stove2, 15);
        dStove.setIsMetered(false);
        dStove2.setIsMetered(false);
        r2DevLst.addDevice(dFridge2);
        r2DevLst.addDevice(dStove2);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        int expected = 2;
        int result = roomList.getMeteredDevicesLst().size();
        assertEquals(expected, result);


    }

    @Test
    void showMeteredDevicesInStr() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("Kitchen1", 0, 5, 5, 3);
        DeviceList r1DevLst = r1.getDeviceList();
        DeviceSpecs fridge = new Fridge(25, 50, 25);
        Device dFridge = new Device("LG Fridge1", fridge, 15);
        dFridge.setIsMetered(true);
        DeviceSpecs stove = new OtherDevices();
        Device dStove = new Device("XStove", stove, 15);
        r1DevLst.addDevice(dFridge);
        r1DevLst.addDevice(dStove);
        Room r2 = new Room("Kitchen2", 0, 6, 4, 3);
        DeviceList r2DevLst = r2.getDeviceList();
        DeviceSpecs fridge2 = new Fridge(25, 50, 25);
        Device dFridge2 = new Device("LG Fridge2", fridge2, 15);
        dFridge2.setIsMetered(true);
        DeviceSpecs stove2 = new OtherDevices();
        Device dStove2 = new Device("XStove", stove2, 15);
        dStove.setIsMetered(false);
        dStove2.setIsMetered(false);
        r2DevLst.addDevice(dFridge2);
        r2DevLst.addDevice(dStove2);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        String expected = "1 - LG Fridge1\n" +
                "2 - LG Fridge2\n";
        String result = roomList.showMeteredDevicesInStr();
        assertEquals(expected, result);
    }
}