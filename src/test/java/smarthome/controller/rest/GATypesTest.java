package smarthome.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.dto.TypeGADTO;
import smarthome.model.TypeGA;
import smarthome.repository.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GATypes.class)
class GATypesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeGARepository repository;

    @MockBean
    private ExternalSensorRepository externalSensorRepository;

    @MockBean
    private GeoRepository geoRepository;

    @MockBean
    private GridRepository gridRepository;

    @MockBean
    private InternalSensorRepository internalSensorRepository;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private SensorTypeRepository sensorTypeRepository;

    @Test
    void all_empty() throws Exception {
        System.out.println(mockMvc);
        this.mockMvc.perform(get("/gatypes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void newType() throws Exception {
        TypeGADTO type = new TypeGADTO("city");
        mockMvc.perform(post("/gatypes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(type)))
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}