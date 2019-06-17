package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import smarthome.model.Room;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListRoomsOfHouseCTRLTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;

    private ListRoomsOfHouseCTRL listRoomsOfHouseCTRL;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.listRoomsOfHouseCTRL = new ListRoomsOfHouseCTRL(roomRepository, gridRepository);
    }

    @Test
    @DisplayName("Check if the repository has 6 rooms with success")
    void roomListSize() {
        when(this.roomRepository.count()).thenReturn(6L);
        assertEquals(6, listRoomsOfHouseCTRL.roomListSize());
    }

    @Test
    @DisplayName("Check if the repository has no rooms")
    void roomListEmpty() {
        when(this.roomRepository.count()).thenReturn(0L);
        assertTrue(listRoomsOfHouseCTRL.roomListEmpty());
    }

    @Test
    @DisplayName("Check if the repository has rooms")
    void roomListNotEmpty() {
        when(this.roomRepository.count()).thenReturn(2L);
        assertFalse(listRoomsOfHouseCTRL.roomListEmpty());
    }

    @Test
    @DisplayName("Have 2 rooms return the right number of rooms")
    void findAll() {
        when(this.roomRepository.findAll()).thenReturn(Stream.of(new Room("B108", "Classroom", 1, 2, 3, 2),
                new Room("B201", "Classroom", 2, 2, 3, 2)).collect(Collectors.toList()));
        assertEquals(2, listRoomsOfHouseCTRL.findAll().size());
    }

}
