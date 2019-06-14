//FIXME
/*package smarthome.controller.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import smarthome.repository.GeoRepository;
import smarthome.repository.SensorTypeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GeoAreaCTRLTest {

    private static final String GeoAreaPathGetParent = "/geoareas/getParent";
    private static final String GeoAreaPathFindParent = "/geoareas/{findParentById}";
    private static final String GeoAreaPathSetParent = "/geoareas/setParent";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeoRepository geoRepository;

    @Test
    @DisplayName("Get Request with success" )
    void showListOfGAS() throws Exception {
            this.mockMvc.perform(get(GeoAreaPathGetParent)).andExpect(status().isOk());

    }

    @Test
    void findByID() {



    }

    @Test
    void setParentOfGAWebCTRL() {
    }
}*/