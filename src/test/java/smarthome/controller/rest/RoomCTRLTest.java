package smarthome.controller.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.House;

import java.lang.reflect.Field;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoomCTRLTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get request")
    void findAllIfEmpty() throws Exception {
        this.mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Create a room wth success")
    void createRoomSuccess() throws Exception {
        RoomDetailDTO room = new RoomDetailDTO("B018","Classroom",2,3.0,3.5,4.0);
        mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(room)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Create a room with success and can´t create another one with the same Id")
    void createRoomWithoutSuccess() throws Exception {
        RoomDetailDTO room = new RoomDetailDTO("B018","Classroom",2,3.0,3.5,4.0);
        mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(room)))
                .andExpect(status().isCreated());

        RoomDetailDTO room1 = new RoomDetailDTO("B018","Classroom",2,3.0,3.5,4.0);
        mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(room1)))
                .andExpect(status().isUnauthorized());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
