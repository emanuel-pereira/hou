package smarthome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorType;
import smarthome.model.validations.Name;
import smarthome.repository.SensorTypeRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class SensorTypeServiceTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;

    private SensorTypeService sensorTypeService;
    Name temperature;
    Name rainfall;
    SensorTypeDTO typeTemperature;
    SensorTypeDTO typeRainfall;


    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.sensorTypeService = new SensorTypeService(this.sensorTypeRepository);
        rainfall = new Name("rainfall");
        temperature = new Name("temperature");
        typeTemperature = new SensorTypeDTO("temperature");
        typeRainfall = new SensorTypeDTO("rainfall");

    }

    @Test
    void createSensorTypeReturnsFalseWhenTypeAlreadyExistsInDB() {
        when(this.sensorTypeRepository.existsByType(temperature)).thenReturn(true);
        boolean result = sensorTypeService.createSensorType(typeTemperature);
        assertFalse(result);
    }

    @Test
    void createSensorTypeReturnsTrueWhenTypeNotExistsInDB() {
        when(this.sensorTypeRepository.existsByType(temperature)).thenReturn(false);
        boolean result = sensorTypeService.createSensorType(typeTemperature);
        assertTrue(result);
    }

    @Test
    void size() {
        when(this.sensorTypeRepository.count()).thenReturn(3L);
        assertEquals(3, sensorTypeService.size());
    }

    @Test
    void findAll() {
        when(this.sensorTypeRepository.findAll()).thenReturn(Stream.of(new SensorType("temperature"), new SensorType("rainfall")).collect(Collectors.toList()));
        assertEquals(2, sensorTypeService.findAll().size());
    }

    @Test
    void existsByTypeReturnsFalseWhenTypeNotExistsInDB() {
        when(this.sensorTypeRepository.existsByType(temperature)).thenReturn(false);
        boolean result = sensorTypeService.existsByType("temperature");
        assertFalse(result);
    }

    @Test
    void existsByTypeReturnsTrueWhenTypeExistsInDB() {
        when(this.sensorTypeRepository.existsByType(rainfall)).thenReturn(true);
        boolean result = sensorTypeService.existsByType("rainfall");
        assertTrue(result);
    }

    @Test
    void findByTypeReturnsRespectiveType() {
        when(this.sensorTypeRepository.findByType(rainfall)).thenReturn(new SensorType("rainfall"));
        SensorType expected = new SensorType("rainfall");
        SensorType result = sensorTypeService.findByType("rainfall");
        assertEquals(expected, result);
    }
    @Test
    void findByTypeRainfallNotEqualsToTemperature() {
        when(this.sensorTypeRepository.findByType(rainfall)).thenReturn(new SensorType("rainfall"));
        SensorType expected = new SensorType("temperature");
        SensorType result = sensorTypeService.findByType("rainfall");
        assertNotEquals(expected, result);
    }

    @Test
    void findByTypeOfNonExistingTypeReturnNull() {
        when(this.sensorTypeRepository.findByType(new Name("wind"))).thenReturn(null);
        Assertions.assertThrows (NullPointerException.class,()->sensorTypeService.findByType("wind"));
    }

    @Test
    void findById() {
        SensorType temperature= new SensorType("temperature");
        temperature.setId(1L);
        when(this.sensorTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(temperature));
        long resultingID=sensorTypeService.findById(1L).getId();
        assertEquals(1L,resultingID);
        String resultingType=sensorTypeService.findById(1L).getSensorType();
        assertEquals("temperature",resultingType);
    }
}
