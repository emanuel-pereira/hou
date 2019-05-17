package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseRoomList;

class AddRoomToHouseCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
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

    /**
     * Create a new room in the house with an empty room list and get the size of the room list to check if it was created
     */

    @Test
    void createNewRoom() {

        RoomList roomList = getHouseRoomList();


        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();
        assertEquals(0, getHouseRoomList().getRoomList().size());

        ctrl1.newAddRoom("R01", "kitchen", 1, 3, 3.5, 2);
        assertEquals(1, roomList.getRoomList().size());
    }


    /**
     * Try to create a new room with no name in the house with an empty room list and get the size of the room list
     * to check that it wasn't created
     */

    @Test
    void cantCreateNewRoom() {


        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();
        assertEquals(0, getHouseRoomList().getRoomList().size());

        ctrl1.newAddRoom("   ", " ", 1, 3, 3.5, 2);
        assertEquals(0, getHouseRoomList().getRoomList().size());
    }

    /**
     * Create a new correct room and checked if the room was created (true). Check if the second one, that has an empty
     * name wasn't created (false).
     */

    @Test
    void createOneRoomSuccessAnotherFail() {

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();
        assertEquals(0, getHouseRoomList().getRoomList().size());

        boolean result = ctrl1.newAddRoom("R01", "kitchen", 1, 3, 3.5, 2);
        assertEquals(1, getHouseRoomList().getRoomList().size());
        assertTrue(result);

        boolean result1 = ctrl1.newAddRoom("  ", " ", 1, 3, 3.5, 2);
        assertEquals(1, getHouseRoomList().getRoomList().size());
        assertFalse(result1);
    }

    @Test
    void checkIffRoomNameExists() {


        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();

        ctrl1.newAddRoom("R01", "kitchen", 1, 3, 3.5, 2);

        boolean result = ctrl1.checkIfRoomNameExists("kitchen");
        assertTrue(result);
    }

    @Test
    void checkIfRoomNameNotExists() {


        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();

        ctrl1.newAddRoom("R01", "kitchen", 1, 3, 3.5, 2);

        boolean result = ctrl1.checkIfRoomNameExists("bedroom");
        assertFalse(result);
    }

    @Test
    void checkIffRoomIdExists() {


        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();

        ctrl1.newAddRoom("R01", "kitchen", 1, 3, 3.5, 2);

        boolean result = ctrl1.checkIfRoomIdExists("R01");
        assertTrue(result);
    }

    @Test
    void checkIfRoomIdNotExists() {


        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();

        ctrl1.newAddRoom("R01", "kitchen", 1, 3, 3.5, 2);

        boolean result = ctrl1.checkIfRoomIdExists("R1");
        assertFalse(result);
    }

}
