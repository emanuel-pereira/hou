package smarthome.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HouseGridsCTRLTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findRooms() throws Exception {
        this.mockMvc.perform(get("/housegrids/1/rooms"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void inexistentGridId() throws Exception {
        this.mockMvc.perform(get("/housegrids/10/rooms"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}