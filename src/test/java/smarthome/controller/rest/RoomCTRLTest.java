package smarthome.controller.rest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.hateoas.Link;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.Room;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;
import smarthome.services.RoomService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class RoomCTRLTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;

    private RoomService roomService;

    private RoomCTRL roomCtrl;

    private Room roomB108;
    private Room roomB210;
    private RoomDetailDTO roomDtoB108;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.roomService = new RoomService(this.roomRepository, this.gridRepository);
        this.roomCtrl = new RoomCTRL(roomService);

        roomB108 = new Room("B108", "Classroom", 1, 3.0, 2.5, 2.0);
        roomB210 = new Room("B110", "Classroom", 2, 2.0, 3.5, 2.0);

        roomDtoB108 = new RoomDetailDTO("B108", "Classroom", 1, 3.0, 2.5, 2.0);

    }

    @Test
    @DisplayName("Find all and return SelfRefLinks for each resource in body")
    void findAllWithLinks() throws NoSuchFieldException {

        when(roomRepository.findAll()).thenReturn(Stream.of(roomB108, roomB210).collect(Collectors.toList()));

        List<Link> expected = Collections.singletonList(new Link("/rooms").withRel("self"));
        List<Link> result = Objects.requireNonNull(roomCtrl.findAll().getBody()).getLinks();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find all and return 2 rooms in resource content")
    void findAllSize() throws NoSuchFieldException {

        when(roomRepository.findAll()).thenReturn(Stream.of(roomB108, roomB210).collect(Collectors.toList()));
        int expected = 2;
        int result = Objects.requireNonNull(roomCtrl.findAll().getBody()).getContent().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find all and return 0 rooms in resource content")
    void findAllNone() throws NoSuchFieldException {

        when(roomRepository.findAll()).thenReturn(new ArrayList<>());

        int expected = 0;
        int result = Objects.requireNonNull(roomCtrl.findAll().getBody()).getContent().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find one and return Http Status 200")
    void findOneStatusOk() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(true);
        when(roomRepository.findById("B108")).thenReturn(Optional.of(roomB108));

        HttpStatus expected = HttpStatus.OK;
        HttpStatus result = roomCtrl.findOne("B108").getStatusCode();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find one and return SelfRefLinks")
    void findOneLinks() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(true);
        when(roomRepository.findById("B108")).thenReturn(Optional.of(roomB108));

        List<Link> expected = Collections.singletonList(new Link("/rooms/B108").withSelfRel());
        List<Link> result = Objects.requireNonNull(roomCtrl.findOne("B108").getBody()).getLinks();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find one and return RoomDetailDTO in body content")
    void findOneBodyDTO() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(true);
        when(roomRepository.findById("B108")).thenReturn(Optional.of(roomB108));

        String expected = "smarthome.dto.RoomDetailDTO";
        String result = Objects.requireNonNull(roomCtrl.findOne("B108").getBody()).getContent().getClass().getName();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Can´t find one and return status 404")
    void findOneStatus404() throws NoSuchFieldException {

        when(roomRepository.existsById("B100")).thenReturn(false);

        int expected = 404;
        int result = roomCtrl.findOne("B100").getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Can´t find one and return error")
    void findOneError() throws NoSuchFieldException {
        when(roomRepository.findById("B100")).thenReturn(null);
        assertTrue(roomCtrl.findOne("B100").getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("Edit room and return status 200")
    void editRoomStatus200() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(true);
        when(roomRepository.findById("B108")).thenReturn(Optional.of(roomB108));

        int expected = 200;
        int result = roomCtrl.editRoom("B108", roomDtoB108).getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Edit room and return status Not Found")
    void editRoomStatusNotFound() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(false);

        HttpStatus expected = HttpStatus.NOT_FOUND;
        HttpStatus result = roomCtrl.editRoom("B108", roomDtoB108).getStatusCode();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Edit room and return RoomDetailDTO in body content")
    void editRoomBodyDTO() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(true);
        when(roomRepository.findById("B108")).thenReturn(Optional.of(roomB108));

        String expected = "smarthome.dto.RoomDetailDTO";

        String result = Objects.requireNonNull(roomCtrl.editRoom("B108", roomDtoB108).getBody()).getContent().getClass().getName();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Create room and return Status Created")
    void createRoomStatusCreate() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(false);
        when(roomRepository.findById("B108")).thenReturn(Optional.of(roomB108));

        HttpStatus expected = HttpStatus.CREATED;
        HttpStatus result = roomCtrl.createRoom(roomDtoB108).getStatusCode();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Create room and return RoomDetailDTO in body content")
    void createRoomBodyDTO() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(false);
        when(roomRepository.findById("B108")).thenReturn(Optional.of(roomB108));

        String expected = "smarthome.dto.RoomDetailDTO";
        String result = Objects.requireNonNull(roomCtrl.createRoom(roomDtoB108).getBody()).getContent().getClass().getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Create room and return Status Conflict")
    void tryCreateRoomStatusConflict() throws NoSuchFieldException {

        when(roomRepository.existsById("B108")).thenReturn(true);

        HttpStatus expected = HttpStatus.CONFLICT;
        HttpStatus result = roomCtrl.createRoom(roomDtoB108).getStatusCode();

        assertEquals(expected, result);
    }

}
