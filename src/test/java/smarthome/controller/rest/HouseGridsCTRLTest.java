package smarthome.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.model.HouseGrid;
import smarthome.model.Room;
import smarthome.repository.Repositories;

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
    void inexistentGridId() throws Exception {
        this.mockMvc.perform(get("/housegrids/10/rooms"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void findGridButWithNoRooms() throws Exception {
        HouseGrid houseGrid1 = new HouseGrid("main grid");
        Repositories.getGridsRepository().save(houseGrid1);

        this.mockMvc.perform(get("/housegrids/2/rooms"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGridWithRooms() throws Exception {
        HouseGrid houseGrid2 = new HouseGrid("secondary grid");
        HouseGrid temp = Repositories.getGridsRepository().save(houseGrid2);

        Room newroom = new Room("B107", "Classroom", 2, 3, 4, 1);
        newroom.setHouseGrid(temp);
        Repositories.getRoomRepository().save(newroom);
        Room secondroom = new Room("B109", "Classroom", 2, 3, 4, 1);
        secondroom.setHouseGrid(temp);
        Repositories.getRoomRepository().save(secondroom);

        this.mockMvc.perform(get("/housegrids/4/rooms"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void all() throws Exception {
        HouseGrid houseGrid2 = new HouseGrid("secondary grid");
        HouseGrid temp = Repositories.getGridsRepository().save(houseGrid2);

        this.mockMvc.perform(get("/housegrids"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGrid() throws Exception {
        HouseGrid houseGrid2 = new HouseGrid("third grid");
        HouseGrid temp = Repositories.getGridsRepository().save(houseGrid2);

        this.mockMvc.perform(get("/housegrids/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGrid_notfound() throws Exception {
        HouseGrid houseGrid2 = new HouseGrid("third grid");
        HouseGrid temp = Repositories.getGridsRepository().save(houseGrid2);

        this.mockMvc.perform(get("/housegrids/111"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}