package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {


    @Test
    @DisplayName("Tests if two different instances of house are the same")
    void differentHouseInstancesAreEqual() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Porto", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("VNG", "Gaia", "City", 20, 20, 2, 2, 5);

        House house1 = new House("Prédio1", a1, g1);

        House house2 = new House("Prédio1", a1, g1);

        //Assert
        assertEquals(house1.hashCode(), house2.hashCode());
        assertEquals(house1, house2);
    }

    @Test
    @DisplayName("Check if two objects are the same")
    void checkIfSameHouse() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Porto", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 20, 20, 2, 2, 5);

        House house1 = new House("Prédio1", a1, g1);


        //Assert
        assertEquals(house1.hashCode(), house1.hashCode());
        assertEquals(house1, house1);
    }

    @Test
    @DisplayName("Tests if two different instances of house are different")
    void differentHouseInstancesAreNotEqual() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 34", "3380-45", "Porto", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 20, 20, 2, 2, 5);


        Address a2 = new Address("Rua Júlio Dinis, 32", "3380-45", "Porto", 41, 12.3, 110);

        House house1 = new House("Prédio1", a1, g1);
        House house2 = new House("Prédio2", a2, g1);

        //Assert
        assertNotEquals(house1.hashCode(), house2.hashCode());
        assertNotEquals(house1, house2);
    }

    @Test
    @DisplayName("Tests if two different objects are different")
    void differentObjectsAreNotEqual() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Beja", 41, 12.3, 110);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 20, 20, 2, 2, 5);

        House house1 = new House("Prédio", a1, g1);

        //Assert
        assertNotEquals(house1, t1);
    }

    @Test
    @DisplayName("Tests if returns house location")
    void getHouseLocation() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Beja", l1);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 20, 20, 2, 2, 5);

        House house1 = new House("Prédio", a1, g1);

        Location result = house1.getHouseLocation();
        Location expected = l1;
        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Tests if returns the nearest GA sensor of a given type")
    void getGASensorsOfGivenType() {

        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Beja", l1);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 41, 12.3, 110, 2, 5);

        House house1 = new House("Prédio", a1, g1);

        ReadingList rL = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 1));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        rL.addReading(r1);
        rL.addReading(r2);

        SensorType sT1 = new SensorType("temperature");
        SensorType sR1 = new SensorType("rainfall");

        ReadingList rL2 = new ReadingList();

        Sensor s1 = g1.getSensorListInGA().newSensor("TempSensor", new GregorianCalendar(2018, 12, 15), 42, 12.4, 112, sT1, "C", rL);
        Sensor s2 = g1.getSensorListInGA().newSensor("RainSensor", new GregorianCalendar(2018, 12, 15), 40, 13, 190, sR1, "Percentage", rL2);
        Sensor s3 = g1.getSensorListInGA().newSensor("RainSensor2", new GregorianCalendar(2018, 12, 15), 41, 12.3, 110, sR1, "Percentage", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);

        Sensor result = house1.findClosestGASensorByType("rainfall");

        //Assert
        assertEquals(s2, result);
    }


    //RoomList

    /**
     * Check if a new room is created and confirm that the get methods are working
     */
    @Test
    void newRoom() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);


        House h = new House("Prédio", a1, g1);
        Room room = h.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 2);

        assertEquals("bedroom", room.getName());
        assertEquals(1, room.getFloor());
        assertEquals(5, room.getArea().getOccupationArea());
    }


    /**
     * Check if a room is create and added to the RoomList
     */
    @Test
    void addOneRoom() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h1 = new House("Prédio", a1, g1);
        Room room = h1.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 2);

        h1.getRoomListFromHouse().addRoom(room);
        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = h1.getRoomListFromHouse().getRoomList();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if two rooms are create and added to the RoomList
     */
    @Test
    void addTwoRooms() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Prédio", a1, g1);
        Room room = h.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room1 = h.getRoomListFromHouse().createNewRoom("garden", 0, 2.5, 3, 2);
        assertEquals(0, h.getRoomListFromHouse().getRoomList().size());

        h.getRoomListFromHouse().addRoom(room);
        assertEquals(1, h.getRoomListFromHouse().getRoomList().size());

        h.getRoomListFromHouse().addRoom(room1);
        assertEquals(2, h.getRoomListFromHouse().getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room, room1);
        List<Room> result = h.getRoomListFromHouse().getRoomList();
        assertEquals(expectedResult, result);
    }

    /**
     * Try to add two rooms but one is empty so only the correct one is added
     */
    @Test
    void addOneRoomEmptyName() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Casa", a1, g1);
        Room room = h.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room1 = h.getRoomListFromHouse().createNewRoom("  ", 0, 2.5, 3, 2);

        assertEquals(0, h.getRoomListFromHouse().getRoomList().size());

        h.getRoomListFromHouse().addRoom(room);
        assertEquals(1, h.getRoomListFromHouse().getRoomList().size());

        h.getRoomListFromHouse().addRoom(room1);
        assertEquals(1, h.getRoomListFromHouse().getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = h.getRoomListFromHouse().getRoomList();
        assertEquals(expectedResult, result);
    }


    /**
     * Don't validate room name if empty and return false
     */
    @Test
    void nameNotValid() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Casa", a1, g1);
        Room room = h.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room1 = h.getRoomListFromHouse().createNewRoom("  ", 0, 2.5, 3, 2);

        assertEquals(0, h.getRoomListFromHouse().getRoomList().size());

        h.getRoomListFromHouse().addRoom(room);
        assertEquals(1, h.getRoomListFromHouse().getRoomList().size());

        h.getRoomListFromHouse().addRoom(room1);
        assertEquals(1, h.getRoomListFromHouse().getRoomList().size());

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
        Address a1 = new Address("Rua Júlio Dinis", "3380-45", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Casa", a1, g1);
        Room room = h.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 1.7);
        Room room1 = h.getRoomListFromHouse().createNewRoom("  ", 0, 2.5, 3, 2);

        assertEquals(0, h.getRoomListFromHouse().getRoomList().size());

        boolean result = h.getRoomListFromHouse().addRoom(room);
        assertEquals(1, h.getRoomListFromHouse().getRoomList().size());
        assertTrue(result);

        boolean result1 = h.getRoomListFromHouse().addRoom(room1);
        assertEquals(1, h.getRoomListFromHouse().getRoomList().size());
        assertFalse(result1);

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result2 = h.getRoomListFromHouse().getRoomList();
        assertEquals(expectedResult, result2);
    }


    @Test
    @DisplayName("Ensure a room is removed from the list of rooms of a house ")
    void removeRoom() {
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House house = new House("Casa", a1, g1);
        Room room1 = house.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room2 = house.getRoomListFromHouse().createNewRoom("kitchen", 1, 4, 5, 2);

        house.getRoomListFromHouse().addRoom(room1);
        house.getRoomListFromHouse().addRoom(room2);
        assertEquals(2, house.getRoomListFromHouse().getRoomList().size());
        assertTrue(house.getRoomListFromHouse().removeRoom(room2));
        assertEquals(1, house.getRoomListFromHouse().getRoomList().size());
    }

    @Test
    @DisplayName("Ensure a room not contained in the list of rooms of a house" +
            "cannot be removed")
    void removeRoomReturnsFalse() {

        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);


        House house = new House("Casa", a1, g1);
        Room room1 = house.getRoomListFromHouse().createNewRoom("bedroom", 1, 2, 2.5, 2);
        house.getRoomListFromHouse().addRoom(room1);
        Room room2 = house.getRoomListFromHouse().createNewRoom("kitchen", 1, 4, 5, 2);

        assertFalse(house.getRoomListFromHouse().removeRoom(room2));
    }

    @Test
    @DisplayName("Add a new house grid to the house grid list of a house and check that the " +
            "same house grid object cannot be added twice")
    void newHouseGrid() {
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House house = new House("Casa", a1, g1);
        HouseGrid h1 = house.newHouseGrid(5, "main grid");

        assertTrue(house.addHouseGrid(h1));

        assertEquals(1, house.getHouseGridList().size());
        //Ensure the same house grid cannot be added twice
        house.addHouseGrid(h1);
        assertEquals(1, house.getHouseGridList().size());
        //Ensure a house grid with 0 inputContractedPower cannot be added to the list
        HouseGrid h2 = house.newHouseGrid(0, "main grid");
        assertFalse(house.addHouseGrid(h2));
        assertEquals(1, house.getHouseGridList().size());
    }


}