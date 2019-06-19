package smarthome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.dto.*;
import smarthome.exceptions.ExternalSensorNotFoundException;
import smarthome.exceptions.GeographicalAreaNotFoundException;
import smarthome.exceptions.SensorTypeNotFoundException;
import smarthome.mapper.ExternalSensorMapper;
import smarthome.model.*;
import smarthome.repository.ExternalSensorRepository;
import smarthome.repository.GeoRepository;
import smarthome.repository.SensorTypeRepository;
import smarthome.repository.TypeGARepository;

import java.security.InvalidParameterException;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ExternalSensorServiceTest {

    @Mock
    private ExternalSensorRepository externalSensorRepository;
    private ExternalSensorService externalSensorService;

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    private SensorTypeService sensorTypeService;

    @Mock
    private GeoRepository geoRepository;
    private GeoAreaService geoAreaService;

    @Mock
    private TypeGARepository typeGARepository;
    private GaTypesService gaTypesService;


    private LocationDTO locationDTO;
    private SensorTypeDTO sensorTypeDTO;
    private SensorBehaviorDTO sensorBehaviorDTO;
    private ExternalSensorDTO externalSensorDTO;
    private ExternalSensorDTO externalSensorDTO2;
    private GeographicalAreaDTO geographicalAreaDTO;
    private TypeGADTO typeGADTO;
    private ExternalSensorMapper mapper;
    private ExternalSensor externalSensor;
    private SensorType temperature;
    private Location location;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.sensorTypeService = new SensorTypeService(this.sensorTypeRepository);
        this.gaTypesService = new GaTypesService(this.typeGARepository);
        this.geoAreaService = new GeoAreaService(this.geoRepository, this.gaTypesService);
        this.externalSensorService = new ExternalSensorService(this.externalSensorRepository, this.geoAreaService, this.sensorTypeService);
        this.locationDTO = new LocationDTO(22, 12, 45);
        this.sensorTypeDTO = new SensorTypeDTO("temperature");
        sensorTypeDTO.setId(1L);
        sensorBehaviorDTO = new SensorBehaviorDTO("Temperature Sensor", new GregorianCalendar(2019, 5, 5), sensorTypeDTO, "C");
        externalSensorDTO = new ExternalSensorDTO("TEMP1", locationDTO, sensorBehaviorDTO, "NONEXITENTGA");
        typeGADTO = new TypeGADTO("city");
        typeGADTO.setId(2L);
        geographicalAreaDTO = new GeographicalAreaDTO("POR", "Porto", typeGADTO, new OccupationArea(25, 32), new Location(22, 32, 45));
        externalSensorDTO2 = new ExternalSensorDTO("TEMP2", locationDTO, sensorBehaviorDTO, "POR");
        mapper = new ExternalSensorMapper();
        externalSensor = mapper.toEntity(externalSensorDTO2);
        temperature = new SensorType("temperature");
        location = new Location(25, 32, 45);

    }


    @Test
    void whenRepositoryHasTwoSensorsThenReturnSize2() {
        when(this.externalSensorRepository.findAll()).thenReturn(Stream.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList()), new ExternalSensor("TEMP2", "Temperature Sensor Porto",
                new GregorianCalendar(2019, 4, 12), location, temperature, "C", new ReadingList()))
                .collect(Collectors.toList()));
        assertEquals(2, externalSensorService.findAll().size());


    }

    @Test
    void whenRepositoryFindByIdReturnsSensorThenServiceGetMethodReturnsSensorId() {
        when(this.externalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        String result = externalSensorService.get("TEMP1").getId();
        assertEquals("TEMP1", result);
    }

    @Test
    void whenRepositoryFindByIdReturnsNullThenServiceGetMethodForThatIdThrowsExternalSensorNotFoundException() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(null);
        Assertions.assertThrows(ExternalSensorNotFoundException.class, () -> externalSensorService.get("TEMP1"));
    }

    @Test
    void whenRepositorySavesExternalSensorThenServiceCreateMethodReturnsRespectiveExternalSensorDTO() {
        when(this.externalSensorRepository.save(externalSensor)).thenReturn(externalSensor);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        ExternalSensorDTO result = externalSensorService.createExternalSensor(externalSensorDTO2);
        assertEquals(externalSensorDTO2, result);
    }

    @Test
    void whenRepositorySaveReturnsFalseWithNonExistentGeoAreaThenServiceCreateMethodThrowsGeographicalAreaNotFoundException() {
        when(this.typeGARepository.existsByType(typeGADTO.getType())).thenReturn(true);
        when(this.externalSensorRepository.existsById("TEMP2")).thenReturn(false);
        Assertions.assertThrows(GeographicalAreaNotFoundException.class, () -> externalSensorService.createExternalSensor(externalSensorDTO2));
    }

    @Test
    void whenRepositorySaveReturnsFalseWithUnexistentSensorTypeThenServiceCreateMethodThrowsSensorTypeNotFoundException() {
        when(this.externalSensorRepository.existsById("TEMP2")).thenReturn(false);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        Assertions.assertThrows(SensorTypeNotFoundException.class, () -> externalSensorService.createExternalSensor(externalSensorDTO2));
    }


    @Test
    void repositorySuccessfullyDeletesExternalSensor() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(externalSensor)).thenReturn(null);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        String deletedExternalSensorId = externalSensorService.removeSensor("TEMP2").getId();
        assertEquals("TEMP2", deletedExternalSensorId);
    }

    @Test
    void repositorySuccessfullyDeactivatesExternalSensor() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(externalSensor));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        boolean result = externalSensorService.deactivate("TEMP2", new GregorianCalendar(2019, 7, 7)).getSensorBehaviorDTO().isActive();
        assertFalse(result);
    }

    @Test
    void serviceThrowsIllegalArgumentExceptionWhenDeactivatingSameSensorTwice() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(externalSensor));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        externalSensorService.deactivate("TEMP2", new GregorianCalendar(2019, 7, 7));
        Assertions.assertThrows(IllegalArgumentException.class, () -> externalSensorService.deactivate("TEMP2", new GregorianCalendar(2019, 7, 7)));
    }

    @Test
    void serviceThrowsInvalidParameterExceptionWhenPauseDateBeforeStartDate() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(externalSensor));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        Assertions.assertThrows(InvalidParameterException.class, () -> externalSensorService.deactivate("TEMP2", new GregorianCalendar(2016, 7, 7)));
    }

    @Test
    public void testInstanceOf() {
        ExternalSensorService externalSensorService = new ExternalSensorService();
        assertThat(externalSensorService).isInstanceOf(ExternalSensorService.class);
    }

    @Test
    public void testInit() {
        externalSensorRepository = null;
        ExternalSensorService externalSensorService1 = new ExternalSensorService();
        externalSensorService1.init();
        assertThat(externalSensorRepository).isNull();
    }


}