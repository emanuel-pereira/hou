package smarthome.controller.REST;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.controller.rest.SensorTypeWeb;
import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorType;
import smarthome.repository.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SensorTypeWeb.class)
@AutoConfigureMockMvc(secure = false)
class SensorTypeWebTest {
    private static final String SENSOR_TYPE_PATH = "/sensorTypes/";
    private static final String FIRST_SENSOR_TYPE_PATH = "/sensorTypes/3";


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private SensorTypeRepository repository;
    @MockBean
    private TypeGARepository geoAreaTypeRepository;
    @MockBean
    private GeoRepository geoRepository;
    @MockBean
    private RoomRepository roomRepository;
    @MockBean
    private InternalSensorRepository internalSensorRepository;
    @MockBean
    private ExternalSensorRepository externalSensorRepository;
    @MockBean
    private GridRepository gridRepository;
    @Test
    void whenReadAll_thenStatusIsOk() throws Exception {
        this.mockMvc.perform(get(SENSOR_TYPE_PATH)).andExpect(status().isOk());
    }

    @Test
    public void whenCreate_thenStatusIsOK() throws Exception {
        SensorTypeDTO type = new SensorTypeDTO();
        type.setSensorType("temperature");
        this.mockMvc.perform(post(SENSOR_TYPE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(type)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenCreateAndDelete_thenStatusIsNotAllowedInDelete() throws Exception {
        SensorTypeDTO type = new SensorTypeDTO();
        type.setSensorType("temperature");
        //posts a new sensorType
        this.mockMvc.perform(post(SENSOR_TYPE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(type)))
                .andExpect(status().isCreated());
        //trying to delete the previously created sensorType retrieves http status 405: method not allowed
        this.mockMvc.perform(delete(SENSOR_TYPE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(type)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void whenCreateAndUpdate_thenStatusIsNotAllowedInUpdate() throws Exception {
        SensorTypeDTO type = new SensorTypeDTO();
        type.setSensorType("temperature");
        this.mockMvc.perform(post(SENSOR_TYPE_PATH)
                .content(asJsonString(type))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
        this.mockMvc.perform(put(SENSOR_TYPE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(type)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void whenCreate_statusIsOk_whenGetOneCreated_thenStatusIsOk() throws Exception {
        SensorTypeDTO type = new SensorTypeDTO();
        type.setSensorType("temperature");
        this.mockMvc.perform(post(SENSOR_TYPE_PATH)
                .content(asJsonString(type))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
        repository.save(new SensorType("temperature"));
        long x=1;
        System.out.println(repository.toString());
        System.out.println(repository.findById(x));
        this.mockMvc.perform(get(SENSOR_TYPE_PATH+1)).andExpect(status().isOk());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
