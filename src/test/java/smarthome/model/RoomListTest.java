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
        Room r1 = new Room("BedRoom",2,3,4,1);
        roomList.addRoom(r1);

        //Assert
        assertEquals("BedRoom", roomList.getRoomList().get(0).getName());
    }

    @Test
    @DisplayName("Tests if a new room is added to the Room list")
    void addRoomToList() {
        //Arrange
        RoomList roomList = new RoomList();
        Room r1 = new Room("LivingRoom",1,3,4,5);

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
        Room r1 = new Room("Hall",0,1,1,3);
        Room r2 = new Room("Garage",1,3,3,4);

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
    @DisplayName("Tests if a room is removed from the Room list")
    void removeRoomFromRoomList() {
        //Arrange
        RoomList roomList = new RoomList();
        Room r1 = new Room("LivingRoom",1,3,4,5);
        Room r2 = new Room("Garage",0,1,3,1);

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
        Room r1 = new Room("A",1,3,3,1);
        Room r2 = new Room("B",2,3,4,5);

        boolean result;

        result = r1.equals(r2);

        assertNotEquals(r1.hashCode(), r2.hashCode());
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that method size() of RoomList class returns 2")
    void size() {
        RoomList roomList = new RoomList();
        Room livingRoom = new Room("LivingRoom",1,3,4,5);
        Room garage = new Room("Garage",0,1,3,1);
        roomList.addRoom(livingRoom);
        roomList.addRoom(garage);
        int expected=2;
        int result= roomList.size();
        assertEquals(expected,result);

    }

    /**
     * Show sum of nominal power in a house grid
     */
    @Test
    public void getCorrectNominalPower() {

        House h = new House();

        HouseGridList hGl = h.getHGListInHouse ();
        HouseGrid hG = hGl.newHouseGrid ("mainGrid",800);
        hGl.addHouseGrid (hG);

        RoomList list = h.getRoomListFromHouse ();

        Room bedroom = list.createNewRoom ("bedroom", 1, 2, 3, 2);
        list.addRoom (bedroom);
        DeviceList dLb = bedroom.getDeviceList ();
        Room kitchen = list.createNewRoom ("kitchen", 0, 1,2,1.5);
        list.addRoom (kitchen);
        DeviceList dLK = kitchen.getDeviceList ();

        Device d1 = dLb.newDeviceWithoutSpecs ("lamp1", bedroom, "lamp", 20);
        Device d2 = dLb.newDeviceWithoutSpecs("lamp2", bedroom, "lamp", 22);

        dLb.addDevice (d1);
        dLb.addDevice (d2);

        Device d3 = dLb.newDeviceWithoutSpecs ("fridge", bedroom, "fridge", 150);
        Device d4 = dLb.newDeviceWithoutSpecs("dishwasher", bedroom, "dishwasher", 100);

        dLK.addDevice (d3);
        dLK.addDevice (d4);

        bedroom.setmHouseGrid (hG);
        kitchen.setmHouseGrid (hG);


        double expectedResult = 292;
        double result = list.getNominalPower (hG);


        assertEquals (expectedResult, result);
    }

}

