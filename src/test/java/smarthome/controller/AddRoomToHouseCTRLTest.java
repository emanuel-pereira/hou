package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class AddRoomToHouseCTRLTest {

    /**
     * Create a new room in the house with an empty room list and get the size of the room list to check if it was created
     */

    @Test
    void createNewRoom() {

        Address a1 = new Address("Rua Júlio Dinis", "3380-45", "Porto", 25, 34, 15);
        Location loc = new Location(25, 35, 15);
        OccupationArea oc = new OccupationArea(40, 45);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", oc, loc);

        House house1 = new House(a1, g1);
        RoomList roomList = house1.getRoomList();

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL(house1);
        assertEquals(0, house1.getRoomList().getRoomList().size());

        ctrl1.newAddRoom("kitchen", 1, 3, 3.5, 2);
        assertEquals(1, roomList.getRoomList().size());
    }


    /**
     * Try to create a new room with no name in the house with an empty room list and get the size of the room list
     * to check that it wasn't created
     */

    @Test
    void cantCreateNewRoom() {
        Address a1 = new Address("Rua Júlio Dinis", "3380-45", "Porto", 22, 32, 8);

        Location loc = new Location(25, 35, 15);
        OccupationArea oc = new OccupationArea(40, 45);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", oc, loc);

        House house2 = new House(a1, g1);

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL(house2);
        assertEquals(0, house2.getRoomList().getRoomList().size());

        ctrl1.newAddRoom(" ", 1, 3, 3.5, 2);
        assertEquals(0, house2.getRoomList().getRoomList().size());
    }

    /**
     * Create a new correct room and checked if the room was created (true). Check if the second one, that has an empty
     * name wasn't created (false).
     */

    @Test
    void createOneRoomSuccessAnotherFail() {
        Address a1 = new Address("Rua Júlio Dinis", "3380-45", "", 41, 12.3, 110);
        Location loc = new Location(25, 35, 15);
        OccupationArea oc = new OccupationArea(40, 45);

        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", oc, loc);

        House house2 = new House(a1, g1);

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL(house2);
        assertEquals(0, house2.getRoomList().getRoomList().size());

        boolean result = ctrl1.newAddRoom("kitchen", 1, 3, 3.5, 2);
        assertEquals(1, house2.getRoomList().getRoomList().size());
        assertTrue(result);

        boolean result1 = ctrl1.newAddRoom(" ", 1, 3, 3.5, 2);
        assertEquals(1, house2.getRoomList().getRoomList().size());
        assertFalse(result1);
    }

    @Test
    void checkIffRoomNameExists() {
        Address a1 = new Address("Rua Luis Pacheco", "3380-45", "Lisboa", 41, 12.3, 110);

        OccupationArea oc = new OccupationArea(40, 45);
        Location loc = new Location(25, 35, 15);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", oc, loc);

        House house2 = new House(a1, g1);

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL(house2);

        ctrl1.newAddRoom("kitchen", 1, 3, 3.5, 2);

        boolean result = ctrl1.checkIfRoomNameExists("kitchen");
        assertTrue(result);
    }

    @Test
    void checkIfRoomNameNotExists() {
        Address a1 = new Address("Rua Luis Pacheco", "3380-45", "Lisboa", 41, 12.3, 110);
        OccupationArea oc = new OccupationArea(40, 45);
        Location loc = new Location(25, 35, 15);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", oc, loc);

        House house2 = new House(a1, g1);

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL(house2);

        ctrl1.newAddRoom("kitchen", 1, 3, 3.5, 2);

        boolean result = ctrl1.checkIfRoomNameExists("bedroom");
        assertFalse(result);
    }

}
