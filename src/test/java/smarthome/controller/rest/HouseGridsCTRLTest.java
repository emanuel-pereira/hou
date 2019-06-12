package smarthome.controller.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import smarthome.dto.HouseGridDTO;
import smarthome.model.HouseGrid;
import smarthome.model.Room;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;
import smarthome.services.HouseGridService;
import smarthome.services.RoomService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class HouseGridsCTRLTest {

    @Mock
    private HouseGridRepository houseGridRepository;

    @Mock
    private RoomRepository roomRepository;

    private HouseGridService houseGridService;

    private RoomService roomService;

    private HouseGridsCTRL houseGridsCTRL;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.houseGridService = new HouseGridService(this.houseGridRepository);
        this.roomService = new RoomService();

        this.houseGridsCTRL = new HouseGridsCTRL(houseGridService, roomService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(houseGridsCTRL).build();

    }

    @Test
    void get_ExistingHousegrids() throws Exception {
        HouseGrid houseGrid1 = new HouseGrid(1L, "A Building");
        HouseGrid houseGrid2 = new HouseGrid(2L, "B Building");
        when(houseGridRepository.findAll()).thenReturn(Stream.of(houseGrid1, houseGrid2).collect(Collectors.toList()));
        this.mockMvc.perform(get("/housegrids")).andExpect(status().isOk());
    }

    @Test
    void add_NewHouseGrid() throws Exception {
        HouseGrid houseGrid = new HouseGrid(2L, "B Bulding");
        HouseGridDTO dto = new HouseGridDTO(2L, "B Bulding");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(dto);

        when(this.houseGridRepository.save(houseGrid)).thenReturn(houseGrid);

        this.mockMvc.perform(post("/housegrids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    void add_NewHouseGrid_NoContent_ThrowsError() throws Exception {
        this.mockMvc.perform(post("/housegrids")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_NewHouseGrid_Incomplete_ThrowsError() throws Exception {
        String jsonInString = "{'id': '1'}";

        this.mockMvc.perform(post("/housegrids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void get_ExistingHouseGrid() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        when(houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        this.mockMvc.perform(get("/housegrids/1")).andExpect(status().isOk());
    }

    @Test
    void get_NonExistingHouseGrid() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        when(houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        this.mockMvc.perform(get("/housegrids/2")).andExpect(status().isNotFound());
    }

    @Test
    void get_ExistingHouseGrid_AttachedRooms() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        when(houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        this.mockMvc.perform(get("/housegrids/1/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"A Building\",\"rooms\":[]}"));
    }

    @Test
    void get_InexistingHouseGrid_AttachedRooms() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        when(houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        this.mockMvc.perform(get("/housegrids/5/rooms"))
                .andExpect(status().isNotFound());
    }

    @Test
    void attachedRoom_inexistentGrid() throws Exception {
        when(houseGridRepository.existsById(1L)).thenReturn(false);

        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("attachId", "2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void attachedRoom_inexistentRoom() throws Exception {
        when(houseGridRepository.existsById(1L)).thenReturn(true);
        when(roomRepository.existsById("2")).thenReturn(false);


        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("attachId", "2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void attachedRoom_illegalUpdate() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        Room room = new Room("2", "B107", 1, 6, 5, 3.5);
        room.setHouseGrid(houseGrid);
        when(houseGridRepository.existsById(1L)).thenReturn(true);
        when(roomRepository.existsById("2")).thenReturn(true);
        when(houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        when(roomRepository.findById("2")).thenReturn(Optional.of(room));


        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("attachId", "2"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void attachedRoom_attachRoom() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        Room room = new Room("2", "B107", 1, 6, 5, 3.5);
        when(this.houseGridRepository.existsById(1L)).thenReturn(true);
        when(this.roomRepository.existsById("2")).thenReturn(true);
        when(this.houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        when(this.roomRepository.findById("2")).thenReturn(Optional.of(room));
        when(this.houseGridRepository.save(houseGrid)).thenReturn(houseGrid);
        when(this.roomRepository.save(room)).thenReturn(room);

        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("attachId", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void detachedRoom_inexistentGrid() throws Exception {
        when(houseGridRepository.existsById(1L)).thenReturn(false);

        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("detachId", "2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void detachedRoom_inexistentRoom() throws Exception {
        when(houseGridRepository.existsById(1L)).thenReturn(true);
        when(roomRepository.existsById("2")).thenReturn(false);


        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("detachId", "2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void detachedRoom_illegalUpdate() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        Room room = new Room("2", "B107", 1, 6, 5, 3.5);
        when(houseGridRepository.existsById(1L)).thenReturn(true);
        when(roomRepository.existsById("2")).thenReturn(true);
        when(houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        when(roomRepository.findById("2")).thenReturn(Optional.of(room));


        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("detachId", "2"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void detachedRoom_sucess() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");
        Room room = new Room("2", "B107", 1, 6, 5, 3.5);
        room.setHouseGrid(houseGrid);
        when(this.houseGridRepository.existsById(1L)).thenReturn(true);
        when(this.roomRepository.existsById("2")).thenReturn(true);
        when(this.houseGridRepository.findById(1L)).thenReturn(Optional.of(houseGrid));
        when(this.roomRepository.findById("2")).thenReturn(Optional.of(room));
        when(this.houseGridRepository.save(houseGrid)).thenReturn(houseGrid);
        when(this.roomRepository.save(room)).thenReturn(room);

        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("detachId", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void simultaneousRequests() throws Exception {
        HouseGrid houseGrid = new HouseGrid(1L, "A Building");

        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("attachId", "2")
                .param("detachId", "2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void invalidRequests() throws Exception {

        this.mockMvc.perform(post("/housegrids/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}