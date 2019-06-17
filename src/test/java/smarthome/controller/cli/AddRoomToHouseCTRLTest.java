package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import smarthome.model.*;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class AddRoomToHouseCTRLTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;


    private AddRoomToHouseCTRL addRoomToHouseCTRL;

    //Will be removed when RoomList is deleted
    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.addRoomToHouseCTRL = new AddRoomToHouseCTRL(roomRepository, gridRepository);
    }


    @Test
    @DisplayName("Create a new room with success")
    void createRoomSuccess() {

        Room room = new Room("B108", "Classroom", 2, 2, 3, 1.5);

        when(this.roomRepository.save(room)).thenReturn(room);

        boolean expected = addRoomToHouseCTRL.newAddRoom("B108", "Classroom", 2, 2, 3, 1.5);
        assertTrue(expected);

    }

    @Test
    @DisplayName("Check if a room exists and return false because he doesn't exists")
    void checkIfRoomIdDoesntExists() {

        when(this.roomRepository.existsById("B108")).thenReturn(false);

        boolean expected = addRoomToHouseCTRL.checkIfRoomIdExists("B108");
        assertFalse(expected);
    }

    @Test
    @DisplayName("Check if a room exists and return true because he exists")
    void checkIfRoomIdExists() {

        when(this.roomRepository.existsById("B108")).thenReturn(true);

        boolean expected = addRoomToHouseCTRL.checkIfRoomIdExists("B108");
        assertTrue(expected);
    }

}
