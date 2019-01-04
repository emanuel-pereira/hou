package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {


    @Test
    @DisplayName("Tests if two different instances of house are the same")
    public void differentHouseInstancesAreEqual() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("city");
        GeographicalArea g1 = new GeographicalArea("Porto", t1, l1);

        House house1 = new House(a1, g1);

        House house2 = new House(a1, g1);

        //Assert
        assertEquals(house1.hashCode(), house2.hashCode());
        assertTrue(house2.equals(house1));
    }


    @Test
    @DisplayName("Tests if two different instances of house are different")
    public void differentHouseInstancesAreNotEqual() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "34", "3380-45", l1);
        TypeGA t1 = new TypeGA("city");
        GeographicalArea g1 = new GeographicalArea("Porto", t1, l1);


        Address a2 = new Address("Rua Júlio Dinis", "34", "3380-45", l1);

        House house1 = new House(a1, g1);
        House house2 = new House(a2, g1);

        //Assert
        assertNotEquals(house1.hashCode(), house2.hashCode());
        assertFalse(house1.equals(house2));
    }

    @Test
    @DisplayName("Tests if two different objects are different")
    public void differentObjectsAreNotEqual() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House house1 = new House(a1, g1);

        //Assert
        assertFalse(house1.equals(t1));
    }


    //RoomList

    /**
     * Check if a new room is created and confirm that the get methods are working
     */
    @Test
    void newRoom() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);


        House list = new House(a1, g1);
        Room room = list.newRoom("bedroom", 1, 2, 2.5,2);

        assertEquals("bedroom", room.getName());
        assertEquals(1, room.getFloor());
        assertEquals(5, room.getArea().getOccupationArea());
    }


    /**
     * Check if a room is create and added to the RoomList
     */
    @Test
    void addOneRoom() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House list = new House(a1, g1);
        Room room = list.newRoom("bedroom", 1, 2, 2.5,2);

        list.addRoom(room);
        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = list.getRoomList();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if two rooms are create and added to the RoomList
     */
    @Test
    void addTwoRooms() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House list = new House(a1, g1);
        Room room = list.newRoom("bedroom", 1, 2, 2.5, 2);
        Room room1 = list.newRoom("garden", 0, 2.5, 3, 2);
        assertEquals(0, list.getRoomList().size());

        list.addRoom(room);
        assertEquals(1, list.getRoomList().size());

        list.addRoom(room1);
        assertEquals(2, list.getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room, room1);
        List<Room> result = list.getRoomList();
        assertEquals(expectedResult, result);
    }

    /**
     * Try to add two rooms but one is empty so only the correct one is added
     */
    @Test
    void addOneRoomEmptyName() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House list = new House(a1, g1);
        Room room = list.newRoom("bedroom", 1, 2, 2.5,2);
        Room room1 = list.newRoom("  ", 0, 2.5, 3,2);

        assertEquals(0, list.getRoomList().size());

        list.addRoom(room);
        assertEquals(1, list.getRoomList().size());

        list.addRoom(room1);
        assertEquals(1, list.getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = list.getRoomList();
        assertEquals(expectedResult, result);
    }


    /**
     * Don't validate room name if empty and return false
     */
    @Test
    void nameNotValid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House list = new House(a1, g1);
        Room room = list.newRoom("bedroom", 1, 2, 2.5,2);
        Room room1 = list.newRoom("  ", 0, 2.5, 3,2);

        assertEquals(0, list.getRoomList().size());

        list.addRoom(room);
        assertEquals(1, list.getRoomList().size());

        list.addRoom(room1);
        assertEquals(1, list.getRoomList().size());

        boolean expectedResult = false;
        String name = " ";

        boolean result = room.validateName(name);
        assertEquals(expectedResult, result);
    }

    /**
     * Validate correct room name and return true and also validate empty room name and return false
     */
    @Test
    void addOneGetTrueAddAnotherGetFalse() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House list = new House(a1, g1);
        Room room = list.newRoom("bedroom", 1, 2, 2.5, 1.7);
        Room room1 = list.newRoom("  ", 0, 2.5, 3, 2);

        assertEquals(0, list.getRoomList().size());

        boolean result = list.addRoom(room);
        assertEquals(1, list.getRoomList().size());
        assertTrue(result);

        boolean result1 = list.addRoom(room1);
        assertEquals(1, list.getRoomList().size());
        assertFalse(result1);

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result2 = list.getRoomList();
        assertEquals(expectedResult, result2);
    }


    @Test
    @DisplayName("Add a new house grid to the house grid list of a house and check that the " +
            "same house grid object cannot be added twice")
    void newHouseGrid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House house = new House(a1, g1);
        HouseGrid h1 = house.newHouseGrid(5,"main grid");

        house.addHouseGrid(h1);
        assertEquals(1, house.getHouseGridList().size());
        //Ensure the same house grid cannot be added twice
        house.addHouseGrid(h1);
        assertEquals(1, house.getHouseGridList().size());
        //Ensure a house grid with 0 inputContractedPower cannot be added to the list
        HouseGrid h2 = house.newHouseGrid(0,"main grid");
        house.addHouseGrid(h2);
        assertEquals(1, house.getHouseGridList().size());
    }

    @Test
    @DisplayName("Ensure a room is removed from the list of rooms of a house ")
    void removeRoom() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);

        House house = new House(a1, g1);
        Room room1 = house.newRoom("bedroom", 1, 2, 2.5, 2);
        Room room2 = house.newRoom("kitchen", 1, 4, 5, 2);

        house.addRoom(room1);
        house.addRoom(room2);
        assertEquals(2, house.getRoomList().size());
        house.removeRoom(room2);
        assertEquals(1, house.getRoomList().size());
    }

    @Test
    @DisplayName("Ensure a room not contained in the list of rooms of a house" +
            "cannot be removed")
    void removeRoomReturnsFalse() {

        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("Lisboa", t1, l1);


        House house = new House(a1, g1);
        Room room1 = house.newRoom("bedroom", 1, 2, 2.5, 2);
        house.addRoom(room1);
        Room room2 = house.newRoom("kitchen", 1, 4, 5, 2);

        assertEquals(false, house.removeRoom(room2));
    }


}