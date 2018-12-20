package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    @Test
    @DisplayName("define and check house address, zip code, location and geographical area")
    void checkHouseLocation() {
        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House house1 = new House(a1, ga1);

        //Act
        Address result = house1.getAddress();
        Address expectedResult = a1;

        GeographicalArea result2 = house1.getGA();
        GeographicalArea expectedResult2 = ga1;


        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);


    }


    @Test
    @DisplayName("Tests if two different instances of house are the same")
    public void differentHouseInstancesAreEqual() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House house1 = new House(a1, ga1);


        House house2 = new House(a1, ga1);

        //Assert
        assertEquals(house1.hashCode(), house2.hashCode());
        assertTrue(house2.equals(house1));
    }


    @Test
    @DisplayName("Tests if two different instances of house are different")
    public void differentHouseInstancesAreNotEqual() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        Address a2 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);

        House house1 = new House(a1, ga1);
        House house2 = new House(a2, ga1);


        //Assert
        assertNotEquals(house1.hashCode(), house2.hashCode());
        assertFalse(house1.equals(house2));
    }

    @Test
    @DisplayName("Tests if two different objects are different")
    public void differentObjectsAreNotEqual() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House house1 = new House(a1, ga1);

        //Assert
        assertFalse(house1.equals(type1));
    }


    //RoomList

    @Test
    void newRoom() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House list = new House(a1, ga1);
        Room room = list.newRoom ("bedroom", 1, 2, 2.5);

        assertEquals ("bedroom", room.getName ());
        assertEquals (1, room.getFloor ());
        assertEquals (5, room.getArea ().getOccupationArea () );
    }


    @Test
    void addOneRoom() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House list = new House(a1, ga1);
        Room room = list.newRoom ("bedroom", 1, 2, 2.5);

        list.addRoom (room);
        List<Room> expectedResult = Arrays.asList (room);
        List<Room> result = list.getRoomList ();

        assertEquals (expectedResult, result);
    }



    @Test
    void addTwoRooms() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House list = new House(a1, ga1);
        Room room = list.newRoom ("bedroom", 1, 2, 2.5);
        Room room1 =list.newRoom ("garden", 0, 2.5,3);
        assertEquals (0, list.getRoomList ().size ());

        list.addRoom (room);
        assertEquals (1, list.getRoomList ().size ());

        list.addRoom (room1);
        assertEquals (2, list.getRoomList ().size ());

        List<Room> expectedResult = Arrays.asList (room,room1);
        List<Room> result = list.getRoomList ();
        assertEquals (expectedResult, result);
    }


    @Test
    void addOneRoomEmptyName() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House list = new House(a1, ga1);
        Room room = list.newRoom ("bedroom", 1, 2, 2.5);
        Room room1 = list.newRoom ("  ", 0, 2.5,3);

        assertEquals (0, list.getRoomList ().size ());

        list.addRoom (room);
        assertEquals (1, list.getRoomList ().size ());

        list.addRoom (room1);
        assertEquals (1, list.getRoomList ().size ());


        List<Room> expectedResult = Arrays.asList (room);
        List<Room> result = list.getRoomList ();
        assertEquals (expectedResult, result);
    }

    @Test
    void addOneRoomEmptyNameGetFalse() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House list = new House(a1, ga1);
        Room room = list.newRoom ("bedroom", 1, 2, 2.5);
        Room room1 = list.newRoom ("  ", 0, 2.5,3);


        assertEquals (0, list.getRoomList ().size ());

        list.addRoom (room);
        assertEquals (1, list.getRoomList ().size ());


        list.addRoom (room1);
        assertEquals (1, list.getRoomList ().size ());


        List<Room> expectedResult = Arrays.asList (room);
        List<Room> result = list.getRoomList ();
        assertEquals (expectedResult, result);
    }


    @Test
    void nameNotValid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House list = new House(a1, ga1);
        Room room = list.newRoom ("bedroom", 1, 2, 2.5);
        Room room1 = list.newRoom ("  ", 0, 2.5,3);

        assertEquals (0, list.getRoomList ().size ());

        list.addRoom (room);
        assertEquals (1, list.getRoomList ().size ());

        list.addRoom (room1);
        assertEquals (1, list.getRoomList ().size ());


        boolean expectedResult = false;
        String name = " ";

        boolean result = room.validateName (name);
        assertEquals (expectedResult, result);
    }

    @Test
    void addOneGetThrueAddAnotherGetFalse() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", 345, null, null, "3380-45", "Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House list = new House(a1, ga1);
        Room room = list.newRoom ("bedroom", 1, 2, 2.5);
        Room room1 = list.newRoom ("  ", 0, 2.5,3);

        assertEquals (0, list.getRoomList ().size ());

        boolean result = list.addRoom (room);
        assertEquals (1, list.getRoomList ().size ());
        assertTrue(result);

        boolean result1 = list.addRoom (room1);
        assertEquals (1, list.getRoomList ().size ());
        assertFalse(result1);

        List<Room> expectedResult = Arrays.asList (room);
        List<Room> result2 = list.getRoomList ();
        assertEquals (expectedResult, result2);
    }

}