package smarthome.controller.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.Room;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;
import smarthome.services.RoomService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class RoomCTRLTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;

    private RoomService roomService;

    private RoomCTRL roomCtrl;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.roomService = new RoomService(this.roomRepository, this.gridRepository);
        this.roomCtrl = new RoomCTRL(roomService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(roomCtrl).build();
    }

    @Test
    @DisplayName("Find all with success")
    void findAllRooms() throws Exception {
        Room room1 = new Room("B107", "Classroom", 1, 2, 3, 1.5);
        Room room2 = new Room("B208", "Classroom", 2, 2.5, 3, 1.5);
        List<Room> rooms = Arrays.asList(room1,room2);
        RoomDTO roomDTO1 = new RoomDTO("B107", "Classroom");
        RoomDTO roomDTO2= new RoomDTO("B208", "Classroom");
        List<RoomDTO> roomsDTO = Arrays.asList(roomDTO1,roomDTO2);

        when(roomRepository.findAll()).thenReturn(rooms);

        this.mockMvc.perform(get("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(roomsDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Create a room with success")
    void createRoom() throws Exception {
        Room room = new Room("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        RoomDetailDTO roomDto = new RoomDetailDTO("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(roomDto);

        when(roomRepository.findById("B018")).thenReturn(Optional.of(room));

        this.mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Can't create a room that already exists")
    void cantCreateRoomExists() throws Exception {
        RoomDetailDTO roomDto = new RoomDetailDTO("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(roomDto);

        when(roomRepository.existsById("B018")).thenReturn(true);

        this.mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Can't create a room")
    void cantCreateRoom() throws Exception {
        String jsonInString = "{'id': 'B108'}";

        this.mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Try to find a room with success")
    void findOneRoomExists() throws Exception {
        Room room = new Room("B310", "Classroom", 3, 2.5, 2.5, 2);
        RoomDetailDTO roomDto = new RoomDetailDTO("B310", "Classroom", 3, 2.5, 2.5, 2);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(roomDto);
        when(roomRepository.existsById("B310")).thenReturn(true);
        when(roomRepository.findById("B310")).thenReturn(Optional.of(room));
        this.mockMvc.perform(get("/rooms/B310")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to find a room that doesn't exist and get not found")
    void findOneRoomDontExist() throws Exception {
        Room room = new Room("B310", "Classroom", 3, 2.5, 2.5, 2);
        when(roomRepository.existsById("B310")).thenReturn(true);
        when(roomRepository.findById("B310")).thenReturn(Optional.of(room));
        this.mockMvc.perform(get("/rooms/B31"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Edit a room with success")
    void editRoom() throws Exception {
        Room room = new Room("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        RoomDetailDTO roomDto = new RoomDetailDTO("B018", "Classroom", 0, 3.0, 2.5, 2.0);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(roomDto);

        when(roomRepository.existsById("B018")).thenReturn(true);
        when(roomRepository.findById("B018")).thenReturn(Optional.of(room));

        this.mockMvc.perform(put("/rooms/B018")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to edit a room that doesn't exist")
    void editRoomNonexistent() throws Exception {
        when(roomRepository.existsById("B108")).thenReturn(false);
        this.mockMvc.perform(get("/rooms/108")).andExpect(status().isNotFound());
    }


}
