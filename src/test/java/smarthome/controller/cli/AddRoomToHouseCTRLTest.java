package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.model.*;
import smarthome.services.RoomService;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseRoomList;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest

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

    @Test
    @DisplayName("Create a new room with an empty repository and get the size of the repository to check if it was created")
    void createNewRoomSuccess() {

        RoomService roomService = new RoomService();

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();
        assertEquals(0,roomService.size());

        ctrl1.newAddRoom("R01", "kitchen", 1, 3, 3.5, 2);
        assertEquals(1, roomService.size());
    }


    @Test
    @DisplayName("Create a new room with no Id and get the size of the repository to check if it wasn't created")
    void cantCreateNewRoom() {

        RoomService roomService = new RoomService();

        AddRoomToHouseCTRL ctrl1 = new AddRoomToHouseCTRL();
        assertEquals(0,roomService.size());

        ctrl1.newAddRoom("R1", "kitchen", 1, 3, 3.5, 2);
        assertEquals(1, roomService.size());

        ctrl1.newAddRoom("R1", "kitchen", 1, 3, 3.5, 2);
        assertEquals(1, roomService.size());
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
